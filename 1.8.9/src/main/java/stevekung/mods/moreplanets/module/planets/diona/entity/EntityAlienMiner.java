package stevekung.mods.moreplanets.module.planets.diona.entity;

import java.util.Random;

import com.google.common.base.Predicate;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.diona.items.ItemDiona;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;

public class EntityAlienMiner extends EntityMob implements IEntityBreathable, ISpaceMob
{
    private EntityLivingBase targetedEntity;
    private int chargedTime;

    public EntityAlienMiner(World world)
    {
        super(world);
        this.setSize(0.5F, 1.25F);
        this.isImmuneToFire = true;
        this.experienceValue = 10;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new AILaserBeamAttack(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new AIHideFindStone(this));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false, new AlienMinerTargetSelector(this)));
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Override
    public void onDataWatcherUpdate(int dataID)
    {
        super.onDataWatcherUpdate(dataID);

        if (dataID == 16)
        {
            this.chargedTime = 0;
            this.targetedEntity = null;
        }
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
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
    protected String getLivingSound()
    {
        return "moreplanets:mob.alienminer.ambient";
    }

    @Override
    protected String getHurtSound()
    {
        return "moreplanets:mob.alienminer.ambient";
    }

    @Override
    protected String getDeathSound()
    {
        return "moreplanets:mob.alienminer.ambient";
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
        int id = potion.getPotionID();
        return id != Potion.poison.id && id != Potion.harm.id && id != Potion.wither.id && id != MPPotions.INFECTED_CRYSTALLIZE.id && id != MPPotions.INFECTED_SPORE.id;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    public float getChargedTime(float time)
    {
        return (this.chargedTime + time) / 80;
    }

    private void setTargetedEntity(int entityId)
    {
        this.dataWatcher.updateObject(16, Integer.valueOf(entityId));
    }

    public boolean hasTargetedEntity()
    {
        return this.dataWatcher.getWatchableObjectInt(16) != 0;
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        for (int j = 0; j < this.rand.nextInt(3); ++j)
        {
            this.entityDropItem(new ItemStack(DionaItems.DIONA_ITEM, 1, ItemDiona.ItemType.ALIEN_MINER_PART.ordinal()), 0.0F);
        }
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
                Entity entity = this.worldObj.getEntityByID(this.dataWatcher.getWatchableObjectInt(16));

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
        private EntityAlienMiner miner;
        private int tickCounter;

        public AILaserBeamAttack(EntityAlienMiner miner)
        {
            this.miner = miner;
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.miner.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        @Override
        public boolean continueExecuting()
        {
            return super.continueExecuting() && this.miner.getDistanceSqToEntity(this.miner.getAttackTarget()) > 5.0D;
        }

        @Override
        public void startExecuting()
        {
            this.tickCounter = -10;
            this.miner.getNavigator().clearPathEntity();
            this.miner.getLookHelper().setLookPositionWithEntity(this.miner.getAttackTarget(), 90.0F, 90.0F);
            this.miner.isAirBorne = true;
        }

        @Override
        public void resetTask()
        {
            this.miner.setTargetedEntity(0);
            this.miner.setAttackTarget((EntityLivingBase)null);
        }

        @Override
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.miner.getAttackTarget();
            this.miner.getNavigator().clearPathEntity();
            this.miner.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);

            if (!this.miner.canEntityBeSeen(entitylivingbase))
            {
                this.miner.setAttackTarget((EntityLivingBase)null);
            }
            else
            {
                ++this.tickCounter;

                if (this.tickCounter == 0)
                {
                    this.miner.setTargetedEntity(this.miner.getAttackTarget().getEntityId());
                    this.miner.playSound("moreplanets:mob.alienminer.charged", 2.0F + this.miner.getChargedTime(0.0F), 0.8F);
                }
                else if (this.tickCounter >= 80)
                {
                    float f = 1.0F;

                    if (this.miner.worldObj.getDifficulty() == EnumDifficulty.HARD)
                    {
                        f += 2.0F;
                    }
                    entitylivingbase.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.miner, this.miner), f);
                    entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.miner), (float)this.miner.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
                    this.miner.playSound("moreplanets:mob.alienminer.attack", 1.0F + this.miner.getChargedTime(0.0F), 0.8F);
                    entitylivingbase.playSound("moreplanets:mob.alienminer.shock", 1.0F + this.miner.getChargedTime(0.0F), 1.0F);
                    this.miner.setAttackTarget((EntityLivingBase)null);

                    if (entitylivingbase instanceof EntityPlayer)
                    {
                        MorePlanetsCore.PROXY.resetFloatingTick((EntityPlayer) entitylivingbase);
                    }
                }
                super.updateTask();
            }
        }
    }

    static class AIHideFindStone extends EntityAIWander
    {
        private EntityAlienMiner miner;
        private boolean field_179484_c;

        public AIHideFindStone(EntityAlienMiner miner)
        {
            super(miner, 1.0D, 10);
            this.miner = miner;
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute()
        {
            if (this.miner.getAttackTarget() != null)
            {
                return false;
            }
            else if (!this.miner.getNavigator().noPath())
            {
                return false;
            }
            else
            {
                Random random = this.miner.getRNG();

                if (random.nextInt(1000) == 0)
                {
                    BlockPos blockpos = new BlockPos(this.miner.posX, this.miner.posY, this.miner.posZ);
                    IBlockState iblockstate = this.miner.worldObj.getBlockState(blockpos.down());

                    if (iblockstate == DionaBlocks.DIONA_BLOCK.getStateFromMeta(0))
                    {
                        this.field_179484_c = true;
                        return true;
                    }
                }
                this.field_179484_c = false;
                return super.shouldExecute();
            }
        }

        @Override
        public boolean continueExecuting()
        {
            return this.field_179484_c ? false : super.continueExecuting();
        }

        @Override
        public void startExecuting()
        {
            if (!this.field_179484_c)
            {
                super.startExecuting();
            }
            else
            {
                World world = this.miner.worldObj;
                BlockPos blockpos = new BlockPos(this.miner.posX, this.miner.posY, this.miner.posZ);
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
        private EntityAlienMiner miner;

        public AlienMinerTargetSelector(EntityAlienMiner miner)
        {
            this.miner = miner;
        }

        @Override
        public boolean apply(EntityLivingBase entity)
        {
            return !(entity instanceof EntityAlienMiner) && entity.getDistanceSqToEntity(this.miner) > 5.0D;
        }
    }
}