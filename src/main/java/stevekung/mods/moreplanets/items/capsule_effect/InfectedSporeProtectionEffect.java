package stevekung.mods.moreplanets.items.capsule_effect;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.PotionMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class InfectedSporeProtectionEffect extends PotionMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/potions/infected_spore_protection.png");

    public InfectedSporeProtectionEffect()
    {
        super("infected_spore_protection", false, ColorUtils.rgbToDecimal(200, 200, 200));
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

    @Override
    protected ResourceLocation getPotionIcon()
    {
        return InfectedSporeProtectionEffect.TEXTURE;
    }
}