package stevekung.mods.moreplanets.util.helper;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;

public class BlockSoundHelper
{
    ////////////////////////////////////////////////////////////////TODO
    public static SoundType ALIEN_EGG = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_SLIME_BREAK, SoundEvents.BLOCK_SLIME_STEP, SoundEvents.BLOCK_SLIME_PLACE, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
    public static SoundType SMALL_SLIME = new SoundType(1.0F, 1.0F, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP);

    //    public static SoundType ALIEN_EGG = new SoundType("slime", 1.0F, 1.0F)
    //    {
    //        @Override
    //        public String getBreakSound()
    //        {
    //            return "moreplanets:block.egg.destroy";
    //        }
    //
    //        @Override
    //        public String getStepSound()
    //        {
    //            return "mob.slime.small";
    //        }
    //
    //        @Override
    //        public String getPlaceSound()
    //        {
    //            return "mob.slime.big";
    //        }
    //    };

    public static SoundType LILYPAD = new SoundType("grass", 1.0F, 1.0F)
    {
        @Override
        public String getPlaceSound()
        {
            return "moreplanets:block.lily.place";
        }
    };
}