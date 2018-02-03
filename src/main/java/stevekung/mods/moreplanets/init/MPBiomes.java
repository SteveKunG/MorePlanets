package stevekung.mods.moreplanets.init;

import java.util.LinkedList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeChalosHills;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeChalosPlains;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeSlimelyWasteland;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome.*;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.world.gen.biome.BiomeBaseMP;

public class MPBiomes
{
    public static final List<BiomeGenBaseGC> biomeList = new LinkedList<>();

    public static Biome DIONA = new BiomeBaseMP("diona", new BiomeProperties("Diona").setRainfall(0.0F));
    public static Biome CHALOS_PLAINS = new BiomeChalosPlains(new BiomeProperties("Chalos Plains").setTemperature(0.8F).setRainfall(0.4F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome CHALOS_HILLS = new BiomeChalosHills(new BiomeProperties("Chalos Hills").setTemperature(0.2F).setRainfall(0.3F).setBaseHeight(1.0F).setHeightVariation(0.5F));
    public static Biome SLIMELY_WASTELAND = new BiomeSlimelyWasteland(new BiomeProperties("Slimely Wasteland").setBaseHeight(0.2F).setHeightVariation(0.2F));
    public static Biome INFECTED_PLAINS = new BiomeInfectedPlains(new BiomeProperties("Infected Plains").setTemperature(0.8F).setRainfall(0.4F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome INFECTED_DEAD_SAVANNA = new BiomeInfectedDeadSavanna(new BiomeProperties("Infected Dead Savanna").setRainDisabled().setRainfall(0.0F).setTemperature(1.2F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome INFECTED_DESERT = new BiomeInfectedDesert(new BiomeProperties("Infected Desert").setRainDisabled().setRainfall(0.0F).setTemperature(2.0F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome INFECTED_RIVER = new BiomeInfectedRiver(new BiomeProperties("Infected River").setBaseHeight(-0.5F).setHeightVariation(0.0F));
    public static Biome INFECTED_OCEAN = new BiomeInfectedOcean(new BiomeProperties("Infected Ocean").setBaseHeight(-1.0F).setHeightVariation(0.1F));
    public static Biome INFECTED_FOREST = new BiomeInfectedForest(new BiomeProperties("Infected Forest").setTemperature(0.7F).setRainfall(0.8F));
    public static Biome INFECTED_DEEP_OCEAN = new BiomeInfectedDeepOcean(new BiomeProperties("Infected Deep Ocean").setBaseHeight(-1.8F).setHeightVariation(0.1F));
    public static Biome INFECTED_DEAD_ROOFED_FOREST = new BiomeInfectedDeadRoofedForest(new BiomeProperties("Infected Dead Roofed Forest").setTemperature(0.7F).setRainfall(0.8F));
    public static Biome INFECTED_EXTREME_HILLS = new BiomeInfectedExtremeHills(new BiomeProperties("Infected Extreme Hills").setTemperature(0.2F).setRainfall(0.3F).setBaseHeight(1.0F).setHeightVariation(0.5F));
    public static Biome INFECTED_SWAMPLAND = new BiomeInfectedSwampland(new BiomeProperties("Infected Swampland").setTemperature(0.8F).setRainfall(0.9F).setBaseHeight(-0.2F).setHeightVariation(0.1F));
    public static Biome INFECTED_DEAD_TAIGA = new BiomeInfectedDeadTaiga(new BiomeProperties("Infected Dead Taiga").setBaseHeight(0.2F).setHeightVariation(0.2F).setTemperature(0.25F).setRainfall(0.8F));
    public static Biome INFECTED_JUNGLE = new BiomeInfectedJungle(new BiomeProperties("Infected Jungle").setTemperature(0.95F).setRainfall(0.9F));
    public static Biome INFECTED_ICE_PLAINS = new BiomeInfectedIcePlains(new BiomeProperties("Infected Ice Plains").setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome GREEN_VEIN = new BiomeGreenVein(new BiomeProperties("Green Vein").setTemperature(0.9F).setRainfall(1.0F).setBaseHeight(0.125F).setHeightVariation(0.05F));

    public static void init()
    {
        CommonRegisterHelper.registerBiome(MPBiomes.DIONA);
        CommonRegisterHelper.registerBiome(MPBiomes.CHALOS_PLAINS);
        CommonRegisterHelper.registerBiome(MPBiomes.CHALOS_HILLS);
        CommonRegisterHelper.registerBiome(MPBiomes.SLIMELY_WASTELAND);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_PLAINS);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_DEAD_SAVANNA);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_DESERT);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_RIVER);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_OCEAN);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_FOREST);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_DEEP_OCEAN);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_DEAD_ROOFED_FOREST);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_EXTREME_HILLS);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_SWAMPLAND);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_DEAD_TAIGA);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_JUNGLE);
        CommonRegisterHelper.registerBiome(MPBiomes.INFECTED_ICE_PLAINS);
        CommonRegisterHelper.registerBiome(MPBiomes.GREEN_VEIN);
    }
}