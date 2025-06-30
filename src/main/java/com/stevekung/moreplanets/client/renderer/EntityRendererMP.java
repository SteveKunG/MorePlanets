package com.stevekung.moreplanets.client.renderer;

import net.minecraft.item.ItemStack;
import com.stevekung.moreplanets.client.renderer.entity.*;

import com.stevekung.moreplanets.entity.EntityAntiGravFallingBlock;
import com.stevekung.moreplanets.entity.EntityBlackHole;
import com.stevekung.moreplanets.entity.EntityBlackHoleStorage;
import com.stevekung.moreplanets.entity.projectile.EntityLaserBullet;
import com.stevekung.moreplanets.entity.projectile.EntitySpaceFishHook;
import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.moons.koentus.client.renderer.entity.RenderFallingKoentusMeteor;
import com.stevekung.moreplanets.moons.koentus.client.renderer.entity.RenderKoentusMeteor;
import com.stevekung.moreplanets.moons.koentus.entity.EntityFallingKoentusMeteor;
import com.stevekung.moreplanets.moons.koentus.entity.EntityKoentusMeteor;
import com.stevekung.moreplanets.planets.chalos.client.renderer.entity.*;

import com.stevekung.moreplanets.planets.chalos.entity.EntityCheeseCow;
import com.stevekung.moreplanets.planets.chalos.entity.EntityCheeseCubeEyeBoss;
import com.stevekung.moreplanets.planets.chalos.entity.EntityCheeseFloater;
import com.stevekung.moreplanets.planets.chalos.entity.EntityCheeseSlime;
import com.stevekung.moreplanets.planets.chalos.entity.projectile.EntityCheeseSpore;
import com.stevekung.moreplanets.planets.chalos.entity.projectile.EntitySmallCheeseSpore;
import com.stevekung.moreplanets.planets.diona.client.renderer.entity.*;
import com.stevekung.moreplanets.planets.diona.entity.*;

import com.stevekung.moreplanets.planets.diona.entity.projectile.EntityAntiGravityArrow;
import com.stevekung.moreplanets.planets.diona.entity.projectile.EntityInfectedPurloniteArrow;
import com.stevekung.moreplanets.planets.fronos.client.render.entity.RenderBearry;
import com.stevekung.moreplanets.planets.fronos.client.render.entity.RenderGiantBlueberry;
import com.stevekung.moreplanets.planets.fronos.client.render.entity.RenderJellySlime;
import com.stevekung.moreplanets.planets.fronos.client.render.entity.RenderMarshmallow;
import com.stevekung.moreplanets.planets.fronos.entity.EntityBearry;
import com.stevekung.moreplanets.planets.fronos.entity.EntityGiantBlueberry;
import com.stevekung.moreplanets.planets.fronos.entity.EntityJellySlime;
import com.stevekung.moreplanets.planets.fronos.entity.EntityMarshmallow;
import com.stevekung.moreplanets.planets.nibiru.client.renderer.entity.*;
import com.stevekung.moreplanets.planets.nibiru.entity.*;
import com.stevekung.moreplanets.planets.nibiru.entity.projectile.*;

import com.stevekung.moreplanets.planets.nibiru.entity.weather.EntityNibiruLightningBolt;
import com.stevekung.moreplanets.utils.client.renderer.entity.RenderSnowballMP;
import com.stevekung.lib.utils.client.ClientRegistryUtils;

