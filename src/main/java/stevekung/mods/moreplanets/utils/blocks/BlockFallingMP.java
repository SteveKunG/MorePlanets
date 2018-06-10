package stevekung.mods.moreplanets.utils.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockFallingMP extends BlockFalling implements ISortableBlock, IItemModelRender
{
    private String name;

    public BlockFallingMP() {}

    public BlockFallingMP(String name)
    {
        this.setUnlocalizedName(name);
    }

    public BlockFallingMP(String name, Material material)
    {
        super(material);
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
        return state.getBlock() == MPBlocks.INFECTED_SAND ? ColorUtils.rgbToDecimal(141, 54, 32) : super.getDustColor(state);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing facing, IPlantable plantable)
    {
        IBlockState plantState = plantable.getPlant(world, pos.offset(facing));

        if (this == MPBlocks.INFECTED_SAND && plantState.getBlock() == Blocks.DEADBUSH)
        {
            return true;
        }
        return false;
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