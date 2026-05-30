package com.example.module;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private final String name;
    private final Category category;
    private boolean enabled;
    private final List<Setting> settings = new ArrayList<>();
    private boolean showSettings = false; // Wird durch Rechtsklick im GUI umschaltbar

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public List<Setting> getSettings() { return settings; }
    
    public boolean isShowSettings() { return showSettings; }
    public void toggleShowSettings() { this.showSettings = !this.showSettings; }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onEnable(); else onDisable();
    }

    public void toggle() { setEnabled(!this.enabled); }
    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
}
