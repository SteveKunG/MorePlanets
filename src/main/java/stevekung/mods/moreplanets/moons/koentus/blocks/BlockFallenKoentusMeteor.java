/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;

public class BlockFallenKoentusMeteor extends Block
{
    public BlockFallenKoentusMeteor(String name)
    {
        super(Material.rock);
        this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
        this.setHardness(50.0F);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("koentus:koentus_meteor");
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
    public Item getItemDropped(int meta, Random random, int par3)
    {
        return KoentusItems.koentus_item;
    }

    @Override
    public int damageDropped(int meta)
    {
        return 3;
    }

    @Override
    public boolean canSilkHarvest()
    {
        return true;
    }

    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!World.doesBlockHaveSolidTopSurface(par1World, par2, par3 + 1, par4))
        {
            for (int l = 0; l < 4; ++l)
            {
                double d0 = par2 + par5Random.nextFloat();
                double d1 = par3 + par5Random.nextFloat();
                double d2 = par4 + par5Random.nextFloat();
                double d3 = 0.0D;
                double d4 = 0.0D;
                double d5 = 0.0D;
                d3 = (par5Random.nextFloat() - 0.5D) * 0.5D;
                d4 = (par5Random.nextFloat() - 0.5D) * 0.5D;
                d5 = (par5Random.nextFloat() - 0.5D) * 0.5D;
                MorePlanetsCore.proxy.spawnMotionParticle("koentusSmoke", d0, d1, d2, d3, d4, d5);
            }
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

        if (this.getItemDropped(par5, world.rand, par7) != Item.getItemFromBlock(this))
        {
            int var8 =  MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
            this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
        }
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
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            this.tryToFall(par1World, par2, par3, par4);
        }
    }

    private void tryToFall(World par1World, int par2, int par3, int par4)
    {
        if (BlockFallenKoentusMeteor.canFallBelow(par1World, par2, par3 - 1, par4) && par3 >= 0)
        {
            par1World.setBlock(par2, par3, par4, Blocks.air, 0, 3);

            while (BlockFallenKoentusMeteor.canFallBelow(par1World, par2, par3 - 1, par4) && par3 > 0)
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
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }
}