package stevekung.mods.moreplanets.init;

import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosAncientChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosDungeonSpawner;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.*;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.*;
import stevekung.mods.moreplanets.tileentity.*;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.tileentity.TileEntityNull;

public class MPTileEntities
{
    public static void init()
    {
        // Main
        CommonRegisterHelper.registerTileEntity(TileEntitySpaceWarpPad.class, "space_warp_pad");
        CommonRegisterHelper.registerTileEntity(TileEntitySpaceWarpPadFull.class, "space_warp_pad_full");
        CommonRegisterHelper.registerTileEntity(TileEntityDummy.class, "tile_dummy_block");
        CommonRegisterHelper.registerTileEntity(TileEntityRocketCrusher.class, "rocket_crusher");
        CommonRegisterHelper.registerTileEntity(TileEntityDarkEnergyReceiver.class, "dark_energy_receiver");
        CommonRegisterHelper.registerTileEntity(TileEntityNull.class, "dummy_null_mp");
        CommonRegisterHelper.registerTileEntity(TileEntityDarkEnergyStorageCluster.class, "dark_energy_storage_cluster");
        CommonRegisterHelper.registerTileEntity(TileEntityNuclearWasteStorageCluster.class, "nuclear_waste_storage_cluster");
        CommonRegisterHelper.registerTileEntity(TileEntityBlackHoleStorage.class, "black_hole_storage");
        CommonRegisterHelper.registerTileEntity(TileEntityAlienDefenderBeacon.class, "alien_defender_beacon");
        CommonRegisterHelper.registerTileEntity(TileEntityShieldGenerator.class, "shield_generator");

        // Diona
        CommonRegisterHelper.registerTileEntity(TileEntityDionaAncientChest.class, "diona_ancient_chest");
        CommonRegisterHelper.registerTileEntity(TileEntityDionaTreasureChest.class, "diona_treasure_chest");
        CommonRegisterHelper.registerTileEntity(TileEntityDionaDungeonSpawner.class, "diona_dungeon_spawner");
        CommonRegisterHelper.registerTileEntity(TileEntityZeliusEgg.class, "zelius_egg");
        CommonRegisterHelper.registerTileEntity(TileEntityInfectedCrystallizeEnderCore.class, "infected_crystallize_ender_core");
        CommonRegisterHelper.registerTileEntity(TileEntityLargeInfectedCrystallize.class, "large_infected_crystallize");
        CommonRegisterHelper.registerTileEntity(TileEntityDarkEnergyCore.class, "dark_energy_core");
        CommonRegisterHelper.registerTileEntity(TileEntityDarkEnergyGenerator.class, "dark_energy_generator");
        CommonRegisterHelper.registerTileEntity(TileEntityCrashedAlienProbe.class, "crashed_alien_probe");

        // Chalos
        CommonRegisterHelper.registerTileEntity(TileEntityChalosAncientChest.class, "chalos_ancient_chest");
        CommonRegisterHelper.registerTileEntity(TileEntityChalosTreasureChest.class, "chalos_treasure_chest");
        CommonRegisterHelper.registerTileEntity(TileEntityChalosDungeonSpawner.class, "chalos_dungeon_spawner");
        CommonRegisterHelper.registerTileEntity(TileEntityCheeseSporeChest.class, "cheese_spore_chest");

        // Nibiru
        CommonRegisterHelper.registerTileEntity(TileEntityNibiruAncientChest.class, "nibiru_ancient_chest");
        CommonRegisterHelper.registerTileEntity(TileEntityNibiruTreasureChest.class, "nibiru_treasure_chest");
        CommonRegisterHelper.registerTileEntity(TileEntityInfectedChest.class, "infected_chest");
        CommonRegisterHelper.registerTileEntity(TileEntityAlienBerryChest.class, "alien_berry_chest");
        CommonRegisterHelper.registerTileEntity(TileEntityMultalicCrystal.class, "multalic_crystal");
        CommonRegisterHelper.registerTileEntity(TileEntityNibiruFurnace.class, "nibiru_furnace");
        CommonRegisterHelper.registerTileEntity(TileEntityJuicerEgg.class, "juicer_egg");
        CommonRegisterHelper.registerTileEntity(TileEntityNuclearWasteTank.class, "nuclear_waste_tank");
        CommonRegisterHelper.registerTileEntity(TileEntityVeinFrame.class, "vein_frame");
        CommonRegisterHelper.registerTileEntity(TileEntityVeinPortal.class, "vein_portal");
        CommonRegisterHelper.registerTileEntity(TileEntityNuclearWasteGenerator.class, "nuclear_waste_generator");
        CommonRegisterHelper.registerTileEntity(TileEntityTerrastoneFurnace.class, "terrastone_furnace");
        CommonRegisterHelper.registerTileEntity(TileEntityNibiruDungeonSpawner.class, "nibiru_dungeon_spawner");
    }
}