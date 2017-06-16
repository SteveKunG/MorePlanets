/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class BlockFlowerMP extends BlockBush
{
    public BlockFlowerMP(Material material)
    {
        super(material);
    }

    public boolean isValidPosition(World world, int x, int y, int z, int metadata)
    {
        if (world.getBlock(x, y - 1, z) == Blocks.air)
        {
            return false;
        }
        return this.canPlaceBlockAt(world, x, y, z);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        Block block = world.getBlock(x, y, z);

        this.dropIfCantStay(world, x, y, z, new ItemStack(block, 1, world.getBlockMetadata(x, y, z)));
    }

    public void dropIfCantStay(World world, int x, int y, int z, ItemStack stack)
    {
        if (!this.canReplace(world, x, y, z, 0, stack))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
        if (world.getBlock(x, y - 1, z) == Blocks.air)
        {
            return false;
        }
        return this.isValidPosition(world, x, y, z, itemStack.getItemDamage());
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
    {
        this.dropIfCantStay(world, x, y, z, new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z)));
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }
}