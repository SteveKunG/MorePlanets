package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;

public class EntityInfectedGuardian extends EntityGuardian implements ISpaceMob, IEntityBreathable
{
    private static final DataParameter<Boolean> MOVING = EntityDataManager.<Boolean>createKey(EntityInfectedGuardian.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.createKey(EntityInfectedGuardian.class, DataSerializers.VARINT);
    private EntityLivingBase targetedEntity;
    private int clientSideAttackTime;

    public EntityInfectedGuardian(World world)
    {
        super(world);
        this.moveHelper = new GuardianMoveHelper(this);
        this.clientSideTailAnimation = this.rand.nextFloat();
        this.clientSideTailAnimationO = this.clientSideTailAnimation;
        this.experienceValue = 10;
        this.setSize(0.85F, 0.85F);
    }

    @Override
    protected void initEntityAI()
    {
        EntityAIMoveTowardsRestriction entityaimovetowardsrestriction = new EntityAIMoveTowardsRestriction(this, 1.0D);
        this.wander = new EntityAIWander(this, 1.0D, 80);
        this.tasks.addTask(4, new AIGuardianAttack(this));
        this.tasks.addTask(5, entityaimovetowardsrestriction);
        this.tasks.addTask(7, this.wander);
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityGuardian.class, 12.0F, 0.01F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.wander.setMutexBits(3);
        entityaimovetowardsrestriction.setMutexBits(3);
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, 10, true, false, new GuardianTargetSelector(this)));
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(MOVING, false);
        this.dataManager.register(TARGET_ENTITY, 0);
    }

    @Override
    public boolean isMoving()
    {
        return this.dataManager.get(MOVING).booleanValue();
    }

    private void setMoving(boolean moving)
    {
        this.dataManager.set(MOVING, Boolean.valueOf(moving));
    }

    private void setTargetedEntity(int entityId)
    {
        this.dataManager.set(TARGET_ENTITY, Integer.valueOf(entityId));
    }

    @Override
    public boolean hasTargetedEntity()
    {
        return this.dataManager.get(TARGET_ENTITY).intValue() != 0;
    }

    @Override
    public EntityLivingBase getTargetedEntity()
    {
        if (!this.hasTargetedEntity())
        {
            return null;
        }
        else if (this.world.isRemote)
        {
            if (this.targetedEntity != null)
            {
                return this.targetedEntity;
            }
            else
            {
                Entity entity = this.world.getEntityByID(this.dataManager.get(TARGET_ENTITY).intValue());

                if (entity instanceof EntityLivingBase)
                {
                    this.targetedEntity = (EntityLivingBase)entity;
                    return this.targetedEntity;
                }
                else
                {
                    return null;
                }
            }
        }
        else
        {
            return this.getAttackTarget();
        }
    }

    @Override
    public void notifyDataManagerChange(DataParameter key)
    {
        super.notifyDataManagerChange(key);

        if (TARGET_ENTITY.equals(key))
        {
            this.clientSideAttackTime = 0;
            this.targetedEntity = null;
        }
    }

    @Override
    public float getBlockPathWeight(BlockPos pos)
    {
        return this.world.getBlockState(pos).getBlock() == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK ? 10.0F + this.world.getLightBrightness(pos) - 0.5F : 0.5F - this.world.getLightBrightness(pos);
    }

    @Override
    public void onLivingUpdate()
    {
        if (this.world.isRemote)
        {
            if (this.hasTargetedEntity())
            {
                if (this.clientSideAttackTime < this.getAttackDuration())
                {
                    ++this.clientSideAttackTime;
                }

                EntityLivingBase entitylivingbase = this.getTargetedEntity();

                if (entitylivingbase != null)
                {
                    this.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);
                    this.getLookHelper().onUpdateLook();
                    double d5 = this.getAttackAnimationScale(0.0F);
                    double d0 = entitylivingbase.posX - this.posX;
                    double d1 = entitylivingbase.posY + entitylivingbase.height * 0.5F - (this.posY + this.getEyeHeight());
                    double d2 = entitylivingbase.posZ - this.posZ;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 = d0 / d3;
                    d1 = d1 / d3;
                    d2 = d2 / d3;
                    double d4 = this.rand.nextDouble();

                    while (d4 < d3)
                    {
                        d4 += 1.8D - d5 + this.rand.nextDouble() * (1.7D - d5);
                        this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + d0 * d4, this.posY + d1 * d4 + this.getEyeHeight(), this.posZ + d2 * d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    }
                }
            }
        }
        super.onLivingUpdate();
    }

    @Override
    public float getAttackAnimationScale(float partialTicks)
    {
        return (this.clientSideAttackTime + partialTicks) / this.getAttackDuration();
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MPLootTables.INFECTED_GUARDIAN;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        boolean defaults = this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F;
        return (this.rand.nextInt(20) == 0 || !this.world.canBlockSeeSky(new BlockPos(this))) && defaults;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (!this.isMoving() && !source.isMagicDamage() && source.getSourceOfDamage() instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)source.getSourceOfDamage();

            if (!source.isExplosion())
            {
                entitylivingbase.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.0F);
                entitylivingbase.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80, 0));
            }
        }
        if (this.wander != null)
        {
            this.wander.makeUpdate();
        }
        return super.attackEntityFrom(source, amount);
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

    static class AIGuardianAttack extends EntityAIBase
    {
        private EntityInfectedGuardian entity;
        private int tickCounter;

        public AIGuardianAttack(EntityInfectedGuardian entity)
        {
            this.entity = entity;
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        @Override
        public boolean continueExecuting()
        {
            return super.continueExecuting() && this.entity.getDistanceSqToEntity(this.entity.getAttackTarget()) > 9.0D;
        }

        @Override
        public void startExecuting()
        {
            this.tickCounter = -10;
            this.entity.getNavigator().clearPathEntity();
            this.entity.getLookHelper().setLookPositionWithEntity(this.entity.getAttackTarget(), 90.0F, 90.0F);
            this.entity.isAirBorne = true;
        }

        @Override
        public void resetTask()
        {
            this.entity.setTargetedEntity(0);
            this.entity.setAttackTarget((EntityLivingBase)null);
            this.entity.wander.makeUpdate();
        }

        @Override
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
            this.entity.getNavigator().clearPathEntity();
            this.entity.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);

            if (!this.entity.canEntityBeSeen(entitylivingbase))
            {
                this.entity.setAttackTarget((EntityLivingBase)null);
            }
            else
            {
                ++this.tickCounter;

                if (this.tickCounter == 0)
                {
                    this.entity.setTargetedEntity(this.entity.getAttackTarget().getEntityId());
                    this.entity.world.setEntityState(this.entity, (byte)21);
                }
                else if (this.tickCounter >= this.entity.getAttackDuration())
                {
                    float f = 1.0F;

                    if (this.entity.world.getDifficulty() == EnumDifficulty.HARD)
                    {
                        f += 2.0F;
                    }

                    entitylivingbase.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.entity, this.entity), f);
                    entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.entity), (float)this.entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                    entitylivingbase.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80, 0));
                    this.entity.setAttackTarget((EntityLivingBase)null);
                }
                super.updateTask();
            }
        }
    }

    static class GuardianMoveHelper extends EntityMoveHelper
    {
        private EntityInfectedGuardian entity;

        public GuardianMoveHelper(EntityInfectedGuardian entity)
        {
            super(entity);
            this.entity = entity;
        }

        @Override
        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.entity.getNavigator().noPath())
            {
                double d0 = this.posX - this.entity.posX;
                double d1 = this.posY - this.entity.posY;
                double d2 = this.posZ - this.entity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = MathHelper.sqrt(d3);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
                this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, f, 30.0F);
                this.entity.renderYawOffset = this.entity.rotationYaw;
                float f1 = (float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                this.entity.setAIMoveSpeed(this.entity.getAIMoveSpeed() + (f1 - this.entity.getAIMoveSpeed()) * 0.125F);
                double d4 = Math.sin((this.entity.ticksExisted + this.entity.getEntityId()) * 0.5D) * 0.05D;
                double d5 = Math.cos(this.entity.rotationYaw * (float)Math.PI / 180.0F);
                double d6 = Math.sin(this.entity.rotationYaw * (float)Math.PI / 180.0F);
                this.entity.motionX += d4 * d5;
                this.entity.motionZ += d4 * d6;
                d4 = Math.sin((this.entity.ticksExisted + this.entity.getEntityId()) * 0.75D) * 0.05D;
                this.entity.motionY += d4 * (d6 + d5) * 0.25D;
                this.entity.motionY += this.entity.getAIMoveSpeed() * d1 * 0.1D;
                EntityLookHelper entitylookhelper = this.entity.getLookHelper();
                double d7 = this.entity.posX + d0 / d3 * 2.0D;
                double d8 = this.entity.getEyeHeight() + this.entity.posY + d1 / d3 * 1.0D;
                double d9 = this.entity.posZ + d2 / d3 * 2.0D;
                double d10 = entitylookhelper.getLookPosX();
                double d11 = entitylookhelper.getLookPosY();
                double d12 = entitylookhelper.getLookPosZ();

                if (!entitylookhelper.getIsLooking())
                {
                    d10 = d7;
                    d11 = d8;
                    d12 = d9;
                }
                this.entity.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
                this.entity.setMoving(true);
            }
            else
            {
                this.entity.setAIMoveSpeed(0.0F);
                this.entity.setMoving(false);
            }
        }
    }

    static class GuardianTargetSelector implements Predicate<EntityLivingBase>
    {
        private EntityInfectedGuardian entity;

        public GuardianTargetSelector(EntityInfectedGuardian entity)
        {
            this.entity = entity;
        }

        @Override
        public boolean apply(EntityLivingBase entity)
        {
            return (entity instanceof EntityPlayer || entity instanceof EntityInfectedSquid || entity instanceof EntityAlienMiner) && entity.getDistanceSqToEntity(this.entity) > 9.0D;
        }
    }
}