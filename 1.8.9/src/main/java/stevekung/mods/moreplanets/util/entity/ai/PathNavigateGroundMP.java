package stevekung.mods.moreplanets.util.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

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
        this.nodeProcessor.setEnterDoors(true);
        return new PathFinder(this.nodeProcessor);
    }

    @Override
    protected Vec3 getEntityPosition()
    {
        return new Vec3(this.theEntity.posX, this.getPathablePosY(), this.theEntity.posZ);
    }

    private int getPathablePosY()
    {
        if (this.theEntity.isInWater() && this.getCanSwim())
        {
            int i = (int)this.theEntity.getEntityBoundingBox().minY;
            Block block = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.theEntity.posX), i, MathHelper.floor_double(this.theEntity.posZ))).getBlock();
            int j = 0;

            while (block == NibiruBlocks.PURIFY_WATER_FLUID_BLOCK)
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
    public void setAvoidsWater(boolean avoidsWater)
    {
        this.nodeProcessor.setAvoidsWater(avoidsWater);
    }

    @Override
    public boolean getAvoidsWater()
    {
        return this.nodeProcessor.getAvoidsWater();
    }

    @Override
    public void setBreakDoors(boolean canBreakDoors)
    {
        this.nodeProcessor.setBreakDoors(canBreakDoors);
    }

    @Override
    public void setEnterDoors(boolean enter)
    {
        this.nodeProcessor.setEnterDoors(enter);
    }

    @Override
    public boolean getEnterDoors()
    {
        return this.nodeProcessor.getEnterDoors();
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