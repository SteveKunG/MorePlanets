package com.stevekung.moreplanets.client.renderer;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.init.MPItems;

public class VariantsRenderer
{
    public static void init()
    {
        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPBlocks.INFESTED_NIBIRU_ROCK, "nibiru_rock");
        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPBlocks.INFESTED_NIBIRU_COBBLESTONE, "nibiru_cobblestone");
        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPBlocks.INFESTED_NIBIRU_VEIN_COBBLESTONE, "nibiru_vein_cobblestone");
        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPBlocks.INFESTED_INFECTED_STONE_BRICKS, "infected_stone_bricks");
        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPBlocks.INFESTED_INFECTED_VEIN_STONE_BRICKS, "infected_vein_stone_bricks");
        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPBlocks.INFESTED_INFECTED_CRACKED_STONE_BRICKS, "infected_cracked_stone_bricks");
        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPBlocks.INFESTED_INFECTED_CHISELED_STONE_BRICKS, "infected_chiseled_stone_bricks");

        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPItems.SPACE_BOW, "space_bow", "space_bow_pulling_0", "space_bow_pulling_1", "space_bow_pulling_2");
        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPItems.SPACE_FISHING_ROD, "space_fishing_rod", "space_fishing_rod_cast");
        MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPItems.LASER_GUN, "laser_gun", "laser_gun_charged", "laser_gun_shoot");
    }
}