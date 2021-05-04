package com.stevekung.moreplanets.world.level.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class MPSoundType
{
    public static final SoundType ALIEN = new SoundType(1.0F, 1.0F, MPSoundEvents.ALIEN_DESTROYED, SoundEvents.SLIME_BLOCK_STEP, SoundEvents.SLIME_BLOCK_PLACE, SoundEvents.SLIME_BLOCK_HIT, SoundEvents.SLIME_BLOCK_FALL);
}