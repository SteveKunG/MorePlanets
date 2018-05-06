package stevekung.mods.moreplanets.planets.diona.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.entity.EntityArrowMP;

public class EntityAntiGravityArrow extends EntityArrowMP
{
    public EntityAntiGravityArrow(World world)
    {
        super(world);
    }

    public EntityAntiGravityArrow(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntityAntiGravityArrow(World world, EntityLivingBase shooter)
    {
        super(world, shooter);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        float motion = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

        if (!this.world.isRemote && (motion < 0.1F || this.inGround) && this.arrowShake <= 0)
        {
            boolean flag = this.pickupStatus == PickupStatus.ALLOWED || this.pickupStatus == PickupStatus.CREATIVE_ONLY && player.capabilities.isCreativeMode;

            if (this.pickupStatus == PickupStatus.ALLOWED && !player.inventory.addItemStackToInventory(new ItemStack(MPItems.ANTI_GRAVITY_ARROW, 1)))
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
        return new DamageSource[] {DamageSource.causeArrowDamage(this, this), DamageSource.causeArrowDamage(this, this.shootingEntity)};
    }

    @Override
    protected void doMotion(float speed, float motion, float motionGravity)
    {
        if (this.ticksInAir >= 1200)
        {
            this.setDead();
        }

        this.motionX *= speed;
        this.motionY *= speed;
        this.motionZ *= speed;

        if (motion < 0.05F)
        {
            this.motionX *= 0.0D;
            this.motionY *= 0.0D;
            this.motionZ *= 0.0D;
        }
        else
        {
            this.motionY -= 0.0D;
        }
    }
}