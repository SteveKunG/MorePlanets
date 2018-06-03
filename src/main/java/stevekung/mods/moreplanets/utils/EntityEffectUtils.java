package stevekung.mods.moreplanets.utils;

import micdoodle8.mods.galacticraft.core.entities.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import stevekung.mods.moreplanets.init.MPPotions;

public class EntityEffectUtils
{
    public static boolean isGalacticraftMob(Entity entity)
    {
        return entity.getClass() == EntityEvolvedSkeleton.class || entity.getClass() == EntityEvolvedZombie.class || entity.getClass() == EntityEvolvedCreeper.class || entity.getClass() == EntityEvolvedSpider.class || entity.getClass() == EntityEvolvedEnderman.class || entity.getClass() == EntityEvolvedWitch.class;
    }

    public static boolean isSpaceNetherMob(Entity entity)
    {
        return entity.getClass() == EntityPigZombie.class || entity.getClass() == EntityBlaze.class || entity.getClass() == EntityWitherSkeleton.class || entity.getClass() == EntityMagmaCube.class || entity.getClass() == EntityGhast.class;
    }

    public static boolean addInfectedSpore(Entity entity)
    {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            if (player.capabilities.isCreativeMode || player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION))
            {
                return false;
            }
            else if (!player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION))
            {
                player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 40, 0));
                return true;
            }
            else
            {
                EntityLivingBase living = (EntityLivingBase) entity;
                living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 40, 0));
                return true;
            }
        }
        return false;
    }
}