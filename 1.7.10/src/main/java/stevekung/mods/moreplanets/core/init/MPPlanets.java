/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody.ScalableDistance;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import stevekung.mods.moreplanets.asteroids.darkasteroids.dimension.TeleportTypeDarkAsteroids;
import stevekung.mods.moreplanets.asteroids.darkasteroids.dimension.WorldProviderDarkAsteroids;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.dimension.TeleportTypeMP;
import stevekung.mods.moreplanets.core.dimension.TeleportTypeOrbitMP;
import stevekung.mods.moreplanets.core.spacestation.jupiter.WorldProviderJupiterOrbit;
import stevekung.mods.moreplanets.core.spacestation.mars.WorldProviderMarsOrbit;
import stevekung.mods.moreplanets.core.world.temp.WorldProviderJupiter;
import stevekung.mods.moreplanets.moons.deimos.dimension.WorldProviderDeimos;
import stevekung.mods.moreplanets.moons.koentus.dimension.WorldProviderKoentus;
import stevekung.mods.moreplanets.moons.phobos.dimension.WorldProviderPhobos;
import stevekung.mods.moreplanets.planets.diona.dimension.WorldProviderDiona;
import stevekung.mods.moreplanets.planets.fronos.dimension.WorldProviderFronos;
import stevekung.mods.moreplanets.planets.kapteynb.dimension.WorldProviderKapteynB;
import stevekung.mods.moreplanets.planets.mercury.dimension.WorldProviderMercury;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.planets.pluto.dimension.WorldProviderPluto;
import stevekung.mods.moreplanets.planets.polongnius.dimension.WorldProviderPolongnius;
import stevekung.mods.moreplanets.planets.siriusb.dimension.WorldProviderSiriusB;
import stevekung.mods.moreplanets.planets.venus.dimension.WorldProviderVenus;

