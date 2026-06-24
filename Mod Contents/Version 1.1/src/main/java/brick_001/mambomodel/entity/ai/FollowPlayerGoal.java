package brick_001.mambomodel.entity.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import java.util.EnumSet;

public class FollowPlayerGoal extends Goal {
    private final MobEntity mob;
    private final double speed;
    private final float minDistance;
    private final float maxDistance;
    private PlayerEntity target;

    public FollowPlayerGoal(MobEntity mob, double speed, float minDistance, float maxDistance) {
        this.mob = mob;
        this.speed = speed;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (this.mob.getRandom().nextInt(20) != 0) return false;

        // Uses built-in player lookup within the max distance radius
        this.target = this.mob.getEntityWorld().getClosestPlayer(this.mob, (double)this.maxDistance);
        return this.target != null && this.target.isAlive();
    }

    @Override
    public boolean shouldContinue() {
        if (this.target == null || !this.target.isAlive()) return false;
        double distSq = this.mob.squaredDistanceTo(this.target);
        return distSq >= (double)(this.minDistance * this.minDistance) && distSq <= (double)(this.maxDistance * this.maxDistance);
    }

    @Override
    public void stop() {
        this.target = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.mob.getLookControl().lookAt(this.target, 10.0F, (float)this.mob.getMaxLookPitchChange());
        this.mob.getNavigation().startMovingTo(this.target, this.speed);
    }
}