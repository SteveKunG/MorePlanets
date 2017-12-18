/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.moons.europa.world.gen.WorldGenEuropaTree;
import stevekung.mods.moreplanets.moons.europa.world.gen.WorldGenEuropaTree1;

public class BlockEuropaSapling extends BlockBush implements IGrowable
{
    private IIcon textures;
    private IIcon flipSapling;

    public BlockEuropaSapling(String name)
    {
        super(Material.water);
        this.setBlockBounds(0.099999994F, 0.0F, 0.099999994F, 0.9F, 0.8F, 0.9F);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.setBlockName(name);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 0)
        {
            this.setBlockBounds(0.099999994F, 0.0F, 0.099999994F, 0.9F, 0.8F, 0.9F);
        }
        else if (meta == 1)
        {
            this.setBlockBounds(0.099999994F, 0.2F, 0.099999994F, 0.9F, 1.0F, 0.9F);
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int silkTouch)
    {
        world.setBlock(x, y, z, EuropaBlocks.europa_water);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.flipSapling = iconRegister.registerIcon("europa:europa_sapling_flip");
        this.textures = iconRegister.registerIcon("europa:sapling_europa");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 1)
        {
            return this.flipSapling;
        }
        return this.textures;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    protected void checkAndDropBlock(World world, int x, int y, int z)
    {
        if (!this.canBlockStay(world, x, y, z))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        if (side == 0)
        {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
        if (side == 0)
        {
            return (world.getBlock(x, y + 1, z) == EuropaBlocks.europa_ice || world.getBlock(x, y + 1, z) == EuropaBlocks.packed_europa_ice) && world.getBlock(x, y - 1, z) == EuropaBlocks.europa_water;
        }
        return this.canBlockStay(world, x, y, z);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y - 1, z) == EuropaBlocks.europa_ice_slush && world.getBlock(x, y + 1, z) == EuropaBlocks.europa_water)
        {
            return true;
        }
        else if (world.getBlockMetadata(x, y, z) == 1)
        {
            if ((world.getBlock(x, y + 1, z) == EuropaBlocks.europa_ice || world.getBlock(x, y + 1, z) == EuropaBlocks.packed_europa_ice) && world.getBlock(x, y - 1, z) == EuropaBlocks.europa_water)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y - 1, z) == EuropaBlocks.europa_ice_slush && world.getBlock(x, y + 1, z) == EuropaBlocks.europa_water)
        {
            return true;
        }
        else if (world.getBlockMetadata(x, y, z) == 1)
        {
            if ((world.getBlock(x, y + 1, z) == EuropaBlocks.europa_ice || world.getBlock(x, y + 1, z) == EuropaBlocks.packed_europa_ice) && world.getBlock(x, y - 1, z) == EuropaBlocks.europa_water)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        this.checkAndDropBlock(world, x, y, z);

        if (world.isRemote)
        {
            return;
        }
        if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0)
        {
            this.func_149853_b(world, random, x, y, z);
        }
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        Object obj = null;

        if (obj == null)
        {
            switch (meta)
            {
            case 0:
                obj = new WorldGenEuropaTree();
                break;
            case 1:
                obj = new WorldGenEuropaTree1();
                break;
            }
        }
        if (obj != null)
        {
            world.setBlockToAir(x, y, z);

            if (!((WorldGenerator)obj).generate(world, rand, x, y, z))
            {
                world.setBlock(x, y, z, this, meta, 2);
            }
        }
    }

    @Override
    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    @Override
    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return p_149852_1_.rand.nextFloat() < 0.45D;
    }
}