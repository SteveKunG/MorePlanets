package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityIronGolem;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

public class EntityAINibiruVillagerFollowGolem extends EntityAIBase
{
    private EntityNibiruVillager entity;
    private EntityIronGolem golem;
    private int takeGolemRoseTick;
    private boolean tookGolemRose;

    public EntityAINibiruVillagerFollowGolem(EntityNibiruVillager entity)
    {
        this.entity = entity;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.entity.getGrowingAge() >= 0)
        {
            return false;
        }
        else if (!this.entity.world.isDaytime())
        {
            return false;
        }
        else
        {
            List<EntityIronGolem> list = this.entity.world.getEntitiesWithinAABB(EntityIronGolem.class, this.entity.getEntityBoundingBox().expand(6.0D, 2.0D, 6.0D));

            if (list.isEmpty())
            {
                return false;
            }
            else
            {
                for (EntityIronGolem golem : list)
                {
                    if (golem.getHoldRoseTick() > 0)
                    {
                        this.golem = golem;
                        break;
                    }
                }
                return this.golem != null;
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.golem.getHoldRoseTick() > 0;
    }

    @Override
    public void startExecuting()
    {
        this.takeGolemRoseTick = this.entity.getRNG().nextInt(320);
        this.tookGolemRose = false;
        this.golem.getNavigator().clearPath();
    }

    @Override
    public void resetTask()
    {
        this.golem = null;
        this.entity.getNavigator().clearPath();
    }

    @Override
    public void updateTask()
    {
        this.entity.getLookHelper().setLookPositionWithEntity(this.golem, 30.0F, 30.0F);

        if (this.golem.getHoldRoseTick() == this.takeGolemRoseTick)
        {
            this.entity.getNavigator().tryMoveToEntityLiving(this.golem, 0.5D);
            this.tookGolemRose = true;
        }
        if (this.tookGolemRose && this.entity.getDistanceSq(this.golem) < 4.0D)
        {
            this.golem.setHoldingRose(false);
            this.entity.getNavigator().clearPath();
        }
    }
}