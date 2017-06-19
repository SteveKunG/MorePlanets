package stevekung.mods.moreplanets.module.planets.chalos.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.entity.projectile.EntitySmallCheeseSpore;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;

public class EntityCheeseFloater extends EntityMob implements IEntityBreathable
{
    private float heightOffset = 0.25F;
    private int heightOffsetUpdateTime;

    public EntityCheeseFloater(World world)
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
    public boolean getCanSpawnHere()
    {
        return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox()) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox()) && this.worldObj.getLightBrightness(this.getPosition()) >= 0.0F;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.getDataWatcher().addObject(16, Byte.valueOf((byte) 0));
    }

    public void setMinion(boolean isMinion)
    {
        this.getDataWatcher().updateObject(16, Byte.valueOf((byte) (isMinion ? 1 : 0)));
    }

    public boolean isMinion()
    {
        return this.getDataWatcher().getWatchableObjectByte(16) == 1;
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
    protected String getHurtSound()
    {
        return "mob.slime.big";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.slime.big";
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
            this.motionY *= 0.35D;
        }
        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (!damageSource.getDamageType().equals("mob") && !damageSource.getDamageType().equals("fireball"))
        {
            if (!this.worldObj.isRemote)
            {
                if (this.worldObj instanceof WorldServer)
                {
                    for (int i = 0; i < 8; i++)
                    {
                        if (this.getHealth() > 0.0F)
                        {
                            ((WorldServer)this.worldObj).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY + 1.5D, this.posZ, 10, this.width / 4.0F, 0.0D, this.width / 4.0F, 0.05D, new int[] {Block.getStateId(ChalosBlocks.CHEESE_SLIME_BLOCK.getDefaultState())});
                        }
                    }
                }
            }

            if (this.isEntityInvulnerable(damageSource))
            {
                return false;
            }
            else if (super.attackEntityFrom(damageSource, damage))
            {
                Entity entity = damageSource.getEntity();

                if (this.riddenByEntity != entity && this.ridingEntity != entity)
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
    protected void dropFewItems(boolean drop, int fortune)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + fortune);

        for (int i = 0; i < j; ++i)
        {
            this.entityDropItem(new ItemStack(ChalosItems.CHEESE_SLIMEBALL, 1), 0.0F);
        }
        for (int i = 0; i < this.rand.nextInt(3); ++i)
        {
            this.entityDropItem(new ItemStack(ChalosItems.CHEESE_FOOD, 1), 0.0F);
        }
        if (this.rand.nextInt(10) == 0)
        {
            this.entityDropItem(new ItemStack(ChalosBlocks.CHEESE_SLIME_BLOCK, 1), 0.0F);
        }
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

    class AIFireballAttack extends EntityAIBase
    {
        private EntityCheeseFloater blaze;
        private int field_179467_b;
        private int field_179468_c;

        public AIFireballAttack(EntityCheeseFloater p_i45846_1_)
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
                        float f = MathHelper.sqrt_float(MathHelper.sqrt_double(d0)) * 0.5F;

                        for (int i = 0; i < 1; ++i)
                        {
                            EntitySmallCheeseSpore entitysmallfireball = new EntitySmallCheeseSpore(this.blaze.worldObj, this.blaze, d1 + this.blaze.getRNG().nextGaussian() * f, d2, d3 + this.blaze.getRNG().nextGaussian() * f);
                            entitysmallfireball.posY = this.blaze.posY + this.blaze.height / 2.0F + 0.5D;
                            this.blaze.worldObj.spawnEntityInWorld(entitysmallfireball);
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