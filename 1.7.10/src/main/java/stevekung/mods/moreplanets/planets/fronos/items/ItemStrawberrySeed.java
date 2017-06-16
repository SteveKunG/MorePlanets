/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ItemStrawberrySeed extends ItemMorePlanet implements IPlantable
{
    public ItemStrawberrySeed(String name)
    {
        super();
        this.setTextureName("fronos:strawberry_seed");
        this.setUnlocalizedName(name);
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return FronosBlocks.strawberry_crops;
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
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else if (player.canPlayerEdit(par4, par5, par6, par7, itemStack) && player.canPlayerEdit(par4, par5 + 1, par6, par7, itemStack))
        {
            Block soil = world.getBlock(par4, par5, par6);

            if (soil != null && soil.canSustainPlant(world, par4, par5, par6, ForgeDirection.UP, this) && world.isAirBlock(par4, par5 + 1, par6))
            {
                world.setBlock(par4, par5 + 1, par6, FronosBlocks.strawberry_crops);
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
}