package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockFenceGateMP extends BlockFenceGate implements ISortableBlock, ISingleBlockRender
{
    private String name;

    public BlockFenceGateMP(String name)
    {
        super(BlockPlanks.EnumType.OAK);
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.name = name;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.BLOCK_TAB;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.FENCE_GATE;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}