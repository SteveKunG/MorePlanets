package stevekung.mods.moreplanets.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import stevekung.mods.moreplanets.entity.projectile.EntityLaserBullet;
import stevekung.mods.moreplanets.planets.diona.entity.projectile.EntityInfectedPurloniteArrow;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityInfectedArrow;

public class DamageSourceMP extends DamageSource
{
    public static final DamageSource INFECTED_PURLONITE = new DamageSource("infected_purlonite").setDamageBypassesArmor().setDifficultyScaled();
    public static final DamageSource INFECTED_GAS = new DamageSource("infected_gas").setDamageBypassesArmor().setDifficultyScaled();
    public static final DamageSource DARK_ENERGY = new DamageSource("dark_energy").setDamageBypassesArmor().setDifficultyScaled();
    public static final DamageSource BLACK_HOLE = new DamageSource("black_hole").setDamageBypassesArmor().setDamageAllowedInCreativeMode();

    public DamageSourceMP(String damageType)
    {
        super(damageType);
    }

    public static DamageSource causeInfectedPurloniteArrowDamage(EntityInfectedPurloniteArrow arrow, Entity indirect)
    {
        return new EntityDamageSourceIndirect("infected_purlonite_arrow", arrow, indirect).setProjectile();
    }

    public static DamageSource causeInfectedArrowDamage(EntityInfectedArrow arrow, Entity indirect)
    {
        return new EntityDamageSourceIndirect("infected_arrow", arrow, indirect).setProjectile();
    }

    public static DamageSource causeLaserDamage(EntityLaserBullet laser, Entity indirectEntity)
    {
        return new EntityDamageSourceIndirect("laser", laser, indirectEntity).setProjectile();
    }
}