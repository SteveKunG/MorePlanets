package stevekung.mods.moreplanets.init;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class MPBiomeTypes
{
    public static void init()
    {
        CommonRegisterHelper.registerBiomeType(MPBiomes.DIONA, COLD, DEAD, DRY);
        CommonRegisterHelper.registerBiomeType(MPBiomes.CHALOS_PLAINS, PLAINS);
        CommonRegisterHelper.registerBiomeType(MPBiomes.CHALOS_HILLS, MOUNTAIN, HILLS);
        CommonRegisterHelper.registerBiomeType(MPBiomes.SLIMELY_WASTELAND, WASTELAND, DRY);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_PLAINS, PLAINS, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_DEAD_SAVANNA, HOT, SAVANNA, PLAINS, SPARSE, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_DESERT, HOT, DRY, SANDY, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_RIVER, RIVER, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_OCEAN, OCEAN, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_FOREST, FOREST, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_DEEP_OCEAN, OCEAN, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_DEAD_ROOFED_FOREST, SPOOKY, DENSE, FOREST, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_EXTREME_HILLS, MOUNTAIN, HILLS, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_SWAMPLAND, WET, SWAMP, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_DEAD_TAIGA, COLD, CONIFEROUS, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_JUNGLE, HOT, WET, DENSE, JUNGLE, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.INFECTED_ICE_PLAINS, COLD, SNOWY, WASTELAND, DEAD);
        CommonRegisterHelper.registerBiomeType(MPBiomes.GREEN_VEIN, FOREST, RARE, MAGICAL);
    }
}