package stevekung.mods.moreplanets.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import stevekung.mods.moreplanets.entity.projectile.EntityLaserBullet;
import stevekung.mods.moreplanets.module.planets.diona.entity.projectile.EntityInfectedCrystallizeArrow;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityInfectedArrow;

public class DamageSourceMP extends DamageSource
{
    public static DamageSource INFECTED_CRYSTALLIZE = new DamageSource("infected_crystallize").setDamageBypassesArmor().setDifficultyScaled();
    public static DamageSource INFECTED_GAS = new DamageSource("infected_gas").setDamageBypassesArmor().setDifficultyScaled();
    public static DamageSource DARK_ENERGY = new DamageSource("dark_energy").setDamageBypassesArmor().setDifficultyScaled();
    public static DamageSource BLACK_HOLE = new DamageSource("black_hole").setDamageBypassesArmor().setDamageAllowedInCreativeMode();

    public DamageSourceMP(String damageType)
    {
        super(damageType);
    }

    public static DamageSource causeInfectedCrystallizeArrowDamage(EntityInfectedCrystallizeArrow arrow, Entity indirect)
    {
        return new EntityDamageSourceIndirect("arrowInfectedCrystallize", arrow, indirect).setProjectile();
    }

    public static DamageSource causeInfectedArrowDamage(EntityInfectedArrow arrow, Entity indirect)
    {
        return new EntityDamageSourceIndirect("arrowInfected", arrow, indirect).setProjectile();
    }

    public static DamageSource causeLaserDamage(EntityLaserBullet laser, Entity indirectEntity)
    {
        return new EntityDamageSourceIndirect("laser", laser, indirectEntity).setProjectile();
    }
}