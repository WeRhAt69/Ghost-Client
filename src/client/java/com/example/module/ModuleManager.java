package com.example.module;

import com.example.module.combat.*;
import com.example.module.movement.*;
import com.example.module.render.*;
import com.example.module.utility.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static ModuleManager instance;
    private final List<Module> modules = new ArrayList<>();

    private ModuleManager() {
        // ⚔️ Combat
        modules.add(new AutoClicker());
        modules.add(new AimAssist());
        modules.add(new Reach());
        modules.add(new TriggerBot());
        modules.add(new Teams());

        // 🏃 Movement
        modules.add(new Velocity());
        modules.add(new Sprint());
        modules.add(new Speed());
        modules.add(new Fly());
        modules.add(new Strafe());

        // 👁️ Render
        modules.add(new ESP());
        modules.add(new Chams());
        modules.add(new Fullbright());

        // 🛠️ Utility
        modules.add(new Search());
        modules.add(new Nametags());
        modules.add(new FastPlace());
        modules.add(new AntiAFK());
        modules.add(new Throwpot());
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

    public Module getModuleByName(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
