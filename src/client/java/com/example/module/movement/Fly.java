package com.example.module.movement;

import com.example.module.Module;
import com.example.module.Category;
import com.example.module.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class Fly extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final SliderSetting flySpeed = new SliderSetting("Fly Speed", 1.0, 0.2, 3.0);

    public Fly() {
        super("Fly", Category.MOVEMENT);
        addSetting(flySpeed);
    }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.player == null) return;

        double speed = flySpeed.getValue();
        mc.player.getAbilities().flying = false;

        double ySpeed = 0;
        if (mc.options.jumpKey.isPressed()) ySpeed = speed * 0.5;
        else if (mc.options.sneakKey.isPressed()) ySpeed = -speed * 0.5;

        Vec3d move = mc.player.getVelocity();
        mc.player.setVelocity(move.x, ySpeed, move.z);
    }

    @Override
    public void onDisable() {
        if (mc.player != null && !mc.player.isCreative()) {
            mc.player.getAbilities().flying = false;
        }
    }
}
