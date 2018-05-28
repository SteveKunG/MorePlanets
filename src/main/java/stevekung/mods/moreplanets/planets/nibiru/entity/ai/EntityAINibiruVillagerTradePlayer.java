package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

public class EntityAINibiruVillagerTradePlayer extends EntityAIBase
{
    private final EntityNibiruVillager entity;

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

            if (player == null)
            {
                return false;
            }
            else if (this.entity.getDistanceSq(player) > 16.0D)
            {
                return false;
            }
            else
            {
                return player.openContainer != null;
            }
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