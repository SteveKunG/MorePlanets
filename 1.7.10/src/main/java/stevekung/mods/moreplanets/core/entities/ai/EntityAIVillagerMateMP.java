/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityFronosVillager;

public class EntityAIVillagerMateMP extends EntityAIBase
{
    private EntityFronosVillager villagerObj;
    private EntityFronosVillager mate;
    private World worldObj;
    private int matingTimeout;
    private Village villageObj;

    public EntityAIVillagerMateMP(EntityFronosVillager villager)
    {
        this.villagerObj = villager;
        this.worldObj = villager.worldObj;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.villagerObj.getGrowingAge() != 0)
        {
            return false;
        }
        else if (this.villagerObj.getRNG().nextInt(500) != 0)
        {
            return false;
        }
        else
        {
            this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.villagerObj.posX), MathHelper.floor_double(this.villagerObj.posY), MathHelper.floor_double(this.villagerObj.posZ), 0);

            if (this.villageObj == null)
            {
                return false;
            }
            else if (!this.checkSufficientDoorsPresentForNewVillager())
            {
                return false;
            }
            else
            {
                Entity entity = this.worldObj.findNearestEntityWithinAABB(EntityVillager.class, this.villagerObj.boundingBox.expand(8.0D, 3.0D, 8.0D), this.villagerObj);

                if (entity == null)
                {
                    return false;
                }
                else
                {
                    this.mate = (EntityFronosVillager)entity;
                    return this.mate.getGrowingAge() == 0;
                }
            }
        }
    }

    @Override
    public void startExecuting()
    {
        this.matingTimeout = 300;
        this.villagerObj.setMating(true);
    }

    @Override
    public void resetTask()
    {
        this.villageObj = null;
        this.mate = null;
        this.villagerObj.setMating(false);
    }

    @Override
    public boolean continueExecuting()
    {
        return this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager() && this.villagerObj.getGrowingAge() == 0;
    }

    @Override
    public void updateTask()
    {
        --this.matingTimeout;
        this.villagerObj.getLookHelper().setLookPositionWithEntity(this.mate, 10.0F, 30.0F);

        if (this.villagerObj.getDistanceSqToEntity(this.mate) > 2.25D)
        {
            this.villagerObj.getNavigator().tryMoveToEntityLiving(this.mate, 0.25D);
        }
        else if (this.matingTimeout == 0 && this.mate.isMating())
        {
            this.giveBirth();
        }

        if (this.villagerObj.getRNG().nextInt(35) == 0)
        {
            this.worldObj.setEntityState(this.villagerObj, (byte)12);
        }
    }

    private boolean checkSufficientDoorsPresentForNewVillager()
    {
        if (!this.villageObj.isMatingSeason())
        {
            return false;
        }
        else
        {
            int i = (int)(this.villageObj.getNumVillageDoors() * 0.35D);
            return this.villageObj.getNumVillagers() < i;
        }
    }

    private void giveBirth()
    {
        EntityFronosVillager entityvillager = this.villagerObj.createChild(this.mate);
        this.mate.setGrowingAge(6000);
        this.villagerObj.setGrowingAge(6000);
        entityvillager.setGrowingAge(-24000);
        entityvillager.setLocationAndAngles(this.villagerObj.posX, this.villagerObj.posY, this.villagerObj.posZ, 0.0F, 0.0F);
        this.worldObj.spawnEntityInWorld(entityvillager);
        this.worldObj.setEntityState(entityvillager, (byte)12);
    }
}