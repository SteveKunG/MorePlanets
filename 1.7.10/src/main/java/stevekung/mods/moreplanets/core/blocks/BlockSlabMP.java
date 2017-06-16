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

public class BlockSlabMP extends BlockSlab
{
    public static enum SlabCategory
    {
        WOOD1, WOOD2, STONE;
    }

    private static String[] woodTypes = new String[] {
            "diona",
            "quonBrick",
            "chiseledQuon",
            "polongnius",
            "nibiru",
            "koentus",
            "koentusStone",
            "koentusBrick",
            "fronos",
            "fronosBrick",
            "fronosCrackBrick",
            "kapteyn",
            "sirius",
            "mercury",
            "venus",
            "pluto"
    };

    private static String[] rockTypes = new String[] {
            "ancientDarkWood",
            "orangeWood",
            "coconutWood",
            "redMapleWood",
            "crystalWood",
            "europaWood",
            "alienWood"
    };

    private IIcon[] textures;
    protected boolean isDoubleSlab;
    private SlabCategory category;

    public BlockSlabMP(String name, boolean isDoubleSlab, Material material, SlabCategory cat)
    {
        super(isDoubleSlab, material);
        this.isDoubleSlab = isDoubleSlab;
        this.category = cat;
        this.setBlockName(name);

        if (material == Material.wood)
        {
            this.setHardness(2.0F);
            this.setResistance(5.0F);
            this.setStepSound(Block.soundTypeWood);
        }
        this.useNeighborBrightness = true;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (this.category == SlabCategory.WOOD2)
        {
            if (meta == 4 || meta == 12)
            {
                return 15;
            }
            else if (this.isDoubleSlab)
            {
                if (meta == 4 || meta == 12)
                {
                    return 15;
                }
            }
        }
        return 0;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        /**Wood Type**/
        if (this.category == SlabCategory.STONE)
        {
            this.textures = new IIcon[8];
            this.textures[0] = par1IconRegister.registerIcon("nibiru:ancient_dark_wood_planks");
            this.textures[1] = par1IconRegister.registerIcon("nibiru:orange_wood_planks");
            this.textures[2] = par1IconRegister.registerIcon("fronos:coconut_wood_planks");
            this.textures[3] = par1IconRegister.registerIcon("fronos:maple_wood_planks");
            this.textures[4] = par1IconRegister.registerIcon("koentus:crystal_planks");
            this.textures[5] = par1IconRegister.registerIcon("europa:europa_planks");
            this.textures[6] = par1IconRegister.registerIcon("mpcore:darkasteroids/alien_wood_planks");
        }
        /**Stone Type 2**/
        else if (this.category == SlabCategory.WOOD2)
        {
            this.textures = new IIcon[8];
            this.textures[0] = par1IconRegister.registerIcon("fronos:fronos_cobblestone");
            this.textures[1] = par1IconRegister.registerIcon("fronos:fronos_stone_brick");
            this.textures[2] = par1IconRegister.registerIcon("fronos:cracked_fronos_stone_brick");
            this.textures[3] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_cobblestone");
            this.textures[4] = par1IconRegister.registerIcon("siriusb:sirius_b_carbon_cobblestone");
            this.textures[5] = par1IconRegister.registerIcon("mercury:mercury_cobblestone");
            this.textures[6] = par1IconRegister.registerIcon("venus:venus_cobblestone");
            this.textures[7] = par1IconRegister.registerIcon("pluto:pluto_cobblestone");
        }
        /**Stone Type 1**/
        else
        {
            this.textures = new IIcon[8];
            this.textures[0] = par1IconRegister.registerIcon("diona:diona_cobblestone");
            this.textures[1] = par1IconRegister.registerIcon("diona:quontonium_brick");
            this.textures[2] = par1IconRegister.registerIcon("diona:chiseled_quontonium");
            this.textures[3] = par1IconRegister.registerIcon("polongnius:polongnius_cobblestone");
            this.textures[4] = par1IconRegister.registerIcon("nibiru:nibiru_cobblestone");
            this.textures[5] = par1IconRegister.registerIcon("koentus:koentus_cobblestone");
            this.textures[6] = par1IconRegister.registerIcon("koentus:koentus_ancient_stone");
            this.textures[7] = par1IconRegister.registerIcon("koentus:ancient_koentus_brick");
        }
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (this.category == SlabCategory.STONE)
        {
            return this.textures[meta & 7];
        }
        else if (this.category == SlabCategory.WOOD1)
        {
            return this.textures[meta & 7];
        }
        else if (this.category == SlabCategory.WOOD2)
        {
            return this.textures[meta & 7];
        }
        return this.blockIcon;
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        int max = 0;

        if (this.category == SlabCategory.WOOD1)
        {
            max = 8;
        }
        else if (this.category == SlabCategory.WOOD2)
        {
            max = 8;
        }
        else if (this.category == SlabCategory.STONE)
        {
            max = 7;
        }
        for (int i = 0; i < max; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public String func_150002_b(int meta)
    {
        if (this.category == SlabCategory.STONE)
        {
            return new StringBuilder().append(BlockSlabMP.rockTypes[this.getTypeFromMeta(meta)]).append("").toString();
        }
        return new StringBuilder().append(BlockSlabMP.woodTypes[this.getWoodType(meta)]).append("").toString();
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta & 7;
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        if (this.isDoubleSlab)
        {
            if (this == MPBlocks.stone_slab_full)
            {
                return Item.getItemFromBlock(MPBlocks.stone_slab_half);
            }
            if (this == MPBlocks.wooden_slab_full)
            {
                return Item.getItemFromBlock(MPBlocks.wooden_slab_half);
            }
            else
            {
                return Item.getItemFromBlock(MPBlocks.stone_slab_half2);
            }
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
        int meta = world.getBlockMetadata(x, y, z);
        float hardness = this.blockHardness;

        if (this.category == SlabCategory.WOOD1)
        {
            switch (meta & 7)
            {
            case 0:
            case 6:
            case 7:
                hardness = 2.5F;
                break;
            case 1:
            case 2:
            case 5:
                hardness = 3.25F;
                break;
            case 3:
                hardness = 3.0F;
                break;
            case 4:
                hardness = 4.25F;
                break;
            default:
                hardness = 2.0F;
                break;
            }
        }
        else if (this.category == SlabCategory.WOOD2)
        {
            switch (meta & 7)
            {
            case 0:
                hardness = 1.75F;
                break;
            case 1:
            case 2:
                hardness = 2.25F;
                break;
            case 3:
                hardness = 3.25F;
                break;
            case 4:
                hardness = 4.5F;
                break;
            }
        }
        return hardness;
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return super.getBlockHardness(world, x, y, z);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        if (block == MPBlocks.stone_slab_full)
        {
            return new ItemStack(MPBlocks.stone_slab_half, 1, meta);
        }
        if (block == MPBlocks.stone_slab_full2)
        {
            return new ItemStack(MPBlocks.stone_slab_half2, 1, meta);
        }
        if (block == MPBlocks.wooden_slab_full)
        {
            return new ItemStack(MPBlocks.wooden_slab_half, 1, meta);
        }
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
        meta = this.getTypeFromMeta(meta) + this.category.ordinal() * 8;

        if (meta < BlockSlabMP.woodTypes.length)
        {
            return meta;
        }
        return 0;
    }

    private int getTypeFromMeta(int meta)
    {
        return meta & 7;
    }
}