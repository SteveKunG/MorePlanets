/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.renderer;

import cpw.mods.fml.client.registry.RenderingRegistry;
import micdoodle8.mods.galacticraft.core.client.render.entities.RenderTier1Rocket;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import stevekung.mods.moreplanets.asteroids.darkasteroids.client.render.entities.RenderDarkAsteroid;
import stevekung.mods.moreplanets.asteroids.darkasteroids.entities.EntityDarkAsteroid;
import stevekung.mods.moreplanets.core.entities.EntityEvolvedWitch;
import stevekung.mods.moreplanets.core.entities.models.ModelRocketMP;
import stevekung.mods.moreplanets.core.entities.models.ModelRocketNoFlagMP;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaCrab;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaSquid;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaWaterBomb;
import stevekung.mods.moreplanets.moons.europa.render.entities.RenderEuropaCrab;
import stevekung.mods.moreplanets.moons.europa.render.entities.RenderEuropaSquid;
import stevekung.mods.moreplanets.moons.europa.render.entities.RenderEuropaWaterBomb;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteor;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteorChunk;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusSludgeling;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusianVillager;
import stevekung.mods.moreplanets.moons.koentus.render.entities.RenderKoentusMeteor;
import stevekung.mods.moreplanets.moons.koentus.render.entities.RenderKoentusMeteorChunk;
import stevekung.mods.moreplanets.moons.koentus.render.entities.RenderKoentusSludgeling;
import stevekung.mods.moreplanets.moons.koentus.render.entities.RenderKoentusianVillager;
import stevekung.mods.moreplanets.planets.diona.entities.*;
import stevekung.mods.moreplanets.planets.diona.entities.models.ModelSpaceWolf;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityLaserMP;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityProjectileFronisiumTNT;
import stevekung.mods.moreplanets.planets.diona.render.entities.*;
import stevekung.mods.moreplanets.planets.diona.render.entities.projectiles.RenderLaser;
import stevekung.mods.moreplanets.planets.diona.render.entities.projectiles.RenderProjectileFronisiumTNT;
import stevekung.mods.moreplanets.planets.fronos.entities.*;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.*;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelCreamCat;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelGrappy1;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelGrappy2;
import stevekung.mods.moreplanets.planets.fronos.render.entities.*;
import stevekung.mods.moreplanets.planets.kapteynb.entities.*;
import stevekung.mods.moreplanets.planets.kapteynb.render.entities.*;
import stevekung.mods.moreplanets.planets.nibiru.entities.*;
import stevekung.mods.moreplanets.planets.nibiru.render.entities.RenderGiantWorm;
import stevekung.mods.moreplanets.planets.nibiru.render.entities.RenderInfectedEvolvedSpiderBoss;
import stevekung.mods.moreplanets.planets.nibiru.render.entities.RenderInfectedWorm;
import stevekung.mods.moreplanets.planets.nibiru.render.entities.RenderInfectedZombie;
import stevekung.mods.moreplanets.planets.pluto.entities.EntityPlutoAlien;
import stevekung.mods.moreplanets.planets.pluto.render.entities.RenderPlutoAlien;
import stevekung.mods.moreplanets.planets.polongnius.entities.*;
import stevekung.mods.moreplanets.planets.polongnius.entities.projectiles.EntityCheeseSpore;
import stevekung.mods.moreplanets.planets.polongnius.render.entities.*;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntityEvolvedSiriusBlazeBoss;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusCreeper;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.entities.projectiles.EntitySiriusSmallFireball;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.RenderEvolvedSiriusBlazeBoss;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.RenderSiriusBlaze;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.RenderSiriusCreeper;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.RenderSiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.projectiles.RenderSiriusSmallFireball;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianBlaze;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianSlime;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianVillager;
import stevekung.mods.moreplanets.planets.venus.render.entities.RenderVenusianBlaze;
import stevekung.mods.moreplanets.planets.venus.render.entities.RenderVenusianSlime;
import stevekung.mods.moreplanets.planets.venus.render.entities.RenderVenusianVillager;

public class EntityRendererMP
{
    private static IModelCustom tier3RocketModel = AdvancedModelLoader.loadModel(new ResourceLocation("galacticraftasteroids:models/tier3rocket.obj"));

    public static void init()
    {
        EntityRendererMP.registerEntityRenderers();
        EntityRendererMP.registerNonEntityRenderers();
    }

