package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.example.gui.GhostClientScreen;
import com.example.module.ModuleManager;
import com.example.module.Module;

public class GhostClientMod implements ClientModInitializer {
    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        // 1. Initialisiere den ModuleManager direkt beim Spielstart
        ModuleManager.getInstance();

        // Register key binding - Right Shift to open menu
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ghostclient.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.ghostclient"
        ));

        // Register client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Menü öffnen bei Tastendruck
            while (openMenuKey.wasPressed()) {
                if (client.player != null && client.currentScreen == null) {
                    client.setScreen(new GhostClientScreen());
                }
            }

            // 2. Führe die Logik aller aktivierten Module in jedem Tick aus
            if (client.player != null) {
                for (Module module : ModuleManager.getInstance().getModules()) {
                    if (module.isEnabled()) {
                        module.onTick();
                    }
                }
            }
        });
    }
}
