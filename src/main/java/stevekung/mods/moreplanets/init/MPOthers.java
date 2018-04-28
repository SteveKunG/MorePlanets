package stevekung.mods.moreplanets.init;

import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizedBomb;
import stevekung.mods.moreplanets.module.planets.diona.entity.projectile.EntityInfectedCrystallizedArrow;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityInfectedArrow;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityInfectedEgg;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityInfectedSnowball;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
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
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(DionaItems.INFECTED_CRYSTALLIZED_BOMB, new BehaviorProjectileDispenseBase(EntityInfectedCrystallizedBomb.class));
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(NibiruItems.INFECTED_SNOWBALL, new BehaviorProjectileDispenseBase(EntityInfectedSnowball.class));
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(NibiruItems.INFECTED_EGG, new BehaviorProjectileDispenseBase(EntityInfectedEgg.class));
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(DionaItems.INFECTED_CRYSTALLIZED_ARROW, new BehaviorProjectileDispenseBase(EntityInfectedCrystallizedArrow.class, true));
        MorePlanetsMod.COMMON_REGISTRY.registerProjectileDispense(NibiruItems.INFECTED_ARROW, new BehaviorProjectileDispenseBase(EntityInfectedArrow.class, true));
    }

    private static void registerEndermanCarriableBlock()
    {
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(DionaBlocks.INFECTED_CRYSTALLIZED_EYE_CORE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(DionaBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(DionaBlocks.ZELIUS_EGG);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(ChalosBlocks.CHEESE_GRASS_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(ChalosBlocks.CHEESE_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(ChalosBlocks.CHEESE_COARSE_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(ChalosBlocks.CHEESE_SLIME_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(ChalosBlocks.CHEESE_MILK_CAKE);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_GRASS);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_CLAY);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_GRAVEL);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_SNOW);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_SNOW_LAYER);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.GREEN_VEIN_GRASS);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_MELON_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_SAND);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_CACTUS);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.NIBIRU_FLOWER);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.INFECTED_VINES_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.PURIFY_GRAVEL);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(NibiruBlocks.NIBIRU_GRASS_PATH);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(FronosBlocks.FRONOS_GRASS);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(FronosBlocks.FRONOS_DIRT);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(FronosBlocks.CANDY_CANE_1);
        MorePlanetsMod.COMMON_REGISTRY.registerCarriable(FronosBlocks.CANDY_CANE_2);
    }
}