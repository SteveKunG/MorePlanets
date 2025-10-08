package com.stevekung.moreplanets.planets.nibiru.entity.ai;

import com.stevekung.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAINibiruVillagerLookAtTradePlayer extends EntityAIWatchClosest
{
    private final EntityNibiruVillager entity;

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