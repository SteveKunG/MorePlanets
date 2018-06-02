package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.world.gen.EnumOreGen;
import stevekung.mods.stevekunglib.world.gen.WorldGenMinableBase;

public class BiomeInfectedBadlands extends BiomeNibiru
{
    private IBlockState[] clayBands;
    private long seed;
    private NoiseGeneratorPerlin pillarNoise;
    private NoiseGeneratorPerlin pillarRoofNoise;
    private NoiseGeneratorPerlin clayBandsOffsetNoise;
    private final boolean brycePillars;
    private final boolean hasForest;
    private WorldGenerator goldGen;

    public BiomeInfectedBadlands(BiomeProperties prop, boolean brycePillars, boolean hasForest)
    {
        super(prop);
        this.brycePillars = brycePillars;
        this.hasForest = hasForest;
        this.topBlock = MPBlocks.INFECTED_SAND.getDefaultState();
        this.fillerBlock = Blocks.STAINED_HARDENED_CLAY.getDefaultState();
        this.decorator.treesPerChunk = -999;
        this.decorator.deadBushPerChunk = 20;
        this.decorator.reedsPerChunk = 3;
        this.decorator.cactiPerChunk = 5;
        this.decorator.flowersPerChunk = 0;
        this.goldGen = new WorldGenMinableBase(MPBlocks.INFECTED_GOLD_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.GOLD);

        if (hasForest)
        {
            this.decorator.treesPerChunk = 5;
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);
        this.decorator.generateOre(this.goldGen, 20, 32, 80, world, rand);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(20) == 0 ? BiomeNibiru.TREE_NO_LEAVES : BiomeNibiru.TREE;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double noiseVal)
    {
        if (this.clayBands == null || this.seed != world.getSeed())
        {
            this.generateBands(world.getSeed());
        }
        if (this.pillarNoise == null || this.pillarRoofNoise == null || this.seed != world.getSeed())
        {
            Random random = new Random(this.seed);
            this.pillarNoise = new NoiseGeneratorPerlin(random, 4);
            this.pillarRoofNoise = new NoiseGeneratorPerlin(random, 1);
        }

        this.seed = world.getSeed();
        double d4 = 0.0D;

        if (this.brycePillars)
        {
            int i = (chunkX & -16) + (chunkZ & 15);
            int j = (chunkZ & -16) + (chunkX & 15);
            double d0 = Math.min(Math.abs(noiseVal), this.pillarNoise.getValue(i * 0.25D, j * 0.25D));

            if (d0 > 0.0D)
            {
                double d2 = Math.abs(this.pillarRoofNoise.getValue(i * 0.001953125D, j * 0.001953125D));
                d4 = d0 * d0 * 2.5D;
                double d3 = Math.ceil(d2 * 50.0D) + 14.0D;

                if (d4 > d3)
                {
                    d4 = d3;
                }
                d4 = d4 + 64.0D;
            }
        }

        int k1 = chunkX & 15;
        int l1 = chunkZ & 15;
        int i2 = world.getSeaLevel();
        IBlockState iblockstate = Blocks.STAINED_HARDENED_CLAY.getDefaultState();
        IBlockState iblockstate3 = this.fillerBlock;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        boolean flag = Math.cos(noiseVal / 3.0D * Math.PI) > 0.0D;
        int l = -1;
        boolean flag1 = false;
        int i1 = 0;

        for (int j1 = 255; j1 >= 0; --j1)
        {
            if (primer.getBlockState(l1, j1, k1).getMaterial() == Material.AIR && j1 < (int)d4)
            {
                primer.setBlockState(l1, j1, k1, MPBlocks.NIBIRU_ROCK.getDefaultState());
            }

            if (j1 <= rand.nextInt(5))
            {
                primer.setBlockState(l1, j1, k1, Blocks.BEDROCK.getDefaultState());
            }
            else if (i1 < 15 || this.brycePillars)
            {
                IBlockState iblockstate1 = primer.getBlockState(l1, j1, k1);

                if (iblockstate1.getMaterial() == Material.AIR)
                {
                    l = -1;
                }
                else if (iblockstate1.getBlock() == MPBlocks.NIBIRU_ROCK)
                {
                    if (l == -1)
                    {
                        flag1 = false;

                        if (k <= 0)
                        {
                            iblockstate = Blocks.AIR.getDefaultState();
                            iblockstate3 = MPBlocks.NIBIRU_ROCK.getDefaultState();
                        }
                        else if (j1 >= i2 - 4 && j1 <= i2 + 1)
                        {
                            iblockstate = Blocks.STAINED_HARDENED_CLAY.getDefaultState();
                            iblockstate3 = this.fillerBlock;
                        }

                        if (j1 < i2 && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                        {
                            iblockstate = MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState();
                        }

                        l = k + Math.max(0, j1 - i2);

                        if (j1 >= i2 - 1)
                        {
                            if (this.hasForest && j1 > 86 + k * 2)
                            {
                                if (flag)
                                {
                                    primer.setBlockState(l1, j1, k1, MPBlocks.INFECTED_COARSE_DIRT.getDefaultState());
                                }
                                else
                                {
                                    primer.setBlockState(l1, j1, k1, MPBlocks.INFECTED_GRASS_BLOCK.getDefaultState());
                                }
                            }
                            else if (j1 > i2 + 3 + k)
                            {
                                IBlockState iblockstate2;

                                if (j1 >= 64 && j1 <= 127)
                                {
                                    if (flag)
                                    {
                                        iblockstate2 = Blocks.HARDENED_CLAY.getDefaultState();
                                    }
                                    else
                                    {
                                        iblockstate2 = this.getBand(chunkX, j1, chunkZ);
                                    }
                                }
                                else
                                {
                                    iblockstate2 = Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE);
                                }
                                primer.setBlockState(l1, j1, k1, iblockstate2);
                            }
                            else
                            {
                                primer.setBlockState(l1, j1, k1, this.topBlock);
                                flag1 = true;
                            }
                        }
                        else
                        {
                            primer.setBlockState(l1, j1, k1, iblockstate3);

                            if (iblockstate3.getBlock() == Blocks.STAINED_HARDENED_CLAY)
                            {
                                primer.setBlockState(l1, j1, k1, Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE));
                            }
                        }
                    }
                    else if (l > 0)
                    {
                        --l;

                        if (flag1)
                        {
                            primer.setBlockState(l1, j1, k1, Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE));
                        }
                        else
                        {
                            primer.setBlockState(l1, j1, k1, this.getBand(chunkX, j1, chunkZ));
                        }
                    }
                    ++i1;
                }
            }
        }
    }

    private void generateBands(long seed)
    {
        this.clayBands = new IBlockState[64];
        Arrays.fill(this.clayBands, Blocks.HARDENED_CLAY.getDefaultState());
        Random random = new Random(seed);
        this.clayBandsOffsetNoise = new NoiseGeneratorPerlin(random, 1);

        for (int l1 = 0; l1 < 64; ++l1)
        {
            l1 += random.nextInt(5) + 1;

            if (l1 < 64)
            {
                this.clayBands[l1] = Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE);
            }
        }

        int i2 = random.nextInt(4) + 2;

        for (int i = 0; i < i2; ++i)
        {
            int j = random.nextInt(3) + 1;
            int k = random.nextInt(64);

            for (int l = 0; k + l < 64 && l < j; ++l)
            {
                this.clayBands[k + l] = Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
            }
        }

        int j2 = random.nextInt(4) + 2;

        for (int k2 = 0; k2 < j2; ++k2)
        {
            int i3 = random.nextInt(3) + 2;
            int l3 = random.nextInt(64);

            for (int i1 = 0; l3 + i1 < 64 && i1 < i3; ++i1)
            {
                this.clayBands[l3 + i1] = Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BROWN);
            }
        }

        int l2 = random.nextInt(4) + 2;

        for (int j3 = 0; j3 < l2; ++j3)
        {
            int i4 = random.nextInt(3) + 1;
            int k4 = random.nextInt(64);

            for (int j1 = 0; k4 + j1 < 64 && j1 < i4; ++j1)
            {
                this.clayBands[k4 + j1] = Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.RED);
            }
        }

        int k3 = random.nextInt(3) + 3;
        int j4 = 0;

        for (int l4 = 0; l4 < k3; ++l4)
        {
            j4 += random.nextInt(16) + 4;

            for (int k1 = 0; j4 + k1 < 64 && k1 < 1; ++k1)
            {
                this.clayBands[j4 + k1] = Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE);

                if (j4 + k1 > 1 && random.nextBoolean())
                {
                    this.clayBands[j4 + k1 - 1] = Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.SILVER);
                }
                if (j4 + k1 < 63 && random.nextBoolean())
                {
                    this.clayBands[j4 + k1 + 1] = Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.SILVER);
                }
            }
        }
    }

    private IBlockState getBand(int x, int y, int z)
    {
        int i = (int)Math.round(this.clayBandsOffsetNoise.getValue(x / 512.0D, z / 512.0D) * 2.0D);
        return this.clayBands[(y + i + 64) % 64];
    }
}