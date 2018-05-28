package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityTerrastoneGolem;

public class EntityAINibiruVillagerFollowGolem extends EntityAIBase
{
    private final EntityNibiruVillager entity;
    private EntityTerrastoneGolem ironGolem;

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
            List<EntityTerrastoneGolem> list = this.entity.world.getEntitiesWithinAABB(EntityTerrastoneGolem.class, this.entity.getEntityBoundingBox().grow(6.0D, 2.0D, 6.0D));

            if (list.isEmpty())
            {
                return false;
            }
            else
            {
                return this.ironGolem != null;
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.ironGolem != null;
    }

    @Override
    public void startExecuting()
    {
        this.ironGolem.getNavigator().clearPath();
    }

    @Override
    public void resetTask()
    {
        this.ironGolem = null;
        this.entity.getNavigator().clearPath();
    }

    @Override
    public void updateTask()
    {
        this.entity.getLookHelper().setLookPositionWithEntity(this.ironGolem, 30.0F, 30.0F);
    }
}