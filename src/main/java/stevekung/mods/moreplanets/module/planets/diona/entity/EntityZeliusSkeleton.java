package stevekung.mods.moreplanets.module.planets.diona.entity;

import java.util.Calendar;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.entity.projectile.EntityInfectedCrystallizeArrow;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;

public class EntityZeliusSkeleton extends EntitySkeleton implements IEntityBreathable
{
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);

    public EntityZeliusSkeleton(World world)
    {
        super(world);
        this.tasks.taskEntries.clear();
        this.targetTasks.taskEntries.clear();
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));

        if (world != null && !world.isRemote)
        {
            this.setCombatTask();
        }
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (super.attackEntityAsMob(entity))
        {
            if (entity instanceof EntityLivingBase)
            {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZE.id, 120, 1));
                this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "moreplanets:mob.infected.attack", 1.0F, 1.0F);
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected Item getDropItem()
    {
        return null;
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int j = 1 + this.rand.nextInt(4);

        if (fortune > 0)
        {
            j += this.rand.nextInt(fortune + 1);
        }
        for (int k = 0; k < j; ++k)
        {
            this.entityDropItem(new ItemStack(DionaItems.DIONA_ITEM, 1, 4), 0.0F);
        }

        int k = this.rand.nextInt(3 + fortune);

        for (int i1 = 0; i1 < k; ++i1)
        {
            this.dropItem(DionaItems.INFECTED_CRYSTALLIZE_ARROW, 1);
        }

        int l = this.rand.nextInt(3 + fortune);

        for (int j1 = 0; j1 < l; ++j1)
        {
            this.dropItem(Items.bone, 1);
        }
    }

    @Override
    protected void addRandomDrop() {}

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setCurrentItemOrArmor(0, new ItemStack(MPItems.SPACE_BOW));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        this.tasks.addTask(4, this.aiArrowAttack);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }
        return data;
    }

    @Override
    public void setCombatTask()
    {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack itemstack = this.getHeldItem();

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
        EntityInfectedCrystallizeArrow entityarrow = new EntityInfectedCrystallizeArrow(this.worldObj, this, target, 1.6F, 14 - this.worldObj.getDifficulty().getDifficultyId() * 4);
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage(distance * 2.0F + this.rand.nextGaussian() * 0.25D + this.worldObj.getDifficulty().getDifficultyId() * 0.11F);

        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + i * 0.5D + 0.5D);
        }
        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0)
        {
            entityarrow.setFire(100);
        }
        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.setCombatTask();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
    }

    @Override
    public void setCurrentItemOrArmor(int slot, ItemStack itemStack)
    {
        super.setCurrentItemOrArmor(slot, itemStack);

        if (!this.worldObj.isRemote && slot == 0)
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
        return potion.getPotionID() == MPPotions.INFECTED_CRYSTALLIZE.id ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }
}