package com.stevekung.moreplanets.world.level.block;

import org.jetbrains.annotations.Nullable;
import com.stevekung.moreplanets.world.level.block.entity.DarkEnergyCoreBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DarkEnergyCoreBlock extends HalfTransparentBlock implements SimpleWaterloggedBlock, EntityBlock
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<State> STATE = EnumProperty.create("state", State.class);
    private static final VoxelShape SHAPE = Shapes.or(Block.box(4, 0.0D, 4, 12, 1, 12), Block.box(3, 1, 3, 13, 3, 13), Block.box(2, 3, 2, 14, 5, 14), Block.box(1, 5, 1, 15, 7, 15), Block.box(2, 7, 2, 14, 8, 14), Block.box(5, 8, 5, 11, 9, 11));

    public DarkEnergyCoreBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(STATE, State.FULL));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext)
    {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
    {
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8).setValue(STATE, State.FULL);
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
    public FluidState getFluidState(BlockState blockState)
    {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(WATERLOGGED, STATE);
    }

    @Override
    public void fallOn(Level level, BlockPos blockPos, Entity entity, float fallDistance)
    {
        if (entity.isSuppressingBounce())
        {
            super.fallOn(level, blockPos, entity, fallDistance);
        }
        else
        {
            entity.causeFallDamage(fallDistance, 0.0F);
        }
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter blockGetter, Entity entity)
    {
        if (entity.isSuppressingBounce())
        {
            super.updateEntityAfterFallOn(blockGetter, entity);
        }
        else
        {
            this.bounceUp(entity);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, Entity entity)
    {
        double d = Math.abs(entity.getDeltaMovement().y);

        if (d < 0.1D && !entity.isSteppingCarefully())
        {
            double e = 0.4D + d * 0.2D;
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(e, 1.0D, e));
        }
        super.stepOn(level, blockPos, entity);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter)
    {
        return new DarkEnergyCoreBlockEntity();
    }

    private void bounceUp(Entity entity)
    {
        Vec3 vec3 = entity.getDeltaMovement();

        if (vec3.y < 0.0D)
        {
            double d = entity instanceof LivingEntity ? 1.0D : 0.8D;
            entity.setDeltaMovement(vec3.x, -vec3.y * d, vec3.z);
        }
    }

    public enum State implements StringRepresentable
    {
        PARTIAL("partial"),
        FULL("full");

        private final String name;

        State(String name)
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