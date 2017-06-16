/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.projectiles;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.util.DamageSourceMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class EntityPoisonArrow extends EntityArrow implements IProjectile
{
    private int x = -1;
    private int y = -1;
    private int z = -1;
    private Block block;
    private int inData;
    private boolean inGround;
    public int canBePickedUp;
    public int arrowShake;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    private double damage = 2.0D;
    private int knockbackStrength;

    public EntityPoisonArrow(World world)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
    }

    public EntityPoisonArrow(World world, double x, double y, double z)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
        this.setPosition(x, y, z);
        this.yOffset = 0.0F;
    }

    public EntityPoisonArrow(World world, EntityLivingBase living, EntityLivingBase living2, float par4, float par5)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = living;

        if (living instanceof EntityPlayer)
        {
            this.canBePickedUp = 1;
        }

        this.posY = living.posY + living.getEyeHeight() - 0.1000000014901161D;
        double d1 = living2.posX - living.posX;
        double d2 = living2.boundingBox.minY + living2.height / 3.0F - this.posY;
        double d3 = living2.posZ - living.posZ;
        double d4 = MathHelper.sqrt_double(d1 * d1 + d3 * d3);

        if (d4 < 1.0E-007D)
        {
            return;
        }

        float f1 = (float)(Math.atan2(d3, d1) * 180.0D / 3.141592741012573D) - 90.0F;
        float f2 = (float)-(Math.atan2(d2, d4) * 180.0D / 3.141592741012573D);
        double d5 = d1 / d4;
        double d6 = d3 / d4;
        this.setLocationAndAngles(living.posX + d5, this.posY, living.posZ + d6, f1, f2);
        this.yOffset = 0.0F;
        float f3 = (float)d4 * 0.2F;
        this.setThrowableHeading(d1, d2 + f3, d3, par4, par5);
    }

    public EntityPoisonArrow(World world, EntityLivingBase living, float par3)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = living;

        if (living instanceof EntityPlayer)
        {
            this.canBePickedUp = 1;
        }

        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(living.posX, living.posY + living.getEyeHeight(), living.posZ, living.rotationYaw, living.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * 3.141593F) * 0.16F;
        this.posY -= 0.1000000014901161D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * 3.141593F) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.141593F);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.141593F);
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * 3.141593F);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, par3 * 1.5F, 1.0F);
    }

    @Override
    protected void entityInit()
    {
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    @Override
    public void setThrowableHeading(double x, double y, double z, float par4, float par5)
    {
        float f1 = MathHelper.sqrt_double(x * x + y * y + z * z);

        x /= f1;
        y /= f1;
        z /= f1;

        x += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par5;
        y += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par5;
        z += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par5;

        x *= par4;
        y *= par4;
        z *= par4;

        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        float f2 = MathHelper.sqrt_double(x * x + z * z);

        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / 3.141592741012573D);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, f2) * 180.0D / 3.141592741012573D);
        this.ticksInGround = 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float par4, float par5, int par6)
    {
        this.setPosition(x, y, z);
        this.setRotation(par4, par5);
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
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / 3.141592741012573D);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, f) * 180.0D / 3.141592741012573D);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592741012573D);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f1) * 180.0D / 3.141592741012573D);
        }

        Block localBlock = this.worldObj.getBlock(this.x, this.y, this.z);

        if (localBlock.getMaterial() != Material.air)
        {
            localBlock.setBlockBoundsBasedOnState(this.worldObj, this.x, this.y, this.z);
            AxisAlignedBB localAxisAlignedBB = localBlock.getCollisionBoundingBoxFromPool(this.worldObj, this.x, this.y, this.z);

            if (localAxisAlignedBB != null && localAxisAlignedBB.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
            {
                this.inGround = true;
            }
        }
        if (this.arrowShake > 0)
        {
            this.arrowShake -= 1;
        }
        if (this.inGround)
        {
            int i = this.worldObj.getBlockMetadata(this.x, this.y, this.z);

            if (localBlock != this.block || i != this.inData)
            {
                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2F;
                this.motionY *= this.rand.nextFloat() * 0.2F;
                this.motionZ *= this.rand.nextFloat() * 0.2F;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
                return;
            }

            this.ticksInGround += 1;

            if (this.ticksInGround == 1200)
            {
                this.setDead();
            }
            return;
        }
        this.ticksInAir += 1;

        Vec3 localVec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 localVec32 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition localMovingObjectPosition1 = this.worldObj.func_147447_a(localVec31, localVec32, false, true, false);

        localVec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        localVec32 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (localMovingObjectPosition1 != null)
        {
            localVec32 = Vec3.createVectorHelper(localMovingObjectPosition1.hitVec.xCoord, localMovingObjectPosition1.hitVec.yCoord, localMovingObjectPosition1.hitVec.zCoord);
        }

        Object localObject1 = null;
        List localList = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
        double d1 = 0.0D;
        Object localObject2;

        for (int j = 0; j < localList.size(); j++)
        {
            Entity localEntity = (Entity)localList.get(j);

            if (localEntity.canBeCollidedWith() && (localEntity != this.shootingEntity || this.ticksInAir >= 5))
            {
                float f5 = 0.3F;
                localObject2 = localEntity.boundingBox.expand(f5, f5, f5);
                MovingObjectPosition localMovingObjectPosition2 = ((AxisAlignedBB)localObject2).calculateIntercept(localVec31, localVec32);

                if (localMovingObjectPosition2 != null)
                {
                    double d2 = localVec31.distanceTo(localMovingObjectPosition2.hitVec);

                    if (d2 < d1 || d1 == 0.0D)
                    {
                        localObject1 = localEntity;
                        d1 = d2;
                    }
                }
            }
        }
        if (localObject1 != null)
        {
            localMovingObjectPosition1 = new MovingObjectPosition((Entity)localObject1);
        }
        if (localMovingObjectPosition1 != null && localMovingObjectPosition1.entityHit != null && localMovingObjectPosition1.entityHit instanceof EntityPlayer)
        {
            EntityPlayer localEntityPlayer = (EntityPlayer)localMovingObjectPosition1.entityHit;

            if (localEntityPlayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(localEntityPlayer))
            {
                localMovingObjectPosition1 = null;
            }
        }

        float f7;

        if (localMovingObjectPosition1 != null)
        {
            float f2;

            if (localMovingObjectPosition1.entityHit != null)
            {
                f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                int m = MathHelper.ceiling_double_int(f2 * this.damage);

                if (this.getIsCritical())
                {
                    m += this.rand.nextInt(m / 2 + 2);
                }

                DamageSource localDamageSource = null;

                if (this.shootingEntity == null)
                {
                    localDamageSource = DamageSourceMP.causePoisonArrowDamage(this, this);
                }
                else
                {
                    localDamageSource = DamageSourceMP.causePoisonArrowDamage(this, this.shootingEntity);
                }
                if (this.isBurning() && (!(localMovingObjectPosition1.entityHit instanceof EntityEnderman) || !(localMovingObjectPosition1.entityHit instanceof EntityEvolvedEnderman)))
                {
                    localMovingObjectPosition1.entityHit.setFire(5);
                }
                if (localMovingObjectPosition1.entityHit.attackEntityFrom(localDamageSource, m))
                {
                    if (localMovingObjectPosition1.entityHit instanceof EntityLivingBase)
                    {
                        localObject2 = localMovingObjectPosition1.entityHit;

                        if (!this.worldObj.isRemote)
                        {
                            ((EntityLivingBase)localObject2).setArrowCountInEntity(((EntityLivingBase)localObject2).getArrowCountInEntity() + 1);
                        }
                        if (this.knockbackStrength > 0)
                        {
                            f7 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

                            if (f7 > 0.0F)
                            {
                                localMovingObjectPosition1.entityHit.addVelocity(this.motionX * this.knockbackStrength * 0.6000000238418579D / f7, 0.1D, this.motionZ * this.knockbackStrength * 0.6000000238418579D / f7);
                            }
                        }
                        if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase)
                        {
                            EnchantmentHelper.func_151384_a((EntityLivingBase)localObject2, this.shootingEntity);
                            EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, (Entity)localObject2);
                        }
                        if (this.shootingEntity != null && localMovingObjectPosition1.entityHit != this.shootingEntity && localMovingObjectPosition1.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
                        }
                        ((EntityLivingBase)localMovingObjectPosition1.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 160, 1));
                    }

                    this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

                    if (!(localMovingObjectPosition1.entityHit instanceof EntityEnderman) || !(localMovingObjectPosition1.entityHit instanceof EntityEvolvedEnderman))
                    {
                        this.setDead();
                    }
                }
                else
                {
                    this.motionX *= -0.1000000014901161D;
                    this.motionY *= -0.1000000014901161D;
                    this.motionZ *= -0.1000000014901161D;
                    this.rotationYaw += 180.0F;
                    this.prevRotationYaw += 180.0F;
                    this.ticksInAir = 0;
                }
            }
            else
            {
                this.x = localMovingObjectPosition1.blockX;
                this.y = localMovingObjectPosition1.blockY;
                this.z = localMovingObjectPosition1.blockZ;
                this.block = this.worldObj.getBlock(this.x, this.y, this.z);
                this.inData = this.worldObj.getBlockMetadata(this.x, this.y, this.z);
                this.motionX = (float)(localMovingObjectPosition1.hitVec.xCoord - this.posX);
                this.motionY = (float)(localMovingObjectPosition1.hitVec.yCoord - this.posY);
                this.motionZ = (float)(localMovingObjectPosition1.hitVec.zCoord - this.posZ);
                f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                this.posX -= this.motionX / f2 * 0.0500000007450581D;
                this.posY -= this.motionY / f2 * 0.0500000007450581D;
                this.posZ -= this.motionZ / f2 * 0.0500000007450581D;
                this.inGround = true;
                this.arrowShake = 7;
                this.setIsCritical(false);

                if (this.block.getMaterial() != Material.air)
                {
                    this.block.onEntityCollidedWithBlock(this.worldObj, this.x, this.y, this.z, this);
                }
            }
        }
        if (this.getIsCritical())
        {
            for (int k = 0; k < 4; k++)
            {
                this.worldObj.spawnParticle("crit", this.posX + this.motionX * k / 4.0D, this.posY + this.motionY * k / 4.0D, this.posZ + this.motionZ * k / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592741012573D);
        this.rotationPitch = (float)(Math.atan2(this.motionY, f3) * 180.0D / 3.141592741012573D);

        while (this.rotationPitch - this.prevRotationPitch < -180.0F)
        {
            this.prevRotationPitch -= 360.0F;
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
        float f6 = 0.05F;

        if (this.isInWater())
        {
            for (int n = 0; n < 4; n++)
            {
                f7 = 0.25F;
                this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f7, this.posY - this.motionY * f7, this.posZ - this.motionZ * f7, this.motionX, this.motionY, this.motionZ);
            }
            f4 = 0.8F;
        }
        if (this.isWet())
        {
            this.extinguish();
        }

        this.motionX *= f4;
        this.motionY *= f4;
        this.motionZ *= f4;
        this.motionY -= f6;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.func_145775_I();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setShort("xTile", (short)this.x);
        nbt.setShort("yTile", (short)this.y);
        nbt.setShort("zTile", (short)this.z);
        nbt.setShort("life", (short)this.ticksInGround);
        nbt.setByte("inTile", (byte)Block.getIdFromBlock(this.block));
        nbt.setByte("inData", (byte)this.inData);
        nbt.setByte("shake", (byte)this.arrowShake);
        nbt.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        nbt.setByte("pickup", (byte)this.canBePickedUp);
        nbt.setDouble("damage", this.damage);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.x = nbt.getShort("xTile");
        this.y = nbt.getShort("yTile");
        this.z = nbt.getShort("zTile");
        this.ticksInGround = nbt.getShort("life");
        this.block = Block.getBlockById(nbt.getByte("inTile") & 0xFF);
        this.inData = nbt.getByte("inData") & 0xFF;
        this.arrowShake = nbt.getByte("shake") & 0xFF;
        this.inGround = nbt.getByte("inGround") == 1;

        if (nbt.hasKey("damage", 99))
        {
            this.damage = nbt.getDouble("damage");
        }
        if (nbt.hasKey("pickup", 99))
        {
            this.canBePickedUp = nbt.getByte("pickup");
        }
        else if (nbt.hasKey("player", 99))
        {
            this.canBePickedUp = nbt.getBoolean("player") ? 1 : 0;
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        if (this.worldObj.isRemote || !this.inGround || this.arrowShake > 0)
        {
            return;
        }

        int i = this.canBePickedUp == 1 || this.canBePickedUp == 2 && player.capabilities.isCreativeMode ? 1 : 0;

        if (this.canBePickedUp == 1 && !player.inventory.addItemStackToInventory(new ItemStack(FronosItems.poison_arrow, 1)))
        {
            i = 0;
        }
        if (i != 0)
        {
            this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            player.onItemPickup(this, 1);
            this.setDead();
        }
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    @Override
    public void setDamage(double damage)
    {
        this.damage = damage;
    }

    @Override
    public double getDamage()
    {
        return this.damage;
    }

    @Override
    public void setKnockbackStrength(int knock)
    {
        this.knockbackStrength = knock;
    }

    @Override
    public boolean canAttackWithItem()
    {
        return false;
    }

    @Override
    public void setIsCritical(boolean critical)
    {
        int i = this.dataWatcher.getWatchableObjectByte(16);

        if (critical)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(i | 0x1)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(i & 0xFFFFFFFE)));
        }
    }

    @Override
    public boolean getIsCritical()
    {
        int i = this.dataWatcher.getWatchableObjectByte(16);
        return (i & 0x1) != 0;
    }
}