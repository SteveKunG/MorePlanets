package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class BlockFallingMP extends BlockFalling implements ISortableBlock, ISingleBlockRender
{
    private String name;

    public BlockFallingMP() {}

    public BlockFallingMP(String name)
    {
        this.setUnlocalizedName(name);
        this.name = name;
    }

    @Override
    public BlockFallingMP setSoundType(SoundType sound)
    {
        this.blockSoundType = sound;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.BLOCK_TAB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state)
    {
        return state.getBlock() == NibiruBlocks.INFECTED_SAND ? ColorHelper.rgbToDecimal(141, 54, 32) : super.getDustColor(state);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.BUILDING_BLOCK;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}