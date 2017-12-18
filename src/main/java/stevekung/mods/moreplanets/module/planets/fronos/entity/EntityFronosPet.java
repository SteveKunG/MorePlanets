package stevekung.mods.moreplanets.module.planets.fronos.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.fronos.entity.ai.EntityAIFaceTexture;
import stevekung.mods.moreplanets.module.planets.fronos.entity.ai.EntityAIFronosPanic;
import stevekung.mods.moreplanets.module.planets.fronos.entity.ai.EntityAIFronosTempt;
import stevekung.mods.moreplanets.module.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.util.entity.ai.PathNavigateGroundMP;

public abstract class EntityFronosPet extends EntityTameable
{
    protected int timeUntilToDropItem;
    public int closeEyeTimer;
    public int panicTimer;
    public int hungryTimer;
    protected EntityAIFaceTexture aiTexture;
    protected EntityAIFronosPanic aiPanic;
    protected EntityAIFronosTempt aiTempt;

    public EntityFronosPet(World world)
    {
        super(world);
    }

    @Override
    protected PathNavigate getNewNavigator(World world)
    {
        return new PathNavigateGroundMP(this, world);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 10)
        {
            this.closeEyeTimer = 20;
        }
        else if (id == 11)
        {
            this.panicTimer = 30;
        }
        else if (id == 12)
        {
            this.hungryTimer = 10;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    protected void updateAITasks()
    {
        this.closeEyeTimer = this.aiTexture.getEatingGrassTimer();
        this.panicTimer = this.aiPanic.getTimer();
        this.hungryTimer = this.aiTempt.getTimer();
        super.updateAITasks();
    }

    @Override
    public void onLivingUpdate()
    {
        if (this.worldObj.isRemote)
        {
            this.closeEyeTimer = Math.max(0, this.closeEyeTimer - 1);
            this.panicTimer = Math.max(0, this.panicTimer - 1);

            if (!this.aiTempt.shouldExecute())
            {
                this.hungryTimer = Math.max(0, this.hungryTimer - 1);
            }
        }
        super.onLivingUpdate();
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.getLayItem() != null && !this.isChild() && !this.worldObj.isRemote && --this.timeUntilToDropItem <= 0)
        {
            this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.entityDropItem(this.getLayItem(), 1.0F);
            this.timeUntilToDropItem = this.rand.nextInt(6000) + 2000;
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);

        if (nbt.hasKey("ItemLayTime"))
        {
            this.timeUntilToDropItem = nbt.getInteger("ItemLayTime");
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("ItemLayTime", this.timeUntilToDropItem);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block)
    {
        this.playSound("moreplanets:mob.fronos.step", 0.15F, 1.0F);
    }

    @Override
    protected String getLivingSound()
    {
        return "moreplanets:mob.fronos.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "moreplanets:mob.fronos.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "moreplanets:mob.fronos.death";
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player)
    {
        return 1 + this.worldObj.rand.nextInt(6);
    }

    @Override
    public void setTamed(boolean tame)
    {
        super.setTamed(tame);

        if (tame)
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.getBlockState(this.getPosition().down()).getBlock() == FronosBlocks.FRONOS_GRASS;
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
        if (!(entity instanceof EntityFronosPet))
        {
            return false;
        }

        EntityFronosPet pet = (EntityFronosPet)entity;

        if (!pet.isTamed())
        {
            return false;
        }
        if (pet.isSitting())
        {
            return false;
        }
        return this.isInLove() && pet.isInLove();
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack)
    {
        return itemStack != null && itemStack.getItem() == FronosItems.FRONOS_FOOD && itemStack.getItemDamage() == 0;
    }

    @Override
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemStack = player.inventory.getCurrentItem();
        boolean isTamedItem = itemStack != null && itemStack.getItem() == FronosItems.FRONOS_FOOD && itemStack.getItemDamage() == 1;

        if (super.interact(player))
        {
            return true;
        }
        if (this.isTamed())
        {
            if (isTamedItem)
            {
                this.heal(5.0F);

                if (itemStack.stackSize <= 0)
                {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                }
                this.aiSit.setSitting(!this.isSitting());
                return true;
            }
            if (this.isOwner(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemStack))
            {
                this.aiSit.setSitting(!this.isSitting());
            }
        }
        else if (isTamedItem)
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
                    this.setHealth(20.0F);
                    this.setOwnerId(player.getUniqueID().toString());
                    this.playTameEffect(true);
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
        return false;
    }

    public boolean isCloseEye()
    {
        return this.closeEyeTimer > 0;
    }

    public boolean isHungry()
    {
        return this.hungryTimer > 0;
    }

    public boolean isPanic()
    {
        return this.panicTimer > 0;
    }

    protected abstract ItemStack getLayItem();
}