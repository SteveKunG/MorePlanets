package stevekung.mods.moreplanets.util.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class BlockSnowLayerMP extends BlockBaseMP
{
    private Item snowball;
    private int meta;

    public BlockSnowLayerMP(String name, Item snow, int meta)
    {
        super(Material.SNOW);
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateHelper.LAYERS, Integer.valueOf(1)));
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeSnow);
        this.setHardness(0.1F);
        this.setBlockBoundsForItemRender();
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
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        int i = state.getValue(BlockStateHelper.LAYERS).intValue() - 1;
        float f = 0.125F;
        return new AxisAlignedBB(pos.getX() + this.minX, pos.getY() + this.minY, pos.getZ() + this.minZ, pos.getX() + this.maxX, pos.getY() + i * f, pos.getZ() + this.maxZ);
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
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        IBlockState iblockstate = world.getBlockState(pos);
        this.getBoundsForLayers(iblockstate.getValue(BlockStateHelper.LAYERS).intValue());
    }

    protected void getBoundsForLayers(int meta)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, meta / 8.0F, 1.0F);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos.down());
        Block block = state.getBlock();
        return block != Blocks.ice && block != Blocks.packed_ice && !(block instanceof IIce) ? block.isLeaves(world, pos.down()) ? true : block == this && state.getValue(BlockStateHelper.LAYERS).intValue() >= 7 ? true : block.isOpaqueCube() && block.getMaterial().blocksMovement() : false;
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
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
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
    {
        super.harvestBlock(world, player, pos, state, tile);
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
    public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.UP ? true : super.shouldSideBeRendered(world, pos, side);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockStateHelper.LAYERS, Integer.valueOf((meta & 7) + 1));
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getValue(BlockStateHelper.LAYERS).intValue() == 1;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateHelper.LAYERS).intValue() - 1;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {BlockStateHelper.LAYERS});
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        return state.getValue(BlockStateHelper.LAYERS) + 1;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        IBlockState state = this.getActualState(world.getBlockState(pos), world, pos);
        return state.getValue(BlockStateHelper.LAYERS) >= 8;
    }
}