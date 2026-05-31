package com.example.module.movement;

import com.example.module.Module;
import com.example.module.Category;
import net.minecraft.client.MinecraftClient;

public class LegitScaffold extends Module {
    private static LegitScaffold instance;
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public LegitScaffold() {
        super("Scaffold", Category.MOVEMENT);
        instance = this;
    }

    public static boolean shouldSafeWalk() {
        return instance != null && instance.isEnabled() && instance.mc.player != null && instance.mc.player.isOnGround();
    }
}
