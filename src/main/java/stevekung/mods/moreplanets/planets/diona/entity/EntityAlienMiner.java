package stevekung.mods.moreplanets.planets.diona.entity;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;

public class EntityAlienMiner extends EntityMob implements IEntityBreathable, ISpaceMob
{
    private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.createKey(EntityAlienMiner.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> HOVER = EntityDataManager.createKey(EntityAlienMiner.class, DataSerializers.BOOLEAN);
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
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityGuardian.class, 12.0F, 0.01F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.tasks.addTask(9, new AISplashBlood(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, 10, true, false, new AlienMinerTargetSelector(this)));
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
        this.dataManager.register(HOVER, true);
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
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return MPSounds.ALIEN_MINER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return MPSounds.ALIEN_MINER_DEATH;
    }

    @Override
    public void onLivingUpdate()
    {
        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }
        if (this.world.isRemote)
        {
            if (this.hasTargetedEntity())
            {
                if (this.chargedTime < 80)
                {
                    ++this.chargedTime;
                }

                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.ALIEN_MINER_SPARK, this.posX, this.posY + 0.85D + this.getHoverTick(FMLClientHandler.instance().getClient().getRenderPartialTicks()), this.posZ, new Object[] { -this.getChargedTime(0.0F) });
                EntityLivingBase entity = this.getTargetedEntity();

                if (entity != null)
                {
                    this.getLookHelper().setLookPositionWithEntity(entity, 90.0F, 90.0F);
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
    public boolean isPotionApplicable(PotionEffect effect)
    {
        Potion potion = effect.getPotion();
        return potion != MobEffects.POISON && potion != MobEffects.INSTANT_DAMAGE && potion != MobEffects.WITHER && potion != MPPotions.INFECTED_CRYSTALLIZED && potion != MPPotions.INFECTED_SPORE;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

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

    public float getChargedTime(float time)
    {
        return (this.chargedTime + time) / 80;
    }

    public boolean hasTargetedEntity()
    {
        return this.dataManager.get(TARGET_ENTITY) != 0;
    }

    public void setHovered(boolean hover)
    {
        this.dataManager.set(HOVER, hover);
    }

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
                Entity entity = this.world.getEntityByID(this.dataManager.get(TARGET_ENTITY));

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

    @SideOnly(Side.CLIENT)
    public float getHoverTick(float partialTicks)
    {
        if (!this.getHovered())
        {
            return 0.0F;
        }
        float partialTicksTime = this.ticksExisted + partialTicks;
        float hoverTime = MathHelper.sin(partialTicksTime / 12) / 30.0F + 0.5F;
        return hoverTime = hoverTime * hoverTime + hoverTime;
    }

    private void setTargetedEntity(int entityId)
    {
        this.dataManager.set(TARGET_ENTITY, entityId);
    }

    private boolean getHovered()
    {
        return this.dataManager.get(HOVER);
    }

    static class AILaserBeamAttack extends EntityAIBase
    {
        private final EntityAlienMiner entity;
        private int tickCounter;

        public AILaserBeamAttack(EntityAlienMiner entity)
        {
            this.entity = entity;
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute()
        {
            EntityLivingBase entity = this.entity.getAttackTarget();
            return entity != null && entity.isEntityAlive();
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            return super.shouldContinueExecuting() && this.entity.getDistanceSq(this.entity.getAttackTarget()) > 5.0D;
        }

        @Override
        public void startExecuting()
        {
            this.tickCounter = -10;
            this.entity.getNavigator().clearPath();
            this.entity.getLookHelper().setLookPositionWithEntity(this.entity.getAttackTarget(), 90.0F, 90.0F);
            this.entity.isAirBorne = true;
        }

        @Override
        public void resetTask()
        {
            this.entity.setTargetedEntity(0);
            this.entity.setAttackTarget(null);
        }

        @Override
        public void updateTask()
        {
            EntityLivingBase entity = this.entity.getAttackTarget();
            this.entity.getNavigator().clearPath();
            this.entity.getLookHelper().setLookPositionWithEntity(entity, 90.0F, 90.0F);

            if (!this.entity.canEntityBeSeen(entity))
            {
                this.entity.setAttackTarget(null);
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

                    if (this.entity.world.getDifficulty() == EnumDifficulty.HARD)
                    {
                        f += 2.0F;
                    }
                    entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.entity, this.entity), f);
                    entity.attackEntityFrom(DamageSource.causeMobDamage(this.entity), (float)this.entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                    this.entity.playSound(MPSounds.ALIEN_MINER_ATTACK, 1.0F + this.entity.getChargedTime(0.0F), 0.8F);
                    entity.playSound(MPSounds.ALIEN_MINER_SHOCK, 1.0F + this.entity.getChargedTime(0.0F), 1.0F);
                    this.entity.setAttackTarget(null);

                    if (entity instanceof EntityPlayer)
                    {
                        MorePlanetsMod.PROXY.resetFloatingTick((EntityPlayer) entity);
                    }
                }
                super.updateTask();
            }
        }
    }

    static class AISplashBlood extends EntityAIWander
    {
        private boolean findStone;

        public AISplashBlood(EntityAlienMiner entity)
        {
            super(entity, 1.0D, 10);
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
                Random rand = this.entity.getRNG();

                if (rand.nextInt(500) == 0)
                {
                    BlockPos pos = new BlockPos(this.entity.posX, this.entity.posY, this.entity.posZ);

                    if (this.entity.world.getBlockState(pos.down()).getBlock() == MPBlocks.DIONA_SURFACE_ROCK)
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
        public boolean shouldContinueExecuting()
        {
            return this.findStone ? false : super.shouldContinueExecuting();
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
                World world = this.entity.world;
                BlockPos pos = new BlockPos(this.entity.posX, this.entity.posY, this.entity.posZ);

                if (world.getBlockState(pos.down()).getBlock() == MPBlocks.DIONA_SURFACE_ROCK)
                {
                    world.setBlockState(pos.down(), MPBlocks.ALIEN_MINER_BLOOD.getDefaultState(), 3);
                }
            }
        }
    }

    static class AlienMinerTargetSelector implements Predicate<EntityLivingBase>
    {
        private final EntityAlienMiner entity;

        public AlienMinerTargetSelector(EntityAlienMiner entity)
        {
            this.entity = entity;
        }

        @Override
        public boolean apply(EntityLivingBase entity)
        {
            return !(entity instanceof EntityAlienMiner) && entity.getDistanceSq(this.entity) > 5.0D;
        }
    }
}