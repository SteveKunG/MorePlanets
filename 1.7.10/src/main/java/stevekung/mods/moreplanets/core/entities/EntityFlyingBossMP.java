/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlyingBossMP extends EntityMob
{
    public EntityFlyingBossMP(World world)
    {
        super(world);
    }

    @Override
    protected void fall(float fall) {}

    @Override
    protected void updateFallState(double par1, boolean par2) {}

    @Override
    public void moveEntityWithHeading(float par1, float par2)
    {
        if (this.isInWater())
        {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.handleLavaMovement())
        {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else
        {
            float f1 = 0.91F;

            if (this.onGround)
            {
                f1 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            this.moveFlying(par1, par2, this.onGround ? 0.1F * f2 : 0.02F);
            f1 = 0.91F;

            if (this.onGround)
            {
                f1 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= f1;
            this.motionY *= f1;
            this.motionZ *= f1;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.posX - this.prevPosX;
        double d2 = this.posZ - this.prevPosZ;
        float f3 = MathHelper.sqrt_double(d1 * d1 + d2 * d2) * 4.0F;

        if (f3 > 1.0F)
        {
            f3 = 1.0F;
        }
        this.limbSwingAmount += (f3 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    @Override
    public boolean isOnLadder()
    {
        return false;
    }
}