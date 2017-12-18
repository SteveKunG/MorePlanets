/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamGolem;

public class ItemCreamGolem extends ItemBaseMP
{
    public ItemCreamGolem(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < this.getItemVariantsName().length; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float xoffset, float yoffset, float zoffset)
    {
        int meta = itemStack.getItemDamage();

        if (world.isRemote)
        {
            return true;
        }
        else
        {
            Block targetBlock = world.getBlock(x, y, z);
            x += Facing.offsetsXForSide[side];
            y += Facing.offsetsYForSide[side];
            z += Facing.offsetsZForSide[side];
            double yOffsetForFence = 0.0D;

            if (side == 1 && targetBlock instanceof BlockFence)
            {
                yOffsetForFence = 0.5D;
            }

            if (meta == 0)
            {
                EntityCreamGolem cream = new EntityCreamGolem(world);

                if (ItemCreamGolem.spawnVanillaCreamGolem(world, x + 0.5D, y + yOffsetForFence, z + 0.5D) && !player.capabilities.isCreativeMode)
                {
                    --itemStack.stackSize;
                }
                if (itemStack.hasDisplayName())
                {
                    ((EntityLiving)cream).setCustomNameTag(itemStack.getDisplayName());
                }
            }
            else if (meta == 1)
            {
                EntityCreamGolem cream = new EntityCreamGolem(world);

                if (ItemCreamGolem.spawnChocolateCreamGolem(world, x + 0.5D, y + yOffsetForFence, z + 0.5D) && !player.capabilities.isCreativeMode)
                {
                    --itemStack.stackSize;
                }
                if (itemStack.hasDisplayName())
                {
                    ((EntityLiving)cream).setCustomNameTag(itemStack.getDisplayName());
                }
            }
            else if (meta == 2)
            {
                EntityCreamGolem cream = new EntityCreamGolem(world);

                if (ItemCreamGolem.spawnStrawberryCreamGolem(world, x + 0.5D, y + yOffsetForFence, z + 0.5D) && !player.capabilities.isCreativeMode)
                {
                    --itemStack.stackSize;
                }
                if (itemStack.hasDisplayName())
                {
                    ((EntityLiving)cream).setCustomNameTag(itemStack.getDisplayName());
                }
            }
            else if (meta == 3)
            {
                EntityCreamGolem cream = new EntityCreamGolem(world);

                if (ItemCreamGolem.spawnOrangeCreamGolem(world, x + 0.5D, y + yOffsetForFence, z + 0.5D) && !player.capabilities.isCreativeMode)
                {
                    --itemStack.stackSize;
                }
                if (itemStack.hasDisplayName())
                {
                    ((EntityLiving)cream).setCustomNameTag(itemStack.getDisplayName());
                }
            }
            else if (meta == 4)
            {
                if (ItemCreamGolem.spawnTeaCreamGolem(world, x + 0.5D, y + yOffsetForFence, z + 0.5D) && !player.capabilities.isCreativeMode)
                {
                    --itemStack.stackSize;
                }

                EntityCreamGolem cream = new EntityCreamGolem(world);

                if (itemStack.hasDisplayName())
                {
                    ((EntityLiving)cream).setCustomNameTag(itemStack.getDisplayName());
                }
            }
            else if (meta == 5)
            {
                if (ItemCreamGolem.spawnLemonCreamGolem(world, x + 0.5D, y + yOffsetForFence, z + 0.5D) && !player.capabilities.isCreativeMode)
                {
                    --itemStack.stackSize;
                }

                EntityCreamGolem cream = new EntityCreamGolem(world);

                if (itemStack.hasDisplayName())
                {
                    ((EntityLiving)cream).setCustomNameTag(itemStack.getDisplayName());
                }
            }
            return true;
        }
    }

    private static boolean spawnVanillaCreamGolem(World world, double x, double y, double z)
    {
        EntityCreamGolem cream = new EntityCreamGolem(world);
        cream.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
        world.spawnEntityInWorld(cream);
        cream.setCreamGolemType(0);
        cream.playLivingSound();
        return true;
    }

    private static boolean spawnChocolateCreamGolem(World world, double x, double y, double z)
    {
        EntityCreamGolem cream = new EntityCreamGolem(world);
        cream.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
        world.spawnEntityInWorld(cream);
        cream.setCreamGolemType(1);
        cream.playLivingSound();
        return true;
    }

    private static boolean spawnStrawberryCreamGolem(World world, double x, double y, double z)
    {
        EntityCreamGolem cream = new EntityCreamGolem(world);
        cream.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
        world.spawnEntityInWorld(cream);
        cream.setCreamGolemType(2);
        cream.playLivingSound();
        return true;
    }

    private static boolean spawnOrangeCreamGolem(World world, double x, double y, double z)
    {
        EntityCreamGolem cream = new EntityCreamGolem(world);
        cream.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
        world.spawnEntityInWorld(cream);
        cream.setCreamGolemType(3);
        cream.playLivingSound();
        return true;
    }

    private static boolean spawnTeaCreamGolem(World world, double x, double y, double z)
    {
        EntityCreamGolem cream = new EntityCreamGolem(world);
        cream.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
        world.spawnEntityInWorld(cream);
        cream.setCreamGolemType(4);
        cream.playLivingSound();
        return true;
    }

    private static boolean spawnLemonCreamGolem(World world, double x, double y, double z)
    {
        EntityCreamGolem cream = new EntityCreamGolem(world);
        cream.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
        world.spawnEntityInWorld(cream);
        cream.setCreamGolemType(5);
        cream.playLivingSound();
        return true;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "vanilla_cream_golem", "chocolate_cream_golem", "strawberry_cream_golem", "orange_cream_golem", "tea_cream_golem", "lemon_cream_golem" };
    }

    @Override
    public String getTexturesFolder()
    {
        return "fronos";
    }
}