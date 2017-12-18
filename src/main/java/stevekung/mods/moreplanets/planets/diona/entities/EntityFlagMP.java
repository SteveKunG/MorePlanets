/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPItems;

public class EntityFlagMP extends Entity
{
    public double xPosition;
    public double yPosition;
    public double zPosition;
    public boolean indestructable = false;

    public EntityFlagMP(World world)
    {
        super(world);
        this.yOffset = 1.5F;
        this.setSize(0.4F, 3F);
        this.ignoreFrustumCheck = true;
    }

    public EntityFlagMP(World par1World, double x, double y, double z, int dir)
    {
        this(par1World);
        this.setFacingAngle(dir);
        this.setPosition(x, y, z);
        this.xPosition = x;
        this.yPosition = y;
        this.zPosition = z;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float par2)
    {
        if (!this.worldObj.isRemote && !this.isDead && !this.indestructable)
        {
            boolean flag = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).capabilities.isCreativeMode;

            if (this.isEntityInvulnerable())
            {
                return false;
            }

            this.setBeenAttacked();
            this.setDamage(this.getDamage() + par2 * 10.0F);

            if (flag || this.getDamage() > 40.0F)
            {
                if (this.riddenByEntity != null)
                {
                    this.riddenByEntity.mountEntity(this);
                }
                if (flag)
                {
                    this.setDead();
                }
                else
                {
                    this.setDead();
                    this.entityDropItem(new ItemStack(MPItems.flag, 1, this.getType()), 0.0F);
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.flag, 1, this.getType());
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity par1Entity)
    {
        return par1Entity.boundingBox;
    }

    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected void entityInit()
    {
        this.dataWatcher.addObject(17, new Float(0.0F));
        this.dataWatcher.addObject(18, new Integer(-1));
        this.dataWatcher.addObject(19, new Integer(-1));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.setType(par1NBTTagCompound.getInteger("Type"));
        this.indestructable = par1NBTTagCompound.getBoolean("Indestructable");
        this.xPosition = par1NBTTagCompound.getDouble("TileX");
        this.yPosition = par1NBTTagCompound.getDouble("TileY");
        this.zPosition = par1NBTTagCompound.getDouble("TileZ");
        this.setFacingAngle(par1NBTTagCompound.getInteger("AngleI"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("Type", Integer.valueOf(this.getType()));
        par1NBTTagCompound.setBoolean("Indestructable", this.indestructable);
        par1NBTTagCompound.setInteger("AngleI", this.getFacingAngle());
        par1NBTTagCompound.setDouble("TileX", this.xPosition);
        par1NBTTagCompound.setDouble("TileY", this.yPosition);
        par1NBTTagCompound.setDouble("TileZ", this.zPosition);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        Vector3 vec = new Vector3(this.posX, this.posY, this.posZ);
        vec = vec.translate(new Vector3(0, -1, 0));
        Block blockAt = vec.getBlock(this.worldObj);

        if (blockAt != null)
        {
            if (blockAt instanceof BlockFence)
            {
            }
            else if (blockAt.isAir(this.worldObj, vec.intX(), vec.intY(), vec.intZ()))
            {
                this.motionY -= 0.02F;
            }
        }
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }

    @Override
    public boolean interactFirst(EntityPlayer par1EntityPlayer)
    {
        if (!this.worldObj.isRemote)
        {
            this.setFacingAngle(this.getFacingAngle() + 3);
        }
        return true;
    }

    public void setDamage(float par1)
    {
        this.dataWatcher.updateObject(17, Float.valueOf(par1));
    }

    public float getDamage()
    {
        return this.dataWatcher.getWatchableObjectFloat(17);
    }

    public void setType(int par1)
    {
        this.dataWatcher.updateObject(18, Integer.valueOf(par1));
    }

    public int getType()
    {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    public void setFacingAngle(int par1)
    {
        this.dataWatcher.updateObject(19, Integer.valueOf(par1));
    }

    public int getFacingAngle()
    {
        return this.dataWatcher.getWatchableObjectInt(19);
    }
}