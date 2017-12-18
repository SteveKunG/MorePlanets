package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import java.util.List;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;

public class EntityInfectedCreeper extends EntityCreeper implements IEntityBreathable, ISpaceMob
{
    public EntityInfectedCreeper(World world)
    {
        super(world);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAICreeperSwell(this));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
    }

    @Override
    public void setDead()
    {
        if (!this.worldObj.isRemote && !this.isChild())
        {
            if (this.rand.nextInt(2) == 0)
            {
                for (int i = 0; i < 4; i++)
                {
                    EntityInfectedWorm worm = new EntityInfectedWorm(this.worldObj);
                    worm.setLocationAndAngles(this.posX, this.posY + this.rand.nextInt(2), this.posZ, 360.0F, 0.0F);
                    this.worldObj.spawnEntityInWorld(worm);
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
        if (!this.worldObj.isRemote)
        {
            boolean flag = this.worldObj.getGameRules().getBoolean("mobGriefing");

            if (this.getPowered())
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius * 2, flag);
                List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.fromBounds(this.posX - this.explosionRadius * 2, this.posY - this.explosionRadius * 2, this.posZ - this.explosionRadius * 2, this.posX + this.explosionRadius * 2, this.posY + this.explosionRadius * 2, this.posZ + this.explosionRadius * 2));

                for (EntityLivingBase living : list)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 240, 1));
                }
            }
            else
            {
                List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.fromBounds(this.posX - this.explosionRadius, this.posY - this.explosionRadius, this.posZ - this.explosionRadius, this.posX + this.explosionRadius, this.posY + this.explosionRadius, this.posZ + this.explosionRadius));

                for (EntityLivingBase living : list)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 120, 1));
                }
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius, flag);
            }
            this.setDead();
        }
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int j2 = this.rand.nextInt(3);

        if (fortune > 0)
        {
            j2 += this.rand.nextInt(fortune + 1);
        }
        for (int k = 0; k < j2; ++k)
        {
            this.entityDropItem(new ItemStack(Items.gunpowder, 1), 0.0F);
        }
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotionID() == MPPotions.INFECTED_SPORE.id ? false : super.isPotionApplicable(potion);
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