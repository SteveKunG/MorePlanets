package com.stevekung.moreplanets.items;

import com.stevekung.moreplanets.planets.nibiru.entity.EntityShlime;
import com.stevekung.moreplanets.utils.items.ItemBaseMP;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemDyeMP extends ItemBaseMP
{
    public ItemDyeMP(String name)
    {
        super(name);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target, EnumHand hand)
    {
        if (target instanceof EntitySheep)
        {
            EntitySheep entitysheep = (EntitySheep)target;
            EnumDyeColor enumdyecolor = EnumDyeColor.BLUE;

            if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != enumdyecolor)
            {
                entitysheep.setFleeceColor(enumdyecolor);
                itemStack.shrink(1);
            }
            return true;
        }
        if (target instanceof EntityShlime)
        {
            EntityShlime entitysheep = (EntityShlime)target;
            EnumDyeColor enumdyecolor = EnumDyeColor.BLUE;

            if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != enumdyecolor)
            {
                entitysheep.setFleeceColor(enumdyecolor);
                itemStack.shrink(1);
            }
            return true;
        }
        else
        {
            return false;
        }
    }
}