/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityUltraVioletFake;

public class BlockUltraVioletSolarFake extends BlockContainer implements IPartialSealableBlock, ITileEntityProvider
{
    public BlockUltraVioletSolarFake(String assetName)
    {
        super(Material.iron);
        this.setStepSound(Block.soundTypeMetal);
        this.setResistance(1000000000000000.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("polongnius:ultra_violet_solar");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return this.blockIcon;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean canDropFromExplosion(Explosion par1Explosion)
    {
        return false;
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        final TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);

        if (tileEntity != null)
        {
            final BlockVec3 mainBlockPosition = ((TileEntityUltraVioletFake) tileEntity).mainBlockPosition;

            if (mainBlockPosition != null)
            {
                return mainBlockPosition.getBlock(par1World).getBlockHardness(par1World, par2, par3, par4);
            }
        }
        return this.blockHardness;
    }

    @Override
    public boolean isSealed(World world, int x, int y, int z, ForgeDirection direction)
    {
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
    {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityUltraVioletFake)
        {
            ((TileEntityUltraVioletFake) tileEntity).onBlockRemoval();
        }
        super.breakBlock(world, x, y, z, par5, par6);
    }

    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        final TileEntityUltraVioletFake tileEntity = (TileEntityUltraVioletFake) par1World.getTileEntity(x, y, z);
        return tileEntity.onBlockActivated(par1World, x, y, z, par5EntityPlayer);
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int meta)
    {
        return new TileEntityUltraVioletFake();
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World par1World, int x, int y, int z)
    {
        TileEntity tileEntity = par1World.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityUltraVioletFake)
        {
            BlockVec3 mainBlockPosition = ((TileEntityUltraVioletFake) tileEntity).mainBlockPosition;

            if (mainBlockPosition != null)
            {
                Block mainBlockID = par1World.getBlock(mainBlockPosition.x, mainBlockPosition.y, mainBlockPosition.z);

                if (Blocks.air != mainBlockID)
                {
                    return mainBlockID.getPickBlock(target, par1World, mainBlockPosition.x, mainBlockPosition.y, mainBlockPosition.z);
                }
            }
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
        final TileEntity tileEntity = worldObj.getTileEntity(target.blockX, target.blockY, target.blockZ);

        if (worldObj.getBlockMetadata(target.blockX, target.blockY, target.blockZ) == 6)
        {
            return true;
        }

        if (tileEntity instanceof TileEntityUltraVioletFake)
        {
            final BlockVec3 mainBlockPosition = ((TileEntityUltraVioletFake) tileEntity).mainBlockPosition;

            if (mainBlockPosition != null)
            {
                effectRenderer.addBlockHitEffects(mainBlockPosition.x, mainBlockPosition.y, mainBlockPosition.z, target);
            }
        }
        return super.addHitEffects(worldObj, target, effectRenderer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    public void makeFakeBlock(World worldObj, BlockVec3 position, BlockVec3 mainBlock, int meta)
    {
        worldObj.setBlock(position.x, position.y, position.z, this, meta, 3);
        ((TileEntityUltraVioletFake) worldObj.getTileEntity(position.x, position.y, position.z)).setMainBlock(mainBlock);
    }
}