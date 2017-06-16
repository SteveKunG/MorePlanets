/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockCoconut extends BlockBaseMP
{
    public BlockCoconut(String name)
    {
        super(Material.wood);
        this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
        this.setTickRandomly(true);
        this.setHardness(1.0F);
        this.setStepSound(Block.soundTypeWood);
        this.setBlockName(name);
        this.setBlockTextureName("fronos:coconut_block");
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
        par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
    }

    @Override
    public void updateTick(World world, int par2, int par3, int par4, Random rand)
    {
        if (!world.isRemote)
        {
            if (!(world.getBlock(par2, par3 + 1, par4) == FronosBlocks.fronos_colorized_leaves))
            {
                this.tryToFall(world, par2, par3, par4);
            }
        }
    }

    private void tryToFall(World par1World, int par2, int par3, int par4)
    {
        if (canFallBelow(par1World, par2, par3 - 1, par4) && par3 >= 0)
        {
            par1World.setBlock(par2, par3, par4, Blocks.air, 0, 3);

            while (canFallBelow(par1World, par2, par3 - 1, par4) && par3 > 0)
            {
                --par3;
            }
            if (par3 > 0)
            {
                par1World.setBlock(par2, par3, par4, this, 0, 3);
            }
        }
    }

    public static boolean canFallBelow(World par0World, int par1, int par2, int par3)
    {
        Block var4 = par0World.getBlock(par1, par2, par3);

        if (var4.getMaterial() == Material.air)
        {
            return true;
        }
        else if (var4 == Blocks.fire)
        {
            return true;
        }
        else
        {
            Material var5 = var4.getMaterial();
            return var5 == Material.water ? true : var5 == Material.lava;
        }
    }

    @Override
    public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6)
    {
        if (!par1World.isRemote && par1World.rand.nextInt(100) == 0)
        {
            par1World.playSoundEffect(par2, par3, par4, "dig.wood", 5.0F, 1.2F);
            par1World.setBlock(par2, par3, par4, FronosBlocks.coconut_milk);
        }
    }
}