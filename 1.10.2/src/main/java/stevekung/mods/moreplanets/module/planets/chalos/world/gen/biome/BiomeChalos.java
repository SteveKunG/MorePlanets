package stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.BiomeDecoratorChalos;
import stevekung.mods.moreplanets.util.world.gen.biome.BiomeBaseMP;

public class BiomeChalos extends BiomeBaseMP
{
    protected IBlockState stoneBlock;
    protected BiomeDecoratorChalos decorator;

    public BiomeChalos(BiomeProperties properties)
    {
        super(properties);
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;
        this.decorator = this.getBiomeDecorator();
    }

    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        return new BiomeDecoratorChalos();
    }

    private BiomeDecoratorChalos getBiomeDecorator()
    {
        return new BiomeDecoratorChalos();
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double stoneNoise)
    {
        this.genChalosBiomeTerrain(world, rand, chunkPrimer, x, z, stoneNoise);
    }

    protected void genChalosBiomeTerrain(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double stoneNoise)
    {
        int i = world.getSeaLevel();
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int j = -1;
        int k = (int)(stoneNoise / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 15;
        int i1 = z & 15;

        for (int j1 = 255; j1 >= 0; --j1)
        {
            if (j1 <= rand.nextInt(5))
            {
                chunkPrimer.setBlockState(i1, j1, l, Blocks.BEDROCK.getDefaultState());
            }
            else
            {
                IBlockState iblockstate2 = chunkPrimer.getBlockState(i1, j1, l);

                if (iblockstate2.getMaterial() == Material.AIR)
                {
                    j = -1;
                }
                else if (iblockstate2.getBlock() == ChalosBlocks.CHALOS_BLOCK)
                {
                    if (this.stoneBlock != null)
                    {
                        chunkPrimer.setBlockState(i1, j1, l, this.stoneBlock);
                    }
                    if (j == -1)
                    {
                        if (k <= 0)
                        {
                            iblockstate = null;
                            iblockstate1 = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
                        }
                        else if (j1 >= i - 4 && j1 <= i + 1)
                        {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                        {
                            iblockstate = ChalosBlocks.CHEESE_OF_MILK_FLUID_BLOCK.getDefaultState();
                        }

                        j = k;

                        if (j1 >= i - 1)
                        {
                            chunkPrimer.setBlockState(i1, j1, l, iblockstate);
                        }
                        else if (j1 < i - 7 - k)
                        {
                            iblockstate = null;
                            iblockstate1 = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
                            chunkPrimer.setBlockState(i1, j1, l, ChalosBlocks.CHALOS_BLOCK.getDefaultState());//gravel
                        }
                        else
                        {
                            chunkPrimer.setBlockState(i1, j1, l, iblockstate1);
                        }
                    }
                    else if (j > 0)
                    {
                        --j;
                        chunkPrimer.setBlockState(i1, j1, l, iblockstate1);
                    }
                }
            }
        }
    }
}