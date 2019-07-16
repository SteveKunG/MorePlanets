package stevekung.mods.moreplanets.entity.projectile;

import java.util.List;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.DamageSourceMP;

public class EntityLaserBullet extends Entity implements IProjectile, IEntityAdditionalSpawnData
{
    private static final DataParameter<Integer> LASER_TYPE = EntityDataManager.createKey(EntityLaserBullet.class, DataSerializers.VARINT);
    private Entity shootingEntity;
    private int ticksInAir;
    private double damage;

    public EntityLaserBullet(World world)
    {
        super(world);
        this.setSize(0.1F, 0.1F);
    }

    public EntityLaserBullet(World world, double x, double y, double z)
    {
        super(world);
        this.setSize(0.1F, 0.1F);
        this.setPosition(x, y, z);
    }

    public EntityLaserBullet(World world, EntityLivingBase shooter, EntityLivingBase indirect, float velocity, float inaccuracy)
    {
        super(world);
        this.shootingEntity = shooter;
        this.posY = shooter.posY + shooter.getEyeHeight() - 0.10000000149011612D;
        double d0 = indirect.posX - shooter.posX;
        double d1 = indirect.getEntityBoundingBox().minY + indirect.height / 3.0F - this.posY;
        double d2 = indirect.posZ - shooter.posZ;
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            float f = (float)(MathHelper.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f1 = (float)-(MathHelper.atan2(d1, d3) * 180.0D / Math.PI);
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f, f1);
            float f2 = (float)(d3 * 0.20000000298023224D);
            this.shoot(d0, d1 + f2, d2, velocity, inaccuracy);
        }
    }

    public EntityLaserBullet(World world, EntityLivingBase shooter, float velocity)
    {
        super(world);
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
        this.shoot(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, 1.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 10.0D;

        if (Double.isNaN(d0))
        {
            d0 = 1.0D;
        }
        d0 = d0 * 64.0D * getRenderDistanceWeight();
        return distance < d0 * d0;
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(EntityLaserBullet.LASER_TYPE, 0);
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / f;
        y = y / f;
        z = z / f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * inaccuracy;
        x = x * velocity;
        y = y * velocity;
        z = z * velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
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
            float f = MathHelper.sqrt(x * x + z * z);
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
            float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
            this.rotationPitch = (float)(MathHelper.atan2(this.motionY, f) * (180D / Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }

        ++this.ticksInAir;
        Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec31, vec3, false, true, false);
        vec31 = new Vec3d(this.posX, this.posY, this.posZ);
        vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (this.ticksInAir > 8)
        {
            this.setDead();
        }
        if (this.isInWater())
        {
            for (int i = 0; i < 4; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX - this.motionX, this.posY - this.motionY, this.posZ - this.motionZ, this.motionX, this.motionY, this.motionZ);
                this.world.playSound(null, this.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.HOSTILE, 0.25F, 0.9F);
            }
            this.setDead();
        }
        if (raytraceresult != null)
        {
            vec3 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
        }

        Entity entity = this.findEntityOnPath(vec31, vec3);

        if (entity != null)
        {
            raytraceresult = new RayTraceResult(entity);
        }

        if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)raytraceresult.entityHit;

            if (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
            {
                raytraceresult = null;
            }
        }

        switch (this.getLaserType())
        {
        case 0:
            this.damage = 2.0D;
            break;
        case 1:
            this.damage = 4.5D;
            break;
        }

        if (this.isInWater())
        {
            this.damage = 0.0D;
        }
        if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult))
        {
            this.onHit(raytraceresult);
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f3 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
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
        this.motionX *= speed;
        this.motionY *= speed;
        this.motionZ *= speed;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.doBlockCollisions();
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
    public boolean canBeAttackedWithItem()
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
        Entity shooter = this.world.getEntityByID(buffer.readInt());

        if (shooter instanceof EntityLivingBase)
        {
            this.shootingEntity = shooter;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        return 15728880;
    }

    @Override
    public float getBrightness()
    {
        return 1.0F;
    }

    public void setDamage(double damage)
    {
        this.damage = damage;
    }

    public int getLaserType()
    {
        return this.dataManager.get(EntityLaserBullet.LASER_TYPE);
    }

    public void setLaserType(EnumLaserType type)
    {
        this.setLaserType(type.ordinal());
    }

    private void setLaserType(int type)
    {
        this.dataManager.set(EntityLaserBullet.LASER_TYPE, type);
    }

    @Nullable
    private Entity findEntityOnPath(Vec3d start, Vec3d end)
    {
        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
        double d0 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = list.get(i);

            if (entity1 != this.shootingEntity || this.ticksInAir >= 5)
            {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

                if (raytraceresult != null)
                {
                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);

                    if (d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }
        return entity;
    }

    private void onHit(RayTraceResult raytraceResult)
    {
        Entity entity = raytraceResult.entityHit;

        if (entity != null)
        {
            float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            int l = MathHelper.ceil(f2 * this.damage);

            DamageSource damagesource;

            if (this.shootingEntity == null)
            {
                damagesource = DamageSourceMP.causeLaserDamage(this, this);
            }
            else
            {
                damagesource = DamageSourceMP.causeLaserDamage(this, this.shootingEntity);
            }

            if (entity.attackEntityFrom(damagesource, l))
            {
                if (entity instanceof EntityLivingBase)
                {
                    EntityLivingBase entitylivingbase = (EntityLivingBase)entity;

                    if (this.shootingEntity instanceof EntityLivingBase)
                    {
                        EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
                        EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, entitylivingbase);
                    }
                    if (this.shootingEntity != null && entity != this.shootingEntity && entity instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                    }
                    if (this.getLaserType() == 1)
                    {
                        entitylivingbase.addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZED, 100));
                    }
                }
                if (!(entity instanceof EntityEnderman))
                {
                    this.setDead();
                }
            }
        }
        else
        {
            this.motionX = (float)(raytraceResult.hitVec.x - this.posX);
            this.motionY = (float)(raytraceResult.hitVec.y - this.posY);
            this.motionZ = (float)(raytraceResult.hitVec.z - this.posZ);
            float f5 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            this.posX -= this.motionX / f5 * 0.05000000074505806D;
            this.posY -= this.motionY / f5 * 0.05000000074505806D;
            this.posZ -= this.motionZ / f5 * 0.05000000074505806D;
            this.setDead();
        }
    }

    public static enum EnumLaserType
    {
        NORMAL,
        INFECTED_CRYSTALLIZED;
    }
}