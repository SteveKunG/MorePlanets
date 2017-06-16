/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;

public class EntityPlutoAlien extends EntityMob implements IEntityBreathable
{
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 20;
    private int explosionRadius = 5;

    public EntityPlutoAlien(World world)
    {
        super(world);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.setSize(0.9F, 1.8F);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
    }

    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte) - 1));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setShort("Fuse", (short)this.fuseTime);
        nbt.setByte("ExplosionRadius", (byte)this.explosionRadius);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);

        if (nbt.hasKey("Fuse", 99))
        {
            this.fuseTime = nbt.getShort("Fuse");
        }
        if (nbt.hasKey("ExplosionRadius", 99))
        {
            this.explosionRadius = nbt.getByte("ExplosionRadius");
        }
    }

    @Override
    public void onUpdate()
    {
        if (this.isEntityAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;

            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound("creeper.primed", 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0)
            {
                this.timeSinceIgnited = 0;
            }
            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
        }
        super.onUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damageAmount)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (super.attackEntityFrom(damageSource, damageAmount))
        {
            if (this.getHealth() < 10.0F && this.rand.nextInt(24) == 0)
            {
                this.setCreeperState(1);
            }
            return true;
        }
        return false;
    }

    @Override
    protected String getHurtSound()
    {
        this.playSound("mob.creeper.say", 0.7F, 0.5F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
        this.playSound(Block.soundTypeStone.getBreakSound(), Block.soundTypeStone.getVolume(), Block.soundTypeStone.getPitch());
        return null;
    }

    @Override
    protected String getDeathSound()
    {
        this.playSound("mob.creeper.death", 0.7F, 0.5F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
        this.playSound(Block.soundTypeStone.getBreakSound(), Block.soundTypeStone.getVolume(), 0.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
        return null;
    }

    @SideOnly(Side.CLIENT)
    public float getCreeperFlashIntensity(float partialTicks)
    {
        return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * partialTicks) / (this.fuseTime - 2);
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int j = this.rand.nextInt(3);

        if (fortune > 0)
        {
            j += this.rand.nextInt(fortune + 1);
        }

        for (int k = 0; k < j; ++k)
        {
            int i = 0;

            if (this.rand.nextInt(3) == 0)
            {
                i = 10;
            }
            if (this.rand.nextInt(5) == 0)
            {
                i = 11;
            }
            if (drop && this.rand.nextInt(10) == 0)
            {
                i = 7;
            }
            this.entityDropItem(new ItemStack(PlutoBlocks.pluto_block, k, i), 0.0F);
        }
        for (int k = 0; k < j; ++k)
        {
            this.dropItem(Items.gunpowder, k);
        }
    }

    public int getCreeperState()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    public void setCreeperState(int state)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)state));
    }

    private void explode()
    {
        if (!this.worldObj.isRemote)
        {
            boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius, flag);
            this.setDead();
        }
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }
}