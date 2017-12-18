/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities.projectiles;

import java.util.List;

import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.core.util.DamageSourceMP;

public class EntityLaserMP extends EntityArrow implements IThrowableEntity
{
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int ticksInAir = 0;
    private float damage;

    public EntityLaserMP(World par1World)
    {
        super(par1World);
    }

    public EntityLaserMP(World par1World, EntityLivingBase par2EntityLivingBase, float par3)
    {
        super(par1World, par2EntityLivingBase, par3);
    }

    public EntityLaserMP(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    @Override
    public void setThrowableHeading(double headingX, double headingY, double headingZ, float speed, float randMod)
    {
        float f2 = MathHelper.sqrt_double(headingX * headingX + headingY * headingY + headingZ * headingZ);
        headingX /= f2;
        headingY /= f2;
        headingZ /= f2;
        headingX += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * randMod;
        headingY += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * randMod;
        headingZ += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * randMod;
        speed = 4.0F;
        headingX *= speed;
        headingY *= speed;
        headingZ *= speed;
        this.motionX = headingX;
        this.motionY = headingY;
        this.motionZ = headingZ;
        float f3 = MathHelper.sqrt_double(headingX * headingX + headingZ * headingZ);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(headingX, headingZ) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(headingY, f3) * 180.0D / Math.PI);
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
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        }
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("LaserType", this.getLaserType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setLaserType(par1NBTTagCompound.getInteger("LaserType"));
    }

    public int getLaserType()
    {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    public void setLaserType(int type)
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)type));
    }

    @Override
    public void onUpdate()
    {
        super.onEntityUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180.0D / Math.PI);
        }

        Block block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);

        if (block == Blocks.air)
        {
            block.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

            if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
            {
                this.setDead();
            }
        }

        ++this.ticksInAir;
        Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec3, vec31, false, true, false);
        vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (movingobjectposition != null)
        {
            vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }

        Entity entity = null;
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
        double d0 = 0.0D;
        int l;
        float f1;

        for (l = 0; l < list.size(); ++l)
        {
            Entity entity1 = (Entity)list.get(l);

            if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
            {
                f1 = 0.3F;
                AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f1, f1, f1);
                MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);

                if (movingobjectposition1 != null)
                {
                    double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

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

        float f2;
        float f3;

        if (movingobjectposition != null)
        {
            if (movingobjectposition.entityHit != null)
            {
                f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                int type = this.getLaserType();
                DamageSource damagesource = null;

                if (this.shootingEntity == null)
                {
                    damagesource = DamageSourceMP.causeLaserDamage(this, this);
                }
                else
                {
                    damagesource = DamageSourceMP.causeLaserDamage(this, this.shootingEntity);
                }

                if (type == 0)
                {
                    this.damage = 6.0F;
                }
                if (type == 1)
                {
                    this.damage = 10.0F;
                }
                if (type == 2)
                {
                    this.damage = 7.5F;

                    if (movingobjectposition.entityHit instanceof EntityLivingBase)
                    {
                        if (!this.worldObj.isRemote)
                        {
                            ((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(MPPotions.electro_magnetic_pulse.id, 480));
                        }
                    }
                }
                if (type == 3)
                {
                    this.damage = 9.5F;

                    if (movingobjectposition.entityHit instanceof EntityLivingBase)
                    {
                        if (!this.worldObj.isRemote)
                        {
                            ((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(MPPotions.chemical.id, 256));
                        }
                    }
                }
                if (type == 4)
                {
                    this.damage = 10.0F;

                    if (movingobjectposition.entityHit instanceof EntityLivingBase)
                    {
                        if (!this.worldObj.isRemote)
                        {
                            ((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(MPPotions.icy_poison.id, 128));
                        }
                    }
                }
                if (movingobjectposition.entityHit.attackEntityFrom(damagesource, this.damage))
                {
                    if (movingobjectposition.entityHit instanceof EntityLivingBase)
                    {
                        if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0));
                        }
                    }
                    this.setDead();
                }
                else
                {
                    this.motionX *= -0.10000000149011612D;
                    this.motionY += 0D;
                    this.motionZ *= -0.10000000149011612D;
                    this.rotationYaw += 180.0F;
                    this.prevRotationYaw += 180.0F;
                    this.ticksInAir = 0;
                }
            }
            else
            {
                this.xTile = movingobjectposition.blockX;
                this.yTile = movingobjectposition.blockY;
                this.zTile = movingobjectposition.blockZ;
                this.motionX = (float)(movingobjectposition.hitVec.xCoord - this.posX);
                this.motionY = (float)(movingobjectposition.hitVec.yCoord + this.posY);
                this.motionZ = (float)(movingobjectposition.hitVec.zCoord - this.posZ);
                f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                this.posX -= this.motionX / f2 * 0.05000000074505806D;
                this.posY += this.motionY / f2 * 0D;
                this.posZ -= this.motionZ / f2 * 0.05000000074505806D;
                this.setDead();
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        for (this.rotationPitch = (float)(Math.atan2(this.motionY, f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
        {
        }

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
        float f4 = 0.99F;
        f1 = 0.05F;

        if (this.isInWater())
        {
            for (int j1 = 0; j1 < 4; ++j1)
            {
                f3 = 0.25F;
                this.worldObj.spawnParticle("smoke", this.posX - this.motionX * f3, this.posY - this.motionY * f3, this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ);
                this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.fizz", 0.7F, 0.9F);
            }
            if (this.worldObj.rand.nextInt(100) == 0)
            {
                this.setDead();
            }
            if (this.getLaserType() == 2)
            {
                if (this.worldObj.rand.nextInt(5) == 0)
                {
                    this.setDead();
                }
            }
        }

        this.motionX *= f4;
        this.motionY *= f4;
        this.motionZ *= f4;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.func_145775_I();

        if (this.ticksExisted > 20)
        {
            this.setDead();
        }
    }

    @Override
    public Entity getThrower()
    {
        return this.shootingEntity;
    }

    @Override
    public void setThrower(Entity entity)
    {
        this.shootingEntity = entity;
    }
}