package stevekung.mods.moreplanets.client.renderer;

import stevekung.mods.moreplanets.client.renderer.tileentity.TileEntityAlienDefenderBeaconRenderer;
import stevekung.mods.moreplanets.client.renderer.tileentity.TileEntityBlackHoleStorageRenderer;
import stevekung.mods.moreplanets.client.renderer.tileentity.TileEntityDarkEnergyReceiverRenderer;
import stevekung.mods.moreplanets.client.renderer.tileentity.TileEntitySpaceWarpPadFullRenderer;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosAncientChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity.*;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.*;
import stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity.*;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.*;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPadFull;
import stevekung.mods.moreplanets.util.EnumChestTexture;
import stevekung.mods.moreplanets.util.client.renderer.tileentity.TileEntityAncientChestRendererMP;
import stevekung.mods.moreplanets.util.client.renderer.tileentity.TileEntityChestRendererMP;
import stevekung.mods.moreplanets.util.client.renderer.tileentity.TileEntityTreasureChestRendererMP;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class TileEntityRenderer
{
    public static void init()
    {
        ClientRegisterHelper.registerTileEntityRendering(TileEntitySpaceWarpPadFull.class, new TileEntitySpaceWarpPadFullRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityZeliusEgg.class, new TileEntityZeliusEggRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityDarkEnergyReceiver.class, new TileEntityDarkEnergyReceiverRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityLargeInfectedCrystallize.class, new TileEntityLargeInfectedCrystallizeRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityInfectedCrystallizeEnderCore.class, new TileEntityInfectedCrystallizeEnderCoreRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityDionaTreasureChest.class, new TileEntityTreasureChestRendererMP(EnumChestTexture.DIONA));
        ClientRegisterHelper.registerTileEntityRendering(TileEntityDionaAncientChest.class, new TileEntityAncientChestRendererMP(EnumChestTexture.DIONA));
        ClientRegisterHelper.registerTileEntityRendering(TileEntityChalosTreasureChest.class, new TileEntityTreasureChestRendererMP(EnumChestTexture.CHALOS));
        ClientRegisterHelper.registerTileEntityRendering(TileEntityChalosAncientChest.class, new TileEntityAncientChestRendererMP(EnumChestTexture.CHALOS));
        ClientRegisterHelper.registerTileEntityRendering(TileEntityNibiruTreasureChest.class, new TileEntityTreasureChestRendererMP(EnumChestTexture.NIBIRU));
        ClientRegisterHelper.registerTileEntityRendering(TileEntityNibiruAncientChest.class, new TileEntityAncientChestRendererMP(EnumChestTexture.NIBIRU));
        ClientRegisterHelper.registerTileEntityRendering(TileEntityCheeseSporeChest.class, new TileEntityChestRendererMP("cheese_spore"));
        ClientRegisterHelper.registerTileEntityRendering(TileEntityInfectedChest.class, new TileEntityChestRendererMP("infected"));
        ClientRegisterHelper.registerTileEntityRendering(TileEntityAlienBerryChest.class, new TileEntityChestRendererMP("alien_berry"));
        ClientRegisterHelper.registerTileEntityRendering(TileEntityMultalicCrystal.class, new TileEntityMultalicCrystalRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityDarkEnergyCore.class, new TileEntityDarkEnergyCoreRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityJuicerEgg.class, new TileEntityJuicerEggRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityNuclearWasteTank.class, new TileEntityNuclearWasteTankRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityVeinFrame.class, new TileEntityVeinFrameRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityVeinPortal.class, new TileEntityVeinPortalRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityDarkEnergyGenerator.class, new TileEntityDarkEnergyGeneratorRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityCrashedAlienProbe.class, new TileEntityCrashedAlienProbeRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityBlackHoleStorage.class, new TileEntityBlackHoleStorageRenderer());
        ClientRegisterHelper.registerTileEntityRendering(TileEntityAlienDefenderBeacon.class, new TileEntityAlienDefenderBeaconRenderer());
    }
}