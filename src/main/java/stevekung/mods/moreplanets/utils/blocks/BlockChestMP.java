package stevekung.mods.moreplanets.utils.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public abstract class BlockChestMP extends BlockContainerMP implements IItemRarity
{
    private static final AxisAlignedBB NORTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0D, 0.9375D, 0.875D, 0.9375D);
    private static final AxisAlignedBB SOUTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 1.0D);
    private static final AxisAlignedBB WEST_CHEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
    private static final AxisAlignedBB EAST_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D);
    private static final AxisAlignedBB NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

    protected BlockChestMP()
    {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockStateProperty.FACING_HORIZON, EnumFacing.NORTH));
        this.setResistance(5.0F);
        this.setHardness(2.5F);
        this.setSoundType(SoundType.WOOD);
    }

    protected BlockChestMP(Material material)
    {
        super(material);
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
    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state)
    {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (world.getBlockState(pos.north()).getBlock() == this)
        {
            return NORTH_CHEST_AABB;
        }
        else if (world.getBlockState(pos.south()).getBlock() == this)
        {
            return SOUTH_CHEST_AABB;
        }
        else if (world.getBlockState(pos.west()).getBlock() == this)
        {
            return WEST_CHEST_AABB;
        }
        else
        {
            return world.getBlockState(pos.east()).getBlock() == this ? EAST_CHEST_AABB : NOT_CONNECTED_AABB;
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        this.checkForSurroundingChests(world, pos, state);

        for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL)
        {
            BlockPos blockpos = pos.offset(facing);
            IBlockState iblockstate = world.getBlockState(blockpos);

            if (iblockstate.getBlock() == this)
            {
                this.checkForSurroundingChests(world, blockpos, iblockstate);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            ILockableContainer lock = this.getLockableContainer(world, pos, false);

            if (lock != null)
            {
                player.displayGUIChest(lock);
            }
        }
        return true;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(BlockStateProperty.FACING_HORIZON, placer.getHorizontalFacing());
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack heldStack)
    {
        EnumFacing facing = EnumFacing.getHorizontal(MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3).getOpposite();
        state = state.withProperty(BlockStateProperty.FACING_HORIZON, facing);
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
        else if (facing.getAxis() != EnumFacing.Axis.X || !flag && !flag1)
        {
            if (facing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3))
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
        if (heldStack.hasDisplayName())
        {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileEntityChestMP)
            {
                ((TileEntityChestMP)tile).setCustomName(heldStack.getDisplayName());
            }
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

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory)tile);
            world.updateComparatorOutputLevel(pos, this);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return this.getChestTile();
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(BlockStateProperty.FACING_HORIZON, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateProperty.FACING_HORIZON).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BlockStateProperty.FACING_HORIZON);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rotation)
    {
        return state.withProperty(BlockStateProperty.FACING_HORIZON, rotation.rotate(state.getValue(BlockStateProperty.FACING_HORIZON)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirror)
    {
        return state.withRotation(mirror.toRotation(state.getValue(BlockStateProperty.FACING_HORIZON)));
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis)
    {
        return !this.isDoubleChest(world, pos) && super.rotateBlock(world, pos, axis);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return this.getUnlocalizedName().contains("ancient_chest") ? EnumSortCategoryBlock.ANCIENT_CHEST : EnumSortCategoryBlock.CHEST;
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return this.getUnlocalizedName().contains("ancient_chest") ? ColorUtils.stringToRGB(IItemRarity.COMMON) : null;
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
            EnumFacing enumfacing = state.getValue(BlockStateProperty.FACING_HORIZON);

            if (iblockstate.getBlock() != this && iblockstate1.getBlock() != this)
            {
                boolean flag = iblockstate.isFullBlock();
                boolean flag1 = iblockstate1.isFullBlock();

                if (iblockstate2.getBlock() == this || iblockstate3.getBlock() == this)
                {
                    BlockPos blockpos1 = iblockstate2.getBlock() == this ? pos.west() : pos.east();
                    IBlockState iblockstate7 = world.getBlockState(blockpos1.north());
                    IBlockState iblockstate6 = world.getBlockState(blockpos1.south());
                    enumfacing = EnumFacing.SOUTH;
                    EnumFacing enumfacing2;

                    if (iblockstate2.getBlock() == this)
                    {
                        enumfacing2 = iblockstate2.getValue(BlockStateProperty.FACING_HORIZON);
                    }
                    else
                    {
                        enumfacing2 = iblockstate3.getValue(BlockStateProperty.FACING_HORIZON);
                    }

                    if (enumfacing2 == EnumFacing.NORTH)
                    {
                        enumfacing = EnumFacing.NORTH;
                    }
                    if ((flag || iblockstate7.isFullBlock()) && !flag1 && !iblockstate6.isFullBlock())
                    {
                        enumfacing = EnumFacing.SOUTH;
                    }
                    if ((flag1 || iblockstate6.isFullBlock()) && !flag && !iblockstate7.isFullBlock())
                    {
                        enumfacing = EnumFacing.NORTH;
                    }
                }
            }
            else
            {
                BlockPos blockpos = iblockstate.getBlock() == this ? pos.north() : pos.south();
                IBlockState iblockstate4 = world.getBlockState(blockpos.west());
                IBlockState iblockstate5 = world.getBlockState(blockpos.east());
                enumfacing = EnumFacing.EAST;
                EnumFacing enumfacing1;

                if (iblockstate.getBlock() == this)
                {
                    enumfacing1 = iblockstate.getValue(BlockStateProperty.FACING_HORIZON);
                }
                else
                {
                    enumfacing1 = iblockstate1.getValue(BlockStateProperty.FACING_HORIZON);
                }

                if (enumfacing1 == EnumFacing.WEST)
                {
                    enumfacing = EnumFacing.WEST;
                }
                if ((iblockstate2.isFullBlock() || iblockstate4.isFullBlock()) && !iblockstate3.isFullBlock() && !iblockstate5.isFullBlock())
                {
                    enumfacing = EnumFacing.EAST;
                }
                if ((iblockstate3.isFullBlock() || iblockstate5.isFullBlock()) && !iblockstate2.isFullBlock() && !iblockstate4.isFullBlock())
                {
                    enumfacing = EnumFacing.WEST;
                }
            }
            state = state.withProperty(BlockStateProperty.FACING_HORIZON, enumfacing);
            world.setBlockState(pos, state, 3);
            return state;
        }
    }

    public IBlockState correctFacing(World world, BlockPos pos, IBlockState state)
    {
        EnumFacing facing = null;

        for (EnumFacing facing1 : EnumFacing.Plane.HORIZONTAL)
        {
            IBlockState iblockstate = world.getBlockState(pos.offset(facing1));

            if (iblockstate.getBlock() == this)
            {
                return state;
            }
            if (iblockstate.isFullBlock())
            {
                if (facing != null)
                {
                    facing = null;
                    break;
                }
                facing = facing1;
            }
        }

        if (facing != null)
        {
            return state.withProperty(BlockStateProperty.FACING_HORIZON, facing.getOpposite());
        }
        else
        {
            EnumFacing facing2 = state.getValue(BlockStateProperty.FACING_HORIZON);

            if (world.getBlockState(pos.offset(facing2)).isFullBlock())
            {
                facing2 = facing2.getOpposite();
            }
            if (world.getBlockState(pos.offset(facing2)).isFullBlock())
            {
                facing2 = facing2.rotateY();
            }
            if (world.getBlockState(pos.offset(facing2)).isFullBlock())
            {
                facing2 = facing2.getOpposite();
            }
            return state.withProperty(BlockStateProperty.FACING_HORIZON, facing2);
        }
    }

    protected boolean isBlocked(World world, BlockPos pos)
    {
        return world.isSideSolid(pos.up(), EnumFacing.DOWN, false);
    }

    private boolean isDoubleChest(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock() != this)
        {
            return false;
        }
        else
        {
            for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL)
            {
                if (world.getBlockState(pos.offset(facing)).getBlock() == this)
                {
                    return true;
                }
            }
            return false;
        }
    }

    protected abstract TileEntityChestMP getChestTile();
    protected abstract ILockableContainer getLockableContainer(World world, BlockPos pos, boolean allowBlocking);
}