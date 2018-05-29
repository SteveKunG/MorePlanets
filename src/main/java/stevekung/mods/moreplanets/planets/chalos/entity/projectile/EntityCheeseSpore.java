package stevekung.mods.moreplanets.planets.chalos.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseSlime;
import stevekung.mods.moreplanets.utils.entity.EntityFireballMP;

public class EntityCheeseSpore extends EntityFireballMP
{
    public EntityCheeseSpore(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    public EntityCheeseSpore(World world, EntityLivingBase living, double x, double y, double z)
    {
        super(world, living, x, y, z);
        this.setSize(1.0F, 1.0F);
    }

    public EntityCheeseSpore(World world, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(world, x, y, z, accelX, accelY, accelZ);
        this.setSize(1.0F, 1.0F);
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 5.0F);
                this.applyEnchantments(this.shootingEntity, result.entityHit);
            }
            if (this.rand.nextInt(4) == 0)
            {
                if (!this.world.isRemote)
                {
                    int count = 1;

                    if (this.rand.nextInt(16) == 0)
                    {
                        count = 4;
                    }

                    for (int i = 0; i < count; ++i)
                    {
                        EntityCheeseSlime slime = new EntityCheeseSlime(this.world);
                        slime.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                        slime.setSlimeSize(this.world.rand.nextInt(2), true);
                        slime.setAbsorptionAmount(10.0F);
                        this.world.spawnEntity(slime);
                    }
                }
            }
            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
            this.world.newExplosion(null, this.posX, this.posY, this.posZ, 1.0F, false, flag);
            this.setDead();
        }
    }
}