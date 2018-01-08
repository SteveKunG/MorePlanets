package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;
import stevekung.mods.moreplanets.util.helper.EntityEffectHelper;

public class EntityZergius extends EntityFlying implements IMob, IEntityBreathable, ISpaceMob
{
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;

    public EntityZergius(World world)
    {
        super(world);
        this.setSize(0.7F, 0.7F);
        this.experienceValue = 5;
    }

    @Override
    protected void initEntityAI()
    {
        this.moveHelper = new ZergiusMoveHelper();
        this.tasks.addTask(3, new AIZergiusRandomFly());
        this.tasks.addTask(4, new AIZergiusAttackTarget());
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float)(this.destPos + (this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

        if (!this.onGround && this.wingRotDelta < 1.0F)
        {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float)(this.wingRotDelta * 0.9D);

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }
        this.wingRotation += this.wingRotDelta * 2.0F;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.checkNoEntityCollision(this.getEntityBoundingBox()) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox()) && this.world.getLightBrightness(this.getPosition()) <= 1.0F;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.5D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), f) && EntityEffectHelper.addInfectedSpore(entity);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SILVERFISH_DEATH;
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

    private class FlyingMoveTargetPosition
    {
        private EntityZergius entity = EntityZergius.this;

        public double posX;
        public double posY;
        public double posZ;
        public double distX;
        public double distY;
        public double distZ;
        public double dist;
        public double aimX;
        public double aimY;
        public double aimZ;

        public FlyingMoveTargetPosition()
        {
            this(0, 0, 0);
        }

        public FlyingMoveTargetPosition(double posX, double posY, double posZ)
        {
            this.setTarget(posX, posY, posZ);
        }

        public void setTarget(double posX, double posY, double posZ)
        {
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.refresh();
        }

        public void refresh()
        {
            this.distX = this.posX - this.entity.posX;
            this.distY = this.posY - this.entity.posY;
            this.distZ = this.posZ - this.entity.posZ;
            this.dist = MathHelper.sqrt(this.distX * this.distX + this.distY * this.distY + this.distZ * this.distZ);

            if (this.dist == 0.0D)
            {
                this.aimX = 0.0D;
                this.aimY = 0.0D;
                this.aimZ = 0.0D;
            }
            else
            {
                this.aimX = this.distX / this.dist;
                this.aimY = this.distY / this.dist;
                this.aimZ = this.distZ / this.dist;
            }
        }

        public boolean isBoxBlocked(AxisAlignedBB box)
        {
            return !this.entity.world.getCollisionBoxes(this.entity, box).isEmpty();
        }

        public boolean isPathClear(double howFar)
        {
            howFar = Math.min(howFar, this.dist);
            AxisAlignedBB box = this.entity.getEntityBoundingBox();

            for (double i = 0.5D; i < howFar; ++i)
            {
                if (this.isBoxBlocked(box.offset(this.aimX * i, this.aimY * i, this.aimZ * i)))
                {
                    return false;
                }
            }
            if (this.isBoxBlocked(box.offset(this.aimX * howFar, this.aimY * howFar, this.aimZ * howFar)))
            {
                return false;
            }
            return true;
        }
    }

    private class ZergiusMoveHelper extends EntityMoveHelper
    {
        private EntityZergius entity = EntityZergius.this;
        private int courseChangeCooldown = 0;
        private double closeEnough = 0.3D;
        private FlyingMoveTargetPosition targetPos = new FlyingMoveTargetPosition();

        public ZergiusMoveHelper()
        {
            super(EntityZergius.this);
        }

        @Override
        public void setMoveTo(double x, double y, double z, double speed)
        {
            super.setMoveTo(x, y, z, speed);
            this.targetPos.setTarget(x, y, z);
        }

        @Override
        public void onUpdateMoveHelper()
        {
            if (this.action != Action.MOVE_TO)
            {
                return;
            }
            if (this.courseChangeCooldown-- > 0)
            {
                return;
            }

            this.courseChangeCooldown += this.entity.getRNG().nextInt(5) + 2;
            this.targetPos.refresh();
            double acceleration = 0.1D;
            this.entity.motionX += this.targetPos.aimX * acceleration;
            this.entity.motionY += this.targetPos.aimY * acceleration;
            this.entity.motionZ += this.targetPos.aimZ * acceleration;
            this.entity.renderYawOffset = this.entity.rotationYaw = -((float)Math.atan2(this.targetPos.distX, this.targetPos.distZ)) * 180.0F / (float)Math.PI;

            if (this.entity.getRNG().nextInt(2) == 0)
            {
                float strafeAmount = this.entity.getRNG().nextFloat() * 0.4F - 0.2F;
                this.entity.motionX += strafeAmount * MathHelper.cos(this.entity.rotationYaw * (float)Math.PI / 180.0F);
                this.entity.motionZ += strafeAmount * MathHelper.sin(this.entity.rotationYaw * (float)Math.PI / 180.0F);
            }
            if (!this.targetPos.isPathClear(1.0D))
            {
                this.action = Action.WAIT;
            }
            else if (this.targetPos.dist < this.closeEnough)
            {
                this.action = Action.WAIT;
            }
        }
    }

    class AIZergiusRandomFly extends EntityAIBase
    {
        private EntityZergius entity = EntityZergius.this;
        private FlyingMoveTargetPosition targetPos = new FlyingMoveTargetPosition();

        public AIZergiusRandomFly()
        {
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute()
        {
            return !this.entity.getMoveHelper().isUpdating();
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            return false;
        }

        @Override
        public void startExecuting()
        {
            Random rand = this.entity.getRNG();

            if (this.tryGoingRandomDirection(rand, 6.0D))
            {
                return;
            }
            if (this.tryGoingRandomDirection(rand, 2.0D))
            {
                return;
            }

            List<EnumFacing> directions = Arrays.asList(EnumFacing.VALUES);
            Collections.shuffle(directions);

            for (EnumFacing facing : directions)
            {
                if (this.tryGoingAlongAxis(rand, facing, 1.0D)) {return;}
            }
        }

        public boolean tryGoingRandomDirection(Random rand, double maxDistance)
        {
            double dirX = (rand.nextDouble() * 2.0D - 1.0D) * maxDistance;
            double dirY = (rand.nextDouble() * 2.0D - 1.1D) * maxDistance;
            double dirZ = (rand.nextDouble() * 2.0D - 1.0D) * maxDistance;
            return this.tryGoing(dirX, dirY, dirZ);
        }

        public boolean tryGoingAlongAxis(Random rand, EnumFacing facing, double maxDistance)
        {
            double dirX = 0.0D;
            double dirY = 0.0D;
            double dirZ = 0.0D;
            switch (facing.getAxis())
            {
            case X:
                dirX = rand.nextDouble() * facing.getAxisDirection().getOffset() * maxDistance;
                break;
            case Y:
                dirY = rand.nextDouble() * facing.getAxisDirection().getOffset() * maxDistance;
                break;
            case Z: default:
                dirZ = rand.nextDouble() * facing.getAxisDirection().getOffset() * maxDistance;
                break;
            }
            return this.tryGoing(dirX, dirY, dirZ);
        }

        public boolean tryGoing(double dirX, double dirY, double dirZ)
        {
            this.targetPos.setTarget(this.entity.posX + dirX, this.entity.posY + dirY, this.entity.posZ + dirZ);
            boolean result = this.targetPos.isPathClear(1.0D);

            if (result)
            {
                this.entity.getMoveHelper().setMoveTo(this.targetPos.posX, this.targetPos.posY, this.targetPos.posZ, 1.0D);
            }
            return result;
        }
    }

    private class AIZergiusAttackTarget extends EntityAIBase
    {
        private EntityZergius wasp = EntityZergius.this;
        private int attackTick = 0;
        private FlyingMoveTargetPosition targetPos = new FlyingMoveTargetPosition();

        public AIZergiusAttackTarget()
        {
            this.setMutexBits(2);
        }

        public boolean attackTargetExists()
        {
            EntityLivingBase attackTarget = this.wasp.getAttackTarget();
            return attackTarget != null && attackTarget.isEntityAlive();
        }

        @Override
        public boolean shouldExecute()
        {
            if (this.attackTick > 0)
            {
                --this.attackTick;
            }
            return this.attackTargetExists();
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            if (this.attackTick > 0)
            {
                --this.attackTick;
            }

            if (!this.attackTargetExists())
            {
                return false;
            }

            EntityLivingBase attackTarget = this.wasp.getAttackTarget();
            this.targetPos.setTarget(attackTarget.posX, attackTarget.posY, attackTarget.posZ);

            double damageRange = this.wasp.width + attackTarget.width;

            if (this.attackTick <= 0 && this.targetPos.dist < damageRange)
            {
                this.wasp.attackEntityAsMob(attackTarget);
                this.attackTick = 16;
            }

            if (this.targetPos.isPathClear(1.0D))
            {
                this.wasp.getMoveHelper().setMoveTo(attackTarget.posX, attackTarget.posY, attackTarget.posZ, 1.0D);
            }
            return true;
        }
    }
}