/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.entities.ai.EntityAITemptMP;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.ai.EntityAICreamCatSit;

public class EntityCreamCat extends EntityTameable
{
    private EntityAITemptMP aiTempt;

    public EntityCreamCat(World world)
    {
        super(world);
        this.setSize(0.6F, 0.8F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, this.aiTempt = new EntityAITemptMP(this, 0.6D, new ItemStack(Items.fish), true));
        this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
        this.tasks.addTask(6, new EntityAICreamCatSit(this, 1.33D));
        this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
        this.tasks.addTask(8, new EntityAIOcelotAttack(this));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(10, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, EntityChicken.class, 750, false));
        this.targetTasks.addTask(2, new EntityAITargetNonTamed(this, EntityStrawberryChicken.class, 750, false));
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }

    @Override
    public void updateAITick()
    {
        if (this.getMoveHelper().isUpdating())
        {
            double d0 = this.getMoveHelper().getSpeed();

            if (d0 == 0.6D)
            {
                this.setSneaking(true);
                this.setSprinting(false);
            }
            else if (d0 == 1.33D)
            {
                this.setSneaking(false);
                this.setSprinting(true);
            }
            else
            {
                this.setSneaking(false);
                this.setSprinting(false);
            }
        }
        else
        {
            this.setSneaking(false);
            this.setSprinting(false);
        }
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.300000011920929D);
    }

    @Override
    protected void fall(float fall)
    {
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("CatType", this.getTameSkin());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.setTameSkin(nbt.getInteger("CatType"));
    }

    @Override
    protected String getLivingSound()
    {
        return this.isTamed() ? "mob.cat.meow" : this.rand.nextInt(4) == 0 ? "mob.cat.purreow" : this.isInLove() ? "mob.cat.purr" : null;
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.cat.hitt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.cat.hitt";
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        this.aiSit.setSitting(false);
        return super.attackEntityFrom(damageSource, damage);
    }

    @Override
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemStack = player.inventory.getCurrentItem();

        if (this.isTamed())
        {
            if (this.func_152114_e(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemStack))
            {
                this.aiSit.setSitting(!this.isSitting());
            }
        }
        else if (this.aiTempt.isRunning() && itemStack != null && itemStack.getItem() == Items.fish && player.getDistanceSqToEntity(this) < 9.0D)
        {
            if (!player.capabilities.isCreativeMode)
            {
                itemStack.stackSize -= 1;
            }
            if (itemStack.stackSize <= 0)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }
            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.setTameSkin(this.worldObj.rand.nextInt(6) + 1);
                    this.func_152115_b(player.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.aiSit.setSitting(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }
            return true;
        }
        if (itemStack != null && itemStack.getItem() == MPItems.spawn_egg_mp && itemStack.getItemDamage() == 1025)
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
        return super.interact(player);
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1025);
    }

    @Override
    public EntityCreamCat createChild(EntityAgeable cat)
    {
        EntityCreamCat creamCat = new EntityCreamCat(this.worldObj);

        if (this.isTamed())
        {
            creamCat.func_152115_b(this.func_152113_b());
            creamCat.setTamed(true);
            creamCat.setTameSkin(this.getTameSkin());
        }
        return creamCat;
    }

    @Override
    public boolean isBreedingItem(ItemStack item)
    {
        return item != null && item.getItem() == Items.fish;
    }

    @Override
    public boolean canMateWith(EntityAnimal entity)
    {
        if (entity == this)
        {
            return false;
        }
        if (!this.isTamed())
        {
            return false;
        }
        if (!(entity instanceof EntityCreamCat))
        {
            return false;
        }
        EntityCreamCat entityocelot = (EntityCreamCat)entity;
        return entityocelot.isTamed();
    }

    public int getTameSkin()
    {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    public void setTameSkin(int skin)
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)skin));
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);
        Block block = this.worldObj.getBlock(i, j - 1, k);
        return block == FronosBlocks.fronos_grass;
    }

    @Override
    public String getCommandSenderName()
    {
        return this.isTamed() ? StatCollector.translateToLocal("entity.creamCat.name") : this.hasCustomNameTag() ? this.getCustomNameTag() : super.getCommandSenderName();
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData entity)
    {
        entity = super.onSpawnWithEgg(entity);

        if (this.worldObj.rand.nextInt(7) == 0)
        {
            for (int i = 0; i < 2; i++)
            {
                EntityCreamCat creamCat = new EntityCreamCat(this.worldObj);
                creamCat.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                creamCat.setGrowingAge(-24000);
                this.worldObj.spawnEntityInWorld(creamCat);
            }
        }
        return entity;
    }
}