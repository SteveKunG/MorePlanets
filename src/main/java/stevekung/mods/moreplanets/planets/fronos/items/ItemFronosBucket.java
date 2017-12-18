/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ItemFronosBucket extends ItemBaseMP
{
    private Block isFull;

    public ItemFronosBucket(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
        this.setContainerItem(Items.bucket);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        this.isFull = ItemFronosBucket.getLiquidFromMeta(itemStack.getItemDamage());
        boolean flag = this.isFull == Blocks.air;

        if (itemStack.getItem() == Items.bucket)
        {
            MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);

            if (pos == null)
            {
                return itemStack;
            }
            else
            {
                Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
                int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

                if (block == FronosBlocks.coconut_milk && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemStack;
                    }
                    if (--itemStack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(FronosItems.fronos_bucket, 1, 0);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.fronos_bucket, 1, 0)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(FronosItems.fronos_bucket, 1, 0), 1.0F);
                    }
                    return itemStack;
                }
                else if (block == FronosBlocks.mineral_water && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemStack;
                    }
                    if (--itemStack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(FronosItems.fronos_bucket, 1, 1);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.fronos_bucket, 1, 1)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(FronosItems.fronos_bucket, 1, 1), 1.0F);
                    }
                    return itemStack;
                }
                else if (block == FronosBlocks.ovantine && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemStack;
                    }
                    if (--itemStack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(FronosItems.fronos_bucket, 1, 2);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.fronos_bucket, 1, 2)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(FronosItems.fronos_bucket, 1, 2), 1.0F);
                    }
                    return itemStack;
                }
                else if (block == FronosBlocks.tea && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemStack;
                    }
                    if (--itemStack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(FronosItems.fronos_bucket, 1, 3);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.fronos_bucket, 1, 3)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(FronosItems.fronos_bucket, 1, 3), 1.0F);
                    }
                    return itemStack;
                }
                else if (block == FronosBlocks.caramel && meta == 3)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemStack;
                    }
                    if (--itemStack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(FronosItems.fronos_bucket, 1, 4);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.fronos_bucket, 1, 4)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(FronosItems.fronos_bucket, 1, 4), 1.0F);
                    }
                    return itemStack;
                }
                else
                {
                    return itemStack;
                }
            }
        }
        else
        {
            MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, flag);

            if (pos == null)
            {
                return itemStack;
            }
            else if (pos.typeOfHit == MovingObjectType.BLOCK)
            {
                int i = pos.blockX;
                int j = pos.blockY;
                int k = pos.blockZ;

                if (!world.canMineBlock(player, i, j, k))
                {
                    return itemStack;
                }
                if (this.isFull == Blocks.air)
                {
                    return new ItemStack(Items.bucket, 1, 0);
                }
                if (pos.sideHit == 0)
                {
                    --j;
                }
                if (pos.sideHit == 1)
                {
                    ++j;
                }
                if (pos.sideHit == 2)
                {
                    --k;
                }
                if (pos.sideHit == 3)
                {
                    ++k;
                }
                if (pos.sideHit == 4)
                {
                    --i;
                }
                if (pos.sideHit == 5)
                {
                    ++i;
                }
                if (!player.canPlayerEdit(i, j, k, pos.sideHit, itemStack))
                {
                    return itemStack;
                }
                if (this.tryPlaceContainedLiquid(world, i, j, k, itemStack) && !player.capabilities.isCreativeMode)
                {
                    return new ItemStack(Items.bucket, 1, 0);
                }
            }
        }
        return itemStack;
    }

    private boolean tryPlaceContainedLiquid(World world, int x, int y, int z, ItemStack itemStack)
    {
        Material material = world.getBlock(x, y, z).getMaterial();
        boolean flag = !material.isSolid();

        if (this.isFull == Blocks.air)
        {
            return false;
        }
        else if (!world.isAirBlock(x, y, z) && world.getBlock(x, y, z).getMaterial().isSolid())
        {
            return false;
        }
        if (world.provider.isHellWorld && this.isFull != Blocks.air)
        {
            world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

            for (int l = 0; l < 8; l++)
            {
                world.spawnParticle("largesmoke", x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
            }
        }
        else
        {
            if (!world.isRemote && flag && !material.isLiquid())
            {
                world.func_147480_a(x, y, z, true);
            }
            if (itemStack.getItem() == this && itemStack.getItemDamage() != 4)
            {
                world.setBlock(x, y, z, this.isFull, 0, 3);
            }
            else
            {
                world.setBlock(x, y, z, this.isFull, 3, 3);
            }
        }
        return true;
    }

    private static Block getLiquidFromMeta(int meta)
    {
        switch (meta)
        {
        case 0:
        default:
            return FronosBlocks.coconut_milk;
        case 1:
            return FronosBlocks.mineral_water;
        case 2:
            return FronosBlocks.ovantine;
        case 3:
            return FronosBlocks.tea;
        case 4:
            return FronosBlocks.caramel;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < this.getItemVariantsName().length; ++i)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "coconut_milk_bucket", "mineral_water_bucket", "ovantine_bucket", "tea_bucket", "caramel_bucket" };
    }

    @Override
    public String getTexturesFolder()
    {
        return "fronos";
    }
}