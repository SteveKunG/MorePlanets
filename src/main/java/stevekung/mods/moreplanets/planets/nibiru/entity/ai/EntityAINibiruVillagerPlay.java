package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

public class EntityAINibiruVillagerPlay extends EntityAIBase
{
    private EntityNibiruVillager entity;
    private EntityLivingBase targetVillager;
    private double speed;
    private int playTime;

    public EntityAINibiruVillagerPlay(EntityNibiruVillager entity, double speed)
    {
        this.entity = entity;
        this.speed = speed;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.entity.getGrowingAge() >= 0)
        {
            return false;
        }
        else if (this.entity.getRNG().nextInt(400) != 0)
        {
            return false;
        }
        else
        {
            List<EntityNibiruVillager> list = this.entity.world.getEntitiesWithinAABB(EntityNibiruVillager.class, this.entity.getEntityBoundingBox().expand(6.0D, 3.0D, 6.0D));
            double d0 = Double.MAX_VALUE;

            for (EntityNibiruVillager entityvillager : list)
            {
                if (entityvillager != this.entity && !entityvillager.isPlaying() && entityvillager.getGrowingAge() < 0)
                {
                    double d1 = entityvillager.getDistanceSq(this.entity);

                    if (d1 <= d0)
                    {
                        d0 = d1;
                        this.targetVillager = entityvillager;
                    }
                }
            }

            if (this.targetVillager == null)
            {
                Vec3d vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 16, 3);

                if (vec3 == null)
                {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.playTime > 0;
    }

    @Override
    public void startExecuting()
    {
        if (this.targetVillager != null)
        {
            this.entity.setPlaying(true);
        }
        this.playTime = 1000;
    }

    @Override
    public void resetTask()
    {
        this.entity.setPlaying(false);
        this.targetVillager = null;
    }

    @Override
    public void updateTask()
    {
        --this.playTime;

        if (this.targetVillager != null)
        {
            if (this.entity.getDistanceSq(this.targetVillager) > 4.0D)
            {
                this.entity.getNavigator().tryMoveToEntityLiving(this.targetVillager, this.speed);
            }
        }
        else if (this.entity.getNavigator().noPath())
        {
            Vec3d vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 16, 3);

            if (vec3 == null)
            {
                return;
            }
            this.entity.getNavigator().tryMoveToXYZ(vec3.x, vec3.y, vec3.z, this.speed);
        }
    }
}