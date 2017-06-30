package stevekung.mods.moreplanets.util.blocks;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVinesMP extends BlockBaseMP implements IShearable
{
    public static PropertyBool UP = PropertyBool.create("up");
    public static PropertyBool NORTH = PropertyBool.create("north");
    public static PropertyBool EAST = PropertyBool.create("east");
    public static PropertyBool SOUTH = PropertyBool.create("south");
    public static PropertyBool WEST = PropertyBool.create("west");
    public static PropertyBool[] ALL_FACES = new PropertyBool[] {UP, NORTH, SOUTH, WEST, EAST};
    protected static AxisAlignedBB UP_AABB = new AxisAlignedBB(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D);
    protected static AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D);
    protected static AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D);

    public BlockVinesMP(String name)
    {
        super(Material.VINE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
        this.setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
        this.setHardness(0.2F);
        this.setUnlocalizedName(name);
    }

    public BlockVinesMP()
    {
        super(Material.VINE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
        this.setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
        this.setHardness(0.2F);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return state.withProperty(UP, Boolean.valueOf(world.getBlockState(pos.up()).isBlockNormalCube()));
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
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
        int i = 0;
        AxisAlignedBB axisalignedbb = FULL_BLOCK_AABB;

        if (state.getValue(UP).booleanValue())
        {
            axisalignedbb = UP_AABB;
            ++i;
        }
        if (state.getValue(NORTH).booleanValue())
        {
            axisalignedbb = NORTH_AABB;
            ++i;
        }
        if (state.getValue(EAST).booleanValue())
        {
            axisalignedbb = EAST_AABB;
            ++i;
        }
        if (state.getValue(SOUTH).booleanValue())
        {
            axisalignedbb = SOUTH_AABB;
            ++i;
        }
        if (state.getValue(WEST).booleanValue())
        {
            axisalignedbb = WEST_AABB;
            ++i;
        }
        return i == 1 ? axisalignedbb : FULL_BLOCK_AABB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return null;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
    {
        switch (side)
        {
        case UP:
            return this.canAttachVineOn(world.getBlockState(pos.up()));
        case NORTH:
        case SOUTH:
        case EAST:
        case WEST:
            return this.canAttachVineOn(world.getBlockState(pos.offset(side.getOpposite())));
        default:
            return false;
        }
    }

    private boolean canAttachVineOn(IBlockState state)
    {
        return state.isFullCube() && state.getMaterial().blocksMovement();
    }

    private boolean recheckGrownSides(World world, BlockPos pos, IBlockState state)
    {
        IBlockState iblockstate = state;

        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
        {
            PropertyBool propertybool = getPropertyFor(enumfacing);

            if (state.getValue(propertybool).booleanValue() && !this.canAttachVineOn(world.getBlockState(pos.offset(enumfacing))))
            {
                IBlockState iblockstate1 = world.getBlockState(pos.up());

                if (iblockstate1.getBlock() != this || !iblockstate1.getValue(propertybool).booleanValue())
                {
                    state = state.withProperty(propertybool, Boolean.valueOf(false));
                }
            }
        }

        if (this.getNumGrownFaces(state) == 0)
        {
            return false;
        }
        else
        {
            if (iblockstate != state)
            {
                world.setBlockState(pos, state, 2);
            }
            return true;
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        if (!world.isRemote && !this.recheckGrownSides(world, pos, state))
        {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.rand.nextInt(4) == 0)
            {
                int i = 4;
                int j = 5;
                boolean flag = false;
                label62:

                    for (int k = -i; k <= i; ++k)
                    {
                        for (int l = -i; l <= i; ++l)
                        {
                            for (int i1 = -1; i1 <= 1; ++i1)
                            {
                                if (world.getBlockState(pos.add(k, i1, l)).getBlock() == this)
                                {
                                    --j;

                                    if (j <= 0)
                                    {
                                        flag = true;
                                        break label62;
                                    }
                                }
                            }
                        }
                    }

                EnumFacing enumfacing1 = EnumFacing.random(rand);
                BlockPos blockpos1 = pos.up();

                if (enumfacing1 == EnumFacing.UP && pos.getY() < 255 && world.isAirBlock(blockpos1))
                {
                    if (!flag)
                    {
                        IBlockState iblockstate2 = state;

                        for (EnumFacing enumfacing3 : EnumFacing.Plane.HORIZONTAL)
                        {
                            if (rand.nextBoolean() || !this.canAttachVineOn(world.getBlockState(blockpos1.offset(enumfacing3))))
                            {
                                iblockstate2 = iblockstate2.withProperty(getPropertyFor(enumfacing3), Boolean.valueOf(false));
                            }
                        }

                        if (iblockstate2.getValue(NORTH).booleanValue() || iblockstate2.getValue(EAST).booleanValue() || iblockstate2.getValue(SOUTH).booleanValue() || iblockstate2.getValue(WEST).booleanValue())
                        {
                            world.setBlockState(blockpos1, iblockstate2, 2);
                        }
                    }
                }
                else if (enumfacing1.getAxis().isHorizontal() && !state.getValue(getPropertyFor(enumfacing1)).booleanValue())
                {
                    if (!flag)
                    {
                        BlockPos blockpos3 = pos.offset(enumfacing1);

                        if (world.getBlockState(blockpos3).getMaterial() == Material.AIR)
                        {
                            EnumFacing enumfacing2 = enumfacing1.rotateY();
                            EnumFacing enumfacing4 = enumfacing1.rotateYCCW();
                            boolean flag1 = state.getValue(getPropertyFor(enumfacing2)).booleanValue();
                            boolean flag2 = state.getValue(getPropertyFor(enumfacing4)).booleanValue();
                            BlockPos blockpos4 = blockpos3.offset(enumfacing2);
                            BlockPos blockpos = blockpos3.offset(enumfacing4);

                            if (flag1 && this.canAttachVineOn(world.getBlockState(blockpos4)))
                            {
                                world.setBlockState(blockpos3, this.getDefaultState().withProperty(getPropertyFor(enumfacing2), Boolean.valueOf(true)), 2);
                            }
                            else if (flag2 && this.canAttachVineOn(world.getBlockState(blockpos)))
                            {
                                world.setBlockState(blockpos3, this.getDefaultState().withProperty(getPropertyFor(enumfacing4), Boolean.valueOf(true)), 2);
                            }
                            else if (flag1 && world.isAirBlock(blockpos4) && this.canAttachVineOn(world.getBlockState(pos.offset(enumfacing2))))
                            {
                                world.setBlockState(blockpos4, this.getDefaultState().withProperty(getPropertyFor(enumfacing1.getOpposite()), Boolean.valueOf(true)), 2);
                            }
                            else if (flag2 && world.isAirBlock(blockpos) && this.canAttachVineOn(world.getBlockState(pos.offset(enumfacing4))))
                            {
                                world.setBlockState(blockpos, this.getDefaultState().withProperty(getPropertyFor(enumfacing1.getOpposite()), Boolean.valueOf(true)), 2);
                            }
                            else if (this.canAttachVineOn(world.getBlockState(blockpos3.up())))
                            {
                                world.setBlockState(blockpos3, this.getDefaultState(), 2);
                            }
                        }
                        else if (world.getBlockState(blockpos3).getMaterial().isOpaque() && world.getBlockState(blockpos3).isFullCube())
                        {
                            world.setBlockState(pos, state.withProperty(getPropertyFor(enumfacing1), Boolean.valueOf(true)), 2);
                        }
                    }
                }
                else
                {
                    if (pos.getY() > 1)
                    {
                        BlockPos blockpos2 = pos.down();
                        IBlockState iblockstate = world.getBlockState(blockpos2);
                        Block block = iblockstate.getBlock();

                        if (iblockstate.getMaterial() == Material.AIR)
                        {
                            IBlockState iblockstate1 = state;

                            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
                            {
                                if (rand.nextBoolean())
                                {
                                    iblockstate1 = iblockstate1.withProperty(getPropertyFor(enumfacing), Boolean.valueOf(false));
                                }
                            }

                            if (iblockstate1.getValue(NORTH).booleanValue() || iblockstate1.getValue(EAST).booleanValue() || iblockstate1.getValue(SOUTH).booleanValue() || iblockstate1.getValue(WEST).booleanValue())
                            {
                                world.setBlockState(blockpos2, iblockstate1, 2);
                            }
                        }
                        else if (block == this)
                        {
                            IBlockState iblockstate3 = iblockstate;

                            for (EnumFacing enumfacing5 : EnumFacing.Plane.HORIZONTAL)
                            {
                                PropertyBool propertybool = getPropertyFor(enumfacing5);

                                if (rand.nextBoolean() && state.getValue(propertybool).booleanValue())
                                {
                                    iblockstate3 = iblockstate3.withProperty(propertybool, Boolean.valueOf(true));
                                }
                            }

                            if (iblockstate3.getValue(NORTH).booleanValue() || iblockstate3.getValue(EAST).booleanValue() || iblockstate3.getValue(SOUTH).booleanValue() || iblockstate3.getValue(WEST).booleanValue())
                            {
                                world.setBlockState(blockpos2, iblockstate3, 2);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false));
        return facing.getAxis().isHorizontal() ? iblockstate.withProperty(getPropertyFor(facing.getOpposite()), Boolean.valueOf(true)) : iblockstate;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(SOUTH, Boolean.valueOf((meta & 1) > 0)).withProperty(WEST, Boolean.valueOf((meta & 2) > 0)).withProperty(NORTH, Boolean.valueOf((meta & 4) > 0)).withProperty(EAST, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (state.getValue(SOUTH).booleanValue())
        {
            i |= 1;
        }
        if (state.getValue(WEST).booleanValue())
        {
            i |= 2;
        }
        if (state.getValue(NORTH).booleanValue())
        {
            i |= 4;
        }
        if (state.getValue(EAST).booleanValue())
        {
            i |= 8;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {UP, NORTH, EAST, SOUTH, WEST});
    }

    public static PropertyBool getPropertyFor(EnumFacing side)
    {
        switch (side)
        {
        case UP:
            return UP;
        case NORTH:
            return NORTH;
        case SOUTH:
            return SOUTH;
        case EAST:
            return EAST;
        case WEST:
            return WEST;
        default:
            throw new IllegalArgumentException(side + " is an invalid choice");
        }
    }

    private int getNumGrownFaces(IBlockState state)
    {
        int i = 0;

        for (PropertyBool propertybool : ALL_FACES)
        {
            if (state.getValue(propertybool).booleanValue())
            {
                ++i;
            }
        }
        return i;
    }

    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity)
    {
        return true;
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        return Arrays.asList(new ItemStack(this, 1));
    }
}