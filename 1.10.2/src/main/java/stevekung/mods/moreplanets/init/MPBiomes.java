package stevekung.mods.moreplanets.init;

import net.minecraft.world.biome.BiomeGenBase;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeChalosHills;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeChalosPlains;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeSlimelyWasteland;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome.*;
import stevekung.mods.moreplanets.util.world.gen.biome.BiomeBaseMP;

public class MPBiomes
{
    public static BiomeGenBase DIONA = new BiomeBaseMP(ConfigManagerMP.idBiomeDiona).setBiomeName("Diona");
    public static BiomeGenBase CHALOS_PLAINS = new BiomeChalosPlains().setBiomeName("Chalos Plains");
    public static BiomeGenBase CHALOS_HILLS = new BiomeChalosHills().setBiomeName("Chalos Hills");
    public static BiomeGenBase SLIMELY_WASTELAND = new BiomeSlimelyWasteland().setBiomeName("Slimely Wasteland");
    public static BiomeGenBase INFECTED_PLAINS = new BiomeInfectedPlains().setBiomeName("Infected Plains");
    public static BiomeGenBase INFECTED_DEAD_SAVANNA = new BiomeInfectedDeadSavanna().setBiomeName("Infected Dead Savanna");
    public static BiomeGenBase INFECTED_DESERT = new BiomeInfectedDesert().setBiomeName("Infected Desert");
    public static BiomeGenBase INFECTED_RIVER = new BiomeInfectedRiver().setBiomeName("Infected River");
    public static BiomeGenBase INFECTED_OCEAN = new BiomeInfectedOcean().setBiomeName("Infected Ocean");
    public static BiomeGenBase INFECTED_FOREST = new BiomeInfectedForest().setBiomeName("Infected Forest");
    public static BiomeGenBase INFECTED_DEEP_OCEAN = new BiomeInfectedDeepOcean().setBiomeName("Infected Deep Ocean");
    public static BiomeGenBase INFECTED_DEAD_ROOFED_FOREST = new BiomeInfectedDeadRoofedForest().setBiomeName("Infected Dead Roofed Forest");
    public static BiomeGenBase INFECTED_EXTREME_HILLS = new BiomeInfectedExtremeHills().setBiomeName("Infected Extreme Hills");
    public static BiomeGenBase INFECTED_SWAMPLAND = new BiomeInfectedSwampland().setBiomeName("Infected Swampland");
    public static BiomeGenBase INFECTED_DEAD_TAIGA = new BiomeInfectedDeadTaiga().setBiomeName("Infected Dead Taiga");
    public static BiomeGenBase INFECTED_JUNGLE = new BiomeInfectedJungle().setBiomeName("Infected Jungle");
    public static BiomeGenBase INFECTED_ICE_PLAINS = new BiomeInfectedIcePlains().setBiomeName("Infected Ice Plains");
    public static BiomeGenBase GREEN_VEIN = new BiomeGreenVein().setBiomeName("Green Vein");
}