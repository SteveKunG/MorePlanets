/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.blocks.base.BlockContainerMP;
import stevekung.mods.moreplanets.core.tileentities.TileEntityTreasureChestMP;

public abstract class BlockTreasureChestMP extends BlockContainerMP
{
    protected Random random = new Random();

    public BlockTreasureChestMP()
    {
        super(Material.rock);
        this.setResistance(10000000.0F);
        this.setHardness(-1.0F);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        this.setBlockTextureName(this.getTextureLocation());
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
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack stack)
    {
        byte var10 = 0;
        int var11 = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (var11 == 0)
        {
            var10 = 2;
        }
        if (var11 == 1)
        {
            var10 = 5;
        }
        if (var11 == 2)
        {
            var10 = 3;
        }
        if (var11 == 3)
        {
            var10 = 4;
        }
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var10, 3);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        Object var10 = par1World.getTileEntity(par2, par3, par4);

        if (var10 == null)
        {
            return true;
        }
        else if (par1World.isSideSolid(par2, par3 + 1, par4, ForgeDirection.DOWN))
        {
            return true;
        }
        else if (par1World.getBlock(par2 - 1, par3, par4) == this && par1World.isSideSolid(par2 - 1, par3 + 1, par4, ForgeDirection.DOWN))
        {
            return true;
        }
        else if (par1World.getBlock(par2 + 1, par3, par4) == this && par1World.isSideSolid(par2 + 1, par3 + 1, par4, ForgeDirection.DOWN))
        {
            return true;
        }
        else if (par1World.getBlock(par2, par3, par4 - 1) == this && par1World.isSideSolid(par2, par3 + 1, par4 - 1, ForgeDirection.DOWN))
        {
            return true;
        }
        else if (par1World.getBlock(par2, par3, par4 + 1) == this && par1World.isSideSolid(par2, par3 + 1, par4 + 1, ForgeDirection.DOWN))
        {
            return true;
        }
        else
        {
            if (par1World.isRemote)
            {
                return true;
            }
            else
            {
                par5EntityPlayer.displayGUIChest((IInventory) var10);
                return true;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World par1World, int meta)
    {
        return this.getTreasureChest();
    }

    public abstract String getTextureLocation();
    public abstract TileEntityTreasureChestMP getTreasureChest();
}