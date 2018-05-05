package stevekung.mods.moreplanets.planets.nibiru.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.blocks.BlockBreakableMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockMultalicCrystalBlock extends BlockBreakableMP
{
    public BlockMultalicCrystalBlock(String name)
    {
        super(Material.GLASS);
        this.setHardness(3.0F);
        this.setLightOpacity(3);
        this.setSoundType(SoundType.GLASS);
        this.setUnlocalizedName(name);
    }

    @Override
    @Nullable
    public float[] getBeaconColorMultiplier(IBlockState state, World world, BlockPos pos, BlockPos beaconPos)
    {
        return ColorUtils.rgbToFloatArray(0, 44, 127);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.INGOT_BLOCK;
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    protected boolean isTranslucent()
    {
        return true;
    }

    @Override
    protected boolean renderSideWithState()
    {
        return false;
    }
}