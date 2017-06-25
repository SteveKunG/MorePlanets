package stevekung.mods.moreplanets.util.blocks;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public abstract class BlockFarmlandMP extends BlockBaseMP
{
    public BlockFarmlandMP()
    {
        super(Material.GROUND);
        this.setTickRandomly(true);
        this.setHardness(0.6F);
        this.setSoundType(SoundType.GROUND);
        this.setLightOpacity(255);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return null;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
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
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        int i = state.getValue(BlockStateHelper.MOISTURE).intValue();

        if (!this.hasWater(world, pos) && !world.isRainingAt(pos.up()))
        {
            if (i == 1)
            {
                world.setBlockState(pos, state.withProperty(BlockStateHelper.MOISTURE, Integer.valueOf(0)), 2);
            }
            else if (!this.hasCrops(world, pos))
            {
                world.setBlockState(pos, this.getDirtBlock().getDefaultState());
            }
        }
        else if (i == 0)
        {
            world.setBlockState(pos, state.withProperty(BlockStateHelper.MOISTURE, Integer.valueOf(1)), 2);
        }
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
    {
        if (entity instanceof EntityLivingBase)
        {
            if (!world.isRemote && world.rand.nextFloat() < fallDistance - 0.5F)
            {
                if (!(entity instanceof EntityPlayer) && !world.getGameRules().getBoolean("mobGriefing"))
                {
                    return;
                }
                world.setBlockState(pos, this.getDirtBlock().getDefaultState());
            }
            super.onFallenUpon(world, pos, entity, fallDistance);
        }
    }

    protected boolean hasCrops(World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos.up()).getBlock();
        return block instanceof BlockBushMP;
    }

    protected boolean hasWater(World world, BlockPos pos)
    {
        if (this.getSourceBlock() != null)
        {
            Iterator iterator = BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();
            MutableBlockPos mutableblockpos;

            do
            {
                if (!iterator.hasNext())
                {
                    return false;
                }
                mutableblockpos = (MutableBlockPos)iterator.next();
            }
            while (world.getBlockState(mutableblockpos).getBlock() != this.getSourceBlock());
            return true;
        }
        else
        {
            Iterator iterator = BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();
            MutableBlockPos mutableblockpos;

            do
            {
                if (!iterator.hasNext())
                {
                    return false;
                }
                mutableblockpos = (MutableBlockPos)iterator.next();
            }
            while (world.getBlockState(mutableblockpos).getMaterial() != Material.WATER);
            return true;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        switch (facing)
        {
        case UP:
            return true;
        case NORTH:
        case SOUTH:
        case WEST:
        case EAST:
            IBlockState blockState = world.getBlockState(pos.offset(facing));
            Block block = blockState.getBlock();
            return !blockState.isOpaqueCube() && block != this;
        default:
            return super.shouldSideBeRendered(state, world, pos, facing);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block)
    {
        if (world.getBlockState(pos.up()).getMaterial().isSolid())
        {
            world.setBlockState(pos, this.getDirtBlock().getDefaultState());
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this.getDirtBlock());
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockStateHelper.MOISTURE, Integer.valueOf(meta & 1));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateHelper.MOISTURE).intValue();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BlockStateHelper.MOISTURE});
    }

    @Override
    public boolean isFertile(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getValue(BlockStateHelper.MOISTURE) == 1;
    }

    @Override
    public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return side != EnumFacing.DOWN && side != EnumFacing.UP;
    }

    protected Block getSourceBlock()
    {
        return null;
    }

    protected abstract Block getDirtBlock();
}