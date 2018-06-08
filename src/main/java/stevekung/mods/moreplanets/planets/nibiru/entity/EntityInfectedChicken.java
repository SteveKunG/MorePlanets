package stevekung.mods.moreplanets.planets.nibiru.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.planets.nibiru.entity.ai.EntityAIFleeNibiruThunder;
import stevekung.mods.moreplanets.utils.EntityEffectUtils;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;

public class EntityInfectedChicken extends EntityChicken implements ISpaceMob, IEntityBreathable
{
    public EntityInfectedChicken(World world)
    {
        super(world);
        this.setSize(0.4F, 0.7F);
        this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, MPItems.INFECTED_WHEAT_SEEDS, false));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, MPItems.INFECTED_MELON_SEEDS, false));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIFleeNibiruThunder(this, 1.5D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    @Override
    public boolean getCanSpawnHere()
    {
        Block block = this.world.getBlockState(this.getPosition().down()).getBlock();
        return (block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.GREEN_VEIN_GRASS_BLOCK) && this.world.getLight(this.getPosition()) > 8 && this.getBlockPathWeight(this.getPosition()) >= 0.0F;
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

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (this.getGrowingAge() >= 0)
        {
            boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());

            if (flag)
            {
                this.applyEnchantments(this, entity);
                return EntityEffectUtils.addInfectedSpore(entity);
            }
            return flag;
        }
        return super.attackEntityAsMob(entity);
    }

    @Override
    public EntityItem dropItem(Item item, int size)
    {
        return this.dropItemWithOffset(MPItems.INFECTED_EGG, 1, 0.0F);
    }

    @Override
    public EntityInfectedChicken createChild(EntityAgeable ageable)
    {
        return new EntityInfectedChicken(this.world);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack)
    {
        return !itemStack.isEmpty() && itemStack.getItem() == MPItems.INFECTED_WHEAT_SEEDS;
    }
}