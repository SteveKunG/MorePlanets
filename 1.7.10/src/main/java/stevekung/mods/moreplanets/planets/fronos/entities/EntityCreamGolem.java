/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.*;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class EntityCreamGolem extends EntityGolem implements IRangedAttackMob
{
    public EntityCreamGolem(World par1World)
    {
        super(par1World);
        this.setSize(0.4F, 1.8F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));
        this.setCreamGolemType(this.rand.nextInt(6));
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1026);
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
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
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
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
    {
        return 1 + this.worldObj.rand.nextInt(4);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posZ);

        if (this.isWet())
        {
            this.attackEntityFrom(DamageSource.drown, 1.0F);
        }

        for (i = 0; i < 4; ++i)
        {
            j = MathHelper.floor_double(this.posX + (i % 2 * 2 - 1) * 0.25F);
            int k = MathHelper.floor_double(this.posY);
            int l = MathHelper.floor_double(this.posZ + (i / 2 % 2 * 2 - 1) * 0.25F);

            int golemType = this.getCreamGolemType();

            if (golemType == 0)
            {
                if (this.worldObj.getBlock(j, k, l) == Blocks.air && FronosBlocks.vanilla_cream_layer.canPlaceBlockAt(this.worldObj, j, k, l))
                {
                    this.worldObj.setBlock(j, k, l, FronosBlocks.vanilla_cream_layer);
                }
            }
            else if (golemType == 1)
            {
                if (this.worldObj.getBlock(j, k, l) == Blocks.air && FronosBlocks.chocolate_cream_layer.canPlaceBlockAt(this.worldObj, j, k, l))
                {
                    this.worldObj.setBlock(j, k, l, FronosBlocks.chocolate_cream_layer);
                }
            }
            else if (golemType == 2)
            {
                if (this.worldObj.getBlock(j, k, l) == Blocks.air && FronosBlocks.strawberry_cream_layer.canPlaceBlockAt(this.worldObj, j, k, l))
                {
                    this.worldObj.setBlock(j, k, l, FronosBlocks.strawberry_cream_layer);
                }
            }
            else if (golemType == 3)
            {
                if (this.worldObj.getBlock(j, k, l) == Blocks.air && FronosBlocks.orange_cream_layer.canPlaceBlockAt(this.worldObj, j, k, l))
                {
                    this.worldObj.setBlock(j, k, l, FronosBlocks.orange_cream_layer);
                }
            }
            else if (golemType == 4)
            {
                if (this.worldObj.getBlock(j, k, l) == Blocks.air && FronosBlocks.tea_cream_layer.canPlaceBlockAt(this.worldObj, j, k, l))
                {
                    this.worldObj.setBlock(j, k, l, FronosBlocks.tea_cream_layer);
                }
            }
            else if (golemType == 5)
            {
                if (this.worldObj.getBlock(j, k, l) == Blocks.air && FronosBlocks.lemon_cream_layer.canPlaceBlockAt(this.worldObj, j, k, l))
                {
                    this.worldObj.setBlock(j, k, l, FronosBlocks.lemon_cream_layer);
                }
            }
        }
    }

    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(16);

        for (int k = 4; k < j; ++k)
        {
            this.entityDropItem(new ItemStack(FronosItems.cream_ball, 1, this.getCreamGolemType()), 0.0F);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("CreamGolemType", this.getCreamGolemType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setCreamGolemType(par1NBTTagCompound.getInteger("CreamGolemType"));
    }

    public int getCreamGolemType()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    public void setCreamGolemType(int par1)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)par1));
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2)
    {
        double d0 = par1EntityLivingBase.posX - this.posX;
        double d2 = par1EntityLivingBase.posZ - this.posZ;
        float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.2F;
        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        int golemType = this.getCreamGolemType();

        if (golemType == 0)
        {
            EntityVanillaCreamBall entitysnowball = new EntityVanillaCreamBall(this.worldObj, this);
            double d1 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - entitysnowball.posY;
            entitysnowball.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
            this.worldObj.spawnEntityInWorld(entitysnowball);
        }
        else if (golemType == 1)
        {
            EntityChocolateCreamBall entitysnowball = new EntityChocolateCreamBall(this.worldObj, this);
            double d1 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - entitysnowball.posY;
            entitysnowball.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
            this.worldObj.spawnEntityInWorld(entitysnowball);
        }
        else if (golemType == 2)
        {
            EntityStrawberryCreamBall entitysnowball = new EntityStrawberryCreamBall(this.worldObj, this);
            double d1 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - entitysnowball.posY;
            entitysnowball.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
            this.worldObj.spawnEntityInWorld(entitysnowball);
        }
        else if (golemType == 3)
        {
            EntityOrangeCreamBall entitysnowball = new EntityOrangeCreamBall(this.worldObj, this);
            double d1 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - entitysnowball.posY;
            entitysnowball.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
            this.worldObj.spawnEntityInWorld(entitysnowball);
        }
        else if (golemType == 4)
        {
            EntityTeaCreamBall entitysnowball = new EntityTeaCreamBall(this.worldObj, this);
            double d1 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - entitysnowball.posY;
            entitysnowball.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
            this.worldObj.spawnEntityInWorld(entitysnowball);
        }
        else if (golemType == 5)
        {
            EntityLemonCreamBall entitysnowball = new EntityLemonCreamBall(this.worldObj, this);
            double d1 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - entitysnowball.posY;
            entitysnowball.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
            this.worldObj.spawnEntityInWorld(entitysnowball);
        }
    }
}