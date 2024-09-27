package com.penguinmod.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final Identifier PENGUIN_AMBIENT_SOUND_ID = Identifier.of("penguinmod:penguin_ambient_sound");
    public static SoundEvent PENGUIN_AMBIENT_SOUND = SoundEvent.of(PENGUIN_AMBIENT_SOUND_ID);


    public static void registerSounds(){
        Registry.register(Registries.SOUND_EVENT, PENGUIN_AMBIENT_SOUND_ID, PENGUIN_AMBIENT_SOUND);
    }
}
