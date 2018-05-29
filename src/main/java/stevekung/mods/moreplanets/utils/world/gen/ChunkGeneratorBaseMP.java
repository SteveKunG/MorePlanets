package stevekung.mods.moreplanets.utils.world.gen;

import java.util.List;
import java.util.Random;
import java.util.Set;

import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.perlin.NoiseModule;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import micdoodle8.mods.galacticraft.core.world.gen.EnumCraterSize;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public abstract class ChunkGeneratorBaseMP implements IChunkGenerator
{
    // Planet gen
    private NoiseModule noise1;
    private NoiseModule noise2;
    private NoiseModule noise3;
    private NoiseModule noise4;

    // Default gen
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    private NoiseGeneratorOctaves scaleNoise;
    private NoiseGeneratorOctaves depthNoise;
    private NoiseGeneratorOctaves forestNoise;
    private double[] depthBuffer = new double[256];
    private double[] heightMap;
    private Biome[] biomesForGeneration;
    private double[] mainNoiseRegion;
    private double[] minLimitRegion;
    private double[] maxLimitRegion;
    private double[] depthRegion;
    private float[] biomeWeights;

    protected World world;
    protected Random rand;
    protected boolean isSingleBiomePlanet;

    public ChunkGeneratorBaseMP(World world, long seed)
    {
        this.world = world;
        this.rand = new Random(seed);
        this.isSingleBiomePlanet = true;

        // Planet gen
        this.noise1 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noise2 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noise3 = new Gradient(this.rand.nextLong(), 1, 0.25F);
        this.noise4 = new Gradient(this.rand.nextLong(), 1, 0.25F);

        // Default gen
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.heightMap = new double[825];
        this.biomeWeights = new float[25];

        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt(i * i + j * j + 0.2F);
                this.biomeWeights[i + 2 + (j + 2) * 5] = f;
            }
        }
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ)
    {
        this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        ChunkPrimer primer = new ChunkPrimer();

        if (this.isSingleBiomePlanet)
        {
            this.generateTerrain(chunkX, chunkZ, primer);
        }
        else
        {
            this.setBlocksInChunk(chunkX, chunkZ, primer);
        }

        this.preGenerateChunk(primer, chunkX, chunkZ);

        if (this.isSingleBiomePlanet)
        {
            this.replaceBiomeBlocks(chunkX, chunkZ, primer);
        }
        else
        {
            this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
            this.replaceBiomeBlocks(chunkX, chunkZ, primer, this.biomesForGeneration);
        }

        this.generateChunk(primer, chunkX, chunkZ);
        Chunk chunk = new Chunk(this.world, primer, chunkX, chunkZ);

        if (!this.isSingleBiomePlanet)
        {
            this.initBiomesArray(chunk);
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int chunkX, int chunkZ)
    {
        BlockFalling.fallInstantly = true;
        int x = chunkX * 16;
        int z = chunkZ * 16;
        BlockPos pos = new BlockPos(x, 0, z);
        Biome biome = this.world.getBiome(pos.add(16, 0, 16));
        this.rand.setSeed(this.world.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkX * k + chunkZ * l ^ this.world.getSeed());
        ChunkPos chunkpos = new ChunkPos(chunkX, chunkZ);
        this.populate(pos, chunkpos, biome, chunkX, chunkZ, x, z);
        BlockFalling.fallInstantly = false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos)
    {
        return this.world.getBiome(pos).getSpawnableList(type);
    }

    @Override
    public boolean generateStructures(Chunk chunk, int x, int z)
    {
        return false;
    }

    /** PLANET GEN **/

    protected int getCraterChance()
    {
        return 300;
    }

    protected int getTerrainHeight()
    {
        return 64;
    }

    protected void createCraters(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        for (int cx = chunkX - 2; cx <= chunkX + 2; cx++)
        {
            for (int cz = chunkZ - 2; cz <= chunkZ + 2; cz++)
            {
                for (int x = 0; x < 16; x++)
                {
                    for (int z = 0; z < 16; z++)
                    {
                        if (this.getCraterChance() > 0)
                        {
                            if (Math.abs(this.randFromPoint(cx * 16 + x, (cz * 16 + z) * 1000)) < this.noise4.getNoise(x * 16 + x, cz * 16 + z) / this.getCraterChance())
                            {
                                Random random = new Random(cx * 16 + x + (cz * 16 + z) * 5000);
                                EnumCraterSize cSize = EnumCraterSize.sizeArray[random.nextInt(EnumCraterSize.sizeArray.length)];
                                int size = random.nextInt(cSize.MAX_SIZE - cSize.MIN_SIZE) + cSize.MIN_SIZE;
                                this.makeCrater(cx * 16 + x, cz * 16 + z, chunkX * 16, chunkZ * 16, size, primer);
                            }
                        }
                    }
                }
            }
        }
    }

    private void replaceBiomeBlocks(int chunkX, int chunkZ, ChunkPrimer chunk)
    {
        int seaLevel = 20;

        for (int x = 0; x < 16; ++x)
        {
            for (int z = 0; z < 16; ++z)
            {
                int noise = (int) (this.noise4.getNoise(x + chunkX * 16, z * chunkZ * 16) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int j = -1;
                IBlockState topBlock = this.getTopBlock();
                IBlockState fillBlock = this.getSubBlock();

                for (int y = 255; y >= 0; --y)
                {
                    if (y <= 0 + this.rand.nextInt(5))
                    {
                        chunk.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());
                    }
                    else
                    {
                        IBlockState iblockstate2 = chunk.getBlockState(x, y, z);

                        if (iblockstate2.getMaterial() == Material.AIR)
                        {
                            j = -1;
                        }
                        else if (iblockstate2.getBlock() == this.getStoneBlock().getBlock())
                        {
                            if (j == -1)
                            {
                                if (noise <= 0)
                                {
                                    topBlock = Blocks.AIR.getDefaultState();
                                    fillBlock = this.getStoneBlock();
                                }
                                else if (y >= seaLevel - 4 && y <= seaLevel + 1)
                                {
                                    topBlock = this.getTopBlock();
                                    fillBlock = this.getSubBlock();
                                }

                                j = noise;

                                if (y >= seaLevel - 1)
                                {
                                    chunk.setBlockState(x, y, z, topBlock);
                                }
                                else if (y < seaLevel - 1 && y >= seaLevel - 2)
                                {
                                    chunk.setBlockState(x, y, z, fillBlock);
                                }
                            }
                            else if (j > 0)
                            {
                                --j;
                                chunk.setBlockState(x, y, z, fillBlock);
                            }
                        }
                    }
                }
            }
        }
    }

    private void generateTerrain(int chunkX, int chunkZ, ChunkPrimer chunk)
    {
        this.noise1.setFrequency(0.0125F);
        this.noise2.setFrequency(0.015F);
        this.noise3.setFrequency(0.01F);
        this.noise4.setFrequency(0.02F);

        for (int x = 0; x < 16; x++)
        {
            for (int z = 0; z < 16; z++)
            {
                double d = this.noise1.getNoise(x + chunkX * 16, z + chunkZ * 16) * 8;
                double d2 = this.noise2.getNoise(x + chunkX * 16, z + chunkZ * 16) * 24;
                double d3 = this.noise3.getNoise(x + chunkX * 16, z + chunkZ * 16) - 0.1;
                d3 *= 4;

                double yDev = 0;

                if (d3 < 0.0D)
                {
                    yDev = d;
                }
                else if (d3 > 1.0D)
                {
                    yDev = d2;
                }
                else
                {
                    yDev = d + (d2 - d) * d3;
                }

                for (int y = 0; y < 128; y++)
                {
                    if (y < this.getTerrainHeight() + yDev)
                    {
                        chunk.setBlockState(x, y, z, this.getStoneBlock());
                    }
                }
            }
        }
    }

    private void makeCrater(int craterX, int craterZ, int chunkX, int chunkZ, int size, ChunkPrimer primer)
    {
        for (int x = 0; x < 16; x++)
        {
            for (int z = 0; z < 16; z++)
            {
                double xDev = craterX - (chunkX + x);
                double zDev = craterZ - (chunkZ + z);

                if (xDev * xDev + zDev * zDev < size * size)
                {
                    xDev /= size;
                    zDev /= size;
                    double sqrtY = xDev * xDev + zDev * zDev;
                    double yDev = sqrtY * sqrtY * 6;
                    yDev = 5 - yDev;
                    int helper = 0;

                    for (int y = 127; y > 0; y--)
                    {
                        if (Blocks.AIR != primer.getBlockState(x, y, z).getBlock() && helper <= yDev)
                        {
                            primer.setBlockState(x, y, z, Blocks.AIR.getDefaultState());
                            helper++;
                        }
                        if (helper > yDev)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    private double randFromPoint(int x, int z)
    {
        int n;
        n = x + z * 57;
        n = n << 13 ^ n;
        return 1.0D - (n * (n * n * 15731 + 789221) + 1376312589 & 0x7fffffff) / 1073741824.0D;
    }

    /** PLANET GEN **/

    /** DEFAULT GEN **/

    protected void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
        this.generateHeightmap(chunkX * 4, 0, chunkZ * 4);

        for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d16 = (d11 - d10) * 0.25D;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.getStoneBlock());
                                }
                                else if (i2 * 8 + j2 < this.world.getSeaLevel())
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.getLiquidBlock());
                                }
                            }
                            d10 += d12;
                            d11 += d13;
                        }
                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    protected void replaceBiomeBlocks(int chunkX, int chunkZ, ChunkPrimer primer, Biome[] biomes)
    {
        this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, chunkX * 16, chunkZ * 16, 16, 16, 0.0625D, 0.0625D, 1.0D);

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                Biome biome = biomes[j + i * 16];
                biome.genTerrainBlocks(this.world, this.rand, primer, chunkX * 16 + i, chunkZ * 16 + j, this.depthBuffer[j + i * 16]);
            }
        }
    }

    protected void initBiomesArray(Chunk chunk)
    {
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
        }
    }

    private void generateHeightmap(int x, int y, int z)
    {
        this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, x, z, 5, 5, 200.0F, 200.0F, 0.5F);
        float f = 684.412F;
        float f1 = 684.412F;
        this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, x, y, z, 5, 33, 5, f / 80.0F, f1 / 160.0F, f / 80.0F);
        this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, x, y, z, 5, 33, 5, f, f1, f);
        this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, x, y, z, 5, 33, 5, f, f1, f);
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];

                for (int j1 = -2; j1 <= 2; ++j1)
                {
                    for (int k1 = -2; k1 <= 2; ++k1)
                    {
                        Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = biome1.getBaseHeight() * 1.0F;
                        float f6 = biome1.getHeightVariation() * 1.0F;
                        float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biome1.getBaseHeight() > biome.getBaseHeight())
                        {
                            f7 /= 2.0F;
                        }
                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.depthRegion[j] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }
                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                }
                else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }
                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = f3;
                double d9 = f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * 8.5F / 8.0D;
                double d0 = 8.5F + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1)
                {
                    double d1 = (l1 - d0) * 12.0F * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = this.minLimitRegion[i] / 512.0F;
                    double d3 = this.maxLimitRegion[i] / 512.0F;
                    double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

                    if (l1 > 29)
                    {
                        double d6 = (l1 - 29) / 3.0F;
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }
                    this.heightMap[i] = d5;
                    ++i;
                }
            }
        }
    }

    protected IBlockState getLiquidBlock()
    {
        return Blocks.WATER.getDefaultState();
    }

    /** DEFAULT GEN **/

    /** POCKET GEN **/

    protected static void generatePocket(World world, Random rand, int xx, int zz, IBlockState pocket, Set<Biome> ignoreBiomes)
    {
        ChunkGeneratorBaseMP.generatePocket(world, rand, xx, zz, pocket, ignoreBiomes, 17 + rand.nextInt(10) + rand.nextInt(5), 3 + rand.nextInt(5));
    }

    protected static void generatePocket(World world, Random rand, int xx, int zz, IBlockState pocket, Set<Biome> ignoreBiomes, int y, int radius)
    {
        BlockVec3 pos = new BlockVec3();

        if (ChunkGeneratorBaseMP.canGeneratePocket(world, rand, xx, zz, pos, ignoreBiomes, y))
        {
            int x = pos.x;
            int cy = pos.y;
            int z = pos.z;

            if (ChunkGeneratorBaseMP.checkPocketPresent(world, x, cy, z, radius, pocket))
            {
                return;
            }

            int r2 = radius * radius;

            for (int bx = -radius; bx <= radius; bx++)
            {
                for (int by = -radius + 2; by <= radius - 2; by++)
                {
                    int xySquared = bx * bx + by * by * 3;

                    for (int bz = -radius; bz <= radius; bz++)
                    {
                        if (xySquared + bz * bz <= r2)
                        {
                            if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x - 1, by + cy, bz + z), pocket))
                            {
                                continue;
                            }
                            if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x + 1, by + cy, bz + z), pocket))
                            {
                                continue;
                            }
                            if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x, by + cy - 1, bz + z), pocket))
                            {
                                continue;
                            }
                            if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x, by + cy, bz + z - 1), pocket))
                            {
                                continue;
                            }
                            if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x, by + cy, bz + z + 1), pocket))
                            {
                                continue;
                            }
                            if (ChunkGeneratorBaseMP.checkBlockAbove(world, new BlockPos(bx + x, by + cy + 1, bz + z)))
                            {
                                continue;
                            }
                            world.setBlockState(new BlockPos(bx + x, by + cy, bz + z), pocket, 2);
                        }
                    }
                }
            }
        }
    }

    private static boolean canGeneratePocket(World world, Random rand, int x, int z, BlockVec3 pos, Set<Biome> ignoreBiomes, int y)
    {
        Biome biome = world.getBiome(new BlockPos(x + 8, 0, z + 8));

        if (!ignoreBiomes.isEmpty())
        {
            for (Biome ignoreBiome : ignoreBiomes)
            {
                if (biome == ignoreBiome)
                {
                    return false;
                }
            }
        }

        rand.setSeed(world.getSeed());
        long i1 = rand.nextInt() / 2L * 2L + 1L;
        long j1 = rand.nextInt() / 2L * 2L + 1L;
        rand.setSeed(x * i1 + z * j1 ^ world.getSeed());

        double randMod = Math.min(0.2D, 0.05D * 1.8D);

        if (biome.getBaseHeight() >= 0.45F)
        {
            randMod /= 2;
        }
        if (biome.getBaseHeight() < -0.5F)
        {
            randMod *= 1.8;
        }

        boolean flag1 = rand.nextDouble() <= randMod;
        boolean flag2 = rand.nextDouble() <= randMod;

        if (flag1 || flag2)
        {
            pos.y = y;
            pos.x = x + 8 - rand.nextInt(16);
            pos.z = z + 8 - rand.nextInt(16);
            return true;
        }
        return false;
    }

    private static boolean checkBlock(World world, BlockPos pos, IBlockState pocket)
    {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (state.getMaterial() == Material.AIR)
        {
            return true;
        }
        return block instanceof BlockLiquid && block != pocket.getBlock();
    }

    private static boolean checkBlockAbove(World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();

        if (block instanceof BlockFalling)
        {
            return true;
        }
        return false;
    }

    private static boolean checkPocketPresent(World world, int x, int cy, int z, int r, IBlockState pocket)
    {
        int r2 = r * r;

        for (int bx = -r; bx <= r; bx++)
        {
            for (int by = -r + 2; by <= r - 2; by++)
            {
                int xySquared = bx * bx + by * by * 3;

                for (int bz = -r; bz <= r; bz++)
                {
                    if (xySquared + bz * bz <= r2)
                    {
                        if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x - 1, by + cy, bz + z), pocket))
                        {
                            continue;
                        }
                        if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x + 1, by + cy, bz + z), pocket))
                        {
                            continue;
                        }
                        if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x, by + cy - 1, bz + z), pocket))
                        {
                            continue;
                        }
                        if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x, by + cy, bz + z - 1), pocket))
                        {
                            continue;
                        }
                        if (ChunkGeneratorBaseMP.checkBlock(world, new BlockPos(bx + x, by + cy, bz + z + 1), pocket))
                        {
                            continue;
                        }
                        if (ChunkGeneratorBaseMP.checkBlockAbove(world, new BlockPos(bx + x, by + cy + 1, bz + z)))
                        {
                            continue;
                        }
                        if (world.getBlockState(new BlockPos(bx + x, by + cy, bz + z)).getBlock() == pocket.getBlock())
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /** POCKET GEN **/

    protected abstract void preGenerateChunk(ChunkPrimer primer, int chunkX, int chunkZ);
    protected abstract void generateChunk(ChunkPrimer primer, int chunkX, int chunkZ);
    protected abstract void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z);
    protected abstract IBlockState getTopBlock();
    protected abstract IBlockState getSubBlock();
    protected abstract IBlockState getStoneBlock();
}