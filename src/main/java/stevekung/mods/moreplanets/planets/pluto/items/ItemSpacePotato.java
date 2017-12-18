/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.items.ItemFoodMP;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;

public class ItemSpacePotato extends ItemFoodMP implements IPlantable
{
    private static int[] foodHunger = new int[] {
            1,//0
            6//1
    };
    private static float[] foodSaturation = new float[] {
            0.3F,//0
            0.6F//1
    };

    public ItemSpacePotato(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setHasSubtypes(true);
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return PlutoBlocks.space_potato_block;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return 0;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Crop;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.eat;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else if (player.canPlayerEdit(par4, par5, par6, par7, itemStack) && player.canPlayerEdit(par4, par5 + 1, par6, par7, itemStack))
        {
            Block soil = world.getBlock(par4, par5, par6);

            if (soil != null && soil.canSustainPlant(world, par4, par5, par6, ForgeDirection.UP, this) && world.isAirBlock(par4, par5 + 1, par6) && itemStack.getItemDamage() == 0)
            {
                world.setBlock(par4, par5 + 1, par6, PlutoBlocks.space_potato_block);
                --itemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < this.getItemVariantsName().length; i++)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int getFoodAmount(ItemStack itemStack)
    {
        return foodHunger[itemStack.getItemDamage()];
    }

    @Override
    public float getFoodSaturation(ItemStack itemStack)
    {
        return foodSaturation[itemStack.getItemDamage()];
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "space_potato", "baked_space_potato" };
    }

    @Override
    public String getResourceLocation()
    {
        return "pluto";
    }
}