package com.example.module.movement;

import com.example.module.Module;
import com.example.module.Category;
import com.example.module.SliderSetting;

public class Velocity extends Module {
    public static SliderSetting horizontal = new SliderSetting("Horizontal %", 85.0, 0.0, 100.0);
    public static SliderSetting vertical = new SliderSetting("Vertical %", 100.0, 0.0, 100.0);

    public Velocity() {
        super("Velocity", Category.MOVEMENT);
        addSetting(horizontal);
        addSetting(vertical);
    }
}
