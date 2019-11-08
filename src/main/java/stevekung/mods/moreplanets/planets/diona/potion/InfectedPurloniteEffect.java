package stevekung.mods.moreplanets.planets.diona.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.DamageSourceMP;
import stevekung.mods.moreplanets.utils.PotionMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class InfectedPurloniteEffect extends PotionMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/potions/infected_purlonite.png");

    public InfectedPurloniteEffect()
    {
        super("infected_purlonite", true, ColorUtils.rgbToDecimal(136, 97, 209));
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if (this == MPPotions.INFECTED_PURLONITE)
        {
            int k = 20 >> amplifier;
            return k > 0 ? duration % k == 0 : true;
        }
        return false;
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this == MPPotions.INFECTED_PURLONITE)
        {
            living.attackEntityFrom(DamageSourceMP.INFECTED_PURLONITE, 1.0F);
        }
    }

    @Override
    protected ResourceLocation getPotionIcon()
    {
        return InfectedPurloniteEffect.TEXTURE;
    }
}