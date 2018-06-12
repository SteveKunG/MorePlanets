package stevekung.mods.moreplanets.planets.diona.entity;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.init.MPSounds;

public class EntityInfectedCrystallizedBomb extends EntityThrowable
{
    public EntityInfectedCrystallizedBomb(World world)
    {
        super(world);
    }

    public EntityInfectedCrystallizedBomb(World world, EntityLivingBase thrower)
    {
        super(world, thrower);
    }

    public EntityInfectedCrystallizedBomb(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F);
        }

        if (!this.world.isRemote)
        {
            this.setDead();
            List<EntityLivingBase> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(2.5D, 2.5D, 2.5D));
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 1.0F + this.rand.nextFloat(), true);
            this.world.playSound(null, this.getPosition(), MPSounds.ALIEN_EGG_DESTROYED, SoundCategory.BLOCKS, 1.5F, 1.0F);

            for (EntityLivingBase living : list)
            {
                living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZED, 128, 1));
            }
        }
    }
}