package stevekung.mods.moreplanets.util;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionMP extends Potion
{
    public PotionMP(String name, boolean badEffect, int color)
    {
        super(new ResourceLocation("moreplanets:" + name), badEffect, color);
        this.setIconIndex(1, 0);
        this.setPotionName("potion." + name);
    }
}