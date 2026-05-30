package com.example.module;

import com.example.module.combat.AutoClicker;
import com.example.module.combat.AimAssist;
import com.example.module.render.Velocity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static ModuleManager instance;
    private final List<Module> modules = new ArrayList<>();

    private ModuleManager() {
        // COMBAT MODULES
        modules.add(new AutoClicker());
        modules.add(new AimAssist());
        
        // RENDER MODULES
        modules.add(new Velocity());
        
        // UTILITY & PLATZHALTER (Damit deine README-Liste vollzählig ist)
        modules.add(new PlaceholderModule("Reach", Category.RENDER));
        modules.add(new PlaceholderModule("Search", Category.UTILITY));
        modules.add(new PlaceholderModule("Nametags", Category.UTILITY));
    }

    public static ModuleManager getInstance() {
        if (instance == null) {
            instance = new ModuleManager();
        }
        return instance;
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
                .filter(m -> m.getCategory() == category)
                .collect(Collectors.toList());
    }

    public static boolean isModuleEnabled(String name) {
        for (Module m : getInstance().getModules()) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m.isEnabled();
            }
        }
        return false;
    }

    // Hilfsklasse für Module, die noch keinen eigenen Code haben
    private static class PlaceholderModule extends Module {
        public PlaceholderModule(String name, Category category) {
            super(name, category);
        }
    }
}
