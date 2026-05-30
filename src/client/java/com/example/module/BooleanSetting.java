package com.example.module;

public class BooleanSetting extends Setting {
    private boolean state;

    public BooleanSetting(String name, boolean defaultState) {
        super(name);
        this.state = defaultState;
    }

    public boolean getState() { return state; }
    public void setState(boolean state) { this.state = state; }
    public void toggle() { this.state = !this.state; }
}
