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
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockFlowerMP;

public class BlockPoppy extends BlockFlowerMP
{
    private static final String[] plants = new String[] {
            "white_poppy",
            "orange_poppy",
            "purple_poppy",
    };

    private IIcon[] textures;

    public BlockPoppy(String name)
    {
        super(Material.plants);
        this.setTickRandomly(true);
        final float var4 = 0.2F;
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[BlockPoppy.plants.length];

        for (int i = 0; i < BlockPoppy.plants.length; ++i)
        {
            this.textures[i] = iconRegister.registerIcon("fronos:" + BlockPoppy.plants[i]);
        }
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
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
    {
        final int meta = world.getBlockMetadata(par2, par3, par4);

        switch (meta)
        {
        default:
            this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
            break;
        }
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockPoppy.plants.length; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
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

    @Override
    public boolean isValidPosition(World world, int x, int y, int z, int metadata)
    {
        final Block block = world.getBlock(x, y - 1, z);
        final int meta = world.getBlockMetadata(x, y - 1, z);

        switch (meta)
        {
        default:
            return block instanceof IFronosGrass || block == FronosBlocks.fronos_dirt;
        }
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        final int meta = world.getBlockMetadata(x, y, z);

        return meta;
    }
}