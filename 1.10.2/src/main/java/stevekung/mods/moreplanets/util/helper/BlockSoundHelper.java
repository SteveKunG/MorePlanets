package stevekung.mods.moreplanets.util.helper;

import net.minecraft.block.SoundType;

public class BlockSoundHelper
{
    public static SoundType ALIEN_EGG = new SoundType("slime", 1.0F, 1.0F)
    {
        @Override
        public String getBreakSound()
        {
            return "moreplanets:block.egg.destroy";
        }

        @Override
        public String getStepSound()
        {
            return "mob.slime.small";
        }

        @Override
        public String getPlaceSound()
        {
            return "mob.slime.big";
        }
    };

    public static SoundType LILYPAD = new SoundType("grass", 1.0F, 1.0F)
    {
        @Override
        public String getPlaceSound()
        {
            return "moreplanets:block.lily.place";
        }
    };

    public static SoundType SMALL_SLIME = new SoundType("slime", 1.0F, 1.0F)
    {
        @Override
        public String getBreakSound()
        {
            return "mob.slime.small";
        }

        @Override
        public String getStepSound()
        {
            return "mob.slime.small";
        }
    };
}