package com.penguinmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.penguinmod.effect.ModEffects;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

@Mixin(BoatEntity.class)
public class BoatMixin {
    
    @Inject(at = @At("TAIL"), method = "tick")
    private void injectMethod(CallbackInfo info) {
        BoatEntity boat = (BoatEntity) (Object) this;
        if(boat.hasPassengers() && boat.getFirstPassenger() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) boat.getFirstPassenger();

            MinecraftClient client = MinecraftClient.getInstance();
            if(player.hasStatusEffect(ModEffects.PENGUINS_GRACE) && client.options.forwardKey.isPressed()){

                // Get the current yaw of the boat (rotation around the vertical axis)
                float yaw = boat.getYaw();

                // Convert yaw to radians
                float yawRad = (float) Math.toRadians(yaw);

                // Calculate the directional vector based on yaw
                double directionX = -MathHelper.sin(yawRad);
                double directionZ = MathHelper.cos(yawRad);

                // Get the current velocity of the boat
                Vec3d velocity = boat.getVelocity().normalize();

                // Define the speed or modify the existing velocity magnitude
                double speed = velocity.length() * 1.25;

                // Set the new velocity aligned with the boat's direction
                boat.setVelocity(directionX * speed, velocity.y, directionZ * speed);

            }
        }
    }
}
