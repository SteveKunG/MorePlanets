/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenTreeMP;
import stevekung.mods.moreplanets.planets.fronos.worldgen.tree.WorldGenCoconutTree;

public class BlockFronosSapling extends BlockSapling
{
    private static String[] saplings = new String[] {
            "coconut",
            "red_maple",
            "yellow_maple",
            "purple_maple"
    };

    private IIcon[] textures;

    public BlockFronosSapling(String name)
    {
        super();
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[BlockFronosSapling.saplings.length];

        for (int i = 0; i < BlockFronosSapling.saplings.length; ++i)
        {
            this.textures[i] = iconRegister.registerIcon("fronos:sapling_" + BlockFronosSapling.saplings[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= BlockFronosSapling.saplings.length)
        {
            meta = 0;
        }
        return this.textures[meta];
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockFronosSapling.saplings.length; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        return this.isValidPosition(world, x, y, z, -1);
    }

    public boolean isValidPosition(World world, int x, int y, int z, int metadata)
    {
        Block block = world.getBlock(x, y - 1, z);

        switch (metadata)
        {
        default:
            return block == Blocks.grass || block == Blocks.dirt || block instanceof IFronosGrass || block == FronosBlocks.fronos_dirt || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
        }
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block soil = par1World.getBlock(par2, par3 - 1, par4);
        return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0)
            {
                this.func_149878_d(world, x, y, z, random);
            }
        }
    }

    @Override
    public void func_149878_d(World world, int x, int y, int z, Random random)
    {
        int meta = world.getBlockMetadata(x, y, z);
        Object obj = null;

        if (obj == null)
        {
            switch (meta)
            {
            // Coconut
            case 0:
                obj = new WorldGenCoconutTree(6 + random.nextInt(8));
                break;
                // Red Maple
            case 1:
                obj = new WorldGenTreeMP(FronosBlocks.fronos_log, FronosBlocks.fronos_leaves, 1, 0, this, world.getBlock(x, y - 1, z) instanceof IFronosGrass, FronosBlocks.fronos_dirt);
                break;
                // Yellow Maple
            case 2:
                obj = new WorldGenTreeMP(FronosBlocks.fronos_log, FronosBlocks.fronos_leaves, 1, 1, this, world.getBlock(x, y - 1, z) instanceof IFronosGrass, FronosBlocks.fronos_dirt);
                break;
                // Purple Maple
            case 3:
                obj = new WorldGenTreeMP(FronosBlocks.fronos_log, FronosBlocks.fronos_leaves, 1, 2, this, world.getBlock(x, y - 1, z) instanceof IFronosGrass, FronosBlocks.fronos_dirt);
                break;
            }
        }

        if (obj != null)
        {
            world.setBlockToAir(x, y, z);

            if (!((WorldGenerator)obj).generate(world, random, x, y, z))
            {
                world.setBlock(x, y, z, this, meta, 2);
            }
        }
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
}