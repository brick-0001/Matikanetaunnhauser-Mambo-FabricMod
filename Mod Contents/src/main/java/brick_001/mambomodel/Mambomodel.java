package brick_001.mambomodel;

import brick_001.mambomodel.entity.CustomSpawnRules;
import brick_001.mambomodel.entity.ModEntities;
import brick_001.mambomodel.item.ModItems;
import brick_001.mambomodel.mixin.SpawnRestrictionInvoker;
import brick_001.mambomodel.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.item.ItemGroups;
import net.minecraft.world.Heightmap;

public class Mambomodel implements ModInitializer {

    @Override
    public void onInitialize() {
        ModEntities.registerEntityAttributes();
        ModItems.registerItems();
        ModSounds.registerSounds();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register( entries -> {
            entries.add(ModItems.MY_MOB_SPAWN_EGG);
        });

        // Call the invoker to bind your custom predicate rules to your custom mob
        SpawnRestrictionInvoker.invokeRegister(
                ModEntities.MY_MOB,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                CustomSpawnRules::checkHorseAvoidanceSpawn
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(), // Runs everywhere in the Overworld
                SpawnGroup.CREATURE,               // Matches horse spawn groups (animals)
                ModEntities.MY_MOB,                // Your custom mob entity type
                20,                                // Weight (how often it rolls to spawn)
                1,                                 // Minimum group count
                3                                  // Maximum group count
        );
    }
}
