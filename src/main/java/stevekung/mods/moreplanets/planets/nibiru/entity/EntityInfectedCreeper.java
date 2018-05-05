package stevekung.mods.moreplanets.planets.nibiru.entity;

import java.util.List;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;

public class EntityInfectedCreeper extends EntityCreeper implements IEntityBreathable, ISpaceMob
{
    public EntityInfectedCreeper(World world)
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
    public void setDead()
    {
        if (!this.world.isRemote && !this.isChild())
        {
            if (this.rand.nextInt(2) == 0)
            {
                for (int i = 0; i < 4; i++)
                {
                    EntityInfectedWorm worm = new EntityInfectedWorm(this.world);
                    worm.setLocationAndAngles(this.posX, this.posY + this.rand.nextInt(2), this.posZ, 360.0F, 0.0F);
                    this.world.spawnEntity(worm);
                }
            }
        }
        super.setDead();
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
    public void explode()
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
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 240, 1));
                }
            }
            else
            {
                List<EntityLivingBase> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.posX - this.explosionRadius, this.posY - this.explosionRadius, this.posZ - this.explosionRadius, this.posX + this.explosionRadius, this.posY + this.explosionRadius, this.posZ + this.explosionRadius));

                for (EntityLivingBase living : list)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 120, 1));
                }
                this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius, flag);
            }
            this.setDead();
        }
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.NIBIRU;
    }
}