package stevekung.mods.moreplanets.init;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeChalosHills;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeChalosPlains;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeSlimelyWasteland;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome.*;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.world.gen.biome.BiomeBaseMP;

public class MPBiomes
{
    public static Biome DIONA = new BiomeBaseMP(new BiomeProperties("Diona"));
    public static Biome CHALOS_PLAINS = new BiomeChalosPlains(new BiomeProperties("Chalos Plains"));
    public static Biome CHALOS_HILLS = new BiomeChalosHills(new BiomeProperties("Chalos Hills"));
    public static Biome SLIMELY_WASTELAND = new BiomeSlimelyWasteland(new BiomeProperties("Slimely Wasteland"));
    public static Biome INFECTED_PLAINS = new BiomeInfectedPlains(new BiomeProperties("Infected Plains"));
    public static Biome INFECTED_DEAD_SAVANNA = new BiomeInfectedDeadSavanna(new BiomeProperties("Infected Dead Savanna"));
    public static Biome INFECTED_DESERT = new BiomeInfectedDesert(new BiomeProperties("Infected Desert"));
    public static Biome INFECTED_RIVER = new BiomeInfectedRiver(new BiomeProperties("Infected River"));
    public static Biome INFECTED_OCEAN = new BiomeInfectedOcean(new BiomeProperties("Infected Ocean"));
    public static Biome INFECTED_FOREST = new BiomeInfectedForest(new BiomeProperties("Infected Forest"));
    public static Biome INFECTED_DEEP_OCEAN = new BiomeInfectedDeepOcean(new BiomeProperties("Infected Deep Ocean"));
    public static Biome INFECTED_DEAD_ROOFED_FOREST = new BiomeInfectedDeadRoofedForest(new BiomeProperties("Infected Dead Roofed Forest"));
    public static Biome INFECTED_EXTREME_HILLS = new BiomeInfectedExtremeHills(new BiomeProperties("Infected Extreme Hills"));
    public static Biome INFECTED_SWAMPLAND = new BiomeInfectedSwampland(new BiomeProperties("Infected Swampland"));
    public static Biome INFECTED_DEAD_TAIGA = new BiomeInfectedDeadTaiga(new BiomeProperties("Infected Dead Taiga"));
    public static Biome INFECTED_JUNGLE = new BiomeInfectedJungle(new BiomeProperties("Infected Jungle"));
    public static Biome INFECTED_ICE_PLAINS = new BiomeInfectedIcePlains(new BiomeProperties("Infected Ice Plains"));
    public static Biome GREEN_VEIN = new BiomeGreenVein(new BiomeProperties("Green Vein"));

    public static void init()
    {
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeDiona, "diona", MPBiomes.DIONA);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeChalosPlains, "chalos_plains", MPBiomes.CHALOS_PLAINS);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeChalosHills, "chalos_hills", MPBiomes.CHALOS_HILLS);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeSlimelyWasteland, "slimely_wasteland", MPBiomes.SLIMELY_WASTELAND);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedPlains, "infected_plains", MPBiomes.INFECTED_PLAINS);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedDeadSavanna, "infected_dead_savanna", MPBiomes.INFECTED_DEAD_SAVANNA);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedDesert, "infected_desert", MPBiomes.INFECTED_DESERT);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedRiver, "infected_river", MPBiomes.INFECTED_RIVER);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedOcean, "infected_ocean", MPBiomes.INFECTED_OCEAN);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedForest, "infected_forest", MPBiomes.INFECTED_FOREST);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedDeepOcean, "infected_deep_ocean", MPBiomes.INFECTED_DEEP_OCEAN);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedDeadRoofedForest, "infected_dead_roofed_forest", MPBiomes.INFECTED_DEAD_ROOFED_FOREST);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedExtremeHills, "infected_extreme_hills", MPBiomes.INFECTED_EXTREME_HILLS);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedSwampland, "infected_swampland", MPBiomes.INFECTED_SWAMPLAND);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedDeadTaiga, "infected_dead_taiga", MPBiomes.INFECTED_DEAD_TAIGA);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedJungle, "infected_jungle", MPBiomes.INFECTED_JUNGLE);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeInfectedIcePlains, "infected_ice_plains", MPBiomes.INFECTED_ICE_PLAINS);
        CommonRegisterHelper.registerBiome(ConfigManagerMP.idBiomeGreenVein, "green_vein", MPBiomes.GREEN_VEIN);
    }
}