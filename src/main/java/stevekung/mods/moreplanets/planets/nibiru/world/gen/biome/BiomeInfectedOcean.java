package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.init.MPBlocks;

public class BiomeInfectedOcean extends BiomeNibiru
{
    public BiomeInfectedOcean(BiomeProperties prop)
    {
        super(prop);
        this.decorator.flowersPerChunk = 4;
    }

    @Override
    public IBlockState pickRandomModdedFlower(Random rand, BlockPos pos)
    {
        return MPBlocks.INFECTED_SEAWEED.getDefaultState();
    }

    @Override
    public void addDefaultFlowers()
    {
        this.addFlower(MPBlocks.INFECTED_SEAWEED.getDefaultState(), 20);
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}