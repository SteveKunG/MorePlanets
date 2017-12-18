package stevekung.mods.moreplanets.module.planets.diona.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.DamageSourceMP;
import stevekung.mods.moreplanets.util.entity.EntityArrowMP;

public class EntityInfectedCrystallizeArrow extends EntityArrowMP
{
    public EntityInfectedCrystallizeArrow(World world)
    {
        super(world);
    }

    public EntityInfectedCrystallizeArrow(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntityInfectedCrystallizeArrow(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float inaccuracy)
    {
        super(world, shooter, target, velocity, inaccuracy);
    }

    public EntityInfectedCrystallizeArrow(World world, EntityLivingBase shooter, float velocity)
    {
        super(world, shooter, velocity);
    }

    @Override
    public void addEffect(EntityLivingBase living)
    {
        if (!this.worldObj.isRemote)
        {
            living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZE.id, 100, 0));
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0)
        {
            boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && player.capabilities.isCreativeMode;

            if (this.canBePickedUp == 1 && !player.inventory.addItemStackToInventory(new ItemStack(DionaItems.INFECTED_CRYSTALLIZE_ARROW, 1)))
            {
                flag = false;
            }
            if (flag)
            {
                this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                player.onItemPickup(this, 1);
                this.setDead();
            }
        }
    }

    @Override
    public DamageSource[] getDamageSource()
    {
        return new DamageSource[] {DamageSourceMP.causeInfectedCrystallizeArrowDamage(this, this), DamageSourceMP.causeInfectedCrystallizeArrowDamage(this, this.shootingEntity)};
    }
}