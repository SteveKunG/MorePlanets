package stevekung.mods.moreplanets.planets.chalos.world.gen;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.chalos.world.gen.dungeon.MapGenChalosDungeon;
import stevekung.mods.moreplanets.planets.chalos.world.gen.dungeon.RoomBossChalos;
import stevekung.mods.moreplanets.planets.chalos.world.gen.dungeon.RoomSpawnerChalos;
import stevekung.mods.moreplanets.planets.chalos.world.gen.dungeon.RoomTreasureChalos;
import stevekung.mods.moreplanets.utils.world.gen.ChunkGeneratorBaseMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.RoomChestMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenSpaceDungeons;
import stevekung.mods.stevekunglib.world.gen.MapGenCavesBase;
import stevekung.mods.stevekunglib.world.gen.MapGenRavineBase;
import stevekung.mods.stevekunglib.world.gen.WorldGenLiquidLake;

public class ChunkGeneratorChalos extends ChunkGeneratorBaseMP
{
    private final MapGenCavesBase caveGenerator = new MapGenCavesBase(MPBlocks.CHEESE_GRASS_BLOCK.getDefaultState(), Blocks.LAVA.getDefaultState(), Sets.newHashSet(MPBlocks.CHEESE_DIRT, MPBlocks.CHALOS_ROCK));
    private final MapGenRavineBase ravineGenerator = new MapGenRavineBase(MPBlocks.CHEESE_GRASS_BLOCK.getDefaultState(), Blocks.LAVA.getDefaultState(), Sets.newHashSet(MPBlocks.CHEESE_DIRT, MPBlocks.CHALOS_ROCK), Sets.newHashSet(MPBlocks.CHEESE_MILK_FLUID_BLOCK));
    private final BiomeDecoratorChalosOre decorator = new BiomeDecoratorChalosOre();
    private final MapGenCheeseSporeHutFeature cheeseSporeHutFeatureGenerator = new MapGenCheeseSporeHutFeature();
    private final MapGenChalosDungeon dungeonGenerator = new MapGenChalosDungeon(new DungeonConfigurationMP(MPBlocks.CHALOS_DUNGEON_BRICK.getDefaultState(), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), Blocks.WEB.getDefaultState(), GCBlocks.unlitTorch.getDefaultState(), MPBlocks.CHALOS_ANCIENT_CHEST.getDefaultState(), 30, 8, 16, 7, 7, RoomBossChalos.class, RoomTreasureChalos.class, RoomSpawnerChalos.class, RoomChestMP.class));

    public ChunkGeneratorChalos(World world, long seed)
    {
        super(world, seed);
        this.isSingleBiomePlanet = false;
    }

    @Override
    protected void preGenerateChunk(ChunkPrimer primer, int chunkX, int chunkZ) {}

    @Override
    protected void generateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        this.caveGenerator.generate(this.world, chunkX, chunkZ, primer);
        this.ravineGenerator.generate(this.world, chunkX, chunkZ, primer);
        this.cheeseSporeHutFeatureGenerator.generate(this.world, chunkX, chunkZ, primer);
    }

    @Override
    protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z)
    {
        biome.decorate(this.world, this.rand, pos);
        this.decorator.decorate(this.world, this.rand, biome, pos);
        int pocketX = chunkX << 4;
        int pocketZ = chunkZ << 4;
        ChunkGeneratorBaseMP.generatePocket(this.world, this.rand, pocketX + 15, pocketZ + 15, MPBlocks.GASEOUS_CHEESE_MILK_BLOCK.getDefaultState(), Sets.newHashSet(MPBiomes.SLIMELY_STREAM), 16 + this.rand.nextInt(16), 3 + this.rand.nextInt(2));
        this.dungeonGenerator.generateStructure(this.world, this.rand, new ChunkPos(chunkX, chunkZ));
        this.cheeseSporeHutFeatureGenerator.generateStructure(this.world, this.rand, new ChunkPos(chunkX, chunkZ));

        for (int i = 0; i < 8; ++i)
        {
            new WorldGenSpaceDungeons(MPBlocks.CHALOS_ANCIENT_CHEST.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), MPBlocks.CHEESE_SLIME_BLOCK.getDefaultState()).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        if (biome != MPBiomes.SLIMELY_STREAM && this.rand.nextInt(8) == 0)
        {
            new WorldGenLiquidLake(MPBlocks.CHEESE_MILK_FLUID_BLOCK.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), false).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
    }

    @Override
    protected IBlockState getTopBlock()
    {
        return Blocks.AIR.getDefaultState();
    }

    @Override
    protected IBlockState getSubBlock()
    {
        return Blocks.AIR.getDefaultState();
    }

    @Override
    protected IBlockState getStoneBlock()
    {
        return MPBlocks.CHALOS_ROCK.getDefaultState();
    }

    @Override
    protected IBlockState getLiquidBlock()
    {
        return MPBlocks.CHEESE_MILK_FLUID_BLOCK.getDefaultState();
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        this.dungeonGenerator.generate(this.world, chunkX, chunkZ, null);
        this.cheeseSporeHutFeatureGenerator.generate(this.world, chunkX, chunkZ, null);
    }

    @Override
    public boolean isInsideStructure(World world, String structureName, BlockPos pos)
    {
        return "CheeseSporeHut".equals(structureName) && this.cheeseSporeHutFeatureGenerator != null ? this.cheeseSporeHutFeatureGenerator.isInsideStructure(pos) : false;
    }

    @Override
    @Nullable
    public BlockPos getNearestStructurePos(World world, String structureName, BlockPos position, boolean findUnexplored)
    {
        return "CheeseSporeHut".equals(structureName) && this.cheeseSporeHutFeatureGenerator != null ? this.cheeseSporeHutFeatureGenerator.getNearestStructurePos(world, position, findUnexplored) : null;
    }
}