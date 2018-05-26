package stevekung.mods.moreplanets.utils.entity.ai;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ));

            for (Block block = this.blockaccess.getBlockState(mutablePos).getBlock(); block == Blocks.FLOWING_WATER || block == Blocks.WATER || block instanceof BlockFluidBase; block = this.blockaccess.getBlockState(mutablePos).getBlock())
            {
                ++i;
                mutablePos.setPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ));
            }
        }
        else if (this.entity.onGround)
        {
            i = MathHelper.floor(this.entity.getEntityBoundingBox().minY + 0.5D);
        }
        else
        {
            BlockPos pos;
            for (pos = new BlockPos(this.entity); (this.blockaccess.getBlockState(pos).getMaterial() == Material.AIR || this.blockaccess.getBlockState(pos).getBlock().isPassable(this.blockaccess, pos)) && pos.getY() > 0; pos = pos.down()) {}
            i = pos.up().getY();
        }

        BlockPos blockpos2 = new BlockPos(this.entity);
        PathNodeType pathnodetype1 = this.getPathNodeType(this.entity, blockpos2.getX(), i, blockpos2.getZ());

        if (this.entity.getPathPriority(pathnodetype1) < 0.0F)
        {
            Set<BlockPos> set = new HashSet<>();
            set.add(new BlockPos(this.entity.getEntityBoundingBox().minX, i, this.entity.getEntityBoundingBox().minZ));
            set.add(new BlockPos(this.entity.getEntityBoundingBox().minX, i, this.entity.getEntityBoundingBox().maxZ));
            set.add(new BlockPos(this.entity.getEntityBoundingBox().maxX, i, this.entity.getEntityBoundingBox().minZ));
            set.add(new BlockPos(this.entity.getEntityBoundingBox().maxX, i, this.entity.getEntityBoundingBox().maxZ));

            for (BlockPos blockpos1 : set)
            {
                PathNodeType type = this.getPathNodeType(this.entity, blockpos1);

                if (this.entity.getPathPriority(type) >= 0.0F)
                {
                    return this.openPoint(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
                }
            }
        }
        return this.openPoint(blockpos2.getX(), i, blockpos2.getZ());
    }
}