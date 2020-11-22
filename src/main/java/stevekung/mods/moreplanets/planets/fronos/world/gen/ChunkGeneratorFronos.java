package stevekung.mods.moreplanets.planets.fronos.world.gen;

import java.util.List;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.world.gen.ChunkGeneratorBaseMP;
import stevekung.mods.stevekunglib.world.gen.MapGenCavesBase;
import stevekung.mods.stevekunglib.world.gen.MapGenRavineBase;
import stevekung.mods.stevekunglib.world.gen.WorldGenLiquidLake;

public class ChunkGeneratorFronos extends ChunkGeneratorBaseMP
{
    private final MapGenCavesBase caveGenerator = new MapGenCavesBase(Sets.newHashSet(MPBlocks.FRONOS_GRASS_BLOCK), Blocks.LAVA.getDefaultState(), Sets.newHashSet(MPBlocks.FRONOS_DIRT, MPBlocks.FRONOS_STONE), Sets.newHashSet(Blocks.WATER));
    private final MapGenRavineBase ravineGenerator = new MapGenRavineBase(Sets.newHashSet(MPBlocks.FRONOS_GRASS_BLOCK), Blocks.LAVA.getDefaultState(), Sets.newHashSet(MPBlocks.FRONOS_DIRT, MPBlocks.FRONOS_STONE), Sets.newHashSet(Blocks.WATER));
    //    private final MapGenNibiruStronghold strongholdGenerator = new MapGenNibiruStronghold();
    //    private final MapGenNibiruVillage villageGenerator = new MapGenNibiruVillage();
    //    private final MapGenNibiruMineshaft mineshaftGenerator = new MapGenNibiruMineshaft();
    //    private final MapGenNibiruPyramid pyramidGenerator = new MapGenNibiruPyramid();
    //    private final MapGenNibiruJungleTemple jungleTempleGenerator = new MapGenNibiruJungleTemple();
    //    private final MapGenNibiruOceanMonument oceanMonumentGenerator = new MapGenNibiruOceanMonument();
    //    private final MapGenNibiruIgloo iglooGenerator = new MapGenNibiruIgloo();
    private final BiomeDecoratorFronosOre decorator = new BiomeDecoratorFronosOre();
    //    private final MapGenNibiruDungeon dungeonGenerator = new MapGenNibiruDungeon(new DungeonConfigurationMP(MPBlocks.NIBIRU_DUNGEON_BRICK.getDefaultState(), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), Blocks.WEB.getDefaultState(), MPBlocks.INFECTED_TORCH.getDefaultState(), MPBlocks.NIBIRU_ANCIENT_CHEST.getDefaultState(), 30, 8, 16, 7, 7, RoomBossNibiru.class, RoomTreasureNibiru.class, RoomSpawnerNibiru.class, RoomChestNibiru.class));

