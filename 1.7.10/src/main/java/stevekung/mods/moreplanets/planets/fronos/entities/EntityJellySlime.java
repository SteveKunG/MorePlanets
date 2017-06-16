/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class EntityJellySlime extends EntityLiving implements IMob
{
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private int slimeJumpDelay;

    public EntityJellySlime(World par1World)
    {
        super(par1World);
        int i = 1 << this.rand.nextInt(2);
        this.yOffset = 0.0F;
        this.slimeJumpDelay = this.rand.nextInt(20) + 10;
        this.setSlimeSize(i);
        this.setTameSkin(this.rand.nextInt(8));
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1018);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)1));
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }

    public void setSlimeSize(int par1)
    {
        this.dataWatcher.updateObject(16, new Byte((byte)par1));
        this.setSize(0.6F * par1, 0.6F * par1);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(par1 * par1);
        this.setHealth(this.getMaxHealth());
        this.experienceValue = par1;
    }

    public int getSlimeSize()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("SlimeType", this.getJellySlimeType());
        par1NBTTagCompound.setInteger("Size", this.getSlimeSize() - 1);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setTameSkin(par1NBTTagCompound.getInteger("SlimeType"));
        this.setSlimeSize(par1NBTTagCompound.getInteger("Size") + 1);
    }

    protected String getJumpSound()
    {
        return this.getSlimeSize() >= 2 ? "mob.slime.big" : "mob.slime.small";
    }

    @Override
    public void onUpdate()
    {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0)
        {
            this.isDead = true;
        }

        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        boolean flag = this.onGround;
        super.onUpdate();
        int i;
        int particles = this.getJellySlimeType();

        if (this.onGround && !flag)
        {
            i = this.getSlimeSize();

            for (int j = 0; j < i * 8; ++j)
            {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * i * 0.5F * f1;

                if (particles == 0)
                {
                    MorePlanetsCore.proxy.spawnParticle("jellyGrape", this.posX + f2, this.boundingBox.minY, this.posZ + f3);
                }
                else if (particles == 1)
                {
                    MorePlanetsCore.proxy.spawnParticle("jellyRaspberry", this.posX + f2, this.boundingBox.minY, this.posZ + f3);
                }
                else if (particles == 2)
                {
                    MorePlanetsCore.proxy.spawnParticle("jellyStrawberry", this.posX + f2, this.boundingBox.minY, this.posZ + f3);
                }
                else if (particles == 3)
                {
                    MorePlanetsCore.proxy.spawnParticle("jellyBerry", this.posX + f2, this.boundingBox.minY, this.posZ + f3);
                }
                else if (particles == 4)
                {
                    MorePlanetsCore.proxy.spawnParticle("jellyLime", this.posX + f2, this.boundingBox.minY, this.posZ + f3);
                }
                else if (particles == 5)
                {
                    MorePlanetsCore.proxy.spawnParticle("jellyOrange", this.posX + f2, this.boundingBox.minY, this.posZ + f3);
                }
                else if (particles == 6)
                {
                    MorePlanetsCore.proxy.spawnParticle("jellyGreen", this.posX + f2, this.boundingBox.minY, this.posZ + f3);
                }
                else if (particles == 7)
                {
                    MorePlanetsCore.proxy.spawnParticle("jellyLemon", this.posX + f2, this.boundingBox.minY, this.posZ + f3);
                }
            }

            if (this.makesSoundOnLand())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }
            this.squishAmount = -0.5F;
        }
        else if (!this.onGround && flag)
        {
            this.squishAmount = 1.0F;
        }

        this.alterSquishAmount();

        if (this.worldObj.isRemote)
        {
            i = this.getSlimeSize();
            this.setSize(0.6F * i, 0.6F * i);
        }
    }

    @Override
    protected void updateEntityActionState()
    {
        this.despawnEntity();
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

        if (entityplayer != null)
        {
            this.faceEntity(entityplayer, 10.0F, 20.0F);
        }

        if (this.onGround && this.slimeJumpDelay-- <= 0)
        {
            this.slimeJumpDelay = this.getJumpDelay();

            if (entityplayer != null)
            {
                this.slimeJumpDelay /= 3;
            }

            this.isJumping = true;

            if (this.makesSoundOnJump())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
            this.moveForward = 1 * this.getSlimeSize();
        }
        else
        {
            this.isJumping = false;

            if (this.onGround)
            {
                this.moveStrafing = this.moveForward = 0.0F;
            }
        }
    }

    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.6F;
    }

    protected int getJumpDelay()
    {
        return this.rand.nextInt(20) + 10;
    }

    protected EntityJellySlime createInstance()
    {
        return new EntityJellySlime(this.worldObj);
    }

    @Override
    public void setDead()
    {
        int i = this.getSlimeSize();

        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
        {
            int j = 2 + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                float f = (k % 2 - 0.5F) * i / 4.0F;
                float f1 = (k / 2 - 0.5F) * i / 4.0F;
                EntityJellySlime entityslime = this.createInstance();
                entityslime.setSlimeSize(i / 2);
                entityslime.setTameSkin(this.getJellySlimeType());
                entityslime.setLocationAndAngles(this.posX + f, this.posY + 0.5D, this.posZ + f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(entityslime);
            }
        }
        super.setDead();
    }

    @Override
    protected String getHurtSound()
    {
        return this.getSlimeSize() >= 2 ? "mob.slime.big" : "mob.slime.small";
    }

    @Override
    protected String getDeathSound()
    {
        return this.getSlimeSize() >= 2 ? "mob.slime.big" : "mob.slime.small";
    }

    public int getJellySlimeType()
    {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    public void setTameSkin(int par1)
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)par1));
    }

    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
        if (this.getSlimeSize() == 1)
        {
            int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

            for (int k = 0; k < j; ++k)
            {
                this.entityDropItem(new ItemStack(FronosItems.jelly, 1, this.getJellySlimeType()), 0.0F);
            }
        }
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F * this.getSlimeSize();
    }

    @Override
    public int getVerticalFaceSpeed()
    {
        return 0;
    }

    protected boolean makesSoundOnJump()
    {
        return this.getSlimeSize() > 0;
    }

    protected boolean makesSoundOnLand()
    {
        return this.getSlimeSize() > 2;
    }
}