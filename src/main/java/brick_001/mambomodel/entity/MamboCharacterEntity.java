package brick_001.mambomodel.entity;

import brick_001.mambomodel.entity.ai.DefendPlayerGoal;
import brick_001.mambomodel.entity.ai.FollowPlayerGoal;
import brick_001.mambomodel.entity.ai.FollowVillagerGoal;
import brick_001.mambomodel.sound.ModSounds;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;

public class MamboCharacterEntity extends PathAwareEntity {

    protected MamboCharacterEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        // attack hostiles nearby
        this.targetSelector.add(0, new DefendPlayerGoal(this, 10.0D, 16D));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2D, false));
        // walk around
        this.goalSelector.add(2, new SwimGoal(this));
        this.goalSelector.add(3, new WanderAroundGoal(this, 0.70));
        // player type following
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 12.0F));
        this.goalSelector.add(5, new FollowPlayerGoal(this, 0.8F, 5.0f, 15.0f));
        // villager type following
        this.goalSelector.add(6, new LookAtEntityGoal(this, VillagerEntity.class, 12.0F));
        this.goalSelector.add(7, new FollowVillagerGoal(this, 0.6F, 3.0f, 10.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        // follow own kind
        this.goalSelector.add(9, new FollowMobGoal(this, 0.6f, 2.0f, 20.0f));
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

    // feeding for healing
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        // 1. Define your specific food and how much it heals
        Item targetFood = Items.CARROT;
        float healAmount = 4.0f;

        // 2. Check if the player is holding the correct item
        if (itemStack.isOf(targetFood)) {

            // 3. Check if the entity actually needs healing
            if (this.getHealth() < this.getMaxHealth()) {

                // 4. Handle server-side logic (Healing and Item Consumption)
                if (!this.getEntityWorld().isClient()) {
                    this.heal(healAmount);

                    // Consume the item if the player is NOT in Creative Mode
                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    // Play an eating sound so it feels responsive
                    this.getEntityWorld().playSoundFromEntity(this, this, SoundEvents.ENTITY_FROG_EAT, SoundCategory.NEUTRAL, 1.0f, 1.3f);
                }
                // 5. Handle client-side logic (Visuals)
                else {
                    // Spawn a heart particle to show the player it worked
                    this.getEntityWorld().addParticleClient(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getRandomBodyY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
                }

                // Return success to tell the game the interaction was handled
                return ActionResult.SUCCESS;
            }
        }

        // If the item wasn't the food, or the entity is already at full health, fallback to default behavior
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

        // 2. ALWAYS return the super method so vanilla spawning logic continues normally
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 600;
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();

    @Override
    public void tick() {
        super.tick();

        if (this.getVelocity().horizontalLengthSquared() > 1.0E-7) {
            this.idleAnimationState.stop();
            this.walkAnimationState.startIfNotRunning(this.age);
        } else {
            this.walkAnimationState.stop();
            this.idleAnimationState.startIfNotRunning(this.age);
        }
    }
}
