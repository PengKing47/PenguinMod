package com.penguinmod.entity.custom;

import org.jetbrains.annotations.Nullable;

import com.penguinmod.effect.ModEffects;
import com.penguinmod.entity.ModEntities;
import com.penguinmod.entity.goal.GoHomeGoal;
import com.penguinmod.entity.goal.LayEggGoal;
import com.penguinmod.entity.goal.PenguinMateGoal;
import com.penguinmod.sound.ModSounds;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;


public class PenguinEntity extends AnimalEntity {

    private static final TrackedData<BlockPos> HOME_POS = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
	private static final TrackedData<Boolean> HAS_EGG = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Boolean> DIGGING_SAND = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<BlockPos> TRAVEL_POS = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
	private static final TrackedData<Boolean> LAND_BOUND = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Boolean> ACTIVELY_TRAVELING = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public int sandDiggingCounter;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationCooldown = 0;

    public PenguinEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
		this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    public void setHomePos(BlockPos pos) {
		this.dataTracker.set(HOME_POS, pos);
	}

	public BlockPos getHomePos() {
		return this.dataTracker.get(HOME_POS);
	}

	public void setTravelPos(BlockPos pos) {
		this.dataTracker.set(TRAVEL_POS, pos);
	}

	public BlockPos getTravelPos() {
		return this.dataTracker.get(TRAVEL_POS);
	}

	public boolean hasEgg() {
		return this.dataTracker.get(HAS_EGG);
	}

	public void setHasEgg(boolean hasEgg) {
		this.dataTracker.set(HAS_EGG, hasEgg);
	}

	public boolean isDiggingSand() {
		return this.dataTracker.get(DIGGING_SAND);
	}

	public void setDiggingSand(boolean diggingSand) {
		this.sandDiggingCounter = diggingSand ? 1 : 0;
		this.dataTracker.set(DIGGING_SAND, diggingSand);
	}

	public boolean isLandBound() {
		return this.dataTracker.get(LAND_BOUND);
	}

	public void setLandBound(boolean landBound) {
		this.dataTracker.set(LAND_BOUND, landBound);
	}

	boolean isActivelyTraveling() {
		return this.dataTracker.get(ACTIVELY_TRAVELING);
	}

	void setActivelyTraveling(boolean traveling) {
		this.dataTracker.set(ACTIVELY_TRAVELING, traveling);
	}

    @Override
	protected void initDataTracker(DataTracker.Builder builder) {
		super.initDataTracker(builder);
		builder.add(HOME_POS, BlockPos.ORIGIN);
		builder.add(HAS_EGG, false);
		builder.add(TRAVEL_POS, BlockPos.ORIGIN);
		builder.add(LAND_BOUND, false);
		builder.add(ACTIVELY_TRAVELING, false);
		builder.add(DIGGING_SAND, false);
	}

    @Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		nbt.putInt("HomePosX", this.getHomePos().getX());
		nbt.putInt("HomePosY", this.getHomePos().getY());
		nbt.putInt("HomePosZ", this.getHomePos().getZ());
		nbt.putBoolean("HasEgg", this.hasEgg());
		nbt.putInt("TravelPosX", this.getTravelPos().getX());
		nbt.putInt("TravelPosY", this.getTravelPos().getY());
		nbt.putInt("TravelPosZ", this.getTravelPos().getZ());
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		int i = nbt.getInt("HomePosX");
		int j = nbt.getInt("HomePosY");
		int k = nbt.getInt("HomePosZ");
		this.setHomePos(new BlockPos(i, j, k));
		super.readCustomDataFromNbt(nbt);
		this.setHasEgg(nbt.getBoolean("HasEgg"));
		int l = nbt.getInt("TravelPosX");
		int m = nbt.getInt("TravelPosY");
		int n = nbt.getInt("TravelPosZ");
		this.setTravelPos(new BlockPos(l, m, n));
	}

    @Nullable
	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
		this.setHomePos(this.getBlockPos());
		this.setTravelPos(BlockPos.ORIGIN);
		return super.initialize(world, difficulty, spawnReason, entityData);
	}

    private void setUpAnimationStates(){
        if (this.idleAnimationCooldown <= 0) {
            this.idleAnimationCooldown = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationCooldown;
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
      float f;
      if (this.getPose() == EntityPose.STANDING) {
         f = Math.min(posDelta * 6.0F, 1.0F);
      } else {
         f = 0.0F;
      }

      this.limbAnimator.updateLimbs(f, 0.2F);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()){
            this.setUpAnimationStates();
        }

        for(PlayerEntity player : this.getWorld().getPlayers()){
            if(player.getPos().distanceTo(this.getPos()) < 10 && player.getVehicle() instanceof BoatEntity){
                player.addStatusEffect(new StatusEffectInstance(ModEffects.PENGUINS_GRACE, 100), this);
            }
        }
    
        
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.CAT_FOOD);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.5f));
        this.goalSelector.add(2, new PenguinMateGoal(this, 0.5f));
        this.goalSelector.add(2, new LayEggGoal(this, .5f));
        this.goalSelector.add(3, new TemptGoal(this, 0.5f, (stack) -> {return stack.isIn(ItemTags.CAT_FOOD);}, false));
        this.goalSelector.add(4, new GoHomeGoal(this, .5f));
        this.goalSelector.add(5, new FollowParentGoal(this, 0.5f));
        this.goalSelector.add(6, new WanderAroundGoal(this, 0.5f));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.5f));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createPenguinAttributes(){
        return MobEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f)
            .add(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 10f);
    }


    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.PENGUIN.create(world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.PENGUIN_AMBIENT_SOUND;
    }

    @Override
    protected SoundEvent getDeathSound() {
       return ModSounds.PENGUIN_AMBIENT_SOUND; //no custom death sound for now
    }
	
	@Override
	protected int getXpToDrop() {
		return (int)(Math.random()*3+1);
	}
	
}



