/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.entities.IEntityLivingPlanet;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.util.EnumDimensionType;

public class EntitySiriusMagmaCube extends EntityLiving implements IMob, IEntityBreathable, IEntityLivingPlanet
{
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private int slimeJumpDelay;

    public EntitySiriusMagmaCube(World par1World)
    {
        super(par1World);
        final int i = 1 << this.rand.nextInt(3);
        this.yOffset = 0.0F;
        this.slimeJumpDelay = this.rand.nextInt(30) + 20;
        this.setSlimeSize(i);
        this.isImmuneToFire = true;
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1032);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)1));
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

    @Override
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            if (this.isWet())
            {
                this.attackEntityFrom(DamageSource.drown, 1.0F);
            }
        }
        super.onLivingUpdate();
    }

    public int getSlimeSize()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Size", this.getSlimeSize() - 1);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setSlimeSize(par1NBTTagCompound.getInteger("Size") + 1);
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
        final boolean flag = this.onGround;
        super.onUpdate();
        int i;

        if (this.onGround && !flag)
        {
            i = this.getSlimeSize();

            for (int j = 0; j < i * 8; ++j)
            {
                final float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                final float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
                final float f2 = MathHelper.sin(f) * i * 0.5F * f1;
                final float f3 = MathHelper.cos(f) * i * 0.5F * f1;
                MorePlanetsCore.proxy.spawnParticle("siriusFlame", this.posX + f2, this.boundingBox.minY, this.posZ + f3);
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
        final EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

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

    @Override
    public int getTotalArmorValue()
    {
        return this.getSlimeSize() * 3;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }

    @Override
    public float getBrightness(float par1)
    {
        return 1.0F;
    }

    @Override
    public boolean isBurning()
    {
        return false;
    }

    protected int getJumpDelay()
    {
        return this.rand.nextInt(30) + 20 * 4;
    }

    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.9F;
    }

    @Override
    protected void jump()
    {
        this.motionY = 0.42F + this.getSlimeSize() * 0.1F;
        this.isAirBorne = true;
    }

    @Override
    protected void fall(float par1) {}

    protected boolean canDamagePlayer()
    {
        return true;
    }

    protected EntitySiriusMagmaCube createInstance()
    {
        return new EntitySiriusMagmaCube(this.worldObj);
    }

    @Override
    public void setDead()
    {
        final int i = this.getSlimeSize();

        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
        {
            final int j = 2 + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                final float f = (k % 2 - 0.5F) * i / 4.0F;
                final float f1 = (k / 2 - 0.5F) * i / 4.0F;
                final EntitySiriusMagmaCube entityslime = this.createInstance();
                entityslime.setSlimeSize(i / 2);
                entityslime.setLocationAndAngles(this.posX + f, this.posY + 0.5D, this.posZ + f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(entityslime);
            }
        }
        super.setDead();
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if (this.canDamagePlayer())
        {
            final int i = this.getSlimeSize();

            if (this.canEntityBeSeen(par1EntityPlayer) && this.getDistanceSqToEntity(par1EntityPlayer) < 0.6D * i * 0.6D * i && par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength()))
            {
                this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected int getAttackStrength()
    {
        return this.getSlimeSize();
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    @Override
    protected Item getDropItem()
    {
        return Items.magma_cream;
    }

    @Override
    protected void dropFewItems(boolean drop, int count)
    {
        final Item item = this.getDropItem();

        if (item != null && this.getSlimeSize() > 1)
        {
            int j = this.rand.nextInt(4) - 2;

            if (count > 0)
            {
                j += this.rand.nextInt(count + 1);
            }
            for (int k = 0; k < j; k++)
            {
                this.dropItem(item, 1);
            }
        }
    }

    protected String getJumpSound()
    {
        return this.getSlimeSize() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
    }

    @Override
    public boolean handleLavaMovement()
    {
        return false;
    }

    protected boolean makesSoundOnLand()
    {
        return true;
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

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public EnumDimensionType canLivingInDimension()
    {
        return EnumDimensionType.SIRIUS_B;
    }
}