package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import java.util.Calendar;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityInfectedArrow;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;
import stevekung.mods.moreplanets.util.helper.EntityEffectHelper;

public class EntityInfectedSkeleton extends EntitySkeleton implements IEntityBreathable, ISpaceMob
{
    private EntityAIAttackRangedBow aiArrowAttack = new EntityAIAttackRangedBow(this, 1.0D, 20, 15.0F);
    private EntityAIAttackMelee aiAttackOnCollide = new EntityAIAttackMelee(this, 1.2D, false)
    {
        @Override
        public void resetTask()
        {
            super.resetTask();
            EntityInfectedSkeleton.this.setSwingingArms(false);
        }

        @Override
        public void startExecuting()
        {
            super.startExecuting();
            EntityInfectedSkeleton.this.setSwingingArms(true);
        }
    };

    public EntityInfectedSkeleton(World world)
    {
        super(world);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (super.attackEntityAsMob(entity))
        {
            return EntityEffectHelper.addInfectedSpore(entity);
        }
        return false;
    }

    @Override
    protected Item getDropItem()
    {
        return null;
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int k = this.rand.nextInt(3 + fortune);

        for (int i1 = 0; i1 < k; ++i1)
        {
            this.dropItem(NibiruItems.INFECTED_ARROW, 1);
        }

        int l = this.rand.nextInt(3 + fortune);

        for (int j1 = 0; j1 < l; ++j1)
        {
            this.dropItem(Items.BONE, 1);
        }
    }

    @Override
    public void setDead()
    {
        if (!this.worldObj.isRemote && !this.isChild())
        {
            if (this.rand.nextInt(4) == 0)
            {
                EntityInfectedWorm worm = new EntityInfectedWorm(this.worldObj);
                worm.setLocationAndAngles(this.posX, this.posY + this.rand.nextInt(2), this.posZ, 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(worm);
            }
        }
        super.setDead();
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(MPItems.SPACE_BOW));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        this.tasks.addTask(4, this.aiArrowAttack);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());

        if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
            }
        }
        return data;
    }

    @Override
    public void setCombatTask()
    {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack itemstack = this.getHeldItemMainhand();

        if (itemstack != null && itemstack.getItem() == MPItems.SPACE_BOW)
        {
            this.tasks.addTask(4, this.aiArrowAttack);
        }
        else
        {
            this.tasks.addTask(4, this.aiAttackOnCollide);
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distance)
    {
        EntityInfectedArrow entityarrow = new EntityInfectedArrow(this.worldObj);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + target.height / 3.0F - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        entityarrow.setThrowableHeading(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, 14 - this.worldObj.getDifficulty().getDifficultyId() * 4);
        int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.POWER, this);
        int j = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.PUNCH, this);
        entityarrow.setDamage(distance * 2.0F + this.rand.nextGaussian() * 0.25D + this.worldObj.getDifficulty().getDifficultyId() * 0.11F);

        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + i * 0.5D + 0.5D);
        }
        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        boolean flag = this.isBurning() && this.worldObj.getDifficultyForLocation(new BlockPos(this)).isHard() && this.rand.nextBoolean() || this.getSkeletonType() == SkeletonType.WITHER;
        flag = flag || EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FLAME, this) > 0;

        if (flag)
        {
            entityarrow.setFire(100);
        }
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.setCombatTask();
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slot, ItemStack itemStack)
    {
        super.setItemStackToSlot(slot, itemStack);

        if (!this.worldObj.isRemote && slot == EntityEquipmentSlot.MAINHAND)
        {
            this.setCombatTask();
        }
    }

    @Override
    public float getEyeHeight()
    {
        return 1.74F;
    }

    @Override
    public double getYOffset()
    {
        return this.isChild() ? 0.0D : -0.35D;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
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
}