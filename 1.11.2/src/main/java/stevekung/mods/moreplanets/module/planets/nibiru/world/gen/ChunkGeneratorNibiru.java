package stevekung.mods.moreplanets.module.planets.nibiru.world.gen;

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
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.dungeon.*;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure.*;
import stevekung.mods.moreplanets.util.world.gen.dungeon.DungeonConfigurationMP;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenLiquidLakes;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenSpaceDungeons;

public class ChunkGeneratorNibiru implements IChunkGenerator
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
    private MapGenBase caveGenerator = new MapGenNibiruCaves();
    private MapGenNibiruStronghold strongholdGenerator = new MapGenNibiruStronghold();
    private MapGenNibiruVillage villageGenerator = new MapGenNibiruVillage();
    private MapGenNibiruMineshaft mineshaftGenerator = new MapGenNibiruMineshaft();
    private MapGenNibiruPyramid pyramidGenerator = new MapGenNibiruPyramid();
    private MapGenNibiruJungleTemple jungleTempleGenerator = new MapGenNibiruJungleTemple();
    private MapGenBase ravineGenerator = new MapGenNibiruRavine();
    private MapGenNibiruOceanMonument oceanMonumentGenerator = new MapGenNibiruOceanMonument();
    public BiomeDecoratorNibiruOre biomedecoratorplanet = new BiomeDecoratorNibiruOre();
    private MapGenNibiruDungeon dungeonGenerator = new MapGenNibiruDungeon(new DungeonConfigurationMP(NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_DUNGEON_BRICK), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), Blocks.WEB.getDefaultState(), NibiruBlocks.INFECTED_TORCH.getDefaultState(), NibiruBlocks.NIBIRU_ANCIENT_CHEST.getDefaultState(), 30, 8, 16, 7, 7, RoomBossNibiru.class, RoomTreasureNibiru.class, RoomSpawnerNibiru.class, RoomChestNibiru.class));
    private Biome[] biomesForGeneration;
    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;

    public ChunkGeneratorNibiru(World world, long seed)
    {
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
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, NibiruBlocks.NIBIRU_BLOCK.getDefaultState());
                                }
                                else if (i2 * 8 + j2 < 63)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState());
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
                Biome biomegenbase = biome[j + i * 16];
                biomegenbase.genTerrainBlocks(this.worldObj, this.rand, chunkPrimer, chunkX * 16 + i, chunkZ * 16 + j, this.depthBuffer[j + i * 16]);
            }
        }
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ)
    {
        this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(chunkX, chunkZ, chunkprimer);
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
        this.replaceBlocksForBiome(chunkX, chunkZ, chunkprimer, this.biomesForGeneration);
        this.caveGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        this.ravineGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        this.mineshaftGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        this.strongholdGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        this.pyramidGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        this.jungleTempleGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        this.oceanMonumentGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        this.villageGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        this.dungeonGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);

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
                Biome biomegenbase = this.biomesForGeneration[k + 2 + (l + 2) * 10];

                for (int j1 = -i1; j1 <= i1; ++j1)
                {
                    for (int k1 = -i1; k1 <= i1; ++k1)
                    {
                        Biome biomegenbase1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = 0.0F + biomegenbase1.getBaseHeight() * 1.0F;
                        float f6 = 0.0F + biomegenbase1.getHeightVariation() * 1.0F;
                        float f7 = this.parabolicField[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.getBaseHeight() > biomegenbase.getBaseHeight())
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
        BlockPos blockpos = new BlockPos(x, 0, z);
        Biome biomegenbase = this.worldObj.getBiome(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.worldObj.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkX * k + chunkZ * l ^ this.worldObj.getSeed());
        ChunkPos chunkcoordintpair = new ChunkPos(chunkX, chunkZ);
        this.biomedecoratorplanet.decorate(this.worldObj, this.rand, biomegenbase, blockpos);
        this.mineshaftGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        this.strongholdGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        this.pyramidGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        this.jungleTempleGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        this.oceanMonumentGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        this.villageGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        this.dungeonGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);

        if (biomegenbase != MPBiomes.INFECTED_OCEAN && biomegenbase != MPBiomes.INFECTED_DEEP_OCEAN && biomegenbase != MPBiomes.INFECTED_RIVER)
        {
            this.generateGas(this.worldObj, this.rand, chunkX << 4, chunkZ << 4);
            this.generateOil(this.worldObj, this.rand, chunkX << 4, chunkZ << 4);
        }
        if (biomegenbase != MPBiomes.INFECTED_DESERT && biomegenbase != MPBiomes.GREEN_VEIN && this.rand.nextInt(4) == 0)
        {
            new WorldGenLiquidLakes(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK, NibiruBlocks.NIBIRU_BLOCK, 0, false).generate(this.worldObj, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        if (biomegenbase == MPBiomes.GREEN_VEIN && this.rand.nextInt(6) == 0)
        {
            new WorldGenLiquidLakes(NibiruBlocks.PURIFY_WATER_FLUID_BLOCK, NibiruBlocks.TERRASTONE, 0, true).generate(this.worldObj, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        if (this.rand.nextInt(8) == 0)
        {
            int y = this.rand.nextInt(this.rand.nextInt(248) + 8);

            if (y < 63 || this.rand.nextInt(10) == 0)
            {
                new WorldGenLiquidLakes(Blocks.LAVA, NibiruBlocks.NIBIRU_BLOCK, 0, true).generate(this.worldObj, this.rand, blockpos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
            }
        }

        for (int i = 0; i < 8; ++i)
        {
            new WorldGenSpaceDungeons(NibiruBlocks.NIBIRU_ANCIENT_CHEST, NibiruBlocks.NIBIRU_BLOCK, NibiruBlocks.NIBIRU_BLOCK, 1, 2).generate(this.worldObj, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }

        biomegenbase.decorate(this.worldObj, this.rand, blockpos);
        WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biomegenbase, x + 8, z + 8, 16, 16, this.rand);

        blockpos = blockpos.add(8, 0, 8);

        for (int snowX = 0; snowX < 16; ++snowX)
        {
            for (int snowZ = 0; snowZ < 16; ++snowZ)
            {
                BlockPos blockpos1 = this.worldObj.getPrecipitationHeight(blockpos.add(snowX, 0, snowZ));
                BlockPos blockpos2 = blockpos1.down();

                if (this.worldObj.canBlockFreezeWater(blockpos2))
                {
                    this.worldObj.setBlockState(blockpos2, NibiruBlocks.INFECTED_ICE.getDefaultState(), 2);
                }
                if (this.worldObj.canSnowAt(blockpos1, true))
                {
                    this.worldObj.setBlockState(blockpos1, NibiruBlocks.INFECTED_SNOW_LAYER.getDefaultState(), 2);
                }
            }
        }
        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        boolean flag = false;

        if (chunk.getInhabitedTime() < 3600L)
        {
            flag |= this.oceanMonumentGenerator.generateStructure(this.worldObj, this.rand, new ChunkPos(chunkX, chunkZ));
        }
        return flag;
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos)
    {
        if (type == EnumCreatureType.MONSTER)
        {
            if (this.pyramidGenerator.canMobSpawn(pos))
            {
                return this.pyramidGenerator.getSpawnList();
            }
            if (this.jungleTempleGenerator.canMobSpawn(pos))
            {
                return this.jungleTempleGenerator.getSpawnList();
            }
            if (this.oceanMonumentGenerator.isPositionInStructure(this.worldObj, pos))
            {
                return this.oceanMonumentGenerator.getSpawnList();
            }
        }
        return this.worldObj.getBiome(pos).getSpawnableList(type);
    }

    @Override
    public BlockPos getStrongholdGen(World world, String structureName, BlockPos position)
    {
        return "NibiruStronghold".equals(structureName) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(world, position) : null;
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        this.mineshaftGenerator.generate(this.worldObj, chunkX, chunkZ, (ChunkPrimer)null);
        this.strongholdGenerator.generate(this.worldObj, chunkX, chunkZ, (ChunkPrimer)null);
        this.pyramidGenerator.generate(this.worldObj, chunkX, chunkZ, (ChunkPrimer)null);
        this.jungleTempleGenerator.generate(this.worldObj, chunkX, chunkZ, (ChunkPrimer)null);
        this.oceanMonumentGenerator.generate(this.worldObj, chunkX, chunkZ, (ChunkPrimer)null);
        this.villageGenerator.generate(this.worldObj, chunkX, chunkZ, (ChunkPrimer)null);
        this.dungeonGenerator.generate(this.worldObj, chunkX, chunkZ, null);
    }

    private void generateGas(World world, Random rand, int xx, int zz)
    {
        BlockVec3 pos = new BlockVec3();

        if (this.gasPresent(world, rand, xx, zz, pos))
        {
            int x = pos.x;
            int cy = pos.y;
            int z = pos.z;
            int r = 2 + rand.nextInt(2);
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
                            world.setBlockState(new BlockPos(bx + x, by + cy, bz + z), NibiruBlocks.HELIUM_GAS_BLOCK.getDefaultState(), 2);
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
            pos.y = 32 + rand.nextInt(10) + rand.nextInt(5);
            pos.x = x + rand.nextInt(16);
            pos.z = z + rand.nextInt(16);
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
        return block instanceof BlockLiquid && block != NibiruBlocks.HELIUM_GAS_BLOCK;
    }

    private void generateOil(World world, Random rand, int xx, int zz)
    {
        BlockVec3 pos = new BlockVec3();

        if (this.oilPresent(world, rand, xx, zz, pos))
        {
            int x = pos.x;
            int cy = pos.y;
            int z = pos.z;
            int r = 3 + rand.nextInt(5);
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
                            if (this.checkBlockOil(world, bx + x - 1, by + cy, bz + z))
                            {
                                continue;
                            }
                            if (this.checkBlockOil(world, bx + x + 1, by + cy, bz + z))
                            {
                                continue;
                            }
                            if (this.checkBlockOil(world, bx + x, by + cy - 1, bz + z))
                            {
                                continue;
                            }
                            if (this.checkBlockOil(world, bx + x, by + cy, bz + z - 1))
                            {
                                continue;
                            }
                            if (this.checkBlockOil(world, bx + x, by + cy, bz + z + 1))
                            {
                                continue;
                            }
                            world.setBlockState(new BlockPos(bx + x, by + cy, bz + z), GCBlocks.crudeOil.getDefaultState(), 2);
                        }
                    }
                }
            }
        }
    }

    private boolean oilPresent(World world, Random rand, int x, int z, BlockVec3 pos)
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
            pos.x = x + rand.nextInt(16);
            pos.z = z + rand.nextInt(16);
            return true;
        }
        return false;
    }

    private boolean checkBlockOil(World world, int x, int y, int z)
    {
        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();

        if (world.getBlockState(new BlockPos(x, y, z)).getMaterial() == Material.AIR)
        {
            return true;
        }
        return block instanceof BlockLiquid && block != GCBlocks.crudeOil;
    }
}