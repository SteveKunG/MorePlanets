package stevekung.mods.moreplanets.client.renderer;

import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;

public class VariantsRenderer
{
    public static void init()
    {
        VariantsRenderer.init3DRendering();

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

    private static void init3DRendering()
    {
        if (!ConfigManagerMP.moreplanets_general.use3DTorchItemModel)
        {
            MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPBlocks.INFECTED_PURLONITE_TORCH, "infected_purlonite_torch_vanilla");
            MorePlanetsMod.CLIENT_REGISTRY.registerVariantsName(MPBlocks.INFECTED_TORCH, "infected_torch_vanilla");
        }
    }
}