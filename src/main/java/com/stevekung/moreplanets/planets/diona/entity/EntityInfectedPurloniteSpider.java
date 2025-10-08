package com.stevekung.moreplanets.planets.diona.entity;

import com.stevekung.moreplanets.init.MPLootTables;
import com.stevekung.moreplanets.init.MPPotions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;

import javax.annotation.Nullable;

public class EntityInfectedPurloniteSpider extends EntitySpider implements IEntityBreathable
{
    public EntityInfectedPurloniteSpider(World world)
    {
        super(world);
        this.setSize(0.9F, 0.6F);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (super.attackEntityAsMob(entity))
        {
            if (entity instanceof EntityLivingBase)
            {
                byte chance = 0;

                if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
                {
                    chance = 7;
                }
                else if (this.world.getDifficulty() == EnumDifficulty.HARD)
                {
                    chance = 15;
                }

                if (chance > 0)
                {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MPPotions.INFECTED_PURLONITE, chance * 20, 0));
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MPLootTables.INFECTED_PURLONITE_SPIDER;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        return data;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() != MPPotions.INFECTED_PURLONITE && super.isPotionApplicable(potion);
    }

    @Override
    public float getEyeHeight()
    {
        return 0.45F;
    }
}