package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityVeinBall;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;

public class EntityVeinFloaterMinion extends EntityMob implements IEntityBreathable, ISpaceMob
{
    private float heightOffset = 0.25F;
    private int heightOffsetUpdateTime;

    public EntityVeinFloaterMinion(World world)
    {
        super(world);
        this.setSize(0.8F, 2.0F);
        this.experienceValue = 10;
        this.tasks.addTask(4, new AIFireballAttack(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.checkNoEntityCollision(this.getEntityBoundingBox()) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox()) && this.world.getLightBrightness(this.getPosition()) >= 0.0F;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    }

    @Override
    protected void updateAITasks()
    {
        --this.heightOffsetUpdateTime;

        if (this.heightOffsetUpdateTime <= 0)
        {
            this.heightOffsetUpdateTime = 75;
            this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 2.5F;
        }

        EntityLivingBase entitylivingbase = this.getAttackTarget();

        if (entitylivingbase != null && entitylivingbase.posY + entitylivingbase.getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset)
        {
            this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            this.isAirBorne = true;
        }
        super.updateAITasks();
    }

    @Override
    public void onLivingUpdate()
    {
        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.5D;
        }
        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (!damageSource.getDamageType().equals("mob") && !damageSource.getDamageType().equals("fireball"))
        {
            if (this.isEntityInvulnerable(damageSource))
            {
                return false;
            }
            else if (super.attackEntityFrom(damageSource, damage))
            {
                Entity entity = damageSource.getEntity();

                if (this.getPassengers().contains(entity) && this.getRidingEntity() != entity)
                {
                    if (entity != this)
                    {
                        this.setRevengeTarget((EntityLivingBase)entity);
                    }
                    return true;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
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

    class AIFireballAttack extends EntityAIBase
    {
        private EntityVeinFloaterMinion blaze;
        private int field_179467_b;
        private int field_179468_c;

        public AIFireballAttack(EntityVeinFloaterMinion p_i45846_1_)
        {
            this.blaze = p_i45846_1_;
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.blaze.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        @Override
        public void startExecuting()
        {
            this.field_179467_b = 0;
        }

        @Override
        public void resetTask() {}

        @Override
        public void updateTask()
        {
            --this.field_179468_c;
            EntityLivingBase entitylivingbase = this.blaze.getAttackTarget();
            double d0 = this.blaze.getDistanceSqToEntity(entitylivingbase);

            if (d0 < 1.5D)
            {
                if (this.field_179468_c <= 0)
                {
                    this.field_179468_c = 20;
                    this.blaze.attackEntityAsMob(entitylivingbase);
                }
                this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (d0 < 30.0D)
            {
                double d1 = entitylivingbase.posX - this.blaze.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.blaze.posY + this.blaze.height / 2.0F);
                double d3 = entitylivingbase.posZ - this.blaze.posZ;

                if (this.field_179468_c <= 0)
                {
                    ++this.field_179467_b;

                    if (this.field_179467_b == 1)
                    {
                        this.field_179468_c = 35;
                    }
                    else if (this.field_179467_b <= 6)
                    {
                        this.field_179468_c = 5;
                    }
                    else
                    {
                        this.field_179468_c = 50;
                        this.field_179467_b = 0;
                    }

                    if (this.field_179467_b > 1)
                    {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;

                        for (int i = 0; i < 1; ++i)
                        {
                            EntityVeinBall entitysmallfireball = new EntityVeinBall(this.blaze.world, this.blaze, d1 + this.blaze.getRNG().nextGaussian() * f, d2, d3 + this.blaze.getRNG().nextGaussian() * f);
                            entitysmallfireball.posY = this.blaze.posY + this.blaze.height / 2.0F + 0.5D;
                            this.blaze.world.spawnEntity(entitysmallfireball);
                        }
                    }
                }
                this.blaze.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            }
            else
            {
                this.blaze.getNavigator().clearPathEntity();
                this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            super.updateTask();
        }
    }
}