package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.example.gui.GhostClientScreen;

public class GhostClientMod implements ClientModInitializer {
    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        // Register key binding - Right Shift to open menu
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ghostclient.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.ghostclient"
        ));

        // Register client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                client.setScreen(new GhostClientScreen());
            }
        });
    }
}
