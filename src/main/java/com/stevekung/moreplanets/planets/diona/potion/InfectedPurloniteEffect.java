package com.stevekung.moreplanets.planets.diona.potion;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.init.MPPotions;
import com.stevekung.moreplanets.utils.DamageSourceMP;
import com.stevekung.moreplanets.utils.PotionMP;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

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
            return k == 0 || duration % k == 0;
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