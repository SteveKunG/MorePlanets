package stevekung.mods.moreplanets.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundEventMP extends SoundEvent
{
    public SoundEventMP(String name)
    {
        super(new ResourceLocation("moreplanets:" + name));
    }
}