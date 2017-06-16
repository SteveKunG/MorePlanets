/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.planets.mars.entities.EntitySlimeling;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.diona.dimension.WorldProviderDiona;

public class EntityDustSludgeling extends EntityMob implements IEntityBreathable
{
    public EntityDustSludgeling(World world)
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
    public ItemStack getPickedResult(MovingObjectPosition moving)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1002);
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
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
    protected void attackEntity(Entity entity, float par2)
    {
        if (this.attackTime <= 0 && par2 < 1.2F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(entity);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damage, float amount)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            return super.attackEntityFrom(damage, amount);
        }
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
    protected void func_145780_a(int x, int y, int z, Block block)
    {
        this.worldObj.playSoundAtEntity(this, "mob.silverfish.step", 1.0F, 1.0F);
    }

    @Override
    public void onUpdate()
    {
        this.renderYawOffset = this.rotationYaw;
        super.onUpdate();
    }

    @Override
    public void onLivingUpdate()
    {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.boundingBox.minY);
        int z = MathHelper.floor_double(this.posZ);
        Block block = this.worldObj.getBlock(x, y - 1, z);
        int meta = this.worldObj.getBlockMetadata(x, y - 1, z);

        if (this.worldObj.provider instanceof WorldProviderDiona)
        {
            for (int i = 0; i < 1; i++)
            {
                if (this.worldObj.rand.nextInt(8) == 0)
                {
                    if (block == DionaBlocks.diona_block && meta == 0)
                    {
                        this.worldObj.spawnParticle("blockcrack_" + String.valueOf(Block.getIdFromBlock(DionaBlocks.diona_block)) + "_0", x + this.worldObj.rand.nextFloat(), y + 0.1F, z + this.worldObj.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
                    }
                    else if (block == DionaBlocks.diona_block && meta == 1)
                    {
                        this.worldObj.spawnParticle("blockcrack_" + String.valueOf(Block.getIdFromBlock(DionaBlocks.diona_block)) + "_1", x + this.worldObj.rand.nextFloat(), y + 0.1F, z + this.worldObj.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
        super.onLivingUpdate();
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
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
}