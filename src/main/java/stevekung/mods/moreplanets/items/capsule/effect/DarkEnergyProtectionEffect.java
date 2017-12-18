package stevekung.mods.moreplanets.items.capsule.effect;

import net.minecraft.entity.EntityLivingBase;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.PotionMP;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class DarkEnergyProtectionEffect extends PotionMP
{
    public DarkEnergyProtectionEffect()
    {
        super("dark_energy_protection", false, ColorHelper.rgbToDecimal(200, 200, 200), 4);
        this.setBeneficial();
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this == MPPotions.DARK_ENERGY_PROTECTION)
        {
            living.removePotionEffect(MPPotions.DARK_ENERGY);
        }
    }
}