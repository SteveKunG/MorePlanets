package com.stevekung.moreplanets.planets.diona.items;

import com.stevekung.moreplanets.planets.diona.entity.EntityInfectedPurloniteBomb;
import com.stevekung.moreplanets.utils.items.MPItemCategory;
import com.stevekung.moreplanets.utils.items.ItemBaseMP;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemInfectedPurloniteBomb extends ItemBaseMP
{
    public ItemInfectedPurloniteBomb(String name)
    {
        this.setMaxStackSize(32);
        this.setTranslationKey(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (!player.capabilities.isCreativeMode)
        {
            itemStack.shrink(1);
        }

        world.playSound(null, player.getPosition(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        player.swingArm(hand);

        if (!world.isRemote)
        {
            EntityInfectedPurloniteBomb bomb = new EntityInfectedPurloniteBomb(world, player);
            bomb.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntity(bomb);
        }
        player.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public MPItemCategory getItemCategory()
    {
        return MPItemCategory.PROJECTILE;
    }
}