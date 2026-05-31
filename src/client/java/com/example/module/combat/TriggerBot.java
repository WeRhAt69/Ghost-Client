package com.example.module.combat;

import com.example.module.Module;
import com.example.module.Category;
import com.example.module.SliderSetting;
import com.example.module.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class TriggerBot extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    
    private final SliderSetting delay = new SliderSetting("Delay MS", 110.0, 50.0, 300.0);
    private final BooleanSetting playersOnly = new BooleanSetting("Players Only", true);
    
    private long lastAttackTime = 0;

    public TriggerBot() {
        super("TriggerBot", Category.COMBAT);
        addSetting(delay);
        addSetting(playersOnly);
    }

    public void onTick() {
        if (!isEnabled() || mc.player == null || mc.currentScreen != null) return;

        // Prüfen, ob das Fadenkreuz auf einer Entität steht
        if (mc.crosshairTarget != null && mc.crosshairTarget.getType() == HitResult.Type.ENTITY) {
            EntityHitResult hit = (EntityHitResult) mc.crosshairTarget;
            
            // Filter für Spieler
            if (playersOnly.getValue() && !(hit.getEntity() instanceof PlayerEntity)) return;
            
            // Schutz vor Teammitgliedern (Falls das Teams-Modul aktiv ist)
            if (hit.getEntity() instanceof PlayerEntity && Teams.isTeammate((PlayerEntity) hit.getEntity())) return;

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastAttackTime >= delay.getValue()) {
                if (mc.interactionManager != null) {
                    // Simuliert den Klick
                    mc.interactionManager.attackEntity(mc.player, hit.getEntity());
                    mc.player.swingHand(Hand.MAIN_HAND);
                    lastAttackTime = currentTime;
                }
            }
        }
    }
}
