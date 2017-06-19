package stevekung.mods.moreplanets.module.planets.diona.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.entity.ai.PathNavigateGroundMP;

public class EntityZeliusZombie extends EntityZombie implements IEntityBreathable
{
    public EntityZeliusZombie(World world)
    {
        super(world);
        ((PathNavigateGroundMP)this.getNavigator()).setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
        this.setSize(0.6F, 1.8F);
    }

    @Override
    protected PathNavigate getNewNavigator(World world)
    {
        return new PathNavigateGroundMP(this, world);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
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
        super.dropFewItems(drop, fortune);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZE.id, 120, 1));
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "moreplanets:mob.infected.attack", 1.0F, 1.0F);
        }
        return super.attackEntityAsMob(entity);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);

        if (this.rand.nextFloat() < 0.1F)
        {
            int i = this.rand.nextInt(3);

            if (i == 0)
            {
                this.setCurrentItemOrArmor(0, new ItemStack(DionaItems.ILLENIUM_SWORD));
            }
            else
            {
                this.setCurrentItemOrArmor(0, new ItemStack(DionaItems.ILLENIUM_PICKAXE));
            }
        }
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotionID() == MPPotions.INFECTED_CRYSTALLIZE.id ? false : super.isPotionApplicable(potion);
    }

    public IAttribute getReinforcementsAttribute()
    {
        return EntityZombie.reinforcementChance;
    }
}