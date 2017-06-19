package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;

public class EntityInfectedSquid extends EntityWaterMob implements IEntityBreathable, ISpaceMob
{
    public float squidPitch;
    public float prevSquidPitch;
    public float squidYaw;
    public float prevSquidYaw;
    public float squidRotation;
    public float prevSquidRotation;
    public float tentacleAngle;
    public float lastTentacleAngle;
    private float randomMotionSpeed;
    private float rotationVelocity;
    private float rotateSpeed;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public EntityInfectedSquid(World world)
    {
        super(world);
        this.setSize(0.95F, 0.95F);
        this.rand.setSeed(1 + this.getEntityId());
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
        this.tasks.addTask(0, new AIMoveRandom(this));
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
    }

    @Override
    public float getEyeHeight()
    {
        return this.height * 0.5F;
    }

    @Override
    protected String getLivingSound()
    {
        return null;
    }

    @Override
    protected String getHurtSound()
    {
        return null;
    }

    @Override
    protected String getDeathSound()
    {
        return null;
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    protected Item getDropItem()
    {
        return null;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int i = this.rand.nextInt(3 + fortune) + 1;

        for (int j = 0; j < i; ++j)
        {
            this.entityDropItem(new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeDamage()), 0.0F);
        }
    }

    @Override
    public boolean isInWater()
    {
        return this.worldObj.handleMaterialAcceleration(this.getEntityBoundingBox().expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.prevSquidPitch = this.squidPitch;
        this.prevSquidYaw = this.squidYaw;
        this.prevSquidRotation = this.squidRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.squidRotation += this.rotationVelocity;

        if (this.squidRotation > Math.PI * 2D)
        {
            if (this.worldObj.isRemote)
            {
                this.squidRotation = (float)Math.PI * 2F;
            }
            else
            {
                this.squidRotation = (float)(this.squidRotation - Math.PI * 2D);

                if (this.rand.nextInt(10) == 0)
                {
                    this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }
                this.worldObj.setEntityState(this, (byte)19);
            }
        }

        if (this.inWater)
        {
            if (this.squidRotation < (float)Math.PI)
            {
                float f = this.squidRotation / (float)Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;

                if (f > 0.75D)
                {
                    this.randomMotionSpeed = 1.0F;
                    this.rotateSpeed = 1.0F;
                }
                else
                {
                    this.rotateSpeed *= 0.8F;
                }
            }
            else
            {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.9F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.worldObj.isRemote)
            {
                this.motionX = this.randomMotionVecX * this.randomMotionSpeed;
                this.motionY = this.randomMotionVecY * this.randomMotionSpeed;
                this.motionZ = this.randomMotionVecZ * this.randomMotionSpeed;
            }
            float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float)MathHelper.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.squidYaw = (float)(this.squidYaw + Math.PI * this.rotateSpeed * 1.5D);
            this.squidPitch += (-((float)MathHelper.atan2(f1, this.motionY)) * 180.0F / (float)Math.PI - this.squidPitch) * 0.1F;
        }
        else
        {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float)Math.PI * 0.25F;

            if (!this.worldObj.isRemote)
            {
                this.motionX = 0.0D;
                this.motionY -= 0.08D;
                this.motionY *= 0.9800000190734863D;
                this.motionZ = 0.0D;
            }
            this.squidPitch = (float)(this.squidPitch + (-90.0F - this.squidPitch) * 0.02D);
        }
    }

    @Override
    public void moveEntityWithHeading(float strafe, float forward)
    {
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.posY > 45.0D && this.posY < this.worldObj.getSeaLevel() && super.getCanSpawnHere();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 19)
        {
            this.squidRotation = 0.0F;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn)
    {
        this.randomMotionVecX = randomMotionVecXIn;
        this.randomMotionVecY = randomMotionVecYIn;
        this.randomMotionVecZ = randomMotionVecZIn;
    }

    public boolean hasMovementVector()
    {
        return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.NIBIRU;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    static class AIMoveRandom extends EntityAIBase
    {
        private EntityInfectedSquid squid;

        public AIMoveRandom(EntityInfectedSquid squid)
        {
            this.squid = squid;
        }

        @Override
        public boolean shouldExecute()
        {
            return true;
        }

        @Override
        public void updateTask()
        {
            int i = this.squid.getAge();

            if (i > 100)
            {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            }
            else if (this.squid.getRNG().nextInt(50) == 0 || !this.squid.inWater || !this.squid.hasMovementVector() || this.squid.getAttackTarget() != null)
            {
                float f = this.squid.getRNG().nextFloat() * (float)Math.PI * 2.0F;
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;
                this.squid.setMovementVector(f1, f2, f3);
            }
        }
    }
}