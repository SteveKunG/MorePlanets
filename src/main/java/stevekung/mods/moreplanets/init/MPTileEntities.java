package stevekung.mods.moreplanets.init;

import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.moons.koentus.tileentity.TileEntityGravityExtractor;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosAncientChest;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosDungeonSpawner;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import stevekung.mods.moreplanets.planets.diona.tileentity.*;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.*;
import stevekung.mods.moreplanets.tileentity.*;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityEnergyStorageClusterMP;

public class MPTileEntities
{
    public static void init()
    {
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntitySpaceWarpPad.class, "space_warp_pad");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntitySpaceWarpPadFull.class, "space_warp_pad_full");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityDummy.class, "dummy");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityDarkEnergyReceiver.class, "dark_energy_receiver");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityEnergyStorageClusterMP.class, "energy_storage_cluster");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityBlackHoleStorage.class, "black_hole_storage");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityAlienDefenderBeacon.class, "alien_defender_beacon");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityShieldGenerator.class, "shield_generator");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityAlienChest.class, "alien_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityDionaAncientChest.class, "diona_ancient_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityDionaTreasureChest.class, "diona_treasure_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityDionaDungeonSpawner.class, "diona_dungeon_spawner");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityZeliusEgg.class, "zelius_egg");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityInfectedCrystallizedEnderCore.class, "infected_crystallized_ender_core");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityInfectedCrystallizedCrystal.class, "infected_crystallized_crystal");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityDarkEnergyCore.class, "dark_energy_core");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityDarkEnergyGenerator.class, "dark_energy_generator");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityCrashedAlienProbe.class, "crashed_alien_probe");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityChalosAncientChest.class, "chalos_ancient_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityChalosTreasureChest.class, "chalos_treasure_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityChalosDungeonSpawner.class, "chalos_dungeon_spawner");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityCheeseSporeChest.class, "cheese_spore_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityNibiruAncientChest.class, "nibiru_ancient_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityNibiruTreasureChest.class, "nibiru_treasure_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityInfectedChest.class, "infected_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityAlienBerryChest.class, "alien_berry_chest");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityMultalicCrystal.class, "multalic_crystal");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityInfectedFurnace.class, "infected_furnace");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityJuicerEgg.class, "juicer_egg");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityNuclearWasteTank.class, "nuclear_waste_tank");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityVeinFrame.class, "vein_frame");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityVeinPortal.class, "vein_portal");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityNuclearWasteGenerator.class, "nuclear_waste_generator");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityTerrastoneFurnace.class, "terrastone_furnace");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityNibiruDungeonSpawner.class, "nibiru_dungeon_spawner");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntitySpacePortal.class, "space_portal");
        MorePlanetsMod.COMMON_REGISTRY.registerTileEntity(TileEntityGravityExtractor.class, "gravity_extractor");
    }
}