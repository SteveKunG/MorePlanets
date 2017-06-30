package stevekung.mods.moreplanets.module.planets.diona.entity;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;

public class EntityAlienMiner extends EntityMob implements IEntityBreathable, ISpaceMob
{
    private static DataParameter<Integer> TARGET_ENTITY = EntityDataManager.createKey(EntityAlienMiner.class, DataSerializers.VARINT);
    private EntityLivingBase targetedEntity;
    private int chargedTime;

    public EntityAlienMiner(World world)
    {
        super(world);
        this.setSize(0.5F, 1.25F);
        this.isImmuneToFire = true;
        this.experienceValue = 10;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(4, new AILaserBeamAttack(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityGuardian.class, 12.0F, 0.01F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false, new AlienMinerTargetSelector(this)));
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Override
    public void notifyDataManagerChange(DataParameter key)
    {
        super.notifyDataManagerChange(key);

        if (TARGET_ENTITY.equals(key))
        {
            this.chargedTime = 0;
            this.targetedEntity = null;
        }
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(TARGET_ENTITY, 0);
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    protected float getSoundPitch()
    {
        return super.getSoundPitch() + 2.5F;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return MPSounds.ALIEN_MINER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound()
    {
        return MPSounds.ALIEN_MINER_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return MPSounds.ALIEN_MINER_AMBIENT;
    }

    @Override
    public void onLivingUpdate()
    {
        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }
        if (this.worldObj.isRemote)
        {
            if (this.hasTargetedEntity())
            {
                if (this.chargedTime < 80)
                {
                    ++this.chargedTime;
                }

                MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.ALIEN_MINER_SPARK, this.posX, this.posY + 1.4D, this.posZ, new Object[] { -this.getChargedTime(0.0F) });
                EntityLivingBase entitylivingbase = this.getTargetedEntity();

                if (entitylivingbase != null)
                {
                    this.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);
                    this.getLookHelper().onUpdateLook();
                }
            }
        }
        super.onLivingUpdate();
    }

    @Override
    protected void updateAITasks()
    {
        if (this.hasTargetedEntity())
        {
            this.motionY += (0.1D - this.motionY) * 0.1D;
            this.isAirBorne = true;
        }
        super.updateAITasks();
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        Potion potionEff = potion.getPotion();
        return potionEff != MobEffects.POISON && potionEff != MobEffects.INSTANT_DAMAGE && potionEff != MobEffects.WITHER && potionEff != MPPotions.INFECTED_CRYSTALLIZE && potionEff != MPPotions.INFECTED_SPORE;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    public float getChargedTime(float time)
    {
        return (this.chargedTime + time) / 80;
    }

    private void setTargetedEntity(int entityId)
    {
        this.dataManager.set(TARGET_ENTITY, Integer.valueOf(entityId));
    }

    public boolean hasTargetedEntity()
    {
        return this.dataManager.get(TARGET_ENTITY) != 0;
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.ALIEN_MINER;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.ROBOT;
    }

    public EntityLivingBase getTargetedEntity()
    {
        if (!this.hasTargetedEntity())
        {
            return null;
        }
        else if (this.worldObj.isRemote)
        {
            if (this.targetedEntity != null)
            {
                return this.targetedEntity;
            }
            else
            {
                Entity entity = this.worldObj.getEntityByID(this.dataManager.get(TARGET_ENTITY));

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

    static class AILaserBeamAttack extends EntityAIBase
    {
        private EntityAlienMiner entity;
        private int tickCounter;

        public AILaserBeamAttack(EntityAlienMiner entity)
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
            return super.continueExecuting() && this.entity.getDistanceSqToEntity(this.entity.getAttackTarget()) > 5.0D;
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
                    this.entity.playSound(MPSounds.ALIEN_MINER_CHARGED, 2.0F + this.entity.getChargedTime(0.0F), 0.8F);
                }
                else if (this.tickCounter >= 80)
                {
                    float f = 1.0F;

                    if (this.entity.worldObj.getDifficulty() == EnumDifficulty.HARD)
                    {
                        f += 2.0F;
                    }
                    entitylivingbase.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.entity, this.entity), f);
                    entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.entity), (float)this.entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                    this.entity.playSound(MPSounds.ALIEN_MINER_ATTACK, 1.0F + this.entity.getChargedTime(0.0F), 0.8F);
                    entitylivingbase.playSound(MPSounds.ALIEN_MINER_SHOCK, 1.0F + this.entity.getChargedTime(0.0F), 1.0F);
                    this.entity.setAttackTarget((EntityLivingBase)null);

                    if (entitylivingbase instanceof EntityPlayer)
                    {
                        MorePlanetsCore.PROXY.resetFloatingTick((EntityPlayer) entitylivingbase);
                    }
                }
                super.updateTask();
            }
        }
    }

    static class AISplashBlood extends EntityAIWander
    {
        private EntityAlienMiner entity;
        private boolean findStone;

        public AISplashBlood(EntityAlienMiner entity)
        {
            super(entity, 1.0D, 10);
            this.entity = entity;
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute()
        {
            if (this.entity.getAttackTarget() != null)
            {
                return false;
            }
            else if (!this.entity.getNavigator().noPath())
            {
                return false;
            }
            else
            {
                Random random = this.entity.getRNG();

                if (random.nextInt(1000) == 0)
                {
                    BlockPos blockpos = new BlockPos(this.entity.posX, this.entity.posY, this.entity.posZ);
                    IBlockState iblockstate = this.entity.worldObj.getBlockState(blockpos.down());

                    if (iblockstate == DionaBlocks.DIONA_BLOCK.getStateFromMeta(0))
                    {
                        this.findStone = true;
                        return true;
                    }
                }
                this.findStone = false;
                return super.shouldExecute();
            }
        }

        @Override
        public boolean continueExecuting()
        {
            return this.findStone ? false : super.continueExecuting();
        }

        @Override
        public void startExecuting()
        {
            if (!this.findStone)
            {
                super.startExecuting();
            }
            else
            {
                World world = this.entity.worldObj;
                BlockPos blockpos = new BlockPos(this.entity.posX, this.entity.posY, this.entity.posZ);
                IBlockState iblockstate = world.getBlockState(blockpos.down());

                if (iblockstate == DionaBlocks.DIONA_BLOCK.getStateFromMeta(0))
                {
                    world.setBlockState(blockpos.down(), DionaBlocks.ALIEN_MINER_BLOOD.getDefaultState(), 3);
                }
            }
        }
    }

    static class AlienMinerTargetSelector implements Predicate<EntityLivingBase>
    {
        private EntityAlienMiner entity;

        public AlienMinerTargetSelector(EntityAlienMiner entity)
        {
            this.entity = entity;
        }

        @Override
        public boolean apply(EntityLivingBase entity)
        {
            return !(entity instanceof EntityAlienMiner) && entity.getDistanceSqToEntity(this.entity) > 5.0D;
        }
    }
}