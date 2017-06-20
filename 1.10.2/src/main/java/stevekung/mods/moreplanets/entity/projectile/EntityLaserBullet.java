package stevekung.mods.moreplanets.entity.projectile;

import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.DamageSourceMP;

public class EntityLaserBullet extends Entity implements IProjectile, IEntityAdditionalSpawnData
{
    public Entity shootingEntity;
    public int ticksInAir;
    public double damage;

    public EntityLaserBullet(World world)
    {
        super(world);
        Entity.renderDistanceWeight = 10.0D;
        this.setSize(0.1F, 0.1F);
    }

    public EntityLaserBullet(World world, double x, double y, double z)
    {
        super(world);
        Entity.renderDistanceWeight = 10.0D;
        this.setSize(0.1F, 0.1F);
        this.setPosition(x, y, z);
    }

    public EntityLaserBullet(World world, EntityLivingBase shooter, EntityLivingBase indirect, float velocity, float inaccuracy)
    {
        super(world);
        Entity.renderDistanceWeight = 10.0D;
        this.shootingEntity = shooter;
        this.posY = shooter.posY + shooter.getEyeHeight() - 0.10000000149011612D;
        double d0 = indirect.posX - shooter.posX;
        double d1 = indirect.getEntityBoundingBox().minY + indirect.height / 3.0F - this.posY;
        double d2 = indirect.posZ - shooter.posZ;
        double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            float f = (float)(MathHelper.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f1 = (float)-(MathHelper.atan2(d1, d3) * 180.0D / Math.PI);
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f, f1);
            float f2 = (float)(d3 * 0.20000000298023224D);
            this.setThrowableHeading(d0, d1 + f2, d2, velocity, inaccuracy);
        }
    }

    public EntityLaserBullet(World world, EntityLivingBase shooter, float velocity)
    {
        super(world);
        Entity.renderDistanceWeight = 10.0D;
        this.shootingEntity = shooter;
        this.setSize(0.1F, 0.1F);
        this.setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        this.posY -= 0.10000000149011612D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI);
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, 1.0F);
    }

    @Override
    protected void entityInit()
    {
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
    }

    @Override
    public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy)
    {
        float f = MathHelper.sqrt_double(x * x + y * y + z * z);
        x = x / f;
        y = y / f;
        z = z / f;
        x = x + this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
        y = y + this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
        z = z + this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
        x = x * velocity;
        y = y * velocity;
        z = z * velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(y, f1) * 180.0D / Math.PI);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean p_180426_10_)
    {
        this.setPosition(x, y, z);
        this.setRotation(yaw, pitch);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z)
    {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(x * x + z * z);
            this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(y, f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(this.motionY, f) * 180.0D / Math.PI);
        }

        ++this.ticksInAir;
        Vec3 vec31 = new Vec3(this.posX, this.posY, this.posZ);
        Vec3 vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);
        vec31 = new Vec3(this.posX, this.posY, this.posZ);
        vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (movingobjectposition != null)
        {
            vec3 = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }

        Entity entity = null;
        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
        double d0 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = list.get(i);

            if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
            {
                float f1 = 0.3F;
                AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand(f1, f1, f1);
                MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);

                if (movingobjectposition1 != null)
                {
                    double d1 = vec31.squareDistanceTo(movingobjectposition1.hitVec);

                    if (d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        if (entity != null)
        {
            movingobjectposition = new MovingObjectPosition(entity);
        }

        if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;

            if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
            {
                movingobjectposition = null;
            }
        }

        if (this.getLaserType() == 0)
        {
            this.damage = 2.0D;
        }

        if (movingobjectposition != null)
        {
            if (movingobjectposition.entityHit != null)
            {
                float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                int l = MathHelper.ceiling_double_int(f2 * this.damage);

                DamageSource damagesource;

                if (this.shootingEntity == null)
                {
                    damagesource = DamageSourceMP.causeLaserDamage(this, this);
                }
                else
                {
                    damagesource = DamageSourceMP.causeLaserDamage(this, this.shootingEntity);
                }

                if (this.isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman))
                {
                    movingobjectposition.entityHit.setFire(5);
                }
                if (movingobjectposition.entityHit.attackEntityFrom(damagesource, l))
                {
                    if (movingobjectposition.entityHit instanceof EntityLivingBase)
                    {
                        EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.entityHit;

                        if (!this.worldObj.isRemote)
                        {
                            entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
                        }
                        if (this.shootingEntity instanceof EntityLivingBase)
                        {
                            EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
                            EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, entitylivingbase);
                        }
                        if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
                        }
                    }

                    if (!(movingobjectposition.entityHit instanceof EntityEnderman))
                    {
                        this.setDead();
                    }
                }
            }
            else
            {
                this.motionX = (float)(movingobjectposition.hitVec.xCoord - this.posX);
                this.motionY = (float)(movingobjectposition.hitVec.yCoord - this.posY);
                this.motionZ = (float)(movingobjectposition.hitVec.zCoord - this.posZ);
                float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                this.posX -= this.motionX / f5 * 0.05000000074505806D;
                this.posY -= this.motionY / f5 * 0.05000000074505806D;
                this.posZ -= this.motionZ / f5 * 0.05000000074505806D;
                this.setDead();
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        for (this.rotationPitch = (float)(MathHelper.atan2(this.motionY, f3) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {}

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }
        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }
        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float speed = 1.5F;

        if (this.isInWater())
        {
            for (int i = 0; i < 4; ++i)
            {
                this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX - this.motionX * f3, this.posY - this.motionY * f3, this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ);
                this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.fizz", 0.25F, 0.9F);
            }
            this.setDead();
        }
        this.motionX *= speed;
        this.motionY *= speed;
        this.motionZ *= speed;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.doBlockCollisions();

        if (this.ticksExisted > 30)
        {
            this.setDead();
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setDouble("Damage", this.damage);
        nbt.setInteger("LaserType", this.getLaserType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.damage = nbt.getDouble("Damage");
        this.setLaserType(nbt.getInteger("LaserType"));
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public boolean canAttackWithItem()
    {
        return false;
    }

    @Override
    public float getEyeHeight()
    {
        return 0.0F;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeInt(this.shootingEntity != null ? this.shootingEntity.getEntityId() : -1);
    }

    @Override
    public void readSpawnData(ByteBuf buffer)
    {
        Entity shooter = this.worldObj.getEntityByID(buffer.readInt());

        if (shooter instanceof EntityLivingBase)
        {
            this.shootingEntity = shooter;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks)
    {
        return 15728880;
    }

    @Override
    public float getBrightness(float partialTicks)
    {
        return 1.0F;
    }

    public void setDamage(double damage)
    {
        this.damage = damage;
    }

    public int getLaserType()
    {
        return this.dataWatcher.getWatchableObjectByte(17);
    }

    private void setLaserType(int type)
    {
        this.dataWatcher.updateObject(17, Byte.valueOf((byte)type));
    }

    public void setLaserType(EnumLaserType type)
    {
        this.setLaserType(type.ordinal());
    }

    public static enum EnumLaserType
    {
        NORMAL;
    }
}