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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ItemFronosBasic extends ItemBaseMP
{
    public ItemFronosBasic(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < this.getItemVariantsName().length; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int par4, int par5, int par6, int side, float par8, float par9, float par10)
    {
        Block block = world.getBlock(par4, par5, par6);

        if (player.canPlayerEdit(par4, par5, par6, side, itemStack) && block == FronosBlocks.coconut)
        {
            if (itemStack.getItemDamage() == 7)
            {
                if (world.isRemote)
                {
                    return true;
                }
                else
                {
                    if (world.rand.nextInt(10) == 0)
                    {
                        world.func_147480_a(par4, par5, par6, false);
                        world.setBlock(par4, par5, par6, FronosBlocks.coconut_milk);
                    }
                    world.playSoundEffect(par4, par5, par6, "dig.wood", 5.0F, 1.2F);
                    --itemStack.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "mineral_crystal", "mineral_piece", "black_diamond", "iridium_ingot", "compressed_black_diamond", "compressed_iridium", "golden_wheat", "fronos_rock", "strawberry_feather", "cheese_string" };
    }

    @Override
    public String getTexturesFolder()
    {
        return "fronos";
    }
}