    private static void registerEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedWitch.class, new RenderEvolvedWitch());
        RenderingRegistry.registerEntityRenderingHandler(EntityDustSludgeling.class, new RenderDustSludgeling());
        RenderingRegistry.registerEntityRenderingHandler(EntitySpaceWolf.class, new RenderSpaceWolf(new ModelSpaceWolf(), new ModelSpaceWolf()));
        RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedEnderman.class, new RenderEvolvedEnderman());
        RenderingRegistry.registerEntityRenderingHandler(EntityDionaCreeperBoss.class, new RenderDionaCreeperBoss());
        RenderingRegistry.registerEntityRenderingHandler(EntityDionaMinionCreeper.class, new RenderDionaMinionCreeper());

        RenderingRegistry.registerEntityRenderingHandler(EntityCheeseCow.class, new RenderCheeseCow());
        RenderingRegistry.registerEntityRenderingHandler(EntityCheeseSlime.class, new RenderCheeseSlime(new ModelSlime(16), new ModelSlime(0)));
        RenderingRegistry.registerEntityRenderingHandler(EntityCheeseCubeEyeBoss.class, new RenderCheeseCubeBoss());

        RenderingRegistry.registerEntityRenderingHandler(EntityInfectedWorm.class, new RenderInfectedWorm());
        RenderingRegistry.registerEntityRenderingHandler(EntityGiantWorm.class, new RenderGiantWorm());
        RenderingRegistry.registerEntityRenderingHandler(EntityInfectedZombie.class, new RenderInfectedZombie());
        RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedInfectedSpiderBoss.class, new RenderInfectedEvolvedSpiderBoss());

        RenderingRegistry.registerEntityRenderingHandler(EntityKoentusianVillager.class, new RenderKoentusianVillager());
        RenderingRegistry.registerEntityRenderingHandler(EntityKoentusSludgeling.class, new RenderKoentusSludgeling());

        RenderingRegistry.registerEntityRenderingHandler(EntityBearry.class, new RenderBearry());
        RenderingRegistry.registerEntityRenderingHandler(EntityBerry.class, new RenderBerry());
        RenderingRegistry.registerEntityRenderingHandler(EntityMarshmallow.class, new RenderMarshmallow());
        RenderingRegistry.registerEntityRenderingHandler(EntityKiwi.class, new RenderKiwi());
        RenderingRegistry.registerEntityRenderingHandler(EntityJellySlime.class, new RenderJellySlime(new ModelSlime(16), new ModelSlime(0)));
        RenderingRegistry.registerEntityRenderingHandler(EntityCreamSlime.class, new RenderCreamSlime(new ModelSlime(16), new ModelSlime(0)));
        RenderingRegistry.registerEntityRenderingHandler(EntityLemonDuck.class, new RenderLemonDuck());
        RenderingRegistry.registerEntityRenderingHandler(EntityStarfish.class, new RenderStarfish());
        RenderingRegistry.registerEntityRenderingHandler(EntityMelon.class, new RenderMelon());
        RenderingRegistry.registerEntityRenderingHandler(EntityTomato.class, new RenderTomato());
        RenderingRegistry.registerEntityRenderingHandler(EntityGrappy.class, new RenderGrappy(new ModelGrappy2(), new ModelGrappy1()));
        RenderingRegistry.registerEntityRenderingHandler(EntityCreamCat.class, new RenderCreamCat(new ModelCreamCat()));
        RenderingRegistry.registerEntityRenderingHandler(EntityCreamGolem.class, new RenderCreamGolem());
        RenderingRegistry.registerEntityRenderingHandler(EntityStrawberryChicken.class, new RenderStrawberryChicken());
        RenderingRegistry.registerEntityRenderingHandler(EntityFronosVillager.class, new RenderFronosVillager());

        RenderingRegistry.registerEntityRenderingHandler(EntityFrozenSludgeling.class, new RenderFrozenSludgeling());

        RenderingRegistry.registerEntityRenderingHandler(EntitySiriusCreeper.class, new RenderSiriusCreeper());
        RenderingRegistry.registerEntityRenderingHandler(EntitySiriusBlaze.class, new RenderSiriusBlaze());
        RenderingRegistry.registerEntityRenderingHandler(EntitySiriusMagmaCube.class, new RenderSiriusMagmaCube());
        RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedSiriusBlazeBoss.class, new RenderEvolvedSiriusBlazeBoss());

        RenderingRegistry.registerEntityRenderingHandler(EntityVenusianBlaze.class, new RenderVenusianBlaze());
        RenderingRegistry.registerEntityRenderingHandler(EntityVenusianSlime.class, new RenderVenusianSlime(new ModelSlime(16), new ModelSlime(0)));
        RenderingRegistry.registerEntityRenderingHandler(EntityVenusianVillager.class, new RenderVenusianVillager());

        RenderingRegistry.registerEntityRenderingHandler(EntityEuropaSquid.class, new RenderEuropaSquid());
        RenderingRegistry.registerEntityRenderingHandler(EntityEuropaWaterBomb.class, new RenderEuropaWaterBomb());
        RenderingRegistry.registerEntityRenderingHandler(EntityEuropaCrab.class, new RenderEuropaCrab());

        RenderingRegistry.registerEntityRenderingHandler(EntityMilkCow.class, new RenderMilkCow());
        RenderingRegistry.registerEntityRenderingHandler(EntityPlutoAlien.class, new RenderPlutoAlien());

        //TEMPLATE HOTFIX
        RenderingRegistry.registerEntityRenderingHandler(EntityFronosCreeperBossTemp.class, new RenderFronosCreeperBossTemp());
    }

    private static void registerNonEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityFlagMP.class, new RenderFlagMP());
        RenderingRegistry.registerEntityRenderingHandler(EntityTier4Rocket.class, new RenderTier1Rocket(new ModelRocketMP(), "diona", "tier4_rocket"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTier4RocketNoFlag.class, new RenderTier1Rocket(new ModelRocketNoFlagMP(), "diona", "tier4_rocket"));
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileFronisiumTNT.class, new RenderProjectileFronisiumTNT());
        RenderingRegistry.registerEntityRenderingHandler(EntityFronisiumTNT.class, new RenderFronisiumTNT());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserMP.class, new RenderLaser());

        RenderingRegistry.registerEntityRenderingHandler(EntityTier5Rocket.class, new RenderTier1Rocket(new ModelRocketMP(), "polongnius", "tier5_rocket"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTier5RocketNoFlag.class, new RenderTier1Rocket(new ModelRocketNoFlagMP(), "polongnius", "tier5_rocket"));
        RenderingRegistry.registerEntityRenderingHandler(EntityCheeseSpore.class, new RenderCheeseSpore(2.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityPolongniusMeteorChunk.class, new RenderPolongniusMeteorChunk());
        RenderingRegistry.registerEntityRenderingHandler(EntityPolongniusMeteor.class, new RenderPolongniusMeteor());

        RenderingRegistry.registerEntityRenderingHandler(EntityTier6Rocket.class, new RenderTier1Rocket(new ModelRocketMP(), "nibiru", "tier6_rocket"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTier6RocketNoFlag.class, new RenderTier1Rocket(new ModelRocketNoFlagMP(), "nibiru", "tier6_rocket"));

        RenderingRegistry.registerEntityRenderingHandler(EntityKoentusMeteorChunk.class, new RenderKoentusMeteorChunk());
        RenderingRegistry.registerEntityRenderingHandler(EntityKoentusMeteor.class, new RenderKoentusMeteor());

        RenderingRegistry.registerEntityRenderingHandler(EntityVanillaCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 0));
        RenderingRegistry.registerEntityRenderingHandler(EntityChocolateCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 1));
        RenderingRegistry.registerEntityRenderingHandler(EntityStrawberryCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 2));
        RenderingRegistry.registerEntityRenderingHandler(EntityOrangeCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 3));
        RenderingRegistry.registerEntityRenderingHandler(EntityTeaCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 4));
        RenderingRegistry.registerEntityRenderingHandler(EntityLemonCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 5));
        RenderingRegistry.registerEntityRenderingHandler(EntityPoisonArrow.class, new RenderPoisonArrow());
        RenderingRegistry.registerEntityRenderingHandler(EntityTier7Rocket.class, new RenderTier7Rocket(EntityRendererMP.tier3RocketModel, "fronos", "tier7_rocket"));

        RenderingRegistry.registerEntityRenderingHandler(EntityUraniumBomb.class, new RenderUraniumBomb());
        RenderingRegistry.registerEntityRenderingHandler(EntityIceCrystalMeteor.class, new RenderIceCrystalMeteor());
        RenderingRegistry.registerEntityRenderingHandler(EntityTier8Rocket.class, new RenderTier8Rocket(EntityRendererMP.tier3RocketModel, "kapteynb", "tier8_rocket"));

        RenderingRegistry.registerEntityRenderingHandler(EntitySiriusSmallFireball.class, new RenderSiriusSmallFireball());

        RenderingRegistry.registerEntityRenderingHandler(EntityDarkAsteroid.class, new RenderDarkAsteroid());
        RenderingRegistry.registerEntityRenderingHandler(EntityIceCrystalMeteorChunk.class, new RenderIceCrystalMeteorChunk());
    }
}