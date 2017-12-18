package stevekung.mods.moreplanets.module.planets.nibiru.entity.ai;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityNibiruVillager;

public class EntityAINibiruVillagerLookAtTradePlayer extends EntityAIWatchClosest
{
    private EntityNibiruVillager theMerchant;

    public EntityAINibiruVillagerLookAtTradePlayer(EntityNibiruVillager theMerchant)
    {
        super(theMerchant, EntityPlayer.class, 8.0F);
        this.theMerchant = theMerchant;
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.theMerchant.isTrading())
        {
            this.closestEntity = this.theMerchant.getCustomer();
            return true;
        }
        else
        {
            return false;
        }
    }
}