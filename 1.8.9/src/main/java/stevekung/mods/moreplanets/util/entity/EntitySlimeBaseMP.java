package stevekung.mods.moreplanets.util.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.util.entity.ai.PathNavigateGroundMP;

public abstract class EntitySlimeBaseMP extends EntityLiving implements IMob, IEntityBreathable
{
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    public boolean wasOnGround;

    public EntitySlimeBaseMP(World world)
    {
        super(world);
        this.moveHelper = new SlimeMoveHelper(this);
        this.tasks.addTask(1, new AISlimeFloat(this));
        this.tasks.addTask(2, new AISlimeAttack(this));
        this.tasks.addTask(3, new AISlimeFaceRandom(this));
        this.tasks.addTask(5, new AISlimeHop(this));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(3, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
    }

    @Override
    protected PathNavigate getNewNavigator(World world)
    {
        return new PathNavigateGroundMP(this, world);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)1));
    }

    public void setSlimeSize(int size)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)size));
        float slimeSize = this.getSizeBased() * size;
        this.setSize(slimeSize, slimeSize);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.overrideHealth();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2F + 0.1F * size);
        this.setHealth(this.getMaxHealth());
        this.experienceValue = size;
    }

    public int getSlimeSize()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("Size", this.getSlimeSize() - 1);
        nbt.setBoolean("wasOnGround", this.wasOnGround);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        int i = tagCompund.getInteger("Size");

        if (i < 0)
        {
            i = 0;
        }
        this.setSlimeSize(i + 1);
        this.wasOnGround = tagCompund.getBoolean("wasOnGround");
    }

    protected String getJumpSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    @Override
    public void onUpdate()
    {
        if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0)
        {
            this.isDead = true;
        }

        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        super.onUpdate();

        if (this.onGround && !this.wasOnGround)
        {
            this.createParticles();

            if (this.makesSoundOnLand())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }
            this.squishAmount = -0.5F;
        }
        else if (!this.onGround && this.wasOnGround)
        {
            this.squishAmount = 1.0F;
        }
        this.wasOnGround = this.onGround;
        this.alterSquishAmount();
    }

    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.6F;
    }

    @Override
    public void onDataWatcherUpdate(int dataID)
    {
        if (dataID == 16)
        {
            float i = this.getSizeBased() * this.getSlimeSize();
            this.setSize(i, i);
            this.rotationYaw = this.rotationYawHead;
            this.renderYawOffset = this.rotationYawHead;

            if (this.isInWater() && this.rand.nextInt(20) == 0)
            {
                this.resetHeight();
            }
        }
        super.onDataWatcherUpdate(dataID);
    }

    @Override
    public void setDead()
    {
        int i = this.getSlimeSize();

        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
        {
            int j = 2 + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                float f = (k % 2 - 0.5F) * i / 4.0F;
                float f1 = (k / 2 - 0.5F) * i / 4.0F;
                EntitySlimeBaseMP entityslime = this.createInstance();

                if (this.hasCustomName())
                {
                    entityslime.setCustomNameTag(this.getCustomNameTag());
                }
                if (this.isNoDespawnRequired())
                {
                    entityslime.enablePersistence();
                }
                entityslime.setSlimeSize(i / 2);
                entityslime.setLocationAndAngles(this.posX + f, this.posY + 0.5D, this.posZ + f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(entityslime);
            }
        }
        super.setDead();
    }

    @Override
    public void applyEntityCollision(Entity entity)
    {
        super.applyEntityCollision(entity);

        if (entity instanceof EntityIronGolem && this.canDamagePlayer())
        {
            this.dealDamage((EntityLivingBase)entity);
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entity)
    {
        if (this.canDamagePlayer())
        {
            this.dealDamage(entity);
        }
    }

    protected void dealDamage(EntityLivingBase entity)
    {
        int i = this.getSlimeSize();

        if (this.canEntityBeSeen(entity) && this.getDistanceSqToEntity(entity) < this.getDetectRange() * i * this.getDetectRange() * i && entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength()))
        {
            this.applyEnchantments(this, entity);
        }
    }

    @Override
    public float getEyeHeight()
    {
        return 0.625F * this.height;
    }

    protected boolean canDamagePlayer()
    {
        return this.getSlimeSize() > 1 && !this.isAIDisabled();
    }

    protected int getAttackStrength()
    {
        return this.getSlimeSize();
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F * this.getSlimeSize();
    }

    @Override
    public int getVerticalFaceSpeed()
    {
        return 0;
    }

    protected boolean makesSoundOnJump()
    {
        return this.getSlimeSize() > 0;
    }

    protected boolean makesSoundOnLand()
    {
        return this.getSlimeSize() > 2;
    }

    @Override
    protected void jump()
    {
        this.motionY = 0.41999998688697815D;
        this.isAirBorne = true;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
    {
        int i = this.rand.nextInt(3);

        if (i < 2 && this.rand.nextFloat() < 0.5F * difficulty.getClampedAdditionalDifficulty())
        {
            ++i;
        }
        int j = 1 << i;
        this.setSlimeSize(j);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    protected float getSizeBased()
    {
        return 0.51000005F;
    }

    protected abstract double getDetectRange();
    public abstract int getJumpDelay();
    protected abstract EntitySlimeBaseMP createInstance();
    protected abstract void createParticles();
    protected abstract void overrideHealth();

    static class AISlimeAttack extends EntityAIBase
    {
        private EntitySlimeBaseMP slime;
        private int field_179465_b;

        public AISlimeAttack(EntitySlimeBaseMP p_i45824_1_)
        {
            this.slime = p_i45824_1_;
            this.setMutexBits(2);
        }

        @Override
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.slime.getAttackTarget();
            return entitylivingbase == null ? false : !entitylivingbase.isEntityAlive() ? false : !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer)entitylivingbase).capabilities.disableDamage;
        }

        @Override
        public void startExecuting()
        {
            this.field_179465_b = 300;
            super.startExecuting();
        }

        @Override
        public boolean continueExecuting()
        {
            EntityLivingBase entitylivingbase = this.slime.getAttackTarget();
            return entitylivingbase == null ? false : !entitylivingbase.isEntityAlive() ? false : entitylivingbase instanceof EntityPlayer && ((EntityPlayer)entitylivingbase).capabilities.disableDamage ? false : --this.field_179465_b > 0;
        }

        @Override
        public void updateTask()
        {
            this.slime.faceEntity(this.slime.getAttackTarget(), 10.0F, 10.0F);
            ((EntitySlimeBaseMP.SlimeMoveHelper)this.slime.getMoveHelper()).func_179920_a(this.slime.rotationYaw, this.slime.canDamagePlayer());
        }
    }

    static class AISlimeFaceRandom extends EntityAIBase
    {
        private EntitySlimeBaseMP slime;
        private float field_179459_b;
        private int field_179460_c;

        public AISlimeFaceRandom(EntitySlimeBaseMP p_i45820_1_)
        {
            this.slime = p_i45820_1_;
            this.setMutexBits(2);
        }

        @Override
        public boolean shouldExecute()
        {
            return this.slime.getAttackTarget() == null && (this.slime.onGround || this.slime.isInWater() || this.slime.isInLava());
        }

        @Override
        public void updateTask()
        {
            if (--this.field_179460_c <= 0)
            {
                this.field_179460_c = 40 + this.slime.getRNG().nextInt(60);
                this.field_179459_b = this.slime.getRNG().nextInt(360);
            }
            ((EntitySlimeBaseMP.SlimeMoveHelper)this.slime.getMoveHelper()).func_179920_a(this.field_179459_b, false);
        }
    }

    static class AISlimeFloat extends EntityAIBase
    {
        private EntitySlimeBaseMP slime;

        public AISlimeFloat(EntitySlimeBaseMP slime)
        {
            this.slime = slime;
            this.setMutexBits(5);
            ((PathNavigateGroundMP)slime.getNavigator()).setCanSwim(true);
        }

        @Override
        public boolean shouldExecute()
        {
            return this.slime.isInWater() || this.slime.isInLava();
        }

        @Override
        public void updateTask()
        {
            if (this.slime.getRNG().nextFloat() < 0.8F)
            {
                this.slime.getJumpHelper().setJumping();
            }
            ((SlimeMoveHelper)this.slime.getMoveHelper()).setSpeed(1.2D);
        }
    }

    static class AISlimeHop extends EntityAIBase
    {
        private EntitySlimeBaseMP slime;

        public AISlimeHop(EntitySlimeBaseMP slime)
        {
            this.slime = slime;
            this.setMutexBits(5);
        }

        @Override
        public boolean shouldExecute()
        {
            return true;
        }

        @Override
        public void updateTask()
        {
            ((SlimeMoveHelper)this.slime.getMoveHelper()).setSpeed(1.0D);
        }
    }

    static class SlimeMoveHelper extends EntityMoveHelper
    {
        private float field_179922_g;
        private int field_179924_h;
        private EntitySlimeBaseMP slime;
        private boolean field_179923_j;

        public SlimeMoveHelper(EntitySlimeBaseMP slime)
        {
            super(slime);
            this.slime = slime;
        }

        public void func_179920_a(float p_179920_1_, boolean p_179920_2_)
        {
            this.field_179922_g = p_179920_1_;
            this.field_179923_j = p_179920_2_;
        }

        public void setSpeed(double speedIn)
        {
            this.speed = speedIn;
            this.update = true;
        }

        @Override
        public void onUpdateMoveHelper()
        {
            this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.field_179922_g, 30.0F);
            this.entity.rotationYawHead = this.entity.rotationYaw;
            this.entity.renderYawOffset = this.entity.rotationYaw;

            if (!this.update)
            {
                this.entity.setMoveForward(0.0F);
            }
            else
            {
                this.update = false;

                if (this.entity.onGround)
                {
                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));

                    if (this.field_179924_h-- <= 0)
                    {
                        this.field_179924_h = this.slime.getJumpDelay();

                        if (this.field_179923_j)
                        {
                            this.field_179924_h /= 3;
                        }

                        this.slime.getJumpHelper().setJumping();

                        if (this.slime.makesSoundOnJump())
                        {
                            this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), ((this.slime.getRNG().nextFloat() - this.slime.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                        }
                    }
                    else
                    {
                        this.slime.moveStrafing = this.slime.moveForward = 0.0F;
                        this.entity.setAIMoveSpeed(0.0F);
                    }
                }
                else
                {
                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
                }
            }
        }
    }
}