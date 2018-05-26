package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAIFleeNibiruThunder extends EntityAIBase
{
    private final EntityCreature creature;
    private double shelterX;
    private double shelterY;
    private double shelterZ;
    private final double movementSpeed;
    private final World world;

    public EntityAIFleeNibiruThunder(EntityCreature entity, double movementSpeed)
    {
        this.creature = entity;
        this.movementSpeed = movementSpeed;
        this.world = entity.world;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (!this.world.isThundering())
        {
            return false;
        }
        else if (!this.world.canSeeSky(new BlockPos(this.creature.posX, this.creature.getEntityBoundingBox().minY, this.creature.posZ)))
        {
            return false;
        }
        else
        {
            Vec3d vec3d = this.findPossibleShelter();

            if (vec3d == null)
            {
                return false;
            }
            else
            {
                this.shelterX = vec3d.x;
                this.shelterY = vec3d.y;
                this.shelterZ = vec3d.z;
                return true;
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return !this.creature.getNavigator().noPath();
    }

    @Override
    public void startExecuting()
    {
        this.creature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
    }

    @Nullable
    private Vec3d findPossibleShelter()
    {
        Random rand = this.creature.getRNG();
        BlockPos pos = new BlockPos(this.creature.posX, this.creature.getEntityBoundingBox().minY, this.creature.posZ);

        for (int i = 0; i < 10; ++i)
        {
            BlockPos pos1 = pos.add(rand.nextInt(20) - 10, rand.nextInt(6) - 3, rand.nextInt(20) - 10);

            if (!this.world.canSeeSky(pos1) && this.creature.getBlockPathWeight(pos1) < 0.0F)
            {
                return new Vec3d(pos1.getX(), pos1.getY(), pos1.getZ());
            }
        }
        return null;
    }
}