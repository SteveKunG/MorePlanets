package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockWallMP extends BlockBaseMP
{
    public static PropertyBool UP = PropertyBool.create("up");
    public static PropertyBool NORTH = PropertyBool.create("north");
    public static PropertyBool EAST = PropertyBool.create("east");
    public static PropertyBool SOUTH = PropertyBool.create("south");
    public static PropertyBool WEST = PropertyBool.create("west");

    public BlockWallMP(Material material)
    {
        super(material);
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess world, BlockPos pos)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        boolean flag = this.canConnectTo(world, pos.north());
        boolean flag1 = this.canConnectTo(world, pos.south());
        boolean flag2 = this.canConnectTo(world, pos.west());
        boolean flag3 = this.canConnectTo(world, pos.east());
        float f = 0.25F;
        float f1 = 0.75F;
        float f2 = 0.25F;
        float f3 = 0.75F;
        float f4 = 1.0F;

        if (flag)
        {
            f2 = 0.0F;
        }
        if (flag1)
        {
            f3 = 1.0F;
        }
        if (flag2)
        {
            f = 0.0F;
        }
        if (flag3)
        {
            f1 = 1.0F;
        }

        if (flag && flag1 && !flag2 && !flag3)
        {
            f4 = 0.8125F;
            f = 0.3125F;
            f1 = 0.6875F;
        }
        else if (!flag && !flag1 && flag2 && flag3)
        {
            f4 = 0.8125F;
            f2 = 0.3125F;
            f3 = 0.6875F;
        }
        this.setBlockBounds(f, 0.0F, f2, f1, f4, f3);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(world, pos);
        this.maxY = 1.5D;
        return super.getCollisionBoundingBox(world, pos, state);
    }

    @Override
    public boolean canPlaceTorchOnTop(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.DOWN ? super.shouldSideBeRendered(world, pos, side) : true;
    }

    protected boolean canConnectTo(IBlockAccess world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();
        return block == Blocks.barrier ? false : !(block instanceof BlockWallMP) && !(block instanceof BlockFenceGate) ? block.getMaterial().isOpaque() && block.isFullCube() ? block.getMaterial() != Material.gourd : false : true;
    }
}