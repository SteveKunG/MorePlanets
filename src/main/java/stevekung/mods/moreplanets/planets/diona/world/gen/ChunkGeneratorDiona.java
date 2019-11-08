package stevekung.mods.moreplanets.planets.diona.world.gen;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.diona.world.gen.dungeon.MapGenDionaDungeon;
import stevekung.mods.moreplanets.planets.diona.world.gen.dungeon.RoomBossDiona;
import stevekung.mods.moreplanets.planets.diona.world.gen.dungeon.RoomSpawnerDiona;
import stevekung.mods.moreplanets.planets.diona.world.gen.dungeon.RoomTreasureDiona;
import stevekung.mods.moreplanets.planets.diona.world.gen.feature.WorldGenCrashedAlienProbe;
import stevekung.mods.moreplanets.planets.diona.world.gen.structure.MapGenDionaMineshaft;
import stevekung.mods.moreplanets.utils.world.gen.ChunkGeneratorBaseMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.RoomChestMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenSpaceDungeons;
import stevekung.mods.stevekunglib.world.gen.MapGenCavesBase;
import stevekung.mods.stevekunglib.world.gen.WorldGenLiquidLake;

public class ChunkGeneratorDiona extends ChunkGeneratorBaseMP
{
    private final BiomeDecoratorDiona decorator = new BiomeDecoratorDiona();
    private final MapGenCavesBase caveGenerator = new MapGenCavesBase(MPBlocks.DIONA_SURFACE_ROCK.getDefaultState(), MPBlocks.INFECTED_PURLONITE_LAVA_FLUID_BLOCK.getDefaultState(), Sets.newHashSet(MPBlocks.DIONA_SUB_SURFACE_ROCK, MPBlocks.DIONA_ROCK));
    private final MapGenDionaMineshaft mineshaftGenerator = new MapGenDionaMineshaft();
    private final MapGenDionaDungeon dungeonGenerator = new MapGenDionaDungeon(new DungeonConfigurationMP(MPBlocks.DIONA_DUNGEON_BRICK.getDefaultState(), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), MPBlocks.INFECTED_PURLONITE_COBWEB.getDefaultState(), MPBlocks.INFECTED_PURLONITE_TORCH.getDefaultState(), MPBlocks.DIONA_ANCIENT_CHEST.getDefaultState(), 30, 8, 16, 7, 7, RoomBossDiona.class, RoomTreasureDiona.class, RoomSpawnerDiona.class, RoomChestMP.class));
    private final MapGenCrashedAlienShipFeature alienShipFeatureGenerator = new MapGenCrashedAlienShipFeature();

    public ChunkGeneratorDiona(World world, long seed)
    {
        super(world, seed);
    }

    @Override
    protected void preGenerateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        this.createCraters(chunkX, chunkZ, primer);
    }

    @Override
    protected void generateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        this.caveGenerator.generate(this.world, chunkX, chunkZ, primer);
        this.mineshaftGenerator.generate(this.world, chunkX, chunkZ, primer);
        this.dungeonGenerator.generate(this.world, chunkX, chunkZ, primer);
        this.alienShipFeatureGenerator.generate(this.world, chunkX, chunkZ, primer);
    }

    @Override
    protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z)
    {
        int y = this.rand.nextInt(this.rand.nextInt(248) + 8);
        this.decorator.decorate(this.world, this.rand, biome, pos);
        this.dungeonGenerator.generateStructure(this.world, this.rand, chunkpos);
        this.mineshaftGenerator.generateStructure(this.world, this.rand, chunkpos);
        this.alienShipFeatureGenerator.generateStructure(this.world, this.rand, chunkpos);

        if (this.rand.nextInt(4) == 0)
        {
            if (y < 48)
            {
                new WorldGenLiquidLake(MPBlocks.INFECTED_PURLONITE_WATER_FLUID_BLOCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), false).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
            }
        }
        if (this.rand.nextInt(8) == 0)
        {
            if (y < 48)
            {
                new WorldGenLiquidLake(MPBlocks.INFECTED_PURLONITE_LAVA_FLUID_BLOCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), true).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
            }
        }
        for (int i = 0; i < 8; ++i)
        {
            new WorldGenSpaceDungeons(MPBlocks.DIONA_ANCIENT_CHEST.getDefaultState(), MPBlocks.DIONA_COBBLESTONE.getDefaultState(), MPBlocks.ALBETIUS_WORM_EGG_ROCK.getDefaultState()).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        if (this.rand.nextInt(250) == 0)
        {
            int posX = this.rand.nextInt(16) + 8;
            int posZ = this.rand.nextInt(16) + 8;
            int posY = this.world.getTopSolidOrLiquidBlock(pos.add(posX, 0, posZ)).getY();
            new WorldGenCrashedAlienProbe().generate(this.world, this.rand, pos.add(posX, posY, posZ));
        }
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        this.mineshaftGenerator.generate(this.world, chunkX, chunkZ, null);
        this.dungeonGenerator.generate(this.world, chunkX, chunkZ, null);
        this.alienShipFeatureGenerator.generate(this.world, chunkX, chunkZ, null);
    }

    @Override
    public boolean isInsideStructure(World world, String name, BlockPos pos)
    {
        if ("DionaMineshaft".equals(name) && this.mineshaftGenerator != null)
        {
            return this.mineshaftGenerator.isInsideStructure(pos);
        }
        else
        {
            return "AlienShip".equals(name) && this.alienShipFeatureGenerator != null ? this.alienShipFeatureGenerator.isInsideStructure(pos.add(0, 4, 0)) : false;
        }
    }

    @Override
    @Nullable
    public BlockPos getNearestStructurePos(World world, String name, BlockPos pos, boolean findUnexplored)
    {
        if ("DionaMineshaft".equals(name) && this.mineshaftGenerator != null)
        {
            return this.mineshaftGenerator.getNearestStructurePos(world, pos, findUnexplored);
        }
        else
        {
            return "AlienShip".equals(name) && this.alienShipFeatureGenerator != null ? this.alienShipFeatureGenerator.getNearestStructurePos(world, pos, findUnexplored) : null;
        }
    }

    @Override
    protected IBlockState getTopBlock()
    {
        return MPBlocks.DIONA_SURFACE_ROCK.getDefaultState();
    }

    @Override
    protected IBlockState getSubBlock()
    {
        return MPBlocks.DIONA_SUB_SURFACE_ROCK.getDefaultState();
    }

    @Override
    protected IBlockState getStoneBlock()
    {
        return MPBlocks.DIONA_ROCK.getDefaultState();
    }
}