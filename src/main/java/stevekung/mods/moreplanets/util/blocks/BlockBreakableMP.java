package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = world.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (this.isTranslucentBlock())
        {
            if (this.renderSideOnOtherState() && world.getBlockState(pos.offset(side.getOpposite())) != iblockstate)
            {
                return true;
            }
            if (block == this)
            {
                return false;
            }
        }
        return super.shouldSideBeRendered(world, pos, side);
    }

    protected abstract boolean isTranslucentBlock();
    protected abstract boolean renderSideOnOtherState();
}