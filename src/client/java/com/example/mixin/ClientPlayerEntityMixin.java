package com.example.mixin;

import com.example.module.ModuleManager;
import com.example.module.movement.Velocity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    
    @Inject(method = "setVelocityClient", at = @At("HEAD"), cancellable = true)
    private void onSetVelocity(double x, double y, double z, CallbackInfo ci) {
        Velocity velocityModule = (Velocity) ModuleManager.getInstance().getModuleByName("Velocity");
        
        if (velocityModule != null && velocityModule.isEnabled()) {
            ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;
            
            // Holt die anpassbaren Schieberegler-Prozentwerte (z.B. 0.0 für 0% Rückstoß)
            double horizontalFactor = velocityModule.getHorizontalSliderValue();
            double verticalFactor = velocityModule.getVerticalSliderValue();
            
            player.setVelocity(x * horizontalFactor, y * verticalFactor, z * horizontalFactor);
            ci.cancel(); // Überschreibt die originale Minecraft-Rückstoßberechnung
        }
    }
}
