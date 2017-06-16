/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.pluto.dimension.WorldProviderPluto;

public class EntityEuropaCrab extends EntityAnimal implements IEntityBreathable
{
    public EntityEuropaCrab(World world)
    {
        super(world);
        this.setSize(0.8F, 0.3F);
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Items.carrot, false));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.setCrabType(this.rand.nextInt(3));
    }

    @Override
    public void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15D);
    }

    @Override
    protected String getLivingSound()
    {
        return null;
    }

    @Override
    protected String getHurtSound()
    {
        return "europa:mob.crab";
    }

    @Override
    protected String getDeathSound()
    {
        return "europa:mob.crab";
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block)
    {
        this.playSound("mob.chicken.step", 0.15F, 1.0F);
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + p_70628_2_);

        for (int k = 0; k < j; ++k)
        {
            if (this.isBurning())
            {
                this.dropItem(Items.cooked_porkchop, 1);
            }
            else
            {
                this.dropItem(Items.porkchop, 1);
            }
        }
    }

    @Override
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemStack = player.inventory.getCurrentItem();

        if (itemStack != null && itemStack.getItem() == MPItems.spawn_egg_mp && itemStack.getItemDamage() == 1039)
        {
            if (!this.worldObj.isRemote)
            {
                EntityAgeable entityageable = this.createChild(this);

                if (entityageable != null)
                {
                    entityageable.setGrowingAge(-24000);
                    entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                    this.worldObj.spawnEntityInWorld(entityageable);

                    if (itemStack.hasDisplayName())
                    {
                        entityageable.setCustomNameTag(itemStack.getDisplayName());
                    }
                    if (!player.capabilities.isCreativeMode)
                    {
                        --itemStack.stackSize;

                        if (itemStack.stackSize <= 0)
                        {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                        }
                    }
                }
            }
            return true;
        }
        if (itemStack == null && player.isSneaking())//TODO
        {
            if (this.getCrabType() == 2)
            {
                if (!this.worldObj.isRemote)
                {
                    EntityItem item = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Items.diamond));
                    this.worldObj.spawnEntityInWorld(item);
                }
                this.setDead();
            }
            return true;
        }
        return super.interact(player);
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1039);
    }

    @Override
    public EntityEuropaCrab createChild(EntityAgeable entity)
    {
        return new EntityEuropaCrab(this.worldObj);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack)
    {
        return itemStack != null && itemStack.getItem() == Items.carrot;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        if (this.worldObj.provider instanceof WorldProviderPluto)//TODO
        {
            return this.posY > 45.0D && this.posY < 63.0D && this.worldObj.checkNoEntityCollision(this.boundingBox);
        }
        return false;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("CrabType", this.getCrabType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.setCrabType(nbt.getInteger("CrabType"));
    }

    public int getCrabType()
    {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    public void setCrabType(int type)
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)type));
    }
}