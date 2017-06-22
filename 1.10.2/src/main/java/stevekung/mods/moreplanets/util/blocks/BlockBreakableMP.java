package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockBreakableMP extends BlockBaseMP
{
    public BlockBreakableMP(Material material)
    {
        super(material);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        Block block = state.getBlock();

        if (this.isTranslucentBlock())
        {
            if (world.getBlockState(pos.offset(side)) != state)
            {
                return true;
            }
            if (block == this)
            {
                return false;
            }
        }
        return super.shouldSideBeRendered(state, world, pos, side);
    }

    protected abstract boolean isTranslucentBlock();
    protected abstract boolean renderSideOnOtherState();
}