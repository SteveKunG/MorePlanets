package stevekung.mods.moreplanets.util.entity.ai;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.BlockFluidBase;

public class WalkNodeProcessorMP extends WalkNodeProcessor
{
    @Override
    public PathPoint getStart()
    {
        int i;

        if (this.getCanSwim() && this.entity.isInWater())
        {
            i = (int)this.entity.getEntityBoundingBox().minY;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor_double(this.entity.posX), i, MathHelper.floor_double(this.entity.posZ));

            for (Block block = this.blockaccess.getBlockState(blockpos$mutableblockpos).getBlock(); block == Blocks.FLOWING_WATER || block == Blocks.WATER || block instanceof BlockFluidBase; block = this.blockaccess.getBlockState(blockpos$mutableblockpos).getBlock())
            {
                ++i;
                blockpos$mutableblockpos.setPos(MathHelper.floor_double(this.entity.posX), i, MathHelper.floor_double(this.entity.posZ));
            }
        }
        else if (this.entity.onGround)
        {
            i = MathHelper.floor_double(this.entity.getEntityBoundingBox().minY + 0.5D);
        }
        else
        {
            BlockPos blockpos;
            for (blockpos = new BlockPos(this.entity); (this.blockaccess.getBlockState(blockpos).getMaterial() == Material.AIR || this.blockaccess.getBlockState(blockpos).getBlock().isPassable(this.blockaccess, blockpos)) && blockpos.getY() > 0; blockpos = blockpos.down()) {}
            i = blockpos.up().getY();
        }

        BlockPos blockpos2 = new BlockPos(this.entity);
        PathNodeType pathnodetype1 = this.getPathNodeType(this.entity, blockpos2.getX(), i, blockpos2.getZ());

        if (this.entity.getPathPriority(pathnodetype1) < 0.0F)
        {
            Set<BlockPos> set = Sets.newHashSet();
            set.add(new BlockPos(this.entity.getEntityBoundingBox().minX, i, this.entity.getEntityBoundingBox().minZ));
            set.add(new BlockPos(this.entity.getEntityBoundingBox().minX, i, this.entity.getEntityBoundingBox().maxZ));
            set.add(new BlockPos(this.entity.getEntityBoundingBox().maxX, i, this.entity.getEntityBoundingBox().minZ));
            set.add(new BlockPos(this.entity.getEntityBoundingBox().maxX, i, this.entity.getEntityBoundingBox().maxZ));

            for (BlockPos blockpos1 : set)
            {
                PathNodeType pathnodetype = this.getPathNodeType(this.entity, blockpos1);

                if (this.entity.getPathPriority(pathnodetype) >= 0.0F)
                {
                    return this.openPoint(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
                }
            }
        }
        return this.openPoint(blockpos2.getX(), i, blockpos2.getZ());
    }

    private PathNodeType getPathNodeType(EntityLiving entitylivingIn, BlockPos pos)
    {
        return this.getPathNodeType(entitylivingIn, pos.getX(), pos.getY(), pos.getZ());
    }

    private PathNodeType getPathNodeType(EntityLiving entitylivingIn, int x, int y, int z)
    {
        return this.getPathNodeType(this.blockaccess, x, y, z, entitylivingIn, this.entitySizeX, this.entitySizeY, this.entitySizeZ, this.getCanBreakDoors(), this.getCanEnterDoors());
    }
}