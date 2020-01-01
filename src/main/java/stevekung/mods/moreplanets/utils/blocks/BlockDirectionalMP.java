package stevekung.mods.moreplanets.utils.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockDirectionalMP extends BlockBaseMP
{
    public BlockDirectionalMP(Material material)
    {
        super(material);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing;

        switch (meta & 7)
        {
        case 0:
            facing = EnumFacing.DOWN;
            break;
        case 1:
            facing = EnumFacing.EAST;
            break;
        case 2:
            facing = EnumFacing.WEST;
            break;
        case 3:
            facing = EnumFacing.SOUTH;
            break;
        case 4:
            facing = EnumFacing.NORTH;
            break;
        case 5:
        default:
            facing = EnumFacing.UP;
        }
        return this.getDefaultState().withProperty(BlockStateProperty.FACING_ALL, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i;

        switch (state.getValue(BlockStateProperty.FACING_ALL))
        {
        case EAST:
            i = 1;
            break;
        case WEST:
            i = 2;
            break;
        case SOUTH:
            i = 3;
            break;
        case NORTH:
            i = 4;
            break;
        case UP:
        default:
            i = 5;
            break;
        case DOWN:
            i = 0;
        }
        return i;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rotation)
    {
        return state.withProperty(BlockStateProperty.FACING_ALL, rotation.rotate(state.getValue(BlockStateProperty.FACING_ALL)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirror)
    {
        return state.withRotation(mirror.toRotation(state.getValue(BlockStateProperty.FACING_ALL)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BlockStateProperty.FACING_ALL);
    }
}