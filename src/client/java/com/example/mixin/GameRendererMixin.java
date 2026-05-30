package com.example.mixin;

import com.example.module.ModuleManager;
import com.example.module.combat.Reach;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    
    @ModifyConstant(method = "updateTargetedEntity", constant = @Constant(doubleValue = 3.0))
    private double getReachDistance(double originalTargetDistance) {
        Reach reachModule = (Reach) ModuleManager.getInstance().getModuleByName("Reach");
        
        if (reachModule != null && reachModule.isEnabled()) {
            // Gibt den stufenlos einstellbaren Wert des GUI-Sliders zurück (Standard: 3.6)
            return reachModule.getReachSliderValue();
        }
        return originalTargetDistance;
    }
}
