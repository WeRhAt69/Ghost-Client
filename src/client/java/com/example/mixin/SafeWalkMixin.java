package com.example.mixin;

import com.example.module.movement.LegitScaffold;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientPlayerEntity.class)
public class SafeWalkMixin {

    @ModifyVariable(method = "move", at = @At("HEAD"), argsOnly = true)
    private Vec3d onMove(Vec3d movement) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        if (LegitScaffold.shouldSafeWalk()) {
            double x = movement.x;
            double z = movement.z;
            double y = movement.y;

            if (!player.getWorld().getCollisions(player, player.getBoundingBox().offset(x, -1.0, 0)).iterator().hasNext()) x = 0;
            if (!player.getWorld().getCollisions(player, player.getBoundingBox().offset(0, -1.0, z)).iterator().hasNext()) z = 0;
            
            return new Vec3d(x, y, z);
        }
        return movement;
    }
}
