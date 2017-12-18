/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.entities;

import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;

public class EntityEledos extends EntityAnimal
{
    private EntityAIControlledByPlayer aiControlledByPlayer;

    public EntityEledos(World world)
    {
        super(world);
        this.setSize(0.9F, 0.9F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.3F));
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
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    @Override
    protected void updateAITasks()
    {
        super.updateAITasks();
    }

    /*@Override
	public boolean canBeSteered()
	{
		return true;
	}*/

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
    }

    @Override
    protected String getLivingSound()
    {
        return null;
    }

    @Override
    protected String getHurtSound()
    {
        return null;
    }

    @Override
    protected String getDeathSound()
    {
        return null;
    }

    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound(null, 0.15F, 1.0F);
    }

    @Override
    protected Item getDropItem()
    {
        if (this.isBurning())
        {
            return Items.cooked_porkchop;
        }
        return Items.porkchop;
    }

    @Override
    protected void dropFewItems(boolean p_dropFewItems_1_, int p_dropFewItems_2_)
    {
        int i = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + p_dropFewItems_2_);

        for (int j = 0; j < i; j++)
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
        ItemStack itemstack = player.inventory.getCurrentItem();
        String owner = this.getEntityData().getString("Owner");

        if (itemstack != null)
        {
            if (!this.worldObj.isRemote && owner == "" && itemstack == new ItemStack(DionaItems.diona_dungeon_key))
            {
                if (!owner.equalsIgnoreCase("") && !player.getGameProfile().getName().equalsIgnoreCase(owner))
                {
                    this.sendMsg(player, owner);
                    return false;
                }

                this.useItem(player, itemstack);
                this.func_146082_f(player);
                owner = player.getGameProfile().getName();
                this.getEntityData().setString("Owner", owner);
                //par1.inventory.addItemStackToInventory(new ItemStack(MainGGbike.key.itemID, 1, 0));

            }
            else if (player.getGameProfile().getName().equalsIgnoreCase(owner) && itemstack == new ItemStack(DionaItems.diona_dungeon_key))
            {
                this.useItem(player, itemstack);
                player.inventory.addItemStackToInventory(new ItemStack(GCItems.buggy, 1, 0));
                this.setDead();
            }
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                if (player.getGameProfile().getName().equalsIgnoreCase(owner))
                {
                    player.mountEntity(this);
                }
                else
                {
                    this.sendMsg(player, owner);
                }
            }
        }
        return true;
    }

    private void sendMsg(EntityPlayer par1, String owner)
    {
        if (owner.equalsIgnoreCase(""))
        {
            par1.addChatMessage(new ChatComponentText("You No Owner"));
        }
        else
        {
            par1.addChatMessage(new ChatComponentText("Animal Owner Is " + owner));
        }
    }

    private void useItem(EntityPlayer par1, ItemStack itemstack)
    {
        if (!par1.capabilities.isCreativeMode)
        {
            --itemstack.stackSize;

            if (itemstack.stackSize <= 0)
            {
                par1.inventory.setInventorySlotContents(par1.inventory.currentItem, (ItemStack)null);
            }
        }
    }

    @Override
    public void moveEntityWithHeading(float par1, float par2)
    {
        if (this.riddenByEntity != null)
        {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            par1 = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
            par2 = ((EntityLivingBase)this.riddenByEntity).moveForward;
            this.stepHeight = 1.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            this.playSound("bike:bike.bikebike", par2, par2);

            if (!this.worldObj.isRemote)
            {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(par1, par2);
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d0 = this.posX - this.prevPosX;
            double d1 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

            if (f4 > 1.0F)
            {
                f4 = 1.0F;
            }

            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        }
        else
        {
            this.stepHeight = 0.5F;
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(par1, par2);
        }
    }

    @Override
    public EntityEledos createChild(EntityAgeable entity)
    {
        return new EntityEledos(this.worldObj);
    }
}