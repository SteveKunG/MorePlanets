package com.stevekung.moreplanets.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MeteoricIronStabilizerBlock extends Block implements SimpleWaterloggedBlock
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    protected static final VoxelShape Y_AXIS_AABB = Block.box(5.5D, 0.0D, 5.5D, 10.5D, 16.0D, 10.5D);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(5.5D, 5.5D, 0.0D, 10.5D, 10.5D, 16.0D);
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D, 5.5D, 5.5D, 16.0D, 10.5D, 10.5D);

    public MeteoricIronStabilizerBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(WATERLOGGED, false));
    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation)
    {
        return switch (rotation)
                {
                    case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (blockState.getValue(AXIS))
                            {
                                case X -> blockState.setValue(AXIS, Direction.Axis.Z);
                                case Z -> blockState.setValue(AXIS, Direction.Axis.X);
                                default -> blockState;
                            };
                    default -> blockState;
                };
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext)
    {
        return switch (blockState.getValue(AXIS))
                {
                    default -> X_AXIS_AABB;
                    case Z -> Z_AXIS_AABB;
                    case Y -> Y_AXIS_AABB;
                };
    }

    @Override
    public FluidState getFluidState(BlockState blockState)
    {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2)
    {
        if (blockState.getValue(WATERLOGGED))
        {
            levelAccessor.getLiquidTicks().scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
    {
        var fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        return this.defaultBlockState().setValue(AXIS, blockPlaceContext.getClickedFace().getAxis()).setValue(WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
    {
        builder.add(AXIS, WATERLOGGED);
    }

    @Override
    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType)
    {
        return false;
    }
}