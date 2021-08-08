package com.stevekung.moreplanets.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class CompactedBlock extends Block
{
    public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);

    public CompactedBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, Type.NONE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.getCompactedState(context.getLevel(), context.getClickedPos());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2)
    {
        return this.getCompactedState(levelAccessor, blockPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(TYPE);
    }

    private BlockState getCompactedState(LevelAccessor level, BlockPos pos)
    {
        var stateTop = level.getBlockState(pos.above());
        var stateBottom = level.getBlockState(pos.below());
        return this.defaultBlockState().setValue(TYPE, stateTop.getBlock() == this && stateBottom.getBlock() == this ? Type.MIDDLE : stateTop.getBlock() == this ? Type.TOP : stateBottom.getBlock() == this ? Type.BOTTOM : Type.NONE);
    }

    public enum Type implements StringRepresentable
    {
        NONE("none"),
        TOP("top"),
        MIDDLE("middle"),
        BOTTOM("bottom");

        private final String name;

        Type(String name)
        {
            this.name = name;
        }

        @Override
        public String toString()
        {
            return this.name;
        }

        @Override
        public String getSerializedName()
        {
            return this.name;
        }
    }
}