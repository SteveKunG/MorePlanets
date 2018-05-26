package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

public class EntityAINibiruVillagerLookAtTradePlayer extends EntityAIWatchClosest
{
    private EntityNibiruVillager entity;

    public EntityAINibiruVillagerLookAtTradePlayer(EntityNibiruVillager entity)
    {
        super(entity, EntityPlayer.class, 8.0F);
        this.entity = entity;
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.entity.isTrading())
        {
            this.closestEntity = this.entity.getCustomer();
            return true;
        }
        else
        {
            return false;
        }
    }
}