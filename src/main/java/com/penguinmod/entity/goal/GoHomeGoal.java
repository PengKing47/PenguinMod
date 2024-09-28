package com.penguinmod.entity.goal;

import com.penguinmod.entity.custom.PenguinEntity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GoHomeGoal extends Goal {
    private final PenguinEntity penguin;
    private final double speed;
    private boolean noPath;
    private int homeReachingTryTicks;

    public GoHomeGoal(PenguinEntity penguin, double speed) {
        this.penguin = penguin;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        if (this.penguin.isBaby()) {
            return false;
        } else if (this.penguin.hasEgg()) {
            return true;
        } else {
            return this.penguin.getRandom().nextInt(toGoalTicks(700)) != 0 ? false : !this.penguin.getHomePos().isWithinDistance(this.penguin.getPos(), 64.0);
        }
    }

    @Override
    public void start() {
        this.penguin.setLandBound(true);
        this.noPath = false;
        this.homeReachingTryTicks = 0;
    }

    @Override
    public void stop() {
        this.penguin.setLandBound(false);
    }

    @Override
    public boolean shouldContinue() {
        return !this.penguin.getHomePos().isWithinDistance(this.penguin.getPos(), 7.0) && !this.noPath && this.homeReachingTryTicks <= this.getTickCount(600);
    }

    @Override
    public void tick() {
        BlockPos blockPos = this.penguin.getHomePos();
        boolean bl = blockPos.isWithinDistance(this.penguin.getPos(), 16.0);
        if (bl) {
            this.homeReachingTryTicks++;
        }

        if (this.penguin.getNavigation().isIdle()) {
            Vec3d vec3d = Vec3d.ofBottomCenter(blockPos);
            Vec3d vec3d2 = NoPenaltyTargeting.findTo(this.penguin, 16, 3, vec3d, (float) (Math.PI / 10));
            if (vec3d2 == null) {
                vec3d2 = NoPenaltyTargeting.findTo(this.penguin, 8, 7, vec3d, (float) (Math.PI / 2));
            }

            if (vec3d2 != null && !bl && !this.penguin.getWorld().getBlockState(BlockPos.ofFloored(vec3d2)).isOf(Blocks.WATER)) {
                vec3d2 = NoPenaltyTargeting.findTo(this.penguin, 16, 5, vec3d, (float) (Math.PI / 2));
            }

            if (vec3d2 == null) {
                this.noPath = true;
                return;
            }

            this.penguin.getNavigation().startMovingTo(vec3d2.x, vec3d2.y, vec3d2.z, this.speed);
        }
    }
}