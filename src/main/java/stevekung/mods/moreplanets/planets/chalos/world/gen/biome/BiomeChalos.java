package stevekung.mods.moreplanets.planets.chalos.world.gen.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseCow;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseFloater;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseSlime;
import stevekung.mods.moreplanets.planets.chalos.world.gen.BiomeDecoratorChalos;
import stevekung.mods.moreplanets.utils.world.gen.biome.BiomeMP;

public class BiomeChalos extends BiomeMP
{
    protected final IBlockState stoneBlock;
    protected final BiomeDecoratorChalos decorator = new BiomeDecoratorChalos();

    public BiomeChalos(BiomeProperties prop)
    {
        super(prop);
        this.topBlock = MPBlocks.CHEESE_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.CHEESE_DIRT.getDefaultState();
        this.stoneBlock = MPBlocks.CHALOS_ROCK.getDefaultState();
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCheeseFloater.class, 20, 1, 2));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCheeseSlime.class, 30, 2, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCheeseCow.class, 8, 4, 4));
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        this.decorator.decorate(world, rand, this, pos);
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double noiseVal)
    {
        int seaLevel = world.getSeaLevel();
        IBlockState topState = this.topBlock;
        IBlockState fillerState = this.fillerBlock;
        int j = -1;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int x = chunkZ & 15;// WTF??
        int z = chunkX & 15;// WTF??

        for (int y = 255; y >= 0; --y)
        {
            if (y <= rand.nextInt(5))
            {
                primer.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());
            }
            else
            {
                IBlockState stoneState = primer.getBlockState(x, y, z);

                if (stoneState.getMaterial() == Material.AIR)
                {
                    j = -1;
                }
                else if (stoneState.getBlock() == MPBlocks.CHALOS_ROCK)
                {
                    primer.setBlockState(x, y, z, this.stoneBlock);

                    if (j == -1)
                    {
                        if (k <= 0)
                        {
                            topState = null;
                            fillerState = MPBlocks.CHALOS_ROCK.getDefaultState();
                        }
                        else if (y >= seaLevel - 4 && y <= seaLevel + 1)
                        {
                            topState = this.topBlock;
                            fillerState = this.fillerBlock;
                        }

                        if (y < seaLevel && (topState == null || topState.getMaterial() == Material.AIR))
                        {
                            topState = MPBlocks.CHEESE_MILK_FLUID_BLOCK.getDefaultState();
                        }

                        j = k;

                        if (y >= seaLevel - 1)
                        {
                            primer.setBlockState(x, y, z, topState);
                        }
                        else if (y < seaLevel - 7 - k)
                        {
                            topState = null;
                            fillerState = MPBlocks.CHALOS_ROCK.getDefaultState();
                            primer.setBlockState(x, y, z, MPBlocks.CHALOS_ROCK.getDefaultState());
                        }
                        else
                        {
                            primer.setBlockState(x, y, z, fillerState);
                        }
                    }
                    else if (j > 0)
                    {
                        --j;
                        primer.setBlockState(x, y, z, fillerState);
                    }
                }
            }
        }
    }
}