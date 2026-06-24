package brick_001.mambomodel.entity.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import java.util.EnumSet;
import java.util.List;

public class FollowVillagerGoal extends Goal {
    private final MobEntity mob;
    private final double speed;
    private final float minDistance;
    private final float maxDistance;
    private VillagerEntity target;

    public FollowVillagerGoal(MobEntity mob, double speed, float minDistance, float maxDistance) {
        this.mob = mob;
        this.speed = speed;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (this.mob.getRandom().nextInt(20) != 0) return false;

        List<VillagerEntity> list = this.mob.getEntityWorld().getEntitiesByClass(
                VillagerEntity.class,
                this.mob.getBoundingBox().expand(this.maxDistance),
                VillagerEntity::isAlive
        );

        if (list.isEmpty()) return false;

        this.target = list.get(this.mob.getRandom().nextInt(list.size()));
        return true;
    }

    @Override
    public boolean shouldContinue() {
        if (this.target == null || !this.target.isAlive())
            return false;
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