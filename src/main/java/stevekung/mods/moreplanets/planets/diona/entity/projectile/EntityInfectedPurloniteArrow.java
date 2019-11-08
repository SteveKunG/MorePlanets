package stevekung.mods.moreplanets.planets.diona.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.DamageSourceMP;
import stevekung.mods.moreplanets.utils.entity.EntityArrowMP;

public class EntityInfectedPurloniteArrow extends EntityArrowMP
{
    public EntityInfectedPurloniteArrow(World world)
    {
        super(world);
    }

    public EntityInfectedPurloniteArrow(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntityInfectedPurloniteArrow(World world, EntityLivingBase shooter)
    {
        super(world, shooter);
    }

    @Override
    protected void addEffect(EntityLivingBase living)
    {
        if (!this.world.isRemote)
        {
            living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_PURLONITE, 100, 0));
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        if (!this.world.isRemote && this.inGround && this.arrowShake <= 0)
        {
            boolean flag = this.pickupStatus == PickupStatus.ALLOWED || this.pickupStatus == PickupStatus.CREATIVE_ONLY && player.capabilities.isCreativeMode;

            if (this.pickupStatus == PickupStatus.ALLOWED && !player.inventory.addItemStackToInventory(new ItemStack(MPItems.INFECTED_PURLONITE_ARROW, 1)))
            {
                flag = false;
            }

            if (flag)
            {
                this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                player.onItemPickup(this, 1);
                this.setDead();
            }
        }
    }

    @Override
    protected DamageSource[] getDamageSource()
    {
        return new DamageSource[] {DamageSourceMP.causeInfectedPurloniteArrowDamage(this, this), DamageSourceMP.causeInfectedPurloniteArrowDamage(this, this.shootingEntity)};
    }
}