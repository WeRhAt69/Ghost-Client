package com.example.module;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private final String name;
    private final Category category;
    private final List<Setting> settings = new ArrayList<>();
    private boolean enabled = false;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public List<Setting> getSettings() { return settings; }
    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) onEnable();
            else onDisable();
        }
    }

    public void toggle() {
        setEnabled(!this.enabled);
    }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
}
