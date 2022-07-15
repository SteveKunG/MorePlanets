package com.stevekung.moreplanets.registry;

import com.stevekung.moreplanets.core.MorePlanets;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class MPSoundEvents
{
    public static final SoundEvent ALIEN_DESTROYED = new SoundEvent(new ResourceLocation(MorePlanets.MOD_ID, "alien_destroyed"));

    public static void init()
    {
        register("alien_destroyed", ALIEN_DESTROYED);
    }

    private static void register(String key, SoundEvent soundEvent)
    {
        Registry.register(Registry.SOUND_EVENT, new ResourceLocation(MorePlanets.MOD_ID, key), soundEvent);
    }
}