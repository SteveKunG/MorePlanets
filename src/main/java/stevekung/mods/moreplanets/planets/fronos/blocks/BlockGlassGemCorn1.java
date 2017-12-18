/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockFlowerMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockGlassGemCorn1 extends BlockFlowerMP
{
    private IIcon[] textures;

    public BlockGlassGemCorn1(String name)
    {
        super(Material.plants);
        this.setStepSound(Block.soundTypeGrass);
        this.setTickRandomly(true);
        float f = 0.4F;
        this.setHardness(0.2F);
        this.setBlockName(name);
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[2];
        this.textures[0] = iconRegister.registerIcon("fronos:glass_gem_corn_0");
        this.textures[1] = iconRegister.registerIcon("fronos:glass_gem_corn_1");
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        if (world.getBlock(x, y + 1, z) != FronosBlocks.glass_gem_corn2 || world.getBlock(x, y + 2, z) != FronosBlocks.glass_gem_corn3)
        {
            world.setBlockToAir(x, y, z);

            if (!player.capabilities.isCreativeMode)
            {
                this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            }
        }
        else if (world.getBlock(x, y + 1, z) == FronosBlocks.glass_gem_corn2 || world.getBlock(x, y + 2, z) == FronosBlocks.glass_gem_corn3)
        {
            if (!player.capabilities.isCreativeMode)
            {
                this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            }

            if (world.getBlock(x, y + 1, z) == FronosBlocks.glass_gem_corn2 && world.getBlockMetadata(x, y + 1, z) == 3)
            {
                this.dropBlockAsItem(world, x, y + 1, z, world.getBlockMetadata(x, y, z), 0);
                this.dropBlockAsItem(world, x, y + 1, z, world.getBlockMetadata(x, y, z), 0);

                if (world.rand.nextInt(8) == 0)
                {
                    this.dropBlockAsItem(world, x, y + 1, z, world.getBlockMetadata(x, y, z), 0);
                    this.dropBlockAsItem(world, x, y + 1, z, world.getBlockMetadata(x, y, z), 0);
                }
            }
            this.dropBlockAsItem(world, x, y + 1, z, world.getBlockMetadata(x, y, z), 0);
            this.dropBlockAsItem(world, x, y + 2, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y + 2, z);
            world.setBlockToAir(x, y + 1, z);
            world.setBlockToAir(x, y, z);
        }
        return false;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);

        if (world.isDaytime() || world.getBlockLightValue(x, y + 1, z) >= 9)
        {
            if (world.getBlockMetadata(x, y, z) == 0)
            {
                if (!World.doesBlockHaveSolidTopSurface(world, x, y + 1, z))
                {
                    if (rand.nextInt(5) == 0)
                    {
                        world.setBlock(x, y + 1, z, FronosBlocks.glass_gem_corn2, 0, 3);
                        world.setBlockMetadataWithNotify(x, y, z, 1, 3);
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, int x, int y, int z)
    {
        return new ItemStack(FronosItems.glass_gem_corn);
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
    public Item getItemDropped(int meta, Random random, int fortune)
    {
        return FronosItems.glass_gem_corn;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y - 1, z);
        int meta = world.getBlockMetadata(x, y, z);

        switch (meta)
        {
        case 0:
        case 1:
            return block == FronosBlocks.fronos_farmland;
        }
        return block == FronosBlocks.fronos_farmland;
    }

    @Override
    public boolean isValidPosition(World world, int x, int y, int z, int meta)
    {
        return world.getBlock(x, y - 1, z) == FronosBlocks.fronos_farmland;
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

        if (meta == 1 && getBlock != FronosBlocks.glass_gem_corn2)
        {
            if (world.getBlock(x, y + 1, z) != FronosBlocks.glass_gem_corn2)
            {
                world.setBlockMetadataWithNotify(x, y, z, 0, 2);
            }
        }
        if (meta == 0 || meta == 1 || meta == 2)
        {
            this.checkBlockCoordValid(world, x, y, z);
        }
    }

    protected void checkBlockCoordValid(World world, int x, int y, int z)
    {
        for (int i = 1; world.getBlock(x, y + i, z) == this; i++)
        {
            if (!this.canBlockStay(world, x, y + i, z))
            {
                this.dropBlockAsItem(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0);
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
        return 1;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return 0;
    }
}