package stevekung.mods.moreplanets.planets.chalos.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseSlime;
import stevekung.mods.moreplanets.utils.entity.EntityFireballMP;

public class EntitySmallCheeseSpore extends EntityFireballMP
{
    public EntitySmallCheeseSpore(World world)
    {
        super(world);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntitySmallCheeseSpore(World world, EntityLivingBase living, double x, double y, double z)
    {
        super(world, living, x, y, z);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntitySmallCheeseSpore(World world, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(world, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 2.5F);
                this.applyEnchantments(this.shootingEntity, result.entityHit);
            }
            if (this.rand.nextInt(5) == 0)
            {
                int count = 1;

                if (this.rand.nextInt(16) == 0)
                {
                    count = 2;
                }

                for (int i = 0; i < count; ++i)
                {
                    EntityCheeseSlime slime = new EntityCheeseSlime(this.world);
                    slime.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    slime.setSlimeSize(1, true);
                    slime.setAbsorptionAmount(2.0F);
                    this.world.spawnEntity(slime);
                }
            }
            this.setDead();
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return false;
    }
}