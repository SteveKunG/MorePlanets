/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.renderer;

import cpw.mods.fml.client.registry.ClientRegistry;
import stevekung.mods.moreplanets.moons.koentus.render.tileentities.TileEntityKoentusAncientChestRenderer;
import stevekung.mods.moreplanets.moons.koentus.render.tileentities.TileEntityKoentusTreasureChestRenderer;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityKoentusAncientChest;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityKoentusTreasureChest;
import stevekung.mods.moreplanets.planets.diona.render.tileentities.TileEntityDionaAncientChestRenderer;
import stevekung.mods.moreplanets.planets.diona.render.tileentities.TileEntityDionaTreasureChestRenderer;
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaAncientChest;
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaTreasureChest;
import stevekung.mods.moreplanets.planets.fronos.render.tileentities.*;
import stevekung.mods.moreplanets.planets.fronos.tileentities.*;
import stevekung.mods.moreplanets.planets.kapteynb.render.tileentities.TileEntityIcyPoisonCrystalRenderer;
import stevekung.mods.moreplanets.planets.kapteynb.render.tileentities.TileEntityKapteynBAncientChestRenderer;
import stevekung.mods.moreplanets.planets.kapteynb.render.tileentities.TileEntityKapteynBAncientChestTempRenderer;
import stevekung.mods.moreplanets.planets.kapteynb.render.tileentities.TileEntityKapteynBTreasureChestRenderer;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityIcyPoisonCrystal;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityKapteynBAncientChest;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityKapteynBAncientChestTemp;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityKapteynBTreasureChest;
import stevekung.mods.moreplanets.planets.mercury.render.tileentities.TileEntityMercuryAncientChestRenderer;
import stevekung.mods.moreplanets.planets.mercury.render.tileentities.TileEntityMercuryTreasureChestRenderer;
import stevekung.mods.moreplanets.planets.mercury.tileentities.TileEntityMercuryAncientChest;
import stevekung.mods.moreplanets.planets.mercury.tileentities.TileEntityMercuryTreasureChest;
import stevekung.mods.moreplanets.planets.nibiru.render.tileentities.TileEntityNibiruAncientChestRenderer;
import stevekung.mods.moreplanets.planets.nibiru.render.tileentities.TileEntityNibiruTreasureChestRenderer;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityNibiruAncientChest;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityNibiruTreasureChest;
import stevekung.mods.moreplanets.planets.pluto.render.tileentities.TileEntityPlutoAncientChestRenderer;
import stevekung.mods.moreplanets.planets.pluto.render.tileentities.TileEntityPlutoTreasureChestRenderer;
import stevekung.mods.moreplanets.planets.pluto.tileentities.TileEntityPlutoAncientChest;
import stevekung.mods.moreplanets.planets.pluto.tileentities.TileEntityPlutoTreasureChest;
import stevekung.mods.moreplanets.planets.polongnius.render.tileentities.TileEntityPolongniusAncientChestRenderer;
import stevekung.mods.moreplanets.planets.polongnius.render.tileentities.TileEntityPolongniusTreasureChestRenderer;
import stevekung.mods.moreplanets.planets.polongnius.render.tileentities.TileEntityUltraVioletSolarPanelRenderer;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityPolongniusAncientChest;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityPolongniusTreasureChest;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityUltraVioletSolarPanel;
import stevekung.mods.moreplanets.planets.siriusb.render.tileentities.TileEntitySiriusBAncientChestRenderer;
import stevekung.mods.moreplanets.planets.siriusb.render.tileentities.TileEntitySiriusBTreasureChestRenderer;
import stevekung.mods.moreplanets.planets.siriusb.tileentities.TileEntitySiriusBAncientChest;
import stevekung.mods.moreplanets.planets.siriusb.tileentities.TileEntitySiriusBTreasureChest;
import stevekung.mods.moreplanets.planets.venus.render.tileentities.TileEntityVenusAncientChestRenderer;
import stevekung.mods.moreplanets.planets.venus.render.tileentities.TileEntityVenusTreasureChestRenderer;
import stevekung.mods.moreplanets.planets.venus.tileentities.TileEntityVenusAncientChest;
import stevekung.mods.moreplanets.planets.venus.tileentities.TileEntityVenusTreasureChest;

public class TileEntityRendererMP
{
    public static void registerTileEntityRenderers()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDionaTreasureChest.class, new TileEntityDionaTreasureChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPolongniusTreasureChest.class, new TileEntityPolongniusTreasureChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNibiruTreasureChest.class, new TileEntityNibiruTreasureChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKoentusTreasureChest.class, new TileEntityKoentusTreasureChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFronosTreasureChest.class, new TileEntityFronosTreasureChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKapteynBTreasureChest.class, new TileEntityKapteynBTreasureChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySiriusBTreasureChest.class, new TileEntitySiriusBTreasureChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMercuryTreasureChest.class, new TileEntityMercuryTreasureChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVenusTreasureChest.class, new TileEntityVenusTreasureChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlutoTreasureChest.class, new TileEntityPlutoTreasureChestRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDionaAncientChest.class, new TileEntityDionaAncientChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPolongniusAncientChest.class, new TileEntityPolongniusAncientChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNibiruAncientChest.class, new TileEntityNibiruAncientChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKoentusAncientChest.class, new TileEntityKoentusAncientChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFronosAncientChest.class, new TileEntityFronosAncientChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKapteynBAncientChest.class, new TileEntityKapteynBAncientChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKapteynBAncientChestTemp.class, new TileEntityKapteynBAncientChestTempRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySiriusBAncientChest.class, new TileEntitySiriusBAncientChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMercuryAncientChest.class, new TileEntityMercuryAncientChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVenusAncientChest.class, new TileEntityVenusAncientChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlutoAncientChest.class, new TileEntityPlutoAncientChestRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUltraVioletSolarPanel.class, new TileEntityUltraVioletSolarPanelRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpaceOysterOpen.class, new TileEntitySpaceOysterOpenRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpaceOysterClose.class, new TileEntitySpaceOysterCloseRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCavernOysterOpen.class, new TileEntityCavernOysterOpenRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCavernOysterClose.class, new TileEntityCavernOysterCloseRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCup.class, new TileEntityCupRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMineralWaterCup.class, new TileEntityMineralWaterCupRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOvantineCup.class, new TileEntityOvantineCupRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoconutMilkCup.class, new TileEntityCoconutMilkCupRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCheeseOfMilkCup.class, new TileEntityCheeseOfMilkCupRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTeaCup.class, new TileEntityTeaCupRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCaramelCup.class, new TileEntityCaramelCupRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIcyPoisonCrystal.class, new TileEntityIcyPoisonCrystalRenderer());
    }
}