package stevekung.mods.moreplanets.module.planets.diona.entity;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.entity.ai.PathNavigateGroundMP;

public class EntityZeliusZombie extends EntityZombie implements IEntityBreathable
{
    public EntityZeliusZombie(World world)
    {
        super(world);
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.ZELIUS_ZOMBIE;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZE, 120, 1));
            this.worldObj.playSound((EntityPlayer) entity, this.posX, this.posY, this.posZ, MPSounds.INFECTED_MOB_ATTACK, SoundCategory.PLAYERS, 1.0F, 1.0F);
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
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(DionaItems.ILLENIUM_SWORD));
            }
            else
            {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(DionaItems.ILLENIUM_PICKAXE));
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
        return potion.getPotion() == MPPotions.INFECTED_CRYSTALLIZE ? false : super.isPotionApplicable(potion);
    }

    public IAttribute getReinforcementsAttribute()
    {
        return EntityZombie.SPAWN_REINFORCEMENTS_CHANCE;
    }
}