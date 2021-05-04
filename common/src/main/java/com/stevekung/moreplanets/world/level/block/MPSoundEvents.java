package com.stevekung.moreplanets.world.level.block;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class MPSoundEvents
{
    public static final SoundEvent ALIEN_DESTROYED = new SoundEvent(new ResourceLocation(MorePlanetsMod.MOD_ID, "alien_destroyed"));

    public static void init()
    {
        MorePlanetsMod.COMMON.registerSound("alien_destroyed", ALIEN_DESTROYED);
    }
}