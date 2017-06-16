/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.achievement.AchievementsMP;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.diona.entities.ai.EntityAISpaceWolfBeg;

public class EntitySpaceWolf extends EntityTameable implements IEntityBreathable
{
    private float field_70926_e;
    private float field_70924_f;
    private boolean isShaking;
    private boolean field_70928_h;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    public EntitySpaceWolf(World world)
    {
        super(world);
        this.setSize(0.6F, 0.8F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAISpaceWolfBeg(this, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySheep.class, 200, false));
        this.setTamed(false);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);
        Block block = this.worldObj.getBlock(i, j - 1, k);
        int meta = this.worldObj.getBlockMetadata(i, j - 1, k);
        return block == DionaBlocks.diona_block && meta == 0;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.300000011920929D);

        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        }
    }

    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public void setAttackTarget(EntityLivingBase living)
    {
        super.setAttackTarget(living);

        if (living == null)
        {
            this.setAngry(false);
        }
        else if (!this.isTamed())
        {
            this.setAngry(true);
        }
    }

    @Override
    protected void updateAITick()
    {
        this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, new Float(this.getHealth()));
        this.dataWatcher.addObject(19, new Byte((byte)0));
        this.dataWatcher.addObject(20, new Byte((byte)BlockColored.func_150032_b(1)));
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block)
    {
        this.playSound("mob.wolf.step", 0.15F, 1.0F);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("Angry", this.isAngry());
        nbt.setByte("CollarColor", (byte)this.getCollarColor());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.setAngry(nbt.getBoolean("Angry"));

        if (nbt.hasKey("CollarColor", 99))
        {
            this.setCollarColor(nbt.getByte("CollarColor"));
        }
    }

    @Override
    protected String getLivingSound()
    {
        if (this.isAngry())
        {
            return "mob.wolf.growl";
        }
        if (this.rand.nextInt(3) == 0)
        {
            if (this.isTamed() && this.dataWatcher.getWatchableObjectFloat(18) < 10.0F)
            {
                return "mob.wolf.whine";
            }
            return "mob.wolf.panting";
        }
        return "mob.wolf.bark";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    protected Item getDropItem()
    {
        return Item.getItemFromBlock(Blocks.air);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!this.worldObj.isRemote && this.isShaking && !this.field_70928_h && !this.hasPath() && this.onGround)
        {
            this.field_70928_h = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
            this.worldObj.setEntityState(this, (byte)8);
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.field_70924_f = this.field_70926_e;

        if (this.func_70922_bv())
        {
            this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
        }
        else
        {
            this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
        }
        if (this.func_70922_bv())
        {
            this.numTicksToChaseTarget = 10;
        }
        if (this.isWet())
        {
            this.isShaking = true;
            this.field_70928_h = false;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        }
        else if ((this.isShaking || this.field_70928_h) && this.field_70928_h)
        {
            if (this.timeWolfIsShaking == 0.0F)
            {
                this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05F;

            if (this.prevTimeWolfIsShaking >= 2.0F)
            {
                this.isShaking = false;
                this.field_70928_h = false;
                this.prevTimeWolfIsShaking = 0.0F;
                this.timeWolfIsShaking = 0.0F;
            }
            if (this.timeWolfIsShaking > 0.4F)
            {
                float f1 = (float)this.boundingBox.minY;
                int i = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * 3.141593F) * 7.0F);

                for (int j = 0; j < i; j++)
                {
                    float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f3 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    this.worldObj.spawnParticle("splash", this.posX + f2, f1 + 0.8F, this.posZ + f3, this.motionX, this.motionY, this.motionZ);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean getWolfShaking()
    {
        return this.isShaking;
    }

    @SideOnly(Side.CLIENT)
    public float getShadingWhileShaking(float par1)
    {
        return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1) / 2.0F * 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float getShakeAngle(float par1, float par2)
    {
        float f = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1 + par2) / 1.8F;

        if (f < 0.0F)
        {
            f = 0.0F;
        }
        else if (f > 1.0F)
        {
            f = 1.0F;
        }
        return MathHelper.sin(f * 3.141593F) * MathHelper.sin(f * 3.141593F * 11.0F) * 0.15F * 3.141593F;
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float par1)
    {
        return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * par1) * 0.15F * 3.141593F;
    }

    @Override
    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    @Override
    public int getVerticalFaceSpeed()
    {
        if (this.isSitting())
        {
            return 20;
        }
        return super.getVerticalFaceSpeed();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }

        Entity localEntity = source.getEntity();
        this.aiSit.setSitting(false);

        if (localEntity != null && !(localEntity instanceof EntityPlayer) && !(localEntity instanceof EntityArrow))
        {
            par2 = (par2 + 1.0F) / 2.0F;
        }
        return super.attackEntityFrom(source, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        int i = this.isTamed() ? 4 : 2;
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    @Override
    public void setTamed(boolean tame)
    {
        super.setTamed(tame);

        if (tame)
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        }
    }

    @Override
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemStack = player.inventory.getCurrentItem();

        if (this.isTamed())
        {
            if (itemStack != null)
            {
                if (itemStack.getItem() instanceof ItemFood)
                {
                    ItemFood localItemFood = (ItemFood)itemStack.getItem();

                    if (localItemFood.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F)
                    {
                        if (!player.capabilities.isCreativeMode)
                        {
                            itemStack.stackSize -= 1;
                        }

                        this.heal(localItemFood.func_150905_g(itemStack));

                        if (itemStack.stackSize <= 0)
                        {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                        }
                        return true;
                    }
                }
                else if (itemStack.getItem() == Items.dye)
                {
                    int i = BlockColored.func_150032_b(itemStack.getItemDamage());

                    if (i != this.getCollarColor())
                    {
                        this.setCollarColor(i);

                        if (!player.capabilities.isCreativeMode)
                        {
                            if (--itemStack.stackSize <= 0)
                            {
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                            }
                        }
                        return true;
                    }
                }
            }
            if (this.func_152114_e(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemStack))
            {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.setPathToEntity(null);
                this.setTarget(null);
                this.setAttackTarget(null);
            }
        }
        else if (itemStack != null && itemStack.getItem() == Items.bone && !this.isAngry())
        {
            if (!player.capabilities.isCreativeMode)
            {
                itemStack.stackSize -= 1;
            }
            if (itemStack.stackSize <= 0)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
            }
            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.setPathToEntity(null);
                    this.setAttackTarget(null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.func_152115_b(player.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                    player.triggerAchievement(AchievementsMP.tameSpaceWolf);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }
            return true;
        }

        if (itemStack != null && itemStack.getItem() == MPItems.spawn_egg_mp && itemStack.getItemDamage() == 1000)
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
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1000);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte health)
    {
        if (health == 8)
        {
            this.field_70928_h = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        }
        else
        {
            super.handleHealthUpdate(health);
        }
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation()
    {
        if (this.isAngry())
        {
            return 1.53938F;
        }
        if (this.isTamed())
        {
            return (0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * 3.141593F;
        }
        return 0.6283186F;
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack)
    {
        if (itemStack == null)
        {
            return false;
        }
        if (!(itemStack.getItem() instanceof ItemFood))
        {
            return false;
        }
        return ((ItemFood)itemStack.getItem()).isWolfsFavoriteMeat();
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    public boolean isAngry()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 0x2) != 0;
    }

    public void setAngry(boolean angry)
    {
        int i = this.dataWatcher.getWatchableObjectByte(16);

        if (angry)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(i | 2)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(i & -3)));
        }
    }

    public int getCollarColor()
    {
        return this.dataWatcher.getWatchableObjectByte(20) & 15;
    }

    public void setCollarColor(int color)
    {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)(color & 15)));
    }

    @Override
    public EntitySpaceWolf createChild(EntityAgeable entity)
    {
        EntitySpaceWolf localEntityWolf = new EntitySpaceWolf(this.worldObj);
        String str = this.func_152113_b();

        if (str != null && str.trim().length() > 0)
        {
            localEntityWolf.func_152115_b(str);
            localEntityWolf.setTamed(true);
        }
        return localEntityWolf;
    }

    public void func_70918_i(boolean par1)
    {
        if (par1)
        {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
        }
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
        if (!(entity instanceof EntitySpaceWolf))
        {
            return false;
        }

        EntitySpaceWolf localEntityWolf = (EntitySpaceWolf)entity;

        if (!localEntityWolf.isTamed())
        {
            return false;
        }
        if (localEntityWolf.isSitting())
        {
            return false;
        }
        return this.isInLove() && localEntityWolf.isInLove();
    }

    public boolean func_70922_bv()
    {
        return this.dataWatcher.getWatchableObjectByte(19) == 1;
    }

    @Override
    protected boolean canDespawn()
    {
        return !this.isTamed() && this.ticksExisted > 2400;
    }

    @Override
    public boolean func_142018_a(EntityLivingBase living, EntityLivingBase living2)
    {
        if (living instanceof EntityCreeper || living instanceof EntityGhast)
        {
            return false;
        }
        if (living instanceof EntitySpaceWolf)
        {
            EntitySpaceWolf localEntityWolf = (EntitySpaceWolf)living;

            if (localEntityWolf.isTamed() && localEntityWolf.getOwner() == living2)
            {
                return false;
            }
        }
        if (living instanceof EntityPlayer && living2 instanceof EntityPlayer && !((EntityPlayer)living2).canAttackPlayer((EntityPlayer)living))
        {
            return false;
        }
        if (living instanceof EntityHorse && ((EntityHorse)living).isTame())
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    protected void dropRareDrop(int par1)
    {
        switch (this.rand.nextInt(10))
        {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
            break;
        case 7:
            //Oxygen tank half empty or less
            this.entityDropItem(new ItemStack(GCItems.oxTankMedium, 1, 901 + this.rand.nextInt(900)), 0.0F);
            break;
        case 8:
            this.dropItem(Item.getItemFromBlock(Blocks.glass), 1);
            break;
        case 9:
            this.dropItem(GCItems.oxygenGear, 1);
            break;
        case 10:
            this.dropItem(GCItems.oxMask, 1);
            break;
        }
    }
}