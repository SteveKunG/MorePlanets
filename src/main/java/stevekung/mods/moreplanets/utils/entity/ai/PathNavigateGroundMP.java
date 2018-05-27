package stevekung.mods.moreplanets.utils.entity.ai;

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
        return new Vec3d(this.entity.posX, this.getPathablePosY(), this.entity.posZ);
    }

    private int getPathablePosY()
    {
        if (this.entity.isInWater() && this.getCanSwim())
        {
            int i = (int)this.entity.getEntityBoundingBox().minY;
            Block block = this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getBlock();
            int j = 0;

            while (block == Blocks.FLOWING_WATER || block == Blocks.WATER || block instanceof BlockFluidBase && this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getMaterial() == Material.WATER)
            {
                ++i;
                block = this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getBlock();
                ++j;

                if (j > 16)
                {
                    return (int)this.entity.getEntityBoundingBox().minY;
                }
            }
            return i;
        }
        else
        {
            return (int)(this.entity.getEntityBoundingBox().minY + 0.5D);
        }
    }
}