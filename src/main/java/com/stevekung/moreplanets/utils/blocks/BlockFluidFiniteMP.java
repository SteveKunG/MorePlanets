package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.utils.blocks.fluid.LiquidUtils;
import com.stevekung.moreplanets.utils.ModelNameGatherer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public abstract class BlockFluidFiniteMP extends BlockFluidFinite implements ModelNameGatherer
{
    private String name;

    public BlockFluidFiniteMP(Fluid fluid, Material material)
    {
        super(fluid, material);
    }

    @Override
    public Block setTranslationKey(String name)
    {
        this.name = name;
        return super.setTranslationKey(name);
    }

    @Override
    @Nullable
    public Boolean isEntityInsideMaterial(IBlockAccess world, BlockPos pos, IBlockState state, Entity entity, double yToTest, Material material, boolean testingHead)
    {
        if (!(entity instanceof EntityPlayer))
        {
            return null;
        }
        return LiquidUtils.checkInsideBlock((EntityPlayer) entity, this) && testingHead;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        IBlockState iblockstate = world.getBlockState(pos.offset(facing));
        Block block = iblockstate.getBlock();

        if (block == this)
        {
            return false;
        }
        if (block instanceof BlockFluidBaseMP || block instanceof BlockFluidFiniteMP)
        {
            return true;
        }
        return super.shouldSideBeRendered(state, world, pos, facing);
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }
}