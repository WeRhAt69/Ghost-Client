package com.example.module.combat;

import com.example.module.Category;
import com.example.module.Module;
import com.example.module.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class AimAssist extends Module {
    
    // Slider für die GUI registrieren (Genau wie bei Vape)
    public static SliderSetting reachDistance;
    public static SliderSetting fovCircle;
    public static SliderSetting smoothSpeed;

    public AimAssist() {
        super("AimAssist", Category.COMBAT);
        
        // Settings: Name, Standardwert, Minimum, Maximum, Schrittweite
        reachDistance = new SliderSetting("Range", 4.0, 3.0, 6.0, 0.1);
        fovCircle = new SliderSetting("FOV Circle", 35.0, 5.0, 180.0, 1.0); // Sichtkreis in Grad
        smoothSpeed = new SliderSetting("Smooth", 0.15, 0.05, 1.0, 0.01);  // Verfolgungs-Geschwindigkeit
        
        addSetting(reachDistance);
        addSetting(fovCircle);
        addSetting(smoothSpeed);
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null || mc.currentScreen != null) {
            return;
        }

        // Findet das nächste Ziel, das sich mathematisch innerhalb des eingestellten Kreises befindet
        Entity target = getClosestTargetInCircle(mc);
        if (target == null) {
            return;
        }

        // 1. Dreidimensionale Vektordifferenz berechnen
        double deltaX = target.getX() - mc.player.getX();
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

        // 4. Interpolierte Anpassung unter Verwendung des "Smooth"-Sliders
        double speed = smoothSpeed.getValue();
        mc.player.setYaw(mc.player.getYaw() + (float) (yawDelta * speed));
        mc.player.setPitch(mc.player.getPitch() + (float) (pitchDelta * speed));
    }

    /**
     * Sucht den am nächsten stehenden Gegner, filtert aber alle heraus,
     * die außerhalb des eingestellten FOV-Kreises liegen.
     */
    private Entity getClosestTargetInCircle(MinecraftClient mc) {
        Entity closestEntity = null;
        double closestDistance = reachDistance.getValue();

        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof PlayerEntity && entity != mc.player && entity.isAlive()) {
                
                // Reichweiten-Prüfung via Slider
                double distance = mc.player.distanceTo(entity);
                if (distance > closestDistance) continue;

                // --- MATHEMATISCHE KREIS-PRÜFUNG (FOV FILTER) ---
                double deltaX = entity.getX() - mc.player.getX();
                double deltaZ = entity.getZ() - mc.player.getZ();
                float expectedYaw = (float) (Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0);
                
                // Berechne den absoluten Winkelabstand zwischen Fadenkreuz und Gegner
                float angleDifference = Math.abs(MathHelper.wrapDegrees(expectedYaw - mc.player.getYaw()));

                // Wenn der Gegner weiter vom Fadenkreuz weg ist als der eingestellte Kreis, ignoriere ihn
                if (angleDifference > fovCircle.getValue()) {
                    continue; 
                }
                // -------------------------------------------------

                closestDistance = distance;
                closestEntity = entity;
            }
        }
        return closestEntity;
    }
}
