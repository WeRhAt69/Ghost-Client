package com.example.module;

public abstract class Module {
    private final String name;
    private final Category category;
    private boolean enabled;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public String getName() { 
        return name; 
    }

    public Category getCategory() { 
        return category; 
    }

    public boolean isEnabled() { 
        return enabled; 
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void toggle() {
        setEnabled(!this.enabled);
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
}
