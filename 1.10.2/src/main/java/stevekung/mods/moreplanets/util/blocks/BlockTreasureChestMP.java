package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public abstract class BlockTreasureChestMP extends BlockContainerMP implements ISingleBlockRender
{
    protected static AxisAlignedBB NORTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0D, 0.9375D, 0.875D, 0.9375D);
    protected static AxisAlignedBB SOUTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 1.0D);
    protected static AxisAlignedBB WEST_CHEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
    protected static AxisAlignedBB EAST_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D);
    protected static AxisAlignedBB NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

    public BlockTreasureChestMP()
    {
        super(Material.ROCK);
        this.setResistance(10000000.0F);
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateHelper.FACING_HORIZON, EnumFacing.NORTH));
        this.setHardness(-1.0F);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return world.getBlockState(pos.north()).getBlock() == this ? NORTH_CHEST_AABB : world.getBlockState(pos.south()).getBlock() == this ? SOUTH_CHEST_AABB : world.getBlockState(pos.west()).getBlock() == this ? WEST_CHEST_AABB : world.getBlockState(pos.east()).getBlock() == this ? EAST_CHEST_AABB : NOT_CONNECTED_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory)tileentity);
            world.updateComparatorOutputLevel(pos, this);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(BlockStateHelper.FACING_HORIZON, placer.getHorizontalFacing().getOpposite());
    }

    public boolean cannotOpenChest(World world, BlockPos pos)
    {
        return this.isBelowSolidBlock(world, pos);
    }

    private boolean isBelowSolidBlock(World world, BlockPos pos)
    {
        return world.isSideSolid(pos.up(), EnumFacing.DOWN, false);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(BlockStateHelper.FACING_HORIZON, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateHelper.FACING_HORIZON).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BlockStateHelper.FACING_HORIZON});
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.TREASURE_CHEST;
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}