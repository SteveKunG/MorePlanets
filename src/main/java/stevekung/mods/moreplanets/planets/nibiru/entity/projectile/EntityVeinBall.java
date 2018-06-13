package stevekung.mods.moreplanets.planets.nibiru.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.utils.entity.EntityFireballMP;

public class EntityVeinBall extends EntityFireballMP
{
    public EntityVeinBall(World world)
    {
        super(world);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityVeinBall(World world, EntityLivingBase living, double x, double y, double z)
    {
        super(world, living, x, y, z);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityVeinBall(World world, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(world, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    @Override
    protected void onImpact(RayTraceResult moving)
    {
        if (!this.world.isRemote)
        {
            if (moving.entityHit != null)
            {
                moving.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 5.0F);
                this.applyEnchantments(this.shootingEntity, moving.entityHit);
            }
            this.setDead();
        }
    }
}