/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockFlowerMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockGlassGemCorn2 extends BlockFlowerMP
{
    private IIcon[] textures;

    public BlockGlassGemCorn2(String name)
    {
        super(Material.plants);
        this.setStepSound(Block.soundTypeGrass);
        this.setTickRandomly(true);
        float f = 0.4F;
        this.setBlockName(name);
        this.setHardness(0.2F);
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[4];
        this.textures[0] = iconRegister.registerIcon("fronos:glass_gem_corn_0");
        this.textures[1] = iconRegister.registerIcon("fronos:glass_gem_corn_1");
        this.textures[2] = iconRegister.registerIcon("fronos:glass_gem_corn_2");
        this.textures[3] = iconRegister.registerIcon("fronos:glass_gem_corn_3");
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);
        int meta = world.getBlockMetadata(x, y, z);

        if (world.isDaytime() || world.getBlockLightValue(x, y + 1, z) >= 9)
        {
            if (meta == 0)
            {
                if (!World.doesBlockHaveSolidTopSurface(world, x, y + 1, z))
                {
                    if (rand.nextInt(5) == 0)
                    {
                        world.setBlockMetadataWithNotify(x, y, z, 1, 3);
                        world.setBlock(x, y + 1, z, FronosBlocks.glass_gem_corn3, 0, 3);
                    }
                }
            }
            if (meta == 1)
            {
                if (rand.nextInt(5) == 0)
                {
                    world.setBlockMetadataWithNotify(x, y, z, 2, 3);
                }
            }
            if (meta == 2)
            {
                if (rand.nextInt(5) == 0)
                {
                    world.setBlockMetadataWithNotify(x, y, z, 3, 3);
                }
            }
        }
    }

    @Override
    public Item getItem(World par1World, int x, int y, int z)
    {
        return FronosItems.glass_gem_corn;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return null;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.textures[meta];
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return FronosItems.glass_gem_corn;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y - 1, z);

        if (world.getBlockMetadata(x, y, z) >= 1)
        {
            return block == FronosBlocks.glass_gem_corn1;
        }
        return block == FronosBlocks.glass_gem_corn1;
    }

    @Override
    public boolean isValidPosition(World world, int x, int y, int z, int meta)
    {
        Block block = world.getBlock(x, y - 1, z);

        if (world.getBlockMetadata(x, y, z) >= 1)
        {
            return block == FronosBlocks.glass_gem_corn1;
        }
        return block == FronosBlocks.glass_gem_corn1;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        return this.isValidPosition(world, x, y, z, -1);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        super.onNeighborBlockChange(world, x, y, z, block);
        this.canBlockStay(world, x, y, z);
        Block getBlock = world.getBlock(x, y + 1, z);
        int meta = world.getBlockMetadata(x, y, z);

        if (meta >= 1 && getBlock != FronosBlocks.glass_gem_corn3)
        {
            if (world.getBlock(x, y + 1, z) != FronosBlocks.glass_gem_corn3)
            {
                world.setBlockMetadataWithNotify(x, y, z, 0, 2);
            }
        }
    }

    @Override
    public int damageDropped(int meta)
    {
        return 0;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random rand)
    {
        if (meta == 3)
        {
            return 3 + rand.nextInt(4);
        }
        return 1;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return 0;
    }
}