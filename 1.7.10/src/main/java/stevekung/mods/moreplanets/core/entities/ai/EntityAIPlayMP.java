/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities.ai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityFronosVillager;

public class EntityAIPlayMP extends EntityAIBase
{
    private EntityFronosVillager villagerObj;
    private EntityLivingBase targetVillager;
    private double field_75261_c;
    private int playTime;

    public EntityAIPlayMP(EntityFronosVillager villager, double play)
    {
        this.villagerObj = villager;
        this.field_75261_c = play;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.villagerObj.getGrowingAge() >= 0)
        {
            return false;
        }
        else if (this.villagerObj.getRNG().nextInt(400) != 0)
        {
            return false;
        }
        else
        {
            List list = this.villagerObj.worldObj.getEntitiesWithinAABB(EntityFronosVillager.class, this.villagerObj.boundingBox.expand(6.0D, 3.0D, 6.0D));
            double d0 = Double.MAX_VALUE;
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityFronosVillager entityvillager = (EntityFronosVillager)iterator.next();

                if (entityvillager != this.villagerObj && !entityvillager.isPlaying() && entityvillager.getGrowingAge() < 0)
                {
                    double d1 = entityvillager.getDistanceSqToEntity(this.villagerObj);

                    if (d1 <= d0)
                    {
                        d0 = d1;
                        this.targetVillager = entityvillager;
                    }
                }
            }

            if (this.targetVillager == null)
            {
                Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);

                if (vec3 == null)
                {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public boolean continueExecuting()
    {
        return this.playTime > 0;
    }

    @Override
    public void startExecuting()
    {
        if (this.targetVillager != null)
        {
            this.villagerObj.setPlaying(true);
        }
        this.playTime = 1000;
    }

    @Override
    public void resetTask()
    {
        this.villagerObj.setPlaying(false);
        this.targetVillager = null;
    }

    @Override
    public void updateTask()
    {
        --this.playTime;

        if (this.targetVillager != null)
        {
            if (this.villagerObj.getDistanceSqToEntity(this.targetVillager) > 4.0D)
            {
                this.villagerObj.getNavigator().tryMoveToEntityLiving(this.targetVillager, this.field_75261_c);
            }
        }
        else if (this.villagerObj.getNavigator().noPath())
        {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);

            if (vec3 == null)
            {
                return;
            }
            this.villagerObj.getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, this.field_75261_c);
        }
    }
}