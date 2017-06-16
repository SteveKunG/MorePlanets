/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.entities.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.siriusb.world.SiriusExplosion;

public class EntitySiriusSmallFireball extends EntityFireball
{
    public EntitySiriusSmallFireball(World world)
    {
        super(world);
        this.setSize(0.3125F, 0.3125F);
        this.setCanExplode(false);
    }

    public EntitySiriusSmallFireball(World world, EntityLivingBase living, double x, double y, double z)
    {
        super(world, living, x, y, z);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntitySiriusSmallFireball(World world, double x, double y, double z, double moX, double moY, double moZ)
    {
        super(world, x, y, z, moX, moY, moZ);
        this.setSize(0.3125F, 0.3125F);
    }

    @Override
    public void entityInit()
    {
        this.dataWatcher.addObject(16, Byte.valueOf((byte) 1));
    }

    @Override
    protected void onImpact(MovingObjectPosition moving)
    {
        if (!this.worldObj.isRemote)
        {
            if (moving.entityHit != null)
            {
                if (!moving.entityHit.isImmuneToFire() && moving.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), this.getCanExplode() ? 6.0F : 5.0F))
                {
                    moving.entityHit.setFire(5);
                }
            }
            else
            {
                int i = moving.blockX;
                int j = moving.blockY;
                int k = moving.blockZ;

                switch (moving.sideHit)
                {
                case 0:
                    --j;
                    break;
                case 1:
                    ++j;
                    break;
                case 2:
                    --k;
                    break;
                case 3:
                    ++k;
                    break;
                case 4:
                    --i;
                    break;
                case 5:
                    ++i;
                }

                if (this.worldObj.isAirBlock(i, j, k))
                {
                    this.worldObj.setBlock(i, j, k, SiriusBBlocks.sirius_fire);
                }
                if (this.getCanExplode())
                {
                    this.explode();
                }
            }
            if (this.getCanExplode())
            {
                this.explode();
            }
            this.setDead();
        }
    }

    private void explode()
    {
        SiriusExplosion explosion = new SiriusExplosion(this.worldObj, this, this.posX, this.posY, this.posZ, 4.0F);
        explosion.isFlaming = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
        explosion.isSmoking = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
        explosion.doExplosionA();
        explosion.doExplosionB(true);
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            if (this.getCanExplode() == true)
            {
                this.setBeenAttacked();

                if (p_70097_1_.getEntity() != null)
                {
                    Vec3 vec3 = p_70097_1_.getEntity().getLookVec();

                    if (vec3 != null)
                    {
                        this.motionX = vec3.xCoord;
                        this.motionY = vec3.yCoord;
                        this.motionZ = vec3.zCoord;
                        this.accelerationX = this.motionX * 0.1D;
                        this.accelerationY = this.motionY * 0.1D;
                        this.accelerationZ = this.motionZ * 0.1D;
                    }

                    if (p_70097_1_.getEntity() instanceof EntityLivingBase)
                    {
                        this.shootingEntity = (EntityLivingBase)p_70097_1_.getEntity();
                    }
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
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("CanExplode", this.getCanExplode());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);

        if (nbt.getBoolean("CanExplode"))
        {
            this.setCanExplode(true);
        }
    }

    public boolean getCanExplode()
    {
        return this.getDataWatcher().getWatchableObjectByte(16) == 1;
    }

    public void setCanExplode(boolean can)
    {
        this.getDataWatcher().updateObject(16, Byte.valueOf((byte) (can ? 1 : 0)));
    }
}