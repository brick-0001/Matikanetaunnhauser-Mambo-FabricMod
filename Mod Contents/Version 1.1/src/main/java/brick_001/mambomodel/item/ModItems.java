package brick_001.mambomodel.item;

import brick_001.mambomodel.entity.ModEntities;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MY_MOB_SPAWN_EGG = Registry.register(
            Registries.ITEM,
            Identifier.of("mambomodel", "mambo_character_spawn_egg"),
            new SpawnEggItem(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of("mambomodel", "mambo_character_spawn_egg"))).spawnEgg(ModEntities.MY_MOB))
    );

    public static void registerItems() {}
}