package stevekung.mods.moreplanets.module.planets.diona.world.gen;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.BlockDiona;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.world.gen.dungeon.MapGenDionaDungeon;
import stevekung.mods.moreplanets.module.planets.diona.world.gen.dungeon.RoomBossDiona;
import stevekung.mods.moreplanets.module.planets.diona.world.gen.dungeon.RoomSpawnerDiona;
import stevekung.mods.moreplanets.module.planets.diona.world.gen.dungeon.RoomTreasureDiona;
import stevekung.mods.moreplanets.module.planets.diona.world.gen.feature.WorldGenCrashedAlienProbe;
import stevekung.mods.moreplanets.module.planets.diona.world.gen.structure.MapGenDionaMineshaft;
import stevekung.mods.moreplanets.util.world.gen.ChunkProviderBaseMP;
import stevekung.mods.moreplanets.util.world.gen.MapGenCaveMP;
import stevekung.mods.moreplanets.util.world.gen.dungeon.DungeonConfigurationMP;
import stevekung.mods.moreplanets.util.world.gen.dungeon.RoomChestMP;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenLiquidLakes;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenSpaceDungeons;

public class ChunkProviderDiona extends ChunkProviderBaseMP
{
    private BiomeDecoratorDiona biomeDecorator = new BiomeDecoratorDiona();
    private Biome[] biomesForGeneration = { MPBiomes.DIONA };
    private MapGenCaveMP caveGenerator = new MapGenCaveMP(DionaBlocks.DIONA_BLOCK, DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK, this.getBlockMetadata());
    private MapGenDionaMineshaft mineshaftGenerator = new MapGenDionaMineshaft();
    private MapGenDionaDungeon dungeonGenerator = new MapGenDionaDungeon(new DungeonConfigurationMP(DionaBlocks.DIONA_BLOCK.getDefaultState().withProperty(BlockDiona.VARIANT, BlockDiona.BlockType.DIONA_DUNGEON_BRICK), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState(), DionaBlocks.INFECTED_CRYSTALLIZE_TORCH.getDefaultState(), DionaBlocks.DIONA_ANCIENT_CHEST.getDefaultState(), 30, 8, 16, 7, 7, RoomBossDiona.class, RoomTreasureDiona.class, RoomSpawnerDiona.class, RoomChestMP.class));

    public ChunkProviderDiona(World world, long seed)
    {
        super(world, seed);
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ)
    {
        ChunkPrimer primer = new ChunkPrimer();
        this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        this.generateTerrain(chunkX, chunkZ, primer);
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
        this.createCraters(chunkX, chunkZ, primer);
        this.replaceBiomeBlocks(chunkX, chunkZ, primer, this.biomesForGeneration);
        this.caveGenerator.generate(this.worldObj, chunkX, chunkZ, primer);
        this.mineshaftGenerator.generate(this.worldObj, chunkX, chunkZ, primer);
        this.dungeonGenerator.generate(this.worldObj, chunkX, chunkZ, primer);
        Chunk chunk = new Chunk(this.worldObj, primer, chunkX, chunkZ);
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
        Biome biomegenbase = this.worldObj.getBiome(pos.add(16, 0, 16));
        ChunkPos chunkcoordintpair = new ChunkPos(chunkX, chunkZ);
        this.rand.setSeed(this.worldObj.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkX * k + chunkZ * l ^ this.worldObj.getSeed());
        WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biomegenbase, x + 8, z + 8, 16, 16, this.rand);
        this.dungeonGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        this.biomeDecorator.decorate(this.worldObj, this.rand, biomegenbase, pos);
        this.mineshaftGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        int y = this.rand.nextInt(this.rand.nextInt(248) + 8);

        if (this.rand.nextInt(4) == 0)
        {
            if (y < 48)
            {
                new WorldGenLiquidLakes(DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK, DionaBlocks.DIONA_BLOCK, 2, false).generate(this.worldObj, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
            }
        }
        if (this.rand.nextInt(8) == 0)
        {
            if (y < 48)
            {
                new WorldGenLiquidLakes(DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK, DionaBlocks.DIONA_BLOCK, 2, true).generate(this.worldObj, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
            }
        }
        for (int i = 0; i < 8; ++i)
        {
            new WorldGenSpaceDungeons(DionaBlocks.DIONA_ANCIENT_CHEST, DionaBlocks.DIONA_BLOCK, DionaBlocks.ALBETIUS_WORM_EGG_ROCK, 0).generate(this.worldObj, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        if (this.rand.nextInt(250) == 0)
        {
            int i2 = this.rand.nextInt(16) + 8;
            int k3 = this.rand.nextInt(16) + 8;
            int l2 = this.worldObj.getTopSolidOrLiquidBlock(pos.add(i2, 0, k3)).getY();
            new WorldGenCrashedAlienProbe().generate(this.worldObj, this.rand, pos.add(i2, l2, k3));
        }
        BlockFalling.fallInstantly = false;
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos)
    {
        return this.worldObj.getBiome(pos).getSpawnableList(type);
    }

    @Override
    protected Block getBaseBlock()
    {
        return DionaBlocks.DIONA_BLOCK;
    }

    @Override
    protected int[] getBlockMetadata()
    {
        return new int[] { 0, 1, 2 };
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        this.mineshaftGenerator.generate(this.worldObj, chunkX, chunkZ, (ChunkPrimer)null);
        this.dungeonGenerator.generate(this.worldObj, chunkX, chunkZ, null);
    }
}