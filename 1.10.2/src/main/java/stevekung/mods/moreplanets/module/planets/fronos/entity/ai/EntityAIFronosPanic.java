package stevekung.mods.moreplanets.module.planets.fronos.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIFronosPanic extends EntityAIBase
{
    private EntityCreature entity;
    protected double speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;
    int timer;

    public EntityAIFronosPanic(EntityCreature entity, double speed)
    {
        this.entity = entity;
        this.speed = speed;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.entity.getAITarget() == null && !this.entity.isBurning())
        {
            return false;
        }
        else
        {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 5, 4);

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.randPosX = vec3.xCoord;
                this.randPosY = vec3.yCoord;
                this.randPosZ = vec3.zCoord;
                return true;
            }
        }
    }

    @Override
    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
        this.timer = 30;
        this.entity.worldObj.setEntityState(this.entity, (byte)11);
    }

    @Override
    public boolean continueExecuting()
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