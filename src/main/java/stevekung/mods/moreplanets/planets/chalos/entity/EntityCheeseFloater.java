package stevekung.mods.moreplanets.planets.chalos.entity;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.planets.chalos.entity.projectile.EntitySmallCheeseSpore;

public class EntityCheeseFloater extends EntityMob implements IEntityBreathable
{
    private static final DataParameter<Boolean> MINION = EntityDataManager.createKey(EntityCheeseFloater.class, DataSerializers.BOOLEAN);
    private float heightOffset = 0.25F;
    private int heightOffsetUpdateTime;

    public EntityCheeseFloater(World world)
    {
        super(world);
        this.setSize(0.8F, 2.0F);
        this.experienceValue = 10;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(4, new AICheeseSporeAttack(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D, 0.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.checkNoEntityCollision(this.getEntityBoundingBox()) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox()) && this.world.getLightBrightness(this.getPosition()) >= 0.0F;
    }

    @Override
    public float getEyeHeight()
    {
        return 1.25F;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(EntityCheeseFloater.MINION, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);

        if (this.isMinion())
        {
            nbt.setBoolean("Minion", true);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);

        if (nbt.getBoolean("Minion"))
        {
            this.setMinion(true);
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SLIME_DEATH;
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

        EntityLivingBase entity = this.getAttackTarget();

        if (entity != null && entity.posY + entity.getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset)
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
            this.motionY *= 0.35D;
        }
        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (!(damageSource.getImmediateSource() instanceof EntitySmallCheeseSpore))
        {
            if (!this.world.isRemote && this.world instanceof WorldServer)
            {
                for (int i = 0; i < 8; i++)
                {
                    if (this.getHealth() > 0.0F)
                    {
                        ((WorldServer)this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY + 1.5D, this.posZ, 10, this.width / 4.0F, 0.0D, this.width / 4.0F, 0.05D, new int[] {Block.getStateId(MPBlocks.CHEESE_SLIME_BLOCK.getDefaultState())});
                    }
                }
            }

            if (this.isEntityInvulnerable(damageSource))
            {
                return false;
            }
            else if (super.attackEntityFrom(damageSource, damage))
            {
                Entity entity = damageSource.getTrueSource();

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
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.CHEESE_FLOATER;
    }

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

    public void setMinion(boolean isMinion)
    {
        this.dataManager.set(EntityCheeseFloater.MINION, isMinion);
    }

    public boolean isMinion()
    {
        return this.dataManager.get(EntityCheeseFloater.MINION);
    }

    class AICheeseSporeAttack extends EntityAIBase
    {
        private final EntityCheeseFloater entity;
        private int attackStep;
        private int attackTime;

        public AICheeseSporeAttack(EntityCheeseFloater entity)
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
        public void startExecuting()
        {
            this.attackStep = 0;
        }

        @Override
        public void resetTask() {}

        @Override
        public void updateTask()
        {
            --this.attackTime;
            EntityLivingBase entity = this.entity.getAttackTarget();
            double distance = this.entity.getDistanceSq(entity);

            if (distance < 1.5D)
            {
                if (this.attackTime <= 0)
                {
                    this.attackTime = 20;
                    this.entity.attackEntityAsMob(entity);
                }
                this.entity.getMoveHelper().setMoveTo(entity.posX, entity.posY, entity.posZ, 1.0D);
            }
            else if (distance < 30.0D)
            {
                double d1 = entity.posX - this.entity.posX;
                double d2 = entity.getEntityBoundingBox().minY + entity.height / 2.0F - (this.entity.posY + this.entity.height / 2.0F);
                double d3 = entity.posZ - this.entity.posZ;

                if (this.attackTime <= 0)
                {
                    ++this.attackStep;

                    if (this.attackStep == 1)
                    {
                        this.attackTime = 35;
                    }
                    else if (this.attackStep <= 6)
                    {
                        this.attackTime = 5;
                    }
                    else
                    {
                        this.attackTime = 50;
                        this.attackStep = 0;
                    }

                    if (this.attackStep > 1)
                    {
                        float f = MathHelper.sqrt(MathHelper.sqrt(distance)) * 0.5F;

                        for (int i = 0; i < 1; ++i)
                        {
                            EntitySmallCheeseSpore cheeseSpore = new EntitySmallCheeseSpore(this.entity.world, this.entity, d1 + this.entity.getRNG().nextGaussian() * f, d2, d3 + this.entity.getRNG().nextGaussian() * f);
                            cheeseSpore.posY = this.entity.posY + this.entity.height / 2.0F + 0.5D;
                            this.entity.world.playSound(null, this.entity.getPosition(), SoundEvents.ENTITY_SLIME_JUMP, SoundCategory.HOSTILE, 1.0F, 2.0F);
                            this.entity.world.spawnEntity(cheeseSpore);
                        }
                    }
                }
                this.entity.getLookHelper().setLookPositionWithEntity(entity, 10.0F, 10.0F);
            }
            else
            {
                this.entity.getNavigator().clearPath();
                this.entity.getMoveHelper().setMoveTo(entity.posX, entity.posY, entity.posZ, 1.0D);
            }
            super.updateTask();
        }
    }
}