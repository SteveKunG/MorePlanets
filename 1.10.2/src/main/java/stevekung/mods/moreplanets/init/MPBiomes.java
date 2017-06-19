package stevekung.mods.moreplanets.init;

import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeChalosHills;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeChalosPlains;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.BiomeSlimelyWasteland;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome.*;
import stevekung.mods.moreplanets.util.world.gen.biome.BiomeBaseMP;

public class MPBiomes
{
    public static Biome DIONA = new BiomeBaseMP(ConfigManagerMP.idBiomeDiona).setBiomeName("Diona");
    public static Biome CHALOS_PLAINS = new BiomeChalosPlains().setBiomeName("Chalos Plains");
    public static Biome CHALOS_HILLS = new BiomeChalosHills().setBiomeName("Chalos Hills");
    public static Biome SLIMELY_WASTELAND = new BiomeSlimelyWasteland().setBiomeName("Slimely Wasteland");
    public static Biome INFECTED_PLAINS = new BiomeInfectedPlains().setBiomeName("Infected Plains");
    public static Biome INFECTED_DEAD_SAVANNA = new BiomeInfectedDeadSavanna().setBiomeName("Infected Dead Savanna");
    public static Biome INFECTED_DESERT = new BiomeInfectedDesert().setBiomeName("Infected Desert");
    public static Biome INFECTED_RIVER = new BiomeInfectedRiver().setBiomeName("Infected River");
    public static Biome INFECTED_OCEAN = new BiomeInfectedOcean().setBiomeName("Infected Ocean");
    public static Biome INFECTED_FOREST = new BiomeInfectedForest().setBiomeName("Infected Forest");
    public static Biome INFECTED_DEEP_OCEAN = new BiomeInfectedDeepOcean().setBiomeName("Infected Deep Ocean");
    public static Biome INFECTED_DEAD_ROOFED_FOREST = new BiomeInfectedDeadRoofedForest().setBiomeName("Infected Dead Roofed Forest");
    public static Biome INFECTED_EXTREME_HILLS = new BiomeInfectedExtremeHills().setBiomeName("Infected Extreme Hills");
    public static Biome INFECTED_SWAMPLAND = new BiomeInfectedSwampland().setBiomeName("Infected Swampland");
    public static Biome INFECTED_DEAD_TAIGA = new BiomeInfectedDeadTaiga().setBiomeName("Infected Dead Taiga");
    public static Biome INFECTED_JUNGLE = new BiomeInfectedJungle().setBiomeName("Infected Jungle");
    public static Biome INFECTED_ICE_PLAINS = new BiomeInfectedIcePlains().setBiomeName("Infected Ice Plains");
    public static Biome GREEN_VEIN = new BiomeGreenVein().setBiomeName("Green Vein");
    
    public static void init()
    {
        
    }
}