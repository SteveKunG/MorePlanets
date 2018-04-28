package stevekung.mods.moreplanets.items.capsule_effect;

import net.minecraft.entity.EntityLivingBase;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.PotionMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class DarkEnergyProtectionEffect extends PotionMP
{
    public DarkEnergyProtectionEffect()
    {
        super("dark_energy_protection", false, ColorUtils.rgbToDecimal(200, 200, 200), 4);
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