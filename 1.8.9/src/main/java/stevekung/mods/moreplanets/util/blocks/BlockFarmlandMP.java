package stevekung.mods.moreplanets.util.blocks;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos.MutableBlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public abstract class BlockFarmlandMP extends BlockBaseMP
{
    public BlockFarmlandMP()
    {
        super(Material.ground);
        this.setTickRandomly(true);
        this.setHardness(0.6F);
        this.setStepSound(soundTypeGravel);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setLightOpacity(255);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return null;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
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
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        int i = state.getValue(BlockStateHelper.MOISTURE).intValue();

        if (!this.hasWater(world, pos) && !world.canLightningStrike(pos.up()))
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
            while (world.getBlockState(mutableblockpos).getBlock().getMaterial() != Material.water);
            return true;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (world.getBlockState(pos.up()).getBlock().getMaterial().isSolid())
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
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateHelper.MOISTURE).intValue();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {BlockStateHelper.MOISTURE});
    }

    @Override
    public boolean isFertile(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getValue(BlockStateHelper.MOISTURE) == 1;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return side != EnumFacing.DOWN && side != EnumFacing.UP;
    }

    protected Block getSourceBlock()
    {
        return null;
    }

    protected abstract Block getDirtBlock();
}