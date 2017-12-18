/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPBlocks;

public class BlockDungeonBrickSlab extends BlockSlab
{
    public static enum DungeonSlabCategory
    {
        WOOD1, WOOD2, STONE;
    }

    private IIcon[] textures;
    private boolean isDoubleSlab;
    private DungeonSlabCategory category;

    public BlockDungeonBrickSlab(String name, boolean par2, Material material, DungeonSlabCategory cat)
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
        this.textures = new IIcon[8];
        this.textures[0] = par1IconRegister.registerIcon("diona:diona_dungeon_brick");
        this.textures[1] = par1IconRegister.registerIcon("polongnius:polongnius_dungeon_brick");
        this.textures[2] = par1IconRegister.registerIcon("nibiru:nibiru_dungeon_brick");
        this.textures[3] = par1IconRegister.registerIcon("koentus:koentus_dungeon_brick");
        this.textures[4] = par1IconRegister.registerIcon("fronos:fronos_dungeon_brick");
        this.textures[5] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_dungeon_brick");
        this.textures[6] = par1IconRegister.registerIcon("siriusb:sirius_b_dungeon_brick");
        this.textures[7] = par1IconRegister.registerIcon("mercury:mercury_dungeon_brick");
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (this.category == DungeonSlabCategory.WOOD1)
        {
            if (meta == 6 || meta == 14)
            {
                return 15;
            }
            else if (this.isDoubleSlab)
            {
                if (meta == 6 || meta == 14)
                {
                    return 15;
                }
            }
        }
        return 0;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (this.category == DungeonSlabCategory.STONE)
        {
            return this.textures[meta & 7];
        }
        else if (this.category == DungeonSlabCategory.WOOD1)
        {
            return this.textures[meta & 7];
        }
        else if (this.category == DungeonSlabCategory.WOOD2)
        {
            return this.textures[meta & 7];
        }
        return this.blockIcon;
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        int max = 0;

        if (this.category == DungeonSlabCategory.WOOD1)
        {
            max = 8;
        }
        else if (this.category == DungeonSlabCategory.WOOD2)
        {
            max = 1;
        }
        else if (this.category == DungeonSlabCategory.STONE)
        {
            max = 3;
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
        if (this == MPBlocks.dungeon_brick_slab_full)
        {
            return Item.getItemFromBlock(MPBlocks.dungeon_brick_slab_half);
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
        return 4.0F;
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return 40.0F;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        if (block == MPBlocks.dungeon_brick_slab_full)
        {
            return new ItemStack(MPBlocks.dungeon_brick_slab_half, 1, meta);
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
        return new String[] { "dionaBrick", "polongniusBrick", "nibiruBrick", "koentusBrick", "fronosBrick", "kapteynBrick", "siriusBrick", "mercury"};
    }
}