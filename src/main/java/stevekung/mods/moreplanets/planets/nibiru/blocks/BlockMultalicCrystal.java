package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityMultalicCrystal;
import stevekung.mods.moreplanets.utils.blocks.BlockDirectionalMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;
import stevekung.mods.stevekunglib.utils.enums.CachedEnum;

public class BlockMultalicCrystal extends BlockDirectionalMP implements ITileEntityProvider
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
        return MPItems.MULTALIC_CRYSTAL_PIECES;
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
        for (EnumFacing facing : CachedEnum.facingValues)
        {
            if (this.canPlaceBlock(world, pos, facing))
            {
                return true;
            }
        }
        return false;
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