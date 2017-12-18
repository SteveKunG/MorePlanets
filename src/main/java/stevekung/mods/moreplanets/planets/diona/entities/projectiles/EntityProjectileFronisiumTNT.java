/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities.projectiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaMinionCreeper;

public class EntityProjectileFronisiumTNT extends EntityFireball
{
    public EntityProjectileFronisiumTNT(World par1World)
    {
        super(par1World);
        this.setSize(1.0F, 1.0F);
    }

    public EntityProjectileFronisiumTNT(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7)
    {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
        this.setSize(1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public EntityProjectileFronisiumTNT(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.setSize(0.3125F, 0.3125F);
    }

    @Override
    public boolean isBurning()
    {
        return false;
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition)
    {
        if (!this.worldObj.isRemote)
        {
            if (movingObjectPosition.entityHit != null && !(movingObjectPosition.entityHit instanceof EntityCreeper))
            {
                movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 6.0F);
            }
            this.worldObj.newExplosion((Entity) null, this.posX, this.posY, this.posZ, 1.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            this.setDead();
        }
        if (this.rand.nextInt(8) == 0)
        {
            if (!this.worldObj.isRemote)
            {
                byte b0 = 1;

                if (this.rand.nextInt(16) == 0)
                {
                    b0 = 4;
                }

                for (int i = 0; i < b0; ++i)
                {
                    EntityDionaMinionCreeper creeper = new EntityDionaMinionCreeper(this.worldObj);
                    creeper.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    creeper.setAbsorptionAmount(16.0F);

                    if (this.rand.nextInt(4) == 0)
                    {
                        creeper.getDataWatcher().updateObject(17, Byte.valueOf((byte) 1));
                    }

                    this.worldObj.spawnEntityInWorld(creeper);
                }
            }
        }
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float light)
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, 0, j))
        {
            double d0 = (this.boundingBox.maxY - this.boundingBox.minY) * 0.66D;
            int k = MathHelper.floor_double(this.posY - this.yOffset + d0);
            return this.worldObj.getLightBrightnessForSkyBlocks(i, k, j, 0);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public float getBrightness(float light)
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, 0, j))
        {
            double d0 = (this.boundingBox.maxY - this.boundingBox.minY) * 0.66D;
            int k = MathHelper.floor_double(this.posY - this.yOffset + d0);
            return this.worldObj.getLightBrightness(i, k, j);
        }
        else
        {
            return 0.0F;
        }
    }
}