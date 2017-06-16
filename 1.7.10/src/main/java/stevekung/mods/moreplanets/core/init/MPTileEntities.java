/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import cpw.mods.fml.common.registry.GameRegistry;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityEledosEgg;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityKoentusAncientChest;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityKoentusDungeonSpawner;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityKoentusTreasureChest;
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaAncientChest;
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaDungeonSpawner;
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaTreasureChest;
import stevekung.mods.moreplanets.planets.fronos.tileentities.*;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.*;
import stevekung.mods.moreplanets.planets.mercury.tileentities.TileEntityMercuryAncientChest;
import stevekung.mods.moreplanets.planets.mercury.tileentities.TileEntityMercuryDungeonSpawner;
import stevekung.mods.moreplanets.planets.mercury.tileentities.TileEntityMercuryTreasureChest;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityNibiruAncientChest;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityNibiruDungeonSpawner;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityNibiruTreasureChest;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityPowerCrystalGenerator;
import stevekung.mods.moreplanets.planets.pluto.tileentities.TileEntityPlutoAncientChest;
import stevekung.mods.moreplanets.planets.pluto.tileentities.TileEntityPlutoDungeonSpawner;
import stevekung.mods.moreplanets.planets.pluto.tileentities.TileEntityPlutoTreasureChest;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.*;
import stevekung.mods.moreplanets.planets.siriusb.tileentities.TileEntitySiriusBAncientChest;
import stevekung.mods.moreplanets.planets.siriusb.tileentities.TileEntitySiriusBDungeonSpawner;
import stevekung.mods.moreplanets.planets.siriusb.tileentities.TileEntitySiriusBTreasureChest;
import stevekung.mods.moreplanets.planets.venus.tileentities.TileEntityVenusAncientChest;
import stevekung.mods.moreplanets.planets.venus.tileentities.TileEntityVenusDungeonSpawner;
import stevekung.mods.moreplanets.planets.venus.tileentities.TileEntityVenusTreasureChest;

public class MPTileEntities
{
    public static void init()
    {
        MPTileEntities.registerTileEntities();
    }

