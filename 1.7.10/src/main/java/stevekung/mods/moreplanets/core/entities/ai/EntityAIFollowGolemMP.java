/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities.ai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityIronGolem;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityFronosVillager;

public class EntityAIFollowGolemMP extends EntityAIBase
{
    private EntityFronosVillager theVillager;
    private EntityIronGolem theGolem;
    private int takeGolemRoseTick;
    private boolean tookGolemRose;

    public EntityAIFollowGolemMP(EntityFronosVillager villager)
    {
        this.theVillager = villager;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.theVillager.getGrowingAge() >= 0)
        {
            return false;
        }
        else if (!this.theVillager.worldObj.isDaytime())
        {
            return false;
        }
        else
        {
            List list = this.theVillager.worldObj.getEntitiesWithinAABB(EntityIronGolem.class, this.theVillager.boundingBox.expand(6.0D, 2.0D, 6.0D));

            if (list.isEmpty())
            {
                return false;
            }
            else
            {
                Iterator iterator = list.iterator();

                while (iterator.hasNext())
                {
                    EntityIronGolem entityirongolem = (EntityIronGolem)iterator.next();

                    if (entityirongolem.getHoldRoseTick() > 0)
                    {
                        this.theGolem = entityirongolem;
                        break;
                    }
                }

                return this.theGolem != null;
            }
        }
    }

    @Override
    public boolean continueExecuting()
    {
        return this.theGolem.getHoldRoseTick() > 0;
    }

    @Override
    public void startExecuting()
    {
        this.takeGolemRoseTick = this.theVillager.getRNG().nextInt(320);
        this.tookGolemRose = false;
        this.theGolem.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask()
    {
        this.theGolem = null;
        this.theVillager.getNavigator().clearPathEntity();
    }

    @Override
    public void updateTask()
    {
        this.theVillager.getLookHelper().setLookPositionWithEntity(this.theGolem, 30.0F, 30.0F);

        if (this.theGolem.getHoldRoseTick() == this.takeGolemRoseTick)
        {
            this.theVillager.getNavigator().tryMoveToEntityLiving(this.theGolem, 0.5D);
            this.tookGolemRose = true;
        }
        if (this.tookGolemRose && this.theVillager.getDistanceSqToEntity(this.theGolem) < 4.0D)
        {
            this.theGolem.setHoldingRose(false);
            this.theVillager.getNavigator().clearPathEntity();
        }
    }
}