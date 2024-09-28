package com.penguinmod.entity.goal;

import com.penguinmod.entity.custom.PenguinEntity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class WanderInWaterGoal extends MoveToTargetPosGoal {
    private final PenguinEntity penguin;

    public WanderInWaterGoal(PenguinEntity penguin, double speed) {
        super(penguin, penguin.isBaby() ? 2.0 : speed, 24);
        this.penguin = penguin;
        this.lowestY = -1;
    }

    @Override
    public boolean shouldContinue() {
        return !this.penguin.isTouchingWater() && this.tryingTime <= 1200 && this.isTargetPos(this.penguin.getWorld(), this.targetPos);
    }

    @Override
    public boolean canStart() {
        if (this.penguin.isBaby() && !this.penguin.isTouchingWater()) {
            return super.canStart();
        } else {
            return !this.penguin.isLandBound() && !this.penguin.isTouchingWater() && !this.penguin.hasEgg() ? super.canStart() : false;
        }
    }

    @Override
    public boolean shouldResetPath() {
        return this.tryingTime % 160 == 0;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.getBlockState(pos).isOf(Blocks.WATER);
    }
    
}