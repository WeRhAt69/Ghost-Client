package com.example.module.movement;

import com.example.module.Module;
import com.example.module.Category;
import net.minecraft.client.MinecraftClient;

public class VapeScaffold extends Module {
    private static VapeScaffold instance;
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public VapeScaffold() {
        super("Scaffold", Category.MOVEMENT);
        instance = this;
    }

    public static boolean shouldSafeWalk() {
        return instance != null && instance.isEnabled() && instance.mc.player != null && instance.mc.player.isOnGround();
    }
}
