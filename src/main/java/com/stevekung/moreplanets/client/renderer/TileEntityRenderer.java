package com.stevekung.moreplanets.client.renderer;

import com.stevekung.moreplanets.client.renderer.tileentity.*;

import com.stevekung.moreplanets.client.renderer.tileentity.*;
import com.stevekung.moreplanets.planets.chalos.tileentity.TileEntityChalosAncientChest;
import com.stevekung.moreplanets.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import com.stevekung.moreplanets.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import com.stevekung.moreplanets.planets.diona.client.renderer.tileentity.*;
import com.stevekung.moreplanets.planets.diona.tileentity.*;
import com.stevekung.moreplanets.planets.nibiru.client.renderer.tileentity.*;
import com.stevekung.moreplanets.planets.nibiru.tileentity.*;
import com.stevekung.moreplanets.tileentity.*;

import com.stevekung.moreplanets.planets.diona.client.renderer.tileentity.*;
import com.stevekung.moreplanets.planets.diona.tileentity.*;
import com.stevekung.moreplanets.planets.nibiru.client.renderer.tileentity.*;
import com.stevekung.moreplanets.planets.nibiru.tileentity.*;
import com.stevekung.moreplanets.tileentity.*;
import com.stevekung.moreplanets.utils.client.renderer.tileentity.TileEntityChestRendererMP;
import com.stevekung.moreplanets.utils.client.renderer.tileentity.TileEntityTreasureChestRendererMP;
import stevekung.mods.stevekunglib.utils.client.ClientRegistryUtils;

public class TileEntityRenderer
{
    public static void init()
    {
        ClientRegistryUtils.registerTileEntityRendering(TileEntitySpaceWarpPadFull.class, new TileEntitySpaceWarpPadFullRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityZeliusEgg.class, new TileEntityZeliusEggRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityDarkEnergyReceiver.class, new TileEntityDarkEnergyReceiverRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityInfectedPurloniteCrystal.class, new TileEntityInfectedPurloniteCrystalRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityInfectedPurloniteEnderCore.class, new TileEntityInfectedPurloniteEnderCoreRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityDionaTreasureChest.class, new TileEntityTreasureChestRendererMP("diona"));
        ClientRegistryUtils.registerTileEntityRendering(TileEntityDionaAncientChest.class, new TileEntityChestRendererMP("diona_ancient"));
        ClientRegistryUtils.registerTileEntityRendering(TileEntityChalosTreasureChest.class, new TileEntityTreasureChestRendererMP("chalos"));
        ClientRegistryUtils.registerTileEntityRendering(TileEntityChalosAncientChest.class, new TileEntityChestRendererMP("chalos_ancient"));
        ClientRegistryUtils.registerTileEntityRendering(TileEntityNibiruTreasureChest.class, new TileEntityTreasureChestRendererMP("nibiru"));
        ClientRegistryUtils.registerTileEntityRendering(TileEntityNibiruAncientChest.class, new TileEntityChestRendererMP("nibiru_ancient"));
        ClientRegistryUtils.registerTileEntityRendering(TileEntityCheeseSporeChest.class, new TileEntityChestRendererMP("cheese_spore"));
        ClientRegistryUtils.registerTileEntityRendering(TileEntityInfectedChest.class, new TileEntityChestRendererMP("infected"));
        ClientRegistryUtils.registerTileEntityRendering(TileEntityAlienBerryChest.class, new TileEntityChestRendererMP("alien_berry"));
        ClientRegistryUtils.registerTileEntityRendering(TileEntityMultalicCrystal.class, new TileEntityMultalicCrystalRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityDarkEnergyCore.class, new TileEntityDarkEnergyCoreRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityJuicerEgg.class, new TileEntityJuicerEggRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityNuclearWasteTank.class, new TileEntityNuclearWasteTankRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityVeinFrame.class, new TileEntityVeinFrameRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityVeinPortal.class, new TileEntityVeinPortalRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityDarkEnergyGenerator.class, new TileEntityDarkEnergyGeneratorRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityCrashedAlienProbe.class, new TileEntityCrashedAlienProbeRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityBlackHoleStorage.class, new TileEntityBlackHoleStorageRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityAlienDefenderBeacon.class, new TileEntityAlienDefenderBeaconRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityShieldGenerator.class, new TileEntityShieldGeneratorRenderer());
        ClientRegistryUtils.registerTileEntityRendering(TileEntityAlienChest.class, new TileEntityChestRendererMP("alien"));
    }
}