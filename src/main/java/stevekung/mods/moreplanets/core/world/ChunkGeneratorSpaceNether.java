package stevekung.mods.moreplanets.core.world;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.fml.common.eventhandler.Event;
import stevekung.mods.moreplanets.utils.world.gen.ChunkGeneratorBaseMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenGlowStone1;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenGlowStone2;

public class ChunkGeneratorSpaceNether extends ChunkGeneratorBaseMP
{
    private double[] slowsandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] depthBuffer = new double[256];
    private double[] buffer;
    private NoiseGeneratorOctaves lperlinNoise1;
    private NoiseGeneratorOctaves lperlinNoise2;
    private NoiseGeneratorOctaves perlinNoise1;
    private NoiseGeneratorOctaves slowsandGravelNoiseGen;
    private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    private final WorldGenFire fireFeature = new WorldGenFire();
    private final WorldGenGlowStone1 glowstoneGen1 = new WorldGenGlowStone1();
    private final WorldGenGlowStone2 glowstoneGen2 = new WorldGenGlowStone2();
    private final WorldGenerator quartzGen = new WorldGenMinable(Blocks.QUARTZ_ORE.getDefaultState(), 14, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenerator magmaGen = new WorldGenMinable(Blocks.MAGMA.getDefaultState(), 33, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenHellLava lavaTrapGen = new WorldGenHellLava(Blocks.FLOWING_LAVA, true);
    private final WorldGenHellLava hellSpringGen = new WorldGenHellLava(Blocks.FLOWING_LAVA, false);
    private final WorldGenBush brownMushroomFeature = new WorldGenBush(Blocks.BROWN_MUSHROOM);
    private final WorldGenBush redMushroomFeature = new WorldGenBush(Blocks.RED_MUSHROOM);
    private MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();
    private MapGenBase genNetherCaves = new MapGenCavesHell();
    double[] pnr;
    double[] ar;
    double[] br;
    double[] noiseData4;
    double[] dr;

    public ChunkGeneratorSpaceNether(World world, long seed)
    {
        super(world, seed);
        this.isSingleBiomePlanet = true;
        this.lperlinNoise1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.lperlinNoise2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
        this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
        this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        world.setSeaLevel(63);

        InitNoiseGensEvent.ContextHell ctx = new InitNoiseGensEvent.ContextHell(this.lperlinNoise1, this.lperlinNoise2, this.perlinNoise1, this.slowsandGravelNoiseGen, this.netherrackExculsivityNoiseGen, this.scaleNoise, this.depthNoise);
        ctx = TerrainGen.getModdedNoiseGenerators(world, this.rand, ctx);
        this.lperlinNoise1 = ctx.getLPerlin1();
        this.lperlinNoise2 = ctx.getLPerlin2();
        this.perlinNoise1 = ctx.getPerlin();
        this.slowsandGravelNoiseGen = ctx.getPerlin2();
        this.netherrackExculsivityNoiseGen = ctx.getPerlin3();
        this.scaleNoise = ctx.getScale();
        this.depthNoise = ctx.getDepth();
        this.genNetherBridge = (MapGenNetherBridge)TerrainGen.getModdedMapGen(this.genNetherBridge, InitMapGenEvent.EventType.NETHER_BRIDGE);
        this.genNetherCaves = TerrainGen.getModdedMapGen(this.genNetherCaves, InitMapGenEvent.EventType.NETHER_CAVE);
    }

    @Override
    protected void preGenerateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        boolean test = false;
        this.createCraters(chunkX, chunkZ, primer);

        if (test)
        {
            this.prepareHeights(chunkX, chunkZ, primer);
            this.buildSurfaces(chunkX, chunkZ, primer);
        }
    }

    @Override
    protected void generateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        this.genNetherCaves.generate(this.world, chunkX, chunkZ, primer);
        this.genNetherBridge.generate(this.world, chunkX, chunkZ, primer);
    }

    @Override
    protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z)
    {
        ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);
        this.genNetherBridge.generateStructure(this.world, this.rand, chunkpos);

        if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.NETHER_LAVA))
        {
            for (int i = 0; i < 8; ++i)
            {
                this.hellSpringGen.generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
            }
        }
        if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.FIRE))
        {
            for (int i = 0; i < this.rand.nextInt(this.rand.nextInt(10) + 1) + 1; ++i)
            {
                this.fireFeature.generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
            }
        }
        if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.GLOWSTONE))
        {
            for (int i = 0; i < this.rand.nextInt(this.rand.nextInt(10) + 1); ++i)
            {
                this.glowstoneGen1.generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
            }
            for (int i = 0; i < 10; ++i)
            {
                this.glowstoneGen2.generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
            }
        }

        ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.world, this.rand, chunkpos));

        if (TerrainGen.decorate(this.world, this.rand, chunkpos, DecorateBiomeEvent.Decorate.EventType.SHROOM))
        {
            if (this.rand.nextBoolean())
            {
                this.brownMushroomFeature.generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
            }
            if (this.rand.nextBoolean())
            {
                this.redMushroomFeature.generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
            }
        }

        if (TerrainGen.generateOre(this.world, this.rand, this.quartzGen, pos, OreGenEvent.GenerateMinable.EventType.QUARTZ))
        {
            for (int i = 0; i < 16; ++i)
            {
                this.quartzGen.generate(this.world, this.rand, pos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
            }
        }

        int i2 = this.world.getSeaLevel() / 2 + 1;

        if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.NETHER_MAGMA))
        {
            for (int i = 0; i < 4; ++i)
            {
                this.magmaGen.generate(this.world, this.rand, pos.add(this.rand.nextInt(16), i2 - 5 + this.rand.nextInt(10), this.rand.nextInt(16)));
            }
        }

        if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.NETHER_LAVA2))
        {
            for (int i = 0; i < 16; ++i)
            {
                this.lavaTrapGen.generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(108) + 10, this.rand.nextInt(16) + 8));
            }
        }
        biome.decorate(this.world, this.rand, new BlockPos(x, 0, z));
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.world, this.rand, chunkpos));
    }

    @Override
    protected IBlockState getTopBlock()
    {
        return Blocks.NETHERRACK.getDefaultState();
    }

    @Override
    protected IBlockState getSubBlock()
    {
        return Blocks.NETHERRACK.getDefaultState();
    }

    @Override
    protected IBlockState getStoneBlock()
    {
        return Blocks.NETHERRACK.getDefaultState();
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        if (creatureType == EnumCreatureType.MONSTER)
        {
            if (this.genNetherBridge.isInsideStructure(pos))
            {
                return this.genNetherBridge.getSpawnList();
            }
            if (this.genNetherBridge.isPositionInStructure(this.world, pos) && this.world.getBlockState(pos.down()).getBlock() == Blocks.NETHER_BRICK)
            {
                return this.genNetherBridge.getSpawnList();
            }
        }
        Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Override
    @Nullable
    public BlockPos getNearestStructurePos(World world, String name, BlockPos position, boolean findUnexplored)
    {
        return "Fortress".equals(name) && this.genNetherBridge != null ? this.genNetherBridge.getNearestStructurePos(world, position, findUnexplored) : null;
    }

    @Override
    public boolean isInsideStructure(World world, String name, BlockPos pos)
    {
        return "Fortress".equals(name) && this.genNetherBridge != null ? this.genNetherBridge.isInsideStructure(pos) : false;
    }

    @Override
    public void recreateStructures(Chunk chunk, int x, int z)
    {
        this.genNetherBridge.generate(this.world, x, z, null);
    }

    private void prepareHeights(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        int j = this.world.getSeaLevel() / 2 + 1;
        this.buffer = this.getHeights(this.buffer, chunkX * 4, 0, chunkZ * 4, 5, 17, 5);

        for (int j1 = 0; j1 < 4; ++j1)
        {
            for (int k1 = 0; k1 < 4; ++k1)
            {
                for (int l1 = 0; l1 < 16; ++l1)
                {
                    double d1 = this.buffer[((j1 + 0) * 5 + k1 + 0) * 17 + l1 + 0];
                    double d2 = this.buffer[((j1 + 0) * 5 + k1 + 1) * 17 + l1 + 0];
                    double d3 = this.buffer[((j1 + 1) * 5 + k1 + 0) * 17 + l1 + 0];
                    double d4 = this.buffer[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 0];
                    double d5 = (this.buffer[((j1 + 0) * 5 + k1 + 0) * 17 + l1 + 1] - d1) * 0.125D;
                    double d6 = (this.buffer[((j1 + 0) * 5 + k1 + 1) * 17 + l1 + 1] - d2) * 0.125D;
                    double d7 = (this.buffer[((j1 + 1) * 5 + k1 + 0) * 17 + l1 + 1] - d3) * 0.125D;
                    double d8 = (this.buffer[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 1] - d4) * 0.125D;

                    for (int i2 = 0; i2 < 8; ++i2)
                    {
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int j2 = 0; j2 < 4; ++j2)
                        {
                            double d15 = d10;
                            double d16 = (d11 - d10) * 0.25D;

                            for (int k2 = 0; k2 < 4; ++k2)
                            {
                                IBlockState iblockstate = null;

                                if (l1 * 8 + i2 < j)
                                {
                                    iblockstate = Blocks.LAVA.getDefaultState();
                                }
                                if (d15 > 0.0D)
                                {
                                    iblockstate = Blocks.NETHERRACK.getDefaultState();
                                }
                                int l2 = j2 + j1 * 4;
                                int i3 = i2 + l1 * 8;
                                int j3 = k2 + k1 * 4;
                                primer.setBlockState(l2, i3, j3, iblockstate);
                                d15 += d16;
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

    private void buildSurfaces(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer, this.world))
        {
            return;
        }
        int i = this.world.getSeaLevel() + 1;
        this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, 0.03125D, 0.03125D, 1.0D);
        this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, chunkX * 16, 109, chunkZ * 16, 16, 1, 16, 0.03125D, 1.0D, 0.03125D);
        this.depthBuffer = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.depthBuffer, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, 0.0625D, 0.0625D, 0.0625D);

        for (int j = 0; j < 16; ++j)
        {
            for (int k = 0; k < 16; ++k)
            {
                boolean flag = this.slowsandNoise[j + k * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.gravelNoise[j + k * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
                int l = (int)(this.depthBuffer[j + k * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int i1 = -1;
                IBlockState iblockstate = Blocks.NETHERRACK.getDefaultState();
                IBlockState iblockstate1 = Blocks.NETHERRACK.getDefaultState();

                for (int j1 = 127; j1 >= 0; --j1)
                {
                    if (j1 < 127 - this.rand.nextInt(5) && j1 > this.rand.nextInt(5))
                    {
                        IBlockState iblockstate2 = primer.getBlockState(k, j1, j);

                        if (iblockstate2.getBlock() != null && iblockstate2.getMaterial() != Material.AIR)
                        {
                            if (iblockstate2.getBlock() == Blocks.NETHERRACK)
                            {
                                if (i1 == -1)
                                {
                                    if (l <= 0)
                                    {
                                        iblockstate = Blocks.AIR.getDefaultState();
                                        iblockstate1 = Blocks.NETHERRACK.getDefaultState();
                                    }
                                    else if (j1 >= i - 4 && j1 <= i + 1)
                                    {
                                        iblockstate = Blocks.NETHERRACK.getDefaultState();
                                        iblockstate1 = Blocks.NETHERRACK.getDefaultState();

                                        if (flag1)
                                        {
                                            iblockstate = Blocks.GRAVEL.getDefaultState();
                                            iblockstate1 = Blocks.NETHERRACK.getDefaultState();
                                        }
                                        if (flag)
                                        {
                                            iblockstate = Blocks.SOUL_SAND.getDefaultState();
                                            iblockstate1 = Blocks.SOUL_SAND.getDefaultState();
                                        }
                                    }

                                    if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                                    {
                                        iblockstate = Blocks.LAVA.getDefaultState();
                                    }

                                    i1 = l;

                                    if (j1 >= i - 1)
                                    {
                                        primer.setBlockState(k, j1, j, iblockstate);
                                    }
                                    else
                                    {
                                        primer.setBlockState(k, j1, j, iblockstate1);
                                    }
                                }
                                else if (i1 > 0)
                                {
                                    --i1;
                                    primer.setBlockState(k, j1, j, iblockstate1);
                                }
                            }
                        }
                        else
                        {
                            i1 = -1;
                        }
                    }
                    else
                    {
                        primer.setBlockState(k, j1, j, Blocks.BEDROCK.getDefaultState());
                    }
                }
            }
        }
    }

    private double[] getHeights(double[] p_185938_1_, int p_185938_2_, int p_185938_3_, int p_185938_4_, int p_185938_5_, int p_185938_6_, int p_185938_7_)
    {
        if (p_185938_1_ == null)
        {
            p_185938_1_ = new double[p_185938_5_ * p_185938_6_ * p_185938_7_];
        }

        ChunkGeneratorEvent.InitNoiseField event = new ChunkGeneratorEvent.InitNoiseField(this, p_185938_1_, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.getResult() == Event.Result.DENY)
        {
            return event.getNoisefield();
        }

        this.noiseData4 = this.scaleNoise.generateNoiseOctaves(this.noiseData4, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 1.0D, 0.0D, 1.0D);
        this.dr = this.depthNoise.generateNoiseOctaves(this.dr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 100.0D, 0.0D, 100.0D);
        this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 8.555150000000001D, 34.2206D, 8.555150000000001D);
        this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 684.412D, 2053.236D, 684.412D);
        this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 684.412D, 2053.236D, 684.412D);
        int i = 0;
        double[] adouble = new double[p_185938_6_];

        for (int j = 0; j < p_185938_6_; ++j)
        {
            adouble[j] = Math.cos(j * Math.PI * 6.0D / p_185938_6_) * 2.0D;
            double d2 = j;

            if (j > p_185938_6_ / 2)
            {
                d2 = p_185938_6_ - 1 - j;
            }
            if (d2 < 4.0D)
            {
                d2 = 4.0D - d2;
                adouble[j] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (int l = 0; l < p_185938_5_; ++l)
        {
            for (int i1 = 0; i1 < p_185938_7_; ++i1)
            {
                for (int k = 0; k < p_185938_6_; ++k)
                {
                    double d4 = adouble[k];
                    double d5 = this.ar[i] / 512.0D;
                    double d6 = this.br[i] / 512.0D;
                    double d7 = (this.pnr[i] / 10.0D + 1.0D) / 2.0D;
                    double d8;

                    if (d7 < 0.0D)
                    {
                        d8 = d5;
                    }
                    else if (d7 > 1.0D)
                    {
                        d8 = d6;
                    }
                    else
                    {
                        d8 = d5 + (d6 - d5) * d7;
                    }

                    d8 = d8 - d4;

                    if (k > p_185938_6_ - 4)
                    {
                        double d9 = (k - (p_185938_6_ - 4)) / 3.0F;
                        d8 = d8 * (1.0D - d9) + -10.0D * d9;
                    }
                    if (k < 0.0D)
                    {
                        double d10 = (0.0D - k) / 4.0D;
                        d10 = MathHelper.clamp(d10, 0.0D, 1.0D);
                        d8 = d8 * (1.0D - d10) + -10.0D * d10;
                    }
                    p_185938_1_[i] = d8;
                    ++i;
                }
            }
        }
        return p_185938_1_;
    }
}