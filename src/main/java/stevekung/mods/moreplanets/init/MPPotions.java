package stevekung.mods.moreplanets.init;

import net.minecraft.potion.Potion;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.items.capsule_effect.DarkEnergyProtectionEffect;
import stevekung.mods.moreplanets.items.capsule_effect.InfectedSporeProtectionEffect;
import stevekung.mods.moreplanets.module.planets.diona.potion.DarkEnergyEffect;
import stevekung.mods.moreplanets.module.planets.diona.potion.InfectedCrystallizedEffect;
import stevekung.mods.moreplanets.module.planets.nibiru.potion.InfectedSporeEffect;

public class MPPotions
{
    public static Potion INFECTED_CRYSTALLIZED;
    public static Potion INFECTED_SPORE;
    public static Potion DARK_ENERGY;
    public static Potion INFECTED_SPORE_PROTECTION;
    public static Potion DARK_ENERGY_PROTECTION;

    public static void init()
    {
        MPPotions.INFECTED_CRYSTALLIZED = new InfectedCrystallizedEffect();
        MPPotions.INFECTED_SPORE = new InfectedSporeEffect();
        MPPotions.DARK_ENERGY = new DarkEnergyEffect();
        MPPotions.INFECTED_SPORE_PROTECTION = new InfectedSporeProtectionEffect();
        MPPotions.DARK_ENERGY_PROTECTION = new DarkEnergyProtectionEffect();

        MorePlanetsMod.COMMON_REGISTRY.registerPotion(MPPotions.INFECTED_CRYSTALLIZED, "infected_crystallized");
        MorePlanetsMod.COMMON_REGISTRY.registerPotion(MPPotions.INFECTED_SPORE, "infected_spore");
        MorePlanetsMod.COMMON_REGISTRY.registerPotion(MPPotions.DARK_ENERGY, "dark_energy");
        MorePlanetsMod.COMMON_REGISTRY.registerPotion(MPPotions.INFECTED_SPORE_PROTECTION, "infected_spore_protection");
        MorePlanetsMod.COMMON_REGISTRY.registerPotion(MPPotions.DARK_ENERGY_PROTECTION, "dark_energy_protection");
    }
}