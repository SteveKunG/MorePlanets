package stevekung.mods.moreplanets.core.todo;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFishBasic extends EntityWaterMob
{
    protected double swimTargetX;
    protected double swimTargetY;
    protected double swimTargetZ;
    private Entity targetEntity;
    protected boolean isAttacking;
    protected float swimRadius = 4.0F;
    protected float swimRadiusHeight = 4.0F;
    protected boolean isAgressive = false;
    protected int attackDamage = 2;
    protected int attackInterval = 50;
    protected float attackSpeed = 1.2F;
    protected float swimSpeed = 0.5F;
    protected boolean jumpOnLand = true;

    public EntityFishBasic(World world)
    {
        super(world);
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public boolean isInWater()
    {
        return this.worldObj.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.WATER, this);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.isInWater())
        {
            this.motionY *= 0.1D;
        }
    }

    @Override
    protected void updateAITasks()
    {
        super.updateAITasks();

        if (this.isInWater())
        {
            double dx = this.swimTargetX - this.posX;
            double dy = this.swimTargetY - this.posY;
            double dz = this.swimTargetZ - this.posZ;
            double dist = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);

            if (dist < 1.0D || dist > 1000.0D)
            {
                this.swimTargetX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * this.swimRadius;
                this.swimTargetY = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * this.swimRadiusHeight;
                this.swimTargetZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * this.swimRadius;
                this.isAttacking = false;
            }

            IBlockState blockState = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.swimTargetX), MathHelper.floor_double(this.swimTargetY + this.height), MathHelper.floor_double(this.swimTargetZ)));

            if (blockState.getMaterial() == Material.WATER)
            {
                this.motionX += dx / dist * 0.05D * this.swimSpeed;
                this.motionY += dy / dist * 0.1D * this.swimSpeed;
                this.motionZ += dz / dist * 0.05D * this.swimSpeed;
            }
            else
            {
                this.swimTargetX = this.posX;
                this.swimTargetY = this.posY + 0.1D;
                this.swimTargetZ = this.posZ;
            }
            if (this.isAttacking)
            {
                this.motionX *= this.attackSpeed;
                this.motionY *= this.attackSpeed;
                this.motionZ *= this.attackSpeed;
            }
            if (this.isAgressive && this.rand.nextInt(this.attackInterval) == 0)
            {
                this.targetEntity = this.findEntityToAttack();

                if (this.targetEntity != null && this.targetEntity.isInWater())
                {
                    this.swimTargetX = this.targetEntity.posX;
                    this.swimTargetY = this.targetEntity.posY;
                    this.swimTargetZ = this.targetEntity.posZ;
                    this.isAttacking = true;
                }
            }
            this.renderYawOffset += (-(float)Math.atan2(this.motionX, this.motionZ) * 180.0F / 3.1415927F - this.renderYawOffset) * 0.5F;
            this.rotationYaw = this.renderYawOffset;
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationPitch += ((float)Math.atan2(this.motionY, f) * 180.0F / 3.1415927F - this.rotationPitch) * 0.5F;
        }
        else if (this.jumpOnLand && this.onGround && this.rand.nextInt(30) == 0)
        {
            this.motionY = 0.30000001192092896D;
            this.motionX = -0.2F + this.rand.nextFloat() * 0.4F;
            this.motionZ = -0.2F + this.rand.nextFloat() * 0.4F;
            this.playSound(this.getSquishSound(), 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected SoundEvent getSquishSound()
    {
        return this.height > 0.8F ? SoundEvents.ENTITY_SLIME_SQUISH : SoundEvents.ENTITY_SMALL_SLIME_SQUISH;
    }

    protected Entity findEntityToAttack()
    {
        EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 16.0D);
        return player != null && this.canEntityBeSeen(player) ? player : null;
    }

    @Override
    public void applyEntityCollision(Entity entity)
    {
        super.applyEntityCollision(entity);

        if (this.isAgressive && this.targetEntity == entity)
        {
            this.attackEntityAsMob(entity);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.posY > 45.0D && this.posY < 63.0D && super.getCanSpawnHere();
    }
}