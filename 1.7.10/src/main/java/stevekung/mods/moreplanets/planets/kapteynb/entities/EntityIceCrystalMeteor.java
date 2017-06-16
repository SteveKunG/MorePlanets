/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.entities;

import java.util.Iterator;
import java.util.List;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;

public class EntityIceCrystalMeteor extends Entity
{
    public EntityLiving shootingEntity;
    public int size;

    public EntityIceCrystalMeteor(World world)
    {
        super(world);
    }

    public EntityIceCrystalMeteor(World world, double x, double y, double z, double motX, double motY, double motZ, int size)
    {
        this(world);
        this.size = size;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        this.motionX = motX;
        this.motionY = motY;
        this.motionZ = motZ;
        this.setSize(size);
    }

    @Override
    public void onUpdate()
    {
        this.setRotation(this.rotationYaw + 2F, this.rotationPitch + 2F);
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.worldObj.isRemote)
        {
            this.spawnParticles();
        }

        Vec3 var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition var3 = this.worldObj.func_147447_a(var15, var2, true, true, false);
        var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (var3 != null)
        {
            var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
        }

        Entity var4 = null;
        List<?> var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(2.0D, 2.0D, 2.0D));
        double var6 = 0.0D;
        Iterator<?> var8 = var5.iterator();

        while (var8.hasNext())
        {
            Entity var9 = (Entity) var8.next();

            if (var9.canBeCollidedWith() && !var9.isEntityEqual(this.shootingEntity))
            {
                float var10 = 0.01F;
                AxisAlignedBB var11 = var9.boundingBox.expand(var10, var10, var10);
                MovingObjectPosition var12 = var11.calculateIntercept(var15, var2);

                if (var12 != null)
                {
                    double var13 = var15.distanceTo(var12.hitVec);

                    if (var13 < var6 || var6 == 0.0D)
                    {
                        var4 = var9;
                        var6 = var13;
                    }
                }
            }
        }

        if (var4 != null)
        {
            var3 = new MovingObjectPosition(var4);
        }

        if (var3 != null)
        {
            this.onImpact(var3);
        }

        if (this.posY <= -20 || this.posY >= 400)
        {
            this.setDead();
        }
    }

    protected void spawnParticles()
    {
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX, this.posY + 1D + Math.random(), this.posZ), new Vector3(0.0D, 0.0D, 0.0D), new Object[] { });
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX + Math.random() / 2, this.posY + 1D + Math.random() / 2, this.posZ), new Vector3(0.0D, 0.0D, 0.0D), new Object[] { });
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX, this.posY + 1D + Math.random(), this.posZ + Math.random()), new Vector3(0.0D, 0.0D, 0.0D), new Object[] { });
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX - Math.random() / 2, this.posY + 1D + Math.random() / 2, this.posZ), new Vector3(0.0D, 0.0D, 0.0D), new Object[] { });
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX, this.posY + 1D + Math.random(), this.posZ - Math.random()), new Vector3(0.0D, 0.0D, 0.0D), new Object[] { });
    }

    protected void onImpact(MovingObjectPosition moving)
    {
        if (!this.worldObj.isRemote)
        {
            if (moving != null)
            {
                Block b = this.worldObj.getBlock(moving.blockX, moving.blockY + 1, moving.blockZ);

                if (b != null && b.isAir(this.worldObj, moving.blockX, moving.blockY + 1, moving.blockZ))
                {
                    this.worldObj.setBlock(moving.blockX, moving.blockY + 1, moving.blockZ, KapteynBBlocks.fallen_ice_crystal_meteor, 0, 3);
                }
                if (moving.entityHit != null)
                {
                    moving.entityHit.attackEntityFrom(EntityIceCrystalMeteor.causeMeteorDamage(this, this.shootingEntity), ConfigManagerCore.hardMode ? 12F : 6F);
                }
            }
            this.worldObj.newExplosion((Entity) null, this.posX, this.posY, this.posZ, this.size / 3 + 2, false, true);
        }
        this.setDead();
    }

    public static DamageSource causeMeteorDamage(EntityIceCrystalMeteor par0EntityMeteor, Entity par1Entity)
    {
        if (par1Entity != null && par1Entity instanceof EntityPlayer)
        {
            StatCollector.translateToLocalFormatted("death." + "meteor", ((EntityPlayer) par1Entity).getGameProfile().getName() + " was hit by a meteor! That's gotta hurt!");
        }
        return new EntityDamageSourceIndirect("explosion", par0EntityMeteor, par1Entity).setProjectile();
    }

    @Override
    public boolean func_145774_a(Explosion explosion, World world, int x, int y, int z, Block block, float p_145774_7_)
    {
        return ConfigManagerCore.meteorBlockDamageEnabled;
    }

    @Override
    protected void entityInit()
    {
        this.dataWatcher.addObject(16, this.size);
        this.noClip = true;
    }

    public int getSize()
    {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    public void setSize(int par1)
    {
        this.dataWatcher.updateObject(16, Integer.valueOf(par1));
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {}
}