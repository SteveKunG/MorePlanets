/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.moons.io.blocks.IoBlocks;

public class ItemTitaniumBucket extends ItemBucket
{
    private Block isFull;

    public ItemTitaniumBucket(Block block, String name)
    {
        super(block);
        this.isFull = block;
        this.setMaxStackSize(1);
        this.setTextureName("io:" + name);
        this.setUnlocalizedName(name);
        this.setContainerItem(IoItems.titanium_bucket);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        boolean flag = this.isFull == Blocks.air;

        if (itemstack.getItem() == IoItems.titanium_bucket)
        {
            MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);

            if (pos == null)
            {
                return itemstack;
            }
            else
            {
                Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
                int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

                if (block == IoBlocks.io_lava && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemstack;
                    }
                    if (--itemstack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(IoItems.io_lava_bucket);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(IoItems.io_lava_bucket)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(IoItems.io_lava_bucket), 1.0F);
                    }
                    return itemstack;
                }
                else if (block == IoBlocks.liquid_red_sulfur && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemstack;
                    }
                    if (--itemstack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(IoItems.liquid_red_sulfur_bucket);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(IoItems.liquid_red_sulfur_bucket)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(IoItems.liquid_red_sulfur_bucket), 1.0F);
                    }
                    return itemstack;
                }
                else if (block == IoBlocks.liquid_yellow_sulfur && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemstack;
                    }
                    if (--itemstack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(IoItems.liquid_yellow_sulfur_bucket);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(IoItems.liquid_yellow_sulfur_bucket)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(IoItems.liquid_yellow_sulfur_bucket), 1.0F);
                    }
                    return itemstack;
                }
                else if (block == IoBlocks.liquid_orange_sulfur && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemstack;
                    }
                    if (--itemstack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(IoItems.liquid_orange_sulfur_bucket);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(IoItems.liquid_orange_sulfur_bucket)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(IoItems.liquid_orange_sulfur_bucket), 1.0F);
                    }
                    return itemstack;
                }
                else if (block == IoBlocks.liquid_brown_sulfur && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemstack;
                    }
                    if (--itemstack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(IoItems.liquid_brown_sulfur_bucket);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(IoItems.liquid_brown_sulfur_bucket)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(IoItems.liquid_brown_sulfur_bucket), 1.0F);
                    }
                    return itemstack;
                }
                else if (block == IoBlocks.io_black_lava && meta == 0)
                {
                    if (player.capabilities.isCreativeMode)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return itemstack;
                    }
                    if (--itemstack.stackSize <= 0)
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        return new ItemStack(IoItems.io_black_lava_bucket);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(IoItems.io_black_lava_bucket)))
                    {
                        world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
                        player.entityDropItem(new ItemStack(IoItems.io_black_lava_bucket), 1.0F);
                    }
                    return itemstack;
                }
                else
                {
                    return itemstack;
                }
            }
        }
        else
        {
            MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, flag);

            if (pos == null)
            {
                return itemstack;
            }
            else if (pos.typeOfHit == MovingObjectType.BLOCK)
            {
                int i = pos.blockX;
                int j = pos.blockY;
                int k = pos.blockZ;

                if (!world.canMineBlock(player, i, j, k))
                {
                    return itemstack;
                }
                if (this.isFull == Blocks.air)
                {
                    return new ItemStack(IoItems.titanium_bucket);
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
                if (!player.canPlayerEdit(i, j, k, pos.sideHit, itemstack))
                {
                    return itemstack;
                }
                if (this.tryPlaceContainedLiquid(world, i, j, k) && !player.capabilities.isCreativeMode)
                {
                    return new ItemStack(IoItems.titanium_bucket);
                }
            }
        }
        return itemstack;
    }

    @Override
    public boolean tryPlaceContainedLiquid(World par1World, int x, int y, int z)
    {
        Material material = par1World.getBlock(x, y, z).getMaterial();
        boolean flag = !material.isSolid();

        if (this.isFull == Blocks.air)
        {
            return false;
        }
        else if (!par1World.isAirBlock(x, y, z) && par1World.getBlock(x, y, z).getMaterial().isSolid())
        {
            return false;
        }
        else
        {
            if (!par1World.isRemote && flag && !material.isLiquid())
            {
                par1World.func_147480_a(x, y, z, true);
            }
            par1World.setBlock(x, y, z, this.isFull, 0, 3);
        }
        return true;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.mpItemsTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }
}