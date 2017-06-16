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
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockCoralMP;

public class BlockFronosCoral extends BlockCoralMP
{
    private static String[] coral = new String[] {
            "0",
            "0",
            "0",
            "0",
            "tomato_bush",
            "glowing_pink_coral",
            "colunus_coral"
    };
    private IIcon[] textures;

    public BlockFronosCoral(String name)
    {
        super(Material.water);
        this.setStepSound(Block.soundTypeGrass);
        this.setTickRandomly(true);
        this.setBlockName(name);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return meta;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[BlockFronosCoral.coral.length];

        for (int i = 0; i < BlockFronosCoral.coral.length; ++i)
        {
            if (!(i >= 0) && !(i <= 4))
            {
                return;
            }
        }
        this.textures[4] = iconRegister.registerIcon("fronos:" + BlockFronosCoral.coral[4]);
        this.textures[5] = iconRegister.registerIcon("fronos:" + BlockFronosCoral.coral[5]);
        this.textures[6] = iconRegister.registerIcon("fronos:" + BlockFronosCoral.coral[6]);
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
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockFronosCoral.coral.length; ++i)
        {
            if (i > 4)
            {
                list.add(new ItemStack(block, 1, i));
            }
        }
    }

    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
        return world.getBlock(x, y + 1, z) == Blocks.water && this.canBlockStay(world, x, y, z, itemStack.getItemDamage());
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z, int metadata)
    {
        Block block = world.getBlock(x, y - 1, z);
        return block == Blocks.dirt || block == Blocks.sand || block == Blocks.sponge || block == Blocks.stone || block == Blocks.clay || block == Blocks.gravel || block == Blocks.grass || block == FronosBlocks.fronos_dirt || block == FronosBlocks.fronos_sand || block == FronosBlocks.fronos_block;
    }

    @Override
    public int damageDropped(int meta)
    {
        if (meta <= 4)
        {
            return 5;
        }
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return 1;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 5)
        {
            return 10;
        }
        return 0;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta <= 4)
        {
            return 5;
        }
        return meta;
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int silkTouch)
    {
        world.setBlock(x, y, z, Blocks.flowing_water);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
}