/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import java.util.HashMap;
import java.util.LinkedHashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.EntityRegistry.EntityRegistration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.asteroids.darkasteroids.entities.EntityDarkAsteroid;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.entities.EntityEvolvedWitch;
import stevekung.mods.moreplanets.core.util.CompatibilityUtilMP;
import stevekung.mods.moreplanets.core.util.MPLog;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaCrab;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaSquid;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaWaterBomb;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteor;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteorChunk;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusSludgeling;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusianVillager;
import stevekung.mods.moreplanets.planets.diona.entities.*;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityLaserMP;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityProjectileFronisiumTNT;
import stevekung.mods.moreplanets.planets.fronos.entities.*;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.*;
import stevekung.mods.moreplanets.planets.kapteynb.entities.*;
import stevekung.mods.moreplanets.planets.nibiru.entities.*;
import stevekung.mods.moreplanets.planets.pluto.entities.EntityPlutoAlien;
import stevekung.mods.moreplanets.planets.pluto.entities.EntityPlutoCreeperBoss;
import stevekung.mods.moreplanets.planets.polongnius.entities.*;
import stevekung.mods.moreplanets.planets.polongnius.entities.projectiles.EntityCheeseSpore;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntityEvolvedSiriusBlazeBoss;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusCreeper;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.entities.projectiles.EntitySiriusSmallFireball;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianBlaze;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianSlime;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianVillager;

public class MPEntities
{
    public static HashMap<Integer, EntityEggInfo> entityEggs = new LinkedHashMap();

    public static void init()
    {
        MPEntities.registerEntities();
        MPEntities.registerNonMobEntities();
    }

    private static void registerEntities()
    {
        registerEntity(EntitySpaceWolf.class, "SpaceWolf", 0, 14144467, 13545366);
        registerEntity(EntityEvolvedEnderman.class, "EvolvedEnderman", 1, 1447446, 0);
        registerEntity(EntityDustSludgeling.class, "DustSludgeling", 2, -4144960, -8355712);
        registerEntity(EntityDionaCreeperBoss.class, "DionaCreeperBoss", 3, -8355712, -16726874);
        registerEntity(EntityDionaMinionCreeper.class, "DionaMinionCreeper", 4, -8355712, -9269647);

        registerEntity(EntityCheeseCow.class, "CheeseCow", 5, -19712, -6985197);
        registerEntity(EntityCheeseSlime.class, "CheeseSlime", 6, -14848, -8323);
        registerEntity(EntityCheeseCubeEyeBoss.class, "CheeseCubeBoss", 7, -14848, -4259830);

        registerEntity(EntityInfectedWorm.class, "InfectedWorm", 8, -5160639, -7131345);
        registerEntity(EntityGiantWorm.class, "GiantWorm", 9, -2060769, -1413099);
        registerEntity(EntityInfectedZombie.class, "InfectedZombie", 10, -7520229, -2060769);
        registerEntity(EntityEvolvedInfectedSpiderBoss.class, "EvolvedInfectedSpiderBoss", 11, -6796279, -13369498);

        registerEntity(EntityKoentusianVillager.class, "KoentusianVillager", 12, -16777040, -16744320);
        registerEntity(EntityKoentusSludgeling.class, "KoentusSludgeling", 13, -16777040, -16777031);

        registerEntity(EntityBearry.class, "Bearry", 14, -391882, -16744448);
        registerEntity(EntityBerry.class, "Berry", 15, -11403100, -10157878);
        registerEntity(EntityMarshmallow.class, "Marshmallow", 16, -2631721, -1052689);
        registerEntity(EntityKiwi.class, "Kiwi", 17, -8608972, -8031948);
        registerEntity(EntityJellySlime.class, "JellySlime", 18, -2005303, -2208060);
        registerEntity(EntityCreamSlime.class, "CreamSlime", 19, -4176, -1827);
        registerEntity(EntityLemonDuck.class, "LemonDuck", 20, -2558710, -3804404);
        registerEntity(EntityStarfish.class, "SpaceStarfish", 21, -3433, -7883);
        registerEntity(EntityMelon.class, "Melon", 22, -26833, -11618715);
        registerEntity(EntityTomato.class, "Tomato", 23, -3538944, -3066352);
        registerEntity(EntityGrappy.class, "Grappy", 24, -4737025, -4144960);
        registerEntity(EntityCreamCat.class, "CreamCat", 25, -3655, -6004);
        registerEntity(EntityCreamGolem.class, "CreamGolem", 26, -3655, -6004);
        registerEntity(EntityStrawberryChicken.class, "StrawberryChicken", 27, -12545, -20225);
        registerEntity(EntityFronosVillager.class, "FronosVillager", 28, -5476528, -4144960);

        registerEntity(EntityFrozenSludgeling.class, "FrozenSludgeling", 29, -4996406, -2038040);

        registerEntity(EntitySiriusCreeper.class, "SiriusCreeper", 30, -4259841, 0);
        registerEntity(EntitySiriusBlaze.class, "SiriusBlaze", 31, -4390913, -6758433);
        registerEntity(EntitySiriusMagmaCube.class, "SiriusMagmaCube", 32, -12163225, -5769739);
        registerEntity(EntityEvolvedSiriusBlazeBoss.class, "EvolvedSiriusBlazeBoss", 33, -4390913, -892881);

        if (ConfigManagerMP.enableVenusPlanet)
        {
            registerEntity(EntityVenusianBlaze.class, CompatibilityUtilMP.is4SpaceVenusLoaded() ? "VenusianBlazeMP" : "VenusianBlaze", 34, -27809, -45282);
            registerEntity(EntityVenusianSlime.class, CompatibilityUtilMP.is4SpaceVenusLoaded() ? "VenusianSlimeMP" : "VenusianSlime", 35, -262144, -205056);
            registerEntity(EntityVenusianVillager.class, CompatibilityUtilMP.is4SpaceVenusLoaded() ? "VenusianVillagerMP" : "VenusianVillager", 36, -13875061, -4875400);
        }

        registerEntity(EntityEuropaSquid.class, "EuropaSquid", 37, -12425324, -6163201);
        registerEntity(EntityEuropaCrab.class, "EuropaCrab", 39, -11574413, -12167066);

        registerEntity(EntityEvolvedWitch.class, "EvolvedWitch", 40, 3407872, 5349438);
        registerEntity(EntityMilkCow.class, "MilkCow", 41, -13487566, -657931);

        registerEntity(EntityPlutoAlien.class, "PlutoAlien", 42, -6125457, -13380436);

        //registerEntity(EntityEvolvedDarkCreeper.class, "EvolvedDarkCreeper", 43, 0, 0);

        //TEMPLATE HOTFIX
        registerEntity(EntityFronosCreeperBossTemp.class, "FronosBossTemp", 38, 0, 0);
        registerEntity(EntityKapteynBCreeperBoss.class, "KapteynBCreeperBoss", 43, 0, 0);
        registerEntity(EntityPlutoCreeperBoss.class, "PlutoCreeperBoss", 44, 0, 0);
    }

