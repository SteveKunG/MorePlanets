package stevekung.mods.moreplanets.planets.nibiru.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;

public class EntityInfectedSnowball extends EntityThrowable
{
    public EntityInfectedSnowball(World world)
    {
        super(world);
    }

    public EntityInfectedSnowball(World world, EntityLivingBase thrower)
    {
        super(world, thrower);
    }

    public EntityInfectedSnowball(World world, double x, double y, double z)
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
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CUSTOM_BREAKING, this.posX, this.posY, this.posZ, new Object[] { MPItems.INFECTED_SNOWBALL });
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            int i = 0;

            if (result.entityHit instanceof EntityBlaze)
            {
                i = 3;
            }
            else if (result.entityHit instanceof EntityLivingBase)
            {
                ((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 100));
            }
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), i);
        }
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}