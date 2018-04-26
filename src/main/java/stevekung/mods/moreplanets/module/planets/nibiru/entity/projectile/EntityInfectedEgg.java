package stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedChicken;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;

public class EntityInfectedEgg extends EntityThrowable
{
    public EntityInfectedEgg(World world)
    {
        super(world);
    }

    public EntityInfectedEgg(World world, EntityLivingBase thrower)
    {
        super(world, thrower);
    }

    public EntityInfectedEgg(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult moving)
    {
        if (moving.entityHit != null)
        {
            moving.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }

        if (!this.world.isRemote && this.rand.nextInt(8) == 0)
        {
            int i = 1;

            if (this.rand.nextInt(32) == 0)
            {
                i = 4;
            }

            for (int j = 0; j < i; ++j)
            {
                EntityInfectedChicken entitychicken = new EntityInfectedChicken(this.world);
                entitychicken.setGrowingAge(-24000);
                entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                this.world.spawnEntity(entitychicken);
            }
        }

        for (int k = 0; k < 8; ++k)
        {
            MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CUSTOM_BREAKING_MOTION, this.posX, this.posY, this.posZ, (this.rand.nextFloat() - 0.5D) * 0.08D, (this.rand.nextFloat() - 0.5D) * 0.08D, (this.rand.nextFloat() - 0.5D) * 0.08D, new Object[] { NibiruItems.INFECTED_EGG });
        }
        if (!this.world.isRemote)
        {
            this.setDead();
        }
    }
}