    private static void registerNonMobEntities()
    {
        RegisterHelper.registerNonMobEntity(EntityFlagMP.class, "MPFlag", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityTier4Rocket.class, "Tier4Rocket", MorePlanetsCore.instance, false);
        RegisterHelper.registerNonMobEntity(EntityTier4RocketNoFlag.class, "Tier4RocketNoFlag", MorePlanetsCore.instance, false);
        RegisterHelper.registerNonMobEntity(EntityProjectileFronisiumTNT.class, "ProjectileFronisiumTNT", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityFronisiumTNT.class, "FronisiumTNT", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityLaserMP.class, "LaserMP", MorePlanetsCore.instance, true);

        RegisterHelper.registerNonMobEntity(EntityTier5Rocket.class, "Tier5Rocket", MorePlanetsCore.instance, false);
        RegisterHelper.registerNonMobEntity(EntityTier5RocketNoFlag.class, "Tier5RocketNoFlag", MorePlanetsCore.instance, false);
        RegisterHelper.registerNonMobEntity(EntityCheeseSpore.class, "CheeseSpore", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityPolongniusMeteorChunk.class, "PolongniusMeteorChunk", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityPolongniusMeteor.class, "PolongniusMeteor", MorePlanetsCore.instance, true);

        RegisterHelper.registerNonMobEntity(EntityTier6Rocket.class, "Tier6Rocket", MorePlanetsCore.instance, false);
        RegisterHelper.registerNonMobEntity(EntityTier6RocketNoFlag.class, "Tier6RocketNoFlag", MorePlanetsCore.instance, false);

        RegisterHelper.registerNonMobEntity(EntityKoentusMeteorChunk.class, "KoentusMeteorChunk", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityKoentusMeteor.class, "KoentusMeteor", MorePlanetsCore.instance, true);

        RegisterHelper.registerNonMobEntity(EntityVanillaCreamBall.class, "VanillaCreamBall", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityChocolateCreamBall.class, "ChocolateCreamBall", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityStrawberryCreamBall.class, "StrawberryCreamBall", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityOrangeCreamBall.class, "OrangeCreamBall", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityTeaCreamBall.class, "TeaCreamBall", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityLemonCreamBall.class, "LemonCreamBall", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityPoisonArrow.class, "PoisonArrow", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityTier7Rocket.class, "Tier7Rocket", MorePlanetsCore.instance, false);

        RegisterHelper.registerNonMobEntity(EntitySiriusSmallFireball.class, "SiriusFireball", MorePlanetsCore.instance, true);

        RegisterHelper.registerNonMobEntity(EntityUraniumBomb.class, "UraniumBomb", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityIceCrystalMeteor.class, "IceCrystalMeteor", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityTier8Rocket.class, "Tier8Rocket", MorePlanetsCore.instance, false);

        RegisterHelper.registerNonMobEntity(EntityEuropaWaterBomb.class, "EuropaWaterBomb", MorePlanetsCore.instance, true);

        RegisterHelper.registerNonMobEntity(EntityDarkAsteroid.class, "DarkAsteroid", MorePlanetsCore.instance, true);
        RegisterHelper.registerNonMobEntity(EntityIceCrystalMeteorChunk.class, "IceCrystalMeteorChunk", MorePlanetsCore.instance, true);
    }

    public static void registerEntity(Class<? extends Entity> entity, String name, int id, int backgroundEggColour, int foregroundEggColour)
    {
        id = id + 1000;
        EntityRegistry.registerModEntity(entity, name, id, MorePlanetsCore.instance, 80, 3, true);
        entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, backgroundEggColour, foregroundEggColour));
    }

    public static String getStringFromID(int id)
    {
        EntityRegistration regis = EntityRegistry.instance().lookupModSpawn(FMLCommonHandler.instance().findContainerFor(MorePlanetsCore.instance), id);

        if (regis != null)
        {
            return regis.getEntityName();
        }
        return null;
    }

    public static Entity createEntityByID(int id, World world)
    {
        Entity entity = null;

        try
        {
            EntityRegistration regis = EntityRegistry.instance().lookupModSpawn(FMLCommonHandler.instance().findContainerFor(MorePlanetsCore.instance), id);
            Class<?> clazz = regis.getEntityClass();

            if (clazz != null)
            {
                entity = (Entity)clazz.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (entity == null)
        {
            MPLog.error("Skipping Entity with id " + id);
        }
        return entity;
    }
}