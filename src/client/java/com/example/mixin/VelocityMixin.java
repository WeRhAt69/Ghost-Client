package com.example.mixin;

import com.example.module.ModuleManager;
import com.example.module.movement.Velocity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class VelocityMixin {

    @Inject(method = "setVelocityClient", at = @At("HEAD"), cancellable = true)
    private void onSetVelocity(double x, double y, double z, CallbackInfo info) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;
        Velocity velocityModule = (Velocity) ModuleManager.getInstance().getModuleByName("Velocity");

        if (velocityModule != null && velocityModule.isEnabled()) {
            // Berechnet die Prozentwerte aus den Vape-GUI-Slidern (z. B. 82% -> 0.82)
            double pctH = Velocity.horizontal.getValue() / 100.0;
            double pctV = Velocity.vertical.getValue() / 100.0;

            // Dämpft die Bewegung auf den Achsen ab
            player.setVelocity(x * pctH, y * pctV, z * pctH);
            
            // Verhindert, dass das normale Minecraft den vollen Rückstoß anwendet
            info.cancel();
        }
    }
}
