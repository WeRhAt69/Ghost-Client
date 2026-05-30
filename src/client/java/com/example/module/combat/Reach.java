package com.example.module.combat;

import com.example.module.Category;
import com.example.module.Module;
import com.example.module.SliderSetting;

public class Reach extends Module {
    public static SliderSetting reachDistance;

    public Reach() {
        super("Reach", Category.COMBAT);
        reachDistance = new SliderSetting("Distance", 3.6, 3.0, 5.0, 0.1);
        addSetting(reachDistance);
    }
}
