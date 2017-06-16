/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockEuropaSandstoneSlab extends BlockSlab
{
    public static enum EuropaSlabCategory
    {
        WOOD1, WOOD2, STONE;
    }

    private IIcon[] textures;
    private boolean isDoubleSlab;
    private EuropaSlabCategory category;

    public BlockEuropaSandstoneSlab(String name, boolean par2, Material material, EuropaSlabCategory cat)
    {
        super(par2, material);
        this.isDoubleSlab = par2;
        this.category = cat;
        this.setBlockName(name);
        this.useNeighborBrightness = true;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.textures = new IIcon[10];
        this.textures[0] = par1IconRegister.registerIcon("europa:europa_sandstone");
        this.textures[1] = par1IconRegister.registerIcon("europa:europa_sandstone_top");
        this.textures[2] = par1IconRegister.registerIcon("europa:europa_sandstone_bottom");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (this.category == EuropaSlabCategory.STONE)
        {
            return this.textures[meta & 7];
        }
        else if (this.category == EuropaSlabCategory.WOOD1)
        {
            if (meta == 0 || meta == 8)
            {
                switch (side)
                {
                case 0:
                    return this.textures[2]; //BOTTOM
                case 1:
                    return this.textures[1]; //TOP
                case 2:
                    return this.textures[0]; //Z-
                case 3:
                    return this.textures[0]; //Z+
                case 4:
                    return this.textures[0]; //X-
                case 5:
                    return this.textures[0]; //X+
                }
            }
            return this.textures[meta & 7];
        }
        else if (this.category == EuropaSlabCategory.WOOD2)
        {
            return this.textures[meta & 7];
        }
        return this.blockIcon;
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        int max = 0;

        if (this.category == EuropaSlabCategory.WOOD1)
        {
            max = 1;
        }
        else if (this.category == EuropaSlabCategory.WOOD2)
        {
            max = 1;
        }
        else if (this.category == EuropaSlabCategory.STONE)
        {
            max = 1;
        }
        for (int i = 0; i < max; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public String func_150002_b(int meta)
    {
        return new StringBuilder().append(this.getTypes()[this.getWoodType(meta)]).append("").toString();
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta & 7;
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        if (this == EuropaBlocks.double_europa_sandstone_slab)
        {
            return Item.getItemFromBlock(EuropaBlocks.half_europa_sandstone_slab);
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        if (!this.isDoubleSlab)
        {
            return MorePlanetsCore.mpBlocksTab;
        }
        return null;
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        return 0.8F;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        if (block == EuropaBlocks.double_europa_sandstone_slab)
        {
            return new ItemStack(EuropaBlocks.half_europa_sandstone_slab, 1, meta);
        }
        return new ItemStack(this, 1, meta & 7);
    }

    @Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this, 2, par1);
    }

    private int getWoodType(int meta)
    {
        meta = meta & 7 + this.category.ordinal() * 8;

        if (meta < this.getTypes().length)
        {
            return meta;
        }
        return 0;
    }

    private String[] getTypes()
    {
        return new String[] { "europa" };
    }
}