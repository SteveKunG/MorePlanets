package com.stevekung.moreplanets.utils.blocks;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.utils.EnumParticleTypesMP;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLeavesMP extends BlockLeaves implements MorePlanetsBlock
{
    private final BlockType type;
    private final String name;

    public BlockLeavesMP(String name, BlockType type)
    {
        this.setTranslationKey(name);
        this.name = name;
        this.type = type;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (world.isRainingAt(pos.up()) && !world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && rand.nextInt(15) == 1)
        {
            double d0 = pos.getX() + rand.nextFloat();
            double d1 = pos.getY() - 0.05D;
            double d2 = pos.getZ() + rand.nextFloat();
            Block block = state.getBlock();

            if (block == MPBlocks.INFECTED_OAK_LEAVES || block == MPBlocks.INFECTED_JUNGLE_LEAVES || block == MPBlocks.INFECTED_SPRUCE_LEAVES)
            {
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.INFECTED_WATER_DRIP, d0, d1, d2);
            }
            else if (block == MPBlocks.ALIEN_BERRY_OAK_LEAVES)
            {
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.PURIFY_WATER_DRIP, d0, d1, d2);
            }
            else
            {
                world.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
        if (this.type == BlockType.ALIEN_BERRY_OAK_LEAVES)
        {
            if (world.isAirBlock(pos.down()) && rand.nextInt(10) == 0)
            {
                double d0 = pos.getX() + rand.nextFloat();
                double d1 = pos.getY() - 0.05D;
                double d2 = pos.getZ() + rand.nextFloat();
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.ALIEN_BERRY_LEAVES_SPARK, d0, d1, d2);
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (this.type == BlockType.INFECTED_OAK_LEAVES)
        {
            return Item.getItemFromBlock(MPBlocks.INFECTED_OAK_SAPLING);
        }
        else if (this.type == BlockType.INFECTED_JUNGLE_LEAVES)
        {
            return Item.getItemFromBlock(MPBlocks.INFECTED_JUNGLE_SAPLING);
        }
        else if (this.type == BlockType.INFECTED_SPRUCE_LEAVES)
        {
            return Item.getItemFromBlock(MPBlocks.INFECTED_SPRUCE_SAPLING);
        }
        else if (this.type == BlockType.ALIEN_BERRY_OAK_LEAVES)
        {
            return Item.getItemFromBlock(MPBlocks.ALIEN_BERRY_OAK_SAPLING);
        }
        else if (this.type == BlockType.OSCALEA_LEAVES)
        {
            return Item.getItemFromBlock(MPBlocks.OSCALEA_SAPLING);
        }
        else if (this.type == BlockType.FROLIA_LEAVES)
        {
            return Item.getItemFromBlock(MPBlocks.FROLIA_SAPLING);
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!state.getValue(DECAYABLE))
        {
            i |= 4;
        }
        if (state.getValue(CHECK_DECAY))
        {
            i |= 8;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(new ItemStack(this));
        return ret;
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.DECORATION_BLOCK;
    }

    @Override
    protected void dropApple(World world, BlockPos pos, IBlockState state, int chance)
    {
        if (world.rand.nextInt(chance) == 0)
        {
            if (this.type == BlockType.INFECTED_OAK_LEAVES)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(MPItems.INFECTED_APPLE));
            }
            else if (this.type == BlockType.ALIEN_BERRY_OAK_LEAVES)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(MPItems.ALIEN_BERRY));
            }
            else if (this.type == BlockType.FROLIA_LEAVES)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(MPItems.BLUE_PEAR));
            }
        }
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta)
    {
        return BlockPlanks.EnumType.OAK;
    }

    public enum BlockType
    {
        INFECTED_OAK_LEAVES,
        INFECTED_SPRUCE_LEAVES,
        INFECTED_JUNGLE_LEAVES,
        ALIEN_BERRY_OAK_LEAVES,
        OSCALEA_LEAVES,
        FROLIA_LEAVES;

        @Override
        public String toString()
        {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}