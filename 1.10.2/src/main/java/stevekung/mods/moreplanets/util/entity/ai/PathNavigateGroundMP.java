package stevekung.mods.moreplanets.util.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

public class PathNavigateGroundMP extends PathNavigateGround
{
    protected WalkNodeProcessorMP nodeProcessor;

    public PathNavigateGroundMP(EntityLiving entity, World world)
    {
        super(entity, world);
    }

    @Override
    protected PathFinder getPathFinder()
    {
        this.nodeProcessor = new WalkNodeProcessorMP();
        this.nodeProcessor.setCanEnterDoors(true);
        return new PathFinder(this.nodeProcessor);
    }

    @Override
    protected Vec3d getEntityPosition()
    {
        return new Vec3d(this.theEntity.posX, this.getPathablePosY(), this.theEntity.posZ);
    }

    private int getPathablePosY()
    {
        if (this.theEntity.isInWater() && this.getCanSwim())
        {
            int i = (int)this.theEntity.getEntityBoundingBox().minY;
            Block block = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.theEntity.posX), i, MathHelper.floor_double(this.theEntity.posZ))).getBlock();
            int j = 0;

            while (block == Blocks.FLOWING_WATER || block == Blocks.WATER || block instanceof BlockFluidBase && this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.theEntity.posX), i, MathHelper.floor_double(this.theEntity.posZ))).getMaterial() == Material.WATER)
            {
                ++i;
                block = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.theEntity.posX), i, MathHelper.floor_double(this.theEntity.posZ))).getBlock();
                ++j;

                if (j > 16)
                {
                    return (int)this.theEntity.getEntityBoundingBox().minY;
                }
            }
            return i;
        }
        else
        {
            return (int)(this.theEntity.getEntityBoundingBox().minY + 0.5D);
        }
    }

    @Override
    public void setBreakDoors(boolean canBreakDoors)
    {
        this.nodeProcessor.setCanBreakDoors(canBreakDoors);
    }

    @Override
    public void setEnterDoors(boolean enterDoors)
    {
        this.nodeProcessor.setCanEnterDoors(enterDoors);
    }

    @Override
    public boolean getEnterDoors()
    {
        return this.nodeProcessor.getCanEnterDoors();
    }

    @Override
    public void setCanSwim(boolean canSwim)
    {
        this.nodeProcessor.setCanSwim(canSwim);
    }

    @Override
    public boolean getCanSwim()
    {
        return this.nodeProcessor.getCanSwim();
    }
}