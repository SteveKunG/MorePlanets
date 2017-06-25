package stevekung.mods.moreplanets.module.planets.diona.potion;

import net.minecraft.entity.EntityLivingBase;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.DamageSourceMP;
import stevekung.mods.moreplanets.util.PotionMP;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class InfectedCrystallizeEffect extends PotionMP
{
    public InfectedCrystallizeEffect()
    {
        super("infected_crystallize", true, ColorHelper.rgbToDecimal(136, 97, 209), 0);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if (this == MPPotions.INFECTED_CRYSTALLIZE)
        {
            int k = 20 >> amplifier;
            return k > 0 ? duration % k == 0 : true;
        }
        return false;
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this == MPPotions.INFECTED_CRYSTALLIZE)
        {
            living.attackEntityFrom(DamageSourceMP.INFECTED_CRYSTALLIZE, 1.0F);
        }
    }
}