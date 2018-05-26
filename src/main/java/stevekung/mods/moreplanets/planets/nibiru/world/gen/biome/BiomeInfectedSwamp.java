package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedSwampTree;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedSwamp extends BiomeNibiru
{
    private static final WorldGenInfectedSwampTree SWAMP_TREE = new WorldGenInfectedSwampTree(true);
    private static final WorldGenInfectedSwampTree SWAMP_TREE_NO_LEAVES = new WorldGenInfectedSwampTree(false);

    public BiomeInfectedSwamp(BiomeProperties prop)
    {
        super(prop);
        this.decorator.infectedTreesPerChunk = 2;
        this.decorator.infectedTallGrassPerChunk = 5;
        this.decorator.waterlilyPerChunk = 4;
        this.decorator.whiteTailPerChunk = 4;
        this.decorator.clayPerChunk = 1;
        this.decorator.reedsPerChunk = 10;
        this.decorator.sandPatchesPerChunk = 0;
        this.decorator.gravelPatchesPerChunk = 0;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(25) == 0)
        {
            BiomeNibiru.SCATTERED_DIRT.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        if (rand.nextInt(64) == 0)
        {
            BiomeNibiru.FOSSILS.generate(world, rand, pos);
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(20) == 0 ? BiomeInfectedSwamp.SWAMP_TREE_NO_LEAVES : BiomeInfectedSwamp.SWAMP_TREE;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double noiseVal)
    {
        double grassColorNoise = GRASS_COLOR_NOISE.getValue(chunkX * 0.25D, chunkZ * 0.25D);

        if (grassColorNoise > 0.0D)
        {
            int x = chunkX & 15;
            int z = chunkZ & 15;

            for (int y = 255; y >= 0; --y)
            {
                if (primer.getBlockState(x, y, z).getMaterial() != Material.AIR)
                {
                    if (y == 62 && primer.getBlockState(x, y, z).getBlock() != MPBlocks.INFECTED_WATER_FLUID_BLOCK)
                    {
                        primer.setBlockState(x, y, z, MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState());

                        if (grassColorNoise < 0.12D)
                        {
                            primer.setBlockState(x, y + 1, z, MPBlocks.SPORELILY.getDefaultState());
                        }
                    }
                    break;
                }
            }
        }
        super.genTerrainBlocks(world, rand, primer, chunkX, chunkZ, noiseVal);
    }
}