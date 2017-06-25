package stevekung.mods.moreplanets.items.capsule.effect;

import net.minecraft.entity.EntityLivingBase;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.PotionMP;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class InfectedSporeProtectionEffect extends PotionMP
{
    public InfectedSporeProtectionEffect()
    {
        super("infected_spore_protection", false, ColorHelper.rgbToDecimal(200, 200, 200), 3);
        this.setBeneficial();
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this == MPPotions.INFECTED_SPORE_PROTECTION)
        {
            living.removePotionEffect(MPPotions.INFECTED_SPORE);
        }
    }
}