public class MPPlanets
{
    public static void init()
    {
        TeleportTypeMP teleport = new TeleportTypeMP();

        // Init Solar Systems
        MorePlanetsCore.siriusSolarSystem = new SolarSystem("sirius", "milkyWay").setMapPosition(new Vector3(1.25F, 1.5F, 0.0F));
        MorePlanetsCore.sirius = MPPlanets.createStar("sirius", MorePlanetsCore.siriusSolarSystem, new ResourceLocation("mpcore:textures/gui/celestialbodies/sirius_celestial.png"));
        MorePlanetsCore.siriusSolarSystem.setMainStar(MorePlanetsCore.sirius);

        MorePlanetsCore.kapteynBSolarSystem = new SolarSystem("kapteyn", "milkyWay").setMapPosition(new Vector3(2.0F, 1.8F, 0.0F));
        MorePlanetsCore.kapteyn = MPPlanets.createStar("kapteyn", MorePlanetsCore.kapteynBSolarSystem, new ResourceLocation("kapteynb:textures/gui/celestialbodies/kapteyn_star_celestial.png"));
        MorePlanetsCore.kapteynBSolarSystem.setMainStar(MorePlanetsCore.kapteyn);

        MorePlanetsCore.darkSolarSystem = new SolarSystem("dark", "milkyWay").setMapPosition(new Vector3(-3.5F, 2.2F, 0.0F));
        MorePlanetsCore.darkStar = MPPlanets.createStar("dark", MorePlanetsCore.darkSolarSystem, new ResourceLocation("mpcore:textures/gui/celestialbodies/dark_star_celestial.png"));
        MorePlanetsCore.darkSolarSystem.setMainStar(MorePlanetsCore.darkStar);

        // Init Planets
        MorePlanetsCore.diona = MPPlanets.createPlanet("diona", MorePlanetsCore.siriusSolarSystem, 8.7446F, 5.0F, 13.7685F, 0.876F, 4, new ResourceLocation("diona:textures/gui/celestialbodies/diona.png"));
        MorePlanetsCore.diona.setDimensionInfo(ConfigManagerMP.idDimensionDiona, WorldProviderDiona.class);
        MorePlanetsCore.diona.atmosphereComponent(IAtmosphericGas.CO2);

        MorePlanetsCore.polongnius = MPPlanets.createPlanet("polongnius", MorePlanetsCore.siriusSolarSystem, 12.2478F, 4.25F, 76.4168F, 1.465F, 5, new ResourceLocation("polongnius:textures/gui/celestialbodies/polongnius.png"));
        MorePlanetsCore.polongnius.setDimensionInfo(ConfigManagerMP.idDimensionPolongnius, WorldProviderPolongnius.class);
        MorePlanetsCore.polongnius.atmosphereComponent(IAtmosphericGas.CO2).atmosphereComponent(IAtmosphericGas.ARGON);

        MorePlanetsCore.nibiru = MPPlanets.createPlanet("nibiru", MorePlanetsCore.siriusSolarSystem, 52.4341F, 3.75F, 71.6582F, 4.678F, 6, new ResourceLocation("nibiru:textures/gui/celestialbodies/nibiru.png"));
        MorePlanetsCore.nibiru.setDimensionInfo(ConfigManagerMP.idDimensionNibiru, WorldProviderNibiru.class);
        MorePlanetsCore.nibiru.atmosphereComponent(IAtmosphericGas.CO2).atmosphereComponent(IAtmosphericGas.ARGON).atmosphereComponent(IAtmosphericGas.HELIUM);

        MorePlanetsCore.fronos = MPPlanets.createPlanet("fronos", MorePlanetsCore.siriusSolarSystem, 1.2762F, 2.5F, 1 / 0.05F, 0.5316F, 7, new ResourceLocation("fronos:textures/gui/celestialbodies/fronos.png"));
        MorePlanetsCore.fronos.setDimensionInfo(ConfigManagerMP.idDimensionFronos, WorldProviderFronos.class);
        MorePlanetsCore.fronos.atmosphereComponent(IAtmosphericGas.OXYGEN).atmosphereComponent(IAtmosphericGas.WATER).atmosphereComponent(IAtmosphericGas.NITROGEN).atmosphereComponent(IAtmosphericGas.HYDROGEN);

        MorePlanetsCore.kapteynB = MPPlanets.createPlanet("kapteynB", MorePlanetsCore.kapteynBSolarSystem, 1 / 2.0F, 1.0F, 1.9746F, 3.7654F, 7, new ResourceLocation("kapteynb:textures/gui/celestialbodies/kapteyn_b.png"));
        MorePlanetsCore.kapteynB.setDimensionInfo(ConfigManagerMP.idDimensionKapteynB, WorldProviderKapteynB.class);
        MorePlanetsCore.kapteynB.atmosphereComponent(IAtmosphericGas.NITROGEN).atmosphereComponent(IAtmosphericGas.WATER).atmosphereComponent(IAtmosphericGas.HELIUM).atmosphereComponent(IAtmosphericGas.HYDROGEN);

        MorePlanetsCore.siriusB = MPPlanets.createPlanet("siriusB", MorePlanetsCore.siriusSolarSystem, 0.0F, 0.225F, Float.MAX_VALUE, 0.125F, 8, new ResourceLocation("siriusb:textures/gui/celestialbodies/sirius_b.png"));
        MorePlanetsCore.siriusB.setDimensionInfo(ConfigManagerMP.idDimensionSiriusB, WorldProviderSiriusB.class);
        MorePlanetsCore.siriusB.atmosphereComponent(IAtmosphericGas.CO2).atmosphereComponent(IAtmosphericGas.HELIUM);

        if (ConfigManagerMP.enableMercuryPlanet)
        {
            MorePlanetsCore.mercury = MPPlanets.createPlanet("mercury", GalacticraftCore.solarSystemSol, 1.45F, 0.5F, 0.24096385542168674698795180722892F, 0.5319F, 4, new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/mercury.png"));
            MorePlanetsCore.mercury.setDimensionInfo(ConfigManagerMP.idDimensionMercury, WorldProviderMercury.class);
            MorePlanetsCore.mercury.atmosphereComponent(IAtmosphericGas.HELIUM).atmosphereComponent(IAtmosphericGas.HYDROGEN);
        }
        if (ConfigManagerMP.enableVenusPlanet)
        {
            MorePlanetsCore.venus = MPPlanets.createPlanet("venus", GalacticraftCore.solarSystemSol, 2.0F, 0.75F, 0.6152793F, 0.0F, 3, new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/venus.png"));
            MorePlanetsCore.venus.setDimensionInfo(ConfigManagerMP.idDimensionVenus, WorldProviderVenus.class);
            MorePlanetsCore.venus.atmosphereComponent(IAtmosphericGas.CO2).atmosphereComponent(IAtmosphericGas.NITROGEN).atmosphereComponent(IAtmosphericGas.ARGON);
        }
        if (ConfigManagerMP.enableJupiterPlanet)
        {
            MorePlanetsCore.jupiter = MPPlanets.createPlanet("jupiter", GalacticraftCore.solarSystemSol, 2.3F, 1.5F, 11.861993428258488499452354874042F, 0.5319F, 4, new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/jupiter.png"));
            MorePlanetsCore.jupiter.setDimensionInfo(ConfigManagerMP.idDimensionJupiter, WorldProviderJupiter.class);
            MorePlanetsCore.jupiter.setBodyIcon(new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/jupiter.png"));
        }
        if (ConfigManagerMP.enablePlutoPlanet)
        {
            MorePlanetsCore.pluto = MPPlanets.createPlanet("pluto", GalacticraftCore.solarSystemSol, 2.0F, 2.5F, 194.84119F, 0.0F, 5, new ResourceLocation("pluto:textures/gui/celestialbodies/pluto.png"));
            MorePlanetsCore.pluto.setDimensionInfo(ConfigManagerMP.idDimensionPluto, WorldProviderPluto.class);
            MorePlanetsCore.pluto.atmosphereComponent(IAtmosphericGas.CO2).atmosphereComponent(IAtmosphericGas.HELIUM).atmosphereComponent(IAtmosphericGas.NITROGEN);
        }

        MorePlanetsCore.darkAsteroids = MPPlanets.createPlanet("darkAsteroids", MorePlanetsCore.darkSolarSystem, (float)(Math.random() * (2 * Math.PI)), 1.375F, 45.0F, 0.0F, 5, new ResourceLocation("mpcore:textures/gui/celestialbodies/dark_asteroids.png"));
        MorePlanetsCore.darkAsteroids.setDimensionInfo(ConfigManagerMP.idDimensionDarkAsteroids, WorldProviderDarkAsteroids.class);

        // Init Moons
        MorePlanetsCore.koentus = MPPlanets.createMoon("koentus", MorePlanetsCore.diona, 2.436F, 9.5F, 1 / 0.01F, 0.3867F, 4, new ResourceLocation("koentus:textures/gui/celestialbodies/koentus.png"));
        MorePlanetsCore.koentus.setDimensionInfo(ConfigManagerMP.idDimensionKoentus, WorldProviderKoentus.class);
        MorePlanetsCore.koentus.atmosphereComponent(IAtmosphericGas.HYDROGEN).atmosphereComponent(IAtmosphericGas.NITROGEN).atmosphereComponent(IAtmosphericGas.HELIUM);

        if (ConfigManagerMP.enablePhobosMoon)
        {
            MorePlanetsCore.phobos = MPPlanets.createMoon("phobos", MarsModule.planetMars, 4.25F, 10.0F, 1 / 0.01F, 0.3867F, 2, new ResourceLocation("phobos:textures/gui/celestialbodies/phobos.png"));
            MorePlanetsCore.phobos.setDimensionInfo(ConfigManagerMP.idDimensionPhobos, WorldProviderPhobos.class);
        }
        if (ConfigManagerMP.enableDeimosMoon)
        {
            MorePlanetsCore.deimos = MPPlanets.createMoon("deimos", MarsModule.planetMars, 2.25F, 15.0F, 1 / 0.01F, 0.3867F, 2, new ResourceLocation("deimos:textures/gui/celestialbodies/deimos.png"));
            MorePlanetsCore.deimos.setDimensionInfo(ConfigManagerMP.idDimensionDeimos, WorldProviderDeimos.class);
        }

        // Init Space Stations
        if (ConfigManagerMP.enableJupiterSpaceStation)
        {
            MorePlanetsCore.jupiterSpaceStation = MPPlanets.createSatellite("jupiterMP", MorePlanetsCore.jupiter, 0.25F, 25.0F, 1 / 0.05F, 0.2667F, 4, new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/spaceStation.png"));
            MorePlanetsCore.jupiterSpaceStation.setDimensionInfo(ConfigManagerMP.idDimensionJupiterSpaceStation, ConfigManagerMP.idDimensionStaticJupiterSpaceStation, WorldProviderJupiterOrbit.class);
        }
        if (ConfigManagerMP.enableMarsSpaceStation)
        {
            MorePlanetsCore.marsSpaceStation = MPPlanets.createSatellite("marsMP", MarsModule.planetMars, 0.25F, 12.5F, 1 / 0.05F, 0.2667F, 2, new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/spaceStation.png"));
            MorePlanetsCore.marsSpaceStation.setDimensionInfo(ConfigManagerMP.idDimensionMarsSpaceStation, ConfigManagerMP.idDimensionStaticMarsSpaceStation, WorldProviderMarsOrbit.class);
        }

        // Register
        GalaxyRegistry.registerSolarSystem(MorePlanetsCore.siriusSolarSystem);
        GalaxyRegistry.registerSolarSystem(MorePlanetsCore.kapteynBSolarSystem);
        GalaxyRegistry.registerSolarSystem(MorePlanetsCore.darkSolarSystem);

        GalaxyRegistry.registerPlanet(MorePlanetsCore.diona);
        GalaxyRegistry.registerPlanet(MorePlanetsCore.polongnius);
        GalaxyRegistry.registerPlanet(MorePlanetsCore.nibiru);
        GalaxyRegistry.registerPlanet(MorePlanetsCore.fronos);
        GalaxyRegistry.registerPlanet(MorePlanetsCore.kapteynB);
        GalaxyRegistry.registerPlanet(MorePlanetsCore.siriusB);
        GalaxyRegistry.registerPlanet(MorePlanetsCore.darkAsteroids);

        GalaxyRegistry.registerMoon(MorePlanetsCore.koentus);

        if (ConfigManagerMP.enableJupiterSpaceStation) { GalaxyRegistry.registerSatellite(MorePlanetsCore.jupiterSpaceStation); }
        if (ConfigManagerMP.enableMarsSpaceStation) { GalaxyRegistry.registerSatellite(MorePlanetsCore.marsSpaceStation); }

        if (ConfigManagerMP.enableMercuryPlanet) { GalaxyRegistry.registerPlanet(MorePlanetsCore.mercury); }
        if (ConfigManagerMP.enableVenusPlanet) { GalaxyRegistry.registerPlanet(MorePlanetsCore.venus); }
        if (ConfigManagerMP.enablePlutoPlanet) { GalaxyRegistry.registerPlanet(MorePlanetsCore.pluto); }
        if (ConfigManagerMP.enableJupiterPlanet) { GalaxyRegistry.registerPlanet(MorePlanetsCore.jupiter); }

        if (ConfigManagerMP.enablePhobosMoon) { GalaxyRegistry.registerMoon(MorePlanetsCore.deimos); }
        if (ConfigManagerMP.enableDeimosMoon) { GalaxyRegistry.registerMoon(MorePlanetsCore.phobos); }

        if (ConfigManagerMP.enableJupiterSpaceStation) { MPPlanets.registerProvider(ConfigManagerMP.idDimensionJupiterSpaceStation, ConfigManagerMP.idDimensionStaticJupiterSpaceStation, WorldProviderJupiterOrbit.class); }
        if (ConfigManagerMP.enableMarsSpaceStation) { MPPlanets.registerProvider(ConfigManagerMP.idDimensionMarsSpaceStation, ConfigManagerMP.idDimensionStaticMarsSpaceStation, WorldProviderMarsOrbit.class); }

        GalacticraftRegistry.registerTeleportType(WorldProviderDiona.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderPolongnius.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderNibiru.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderKoentus.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderFronos.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderKapteynB.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderSiriusB.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderMercury.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderVenus.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderPluto.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderDeimos.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderPhobos.class, teleport);
        GalacticraftRegistry.registerTeleportType(WorldProviderJupiterOrbit.class, new TeleportTypeOrbitMP());
        GalacticraftRegistry.registerTeleportType(WorldProviderMarsOrbit.class, new TeleportTypeOrbitMP());
        GalacticraftRegistry.registerTeleportType(WorldProviderDarkAsteroids.class, new TeleportTypeDarkAsteroids());

        GalacticraftRegistry.registerRocketGui(WorldProviderDiona.class, new ResourceLocation("diona:textures/gui/diona_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderPolongnius.class, new ResourceLocation("polongnius:textures/gui/polongnius_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderNibiru.class, new ResourceLocation("nibiru:textures/gui/nibiru_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderKoentus.class, new ResourceLocation("koentus:textures/gui/koentus_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderFronos.class, new ResourceLocation("fronos:textures/gui/fronos_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderKapteynB.class, new ResourceLocation("kapteynb:textures/gui/kapteyn_b_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderSiriusB.class, new ResourceLocation("siriusb:textures/gui/sirius_b_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderMercury.class, new ResourceLocation("mercury:textures/gui/mercury_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderVenus.class, new ResourceLocation("venus:textures/gui/venus_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderPluto.class, new ResourceLocation("pluto:textures/gui/pluto_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderDeimos.class, new ResourceLocation("deimos:textures/gui/deimos_rocket_gui.png"));
        GalacticraftRegistry.registerRocketGui(WorldProviderPhobos.class, new ResourceLocation("phobos:textures/gui/phobos_rocket_gui.png"));
    }

    public static Planet createPlanet(String name, SolarSystem solar, float phaseShift, float distance, float orbitTime, float size, int tier, ResourceLocation resource)
    {
        Planet planet = new Planet(name).setParentSolarSystem(solar);
        planet.setPhaseShift(phaseShift);
        planet.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
        planet.setRelativeOrbitTime(orbitTime);
        planet.setRelativeSize(size);
        planet.setTierRequired(tier);
        planet.setBodyIcon(resource);
        return planet;
    }

    public static Moon createMoon(String name, Planet planet, float phaseShift, float distance, float orbitTime, float size, int tier, ResourceLocation resource)
    {
        Moon moon = new Moon(name).setParentPlanet(planet);
        moon.setPhaseShift(phaseShift);
        moon.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
        moon.setRelativeOrbitTime(orbitTime);
        moon.setRelativeSize(size);
        moon.setTierRequired(tier);
        moon.setBodyIcon(resource);
        return moon;
    }

    public static Satellite createSatellite(String name, Planet planet, float phaseShift, float distance, float orbitTime, float size, int tier, ResourceLocation resource)
    {
        Satellite satellite = new Satellite(name).setParentBody(planet);
        satellite.setPhaseShift(phaseShift);
        satellite.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
        satellite.setRelativeOrbitTime(orbitTime);
        satellite.setRelativeSize(size);
        satellite.setTierRequired(tier);
        satellite.setBodyIcon(resource);
        return satellite;
    }

    public static Star createStar(String name, SolarSystem solar, ResourceLocation resource)
    {
        Star star = new Star(name).setParentSolarSystem(solar);
        star.setTierRequired(-1);
        star.setBodyIcon(resource);
        return star;
    }

    public static void registerProvider(int id, int staticId, Class<? extends WorldProvider> provider)
    {
        GalacticraftRegistry.registerProvider(id, provider, false, 0);
        GalacticraftRegistry.registerProvider(staticId, provider, true, 0);
    }
}