package com.stevekung.moreplanets.items.capsule_effect;

import java.util.ArrayList;
import java.util.List;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.init.MPPotions;
import com.stevekung.moreplanets.utils.PotionMP;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

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

    @Override
    public List<ItemStack> getCurativeItems()
    {
        return new ArrayList<>();
    }
}