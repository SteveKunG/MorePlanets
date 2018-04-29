package stevekung.mods.moreplanets.utils.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockFallingMP extends BlockFalling implements ISortableBlock, ISingleBlockRender
{
    private String name;

    public BlockFallingMP() {}

    public BlockFallingMP(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public Block setUnlocalizedName(String name)
    {
        this.name = name;
        return super.setUnlocalizedName(name);
    }

    @Override
    public BlockFallingMP setSoundType(SoundType sound)
    {
        this.blockSoundType = sound;
        return this;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state)
    {
        return state.getBlock() == NibiruBlocks.INFECTED_SAND ? ColorUtils.rgbToDecimal(141, 54, 32) : super.getDustColor(state);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.BUILDING_BLOCK;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}