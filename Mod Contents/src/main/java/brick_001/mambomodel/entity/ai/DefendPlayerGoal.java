package brick_001.mambomodel.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import java.util.EnumSet;
import java.util.List;

public class DefendPlayerGoal extends Goal {
    private final MobEntity mob;
    private final double maxPlayerDistance; // Maximum distance between our mob and the player
    private final double maxEnemyDistance;  // Maximum distance between the enemy and the player
    private LivingEntity targetEnemy;

    // Updated constructor to accept two distinct ranges
    public DefendPlayerGoal(MobEntity mob, double maxPlayerDistance, double maxEnemyDistance) {
        this.mob = mob;
        this.maxPlayerDistance = maxPlayerDistance;
        this.maxEnemyDistance = maxEnemyDistance;
        this.setControls(EnumSet.of(Goal.Control.TARGET));
    }

    @Override
    public boolean canStart() {
        PlayerEntity closestPlayer = this.mob.getEntityWorld().getClosestPlayer(this.mob, this.maxPlayerDistance);

        if (closestPlayer == null || closestPlayer.isSpectator()) {
            return false;
        }

        List<HostileEntity> nearbyHostiles = this.mob.getEntityWorld().getEntitiesByClass(
                HostileEntity.class,
                closestPlayer.getBoundingBox().expand(this.maxEnemyDistance),
                LivingEntity::isAlive
        );

        if (nearbyHostiles.isEmpty()) {
            return false;
        }

        // Initialize variables to track the closest enemy and its distance
        HostileEntity closestEnemy = null;
        double closestDistanceSq = Double.MAX_VALUE;

        // Loop through all nearby hostiles to find the closest one to the PLAYER
        for (HostileEntity enemy : nearbyHostiles) {
            double distanceSq = closestPlayer.squaredDistanceTo(enemy);

            if (distanceSq < closestDistanceSq) {
                closestDistanceSq = distanceSq;
                closestEnemy = enemy;
            }
        }

        this.targetEnemy = closestEnemy;
        return this.targetEnemy != null;
    }

    @Override
    public void start() {
        this.mob.setTarget(this.targetEnemy);
        super.start();
    }

    @Override
    public void stop() {
        this.targetEnemy = null;
    }
}