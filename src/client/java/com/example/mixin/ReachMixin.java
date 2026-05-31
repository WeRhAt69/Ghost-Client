package com.example.mixin;

import com.example.module.ModuleManager;
import com.example.module.combat.Reach;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class ReachMixin {
    
    @Inject(method = "getEntityInteractionRange", at = @At("HEAD"), cancellable = true)
    private void modifyReach(CallbackInfoReturnable<Double> info) {
        // Holt das registrierte Reach-Modul über den Manager
        Reach reachModule = (Reach) ModuleManager.getInstance().getModuleByName("Reach");
        
        if (reachModule != null && reachModule.isEnabled()) {
            // Setzt die Reichweite exakt auf den eingestellten Slider-Wert (z. B. 3.15 Blöcke)
            info.setReturnValue(Reach.reachDistance.getValue());
        }
    }
}
