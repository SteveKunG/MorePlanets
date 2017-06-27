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
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        IBlockState iblockstate = world.getBlockState(pos.offset(facing));
        Block block = iblockstate.getBlock();

        if (this.isTranslucent())
        {
            if (this.renderSideWithState() && world.getBlockState(pos.offset(facing)) != world.getBlockState(pos))
            {
                return true;
            }
            if (block == this)
            {
                return false;
            }
        }
        return super.shouldSideBeRendered(state, world, pos, facing);
    }

    protected abstract boolean isTranslucent();
    protected abstract boolean renderSideWithState();
}