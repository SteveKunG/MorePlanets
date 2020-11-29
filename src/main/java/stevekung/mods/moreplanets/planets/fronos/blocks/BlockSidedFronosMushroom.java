package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.blocks.BlockBushMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockSidedFronosMushroom extends BlockBushMP
{
    public BlockSidedFronosMushroom(String name)
    {
        super(Material.PLANTS);
        this.setUnlocalizedName(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockStateProperty.FACING_HORIZON, EnumFacing.NORTH));
        this.setTickRandomly(true);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(BlockStateProperty.FACING_HORIZON))
        {
        case SOUTH:
            return new AxisAlignedBB(0.3125D, 0.3125D, 0.6625D, 0.6875D, 0.75D, 1.0D);
        case NORTH:
        default:
            return new AxisAlignedBB(0.3125D, 0.3125D, 0.0D, 0.6875D, 0.75D, 0.3375D);
        case WEST:
            return new AxisAlignedBB(0.0D, 0.3125D, 0.3125D, 0.3225D, 0.75D, 0.6875D);
        case EAST:
            return new AxisAlignedBB(0.6725D, 0.3125D, 0.3125D, 1.0D, 0.75D, 0.6875D);
        }
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(BlockStateProperty.FACING_HORIZON, rot.rotate(state.getValue(BlockStateProperty.FACING_HORIZON)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(BlockStateProperty.FACING_HORIZON)));
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        EnumFacing enumfacing = EnumFacing.fromAngle(placer.rotationYaw);
        world.setBlockState(pos, state.withProperty(BlockStateProperty.FACING_HORIZON, enumfacing), 2);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (!facing.getAxis().isHorizontal())
        {
            facing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(BlockStateProperty.FACING_HORIZON, facing.getOpposite());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockStateProperty.FACING_HORIZON, EnumFacing.getHorizontal(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateProperty.FACING_HORIZON).getHorizontalIndex();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BlockStateProperty.FACING_HORIZON);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        return world.getBlockState(pos.north()).getBlock() == MPBlocks.OSCALEA_LOG || world.getBlockState(pos.south()).getBlock() == MPBlocks.OSCALEA_LOG || world.getBlockState(pos.east()).getBlock() == MPBlocks.OSCALEA_LOG || world.getBlockState(pos.west()).getBlock() == MPBlocks.OSCALEA_LOG;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        return world.getBlockState(pos.north()).getBlock() == MPBlocks.OSCALEA_LOG || world.getBlockState(pos.south()).getBlock() == MPBlocks.OSCALEA_LOG || world.getBlockState(pos.east()).getBlock() == MPBlocks.OSCALEA_LOG || world.getBlockState(pos.west()).getBlock() == MPBlocks.OSCALEA_LOG;
    }
}