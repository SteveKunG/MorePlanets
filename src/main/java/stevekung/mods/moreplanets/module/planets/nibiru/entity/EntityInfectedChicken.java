package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;
import stevekung.mods.moreplanets.util.helper.EntityEffectHelper;

public class EntityInfectedChicken extends EntityAnimal implements ISpaceMob, IEntityBreathable
{
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;
    public int timeUntilNextEgg;
    public boolean chickenJockey;

    public EntityInfectedChicken(World world)
    {
        super(world);
        this.setSize(0.4F, 0.7F);
        this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, NibiruItems.INFECTED_WHEAT_SEEDS, false));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, NibiruItems.INFECTED_MELON_SEEDS, false));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor_double(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        return this.worldObj.getBlockState(blockpos.down()).getBlock() == NibiruBlocks.INFECTED_GRASS && this.worldObj.getLight(blockpos) > 8 && this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotionID() == MPPotions.INFECTED_SPORE.id ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.NIBIRU;
    }

    @Override
    public float getEyeHeight()
    {
        return this.height;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (this.getGrowingAge() >= 0)
        {
            boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (int)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());

            if (flag)
            {
                this.applyEnchantments(this, entity);
                return EntityEffectHelper.addInfectedSpore(entity);
            }
            return flag;
        }
        return super.attackEntityAsMob(entity);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float)(this.destPos + (this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp_float(this.destPos, 0.0F, 1.0F);

        if (!this.onGround && this.wingRotDelta < 1.0F)
        {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float)(this.wingRotDelta * 0.9D);

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        this.wingRotation += this.wingRotDelta * 2.0F;

        if (!this.worldObj.isRemote && !this.isChild() && !this.isChickenJockey() && --this.timeUntilNextEgg <= 0)
        {
            this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(NibiruItems.INFECTED_EGG, 1);
            this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        }
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    @Override
    protected String getLivingSound()
    {
        return "mob.chicken.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.chicken.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.chicken.hurt";
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block)
    {
        this.playSound("mob.chicken.step", 0.15F, 1.0F);
    }

    @Override
    protected Item getDropItem()
    {
        return Items.feather;
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int i = this.rand.nextInt(3) + this.rand.nextInt(1 + fortune);

        for (int j = 0; j < i; ++j)
        {
            this.dropItem(Items.feather, 1);
        }

        if (this.isBurning())
        {
            this.dropItem(Items.cooked_chicken, 1);
        }
        else
        {
            this.dropItem(Items.chicken, 1);
        }
    }

    @Override
    public EntityInfectedChicken createChild(EntityAgeable ageable)
    {
        return new EntityInfectedChicken(this.worldObj);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack)
    {
        return itemStack != null && itemStack.getItem() == NibiruItems.INFECTED_WHEAT_SEEDS;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.chickenJockey = tagCompund.getBoolean("IsChickenJockey");

        if (tagCompund.hasKey("EggLayTime"))
        {
            this.timeUntilNextEgg = tagCompund.getInteger("EggLayTime");
        }
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player)
    {
        return this.isChickenJockey() ? 10 : super.getExperiencePoints(player);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setBoolean("IsChickenJockey", this.chickenJockey);
        tagCompound.setInteger("EggLayTime", this.timeUntilNextEgg);
    }

    @Override
    protected boolean canDespawn()
    {
        return this.isChickenJockey() && this.riddenByEntity == null;
    }

    @Override
    public void updateRiderPosition()
    {
        super.updateRiderPosition();
        float f = MathHelper.sin(this.renderYawOffset * (float)Math.PI / 180.0F);
        float f1 = MathHelper.cos(this.renderYawOffset * (float)Math.PI / 180.0F);
        float f2 = 0.1F;
        float f3 = 0.0F;
        this.riddenByEntity.setPosition(this.posX + f2 * f, this.posY + this.height * 0.5F + this.riddenByEntity.getYOffset() + f3, this.posZ - f2 * f1);

        if (this.riddenByEntity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)this.riddenByEntity).renderYawOffset = this.renderYawOffset;
        }
    }

    public boolean isChickenJockey()
    {
        return this.chickenJockey;
    }

    public void setChickenJockey(boolean jockey)
    {
        this.chickenJockey = jockey;
    }
}