public class EntityRendererMP
{
    public static void init()
    {
        ClientRegistryUtils.registerEntityRendering(EntityAlbetiusWorm.class, RenderAlbetiusWorm::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedPurloniteWorm.class, RenderInfectedPurloniteWorm::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedPurloniteSpider.class, RenderInfectedPurloniteSpider::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedPurloniteTentacle.class, RenderInfectedPurloniteTentacle::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedPurloniteSlimeBoss.class, RenderInfectedPurloniteSlimeBoss::new);
        ClientRegistryUtils.registerEntityRendering(EntityZeliusZombie.class, RenderZeliusZombie::new);
        ClientRegistryUtils.registerEntityRendering(EntityZeliusCreeper.class, RenderZeliusCreeper::new);
        ClientRegistryUtils.registerEntityRendering(EntityCheeseCubeEyeBoss.class, RenderCheeseCubeEyeBoss::new);
        ClientRegistryUtils.registerEntityRendering(EntityCheeseSpore.class, RenderCheeseSpore::new);
        ClientRegistryUtils.registerEntityRendering(EntitySmallCheeseSpore.class, RenderSmallCheeseSpore::new);
        ClientRegistryUtils.registerEntityRendering(EntityCheeseFloater.class, RenderCheeseFloater::new);
        ClientRegistryUtils.registerEntityRendering(EntityCheeseSlime.class, RenderCheeseSlime::new);
        ClientRegistryUtils.registerEntityRendering(EntityCheeseCow.class, RenderCheeseCow::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedPurloniteBomb.class, RenderInfectedPurloniteBomb::new);
        ClientRegistryUtils.registerEntityRendering(EntityGiantWorm.class, RenderGiantWorm::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedZombie.class, RenderInfectedZombie::new);
        ClientRegistryUtils.registerEntityRendering(EntityAlienMiner.class, RenderAlienMiner::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedPurloniteSlimeMinion.class, RenderInfectedPurloniteSlimeMinion::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedWorm.class, RenderInfectedWorm::new);
        ClientRegistryUtils.registerEntityRendering(EntityNibiruLightningBolt.class, RenderNibiruLightningBolt::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedSnowman.class, RenderInfectedSnowman::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedSnowball.class, manager -> new RenderSnowballMP(manager, new ItemStack(MPItems.INFECTED_SNOWBALL)));
        ClientRegistryUtils.registerEntityRendering(EntityPurifiedSnowball.class, manager -> new RenderSnowballMP(manager, new ItemStack(MPItems.PURIFIED_SNOWBALL)));
        ClientRegistryUtils.registerEntityRendering(EntityZeliusSkeleton.class, RenderZeliusSkeleton::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedGuardian.class, RenderInfectedGuardian::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedElderGuardian.class, RenderInfectedElderGuardian::new);
        ClientRegistryUtils.registerEntityRendering(EntityBlackHole.class, RenderBlackHole::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedSquid.class, RenderInfectedSquid::new);
        ClientRegistryUtils.registerEntityRendering(EntityDarkLightningBolt.class, RenderDarkLightningBolt::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedEgg.class, manager -> new RenderSnowballMP(manager, new ItemStack(MPItems.INFECTED_EGG)));
        ClientRegistryUtils.registerEntityRendering(EntityInfectedChicken.class, RenderInfectedChicken::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedPurloniteArrow.class, RenderInfectedPurloniteArrow::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedCow.class, RenderInfectedCow::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedArrow.class, RenderInfectedArrow::new);
        ClientRegistryUtils.registerEntityRendering(EntitySpaceFishHook.class, RenderSpaceFishHook::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedCaveSpider.class, RenderInfectedCaveSpider::new);
        ClientRegistryUtils.registerEntityRendering(EntityAlienBeam.class, RenderAlienBeam::new);
        ClientRegistryUtils.registerEntityRendering(EntityVeinEye.class, manager -> new RenderSnowballMP(manager, new ItemStack(MPItems.VEIN_EYE)));
        ClientRegistryUtils.registerEntityRendering(EntityShlime.class, RenderShlime::new);
        ClientRegistryUtils.registerEntityRendering(EntityLaserBullet.class, RenderLaserBullet::new);
        ClientRegistryUtils.registerEntityRendering(EntityNibiruVillager.class, RenderNibiruVillager::new);
        ClientRegistryUtils.registerEntityRendering(EntityZergius.class, RenderZergius::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedCreeper.class, RenderInfectedCreeper::new);
        ClientRegistryUtils.registerEntityRendering(EntityInfectedSkeleton.class, RenderInfectedSkeleton::new);
        ClientRegistryUtils.registerEntityRendering(EntityVeinFloater.class, RenderVeinFloater::new);
        ClientRegistryUtils.registerEntityRendering(EntityVeinFloaterMinion.class, RenderVeinFloaterMinion::new);
        ClientRegistryUtils.registerEntityRendering(EntityVeinBall.class, RenderVeinBall::new);
        ClientRegistryUtils.registerEntityRendering(EntityMiniVeinFloater.class, RenderMiniVeinFloater::new);
        ClientRegistryUtils.registerEntityRendering(EntityGiantBlueberry.class, RenderGiantBlueberry::new);
        ClientRegistryUtils.registerEntityRendering(EntityMarshmallow.class, RenderMarshmallow::new);
        ClientRegistryUtils.registerEntityRendering(EntityBearry.class, RenderBearry::new);
        ClientRegistryUtils.registerEntityRendering(EntityBlackHoleStorage.class, RenderBlackHoleStorage::new);
        ClientRegistryUtils.registerEntityRendering(EntityAntiGravityArrow.class, RenderAntiGravityArrow::new);
        ClientRegistryUtils.registerEntityRendering(EntityJellySlime.class, RenderJellySlime::new);
        ClientRegistryUtils.registerEntityRendering(EntityFallingKoentusMeteor.class, RenderFallingKoentusMeteor::new);
        ClientRegistryUtils.registerEntityRendering(EntityAntiGravFallingBlock.class, RenderAntiGravFallingBlock::new);
        ClientRegistryUtils.registerEntityRendering(EntityTerrastoneGolem.class, RenderTerrastoneGolem::new);
        ClientRegistryUtils.registerEntityRendering(EntityTerrasquid.class, RenderTerrasquid::new);
        ClientRegistryUtils.registerEntityRendering(EntityKoentusMeteor.class, RenderKoentusMeteor::new);
    }
}