package com.stevekung.moreplanets.planets.diona.blocks;

import java.util.Random;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.utils.CompatibilityManagerMP;
import com.stevekung.moreplanets.utils.blocks.BlockBaseMP;
import com.stevekung.moreplanets.utils.blocks.EnumSortCategoryBlock;
import com.stevekung.moreplanets.utils.itemblocks.IItemRarity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlienMinerBlood extends BlockBaseMP
{
    public BlockAlienMinerBlood(String name)
    {
        super(Material.ROCK);
        this.setTickRandomly(true);
        this.setHardness(1.25F);
        this.setTranslationKey(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return CompatibilityManagerMP.isCTMLoaded ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (rand.nextInt(200) == 0)
        {
            world.setBlockState(pos, MPBlocks.DIONA_SURFACE_ROCK.getDefaultState());
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MPBlocks.DIONA_SURFACE_ROCK);
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return false;
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return ColorUtils.stringToRGB(IItemRarity.ALIEN);
    }
}