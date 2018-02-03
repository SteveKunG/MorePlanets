package stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.WASTELAND;

import java.util.LinkedList;

import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class BiomeSlimelyWasteland extends BiomeChalos
{
    public BiomeSlimelyWasteland(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = ChalosBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.stoneBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
    }

    @Override
    public void registerTypes(Biome biome)
    {
        CommonRegisterHelper.registerBiomeType(biome, WASTELAND, DRY);
    }

    @Override
    public void initialiseMobLists(LinkedList<SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}