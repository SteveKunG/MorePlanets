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
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.entities.ai.EntityAITemptMP;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class EntityStarfish extends EntityAnimal
{
    public EntityStarfish(World par1World)
    {
        super(par1World);
        this.setSize(0.5F, 0.15F);
        this.tasks.addTask(0, new EntityAIPanic(this, 1.4D));
        this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(2, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAITemptMP(this, 1.1D, new ItemStack(FronosItems.fronos_food2, 1, 0), false));
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.boundingBox.minY);
        int z = MathHelper.floor_double(this.posZ);
        Block block = this.worldObj.getBlock(x, y - 1, z);
        int meta = this.worldObj.getBlockMetadata(x, y - 1, z);

        if (block == FronosBlocks.fronos_sand && meta == 1 || block == Blocks.sand)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemStack = player.inventory.getCurrentItem();

        if (itemStack != null && itemStack.getItem() == MPItems.spawn_egg_mp && itemStack.getItemDamage() == 1021)
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
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1021);
    }

    @Override
    public void knockBack(Entity par1Entity, float par2, double par3, double par5)
    {
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.025D);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    @Override
    protected void fall(float par1) {}

    @Override
    protected String getHurtSound()
    {
        return "mob.slime.small";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.slime.small";
    }

    @Override
    protected Item getDropItem()
    {
        return Item.getItemFromBlock(Blocks.air);
    }

    @Override
    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        int meta = par1ItemStack.getItemDamage();
        return par1ItemStack.getItem() == FronosItems.fronos_food2 && meta == 1;
    }

    /*@Override
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);

        for (int k = 0; k < j; ++k)
        {
            this.dropItem(Item.feather.itemID, 1);
        }

        if (this.isBurning())
        {
            this.dropItem(Item.chickenCooked.itemID, 1);
        }
        else
        {
            this.dropItem(Item.chickenRaw.itemID, 1);
        }
    }*/

    /*@Override
    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack != null && par1ItemStack.getItem() instanceof ItemFood;
    }*/

    @Override
    public EntityStarfish createChild(EntityAgeable par1EntityAgeable)
    {
        return new EntityStarfish(this.worldObj);
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public boolean allowLeashing()
    {
        return false;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData entity)
    {
        if (this.worldObj.rand.nextInt(5) == 0)
        {
            for (int i = 0; i < 2; i++)
            {
                EntityStarfish starfish = new EntityStarfish(this.worldObj);
                starfish.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                starfish.setGrowingAge(-24000);
                this.worldObj.spawnEntityInWorld(starfish);
            }
        }
        return entity;
    }
}