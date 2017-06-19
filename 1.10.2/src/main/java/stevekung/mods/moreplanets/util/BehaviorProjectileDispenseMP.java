package stevekung.mods.moreplanets.util;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class BehaviorProjectileDispenseMP extends BehaviorProjectileDispense
{
    private Class<? extends IProjectile> projectile;
    private boolean isArrow;

    public BehaviorProjectileDispenseMP(Class<? extends IProjectile> projectile)
    {
        this.projectile = projectile;
    }

    public BehaviorProjectileDispenseMP(Class<? extends IProjectile> projectile, boolean isArrow)
    {
        this.projectile = projectile;
        this.isArrow = isArrow;
    }

    @Override
    protected IProjectile getProjectileEntity(World world, IPosition pos)
    {
        if (this.isArrow)
        {
            try
            {
                EntityArrow arrow = (EntityArrow) this.projectile.getConstructor(World.class, double.class, double.class, double.class).newInstance(world, pos.getX(), pos.getY(), pos.getZ());
                arrow.canBePickedUp = 1;
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