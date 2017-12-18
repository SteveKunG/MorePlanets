/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityIceCrystalMeteor;

public class BlockFallenIceCrystalMeteor extends BlockBaseMP implements ITileEntityProvider
{
    public BlockFallenIceCrystalMeteor(String name)
    {
        super(Material.glass);
        this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeGlass);
        this.setBlockTextureName("kapteynb:fallen_ice_crystal_meteor");
        this.setBlockName(name);
    }

    @Override
    public int getRenderType()
    {
        return MorePlanetsCore.proxy.getBlockRender(this);
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int fortune)
    {
        super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, fortune);

        if (this.getItemDropped(par5, world.rand, fortune) != Item.getItemFromBlock(this))
        {
            int var8 = MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
            this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
        }
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
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return true;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random rand)
    {
        return 1 + rand.nextInt(1);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        return KapteynBItems.kapteyn_b_item;
    }

    @Override
    public int damageDropped(int meta)
    {
        return 5;
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
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            this.tryToFall(world, x, y, z);
        }
    }

    private void tryToFall(World par1World, int par2, int par3, int par4)
    {
        if (this.canFallBelow(par1World, par2, par3 - 1, par4) && par3 >= 0)
        {
            par1World.setBlock(par2, par3, par4, Blocks.air, 0, 3);

            while (this.canFallBelow(par1World, par2, par3 - 1, par4) && par3 > 0)
            {
                --par3;
            }
            if (par3 > 0)
            {
                par1World.setBlock(par2, par3, par4, this, 0, 3);
            }
        }
    }

    boolean canFallBelow(World par0World, int par1, int par2, int par3)
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
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return true;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return new ItemStack(this, 1, 0);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityIceCrystalMeteor();
    }
}