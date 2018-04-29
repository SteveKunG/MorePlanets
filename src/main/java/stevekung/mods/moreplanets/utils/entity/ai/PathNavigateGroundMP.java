package stevekung.mods.moreplanets.utils.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
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
    protected boolean isDirectPathBetweenPoints(Vec3d posVec31, Vec3d posVec32, int sizeX, int sizeY, int sizeZ)
    {
        int i = MathHelper.floor(posVec31.x);
        int j = MathHelper.floor(posVec31.z);
        double d0 = posVec32.x - posVec31.x;
        double d1 = posVec32.z - posVec31.z;
        double d2 = d0 * d0 + d1 * d1;

        if (d2 < 1.0E-8D)
        {
            return false;
        }
        else
        {
            double d3 = 1.0D / Math.sqrt(d2);
            d0 = d0 * d3;
            d1 = d1 * d3;
            sizeX = sizeX + 2;
            sizeZ = sizeZ + 2;

            if (!this.isSafeToStandAt(i, (int)posVec31.y, j, sizeX, sizeY, sizeZ, posVec31, d0, d1))
            {
                return false;
            }
            else
            {
                sizeX = sizeX - 2;
                sizeZ = sizeZ - 2;
                double d4 = 1.0D / Math.abs(d0);
                double d5 = 1.0D / Math.abs(d1);
                double d6 = i - posVec31.x;
                double d7 = j - posVec31.z;

                if (d0 >= 0.0D)
                {
                    ++d6;
                }
                if (d1 >= 0.0D)
                {
                    ++d7;
                }

                d6 = d6 / d0;
                d7 = d7 / d1;
                int k = d0 < 0.0D ? -1 : 1;
                int l = d1 < 0.0D ? -1 : 1;
                int i1 = MathHelper.floor(posVec32.x);
                int j1 = MathHelper.floor(posVec32.z);
                int k1 = i1 - i;
                int l1 = j1 - j;

                while (k1 * k > 0 || l1 * l > 0)
                {
                    if (d6 < d7)
                    {
                        d6 += d4;
                        i += k;
                        k1 = i1 - i;
                    }
                    else
                    {
                        d7 += d5;
                        j += l;
                        l1 = j1 - j;
                    }
                    if (!this.isSafeToStandAt(i, (int)posVec31.y, j, sizeX, sizeY, sizeZ, posVec31, d0, d1))
                    {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    @Override
    protected Vec3d getEntityPosition()
    {
        return new Vec3d(this.entity.posX, this.getPathablePosY(), this.entity.posZ);
    }

    @Override
    public void setBreakDoors(boolean canBreakDoors)
    {
        this.nodeProcessor.setCanOpenDoors(canBreakDoors);
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

    private boolean isSafeToStandAt(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Vec3d vec31, double xx, double zz)
    {
        int i = x - sizeX / 2;
        int j = z - sizeZ / 2;

        if (!this.isPositionClear(i, y, j, sizeX, sizeY, sizeZ, vec31, xx, zz))
        {
            return false;
        }
        else
        {
            for (int k = i; k < i + sizeX; ++k)
            {
                for (int l = j; l < j + sizeZ; ++l)
                {
                    double d0 = k + 0.5D - vec31.x;
                    double d1 = l + 0.5D - vec31.z;

                    if (d0 * xx + d1 * zz >= 0.0D)
                    {
                        PathNodeType pathnodetype = this.nodeProcessor.getPathNodeType(this.world, k, y - 1, l, this.entity, sizeX, sizeY, sizeZ, true, true);

                        if (pathnodetype == PathNodeType.WATER)
                        {
                            return false;
                        }
                        if (pathnodetype == PathNodeType.LAVA)
                        {
                            return false;
                        }
                        if (pathnodetype == PathNodeType.OPEN)
                        {
                            return false;
                        }

                        pathnodetype = this.nodeProcessor.getPathNodeType(this.world, k, y, l, this.entity, sizeX, sizeY, sizeZ, true, true);
                        float f = this.entity.getPathPriority(pathnodetype);

                        if (f < 0.0F || f >= 8.0F)
                        {
                            return false;
                        }
                        if (pathnodetype == PathNodeType.DAMAGE_FIRE || pathnodetype == PathNodeType.DANGER_FIRE || pathnodetype == PathNodeType.DAMAGE_OTHER)
                        {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    private boolean isPositionClear(int x, int y, int z, int maxX, int maxY, int maxZ, Vec3d vec, double xx, double zz)
    {
        for (BlockPos blockpos : BlockPos.getAllInBox(new BlockPos(x, y, z), new BlockPos(x + maxX - 1, y + maxY - 1, z + maxZ - 1)))
        {
            double d0 = blockpos.getX() + 0.5D - vec.x;
            double d1 = blockpos.getZ() + 0.5D - vec.z;

            if (d0 * xx + d1 * zz >= 0.0D)
            {
                Block block = this.world.getBlockState(blockpos).getBlock();

                if (!block.isPassable(this.world, blockpos))
                {
                    return false;
                }
            }
        }
        return true;
    }
}