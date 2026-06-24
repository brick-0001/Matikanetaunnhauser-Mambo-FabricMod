package brick_001.mambomodel.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    // Create the SoundEvents
    public static final SoundEvent ENTITY_MAMBO_CHARACTER_AMBIENT = registerSoundEvent("entity.mambo_character.ambient");
    public static final SoundEvent ENTITY_MAMBO_CHARACTER_HURT = registerSoundEvent("entity.mambo_character.hurt");
    public static final SoundEvent ENTITY_MAMBO_CHARACTER_DEATH = registerSoundEvent("entity.mambo_character.death");
    public static final SoundEvent ENTITY_MAMBO_CHARACTER_SPAWN = registerSoundEvent("entity.mambo_character.spawn");

    // Helper method to register sounds
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of("mambomodel", name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    // Call this method in your ModInitializer
    public static void registerSounds() {
        System.out.println("Registering Mod Sounds for mambomodel");
    }
}