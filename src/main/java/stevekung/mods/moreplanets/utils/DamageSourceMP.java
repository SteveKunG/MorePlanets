package stevekung.mods.moreplanets.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import stevekung.mods.moreplanets.entity.projectile.EntityLaserBullet;
import stevekung.mods.moreplanets.module.planets.diona.entity.projectile.EntityInfectedCrystallizedArrow;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityInfectedArrow;

public class DamageSourceMP extends DamageSource
{
    public static final DamageSource INFECTED_CRYSTALLIZED = new DamageSource("infected_crystallized").setDamageBypassesArmor().setDifficultyScaled();
    public static final DamageSource INFECTED_GAS = new DamageSource("infected_gas").setDamageBypassesArmor().setDifficultyScaled();
    public static final DamageSource DARK_ENERGY = new DamageSource("dark_energy").setDamageBypassesArmor().setDifficultyScaled();
    public static final DamageSource BLACK_HOLE = new DamageSource("black_hole").setDamageBypassesArmor().setDamageAllowedInCreativeMode();

    public DamageSourceMP(String damageType)
    {
        super(damageType);
    }

    public static DamageSource causeInfectedCrystallizedArrowDamage(EntityInfectedCrystallizedArrow arrow, Entity indirect)
    {
        return new EntityDamageSourceIndirect("arrow_infected_crystallized", arrow, indirect).setProjectile();
    }

    public static DamageSource causeInfectedArrowDamage(EntityInfectedArrow arrow, Entity indirect)
    {
        return new EntityDamageSourceIndirect("arrow_infected", arrow, indirect).setProjectile();
    }

    public static DamageSource causeLaserDamage(EntityLaserBullet laser, Entity indirectEntity)
    {
        return new EntityDamageSourceIndirect("laser", laser, indirectEntity).setProjectile();
    }
}