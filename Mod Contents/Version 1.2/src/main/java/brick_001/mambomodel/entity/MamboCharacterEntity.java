package brick_001.mambomodel.entity;

import brick_001.mambomodel.entity.ai.DefendPlayerGoal;
import brick_001.mambomodel.entity.ai.FollowPlayerGoal;
import brick_001.mambomodel.entity.ai.FollowVillagerGoal;
import brick_001.mambomodel.sound.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class MamboCharacterEntity extends PathAwareEntity {

    private static final Logger log = LoggerFactory.getLogger(MamboCharacterEntity.class);
    public float timeUntilSitAnimation = 0;

    protected MamboCharacterEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    // stuff to do with staying sitting when unloaded and then loaded again
    private static final TrackedData<Boolean> STANDING = DataTracker.registerData(MamboCharacterEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public boolean isStanding() {
        return this.dataTracker.get(STANDING);
    }
    public void setStanding(boolean standing) {
        this.dataTracker.set(STANDING, standing);
    }
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(STANDING, true);
    }

    @Override
    protected void initGoals() {
        // stuff they need to stand for
        this.targetSelector.add(0, new DefendPlayerGoal(this, 10.0D, 16D));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(2, new WanderAroundGoal(this, 0.70));
        this.goalSelector.add(3, new FollowPlayerGoal(this, 0.8F, 5.0f, 15.0f));
        this.goalSelector.add(4, new FollowVillagerGoal(this, 0.6F, 3.0f, 10.0f));
        // stuff they can do even when sitting
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 12.0F));
        this.goalSelector.add(4, new LookAtEntityGoal(this, VillagerEntity.class, 12.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createMyMobAttributes() {
        return AmbientEntity.createMobAttributes()
                // health
                .add(EntityAttributes.MAX_HEALTH, 40.0D)
                // step height
                .add(EntityAttributes.STEP_HEIGHT, 1.5D)
                // move speed
                .add(EntityAttributes.MOVEMENT_SPEED, 0.5D)
                // armour
                .add(EntityAttributes.ARMOR, 3.0D)
                // attack stats
                .add(EntityAttributes.ATTACK_DAMAGE, 4.5D)
                .add(EntityAttributes.ATTACK_SPEED, 0.7D)
                .add(EntityAttributes.ATTACK_KNOCKBACK, 1.3D)
                .add(EntityAttributes.ENTITY_INTERACTION_RANGE, 1.2D);
    }

    @Override
    public void travel(net.minecraft.util.math.Vec3d movementInput) {
        if (!isStanding()) {
            if (this.getNavigation().getCurrentPath() != null) {
                this.getNavigation().stop();
            }

            super.travel(net.minecraft.util.math.Vec3d.ZERO);
            return;
        }
        super.travel(movementInput);
    }

    // give & take carrot
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack playerStack = player.getStackInHand(hand);
        ItemStack mobStack = this.getMainHandStack();

        // player give carrot
        if (playerStack.isOf(Items.CARROT) && this.getMainHandStack().isEmpty() && !player.isSneaking()) {
            if (!this.getEntityWorld().isClient()) {

                ItemStack stackToEquip = playerStack.copyWithCount(1);
                this.equipStack(EquipmentSlot.MAINHAND, stackToEquip);

                playerStack.decrement(1);

                this.playSound(SoundEvents.ENTITY_ALLAY_ITEM_GIVEN, 1.0f, 1.0f);

                return ActionResult.SUCCESS;
            }
        }

        // player take carrot
        if (playerStack.isEmpty() && !mobStack.isEmpty() && !player.isSneaking()) {
            if (!this.getEntityWorld().isClient()) {
                player.setStackInHand(hand, mobStack.copy());
                this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);

                this.playSound(SoundEvents.ENTITY_ALLAY_ITEM_TAKEN, 1.0f, 1.0f);

                return ActionResult.SUCCESS;
            }
        }

        // make sit or stand
        if (player.isSneaking() && hand == Hand.MAIN_HAND) {
            this.setStanding(!this.isStanding());
            System.out.println("👉 DEBUG: player sneak clicked mambo!");
            System.out.println(this.isStanding());
            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }

    @Override
    protected float getSoundVolume() {
        return 1.0F; // Ensure this isn't returning 0.0F or missing entirely if overridden
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        System.out.println("👉 DEBUG: getAmbientSound() has been called!");
        return ModSounds.ENTITY_MAMBO_CHARACTER_AMBIENT;
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        System.out.println("👉 DEBUG: getHurtSound() has been called!");
        return ModSounds.ENTITY_MAMBO_CHARACTER_HURT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        System.out.println("👉 DEBUG: getDeathSound() has been called!");
        return ModSounds.ENTITY_MAMBO_CHARACTER_DEATH;
    }

    @Override
    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {

        world.playSound(this, this.getBlockPos(), ModSounds.ENTITY_MAMBO_CHARACTER_SPAWN, SoundCategory.NEUTRAL, 1.0F, 1.0F);

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 600;
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState eatAnimationState = new AnimationState();
    public final AnimationState hurtAnimationState = new AnimationState();
    public final AnimationState sit_1AnimationState = new AnimationState();
    public final AnimationState sit_2AnimationState = new AnimationState();

    @Override
    public void tick() {
        super.tick();
        timeUntilSitAnimation = timeUntilSitAnimation - 1;

        if (this.isStanding()) {
            this.sit_1AnimationState.stop();
            this.sit_2AnimationState.stop();

            if (this.getVelocity().horizontalLengthSquared() > 0.1F) {
                this.idleAnimationState.stop();
                this.walkAnimationState.startIfNotRunning(this.age);
            } else {
                this.walkAnimationState.stop();
                this.idleAnimationState.startIfNotRunning(this.age);
            }
        }
        else if (timeUntilSitAnimation <= 0) {
            this.idleAnimationState.stop();
            this.walkAnimationState.stop();

            if (getRandom().nextInt(5) == 1) {
                this.sit_1AnimationState.stop();
                this.sit_2AnimationState.stop();
                this.sit_2AnimationState.startIfNotRunning(this.age);
                timeUntilSitAnimation = 32;
                System.out.println("Sit 2 animation played");
            }
            else {
                this.sit_1AnimationState.stop();
                this.sit_2AnimationState.stop();
                this.sit_1AnimationState.startIfNotRunning(this.age);
                timeUntilSitAnimation = 23;
                System.out.println("Sit 1 animation played");
            }
        }

        if (this.getHealth() < getMaxHealth()) {
            this.walkAnimationState.stop();
            this.idleAnimationState.stop();
            this.hurtAnimationState.stop();

            if (this.getMainHandStack() != ItemStack.EMPTY) {
                this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);

                this.eatAnimationState.start(this.age);

                this.getEntityWorld().playSoundFromEntity(this, this, SoundEvents.ENTITY_FROG_EAT, SoundCategory.NEUTRAL, 1.0f, 1.3f);
                this.getEntityWorld().addParticleClient(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getRandomBodyY() + 0.5, this.getZ(), 0.0, 1.0, 0.0);

                this.heal(3.0F);
            }
        }
    }

    @Override
    public void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putBoolean("IsStanding", this.isStanding());
    }

    @Override
    public void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.setStanding(view.getBoolean("IsStanding", false));
    }
}
