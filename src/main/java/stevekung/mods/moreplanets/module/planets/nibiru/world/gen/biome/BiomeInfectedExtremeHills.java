package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedBigTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga2;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenMultalicCrystal;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenMinableMP;

public class BiomeInfectedExtremeHills extends BiomeNibiru
{
    private WorldGenerator theWorldGenerator;
    private int field_150635_aE;
    private int field_150636_aF;
    private int field_150638_aH;

    public BiomeInfectedExtremeHills()
    {
        super(ConfigManagerMP.idBiomeInfectedExtremeHills);
        this.theWorldGenerator = new WorldGenMinableMP(NibiruBlocks.NIBIRU_SILVERFISH_STONE.getDefaultState(), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), 8);
        this.enableSnow = true;
        this.field_150635_aE = 0;
        this.field_150636_aF = 1;
        this.field_150638_aH = this.field_150635_aE;
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.setTemperatureRainfall(0.2F, 0.3F);
        this.setHeight(new Height(1.0F, 0.5F));
        this.getBiomeDecorator().infectedTallGrassPerChunk = 2;
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        if (rand.nextInt(3) > 0)
        {
            return new WorldGenInfectedDeadTaiga2(true);
        }
        else if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(5) == 0 ? new WorldGenInfectedBigTree(false, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0) : new WorldGenInfectedTree(false, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0);
        }
        else
        {
            return super.genBigTreeChance(rand);
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);
        int k = 3 + rand.nextInt(6);
        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l)
        {
            i1 = rand.nextInt(16);
            j1 = rand.nextInt(28) + 4;
            int k1 = rand.nextInt(16);

            if (world.getBlockState(pos.add(i1, j1, k1)) == NibiruBlocks.NIBIRU_BLOCK.getDefaultState())
            {
                world.setBlockState(pos.add(i1, j1, k1), NibiruBlocks.NIBIRU_ORE.getStateFromMeta(6), 2);
            }
        }
        for (k = 0; k < 7; ++k)
        {
            l = rand.nextInt(16);
            i1 = rand.nextInt(64);
            j1 = rand.nextInt(16);
            this.theWorldGenerator.generate(world, rand, pos.add(l, i1, j1));
        }

        for (int i = 0; i < 24; i++)
        {
            new WorldGenMultalicCrystal().generate(world, rand, pos.add(rand.nextInt(16), rand.nextInt(28), rand.nextInt(16)));
        }
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int chunkX, int chunkZ, double stoneNoise)
    {
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();

        if (stoneNoise > 1.0D && this.field_150638_aH != this.field_150636_aF)
        {
            this.topBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
            this.fillerBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        }
        this.genPlanetTerrain(world, rand, chunkPrimer, chunkX, chunkZ, stoneNoise);
    }
}