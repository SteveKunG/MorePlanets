package stevekung.mods.moreplanets.util.blocks;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public abstract class BlockAncientChestMP extends BlockContainerMP implements ISingleBlockRender
{
    protected BlockAncientChestMP()
    {
        super(Material.wood);
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateHelper.FACING, EnumFacing.NORTH));
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        this.setResistance(5.0F);
        this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 2;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        if (world.getBlockState(pos.north()).getBlock() == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (world.getBlockState(pos.south()).getBlock() == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        }
        else if (world.getBlockState(pos.west()).getBlock() == this)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (world.getBlockState(pos.east()).getBlock() == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        }
        else
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        this.checkForSurroundingChests(world, pos, state);
        Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

        while (iterator.hasNext())
        {
            EnumFacing enumfacing = (EnumFacing)iterator.next();
            BlockPos blockpos1 = pos.offset(enumfacing);
            IBlockState iblockstate1 = world.getBlockState(blockpos1);

            if (iblockstate1.getBlock() == this)
            {
                this.checkForSurroundingChests(world, blockpos1, iblockstate1);
            }
        }
    }

    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(BlockStateHelper.FACING, placer.getHorizontalFacing());
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3).getOpposite();
        state = state.withProperty(BlockStateHelper.FACING, enumfacing);
        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = this == world.getBlockState(blockpos1).getBlock();
        boolean flag1 = this == world.getBlockState(blockpos2).getBlock();
        boolean flag2 = this == world.getBlockState(blockpos3).getBlock();
        boolean flag3 = this == world.getBlockState(blockpos4).getBlock();

        if (!flag && !flag1 && !flag2 && !flag3)
        {
            world.setBlockState(pos, state, 3);
        }
        else if (enumfacing.getAxis() == EnumFacing.Axis.X && (flag || flag1))
        {
            if (flag)
            {
                world.setBlockState(blockpos1, state, 3);
            }
            else
            {
                world.setBlockState(blockpos2, state, 3);
            }
            world.setBlockState(pos, state, 3);
        }
        else if (enumfacing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3))
        {
            if (flag2)
            {
                world.setBlockState(blockpos3, state, 3);
            }
            else
            {
                world.setBlockState(blockpos4, state, 3);
            }
            world.setBlockState(pos, state, 3);
        }
    }

    public IBlockState checkForSurroundingChests(World world, BlockPos pos, IBlockState state)
    {
        if (world.isRemote)
        {
            return state;
        }
        else
        {
            IBlockState iblockstate1 = world.getBlockState(pos.north());
            IBlockState iblockstate2 = world.getBlockState(pos.south());
            IBlockState iblockstate3 = world.getBlockState(pos.west());
            IBlockState iblockstate4 = world.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(BlockStateHelper.FACING);
            Block block = iblockstate1.getBlock();
            Block block1 = iblockstate2.getBlock();
            Block block2 = iblockstate3.getBlock();
            Block block3 = iblockstate4.getBlock();

            if (block != this && block1 != this)
            {
                boolean flag = block.isFullBlock();
                boolean flag1 = block1.isFullBlock();

                if (block2 == this || block3 == this)
                {
                    BlockPos blockpos2 = block2 == this ? pos.west() : pos.east();
                    IBlockState iblockstate7 = world.getBlockState(blockpos2.north());
                    IBlockState iblockstate8 = world.getBlockState(blockpos2.south());
                    enumfacing = EnumFacing.SOUTH;
                    EnumFacing enumfacing2;

                    if (block2 == this)
                    {
                        enumfacing2 = iblockstate3.getValue(BlockStateHelper.FACING);
                    }
                    else
                    {
                        enumfacing2 = iblockstate4.getValue(BlockStateHelper.FACING);
                    }

                    if (enumfacing2 == EnumFacing.NORTH)
                    {
                        enumfacing = EnumFacing.NORTH;
                    }

                    Block block6 = iblockstate7.getBlock();
                    Block block7 = iblockstate8.getBlock();

                    if ((flag || block6.isFullBlock()) && !flag1 && !block7.isFullBlock())
                    {
                        enumfacing = EnumFacing.SOUTH;
                    }
                    if ((flag1 || block7.isFullBlock()) && !flag && !block6.isFullBlock())
                    {
                        enumfacing = EnumFacing.NORTH;
                    }
                }
            }
            else
            {
                BlockPos blockpos1 = block == this ? pos.north() : pos.south();
                IBlockState iblockstate5 = world.getBlockState(blockpos1.west());
                IBlockState iblockstate6 = world.getBlockState(blockpos1.east());
                enumfacing = EnumFacing.EAST;
                EnumFacing enumfacing1;

                if (block == this)
                {
                    enumfacing1 = iblockstate1.getValue(BlockStateHelper.FACING);
                }
                else
                {
                    enumfacing1 = iblockstate2.getValue(BlockStateHelper.FACING);
                }

                if (enumfacing1 == EnumFacing.WEST)
                {
                    enumfacing = EnumFacing.WEST;
                }

                Block block4 = iblockstate5.getBlock();
                Block block5 = iblockstate6.getBlock();

                if ((block2.isFullBlock() || block4.isFullBlock()) && !block3.isFullBlock() && !block5.isFullBlock())
                {
                    enumfacing = EnumFacing.EAST;
                }

                if ((block3.isFullBlock() || block5.isFullBlock()) && !block2.isFullBlock() && !block4.isFullBlock())
                {
                    enumfacing = EnumFacing.WEST;
                }
            }
            state = state.withProperty(BlockStateHelper.FACING, enumfacing);
            world.setBlockState(pos, state, 3);
            return state;
        }
    }

    public IBlockState correctFacing(World world, BlockPos pos, IBlockState state)
    {
        EnumFacing enumfacing = null;
        Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

        while (iterator.hasNext())
        {
            EnumFacing enumfacing1 = (EnumFacing)iterator.next();
            IBlockState iblockstate1 = world.getBlockState(pos.offset(enumfacing1));

            if (iblockstate1.getBlock() == this)
            {
                return state;
            }

            if (iblockstate1.isFullBlock())
            {
                if (enumfacing != null)
                {
                    enumfacing = null;
                    break;
                }
                enumfacing = enumfacing1;
            }
        }

        if (enumfacing != null)
        {
            return state.withProperty(BlockStateHelper.FACING, enumfacing.getOpposite());
        }
        else
        {
            EnumFacing enumfacing2 = state.getValue(BlockStateHelper.FACING);

            if (world.getBlockState(pos.offset(enumfacing2)).isFullBlock())
            {
                enumfacing2 = enumfacing2.getOpposite();
            }
            if (world.getBlockState(pos.offset(enumfacing2)).isFullBlock())
            {
                enumfacing2 = enumfacing2.rotateY();
            }
            if (world.getBlockState(pos.offset(enumfacing2)).isFullBlock())
            {
                enumfacing2 = enumfacing2.getOpposite();
            }
            return state.withProperty(BlockStateHelper.FACING, enumfacing2);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        int i = 0;
        BlockPos blockpos1 = pos.west();
        BlockPos blockpos2 = pos.east();
        BlockPos blockpos3 = pos.north();
        BlockPos blockpos4 = pos.south();

        if (world.getBlockState(blockpos1).getBlock() == this)
        {
            if (this.isDoubleChest(world, blockpos1))
            {
                return false;
            }
            ++i;
        }

        if (world.getBlockState(blockpos2).getBlock() == this)
        {
            if (this.isDoubleChest(world, blockpos2))
            {
                return false;
            }
            ++i;
        }

        if (world.getBlockState(blockpos3).getBlock() == this)
        {
            if (this.isDoubleChest(world, blockpos3))
            {
                return false;
            }
            ++i;
        }

        if (world.getBlockState(blockpos4).getBlock() == this)
        {
            if (this.isDoubleChest(world, blockpos4))
            {
                return false;
            }
            ++i;
        }
        return i <= 1;
    }

    private boolean isDoubleChest(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock() != this)
        {
            return false;
        }
        else
        {
            Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();
            EnumFacing enumfacing;

            do
            {
                if (!iterator.hasNext())
                {
                    return false;
                }
                enumfacing = (EnumFacing)iterator.next();
            }
            while (world.getBlockState(pos.offset(enumfacing)).getBlock() != this);
            return true;
        }
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
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return this.getChestTile();
    }

    protected boolean isBlocked(World world, BlockPos pos)
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
        return this.getDefaultState().withProperty(BlockStateHelper.FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateHelper.FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BlockStateHelper.FACING});
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.ANCIENT_CHEST;
    }

    @Override
    public Block getBlock()
    {
        return this;
    }

    protected abstract TileEntity getChestTile();
}