package com.stevekung.moreplanets.planets.nibiru.entity;

import com.stevekung.moreplanets.init.MPPotions;
import com.stevekung.moreplanets.utils.entity.ISpaceMob;

import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;

public class EntityInfectedSquid extends EntitySquid implements IEntityBreathable, ISpaceMob
{
    public EntityInfectedSquid(World world)
    {
        super(world);
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() != MPPotions.INFECTED_SPORE && super.isPotionApplicable(potion);
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.NIBIRU;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }
}