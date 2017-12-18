/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockCoralMP extends Block
{
    protected BlockCoralMP(Material material)
    {
        super(material);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        this.checkAndDropBlock(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        this.checkAndDropBlock(world, x, y, z);
        super.onNeighborBlockChange(world, x, y, z, block);
    }

    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
        return this.canBlockStay(world, x, y, z, itemStack.getItemDamage());
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return super.canBlockStay(world, x, y, z);
    }

    public boolean canBlockStay(World world, int x, int y, int z, int metadata)
    {
        return this.canBlockStay(world, x, y, z);
    }

    protected void checkAndDropBlock(World world, int x, int y, int z)
    {
        if (!this.canBlockStay(world, x, y, z, world.getBlockMetadata(x, y, z)))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
}