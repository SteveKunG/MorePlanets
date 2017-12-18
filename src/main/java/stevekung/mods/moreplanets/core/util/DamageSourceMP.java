/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityLaserMP;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityPoisonArrow;

public class DamageSourceMP extends DamageSource
{
    public static DamageSourceMP chemical = (DamageSourceMP) new DamageSourceMP("chemical").setDamageBypassesArmor();
    public static DamageSourceMP infectionVine = (DamageSourceMP) new DamageSourceMP("infectionVine").setDamageBypassesArmor().setDifficultyScaled();
    public static DamageSourceMP purpleSpike = (DamageSourceMP)new DamageSourceMP("purpleSpike").setDamageBypassesArmor();
    public static DamageSourceMP infectedGas = (DamageSourceMP)new DamageSourceMP("infectedGas").setDamageBypassesArmor().setDifficultyScaled();
    public static DamageSourceMP icy_poison = (DamageSourceMP)new DamageSourceMP("icy_poison").setDamageBypassesArmor().setDifficultyScaled();

    public DamageSourceMP(String damageType)
    {
        super(damageType);
    }

    public static DamageSource causeLaserDamage(EntityLaserMP laser, Entity par1Entity)
    {
        return new EntityDamageSourceIndirect("laser", laser, par1Entity).setProjectile();
    }

    public static DamageSource causePoisonArrowDamage(EntityPoisonArrow par0EntityArrow, Entity par1Entity)
    {
        return new EntityDamageSourceIndirect("poisonArrow", par0EntityArrow, par1Entity).setProjectile();
    }
}