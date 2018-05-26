package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

public class EntityAINibiruVillagerTradePlayer extends EntityAIBase
{
    private EntityNibiruVillager entity;

    public EntityAINibiruVillagerTradePlayer(EntityNibiruVillager entity)
    {
        this.entity = entity;
        this.setMutexBits(5);
    }

    @Override
    public boolean shouldExecute()
    {
        if (!this.entity.isEntityAlive())
        {
            return false;
        }
        else if (this.entity.isInWater())
        {
            return false;
        }
        else if (!this.entity.onGround)
        {
            return false;
        }
        else if (this.entity.velocityChanged)
        {
            return false;
        }
        else
        {
            EntityPlayer player = this.entity.getCustomer();
            return player == null ? false : this.entity.getDistanceSq(player) > 16.0D ? false : player.openContainer instanceof Container;
        }
    }

    @Override
    public void startExecuting()
    {
        this.entity.getNavigator().clearPath();
    }

    @Override
    public void resetTask()
    {
        this.entity.setCustomer(null);
    }
}