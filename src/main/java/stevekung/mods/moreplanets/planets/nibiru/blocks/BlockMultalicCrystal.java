package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityMultalicCrystal;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockMultalicCrystal extends BlockBaseMP implements ITileEntityProvider
{
    public BlockMultalicCrystal(String name)
    {
        super(Material.ROCK);
        this.setLightLevel(0.2F);
        this.setResistance(1.0F);
        this.setHardness(0.4F);
        this.setSoundType(SoundType.GLASS);
        this.setUnlocalizedName(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockStateProperty.FACING_ALL, EnumFacing.UP));
        this.setLightOpacity(255);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        EnumFacing facing = state.getValue(BlockStateProperty.FACING_ALL);
        double box = 0.0625D;

        switch (facing)
        {
        case NORTH:
        default:
            return new AxisAlignedBB(0.0D + box, 0.0D + box, 0.0D + box, 1.0D - box, 1.0D - box, 1.0D);
        case EAST:
            return new AxisAlignedBB(0.0D, 0.0D + box, 0.0D + box, 1.0D - box, 1.0D - box, 1.0D - box);
        case WEST:
            return new AxisAlignedBB(0.0D + box, 0.0D + box, 0.0D + box, 1.0D, 1.0D - box, 1.0D - box);
        case SOUTH:
            return new AxisAlignedBB(0.0D + box, 0.0D + box, 0.0D, 1.0D - box, 1.0D - box, 1.0D - box);
        case UP:
            return new AxisAlignedBB(0.0D + box, 0.0D, 0.0D + box, 1.0D - box, 1.0D - box, 1.0D - box);
        case DOWN:
            return new AxisAlignedBB(0.0D + box, 0.0D + box, 0.0D + box, 1.0D - box, 1.0D, 1.0D - box);
        }
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);

        if (this.getItemDropped(state, world.rand, fortune) != Item.getItemFromBlock(this))
        {
            this.dropXpOnBlockBreak(world, pos, MathHelper.getInt(world.rand, 3, 5));
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(state, rand, fortune))
        {
            int i = rand.nextInt(fortune + 1) - 1;

            if (i < 0)
            {
                i = 0;
            }
            return this.quantityDropped(rand) * (i + 1);
        }
        return this.quantityDropped(rand);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 1 + rand.nextInt(2);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return NibiruItems.MULTALIC_CRYSTAL_PIECES;
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
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityMultalicCrystal();
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing)
    {
        return this.canPlaceBlock(world, pos, facing.getOpposite());
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        for (EnumFacing facing : EnumFacing.VALUES)
        {
            if (this.canPlaceBlock(world, pos, facing))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        if (world.getTileEntity(pos) instanceof TileEntityMultalicCrystal)
        {
            ((TileEntityMultalicCrystal)world.getTileEntity(pos)).setFacing(EnumFacing.getFront(this.getMetaFromState(state)));
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.canPlaceBlock(world, pos, facing.getOpposite()) ? this.getDefaultState().withProperty(BlockStateProperty.FACING_ALL, facing) : this.getDefaultState().withProperty(BlockStateProperty.FACING_ALL, EnumFacing.DOWN);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        if (this.checkForDrop(world, pos) && !this.canPlaceBlock(world, pos, state.getValue(BlockStateProperty.FACING_ALL).getOpposite()))
        {
            world.destroyBlock(pos, false);
            world.setBlockToAir(pos);
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
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

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }

    private boolean canPlaceBlock(World world, BlockPos pos, EnumFacing facing)
    {
        BlockPos blockpos = pos.offset(facing);
        return world.getBlockState(blockpos).isSideSolid(world, blockpos, facing.getOpposite()) || world.getBlockState(blockpos).getBlock() == MPBlocks.MULTALIC_CRYSTAL_BLOCK;
    }

    private boolean checkForDrop(World world, BlockPos pos)
    {
        if (this.canPlaceBlockAt(world, pos))
        {
            return true;
        }
        else
        {
            world.destroyBlock(pos, false);
            return false;
        }
    }
}