package stevekung.mods.moreplanets.util.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class BlockSnowLayerMP extends BlockBaseMP
{
    protected static AxisAlignedBB[] SNOW_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
    private Item snowball;
    private int meta;

    public BlockSnowLayerMP(String name, Item snow, int meta)
    {
        super(Material.SNOW);
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateHelper.LAYERS, Integer.valueOf(1)));
        this.setTickRandomly(true);
        this.setSoundType(SoundType.SNOW);
        this.setHardness(0.1F);
        this.setUnlocalizedName(name);
        this.snowball = snow;
        this.meta = meta;
        this.name = name;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }

    @Override
    public boolean isPassable(IBlockAccess world, BlockPos pos)
    {
        return world.getBlockState(pos).getValue(BlockStateHelper.LAYERS).intValue() < 5;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return SNOW_AABB[state.getValue(BlockStateHelper.LAYERS).intValue()];
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {
        int i = state.getValue(BlockStateHelper.LAYERS).intValue() - 1;
        AxisAlignedBB axisalignedbb = state.getBoundingBox(world, pos);
        return new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, i * 0.125F, axisalignedbb.maxZ);
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
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos.down());
        Block block = state.getBlock();
        return block != Blocks.ICE && block != Blocks.PACKED_ICE && !(block instanceof IIce) ? block.isLeaves(state, world, pos.down()) ? true : block == this && state.getValue(BlockStateHelper.LAYERS).intValue() >= 7 ? true : state.isOpaqueCube() && state.getMaterial().blocksMovement() : false;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block)
    {
        this.checkAndDropBlock(world, pos);
    }

    private boolean checkAndDropBlock(World world, BlockPos pos)
    {
        if (!this.canPlaceBlockAt(world, pos))
        {
            world.setBlockToAir(pos);
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        super.harvestBlock(world, player, pos, state, tile, heldStack);
        world.setBlockToAir(pos);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this.snowball;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.meta;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (world.getLightFor(EnumSkyBlock.BLOCK, pos) > 11)
        {
            world.setBlockToAir(pos);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        if (side == EnumFacing.UP)
        {
            return true;
        }
        else
        {
            IBlockState iblockstate = world.getBlockState(pos.offset(side));
            return iblockstate.getBlock() == this && iblockstate.getValue(BlockStateHelper.LAYERS).intValue() >= state.getValue(BlockStateHelper.LAYERS).intValue() ? true : super.shouldSideBeRendered(state, world, pos, side);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockStateHelper.LAYERS, Integer.valueOf((meta & 7) + 1));
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        return world.getBlockState(pos).getValue(BlockStateHelper.LAYERS).intValue() == 1;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateHelper.LAYERS).intValue() - 1;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BlockStateHelper.LAYERS});
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        return state.getValue(BlockStateHelper.LAYERS) + 1;
    }

    @Override
    public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        state = this.getActualState(world.getBlockState(pos), world, pos);
        return state.getValue(BlockStateHelper.LAYERS) >= 8;
    }
}