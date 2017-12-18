/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntitySpaceOysterClose;

public class BlockSpaceOysterClose extends BlockBaseMP implements ITileEntityProvider
{
    public BlockSpaceOysterClose(String name)
    {
        super(Material.plants);
        this.setHardness(0.6F);
        this.setBlockBounds(0.225F, 0.0F, 0.225F, 0.775F, 0.275F, 0.775F);
        this.setTickRandomly(true);
        this.setBlockName(name);
        this.setBlockTextureName("fronos:oyster");
    }

    @Override
    public int getRenderType()
    {
        return MorePlanetsCore.proxy.getBlockRender(this);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return 4;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);

        if (!world.isRemote)
        {
            if (world.isDaytime() || world.getBlockLightValue(x, y + 1, z) >= 9)
            {
                if (world.rand.nextInt(10) == 0)
                {
                    world.setBlock(x, y, z, FronosBlocks.space_oyster_open, world.getBlockMetadata(x, y, z), 3);
                }
                if (world.getBlock(x, y - 1, z) == Blocks.sand || world.getBlock(x, y - 1, z) == FronosBlocks.golden_grass || world.getBlock(x, y - 1, z) == FronosBlocks.fronos_sand && world.getBlockMetadata(x, y - 1, z) == 1)
                {
                    world.setBlock(x, y, z, FronosBlocks.space_oyster_open, world.getBlockMetadata(x, y, z), 3);
                }
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
        this.canBlockStay(par1World, par2, par3, par4);
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        Block block = par1World.getBlock(par2, par3 - 1, par4);

        if (block == null)
        {
            return false;
        }
        if (!block.isLeaves(par1World, par2, par3 - 1, par4) && !block.isOpaqueCube())
        {
            return false;
        }
        return par1World.getBlock(par2, par3 - 1, par4).getMaterial().blocksMovement();
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        byte change = 0;

        switch (angle)
        {
        case 0:
            change = 0;
            break;
        case 1:
            change = 3;
            break;
        case 2:
            change = 1;
            break;
        case 3:
            change = 2;
            break;
        }
        world.setBlockMetadataWithNotify(x, y, z, change, 3);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntitySpaceOysterClose();
    }
}