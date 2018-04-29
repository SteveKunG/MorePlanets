package stevekung.mods.moreplanets.utils.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

public class BlockStairsMP extends BlockStairs implements ISortableBlock, ISingleBlockRender
{
    private EnumSortCategoryBlock category;
    private String name;

    public BlockStairsMP(String name, EnumStairsType type)
    {
        super(type.getParent().getDefaultState());
        this.setUnlocalizedName(name);
        this.setHardness(type.getHardness());

        if (type == EnumStairsType.WOODEN)
        {
            this.setSoundType(SoundType.WOOD);
        }
        if (type == EnumStairsType.DUNGEON_BRICK)
        {
            this.setResistance(40.0F);
        }
        this.useNeighborBrightness = true;
    }

    @Override
    public Block setUnlocalizedName(String name)
    {
        this.name = name;
        return super.setUnlocalizedName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return this.category == null ? EnumSortCategoryBlock.STAIRS_STONE : this.category;
    }

    public BlockStairsMP setSortCategory(EnumSortCategoryBlock category)
    {
        this.category = category;
        return this;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    public enum EnumStairsType
    {
        COBBLESTONE(Blocks.STONE, 2.0F),
        STONE_BRICK(Blocks.STONE, 1.5F),
        DUNGEON_BRICK(Blocks.STONE, 4.0F),
        SANDSTONE(Blocks.STONE, 0.8F),
        WOODEN(Blocks.PLANKS, 2.0F);

        private float hardness;
        private Block parent;

        private EnumStairsType(Block parent, float hardness)
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