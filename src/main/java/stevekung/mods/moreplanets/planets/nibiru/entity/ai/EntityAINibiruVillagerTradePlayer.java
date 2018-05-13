package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

public class EntityAINibiruVillagerTradePlayer extends EntityAIBase
{
    private EntityNibiruVillager villager;

    public EntityAINibiruVillagerTradePlayer(EntityNibiruVillager villager)
    {
        this.villager = villager;
        this.setMutexBits(5);
    }

    @Override
    public boolean shouldExecute()
    {
        if (!this.villager.isEntityAlive())
        {
            return false;
        }
        else if (this.villager.isInWater())
        {
            return false;
        }
        else if (!this.villager.onGround)
        {
            return false;
        }
        else if (this.villager.velocityChanged)
        {
            return false;
        }
        else
        {
            EntityPlayer entityplayer = this.villager.getCustomer();
            return entityplayer == null ? false : this.villager.getDistanceSq(entityplayer) > 16.0D ? false : entityplayer.openContainer instanceof Container;
        }
    }

    @Override
    public void startExecuting()
    {
        this.villager.getNavigator().clearPath();
    }

    @Override
    public void resetTask()
    {
        this.villager.setCustomer(null);
    }
}