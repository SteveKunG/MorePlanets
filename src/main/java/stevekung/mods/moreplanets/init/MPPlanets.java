package stevekung.mods.moreplanets.init;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import micdoodle8.mods.galacticraft.core.entities.*;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.chalos.dimension.WorldProviderChalos;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseCow;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseFloater;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseSlime;
import stevekung.mods.moreplanets.planets.diona.dimension.WorldProviderDiona;
import stevekung.mods.moreplanets.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusCreeper;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusSkeleton;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusZombie;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.planets.nibiru.entity.*;
import stevekung.mods.moreplanets.utils.CelestialRegistryUtils;

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
        MPPlanets.LAZENDUS_SOLAR_SYSTEM = CelestialRegistryUtils.createStarAndSolarSystem(MPPlanets.LAZENDUS_SOLAR_SYSTEM, MPPlanets.LAZENDUS, "lazendus", "lazendus", new Vector3(0.75F, 1.25F, 0.0F), "lazendus_celestial");

        // Planets
        MPPlanets.DIONA = CelestialRegistryUtils.createPlanet("diona", MPPlanets.LAZENDUS_SOLAR_SYSTEM, -14.25F, 4.25F, 20.0F, 0.876F, ConfigManagerMP.moreplanets_planet_settings.planetDionaTier, ConfigManagerMP.moreplanets_dimension.idDimensionDiona, WorldProviderDiona.class);
        MPPlanets.CHALOS = CelestialRegistryUtils.createPlanet("chalos", MPPlanets.LAZENDUS_SOLAR_SYSTEM, 8.75F, 3.5F, 60.5F, 10.0F, ConfigManagerMP.moreplanets_planet_settings.planetChalosTier, ConfigManagerMP.moreplanets_dimension.idDimensionChalos, WorldProviderChalos.class);
        MPPlanets.NIBIRU = CelestialRegistryUtils.createPlanet("nibiru", MPPlanets.LAZENDUS_SOLAR_SYSTEM, 27.0F, 2.0F, 1050.5F, 2.0F, ConfigManagerMP.moreplanets_planet_settings.planetNibiruTier, ConfigManagerMP.moreplanets_dimension.idDimensionNibiru, WorldProviderNibiru.class);

        CelestialRegistryUtils.setAtmosphereComponentList(MPPlanets.DIONA, EnumAtmosphericGas.ARGON, EnumAtmosphericGas.HELIUM);
        CelestialRegistryUtils.setAtmosphere(MPPlanets.DIONA, false, false, false, 0.0F, 0.0F, 0.0F);
        CelestialRegistryUtils.setChecklistKeys(MPPlanets.DIONA, "equipOxygenSuit");
        CelestialRegistryUtils.setBiomeInfo(MPPlanets.DIONA, MPBiomes.DIONA);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityEvolvedZombie.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityEvolvedSpider.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityEvolvedSkeleton.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityEvolvedCreeper.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityEvolvedEnderman.class, 10, 1, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityEvolvedWitch.class, 5, 1, 1);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityZeliusZombie.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityZeliusCreeper.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityZeliusSkeleton.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.DIONA, EntityAlienMiner.class, 1, 1, 2);

        CelestialRegistryUtils.setAtmosphereComponentList(MPPlanets.CHALOS, EnumAtmosphericGas.HYDROGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.CO2);
        CelestialRegistryUtils.setAtmosphere(MPPlanets.CHALOS, false, false, false, 0.0F, 0.65F, 28.0F);
        CelestialRegistryUtils.setChecklistKeys(MPPlanets.CHALOS, "equipOxygenSuit");
        CelestialRegistryUtils.setBiomeInfo(MPPlanets.CHALOS, MPBiomes.CHALOS_PLAINS, MPBiomes.CHALOS_MOUTAINS, MPBiomes.SLIMELY_STREAM);
        CelestialRegistryUtils.addMobInfo(MPPlanets.CHALOS, EntityEvolvedZombie.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.CHALOS, EntityEvolvedSpider.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.CHALOS, EntityEvolvedSkeleton.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.CHALOS, EntityEvolvedCreeper.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.CHALOS, EntityEvolvedEnderman.class, 10, 1, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.CHALOS, EntityEvolvedWitch.class, 5, 1, 1);
        CelestialRegistryUtils.addMobInfo(MPPlanets.CHALOS, EntityCheeseFloater.class, 20, 1, 2);
        CelestialRegistryUtils.addMobInfo(MPPlanets.CHALOS, EntityCheeseSlime.class, 30, 2, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.CHALOS, EntityCheeseCow.class, 8, 4, 4);

        CelestialRegistryUtils.setAtmosphereComponentList(MPPlanets.NIBIRU, EnumAtmosphericGas.WATER, EnumAtmosphericGas.HELIUM, EnumAtmosphericGas.CO2, EnumAtmosphericGas.ARGON);
        CelestialRegistryUtils.setAtmosphere(MPPlanets.NIBIRU, false, true, true, 0.0F, 1.25F, 46.5F);
        CelestialRegistryUtils.setChecklistKeys(MPPlanets.NIBIRU, "equipOxygenSuit", "thermalPaddingT2", "craftInfectedProtectionCapsule");
        CelestialRegistryUtils.setBiomeInfo(MPPlanets.NIBIRU, MPBiomes.INFECTED_PLAINS, MPBiomes.INFECTED_DEAD_SAVANNA, MPBiomes.INFECTED_DESERT, MPBiomes.INFECTED_RIVER, MPBiomes.INFECTED_OCEAN, MPBiomes.INFECTED_DEEP_OCEAN, MPBiomes.INFECTED_FOREST, MPBiomes.INFECTED_DEAD_DARK_FOREST, MPBiomes.INFECTED_MOUNTAINS, MPBiomes.INFECTED_SWAMP, MPBiomes.INFECTED_TAIGA, MPBiomes.INFECTED_JUNGLE, MPBiomes.INFECTED_ICE_SPIKES, MPBiomes.GREEN_VEIN_FIELDS);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedZombie.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedSpider.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedSkeleton.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedCreeper.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedEnderman.class, 10, 1, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityEvolvedWitch.class, 5, 1, 1);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityInfectedChicken.class, 10, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityInfectedCow.class, 8, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityShlime.class, 12, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityInfectedZombie.class, 100, 4, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityGiantWorm.class, 100, 2, 4);
        CelestialRegistryUtils.addMobInfo(MPPlanets.NIBIRU, EntityInfectedSquid.class, 10, 4, 4);

        //        MPPlanets.FRONOS = CelestialRegisterHelper.createPlanet("fronos", MPPlanets.LAZENDUS_SOLAR_SYSTEM, 1.2762F, 1.5F, 1 / 0.05F, 0.5F, 7, ConfigManagerMP.idDimensionFronos, WorldProviderFronos.class);
        //        CelestialRegisterHelper.setAtmosphereComponentList(MPPlanets.NIBIRU, EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.HYDROGEN);
    }

    public static void register()
    {
        CelestialRegistryUtils.registerSolarSystem(MPPlanets.LAZENDUS_SOLAR_SYSTEM);
        CelestialRegistryUtils.registerPlanet(MPPlanets.DIONA);
        CelestialRegistryUtils.registerPlanet(MPPlanets.CHALOS);
        CelestialRegistryUtils.registerPlanet(MPPlanets.NIBIRU);
        //        CelestialRegisterHelper.registerPlanet(MPPlanets.FRONOS);
        CelestialRegistryUtils.registerTeleportType(WorldProviderDiona.class, new TeleportTypeMoon());
        CelestialRegistryUtils.registerTeleportType(WorldProviderChalos.class, new TeleportTypeMoon());
        CelestialRegistryUtils.registerTeleportType(WorldProviderNibiru.class, new TeleportTypeMoon());
        //        CelestialRegisterHelper.registerTeleportType(WorldProviderFronos.class, new TeleportTypeMoon());
        CelestialRegistryUtils.registerRocketGui(WorldProviderDiona.class, "diona");
        CelestialRegistryUtils.registerRocketGui(WorldProviderChalos.class, "chalos");
        CelestialRegistryUtils.registerRocketGui(WorldProviderNibiru.class, "nibiru");
        //        CelestialRegisterHelper.registerRocketGui(WorldProviderFronos.class, "fronos");
    }
}