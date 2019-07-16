package stevekung.mods.moreplanets.planets.fronos.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityAIFronosPanic extends EntityAIBase
{
    private final EntityCreature entity;
    private final double speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;
    private int timer;

    public EntityAIFronosPanic(EntityCreature entity, double speed)
    {
        this.entity = entity;
        this.speed = speed;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.entity.getRevengeTarget() == null && !this.entity.isBurning())
        {
            return false;
        }
        else
        {
            Vec3d vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 5, 4);

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.randPosX = vec3.x;
                this.randPosY = vec3.y;
                this.randPosZ = vec3.z;
                return true;
            }
        }
    }

    @Override
    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
        this.timer = 30;
        this.entity.world.setEntityState(this.entity, (byte)11);
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return !this.entity.getNavigator().noPath() && this.timer > 0;
    }

    @Override
    public void resetTask()
    {
        this.timer = 0;
    }

    @Override
    public void updateTask()
    {
        this.timer = Math.max(0, this.timer - 1);
    }

    public int getTimer()
    {
        return this.timer;
    }
}