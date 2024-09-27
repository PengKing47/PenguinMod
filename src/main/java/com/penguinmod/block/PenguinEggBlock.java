package com.penguinmod.block;

import com.mojang.serialization.MapCodec;
import com.penguinmod.entity.ModEntities;
import com.penguinmod.entity.custom.PenguinEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class PenguinEggBlock extends Block {

    public static final MapCodec<PenguinEggBlock> CODEC = createCodec(PenguinEggBlock::new);
	public static final int field_31272 = 2;
	public static final int field_31273 = 1;
	public static final int field_31274 = 4;
	public static final IntProperty HATCH = Properties.HATCH;
	public static final IntProperty EGGS = Properties.EGGS;
	public static final int HATCH_TIME = 5;
	public int hatchTimer;

    public PenguinEggBlock(Settings settings) {
        super(settings.ticksRandomly());
        this.setDefaultState(this.stateManager.getDefaultState().with(HATCH, Integer.valueOf(0)).with(EGGS, Integer.valueOf(1)));
		this.hatchTimer = HATCH_TIME;
		
    }

	

    @Override
	protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		
		hatchTimer--;
		if(hatchTimer <= 0){
			world.playSound(null, pos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
			world.removeBlock(pos, false);
			world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(state));

			for (int j = 0; j < state.get(EGGS); j++) {
				world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));
				PenguinEntity penguin = ModEntities.PENGUIN.create(world);
				if (penguin != null) {
					penguin.setBreedingAge(-24000);
					penguin.setHomePos(pos);
					penguin.refreshPositionAndAngles((double)pos.getX() + 0.3 + (double)j * 0.2, (double)pos.getY(), (double)pos.getZ() + 0.3, 0.0F, 0.0F);
					world.spawnEntity(penguin);
					hatchTimer = HATCH_TIME;
				}
			}
		}
	}


    @Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 7.0, 15.0);
	}

    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(HATCH, EGGS);
	}


	
}
