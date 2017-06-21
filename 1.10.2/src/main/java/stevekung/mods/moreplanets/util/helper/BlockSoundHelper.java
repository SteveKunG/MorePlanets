package stevekung.mods.moreplanets.util.helper;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import stevekung.mods.moreplanets.init.MPSounds;

public class BlockSoundHelper
{
    public static SoundType ALIEN_EGG = new SoundType(1.0F, 1.0F, MPSounds.ALIEN_EGG_DESTROYED, SoundEvents.BLOCK_SLIME_STEP, SoundEvents.BLOCK_SLIME_PLACE, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
    public static SoundType SMALL_SLIME = new SoundType(1.0F, 1.0F, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP);
    public static SoundType LILYPAD = new SoundType(1.0F, 1.0F, MPSounds.LILYPAD_PLACE, SoundEvents.BLOCK_GRASS_STEP, SoundEvents.BLOCK_GRASS_PLACE, SoundEvents.BLOCK_GRASS_HIT, SoundEvents.BLOCK_GRASS_FALL);
}