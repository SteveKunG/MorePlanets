package stevekung.mods.moreplanets.planets.diona.entity;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.init.MPPotions;

public class EntityZeliusCreeper extends EntityCreeper implements IEntityBreathable
{
    public EntityZeliusCreeper(World world)
    {
        super(world);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
        super.fall(distance, damageMultiplier);

        if (distance >= 5 && this.getHealth() <= 0.0F)
        {
            this.explode();
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.isEntityAlive())
        {
            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.explode();
            }
        }
    }

    @Override
    protected void explode()
    {
        if (!this.world.isRemote)
        {
            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");

            if (this.getPowered())
            {
                this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius * 2, flag);
                List<EntityLivingBase> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.posX - this.explosionRadius * 2, this.posY - this.explosionRadius * 2, this.posZ - this.explosionRadius * 2, this.posX + this.explosionRadius * 2, this.posY + this.explosionRadius * 2, this.posZ + this.explosionRadius * 2));

                for (EntityLivingBase living : list)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZED, 240, 1));
                }
            }
            else
            {
                List<EntityLivingBase> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.posX - this.explosionRadius, this.posY - this.explosionRadius, this.posZ - this.explosionRadius, this.posX + this.explosionRadius, this.posY + this.explosionRadius, this.posZ + this.explosionRadius));

                for (EntityLivingBase living : list)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZED, 120, 1));
                }
                this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius, flag);
            }
            this.setDead();
            this.spawnLingeringCloud();
        }
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.ZELIUS_CREEPER;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_CRYSTALLIZED ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }
}