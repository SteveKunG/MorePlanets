package stevekung.mods.moreplanets.items.capsule_effect;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.PotionMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class DarkEnergyProtectionEffect extends PotionMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/potions/dark_energy_protection.png");

    public DarkEnergyProtectionEffect()
    {
        super("dark_energy_protection", false, ColorUtils.rgbToDecimal(200, 200, 200));
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

    @Override
    protected ResourceLocation getPotionIcon()
    {
        return DarkEnergyProtectionEffect.TEXTURE;
    }
}