    public ChunkGeneratorFronos(World world, long seed)
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
        //        this.mineshaftGenerator.generate(this.world, chunkX, chunkZ, primer);
        //        this.strongholdGenerator.generate(this.world, chunkX, chunkZ, primer);
        //        this.pyramidGenerator.generate(this.world, chunkX, chunkZ, primer);
        //        this.jungleTempleGenerator.generate(this.world, chunkX, chunkZ, primer);
        //        this.oceanMonumentGenerator.generate(this.world, chunkX, chunkZ, primer);
        //        this.villageGenerator.generate(this.world, chunkX, chunkZ, primer);
        //        this.dungeonGenerator.generate(this.world, chunkX, chunkZ, primer);
        //        this.iglooGenerator.generate(this.world, chunkX, chunkZ, primer);
    }

    @Override
    protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z)
    {
        this.decorator.decorate(this.world, this.rand, biome, pos);
        //        this.mineshaftGenerator.generateStructure(this.world, this.rand, chunkpos);
        //        this.strongholdGenerator.generateStructure(this.world, this.rand, chunkpos);
        //        this.pyramidGenerator.generateStructure(this.world, this.rand, chunkpos);
        //        this.jungleTempleGenerator.generateStructure(this.world, this.rand, chunkpos);
        //        this.oceanMonumentGenerator.generateStructure(this.world, this.rand, chunkpos);
        //        this.villageGenerator.generateStructure(this.world, this.rand, chunkpos);
        //        this.dungeonGenerator.generateStructure(this.world, this.rand, chunkpos);
        //        this.iglooGenerator.generateStructure(this.world, this.rand, chunkpos);

        if (/*biome != MPBiomes.INFECTED_DESERT && biome != MPBiomes.GREEN_VEIN_FIELDS && */this.rand.nextInt(4) == 0)
        {
            new WorldGenLiquidLake(Blocks.WATER.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), false).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        if (this.rand.nextInt(8) == 0)
        {
            int y = this.rand.nextInt(this.rand.nextInt(248) + 8);

            if (y < 63 || this.rand.nextInt(10) == 0)
            {
                new WorldGenLiquidLake(Blocks.LAVA.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), true).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
            }
        }

        //        for (int i = 0; i < 8; ++i)
        //        {
        //            new WorldGenSpaceDungeons(MPBlocks.NIBIRU_ANCIENT_CHEST.getDefaultState(), MPBlocks.NIBIRU_COBBLESTONE.getDefaultState(), MPBlocks.NIBIRU_VEIN_COBBLESTONE.getDefaultState()).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        //        }

        biome.decorate(this.world, this.rand, pos);
        //        pos = pos.add(8, 0, 8);
        //
        //        for (int snowX = 0; snowX < 16; ++snowX)
        //        {
        //            for (int snowZ = 0; snowZ < 16; ++snowZ)
        //            {
        //                BlockPos blockpos1 = this.world.getPrecipitationHeight(pos.add(snowX, 0, snowZ));
        //                BlockPos blockpos2 = blockpos1.down();
        //
        //                if (this.world.canBlockFreezeWater(blockpos2))
        //                {
        //                    this.world.setBlockState(blockpos2, MPBlocks.INFECTED_ICE.getDefaultState(), 2);
        //                }
        //                if (this.world.canSnowAt(blockpos1, true))
        //                {
        //                    Biome biome2 = this.world.getBiome(blockpos1);
        //                    this.world.setBlockState(blockpos1, biome2 == MPBiomes.COLD_GREEN_VEIN_MOUTAINS ? MPBlocks.PURIFIED_SNOW_LAYER.getDefaultState() : MPBlocks.INFECTED_SNOW_LAYER.getDefaultState(), 2);
        //                }
        //            }
        //        }
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
        return MPBlocks.FRONOS_STONE.getDefaultState();
    }

    @Override
    protected IBlockState getLiquidBlock()
    {
        return Blocks.WATER.getDefaultState();
    }

    @Override
    public boolean generateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        boolean flag = false;

        //        if (chunk.getInhabitedTime() < 3600L)
        //        {
        //            flag |= this.oceanMonumentGenerator.generateStructure(this.world, this.rand, new ChunkPos(chunkX, chunkZ));
        //        }
        return flag;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos)
    {
        //        if (type == EnumCreatureType.MONSTER)
        //        {
        //            if (this.pyramidGenerator.canMobSpawn(pos))
        //            {
        //                return this.pyramidGenerator.getSpawnList();
        //            }
        //            if (this.jungleTempleGenerator.canMobSpawn(pos))
        //            {
        //                return this.jungleTempleGenerator.getSpawnList();
        //            }
        //            if (this.oceanMonumentGenerator.isPositionInStructure(this.world, pos))
        //            {
        //                return this.oceanMonumentGenerator.getSpawnList();
        //            }
        //        }
        return super.getPossibleCreatures(type, pos);
    }

    @Override
    public BlockPos getNearestStructurePos(World world, String name, BlockPos pos, boolean findUnexplored)
    {
        return null;
        //        if ("NibiruStronghold".equals(name) && this.strongholdGenerator != null)
        //        {
        //            return this.strongholdGenerator.getNearestStructurePos(world, pos, findUnexplored);
        //        }
        //        else if ("NibiruOceanMonument".equals(name) && this.oceanMonumentGenerator != null)
        //        {
        //            return this.oceanMonumentGenerator.getNearestStructurePos(world, pos, findUnexplored);
        //        }
        //        else if ("NibiruVillage".equals(name) && this.villageGenerator != null)
        //        {
        //            return this.villageGenerator.getNearestStructurePos(world, pos, findUnexplored);
        //        }
        //        else if ("NibiruMineshaft".equals(name) && this.mineshaftGenerator != null)
        //        {
        //            return this.mineshaftGenerator.getNearestStructurePos(world, pos, findUnexplored);
        //        }
        //        else if ("NibiruBossDungeon".equals(name) && this.dungeonGenerator != null)
        //        {
        //            return this.dungeonGenerator.getNearestStructurePos(world, pos, findUnexplored);
        //        }
        //        else if ("NibiruIgloo".equals(name) && this.iglooGenerator != null)
        //        {
        //            return this.iglooGenerator.getNearestStructurePos(world, pos, findUnexplored);
        //        }
        //        else if ("NibiruPyramid".equals(name) && this.pyramidGenerator != null)
        //        {
        //            return this.pyramidGenerator.getNearestStructurePos(world, pos, findUnexplored);
        //        }
        //        else
        //        {
        //            return "NibiruJungleTemple".equals(name) && this.jungleTempleGenerator != null ? this.jungleTempleGenerator.getNearestStructurePos(world, pos, findUnexplored) : null;
        //        }
    }

    @Override
    public boolean isInsideStructure(World world, String name, BlockPos pos)
    {
        return false;
        //        if ("NibiruStronghold".equals(name) && this.strongholdGenerator != null)
        //        {
        //            return this.strongholdGenerator.isInsideStructure(pos);
        //        }
        //        else if ("NibiruOceanMonument".equals(name) && this.oceanMonumentGenerator != null)
        //        {
        //            return this.oceanMonumentGenerator.isInsideStructure(pos);
        //        }
        //        else if ("NibiruVillage".equals(name) && this.villageGenerator != null)
        //        {
        //            return this.villageGenerator.isInsideStructure(pos);
        //        }
        //        else if ("NibiruMineshaft".equals(name) && this.mineshaftGenerator != null)
        //        {
        //            return this.mineshaftGenerator.isInsideStructure(pos);
        //        }
        //        else if ("NibiruBossDungeon".equals(name) && this.dungeonGenerator != null)
        //        {
        //            return this.dungeonGenerator.isInsideStructure(pos);
        //        }
        //        else if ("NibiruIgloo".equals(name) && this.iglooGenerator != null)
        //        {
        //            return this.iglooGenerator.isInsideStructure(pos);
        //        }
        //        else if ("NibiruPyramid".equals(name) && this.pyramidGenerator != null)
        //        {
        //            return this.pyramidGenerator.isInsideStructure(pos);
        //        }
        //        else
        //        {
        //            return "NibiruJungleTemple".equals(name) && this.jungleTempleGenerator != null ? this.jungleTempleGenerator.isInsideStructure(pos) : false;
        //        }
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        //        this.mineshaftGenerator.generate(this.world, chunkX, chunkZ, null);
        //        this.strongholdGenerator.generate(this.world, chunkX, chunkZ, null);
        //        this.pyramidGenerator.generate(this.world, chunkX, chunkZ, null);
        //        this.jungleTempleGenerator.generate(this.world, chunkX, chunkZ, null);
        //        this.oceanMonumentGenerator.generate(this.world, chunkX, chunkZ, null);
        //        this.villageGenerator.generate(this.world, chunkX, chunkZ, null);
        //        this.dungeonGenerator.generate(this.world, chunkX, chunkZ, null);
        //        this.iglooGenerator.generate(this.world, chunkX, chunkZ, null);
    }
}