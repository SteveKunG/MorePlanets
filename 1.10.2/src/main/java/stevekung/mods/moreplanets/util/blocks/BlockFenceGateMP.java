package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockFenceGateMP extends BlockFenceGate implements ISortableBlock, ISingleBlockRender
{
    private String name;

    public BlockFenceGateMP(String name)
    {
        super(BlockPlanks.EnumType.OAK);
        this.setUnlocalizedName(name);
        this.setStepSound(soundTypeWood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.name = name;
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
        return EnumSortCategoryBlock.FENCE_GATE;
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
}