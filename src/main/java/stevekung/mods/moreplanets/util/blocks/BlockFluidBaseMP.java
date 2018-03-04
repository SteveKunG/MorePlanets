package stevekung.mods.moreplanets.util.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
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

    //TODO: Remove in 1.11+
    @Override
    @Deprecated
    @Nonnull
    public IBlockState getExtendedState(@Nonnull IBlockState oldState, @Nonnull IBlockAccess world, @Nonnull BlockPos pos)
    {
        IExtendedBlockState state = (IExtendedBlockState)oldState;
        state = state.withProperty(FLOW_DIRECTION, (float)getFlowDirection(world, pos));
        IBlockState[][] upBlockState = new IBlockState[3][3];
        float[][] height = new float[3][3];
        float[][] corner = new float[2][2];
        upBlockState[1][1] = world.getBlockState(pos.down(this.densityDir));
        height[1][1] = this.getFluidHeightForRender(world, pos, upBlockState[1][1]);

        if (height[1][1] == 1)
        {
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    corner[i][j] = 1;
                }
            }
        }
        else
        {
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (i != 1 || j != 1)
                    {
                        upBlockState[i][j] = world.getBlockState(pos.add(i - 1, 0, j - 1).down(this.densityDir));
                        height[i][j] = this.getFluidHeightForRender(world, pos.add(i - 1, 0, j - 1), upBlockState[i][j]);
                    }
                }
            }
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    corner[i][j] = this.getFluidHeightAverage(height[i][j], height[i][j + 1], height[i + 1][j], height[i + 1][j + 1]);
                }
            }
            //check for downflow above corners
            boolean n =  this.isFluid(upBlockState[0][1]);
            boolean s =  this.isFluid(upBlockState[2][1]);
            boolean w =  this.isFluid(upBlockState[1][0]);
            boolean e =  this.isFluid(upBlockState[1][2]);
            boolean nw = this.isFluid(upBlockState[0][0]);
            boolean ne = this.isFluid(upBlockState[0][2]);
            boolean sw = this.isFluid(upBlockState[2][0]);
            boolean se = this.isFluid(upBlockState[2][2]);

            if (nw || n || w)
            {
                corner[0][0] = 1;
            }
            if (ne || n || e)
            {
                corner[0][1] = 1;
            }
            if (sw || s || w)
            {
                corner[1][0] = 1;
            }
            if (se || s || e)
            {
                corner[1][1] = 1;
            }
        }
        state = state.withProperty(LEVEL_CORNERS[0], corner[0][0]);
        state = state.withProperty(LEVEL_CORNERS[1], corner[0][1]);
        state = state.withProperty(LEVEL_CORNERS[2], corner[1][1]);
        state = state.withProperty(LEVEL_CORNERS[3], corner[1][0]);
        return state;
    }

    private boolean isFluid(@Nonnull IBlockState state)
    {
        return state.getMaterial().isLiquid() || state.getBlock() instanceof IFluidBlock;
    }

    private float getFluidHeightForRender(IBlockAccess world, BlockPos pos, @Nonnull IBlockState up)
    {
        IBlockState here = world.getBlockState(pos);

        if (here.getBlock() == this)
        {
            if (up.getMaterial().isLiquid() || up.getBlock() instanceof IFluidBlock)
            {
                return 1;
            }
            if (this.getMetaFromState(here) == this.getMaxRenderHeightMeta())
            {
                return 0.875F;
            }
        }
        if (here.getBlock() instanceof BlockLiquid)
        {
            return Math.min(1 - BlockLiquid.getLiquidHeightPercent(here.getValue(BlockLiquid.LEVEL)), 14f / 16);
        }
        return !here.getMaterial().isSolid() && up.getBlock() == this ? 1 : this.getQuantaPercentage(world, pos) * 0.875F;
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