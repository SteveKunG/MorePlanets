package stevekung.mods.moreplanets.init;

import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedCrystallizedBomb;
import stevekung.mods.moreplanets.planets.diona.entity.projectile.EntityInfectedCrystallizedArrow;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityInfectedArrow;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityInfectedEgg;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityInfectedSnowball;
import stevekung.mods.stevekunglib.utils.BehaviorProjectileDispenseBase;

public class MPOthers
{
    public static void init()
    {
        MPOthers.registerDispenserObject();
        MPOthers.registerEndermanCarriableBlock();
    }

    private static void registerDispenserObject()
    {
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(MPItems.INFECTED_CRYSTALLIZED_BOMB, new BehaviorProjectileDispenseBase(EntityInfectedCrystallizedBomb.class));
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(MPItems.INFECTED_SNOWBALL, new BehaviorProjectileDispenseBase(EntityInfectedSnowball.class));
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(MPItems.INFECTED_EGG, new BehaviorProjectileDispenseBase(EntityInfectedEgg.class));
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(MPItems.INFECTED_CRYSTALLIZED_ARROW, new BehaviorProjectileDispenseBase(EntityInfectedCrystallizedArrow.class, true));
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(MPItems.INFECTED_ARROW, new BehaviorProjectileDispenseBase(EntityInfectedArrow.class, true));
    }

    private static void registerEndermanCarriableBlock()
    {
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_CRYSTALLIZED_EYE_CORE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.ZELIUS_EGG);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.CHEESE_GRASS_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.CHEESE_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.CHEESE_COARSE_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.CHEESE_SLIME_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.CHEESE_MILK_CAKE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_GRASS_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_COARSE_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_CLAY);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_GRAVEL);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_SNOW);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_SNOW_LAYER);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.GREEN_VEIN_GRASS_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_MELON);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_SAND);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_CACTUS);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.PURE_HERB);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.TERRAPUFF_HERB);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.BATASIA_DANDELION);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.PYOLONIA);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.PHILIPY);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.WHITE_TAIL);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.VEALIUM_VINES);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.TERRASHROOM);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_VINES_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.PURIFIED_GRAVEL);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.INFECTED_GRASS_PATH);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.GREEN_VEIN_GRASS_PATH);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.FRONOS_GRASS_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.FRONOS_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.FRONOS_COARSE_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.RED_CANDY_CANE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.GREEN_CANDY_CANE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.BLUE_CANDY_CANE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.ORANGE_CANDY_CANE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.PINK_CANDY_CANE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.YELLOW_CANDY_CANE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.PURPLE_CANDY_CANE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(MPBlocks.RAINBOW_CANDY_CANE);
    }
}