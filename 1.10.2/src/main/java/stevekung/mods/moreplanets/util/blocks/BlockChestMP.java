package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;
import stevekung.mods.moreplanets.util.tileentity.TileEntityChestMP;

public abstract class BlockChestMP extends BlockContainerMP implements ISingleBlockRender
{
    protected BlockChestMP()
    {
        super(Material.wood);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockStateHelper.FACING, EnumFacing.NORTH));
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        this.setHardness(2.5F);
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

        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
        {
            BlockPos blockpos = pos.offset(enumfacing);
            IBlockState iblockstate = world.getBlockState(blockpos);

            if (iblockstate.getBlock() == this)
            {
                this.checkForSurroundingChests(world, blockpos, iblockstate);
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
        BlockPos blockpos = pos.north();
        BlockPos blockpos1 = pos.south();
        BlockPos blockpos2 = pos.west();
        BlockPos blockpos3 = pos.east();
        boolean flag = this == world.getBlockState(blockpos).getBlock();
        boolean flag1 = this == world.getBlockState(blockpos1).getBlock();
        boolean flag2 = this == world.getBlockState(blockpos2).getBlock();
        boolean flag3 = this == world.getBlockState(blockpos3).getBlock();

        if (!flag && !flag1 && !flag2 && !flag3)
        {
            world.setBlockState(pos, state, 3);
        }
        else if (enumfacing.getAxis() != EnumFacing.Axis.X || !flag && !flag1)
        {
            if (enumfacing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3))
            {
                if (flag2)
                {
                    world.setBlockState(blockpos2, state, 3);
                }
                else
                {
                    world.setBlockState(blockpos3, state, 3);
                }
                world.setBlockState(pos, state, 3);
            }
        }
        else
        {
            if (flag)
            {
                world.setBlockState(blockpos, state, 3);
            }
            else
            {
                world.setBlockState(blockpos1, state, 3);
            }
            world.setBlockState(pos, state, 3);
        }

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityChestMP)
            {
                ((TileEntityChestMP)tileentity).setCustomName(stack.getDisplayName());
            }
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
            IBlockState iblockstate = world.getBlockState(pos.north());
            IBlockState iblockstate1 = world.getBlockState(pos.south());
            IBlockState iblockstate2 = world.getBlockState(pos.west());
            IBlockState iblockstate3 = world.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(BlockStateHelper.FACING);
            Block block = iblockstate.getBlock();
            Block block1 = iblockstate1.getBlock();
            Block block2 = iblockstate2.getBlock();
            Block block3 = iblockstate3.getBlock();

            if (block != this && block1 != this)
            {
                boolean flag = block.isFullBlock();
                boolean flag1 = block1.isFullBlock();

                if (block2 == this || block3 == this)
                {
                    BlockPos blockpos1 = block2 == this ? pos.west() : pos.east();
                    IBlockState iblockstate6 = world.getBlockState(blockpos1.north());
                    IBlockState iblockstate7 = world.getBlockState(blockpos1.south());
                    enumfacing = EnumFacing.SOUTH;
                    EnumFacing enumfacing2;

                    if (block2 == this)
                    {
                        enumfacing2 = iblockstate2.getValue(BlockStateHelper.FACING);
                    }
                    else
                    {
                        enumfacing2 = iblockstate3.getValue(BlockStateHelper.FACING);
                    }

                    if (enumfacing2 == EnumFacing.NORTH)
                    {
                        enumfacing = EnumFacing.NORTH;
                    }

                    Block block6 = iblockstate6.getBlock();
                    Block block7 = iblockstate7.getBlock();

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
                BlockPos blockpos = block == this ? pos.north() : pos.south();
                IBlockState iblockstate4 = world.getBlockState(blockpos.west());
                IBlockState iblockstate5 = world.getBlockState(blockpos.east());
                enumfacing = EnumFacing.EAST;
                EnumFacing enumfacing1;

                if (block == this)
                {
                    enumfacing1 = iblockstate.getValue(BlockStateHelper.FACING);
                }
                else
                {
                    enumfacing1 = iblockstate1.getValue(BlockStateHelper.FACING);
                }

                if (enumfacing1 == EnumFacing.WEST)
                {
                    enumfacing = EnumFacing.WEST;
                }

                Block block4 = iblockstate4.getBlock();
                Block block5 = iblockstate5.getBlock();

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

        for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL)
        {
            IBlockState iblockstate = world.getBlockState(pos.offset(enumfacing1));

            if (iblockstate.getBlock() == this)
            {
                return state;
            }

            if (iblockstate.getBlock().isFullBlock())
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

            if (world.getBlockState(pos.offset(enumfacing2)).getBlock().isFullBlock())
            {
                enumfacing2 = enumfacing2.getOpposite();
            }
            if (world.getBlockState(pos.offset(enumfacing2)).getBlock().isFullBlock())
            {
                enumfacing2 = enumfacing2.rotateY();
            }
            if (world.getBlockState(pos.offset(enumfacing2)).getBlock().isFullBlock())
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
        BlockPos blockpos = pos.west();
        BlockPos blockpos1 = pos.east();
        BlockPos blockpos2 = pos.north();
        BlockPos blockpos3 = pos.south();

        if (world.getBlockState(blockpos).getBlock() == this)
        {
            if (this.isDoubleChest(world, blockpos))
            {
                return false;
            }
            ++i;
        }
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
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                if (world.getBlockState(pos.offset(enumfacing)).getBlock() == this)
                {
                    return true;
                }
            }
            return false;
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            ILockableContainer ilockablecontainer = this.getLockableContainer(world, pos);

            if (ilockablecontainer != null)
            {
                playerIn.displayGUIChest(ilockablecontainer);
                playerIn.triggerAchievement(StatList.field_181723_aa);
            }
            return true;
        }
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
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World world, BlockPos pos)
    {
        return Container.calcRedstoneFromInventory(this.getLockableContainer(world, pos));
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
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {BlockStateHelper.FACING});
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.CHEST;
    }

    @Override
    public Block getBlock()
    {
        return this;
    }

    protected abstract ILockableContainer getLockableContainer(World world, BlockPos pos);
    protected abstract TileEntity getChestTile();
}