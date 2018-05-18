package stevekung.mods.moreplanets.planets.nibiru.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedChicken;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;

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
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CUSTOM_BREAKING_MOTION, this.posX, this.posY, this.posZ, (this.rand.nextFloat() - 0.5D) * 0.08D, (this.rand.nextFloat() - 0.5D) * 0.08D, (this.rand.nextFloat() - 0.5D) * 0.08D, new Object[] { MPItems.INFECTED_EGG });
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }
        if (!this.world.isRemote)
        {
            if (this.rand.nextInt(8) == 0)
            {
                int i = 1;

                if (this.rand.nextInt(32) == 0)
                {
                    i = 4;
                }

                for (int j = 0; j < i; ++j)
                {
                    EntityInfectedChicken chicken = new EntityInfectedChicken(this.world);
                    chicken.setGrowingAge(-24000);
                    chicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    this.world.spawnEntity(chicken);
                }
            }
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}