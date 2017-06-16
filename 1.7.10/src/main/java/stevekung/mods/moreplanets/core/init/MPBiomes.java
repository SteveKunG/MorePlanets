package stevekung.mods.moreplanets.core.init;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import stevekung.mods.moreplanets.moons.deimos.worldgen.BiomeGenBaseDeimos;
import stevekung.mods.moreplanets.moons.koentus.worldgen.BiomeGenBaseKoentus;
import stevekung.mods.moreplanets.moons.phobos.worldgen.BiomeGenBasePhobos;
import stevekung.mods.moreplanets.planets.diona.worldgen.BiomeGenBaseDiona;
import stevekung.mods.moreplanets.planets.fronos.worldgen.biome.BiomeGenBaseFronos;
import stevekung.mods.moreplanets.planets.kapteynb.worldgen.BiomeGenBaseKapteynB;
import stevekung.mods.moreplanets.planets.mercury.worldgen.BiomeGenBaseMercury;
import stevekung.mods.moreplanets.planets.nibiru.worldgen.BiomeGenBaseNibiru;
import stevekung.mods.moreplanets.planets.pluto.worldgen.BiomeGenBasePluto;
import stevekung.mods.moreplanets.planets.polongnius.worldgen.BiomeGenBasePolongnius;
import stevekung.mods.moreplanets.planets.siriusb.worldgen.BiomeGenBaseSiriusB;
import stevekung.mods.moreplanets.planets.venus.worldgen.BiomeGenBaseVenus;

public class MPBiomes
{
    public static void init()
    {
        BiomeDictionary.registerBiomeType(BiomeGenBaseDiona.diona, Type.COLD, Type.DRY, Type.DEAD);
        BiomeDictionary.registerBiomeType(BiomeGenBasePolongnius.polongnius, Type.HOT, Type.DRY, Type.DEAD, Type.DENSE);
        BiomeDictionary.registerBiomeType(BiomeGenBaseNibiru.nibiru, Type.HOT, Type.DRY, Type.DEAD, Type.HILLS, Type.SANDY, Type.WASTELAND, Type.DENSE);
        BiomeDictionary.registerBiomeType(BiomeGenBaseKoentus.koentus, Type.COLD, Type.DRY, Type.DEAD, Type.SPOOKY, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BiomeGenBaseFronos.grassyPlains, Type.LUSH, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BiomeGenBaseFronos.goldenField, Type.LUSH, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BiomeGenBaseFronos.coconutForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BiomeGenBaseFronos.mapleForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BiomeGenBaseFronos.purpleMapleForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BiomeGenBaseFronos.candyLand, Type.MAGICAL);
        BiomeDictionary.registerBiomeType(BiomeGenBaseKapteynB.kapteynB, Type.COLD, Type.DRY, Type.DEAD, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BiomeGenBaseSiriusB.siriusB, Type.HOT, Type.DRY, Type.DEAD, Type.SPARSE, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BiomeGenBaseMercury.mercury, Type.HOT, Type.DRY, Type.DEAD);
        BiomeDictionary.registerBiomeType(BiomeGenBaseVenus.venus, Type.HOT, Type.DRY, Type.DEAD, Type.DENSE, Type.HILLS, Type.SANDY);
        BiomeDictionary.registerBiomeType(BiomeGenBasePluto.pluto, Type.COLD, Type.DRY, Type.DEAD, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BiomeGenBasePhobos.phobos, Type.COLD, Type.DRY, Type.DEAD);
        BiomeDictionary.registerBiomeType(BiomeGenBaseDeimos.deimos, Type.COLD, Type.DRY, Type.DEAD);
    }
}