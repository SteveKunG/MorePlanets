package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;

public class BlockStairsMP extends BlockStairs implements MorePlanetsBlock
{
    private MPBlockCategory category;
    private final String name;

    public BlockStairsMP(String name, EnumStairsType type)
    {
        super(type.getParent().getDefaultState());
        this.setTranslationKey(name);
        this.setHardness(type.getHardness());

        if (type == EnumStairsType.WOODEN)
        {
            this.setSoundType(SoundType.WOOD);
        }
        else if (type == EnumStairsType.ALIEN_SHIP)
        {
            this.setSoundType(SoundType.METAL);
        }
        else if (type == EnumStairsType.DUNGEON_BRICK)
        {
            this.setResistance(40.0F);
        }
        this.name = name;
        this.useNeighborBrightness = true;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return this.category == null ? MPBlockCategory.STAIRS_STONE : this.category;
    }

    public BlockStairsMP setSortCategory(MPBlockCategory category)
    {
        this.category = category;
        return this;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }

    public enum EnumStairsType
    {
        COBBLESTONE(Blocks.STONE, 2.0F),
        STONE_BRICK(Blocks.STONE, 1.5F),
        DUNGEON_BRICK(Blocks.STONE, 4.0F),
        SANDSTONE(Blocks.STONE, 0.8F),
        WOODEN(Blocks.PLANKS, 2.0F),
        ALIEN_SHIP(Blocks.IRON_BLOCK, 4.0F);

        private final float hardness;
        private final Block parent;

        EnumStairsType(Block parent, float hardness)
        {
            this.hardness = hardness;
            this.parent = parent;
        }

        public float getHardness()
        {
            return this.hardness;
        }

        public Block getParent()
        {
            return this.parent;
        }
    }
}