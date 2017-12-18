/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.planets.mars.entities.EntitySlimeling;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.entities.IEntityLivingPlanet;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.core.util.EnumDimensionType;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;

public class EntityInfectedWorm extends EntityMob implements IEntityBreathable, IEntityLivingPlanet
{
    public EntityInfectedWorm(World world)
    {
        super(world);
        this.setSize(0.2F, 0.2F);
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.25F, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEvolvedZombie.class, 0, false, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEvolvedSkeleton.class, 0, false, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEvolvedSpider.class, 0, false, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEvolvedCreeper.class, 0, false, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEvolvedEnderman.class, 0, false, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySlimeling.class, 200, false));
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (super.attackEntityAsMob(entity))
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MPPotions.infected_gas.id, 20, 0));
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1008);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
    }

    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected Entity findPlayerToAttack()
    {
        return this.worldObj.getClosestVulnerablePlayerToEntity(this, 8.0D);
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.silverfish.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.silverfish.hit";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.silverfish.kill";
    }

    @Override
    protected void attackEntity(Entity entity, float par2)
    {
        if (this.attackTime <= 0 && par2 < 1.2F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), par2);
        }
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block)
    {
        this.worldObj.playSoundAtEntity(this, "mob.silverfish.step", 1.0F, 1.0F);
    }

    @Override
    protected Item getDropItem()
    {
        return Item.getItemFromBlock(Blocks.air);
    }

    @Override
    public void onUpdate()
    {
        this.renderYawOffset = this.rotationYaw;
        super.onUpdate();
    }

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        if (super.getCanSpawnHere())
        {
            EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 5.0D);
            return player == null;
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public EnumDimensionType canLivingInDimension()
    {
        return EnumDimensionType.NIBIRU;
    }
}