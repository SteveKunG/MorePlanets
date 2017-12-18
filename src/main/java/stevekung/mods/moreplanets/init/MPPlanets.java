package stevekung.mods.moreplanets.init;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import micdoodle8.mods.galacticraft.core.entities.*;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.chalos.dimension.WorldProviderChalos;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseCow;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseFloater;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseSlime;
import stevekung.mods.moreplanets.module.planets.diona.dimension.WorldProviderDiona;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityZeliusCreeper;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityZeliusSkeleton;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityZeliusZombie;
import stevekung.mods.moreplanets.module.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.*;
import stevekung.mods.moreplanets.util.helper.CelestialRegisterHelper;

public class MPPlanets
{
    public static Planet DIONA;
    public static Planet CHALOS;
    public static Planet NIBIRU;
    public static Planet FRONOS;
    public static SolarSystem LAZENDUS_SOLAR_SYSTEM;
    public static Star LAZENDUS;

    public static void init()
    {
        // Solar System
        MPPlanets.LAZENDUS_SOLAR_SYSTEM = CelestialRegisterHelper.createStarAndSolarSystem(MPPlanets.LAZENDUS_SOLAR_SYSTEM, MPPlanets.LAZENDUS, "lazendus", "lazendus", new Vector3(0.75F, 1.25F, 0.0F), "lazendus_celestial");

        // Planets
        MPPlanets.DIONA = CelestialRegisterHelper.createPlanet("diona", MPPlanets.LAZENDUS_SOLAR_SYSTEM, -14.25F, 4.25F, 20.0F, 0.876F, 4, ConfigManagerMP.idDimensionDiona, WorldProviderDiona.class);
        CelestialRegisterHelper.setAtmosphereComponentList(MPPlanets.DIONA, EnumAtmosphericGas.ARGON, EnumAtmosphericGas.HELIUM);
        CelestialRegisterHelper.setAtmosphere(MPPlanets.DIONA, false, false, false, 0.0F, 0.0F, 0.0F);
        CelestialRegisterHelper.setChecklistKeys(MPPlanets.DIONA, "equipOxygenSuit");
        CelestialRegisterHelper.setBiomeInfo(MPPlanets.DIONA, MPBiomes.DIONA);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityEvolvedZombie.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityEvolvedSpider.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityEvolvedSkeleton.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityEvolvedCreeper.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityEvolvedEnderman.class, 10, 1, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityEvolvedWitch.class, 5, 1, 1);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityZeliusZombie.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityZeliusCreeper.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityZeliusSkeleton.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.DIONA, EntityAlienMiner.class, 1, 1, 2);

        MPPlanets.CHALOS = CelestialRegisterHelper.createPlanet("chalos", MPPlanets.LAZENDUS_SOLAR_SYSTEM, 8.75F, 3.5F, 60.5F, 10.0F, 5, ConfigManagerMP.idDimensionChalos, WorldProviderChalos.class);
        CelestialRegisterHelper.setAtmosphereComponentList(MPPlanets.CHALOS, EnumAtmosphericGas.HYDROGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.CO2);
        CelestialRegisterHelper.setAtmosphere(MPPlanets.CHALOS, false, false, false, 0.0F, 0.65F, 28.0F);
        CelestialRegisterHelper.setChecklistKeys(MPPlanets.CHALOS, "equipOxygenSuit");
        CelestialRegisterHelper.setBiomeInfo(MPPlanets.CHALOS, MPBiomes.CHALOS_PLAINS, MPBiomes.CHALOS_HILLS, MPBiomes.SLIMELY_WASTELAND);
        CelestialRegisterHelper.addMobInfo(MPPlanets.CHALOS, EntityEvolvedZombie.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.CHALOS, EntityEvolvedSpider.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.CHALOS, EntityEvolvedSkeleton.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.CHALOS, EntityEvolvedCreeper.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.CHALOS, EntityEvolvedEnderman.class, 10, 1, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.CHALOS, EntityEvolvedWitch.class, 5, 1, 1);
        CelestialRegisterHelper.addMobInfo(MPPlanets.CHALOS, EntityCheeseFloater.class, 20, 1, 2);
        CelestialRegisterHelper.addMobInfo(MPPlanets.CHALOS, EntityCheeseSlime.class, 30, 2, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.CHALOS, EntityCheeseCow.class, 8, 4, 4);

        MPPlanets.NIBIRU = CelestialRegisterHelper.createPlanet("nibiru", MPPlanets.LAZENDUS_SOLAR_SYSTEM, 27.0F, 2.0F, 1050.5F, 2.0F, 6, ConfigManagerMP.idDimensionNibiru, WorldProviderNibiru.class);
        CelestialRegisterHelper.setAtmosphereComponentList(MPPlanets.NIBIRU, EnumAtmosphericGas.WATER, EnumAtmosphericGas.HELIUM, EnumAtmosphericGas.CO2, EnumAtmosphericGas.ARGON);
        CelestialRegisterHelper.setAtmosphere(MPPlanets.NIBIRU, false, true, true, 0.0F, 1.25F, 46.5F);
        CelestialRegisterHelper.setChecklistKeys(MPPlanets.NIBIRU, "equipOxygenSuit", "thermalPaddingT2", "craftInfectedProtectionCapsule");
        CelestialRegisterHelper.setBiomeInfo(MPPlanets.NIBIRU, MPBiomes.INFECTED_PLAINS, MPBiomes.INFECTED_DEAD_SAVANNA, MPBiomes.INFECTED_DESERT, MPBiomes.INFECTED_RIVER, MPBiomes.INFECTED_OCEAN, MPBiomes.INFECTED_DEEP_OCEAN, MPBiomes.INFECTED_FOREST, MPBiomes.INFECTED_DEAD_ROOFED_FOREST, MPBiomes.INFECTED_EXTREME_HILLS, MPBiomes.INFECTED_SWAMPLAND, MPBiomes.INFECTED_DEAD_TAIGA, MPBiomes.INFECTED_JUNGLE, MPBiomes.INFECTED_ICE_PLAINS, MPBiomes.GREEN_VEIN);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedZombie.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedSpider.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedSkeleton.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedCreeper.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedEnderman.class, 10, 1, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedWitch.class, 5, 1, 1);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityInfectedChicken.class, 10, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityInfectedCow.class, 8, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityShlime.class, 12, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityInfectedZombie.class, 100, 4, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityGiantWorm.class, 100, 2, 4);
        CelestialRegisterHelper.addMobInfo(MPPlanets.NIBIRU, EntityInfectedSquid.class, 10, 4, 4);

        //        MPPlanets.FRONOS = CelestialRegisterHelper.createPlanet("fronos", MPPlanets.LAZENDUS_SOLAR_SYSTEM, 1.2762F, 1.5F, 1 / 0.05F, 0.5F, 7, ConfigManagerMP.idDimensionFronos, WorldProviderFronos.class);
        //        CelestialRegisterHelper.setAtmosphereComponentList(MPPlanets.NIBIRU, EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.HYDROGEN);

        MPPlanets.register();
    }

    private static void register()
    {
        CelestialRegisterHelper.registerSolarSystem(MPPlanets.LAZENDUS_SOLAR_SYSTEM);
        CelestialRegisterHelper.registerPlanet(MPPlanets.DIONA);
        CelestialRegisterHelper.registerPlanet(MPPlanets.CHALOS);
        CelestialRegisterHelper.registerPlanet(MPPlanets.NIBIRU);
        //        CelestialRegisterHelper.registerPlanet(MPPlanets.FRONOS);
        CelestialRegisterHelper.registerTeleportType(WorldProviderDiona.class, new TeleportTypeMoon());
        CelestialRegisterHelper.registerTeleportType(WorldProviderChalos.class, new TeleportTypeMoon());
        CelestialRegisterHelper.registerTeleportType(WorldProviderNibiru.class, new TeleportTypeMoon());
        //        CelestialRegisterHelper.registerTeleportType(WorldProviderFronos.class, new TeleportTypeMoon());
        CelestialRegisterHelper.registerRocketGui(WorldProviderDiona.class, "diona");
        CelestialRegisterHelper.registerRocketGui(WorldProviderChalos.class, "chalos");
        CelestialRegisterHelper.registerRocketGui(WorldProviderNibiru.class, "nibiru");
        //        CelestialRegisterHelper.registerRocketGui(WorldProviderFronos.class, "fronos");
    }
}