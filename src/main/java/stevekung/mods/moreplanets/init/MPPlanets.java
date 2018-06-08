package stevekung.mods.moreplanets.init;

import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.dimension.WorldProviderSpaceNether;
import stevekung.mods.moreplanets.moons.koentus.dimension.WorldProviderKoentus;
import stevekung.mods.moreplanets.planets.chalos.dimension.WorldProviderChalos;
import stevekung.mods.moreplanets.planets.diona.dimension.WorldProviderDiona;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.utils.CelestialRegistryUtils;

public class MPPlanets
{
    public static Planet SPACE_NETHER;
    public static Planet DIONA;
    public static Planet CHALOS;
    public static Planet NIBIRU;
    public static Planet FRONOS;
    public static Moon KOENTUS;
    public static SolarSystem LAZENDUS_SOLAR_SYSTEM;
    public static Star LAZENDUS;

    public static void init()
    {
        // Solar System
        MPPlanets.LAZENDUS_SOLAR_SYSTEM = CelestialRegistryUtils.createStarAndSolarSystem(MPPlanets.LAZENDUS_SOLAR_SYSTEM, MPPlanets.LAZENDUS, "lazendus", "lazendus", new Vector3(0.75F, 1.25F, 0.0F), "lazendus_celestial");

        // Planets
        MPPlanets.SPACE_NETHER = CelestialRegistryUtils.createPlanet("space_nether", GalacticraftCore.solarSystemSol, 0.0F, 0.25F, 1.0F, 1.0F, -1, ConfigManagerMP.moreplanets_dimension.idDimensionSpaceNether, WorldProviderSpaceNether.class);
        MPPlanets.DIONA = CelestialRegistryUtils.createPlanet("diona", MPPlanets.LAZENDUS_SOLAR_SYSTEM, -14.25F, 4.25F, 20.0F, 0.876F, ConfigManagerMP.moreplanets_planet_settings.planetDionaTier, ConfigManagerMP.moreplanets_dimension.idDimensionDiona, WorldProviderDiona.class);
        MPPlanets.CHALOS = CelestialRegistryUtils.createPlanet("chalos", MPPlanets.LAZENDUS_SOLAR_SYSTEM, 8.75F, 3.5F, 60.5F, 10.0F, ConfigManagerMP.moreplanets_planet_settings.planetChalosTier, ConfigManagerMP.moreplanets_dimension.idDimensionChalos, WorldProviderChalos.class);
        MPPlanets.NIBIRU = CelestialRegistryUtils.createPlanet("nibiru", MPPlanets.LAZENDUS_SOLAR_SYSTEM, 27.0F, 2.0F, 1050.5F, 2.0F, ConfigManagerMP.moreplanets_planet_settings.planetNibiruTier, ConfigManagerMP.moreplanets_dimension.idDimensionNibiru, WorldProviderNibiru.class);

        // Moons
        MPPlanets.KOENTUS = CelestialRegistryUtils.createMoon("koentus", MPPlanets.DIONA, 2.436F, 9.5F, 1 / 0.01F, 0.3867F, ConfigManagerMP.moreplanets_moon_settings.moonKoentusTier, ConfigManagerMP.moreplanets_dimension.idDimensionKoentus, WorldProviderKoentus.class);

        // Planets
        CelestialRegistryUtils.setAtmosphereComponentList(MPPlanets.DIONA, EnumAtmosphericGas.ARGON, EnumAtmosphericGas.HELIUM);
        CelestialRegistryUtils.setAtmosphere(MPPlanets.DIONA, false, false, false, 0.0F, 0.0F, 0.0F);
        CelestialRegistryUtils.setChecklistKeys(MPPlanets.DIONA, "equip_oxygen_suit");

        CelestialRegistryUtils.setAtmosphereComponentList(MPPlanets.CHALOS, EnumAtmosphericGas.HYDROGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.CO2);
        CelestialRegistryUtils.setAtmosphere(MPPlanets.CHALOS, false, false, false, 0.0F, 0.65F, 28.0F);
        CelestialRegistryUtils.setChecklistKeys(MPPlanets.CHALOS, "equip_oxygen_suit");

        CelestialRegistryUtils.setAtmosphereComponentList(MPPlanets.NIBIRU, EnumAtmosphericGas.WATER, EnumAtmosphericGas.HELIUM, EnumAtmosphericGas.CO2, EnumAtmosphericGas.ARGON);
        CelestialRegistryUtils.setAtmosphere(MPPlanets.NIBIRU, false, true, true, 0.0F, 1.25F, 46.5F);
        CelestialRegistryUtils.setChecklistKeys(MPPlanets.NIBIRU, "equip_oxygen_suit", "thermal_padding_t2", "equip_shield_controller", "craft_infected_spore_protection_capsule");

        // Moons
        CelestialRegistryUtils.setAtmosphereComponentList(MPPlanets.KOENTUS, EnumAtmosphericGas.HYDROGEN, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.HELIUM);
        CelestialRegistryUtils.setAtmosphere(MPPlanets.KOENTUS, false, false, false, -2.0F, 2.0F, 5.0F);

        //        MPPlanets.FRONOS = CelestialRegisterHelper.createPlanet("fronos", MPPlanets.LAZENDUS_SOLAR_SYSTEM, 1.2762F, 1.5F, 1 / 0.05F, 0.5F, 7, ConfigManagerMP.idDimensionFronos, WorldProviderFronos.class);
        //        CelestialRegisterHelper.setAtmosphereComponentList(MPPlanets.NIBIRU, EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.HYDROGEN);
    }

    public static void register()
    {
        TeleportTypeMoon teleport = new TeleportTypeMoon();

        CelestialRegistryUtils.registerSolarSystem(MPPlanets.LAZENDUS_SOLAR_SYSTEM);

        if (ConfigManagerMP.moreplanets_general.enableSurvivalPlanetSelection)
        {
            CelestialRegistryUtils.registerPlanet(MPPlanets.SPACE_NETHER);
        }

        CelestialRegistryUtils.registerPlanet(MPPlanets.DIONA);
        CelestialRegistryUtils.registerPlanet(MPPlanets.CHALOS);
        CelestialRegistryUtils.registerPlanet(MPPlanets.NIBIRU);
        CelestialRegistryUtils.registerMoon(MPPlanets.KOENTUS);
        //        CelestialRegisterHelper.registerPlanet(MPPlanets.FRONOS);
        CelestialRegistryUtils.registerTeleportType(WorldProviderDiona.class, teleport);
        CelestialRegistryUtils.registerTeleportType(WorldProviderChalos.class, teleport);
        CelestialRegistryUtils.registerTeleportType(WorldProviderNibiru.class, teleport);
        CelestialRegistryUtils.registerTeleportType(WorldProviderKoentus.class, teleport);
        //        CelestialRegisterHelper.registerTeleportType(WorldProviderFronos.class, new TeleportTypeMoon());
        CelestialRegistryUtils.registerRocketGui(WorldProviderDiona.class, "diona");
        CelestialRegistryUtils.registerRocketGui(WorldProviderChalos.class, "chalos");
        CelestialRegistryUtils.registerRocketGui(WorldProviderNibiru.class, "nibiru");
        CelestialRegistryUtils.registerRocketGui(WorldProviderKoentus.class, "koentus");
        //        CelestialRegisterHelper.registerRocketGui(WorldProviderFronos.class, "fronos");
    }
}