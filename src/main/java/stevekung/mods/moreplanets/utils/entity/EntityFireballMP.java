package stevekung.mods.moreplanets.utils.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityFireballMP extends Entity
{
    protected EntityLivingBase shootingEntity;
    private int ticksAlive;
    private int ticksInAir;
    protected boolean isFireball;
    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;

    public EntityFireballMP(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    public EntityFireballMP(World world, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        double d0 = MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
    }

    public EntityFireballMP(World world, EntityLivingBase shooter, double accelX, double accelY, double accelZ)
    {
        super(world);
        this.shootingEntity = shooter;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        accelX = accelX + this.rand.nextGaussian() * 0.4D;
        accelY = accelY + this.rand.nextGaussian() * 0.4D;
        accelZ = accelZ + this.rand.nextGaussian() * 0.4D;
        double d0 = MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
    }

    @Override
    protected void entityInit() {}

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;

        if (Double.isNaN(d0))
        {
            d0 = 4.0D;
        }
        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    @Override
    public void onUpdate()
    {
        if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.isDead) && this.world.isBlockLoaded(new BlockPos(this)))
        {
            super.onUpdate();

            if (this.isFireballFiery())
            {
                this.setFire(1);
            }

            ++this.ticksInAir;
            RayTraceResult result = ProjectileHelper.forwardsRaycast(this, true, this.ticksInAir >= 25, this.shootingEntity);

            if (result != null && !ForgeEventFactory.onProjectileImpact(this, result))
            {
                this.onImpact(result);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getMotionFactor();

            if (this.isFireball && this.isInWater())
            {
                for (int i = 0; i < 4; ++i)
                {
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
                }
                f = 0.8F;
            }
            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= f;
            this.motionY *= f;
            this.motionZ *= f;

            if (this.isFireball)
            {
                this.world.spawnParticle(this.getParticleType(), this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
            }
            this.setPosition(this.posX, this.posY, this.posZ);
        }
        else
        {
            this.setDead();
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setTag("Direction", this.newDoubleNBTList(new double[] {this.motionX, this.motionY, this.motionZ}));
        compound.setTag("Power", this.newDoubleNBTList(new double[] {this.accelerationX, this.accelerationY, this.accelerationZ}));
        compound.setInteger("Life", this.ticksAlive);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("Power", 9))
        {
            NBTTagList nbttaglist = compound.getTagList("Power", 6);

            if (nbttaglist.tagCount() == 3)
            {
                this.accelerationX = nbttaglist.getDoubleAt(0);
                this.accelerationY = nbttaglist.getDoubleAt(1);
                this.accelerationZ = nbttaglist.getDoubleAt(2);
            }
        }

        this.ticksAlive = compound.getInteger("Life");

        if (compound.hasKey("Direction", 9) && compound.getTagList("Direction", 6).tagCount() == 3)
        {
            NBTTagList nbttaglist1 = compound.getTagList("Direction", 6);
            this.motionX = nbttaglist1.getDoubleAt(0);
            this.motionY = nbttaglist1.getDoubleAt(1);
            this.motionZ = nbttaglist1.getDoubleAt(2);
        }
        else
        {
            this.setDead();
        }
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return this.isFireball;
    }

    @Override
    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            this.markVelocityChanged();

            if (source.getTrueSource() != null)
            {
                Vec3d vec3d = source.getTrueSource().getLookVec();

                if (vec3d != null)
                {
                    this.motionX = vec3d.x;
                    this.motionY = vec3d.y;
                    this.motionZ = vec3d.z;
                    this.accelerationX = this.motionX * 0.1D;
                    this.accelerationY = this.motionY * 0.1D;
                    this.accelerationZ = this.motionZ * 0.1D;
                }
                if (source.getTrueSource() instanceof EntityLivingBase)
                {
                    this.shootingEntity = (EntityLivingBase)source.getTrueSource();
                }
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    public float getBrightness()
    {
        if (this.isFireball)
        {
            return 1.0F;
        }
        else
        {
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));

            if (this.world.isBlockLoaded(mutablePos))
            {
                mutablePos.setY(MathHelper.floor(this.posY + this.getEyeHeight()));
                return this.world.getLightBrightness(mutablePos);
            }
            else
            {
                return 0.0F;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        if (this.isFireball)
        {
            return 15728880;
        }
        else
        {
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));

            if (this.world.isBlockLoaded(mutablePos))
            {
                mutablePos.setY(MathHelper.floor(this.posY + this.getEyeHeight()));
                return this.world.getCombinedLight(mutablePos, 0);
            }
            else
            {
                return 0;
            }
        }
    }

    protected boolean isFireballFiery()
    {
        return this.isFireball;
    }

    protected EnumParticleTypes getParticleType()
    {
        return EnumParticleTypes.SMOKE_NORMAL;
    }

    protected float getMotionFactor()
    {
        return 0.95F;
    }

    protected abstract void onImpact(RayTraceResult result);
}