/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockContainerMP;

public abstract class BlockAncientChestMP extends BlockContainerMP
{
    protected Random random = new Random();

    public BlockAncientChestMP()
    {
        super(Material.wood);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        this.setResistance(5.0F);
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeWood);
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
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        if (par1IBlockAccess.getBlock(par2, par3, par4 - 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (par1IBlockAccess.getBlock(par2, par3, par4 + 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        }
        else if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) == this)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (par1IBlockAccess.getBlock(par2 + 1, par3, par4) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        }
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.unifyAdjacentChests(par1World, par2, par3, par4);
        Block l = par1World.getBlock(par2, par3, par4 - 1);
        Block i1 = par1World.getBlock(par2, par3, par4 + 1);
        Block j1 = par1World.getBlock(par2 - 1, par3, par4);
        Block k1 = par1World.getBlock(par2 + 1, par3, par4);

        if (l == this)
        {
            this.unifyAdjacentChests(par1World, par2, par3, par4 - 1);
        }
        if (i1 == this)
        {
            this.unifyAdjacentChests(par1World, par2, par3, par4 + 1);
        }
        if (j1 == this)
        {
            this.unifyAdjacentChests(par1World, par2 - 1, par3, par4);
        }
        if (k1 == this)
        {
            this.unifyAdjacentChests(par1World, par2 + 1, par3, par4);
        }
    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        Block l = par1World.getBlock(par2, par3, par4 - 1);
        Block i1 = par1World.getBlock(par2, par3, par4 + 1);
        Block j1 = par1World.getBlock(par2 - 1, par3, par4);
        Block k1 = par1World.getBlock(par2 + 1, par3, par4);
        byte b0 = 0;
        int l1 = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l1 == 0)
        {
            b0 = 2;
        }
        if (l1 == 1)
        {
            b0 = 5;
        }
        if (l1 == 2)
        {
            b0 = 3;
        }
        if (l1 == 3)
        {
            b0 = 4;
        }
        if (l != this && i1 != this && j1 != this && k1 != this)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
        }
        else
        {
            if ((l == this || i1 == this) && (b0 == 4 || b0 == 5))
            {
                if (l == this)
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4 - 1, b0, 3);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4 + 1, b0, 3);
                }
                par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
            }
            if ((j1 == this || k1 == this) && (b0 == 2 || b0 == 3))
            {
                if (j1 == this)
                {
                    par1World.setBlockMetadataWithNotify(par2 - 1, par3, par4, b0, 3);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2 + 1, par3, par4, b0, 3);
                }
                par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
            }
        }
    }

    public void unifyAdjacentChests(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            Block l = par1World.getBlock(par2, par3, par4 - 1);
            Block i1 = par1World.getBlock(par2, par3, par4 + 1);
            Block j1 = par1World.getBlock(par2 - 1, par3, par4);
            Block k1 = par1World.getBlock(par2 + 1, par3, par4);
            Block l1;
            Block i2;
            byte b0;
            int j2;

            if (l != this && i1 != this)
            {
                if (j1 != this && k1 != this)
                {
                    b0 = 3;

                    if (l.func_149730_j() && !i1.func_149730_j())
                    {
                        b0 = 3;
                    }
                    if (i1.func_149730_j() && !l.func_149730_j())
                    {
                        b0 = 2;
                    }
                    if (j1.func_149730_j() && !k1.func_149730_j())
                    {
                        b0 = 5;
                    }
                    if (k1.func_149730_j() && !j1.func_149730_j())
                    {
                        b0 = 4;
                    }
                }
                else
                {
                    l1 = par1World.getBlock(j1 == this ? par2 - 1 : par2 + 1, par3, par4 - 1);
                    i2 = par1World.getBlock(j1 == this ? par2 - 1 : par2 + 1, par3, par4 + 1);
                    b0 = 3;

                    if (j1 == this)
                    {
                        j2 = par1World.getBlockMetadata(par2 - 1, par3, par4);
                    }
                    else
                    {
                        j2 = par1World.getBlockMetadata(par2 + 1, par3, par4);
                    }
                    if (j2 == 2)
                    {
                        b0 = 2;
                    }
                    if ((l.func_149730_j() || l1.func_149730_j()) && !i1.func_149730_j() && !i2.func_149730_j())
                    {
                        b0 = 3;
                    }
                    if ((i1.func_149730_j() || i2.func_149730_j()) && !l.func_149730_j() && !l1.func_149730_j())
                    {
                        b0 = 2;
                    }
                }
            }
            else
            {
                l1 = par1World.getBlock(par2 - 1, par3, l == this ? par4 - 1 : par4 + 1);
                i2 = par1World.getBlock(par2 + 1, par3, l == this ? par4 - 1 : par4 + 1);
                b0 = 5;

                if (l == this)
                {
                    j2 = par1World.getBlockMetadata(par2, par3, par4 - 1);
                }
                else
                {
                    j2 = par1World.getBlockMetadata(par2, par3, par4 + 1);
                }
                if (j2 == 4)
                {
                    b0 = 4;
                }
                if ((j1.func_149730_j() || l1.func_149730_j()) && !k1.func_149730_j() && !i2.func_149730_j())
                {
                    b0 = 5;
                }
                if ((k1.func_149730_j() || i2.func_149730_j()) && !j1.func_149730_j() && !l1.func_149730_j())
                {
                    b0 = 4;
                }
            }
            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        int l = 0;

        if (par1World.getBlock(par2 - 1, par3, par4) == this)
        {
            ++l;
        }
        if (par1World.getBlock(par2 + 1, par3, par4) == this)
        {
            ++l;
        }
        if (par1World.getBlock(par2, par3, par4 - 1) == this)
        {
            ++l;
        }
        if (par1World.getBlock(par2, par3, par4 + 1) == this)
        {
            ++l;
        }
        return l > 1 ? false : this.isThereANeighborChest(par1World, par2 - 1, par3, par4) ? false : this.isThereANeighborChest(par1World, par2 + 1, par3, par4) ? false : this.isThereANeighborChest(par1World, par2, par3, par4 - 1) ? false : !this.isThereANeighborChest(par1World, par2, par3, par4 + 1);
    }

    protected boolean isThereANeighborChest(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlock(par2, par3, par4) != this ? false : par1World.getBlock(par2 - 1, par3, par4) == this ? true : par1World.getBlock(par2 + 1, par3, par4) == this ? true : par1World.getBlock(par2, par3, par4 - 1) == this ? true : par1World.getBlock(par2, par3, par4 + 1) == this;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return this.getChestTile();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        if (this.chestTexture() == null)
        {
            return;
        }
        this.blockIcon = par1IconRegister.registerIcon(this.chestTexture());
    }

    public abstract String chestTexture();
    public abstract TileEntity getChestTile();
}