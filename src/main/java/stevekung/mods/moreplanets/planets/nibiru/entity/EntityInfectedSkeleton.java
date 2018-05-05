package stevekung.mods.moreplanets.planets.nibiru.entity;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityInfectedArrow;
import stevekung.mods.moreplanets.utils.EntityEffectUtils;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;
import stevekung.mods.moreplanets.utils.entity.ai.EntityAIAttackRangedBowMP;

public class EntityInfectedSkeleton extends EntitySkeleton implements IEntityBreathable, ISpaceMob
{
    private EntityAIAttackRangedBowMP aiArrowAttack = new EntityAIAttackRangedBowMP(this, 1.0D, 20, 15.0F);
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
        this.setCombatTask();
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
            return EntityEffectUtils.addInfectedSpore(entity);
        }
        return false;
    }

    @Override
    public void setDead()
    {
        if (!this.world.isRemote && !this.isChild())
        {
            if (this.rand.nextInt(4) == 0)
            {
                EntityInfectedWorm worm = new EntityInfectedWorm(this.world);
                worm.setLocationAndAngles(this.posX, this.posY + this.rand.nextInt(2), this.posZ, 360.0F, 0.0F);
                this.world.spawnEntity(worm);
            }
        }
        super.setDead();
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MPLootTables.INFECTED_SKELETON;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(MPItems.SPACE_BOW));
    }

    @Override
    public void setCombatTask()
    {
        if (this.world != null && !this.world.isRemote)
        {
            ItemStack itemStack = this.getHeldItemMainhand();

            if (itemStack.getItem() == MPItems.SPACE_BOW)
            {
                int i = 20;

                if (this.world.getDifficulty() != EnumDifficulty.HARD)
                {
                    i = 40;
                }
                this.aiArrowAttack.setAttackCooldown(i);
                this.tasks.addTask(4, this.aiArrowAttack);
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distance)
    {
        EntityInfectedArrow entityarrow = new EntityInfectedArrow(this.world, this);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + target.height / 3.0F - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, 14 - this.world.getDifficulty().getDifficultyId() * 4);
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityarrow);
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slot, ItemStack itemStack)
    {
        super.setItemStackToSlot(slot, itemStack);

        if (!this.world.isRemote && slot == EntityEquipmentSlot.MAINHAND)
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