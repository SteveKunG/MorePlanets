/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.util.MPLog;
import stevekung.mods.moreplanets.planets.diona.potion.EMPEffect;
import stevekung.mods.moreplanets.planets.kapteynb.potion.ChemicalEffect;
import stevekung.mods.moreplanets.planets.kapteynb.potion.IceCrystalEffect;
import stevekung.mods.moreplanets.planets.nibiru.potion.InfectedGasEffect;

public class MPPotions
{
    public static Potion infected_gas;
    public static Potion chemical;
    public static Potion electro_magnetic_pulse;
    public static Potion icy_poison;
    public static Potion[] potionTypes;

    public static void init()
    {
        MPPotions.initPotionHook();
        MPPotions.intializePotions();
    }

    private static void intializePotions()
    {
        MPPotions.infected_gas = new InfectedGasEffect(ConfigManagerMP.idPotionInfectedGas, true, -4502242).setPotionName("potion.infected.gas");
        MPPotions.chemical = new ChemicalEffect(ConfigManagerMP.idPotionChemical, true, -16718336).setPotionName("potion.chemical");
        MPPotions.electro_magnetic_pulse = new EMPEffect(ConfigManagerMP.idPotionEMP, true, -14258727).setPotionName("potion.emp").func_111184_a(SharedMonsterAttributes.movementSpeed, "45166E8E-7CE8-4030-940E-514C1F160890", -2.5D, 2);
        MPPotions.icy_poison = new IceCrystalEffect(ConfigManagerMP.idPotionIcyPoison, true, -6564921).setPotionName("potion.icy_poison").func_111184_a(SharedMonsterAttributes.movementSpeed, "9623E0072-7CE8-4030-940E-514C1F160890", -0.20000000596046448D, 2);
    }

    private static void initPotionHook()
    {
        for (Field f : Potion.class.getDeclaredFields())
        {
            f.setAccessible(true);

            try
            {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a"))
                {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                    potionTypes = (Potion[])f.get(null);
                    Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            }
            catch (Exception e)
            {
                MPLog.error("Potion registering failed, please report this to More Planets GitHub");
            }
        }
    }
}