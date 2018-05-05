package stevekung.mods.moreplanets.planets.nibiru.entity;

import java.util.Calendar;
import java.util.List;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.EntityEffectUtils;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;

public class EntityInfectedZombie extends EntityZombie implements IEntityBreathable, ISpaceMob
{
    public EntityInfectedZombie(World world)
    {
        super(world);
    }

    @Override
    protected void applyEntityAI()
    {
        super.applyEntityAI();
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityNibiruVillager.class, false));
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
                EntityGiantWorm worm = new EntityGiantWorm(this.world);
                worm.setLocationAndAngles(this.posX, this.posY + this.rand.nextInt(2), this.posZ, 360.0F, 0.0F);
                this.world.spawnEntity(worm);
            }
        }
        super.setDead();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigManagerCore.hardMode ? 5.0D : 3.0D);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
        float f = difficulty.getClampedAdditionalDifficulty();
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);

        if (data == null)
        {
            data = new GroupData(this.world.rand.nextFloat() < net.minecraftforge.common.ForgeModContainer.zombieBabyChance);
        }

        if (data instanceof GroupData)
        {
            GroupData entityzombie$groupdata = (GroupData)data;

            if (entityzombie$groupdata.isChild)
            {
                this.setChild(true);

                if (this.world.rand.nextFloat() < 0.05D)
                {
                    List<EntityInfectedChicken> list = this.world.getEntitiesWithinAABB(EntityInfectedChicken.class, this.getEntityBoundingBox().grow(5.0D, 3.0D, 5.0D), EntitySelectors.IS_STANDALONE);

                    if (!list.isEmpty())
                    {
                        EntityInfectedChicken entitychicken = list.get(0);
                        entitychicken.setChickenJockey(true);
                        this.startRiding(entitychicken);
                    }
                }
                else if (this.world.rand.nextFloat() < 0.05D)
                {
                    EntityInfectedChicken entitychicken1 = new EntityInfectedChicken(this.world);
                    entitychicken1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    entitychicken1.onInitialSpawn(difficulty, (IEntityLivingData)null);
                    entitychicken1.setChickenJockey(true);
                    this.world.spawnEntity(entitychicken1);
                    this.startRiding(entitychicken1);
                }
            }
        }

        this.setBreakDoorsAItask(this.rand.nextFloat() < f * 0.1F);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);

        if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD) == null)
        {
            Calendar calendar = this.world.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
            }
        }

        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
        double d0 = this.rand.nextDouble() * 1.5D * f;

        if (d0 > 1.0D)
        {
            this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
        }

        if (this.rand.nextFloat() < f * 0.05F)
        {
            this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
            this.setBreakDoorsAItask(true);
        }
        return data;
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.NIBIRU;
    }

    public IAttribute getReinforcementsAttribute()
    {
        return EntityZombie.SPAWN_REINFORCEMENTS_CHANCE;
    }

    class GroupData implements IEntityLivingData
    {
        public boolean isChild;

        private GroupData(boolean isBaby)
        {
            this.isChild = false;
            this.isChild = isBaby;
        }
    }
}