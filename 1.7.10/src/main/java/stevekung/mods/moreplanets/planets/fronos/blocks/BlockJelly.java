/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockJelly extends BlockBreakable
{
    private IIcon[] textures;

    public BlockJelly(String name)
    {
        super(name, Material.cloth, false);
        this.setStepSound(MorePlanetsCore.soundTypeSmallSlime);
        this.setHardness(0.2F);
        this.setBlockName(name);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public int getRenderType()
    {
        return MorePlanetsCore.proxy.getBlockRender(this);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        final float dy = 0.75F;
        final Block block = par1World.getBlock(par2, par3 - 1, par4);
        AxisAlignedBB box = null;

        if (block != this && block != Blocks.air)
        {
            box = AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1 - dy, par4 + 1);
        }
        return box;
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        this.onEntityJump(par1World, par2, par3, par4, par5Entity);
    }

    private void onEntityJump(World world, int i, int j, int k, Entity par5Entity)
    {
        float m = 1.05F;
        par5Entity.fallDistance = 0.0F;

        if (world.getBlock(i + 1, j, k) == this && world.getBlock(i - 1, j, k) == this && world.getBlock(i, j, k + 1) == this && world.getBlock(i, j, k - 1) == this)
        {
            m = 1.55F;
        }

        par5Entity.fallDistance = 0.0F;

        if (par5Entity.motionY < -0.15)
        {
            par5Entity.motionX *= m;
            par5Entity.motionY *= -m;
            par5Entity.motionZ *= m;
        }
        else
        {
            par5Entity.motionX *= 0.5D;
            par5Entity.motionY *= 0.5D;
            par5Entity.motionZ *= 0.5D;
        }
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.textures = new IIcon[this.getTypes().length];

        for (int i = 0; i < this.getTypes().length; ++i)
        {
            this.textures[i] = par1IconRegister.registerIcon("fronos:" + this.getTypes()[i]);
        }
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= this.textures.length)
        {
            meta = 0;
        }
        return this.textures[meta];
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < this.getTypes().length; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    private String[] getTypes()
    {
        return new String[] { "grape_jelly_block", "raspberry_jelly_block", "strawberry_jelly_block", "berry_jelly_block", "lime_jelly_block", "orange_jelly_block", "green_jelly_block", "lemon_jelly_block" };
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}