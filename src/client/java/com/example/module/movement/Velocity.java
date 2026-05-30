package com.example.module.movement;

import com.example.module.Category;
import com.example.module.Module;
import com.example.module.SliderSetting;

public class Velocity extends Module {
    public static SliderSetting horizontalModifier;
    public static SliderSetting verticalModifier;

    public Velocity() {
        super("Velocity", Category.MOVEMENT);
        horizontalModifier = new SliderSetting("Horizontal", 0.6, 0.0, 1.0, 0.05);
        verticalModifier = new SliderSetting("Vertical", 1.0, 0.0, 1.0, 0.05);
        addSetting(horizontalModifier);
        addSetting(verticalModifier);
    }
}