    private static void registerTileEntities()
    {
        // Diona
        GameRegistry.registerTileEntity(TileEntityDionaTreasureChest.class, "DionaTreasureChest");
        GameRegistry.registerTileEntity(TileEntityDionaDungeonSpawner.class, "DionaDungeonSpawner");
        GameRegistry.registerTileEntity(TileEntityDionaAncientChest.class, "DionaAncientChest");

        // Polongnius
        GameRegistry.registerTileEntity(TileEntityPolongniusTreasureChest.class, "PolongniusTreasureChest");
        GameRegistry.registerTileEntity(TileEntityUltraVioletSolarPanel.class, "UltraVioletSolarPanel");
        GameRegistry.registerTileEntity(TileEntityPolongniusAncientChest.class, "PolongniusAncientChest");
        GameRegistry.registerTileEntity(TileEntityPolongniusDungeonSpawner.class, "PolongniusDungeonSpawner");
        GameRegistry.registerTileEntity(TileEntityUltraVioletFake.class, "UltraVioletSolarFake");

        // Nibiru
        GameRegistry.registerTileEntity(TileEntityNibiruTreasureChest.class, "NibiruTreasureChest");
        GameRegistry.registerTileEntity(TileEntityNibiruAncientChest.class, "NibiruAncientChest");
        GameRegistry.registerTileEntity(TileEntityPowerCrystalGenerator.class, "PowerCrystalGenerator");
        GameRegistry.registerTileEntity(TileEntityNibiruDungeonSpawner.class, "NibiruDungeonSpawner");

        // Koentus
        GameRegistry.registerTileEntity(TileEntityKoentusTreasureChest.class, "KoentusTreasureChest");
        GameRegistry.registerTileEntity(TileEntityEledosEgg.class, "EledosEgg");
        GameRegistry.registerTileEntity(TileEntityKoentusAncientChest.class, "KoentusAncientChest");
        GameRegistry.registerTileEntity(TileEntityKoentusDungeonSpawner.class, "KoentusDungeonSpawner");

        // Fronos
        GameRegistry.registerTileEntity(TileEntitySpaceOysterOpen.class, "SpaceOysterOpen");
        GameRegistry.registerTileEntity(TileEntitySpaceOysterClose.class, "SpaceOysterClose");
        GameRegistry.registerTileEntity(TileEntityCavernOysterOpen.class, "CavernOysterOpen");
        GameRegistry.registerTileEntity(TileEntityCavernOysterClose.class, "CavernOysterClose");
        GameRegistry.registerTileEntity(TileEntityFronosTreasureChest.class, "FronosTreasureChest");
        GameRegistry.registerTileEntity(TileEntityCandyExtractor.class, "CandyExtractor");
        GameRegistry.registerTileEntity(TileEntityFronosAncientChest.class, "FronosAncientChest");
        GameRegistry.registerTileEntity(TileEntityFronosDungeonSpawner.class, "FronosDungeonSpawner");
        GameRegistry.registerTileEntity(TileEntityMineralWaterGenerator.class, "MineralWaterGenerator");
        GameRegistry.registerTileEntity(TileEntityCup.class, "Cup");
        GameRegistry.registerTileEntity(TileEntityMineralWaterCup.class, "MineralWaterCup");
        GameRegistry.registerTileEntity(TileEntityOvantineCup.class, "OvantineCup");
        GameRegistry.registerTileEntity(TileEntityCoconutMilkCup.class, "CoconutMilkCup");
        GameRegistry.registerTileEntity(TileEntityCheeseOfMilkCup.class, "CheeseOfMilkCup");
        GameRegistry.registerTileEntity(TileEntityTeaCup.class, "TeaCup");
        GameRegistry.registerTileEntity(TileEntityCaramelCup.class, "CaramelCup");

        // Kapteyn B
        GameRegistry.registerTileEntity(TileEntityKapteynBTreasureChest.class, "KapteynBTreasureChest");
        GameRegistry.registerTileEntity(TileEntityKapteynBAncientChest.class, "KapteynBAncientChest");
        GameRegistry.registerTileEntity(TileEntityKapteynBAncientChestTemp.class, "KapteynBAncientChestTemp");
        GameRegistry.registerTileEntity(TileEntityKapteynBDungeonSpawner.class, "KapteynBDungeonSpawner");
        GameRegistry.registerTileEntity(TileEntityUraniumWaste.class, "UraniumWaste");
        GameRegistry.registerTileEntity(TileEntityIcyPoisonCrystal.class, "IcyPoisonCrystal");
        GameRegistry.registerTileEntity(TileEntityFrozenWaterGeyser.class, "FrozenWaterGeyser");
        GameRegistry.registerTileEntity(TileEntityIceCrystalMeteor.class, "IceCrystalMeteor");

        // Sirius B
        GameRegistry.registerTileEntity(TileEntitySiriusBTreasureChest.class, "SiriusBTreasureChest");
        GameRegistry.registerTileEntity(TileEntitySiriusBAncientChest.class, "SiriusBAncientChest");
        GameRegistry.registerTileEntity(TileEntitySiriusBDungeonSpawner.class, "SiriusBDungeonSpawner");

        // Mercury
        GameRegistry.registerTileEntity(TileEntityMercuryDungeonSpawner.class, "MercuryDungeonSpawner");
        GameRegistry.registerTileEntity(TileEntityMercuryAncientChest.class, "MercuryAncientChest");
        GameRegistry.registerTileEntity(TileEntityMercuryTreasureChest.class, "MercuryTreasureChest");

        // Venus
        GameRegistry.registerTileEntity(TileEntityVenusDungeonSpawner.class, "VenusDungeonSpawner");
        GameRegistry.registerTileEntity(TileEntityVenusAncientChest.class, "VenusAncientChest");
        GameRegistry.registerTileEntity(TileEntityVenusTreasureChest.class, "VenusTreasureChest");

        // Pluto
        GameRegistry.registerTileEntity(TileEntityPlutoDungeonSpawner.class, "PlutoDungeonSpawner");
        GameRegistry.registerTileEntity(TileEntityPlutoAncientChest.class, "PlutoAncientChest");
        GameRegistry.registerTileEntity(TileEntityPlutoTreasureChest.class, "PlutoTreasureChest");
    }
}