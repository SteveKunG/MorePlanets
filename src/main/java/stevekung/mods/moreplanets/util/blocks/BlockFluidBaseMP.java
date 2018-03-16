package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;

public abstract class BlockFluidBaseMP extends BlockFluidClassic implements ISingleBlockRender
{
    public BlockFluidBaseMP(Fluid fluid)
    {
        super(fluid, Material.WATER);
        this.setHardness(100.0F);
    }

    public BlockFluidBaseMP(Fluid fluid, Material material)
    {
        super(fluid, material);
        this.setHardness(100.0F);
    }

    @Override
    public boolean isPassable(IBlockAccess world, BlockPos pos)
    {
        return this.blockMaterial != Material.LAVA;
    }

    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos pos)
    {
        if (world.getBlockState(pos).getMaterial().isLiquid())
        {
            return false;
        }
        if (world.getBlockState(pos).getMaterial() == Material.LAVA)
        {
            return false;
        }
        return super.canDisplace(world, pos);
    }

    @Override
    public boolean displaceIfPossible(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getMaterial().isLiquid())
        {
            return false;
        }
        if (world.getBlockState(pos).getMaterial() == Material.LAVA)
        {
            return false;
        }
        return super.displaceIfPossible(world, pos);
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
        if (block != this && block instanceof BlockFluidBaseMP)
        {
            return true;
        }
        return super.shouldSideBeRendered(state, world, pos, facing);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }

    protected void triggerMixEffects(World world, BlockPos pos)
    {
        world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

        for (int i = 0; i < 8; ++i)
        {
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.MC_SMOKE_LARGE, pos.getX() + Math.random(), pos.getY() + 1.2D, pos.getZ() + Math.random());
        }
    }
}