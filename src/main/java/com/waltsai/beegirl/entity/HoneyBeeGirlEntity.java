package com.waltsai.beegirl.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.FlyGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HoneyBeeGirlEntity extends TameableEntity {
    public static TrackedData<Boolean> IS_FLYING = DataTracker.registerData(HoneyBeeGirlEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public float prevStrideDistance;
    public float strideDistance;
    public double prevHairX;
    public double prevHairY;
    public double prevHairZ;
    public double hairX;
    public double hairY;
    public double hairZ;
    protected HoneyBeeGirlEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 65, false);

        BirdNavigation navigation = new BirdNavigation(this, this.world);
        navigation.setCanEnterOpenDoors(true);
        navigation.setCanPathThroughDoors(true);
        navigation.setCanSwim(false);
        this.navigation = navigation;
    }

    public static DefaultAttributeContainer.Builder createBeeGirlAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 120.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.45D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 11.0D).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 8.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FlyGoal(this, 1.0D));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        nbt.putBoolean("Flying", isInFlyingPose());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        this.setInFlyingPose(nbt.getBoolean("Flying"));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(IS_FLYING, false);
    }

    public boolean isInFlyingPose() {
        return this.dataTracker.get(IS_FLYING);
    }

    public void setInFlyingPose(boolean flying) {
        this.dataTracker.set(IS_FLYING, flying);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }


    private void updateHairAngles() {
        this.prevHairX = this.hairX;
        this.prevHairY = this.hairY;
        this.prevHairZ = this.hairZ;
        double d = this.getX() - this.hairX;
        double e = this.getY() - this.hairY;
        double f = this.getZ() - this.hairZ;
        double g = 10.0D;
        if (d > 10.0D) {
            this.hairX = this.getX();
            this.prevHairX = this.hairX;
        }

        if (f > 10.0D) {
            this.hairZ = this.getZ();
            this.prevHairZ = this.hairZ;
        }

        if (e > 10.0D) {
            this.hairY = this.getY();
            this.prevHairY = this.hairY;
        }

        if (d < -10.0D) {
            this.hairX = this.getX();
            this.prevHairX = this.hairX;
        }

        if (f < -10.0D) {
            this.hairZ = this.getZ();
            this.prevHairZ = this.hairZ;
        }

        if (e < -10.0D) {
            this.hairY = this.getY();
            this.prevHairY = this.hairY;
        }

        this.hairX += d * 0.25D;
        this.hairZ += f * 0.25D;
        this.hairY += e * 0.25D;
    }


    @Override
    public void tick() {
        super.tick();

        this.updateHairAngles();
    }

    @Override
    public void tickMovement() {
        this.prevStrideDistance = this.strideDistance;
        super.tickMovement();
        float g;
        if (this.onGround && !this.isDead()) {
            g = Math.min(0.1F, (float)this.getVelocity().horizontalLength());
        } else {
            g = 0.0F;
        }
        this.strideDistance += (g - this.strideDistance) * 0.4F;

        this.tickHandSwing();
    }
}
