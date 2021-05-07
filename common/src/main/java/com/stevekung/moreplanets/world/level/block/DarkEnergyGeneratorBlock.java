package com.stevekung.moreplanets.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DarkEnergyGeneratorBlock extends Block
{
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    private static final VoxelShape SHAPE = Shapes.or(Block.box(1, 0.0D, 1, 15, 4, 15), Block.box(2, 4, 2, 14, 6, 14), Block.box(5, 6, 5, 11, 7, 11), Block.box(3, 0, 10, 0, 7, 6), Block.box(13, 0, 10, 16, 7, 6), Block.box(10, 0, 3, 6, 7, 0), Block.box(10, 0, 13, 6, 7, 16));

    public DarkEnergyGeneratorBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext)
    {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(ACTIVE);
    }

    public static int getLightLevel(BlockState state)
    {
        return state.getValue(ACTIVE) ? 6 : 0;
    }
}