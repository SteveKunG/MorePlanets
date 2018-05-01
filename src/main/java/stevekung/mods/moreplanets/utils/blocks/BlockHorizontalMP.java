package stevekung.mods.moreplanets.utils.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockHorizontalMP extends BlockLogMP
{
    public BlockHorizontalMP(String name, Material material)
    {
        super(material);
        this.setHardness(0.55F);
        this.setResistance(3.0F);
        this.setSoundType(SoundType.CLOTH);
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.AXIS, BlockStateProperty.EnumAxis.Y));
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return false;
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos)
    {
        return false;
    }
}