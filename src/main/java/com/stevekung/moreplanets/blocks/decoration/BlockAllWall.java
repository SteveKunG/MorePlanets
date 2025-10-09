package com.stevekung.moreplanets.blocks.decoration;

import java.util.Locale;

import com.stevekung.moreplanets.utils.blocks.BlockWallMP;
import com.stevekung.moreplanets.utils.blocks.MPBlockCategory;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class BlockAllWall extends BlockWallMP
{
    public BlockAllWall(String name, BlockType type)
    {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
        this.setTranslationKey(name);

        if (type.isDungeonBrick())
        {
            this.setHardness(2.0F);
        }
        else
        {
            this.setHardness(4.0F);
            this.setResistance(40.0F);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, UP, NORTH, EAST, WEST, SOUTH);
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.WALL;
    }

    public enum BlockType
    {
        DIONA_COBBLESTONE_WALL,
        CHALOS_COBBLESTONE_WALL,
        NIBIRU_COBBLESTONE_WALL,
        DIONA_DUNGEON_BRICK_WALL(true),
        CHALOS_DUNGEON_BRICK_WALL(true),
        NIBIRU_DUNGEON_BRICK_WALL(true);

        private boolean isDungeonBrick;

        BlockType() {}

        BlockType(boolean isDungeonBrick)
        {
            this.isDungeonBrick = isDungeonBrick;
        }

        @Override
        public String toString()
        {
            return this.name().toLowerCase(Locale.ROOT);
        }

        public boolean isDungeonBrick()
        {
            return this.isDungeonBrick;
        }
    }
}