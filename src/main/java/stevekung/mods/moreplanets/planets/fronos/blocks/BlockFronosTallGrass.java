/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.ArrayList;
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
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockFlowerMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockFronosTallGrass extends BlockFlowerMP implements IShearable
{
    private static String[] fronosGrass = new String[] {
            "fronos_short_grass",//0
            "fronos_medium_grass",//1
            "fronos_tall_grass",//2
            "pink_short_grass",//3
            "pink_medium_grass",//4
            "pink_tall_grass",//5
            "purple_short_grass",//6
            "purple_medium_grass",//7
            "purple_tall_grass",//8
            "plains_short_grass",//9
            "plains_medium_grass",//10
            "plains_tall_grass",//11
            "golden_short_grass",//12
            "golden_medium_grass",//13
            "golden_tall_grass"//14
    };

    private IIcon[] textures;

    public BlockFronosTallGrass(String name)
    {
        super(Material.plants);
        this.setTickRandomly(true);
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[BlockFronosTallGrass.fronosGrass.length];

        for (int i = 0; i < BlockFronosTallGrass.fronosGrass.length; ++i)
        {
            this.textures[i] = iconRegister.registerIcon("fronos:" + BlockFronosTallGrass.fronosGrass[i]);
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
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) >= 12)
        {
            return 10;
        }
        return 0;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        switch (meta)
        {
        case 0:
        case 3:
        case 6:
        case 9:
        case 12:
            this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.5F, 0.9F);
            break;
        default:
            this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
            break;
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ItemStack item = ForgeHooks.getGrassSeed(world);

        if (meta >= 12)
        {
            if (world.rand.nextInt(15) == 0)
            {
                ret.add(new ItemStack(FronosItems.golden_seeds, 1));
            }
        }
        else
        {
            if (world.rand.nextInt(8) != 0)
            {
                return ret;
            }
            if (item != null)
            {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockFronosTallGrass.fronosGrass.length; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        this.isValidPosition(world, x, y, z, world.getBlockMetadata(x, y, z));
    }

    @Override
    public boolean isValidPosition(World world, int x, int y, int z, int meta)
    {
        Block block = world.getBlock(x, y - 1, z);

        if (meta >= 0 && meta <= 2)
        {
            return block == FronosBlocks.fronos_grass || block == FronosBlocks.fronos_dirt;
        }
        else if (meta >= 3 && meta <= 5)
        {
            return block == FronosBlocks.pink_grass || block == FronosBlocks.fronos_dirt;
        }
        else if (meta >= 5 && meta <= 8)
        {
            return block == FronosBlocks.purple_grass || block == FronosBlocks.fronos_dirt;
        }
        else if (meta >= 8 && meta <= 11)
        {
            return block == FronosBlocks.plains_grass || block == FronosBlocks.fronos_dirt;
        }
        else if (meta >= 12 || meta <= 14)
        {
            return block == FronosBlocks.golden_grass || block == FronosBlocks.fronos_dirt;
        }
        return false;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        super.randomDisplayTick(world, x, y, z, rand);
        int meta = world.getBlockMetadata(x, y, z);

        if (meta >= 12)
        {
            if (rand.nextInt(1) == 0)
            {
                MorePlanetsCore.proxy.spawnParticle("goldSmoke", x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat());
            }
        }
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> itemStack = new ArrayList<ItemStack>();
        itemStack.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return itemStack;
    }
}