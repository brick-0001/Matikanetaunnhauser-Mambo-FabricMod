package brick_001.mambomodel.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MamboCharacterEntity> MY_MOB = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of("mambomodel", "mambo_character"),
            EntityType.Builder.create(MamboCharacterEntity::new, SpawnGroup.AMBIENT)
                    .dimensions(1.0f, 1.95f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("mambomodel", "mambo_character")))
    );

    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(MY_MOB, MamboCharacterEntity.createMyMobAttributes());
    }
}
