package brick_001.mambomodel.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import java.util.List;

public class CustomSpawnRules {

    public static <T extends MobEntity> boolean checkHorseAvoidanceSpawn(
            EntityType<T> type,
            ServerWorldAccess world,
            SpawnReason spawnReason,
            BlockPos pos,
            Random random) {

        // 1. Scan a 48-block radius area around the potential spawn point
        double radius = 48.0;
        Box searchBox = new Box(pos).expand(radius);

        // 2. Count the number of loaded horses inside this area
        List<HorseEntity> nearbyHorses = world.getEntitiesByClass(HorseEntity.class, searchBox, horse -> true);
        int horseCount = nearbyHorses.size();

        if (horseCount > 0) {
            // 3. Add 10% success chance for every horse found
            float successChance = 0.0f + (horseCount * 0.10f);

            // 4. Roll the dice; if the roll fails, block the mob from spawning
            if (random.nextFloat() > successChance) {
                return false;
            }
        }

        // 5. Fallback to standard vanilla environmental spawn checks (light levels, solid ground)
        return MobEntity.canMobSpawn(type, world, spawnReason, pos, random);
    }
}