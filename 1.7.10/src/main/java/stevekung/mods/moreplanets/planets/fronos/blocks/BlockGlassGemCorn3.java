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
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockFlowerMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockGlassGemCorn3 extends BlockFlowerMP
{
    public BlockGlassGemCorn3(String name)
    {
        super(Material.plants);
        this.setStepSound(Block.soundTypeGrass);
        this.setTickRandomly(true);
        float f = 0.4F;
        this.setBlockName(name);
        this.setHardness(0.2F);
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon("fronos:glass_gem_corn_0");
    }

    @Override
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return FronosItems.glass_gem_corn;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return null;
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        return FronosItems.glass_gem_corn;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y - 1, z);

        if (world.getBlockMetadata(x, y, z) >= 1)
        {
            return block == FronosBlocks.glass_gem_corn2;
        }
        return block == FronosBlocks.glass_gem_corn2;
    }

    @Override
    public boolean isValidPosition(World world, int x, int y, int z, int meta)
    {
        Block block = world.getBlock(x, y - 1, z);

        if (world.getBlockMetadata(x, y, z) >= 1)
        {
            return block == FronosBlocks.glass_gem_corn2;
        }
        return block == FronosBlocks.glass_gem_corn2;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        return this.isValidPosition(world, x, y, z, -1);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        super.onNeighborBlockChange(world, x, y, z, block);
        this.canBlockStay(world, x, y, z);

        if (world.getBlock(x, y - 1, z) != FronosBlocks.glass_gem_corn2)
        {
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        Block block = par1World.getBlock(par2, par3 - 1, par4);

        if (block == null || !(block == FronosBlocks.glass_gem_corn2))
        {
            return false;
        }
        return par1World.getBlock(par2, par3 - 1, par4).getMaterial().blocksMovement();
    }

    @Override
    public int damageDropped(int meta)
    {
        return 0;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random rand)
    {
        return 1;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return 0;
    }
}