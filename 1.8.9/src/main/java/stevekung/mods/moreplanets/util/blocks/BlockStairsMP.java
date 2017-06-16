package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

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
            this.setStepSound(soundTypeWood);
        }
        if (type == EnumStairsType.DUNGEON_BRICK)
        {
            this.setResistance(40.0F);
        }
        this.name = name;
        this.useNeighborBrightness = true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.BLOCK_TAB;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
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

    @Override
    public Block getBlock()
    {
        return this;
    }

    public enum EnumStairsType
    {
        COBBLESTONE(Blocks.stone, 2.0F),
        STONE_BRICK(Blocks.stone, 1.5F),
        DUNGEON_BRICK(Blocks.stone, 4.0F),
        SANDSTONE(Blocks.stone, 0.8F),
        WOODEN(Blocks.planks, 2.0F);

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