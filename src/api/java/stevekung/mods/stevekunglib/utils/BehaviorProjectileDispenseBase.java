package stevekung.mods.stevekunglib.utils;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BehaviorProjectileDispenseBase extends BehaviorProjectileDispense
{
    private final Class<? extends IProjectile> projectile;
    private final boolean isArrow;

    public BehaviorProjectileDispenseBase(Class<? extends IProjectile> projectile)
    {
        this(projectile, false);
    }

    public BehaviorProjectileDispenseBase(Class<? extends IProjectile> projectile, boolean isArrow)
    {
        this.projectile = projectile;
        this.isArrow = isArrow;
    }

    @Override
    protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack itemStack)
    {
        if (this.isArrow)
        {
            try
            {
                EntityArrow arrow = (EntityArrow) this.projectile.getConstructor(World.class, double.class, double.class, double.class).newInstance(world, pos.getX(), pos.getY(), pos.getZ());
                arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
                return arrow;
            }
            catch (Exception e) {}
        }
        else
        {
            try
            {
                return this.projectile.getConstructor(World.class, double.class, double.class, double.class).newInstance(world, pos.getX(), pos.getY(), pos.getZ());
            }
            catch (Exception e) {}
        }
        return null;
    }
}