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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;

public class EntityInfectedGuardian extends EntityGuardian implements ISpaceMob, IEntityBreathable
{
    private static DataParameter<Byte> STATUS = EntityDataManager.createKey(EntityInfectedGuardian.class, DataSerializers.BYTE);
    private static DataParameter<Integer> TARGET_ENTITY = EntityDataManager.createKey(EntityInfectedGuardian.class, DataSerializers.VARINT);
    private float clientSideTailAnimation;
    private float clientSideTailAnimationO;
    private float clientSideTailAnimationSpeed;
    private float clientSideSpikesAnimation;
    private float clientSideSpikesAnimationO;
    private EntityLivingBase targetedEntity;
    private int clientSideAttackTime;
    private boolean clientSideTouchedGround;
    private EntityAIWander wander;

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
        this.dataManager.register(STATUS, Byte.valueOf((byte)0));
        this.dataManager.register(TARGET_ENTITY, Integer.valueOf(0));
    }

    private boolean isSyncedFlagSet(int flagId)
    {
        return (this.dataManager.get(STATUS).byteValue() & flagId) != 0;
    }

    private void setSyncedFlag(int flagId, boolean state)
    {
        byte b0 = this.dataManager.get(STATUS).byteValue();

        if (state)
        {
            this.dataManager.set(STATUS, Byte.valueOf((byte)(b0 | flagId)));
        }
        else
        {
            this.dataManager.set(STATUS, Byte.valueOf((byte)(b0 & ~flagId)));
        }
    }

    @Override
    public boolean isMoving()
    {
        return this.isSyncedFlagSet(2);
    }

    private void setMoving(boolean moving)
    {
        this.setSyncedFlag(2, moving);
    }

    @Override
    public int getAttackDuration()
    {
        return this.isElder() ? 60 : 80;
    }

    @Override
    public boolean isElder()
    {
        return this.isSyncedFlagSet(4);
    }

    @Override
    public void setElder(boolean elder)
    {
        this.setSyncedFlag(4, elder);

        if (elder)
        {
            this.setSize(1.9975F, 1.9975F);
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
            this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
            this.enablePersistence();
            this.wander.setExecutionChance(400);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setElder()
    {
        this.setElder(true);
        this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation = 1.0F;
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
        else if (this.worldObj.isRemote)
        {
            if (this.targetedEntity != null)
            {
                return this.targetedEntity;
            }
            else
            {
                Entity entity = this.worldObj.getEntityByID(this.dataManager.get(TARGET_ENTITY).intValue());

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

        if (STATUS.equals(key))
        {
            if (this.isElder() && this.width < 1.0F)
            {
                this.setSize(1.9975F, 1.9975F);
            }
        }
        else if (TARGET_ENTITY.equals(key))
        {
            this.clientSideAttackTime = 0;
            this.targetedEntity = null;
        }
    }

    @Override
    public float getBlockPathWeight(BlockPos pos)
    {
        return this.worldObj.getBlockState(pos).getBlock() == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK ? 10.0F + this.worldObj.getLightBrightness(pos) - 0.5F : 0.5F - this.worldObj.getLightBrightness(pos);
    }

    @Override
    public void onLivingUpdate()
    {
        if (this.worldObj.isRemote)
        {
            this.clientSideTailAnimationO = this.clientSideTailAnimation;

            if (!this.isInWater())
            {
                this.clientSideTailAnimationSpeed = 2.0F;

                if (this.motionY > 0.0D && this.clientSideTouchedGround && !this.isSilent())
                {
                    this.worldObj.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GUARDIAN_FLOP, this.getSoundCategory(), 1.0F, 1.0F, false);
                }
                this.clientSideTouchedGround = this.motionY < 0.0D && this.worldObj.isBlockNormalCube(new BlockPos(this).down(), false);
            }
            else if (this.isMoving())
            {
                if (this.clientSideTailAnimationSpeed < 0.5F)
                {
                    this.clientSideTailAnimationSpeed = 4.0F;
                }
                else
                {
                    this.clientSideTailAnimationSpeed += (0.5F - this.clientSideTailAnimationSpeed) * 0.1F;
                }
            }
            else
            {
                this.clientSideTailAnimationSpeed += (0.125F - this.clientSideTailAnimationSpeed) * 0.2F;
            }

            this.clientSideTailAnimation += this.clientSideTailAnimationSpeed;
            this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation;

            if (!this.isInWater())
            {
                this.clientSideSpikesAnimation = this.rand.nextFloat();
            }
            else if (this.isMoving())
            {
                this.clientSideSpikesAnimation += (0.0F - this.clientSideSpikesAnimation) * 0.25F;
            }
            else
            {
                this.clientSideSpikesAnimation += (1.0F - this.clientSideSpikesAnimation) * 0.06F;
            }

            if (this.isMoving() && this.isInWater())
            {
                Vec3d vec3 = this.getLook(0.0F);

                for (int i = 0; i < 2; ++i)
                {
                    this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width - vec3.xCoord * 1.5D, this.posY + this.rand.nextDouble() * this.height - vec3.yCoord * 1.5D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width - vec3.zCoord * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }

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
                        this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + d0 * d4, this.posY + d1 * d4 + this.getEyeHeight(), this.posZ + d2 * d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    }
                }
            }
        }

        if (this.inWater)
        {
            this.setAir(300);
        }
        else if (this.onGround)
        {
            this.motionY += 0.5D;
            this.motionX += (this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F;
            this.motionZ += (this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F;
            this.rotationYaw = this.rand.nextFloat() * 360.0F;
            this.onGround = false;
            this.isAirBorne = true;
        }

        if (this.hasTargetedEntity())
        {
            this.rotationYaw = this.rotationYawHead;
        }
        super.onLivingUpdate();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getTailAnimation(float partialTicks)
    {
        return this.clientSideTailAnimationO + (this.clientSideTailAnimation - this.clientSideTailAnimationO) * partialTicks;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSpikesAnimation(float partialTicks)
    {
        return this.clientSideSpikesAnimationO + (this.clientSideSpikesAnimation - this.clientSideSpikesAnimationO) * partialTicks;
    }

    @Override
    public float getAttackAnimationScale(float partialTicks)
    {
        return (this.clientSideAttackTime + partialTicks) / this.getAttackDuration();
    }

    @Override
    protected void updateAITasks()
    {
        if (this.isElder())
        {
            if ((this.ticksExisted + this.getEntityId()) % 1200 == 0)
            {
                Potion potion = MobEffects.MINING_FATIGUE;

                for (EntityPlayerMP entityplayermp : this.worldObj.getPlayers(EntityPlayerMP.class, entity -> EntityInfectedGuardian.this.getDistanceSqToEntity(entity) < 2500.0D && entity.interactionManager.survivalOrAdventure()))
                {
                    if (!entityplayermp.isPotionActive(potion) || entityplayermp.getActivePotionEffect(potion).getAmplifier() < 2 || entityplayermp.getActivePotionEffect(potion).getDuration() < 1200)
                    {
                        MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.INFECTED_GUARDIAN_APPEARANCE, entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ);
                        entityplayermp.worldObj.playSound(entityplayermp, entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.HOSTILE, 1.0F, 1.0F);
                        entityplayermp.addPotionEffect(new PotionEffect(potion, 6000, 2));

                        if (!entityplayermp.capabilities.isCreativeMode && !entityplayermp.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION))
                        {
                            entityplayermp.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80, 2));
                        }
                    }
                }
            }

            if (!this.hasHome())
            {
                this.setHomePosAndDistance(new BlockPos(this), 16);
            }
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return this.isElder() ? MPLootTables.INFECTED_ELDER_GUARDIAN : MPLootTables.INFECTED_GUARDIAN;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        boolean defaults = this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F;
        return (this.rand.nextInt(20) == 0 || !this.worldObj.canBlockSeeSky(new BlockPos(this))) && defaults;
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
            return super.continueExecuting() && (this.entity.isElder() || this.entity.getDistanceSqToEntity(this.entity.getAttackTarget()) > 9.0D);
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
                    this.entity.worldObj.setEntityState(this.entity, (byte)21);
                }
                else if (this.tickCounter >= this.entity.getAttackDuration())
                {
                    float f = 1.0F;

                    if (this.entity.worldObj.getDifficulty() == EnumDifficulty.HARD)
                    {
                        f += 2.0F;
                    }

                    if (this.entity.isElder())
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
                d3 = MathHelper.sqrt_double(d3);
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