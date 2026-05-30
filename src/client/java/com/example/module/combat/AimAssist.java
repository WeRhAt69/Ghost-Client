package com.example.module.combat;

import com.example.module.Category;
import com.example.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class AimAssist extends Module {
    // 0.15 bedeutet, dass sich das Fadenkreuz pro Tick um 15% dem Ziel annähert (sehr weich)
    private final double SMOOTHING = 0.15; 
    private final double MAXIMUM_RANGE = 4.5; // Maximale Such-Reichweite in Blöcken

    public AimAssist() {
        super("AimAssist", Category.COMBAT);
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null || mc.currentScreen != null) {
            return;
        }

        // Findet das am nächsten stehende, gültige Ziel
        Entity target = getClosestTarget(mc);
        if (target == null) {
            return;
        }

        // 1. Dreidimensionale Vektordifferenz berechnen
        double deltaX = target.getX() - mc.player.getX();
        // Visiert den Oberkörper/Halsbereich an (Augenhöhe abzüglich eines kleinen Offsets)
        double targetHeight = target.getY() + target.getStandingEyeHeight() - 0.35;
        double localHeight = mc.player.getY() + mc.player.getStandingEyeHeight();
        double deltaY = targetHeight - localHeight;
        double deltaZ = target.getZ() - mc.player.getZ();

        // 2. Sphärische Trigonometrie: Umrechnung der Raumachsen in Rotationsgrade
        double horizontalDistance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        float targetYaw = (float) (Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0);
        float targetPitch = (float) (-Math.toDegrees(Math.atan2(deltaY, horizontalDistance)));

        // 3. Winkel-Differenzen normalisieren, um 360-Grad-Umdrehungsfehler zu verhindern
        float yawDelta = MathHelper.wrapDegrees(targetYaw - mc.player.getYaw());
        float pitchDelta = targetPitch - mc.player.getPitch();

        // 4. Interpolierte Anpassung (Schrittweise Annäherung anstatt direktes Aufschalten)
        mc.player.setYaw(mc.player.getYaw() + (float) (yawDelta * this.SMOOTHING));
        mc.player.setPitch(mc.player.getPitch() + (float) (pitchDelta * this.SMOOTHING));
    }

    /**
     * Durchsucht alle geladenen Entitäten in der Welt und filtert nach dem nächsten lebenden Spieler.
     */
    private Entity getClosestTarget(MinecraftClient mc) {
        Entity closestEntity = null;
        double closestDistance = this.MAXIMUM_RANGE;

        for (Entity entity : mc.world.getEntities()) {
            // Nur andere, lebende Spieler ins Visier nehmen
            if (entity instanceof PlayerEntity && entity != mc.player && entity.isAlive()) {
                double distance = mc.player.distanceTo(entity);
                
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestEntity = entity;
                }
            }
        }
        return closestEntity;
    }
}
