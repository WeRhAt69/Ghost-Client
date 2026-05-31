package com.example.module.movement;

import com.example.module.Module;
import com.example.module.Category;
import com.example.module.SliderSetting;

public class Velocity extends Module {
    // Statische Einstellungen, damit das Mixin darauf zugreifen kann
    public static SliderSetting horizontal = new SliderSetting("Horizontal %", 82.0, 50.0, 100.0);
    public static SliderSetting vertical = new SliderSetting("Vertical %", 100.0, 50.0, 100.0);

    public Velocity() {
        super("Velocity", Category.MOVEMENT);
        addSetting(horizontal);
        addSetting(vertical);
    }
}
