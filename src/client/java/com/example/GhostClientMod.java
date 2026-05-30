package com.example;

import com.example.gui.GhostClientScreen;
import com.example.module.Module;
import com.example.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class GhostClientMod implements ClientModInitializer {
    private static KeyBinding menuKeyBind;

    @Override
    public void onInitializeClient() {
        menuKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ghostclient.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.ghostclient.general"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            if (menuKeyBind.wasPressed() && client.currentScreen == null) {
                client.setScreen(new GhostClientScreen());
            }

            for (Module module : ModuleManager.getInstance().getModules()) {
                if (module.isEnabled()) {
                    module.onUpdate();
                }
            }
        });
    }
}
