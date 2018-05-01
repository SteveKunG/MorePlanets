package stevekung.mods.moreplanets.module.planets.chalos.world.gen;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.*;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.dungeon.MapGenChalosDungeon;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.dungeon.RoomBossChalos;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.dungeon.RoomSpawnerChalos;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.dungeon.RoomTreasureChalos;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.RoomChestMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenSpaceDungeons;
import stevekung.mods.stevekunglib.world.gen.WorldGenLiquidLake;

public class ChunkGeneratorChalos implements IChunkGenerator
{
    private Random rand;
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    public NoiseGeneratorOctaves forestNoise;
    private World worldObj;
    private double[] heightMap;
    private float[] parabolicField;
    private double[] depthBuffer = new double[256];
    private MapGenBase caveGenerator = new MapGenChalosCave();
    private MapGenBase ravineGenerator = new MapGenChalosRavine();
    public BiomeDecoratorChalosOre biomedecoratorplanet = new BiomeDecoratorChalosOre();
    private Biome[] biomesForGeneration;
    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;

    private MapGenChalosDungeon dungeonGenerator = new MapGenChalosDungeon(new DungeonConfigurationMP(ChalosBlocks.CHALOS_DUNGEON_BRICK.getDefaultState(), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), Blocks.WEB.getDefaultState(), GCBlocks.unlitTorch.getDefaultState(), ChalosBlocks.CHALOS_ANCIENT_CHEST.getDefaultState(), 30, 8, 16, 7, 7, RoomBossChalos.class, RoomTreasureChalos.class, RoomSpawnerChalos.class, RoomChestMP.class));

    public ChunkGeneratorChalos(World world, long seed)
    {
        super();
        this.worldObj = world;
        this.rand = new Random(seed);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.heightMap = new double[825];
        this.parabolicField = new float[25];

        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt(i * i + j * j + 0.2F);
                this.parabolicField[i + 2 + (j + 2) * 5] = f;
            }
        }
        NoiseGenerator[] noiseGens = {this.minLimitPerlinNoise, this.maxLimitPerlinNoise, this.mainPerlinNoise, this.surfaceNoise, this.scaleNoise, this.depthNoise, this.forestNoise};
        this.minLimitPerlinNoise = (NoiseGeneratorOctaves)noiseGens[0];
        this.maxLimitPerlinNoise = (NoiseGeneratorOctaves)noiseGens[1];
        this.mainPerlinNoise = (NoiseGeneratorOctaves)noiseGens[2];
        this.surfaceNoise = (NoiseGeneratorPerlin)noiseGens[3];
        this.scaleNoise = (NoiseGeneratorOctaves)noiseGens[4];
        this.depthNoise = (NoiseGeneratorOctaves)noiseGens[5];
        this.forestNoise = (NoiseGeneratorOctaves)noiseGens[6];
    }

    public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
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
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * d0;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * d0;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * d0;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * d0;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, ChalosBlocks.CHALOS_ROCK.getDefaultState());
                                }
                                else if (i2 * 8 + j2 < 63)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, ChalosBlocks.CHEESE_MILK_FLUID_BLOCK.getDefaultState());
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

    public void replaceBlocksForBiome(int chunkX, int chunkZ, ChunkPrimer chunkPrimer, Biome[] biome)
    {
        double d0 = 0.03125D;
        this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, chunkX * 16, chunkZ * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                Biome Biome = biome[j + i * 16];
                Biome.genTerrainBlocks(this.worldObj, this.rand, chunkPrimer, chunkX * 16 + i, chunkZ * 16 + j, this.depthBuffer[j + i * 16]);
            }
        }
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ)
    {
        this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(chunkX, chunkZ, chunkprimer);
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
        this.replaceBlocksForBiome(chunkX, chunkZ, chunkprimer, this.biomesForGeneration);
        this.caveGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        this.ravineGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, chunkX, chunkZ);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    private void generateHeightmap(int chunkX, int chunkY, int chunkZ)
    {
        this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, chunkX, chunkZ, 5, 5, 200.0F, 200.0F, 0.5F);
        float f = 684.412F;
        float f1 = 684.412F;
        this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, chunkX, chunkY, chunkZ, 5, 33, 5, f / 80.0F, f1 / 160.0F, f / 80.0F);
        this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, chunkX, chunkY, chunkZ, 5, 33, 5, f, f1, f);
        this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, chunkX, chunkY, chunkZ, 5, 33, 5, f, f1, f);
        chunkZ = 0;
        chunkX = 0;
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];

                for (int j1 = -i1; j1 <= i1; ++j1)
                {
                    for (int k1 = -i1; k1 <= i1; ++k1)
                    {
                        Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = 0.0F + biome1.getBaseHeight() * 1.0F;
                        float f6 = 0.0F + biome1.getHeightVariation() * 1.0F;
                        float f7 = this.parabolicField[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

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
                    double d5 = MathHelper.clamp(d2, d3, d4) - d1;

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

    @Override
    public void populate(int chunkX, int chunkZ)
    {
        BlockFalling.fallInstantly = true;
        int x = chunkX * 16;
        int z = chunkZ * 16;
        BlockPos pos = new BlockPos(x, 0, z);
        Biome biomeGen = this.worldObj.getBiome(pos.add(16, 0, 16));
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkX * var7 + chunkZ * var9 ^ this.worldObj.getSeed());
        biomeGen.decorate(this.worldObj, this.rand, pos);
        this.biomedecoratorplanet.decorate(this.worldObj, this.rand, biomeGen, pos);
        WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biomeGen, x + 8, z + 8, 16, 16, this.rand);
        int worldX = chunkX << 4;
        int worldZ = chunkZ << 4;
        this.generateGas(this.worldObj, this.rand, worldX + 15, worldZ + 15);
        this.dungeonGenerator.generateStructure(this.worldObj, this.rand, new ChunkPos(chunkX, chunkZ));

        for (int i = 0; i < 8; ++i)
        {
            new WorldGenSpaceDungeons(ChalosBlocks.CHALOS_ANCIENT_CHEST.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), ChalosBlocks.CHEESE_SLIME_BLOCK.getDefaultState()).generate(this.worldObj, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        if (biomeGen != MPBiomes.SLIMELY_WASTELAND && this.rand.nextInt(8) == 0)
        {
            new WorldGenLiquidLake(ChalosBlocks.CHEESE_MILK_FLUID_BLOCK.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), false).generate(this.worldObj, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        BlockFalling.fallInstantly = false;
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        this.dungeonGenerator.generate(this.worldObj, chunkX, chunkZ, null);
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos)
    {
        return this.worldObj.getBiome(pos).getSpawnableList(type);
    }

    @Override
    public BlockPos getNearestStructurePos(World world, String structureName, BlockPos position, boolean findUnexplored)
    {
        return null;
    }

    @Override
    public boolean isInsideStructure(World world, String structureName, BlockPos pos)
    {
        return false;
    }

    @Override
    public boolean generateStructures(Chunk chunk, int x, int z)
    {
        return true;
    }

    private void generateGas(World world, Random rand, int xx, int zz)
    {
        BlockVec3 pos = new BlockVec3();

        if (this.gasPresent(world, rand, xx, zz, pos))
        {
            int x = pos.x;
            int cy = pos.y;
            int z = pos.z;
            int r = 1 + rand.nextInt(2);
            int r2 = r * r;

            for (int bx = -r; bx <= r; bx++)
            {
                for (int by = -r + 2; by <= r - 2; by++)
                {
                    for (int bz = -r; bz <= r; bz++)
                    {
                        int d2 = bx * bx + by * by * 3 + bz * bz;

                        if (d2 <= r2)
                        {
                            if (this.checkBlockGas(world, bx + x - 1, by + cy, bz + z))
                            {
                                continue;
                            }
                            if (this.checkBlockGas(world, bx + x + 1, by + cy, bz + z))
                            {
                                continue;
                            }
                            if (this.checkBlockGas(world, bx + x, by + cy - 1, bz + z))
                            {
                                continue;
                            }
                            if (this.checkBlockGas(world, bx + x, by + cy, bz + z - 1))
                            {
                                continue;
                            }
                            if (this.checkBlockGas(world, bx + x, by + cy, bz + z + 1))
                            {
                                continue;
                            }
                            world.setBlockState(new BlockPos(bx + x, by + cy, bz + z), ChalosBlocks.GASEOUS_CHEESE_MILK_BLOCK.getDefaultState(), 2);
                        }
                    }
                }
            }
        }
    }

    private boolean gasPresent(World world, Random rand, int x, int z, BlockVec3 pos)
    {
        rand.setSeed(world.getSeed());
        long i1 = rand.nextInt() / 2L * 2L + 1L;
        long j1 = rand.nextInt() / 2L * 2L + 1L;
        rand.setSeed(x * i1 + z * j1 ^ world.getSeed());
        double randMod = Math.min(0.2D, 0.08D);
        boolean flag1 = rand.nextDouble() <= randMod;
        boolean flag2 = rand.nextDouble() <= randMod;

        if (flag1 || flag2)
        {
            pos.y = 17 + rand.nextInt(10) + rand.nextInt(5);
            pos.x = x + 8 - rand.nextInt(16);
            pos.z = z + 8 - rand.nextInt(16);
            return true;
        }
        return false;
    }

    private boolean checkBlockGas(World world, int x, int y, int z)
    {
        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();

        if (world.getBlockState(new BlockPos(x, y, z)).getMaterial() == Material.AIR)
        {
            return true;
        }
        return block instanceof BlockLiquid && block != ChalosBlocks.GASEOUS_CHEESE_MILK_BLOCK;
    }
}