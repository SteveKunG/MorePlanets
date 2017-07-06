package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import com.google.common.base.Predicate;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;

public class EntityInfectedGuardian extends EntityGuardian implements ISpaceMob, IEntityBreathable
{
    private float field_175482_b;
    private float field_175484_c;
    private float field_175483_bk;
    private float field_175485_bl;
    private float field_175486_bm;
    private EntityLivingBase targetedEntity;
    private int field_175479_bo;
    private boolean field_175480_bp;
    private EntityAIWander wander;

    public EntityInfectedGuardian(World world)
    {
        super(world);
        this.tasks.taskEntries.clear();
        this.targetTasks.taskEntries.clear();
        this.experienceValue = 10;
        this.setSize(0.85F, 0.85F);
        this.tasks.addTask(4, new EntityInfectedGuardian.AIGuardianAttack(this));
        EntityAIMoveTowardsRestriction entityaimovetowardsrestriction;
        this.tasks.addTask(5, entityaimovetowardsrestriction = new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, this.wander = new EntityAIWander(this, 1.0D, 80));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityInfectedGuardian.class, 12.0F, 0.01F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.wander.setMutexBits(3);
        entityaimovetowardsrestriction.setMutexBits(3);
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false, new EntityInfectedGuardian.GuardianTargetSelector(this)));
        this.moveHelper = new EntityInfectedGuardian.GuardianMoveHelper(this);
        this.field_175484_c = this.field_175482_b = this.rand.nextFloat();
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotionID() == MPPotions.INFECTED_SPORE.id ? false : super.isPotionApplicable(potion);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
    }

    private boolean isSyncedFlagSet(int flagId)
    {
        return (this.dataWatcher.getWatchableObjectInt(16) & flagId) != 0;
    }

    private void setSyncedFlag(int flagId, boolean state)
    {
        int i = this.dataWatcher.getWatchableObjectInt(16);

        if (state)
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(i | flagId));
        }
        else
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(i & ~flagId));
        }
    }

    @Override
    public boolean func_175472_n()
    {
        return this.isSyncedFlagSet(2);
    }

    private void func_175476_l(boolean p_175476_1_)
    {
        this.setSyncedFlag(2, p_175476_1_);
    }

    @Override
    public int func_175464_ck()
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
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
            this.enablePersistence();
            this.wander.setExecutionChance(400);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setElder()
    {
        this.setElder(true);
        this.field_175486_bm = this.field_175485_bl = 1.0F;
    }

    private void setTargetedEntity(int entityId)
    {
        this.dataWatcher.updateObject(17, Integer.valueOf(entityId));
    }

    @Override
    public boolean hasTargetedEntity()
    {
        return this.dataWatcher.getWatchableObjectInt(17) != 0;
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
                Entity entity = this.worldObj.getEntityByID(this.dataWatcher.getWatchableObjectInt(17));

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
    public void onDataWatcherUpdate(int dataID)
    {
        super.onDataWatcherUpdate(dataID);

        if (dataID == 16)
        {
            if (this.isElder() && this.width < 1.0F)
            {
                this.setSize(1.9975F, 1.9975F);
            }
        }
        else if (dataID == 17)
        {
            this.field_175479_bo = 0;
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
            this.field_175484_c = this.field_175482_b;

            if (!this.isInWater())
            {
                this.field_175483_bk = 2.0F;

                if (this.motionY > 0.0D && this.field_175480_bp && !this.isSilent())
                {
                    this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.guardian.flop", 1.0F, 1.0F, false);
                }
                this.field_175480_bp = this.motionY < 0.0D && this.worldObj.isBlockNormalCube(new BlockPos(this).down(), false);
            }
            else if (this.func_175472_n())
            {
                if (this.field_175483_bk < 0.5F)
                {
                    this.field_175483_bk = 4.0F;
                }
                else
                {
                    this.field_175483_bk += (0.5F - this.field_175483_bk) * 0.1F;
                }
            }
            else
            {
                this.field_175483_bk += (0.125F - this.field_175483_bk) * 0.2F;
            }

            this.field_175482_b += this.field_175483_bk;
            this.field_175486_bm = this.field_175485_bl;

            if (!this.isInWater())
            {
                this.field_175485_bl = this.rand.nextFloat();
            }
            else if (this.func_175472_n())
            {
                this.field_175485_bl += (0.0F - this.field_175485_bl) * 0.25F;
            }
            else
            {
                this.field_175485_bl += (1.0F - this.field_175485_bl) * 0.06F;
            }

            if (this.func_175472_n() && this.isInWater())
            {
                Vec3 vec3 = this.getLook(0.0F);

                for (int i = 0; i < 2; ++i)
                {
                    this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width - vec3.xCoord * 1.5D, this.posY + this.rand.nextDouble() * this.height - vec3.yCoord * 1.5D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width - vec3.zCoord * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }

            if (this.hasTargetedEntity())
            {
                if (this.field_175479_bo < this.func_175464_ck())
                {
                    ++this.field_175479_bo;
                }

                EntityLivingBase entitylivingbase = this.getTargetedEntity();

                if (entitylivingbase != null)
                {
                    this.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);
                    this.getLookHelper().onUpdateLook();
                    double d5 = this.func_175477_p(0.0F);
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
    public float func_175471_a(float p_175471_1_)
    {
        return this.field_175484_c + (this.field_175482_b - this.field_175484_c) * p_175471_1_;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float func_175469_o(float p_175469_1_)
    {
        return this.field_175486_bm + (this.field_175485_bl - this.field_175486_bm) * p_175469_1_;
    }

    @Override
    public float func_175477_p(float p_175477_1_)
    {
        return (this.field_175479_bo + p_175477_1_) / this.func_175464_ck();
    }

    @Override
    protected void updateAITasks()
    {
        if (this.isElder())
        {
            if ((this.ticksExisted + this.getEntityId()) % 1200 == 0)
            {
                Potion potion = Potion.digSlowdown;

                for (EntityPlayerMP entityplayermp : this.worldObj.getPlayers(EntityPlayerMP.class, p_apply_1_ -> EntityInfectedGuardian.this.getDistanceSqToEntity(p_apply_1_) < 2500.0D && p_apply_1_.theItemInWorldManager.survivalOrAdventure()))
                {
                    if (!entityplayermp.isPotionActive(potion) || entityplayermp.getActivePotionEffect(potion).getAmplifier() < 2 || entityplayermp.getActivePotionEffect(potion).getDuration() < 1200)
                    {
                        MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.INFECTED_GUARDIAN_APPEARANCE, entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ);
                        entityplayermp.worldObj.playSound(entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, "mob.guardian.curse", 1.0F, 1.0F, false);
                        entityplayermp.addPotionEffect(new PotionEffect(potion.id, 6000, 2));

                        if (!entityplayermp.capabilities.isCreativeMode && !entityplayermp.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION))
                        {
                            entityplayermp.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 80, 2));
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
    protected void dropFewItems(boolean drop, int fortune)
    {
        int i = this.rand.nextInt(3) + this.rand.nextInt(fortune + 1);

        if (i > 0)
        {
            this.entityDropItem(new ItemStack(NibiruItems.INFECTED_PRISMARINE, i, 0), 1.0F);
        }

        if (this.rand.nextInt(3 + fortune) > 1)
        {
            this.entityDropItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.getMetadata()), 1.0F);
        }
        else if (this.rand.nextInt(3 + fortune) > 1)
        {
            this.entityDropItem(new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 1), 1.0F);
        }

        if (drop && this.isElder())
        {
            this.entityDropItem(new ItemStack(NibiruBlocks.INFECTED_SPONGE, 1, 1), 1.0F);
        }
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
        if (!this.func_175472_n() && !source.isMagicDamage() && source.getSourceOfDamage() instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)source.getSourceOfDamage();

            if (!source.isExplosion())
            {
                entitylivingbase.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.0F);
                entitylivingbase.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 80, 0));
            }
        }
        this.wander.makeUpdate();
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
        private EntityInfectedGuardian theEntity;
        private int tickCounter;

        public AIGuardianAttack(EntityInfectedGuardian p_i45833_1_)
        {
            this.theEntity = p_i45833_1_;
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.theEntity.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        @Override
        public boolean continueExecuting()
        {
            return super.continueExecuting() && (this.theEntity.isElder() || this.theEntity.getDistanceSqToEntity(this.theEntity.getAttackTarget()) > 9.0D);
        }

        @Override
        public void startExecuting()
        {
            this.tickCounter = -10;
            this.theEntity.getNavigator().clearPathEntity();
            this.theEntity.getLookHelper().setLookPositionWithEntity(this.theEntity.getAttackTarget(), 90.0F, 90.0F);
            this.theEntity.isAirBorne = true;
        }

        @Override
        public void resetTask()
        {
            this.theEntity.setTargetedEntity(0);
            this.theEntity.setAttackTarget((EntityLivingBase)null);
            this.theEntity.wander.makeUpdate();
        }

        @Override
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.theEntity.getAttackTarget();
            this.theEntity.getNavigator().clearPathEntity();
            this.theEntity.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);

            if (!this.theEntity.canEntityBeSeen(entitylivingbase))
            {
                this.theEntity.setAttackTarget((EntityLivingBase)null);
            }
            else
            {
                ++this.tickCounter;

                if (this.tickCounter == 0)
                {
                    this.theEntity.setTargetedEntity(this.theEntity.getAttackTarget().getEntityId());
                    this.theEntity.worldObj.setEntityState(this.theEntity, (byte)21);
                }
                else if (this.tickCounter >= this.theEntity.func_175464_ck())
                {
                    float f = 1.0F;

                    if (this.theEntity.worldObj.getDifficulty() == EnumDifficulty.HARD)
                    {
                        f += 2.0F;
                    }

                    if (this.theEntity.isElder())
                    {
                        f += 2.0F;
                    }
                    entitylivingbase.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.theEntity, this.theEntity), f);
                    entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.theEntity), (float)this.theEntity.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
                    entitylivingbase.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 80, 0));
                    this.theEntity.setAttackTarget((EntityLivingBase)null);
                }
                super.updateTask();
            }
        }
    }

    static class GuardianMoveHelper extends EntityMoveHelper
    {
        private EntityInfectedGuardian entityGuardian;

        public GuardianMoveHelper(EntityInfectedGuardian p_i45831_1_)
        {
            super(p_i45831_1_);
            this.entityGuardian = p_i45831_1_;
        }

        @Override
        public void onUpdateMoveHelper()
        {
            if (this.update && !this.entityGuardian.getNavigator().noPath())
            {
                double d0 = this.posX - this.entityGuardian.posX;
                double d1 = this.posY - this.entityGuardian.posY;
                double d2 = this.posZ - this.entityGuardian.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = MathHelper.sqrt_double(d3);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
                this.entityGuardian.rotationYaw = this.limitAngle(this.entityGuardian.rotationYaw, f, 30.0F);
                this.entityGuardian.renderYawOffset = this.entityGuardian.rotationYaw;
                float f1 = (float)(this.speed * this.entityGuardian.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                this.entityGuardian.setAIMoveSpeed(this.entityGuardian.getAIMoveSpeed() + (f1 - this.entityGuardian.getAIMoveSpeed()) * 0.125F);
                double d4 = Math.sin((this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.5D) * 0.05D;
                double d5 = Math.cos(this.entityGuardian.rotationYaw * (float)Math.PI / 180.0F);
                double d6 = Math.sin(this.entityGuardian.rotationYaw * (float)Math.PI / 180.0F);
                this.entityGuardian.motionX += d4 * d5;
                this.entityGuardian.motionZ += d4 * d6;
                d4 = Math.sin((this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.75D) * 0.05D;
                this.entityGuardian.motionY += d4 * (d6 + d5) * 0.25D;
                this.entityGuardian.motionY += this.entityGuardian.getAIMoveSpeed() * d1 * 0.1D;
                EntityLookHelper entitylookhelper = this.entityGuardian.getLookHelper();
                double d7 = this.entityGuardian.posX + d0 / d3 * 2.0D;
                double d8 = this.entityGuardian.getEyeHeight() + this.entityGuardian.posY + d1 / d3 * 1.0D;
                double d9 = this.entityGuardian.posZ + d2 / d3 * 2.0D;
                double d10 = entitylookhelper.getLookPosX();
                double d11 = entitylookhelper.getLookPosY();
                double d12 = entitylookhelper.getLookPosZ();

                if (!entitylookhelper.getIsLooking())
                {
                    d10 = d7;
                    d11 = d8;
                    d12 = d9;
                }
                this.entityGuardian.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
                this.entityGuardian.func_175476_l(true);
            }
            else
            {
                this.entityGuardian.setAIMoveSpeed(0.0F);
                this.entityGuardian.func_175476_l(false);
            }
        }
    }

    static class GuardianTargetSelector implements Predicate<EntityLivingBase>
    {
        private EntityInfectedGuardian parentEntity;

        public GuardianTargetSelector(EntityInfectedGuardian parent)
        {
            this.parentEntity = parent;
        }

        @Override
        public boolean apply(EntityLivingBase entity)
        {
            return (entity instanceof EntityPlayer || entity instanceof EntityInfectedSquid || entity instanceof EntityAlienMiner) && entity.getDistanceSqToEntity(this.parentEntity) > 9.0D;
        }
    }
}