package stevekung.mods.moreplanets.util;

import net.minecraft.potion.Potion;

public class PotionMP extends Potion
{
    public PotionMP(String name, boolean badEffect, int color)
    {
        super(badEffect, color);
        this.setIconIndex(1, 0);
        this.setPotionName("potion." + name);
    }
}