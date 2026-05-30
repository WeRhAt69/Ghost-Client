package com.example.module;

import com.example.module.combat.AutoClicker;
import com.example.module.combat.AimAssist;
import com.example.module.combat.Reach;
import com.example.module.movement.Velocity;
import com.example.module.utility.Search;
import com.example.module.utility.Nametags;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static ModuleManager instance;
    private final List<Module> modules = new ArrayList<>();

    private Grid() {} // Verhindert Instanziierung außerhalb
    private ModuleManager() {
        // COMBAT MODULES
        modules.add(new AutoClicker());
        modules.add(new AimAssist());
        modules.add(new Reach());
        
        // MOVEMENT MODULES
        modules.add(new Velocity());
        
        // UTILITY MODULES
        modules.add(new Search());
        modules.add(new Nametags());
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
}
