package com.example.module.combat;

import com.example.module.Module;
import com.example.module.Category;
import com.example.module.SliderSetting;

public class Reach extends Module {
    // Statische Einstellung für das Mixin (Standard auf deine gewünschten 3.15 Blöcke)
    public static SliderSetting reachDistance = new SliderSetting("Distance", 3.15, 3.0, 3.5);

    public Reach() {
        super("Reach", Category.COMBAT);
        addSetting(reachDistance);
    }
}
