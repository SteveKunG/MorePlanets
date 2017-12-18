/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.entities.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseSlime;

public class EntityCheeseSpore extends EntityFireball
{
    public EntityCheeseSpore(World par1World)
    {
        super(par1World);
        this.setSize(1.0F, 1.0F);
    }

    public EntityCheeseSpore(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7)
    {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
        this.setSize(1.0F, 1.0F);
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
                movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 10.0F);
            }
            this.worldObj.newExplosion((Entity) null, this.posX, this.posY, this.posZ, 1.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            this.setDead();
        }
        if (this.rand.nextInt(4) == 0)
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
                    EntityCheeseSlime slime = new EntityCheeseSlime(this.worldObj);
                    slime.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    slime.setSlimeSize(this.worldObj.rand.nextInt(4));
                    slime.setAbsorptionAmount(6.0F);
                    this.worldObj.spawnEntityInWorld(slime);
                }
            }
        }
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }
}