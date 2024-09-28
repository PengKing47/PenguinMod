package com.penguinmod.entity.goal;

import com.penguinmod.block.ModBlocks;
import com.penguinmod.block.PenguinEggBlock;
import com.penguinmod.entity.custom.PenguinEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class LayEggGoal extends MoveToTargetPosGoal {

    private final PenguinEntity penguin;

	public LayEggGoal(PenguinEntity penguin, double speed) {
		super(penguin, speed, 16);
		this.penguin = penguin;
	}

	@Override
	public boolean canStart() {
		return this.penguin.hasEgg() && !penguin.isInFluid();
	}

	@Override
	public boolean shouldContinue() {
		return super.shouldContinue() && this.penguin.hasEgg() && !penguin.isInFluid();
	}

	@Override
	public void tick() {
		super.tick();
		World world = this.penguin.getWorld();
		BlockPos blockPos = this.penguin.getBlockPos();
		BlockState blockState = ModBlocks.PENGUIN_EGG.getDefaultState().with(PenguinEggBlock.EGGS, Integer.valueOf(1));
		if (blockState != null && !world.getBlockState(blockPos.down()).isOf(Blocks.WATER) && !world.getBlockState(blockPos).isOf(Blocks.WATER)) {
			blockState = Block.postProcessState(blockState, this.penguin.getWorld(), blockPos);
			world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);
			world.emitGameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Emitter.of(this.penguin, blockState));
			this.penguin.setHasEgg(false);
			this.penguin.setLoveTicks(600);
		}
	}

	@Override
	protected boolean isTargetPos(WorldView world, BlockPos pos) {
		return !world.isAir(pos.up()) ? false : true;
	}
}