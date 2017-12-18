/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.worldgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.perlin.NoiseModule;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import micdoodle8.mods.galacticraft.core.world.gen.EnumCraterSize;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;
import stevekung.mods.moreplanets.core.entities.EntityEvolvedWitch;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.worldgen.MapGenCaveMP;
import stevekung.mods.moreplanets.core.worldgen.dungeon.RoomEmptyMP;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenSpaceDungeons;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenSplashBlock;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityGiantWorm;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedZombie;
import stevekung.mods.moreplanets.planets.nibiru.worldgen.dungeon.RoomBossNibiru;
import stevekung.mods.moreplanets.planets.nibiru.worldgen.dungeon.RoomChestsNibiru;
import stevekung.mods.moreplanets.planets.nibiru.worldgen.dungeon.RoomSpawnerNibiru;
import stevekung.mods.moreplanets.planets.nibiru.worldgen.dungeon.RoomTreasureNibiru;
import stevekung.mods.moreplanets.planets.nibiru.worldgen.tree.WorldGenNibiruFruitTree;

public class ChunkProviderNibiru extends ChunkProviderGenerate
{
    Block topBlockID = NibiruBlocks.nibiru_block;
    byte topBlockMeta = 0;
    Block fillBlockID = NibiruBlocks.nibiru_block;
    byte fillBlockMeta = 1;
    Block lowerBlockID = NibiruBlocks.nibiru_block;
    byte lowerBlockMeta = 2;

    private Random rand;

    private Gradient noiseGen1;
    private Gradient noiseGen2;
    private Gradient noiseGen3;
    private Gradient noiseGen4;
    private Gradient noiseGen5;
    private Gradient noiseGen6;
    private Gradient noiseGen7;
    private NoiseModule noiseCraterGen;

    private World worldObj;
    public BiomeDecoratorNibiru biomedecoratorplanet = new BiomeDecoratorNibiru();
    private MapGenCaveMP caveGenerator = new MapGenCaveMP(NibiruBlocks.nibiru_block);
    private MapGenCavernNibiru cavernGenerator = new MapGenCavernNibiru();
    private MapGenNibiruRavine ravineGenerator = new MapGenNibiruRavine();
    private MapGenDungeon dungeonGenerator = new MapGenDungeon(NibiruBlocks.nibiru_block, 12, 8, 24, 4);

    {
        this.dungeonGenerator.otherRooms.add(new RoomEmptyMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomChestsNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomChestsNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.bossRooms.add(new RoomBossNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.treasureRooms.add(new RoomTreasureNibiru(null, 0, 0, 0, ForgeDirection.UNKNOWN));
    }

    private BiomeGenBase[] biomesForGeneration = { BiomeGenBaseNibiru.nibiru };

    private static double TERRAIN_HEIGHT_MOD = 18;
    private static double SMALL_FEATURE_HEIGHT_MOD = 36;
    private static double MOUNTAIN_HEIGHT_MOD = 120;
    private static double VALLEY_HEIGHT_MOD = 64;
    private static int CRATER_PROB = 300;

    // DO NOT CHANGE
    private static int MID_HEIGHT = 86;
    private static int CHUNK_SIZE_X = 16;
    private static int CHUNK_SIZE_Y = 256;
    private static int CHUNK_SIZE_Z = 16;
    private static double MAIN_FEATURE_FILTER_MOD = 8;
    private static double LARGE_FEATURE_FILTER_MOD = 12;
    private static double SMALL_FEATURE_FILTER_MOD = 12;

    public ChunkProviderNibiru(World par1World, long par2, boolean par4)
    {
        super(par1World, par2, par4);
        this.worldObj = par1World;
        this.rand = new Random(par2);

        this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noiseGen3 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noiseGen4 = new Gradient(this.rand.nextLong(), 2, 0.25F);
        this.noiseGen5 = new Gradient(this.rand.nextLong(), 1, 0.25F);
        this.noiseGen6 = new Gradient(this.rand.nextLong(), 1, 0.25F);
        this.noiseGen7 = new Gradient(this.rand.nextLong(), 1, 0.25F);
        this.noiseCraterGen = new Gradient(this.rand.nextLong(), 1, 0.25F);
    }

    public void generateTerrain(int chunkX, int chunkZ, Block[] idArray, byte[] metaArray)
    {
        this.noiseGen1.setFrequency(0.0125F);
        this.noiseGen2.setFrequency(0.015F);
        this.noiseGen3.setFrequency(0.01F);
        this.noiseGen4.setFrequency(0.02F);
        this.noiseGen5.setFrequency(0.01F);
        this.noiseGen6.setFrequency(0.001F);
        this.noiseGen7.setFrequency(0.005F);
        this.noiseCraterGen.setFrequency(0.02F);

        for (int x = 0; x < ChunkProviderNibiru.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderNibiru.CHUNK_SIZE_Z; z++)
            {
                double baseHeight = this.noiseGen1.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderNibiru.TERRAIN_HEIGHT_MOD;
                double smallHillHeight = this.noiseGen2.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderNibiru.SMALL_FEATURE_HEIGHT_MOD;
                double mountainHeight = Math.abs(this.noiseGen3.getNoise(chunkX * 16 + x, chunkZ * 16 + z));
                double valleyHeight = Math.abs(this.noiseGen4.getNoise(chunkX * 16 + x, chunkZ * 16 + z));
                double featureFilter = this.noiseGen5.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderNibiru.MAIN_FEATURE_FILTER_MOD;
                double largeFilter = this.noiseGen6.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderNibiru.LARGE_FEATURE_FILTER_MOD;
                double smallFilter = this.noiseGen7.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderNibiru.SMALL_FEATURE_FILTER_MOD - 0.5;
                mountainHeight = this.lerp(smallHillHeight, mountainHeight * ChunkProviderNibiru.MOUNTAIN_HEIGHT_MOD, this.fade(this.clamp(mountainHeight * 2, 0, 1)));
                valleyHeight = this.lerp(smallHillHeight, valleyHeight * ChunkProviderNibiru.VALLEY_HEIGHT_MOD - ChunkProviderNibiru.VALLEY_HEIGHT_MOD + 9, this.fade(this.clamp((valleyHeight + 2) * 4, 0, 1)));

                double yDev = this.lerp(valleyHeight, mountainHeight, this.fade(largeFilter));
                yDev = this.lerp(smallHillHeight, yDev, smallFilter);
                yDev = this.lerp(baseHeight, yDev, featureFilter);

                for (int y = 0; y < ChunkProviderNibiru.CHUNK_SIZE_Y; y++)
                {
                    if (y < ChunkProviderNibiru.MID_HEIGHT + yDev)
                    {
                        idArray[this.getIndex(x, y, z)] = this.lowerBlockID;
                        metaArray[this.getIndex(x, y, z)] = this.lowerBlockMeta;
                    }
                }
            }
        }
    }

    private double lerp(double d1, double d2, double t)
    {
        if (t < 0.0)
        {
            return d1;
        }
        else if (t > 1.0)
        {
            return d2;
        }
        else
        {
            return d1 + (d2 - d1) * t;
        }
    }

    private double fade(double n)
    {
        return n * n * n * (n * (n * 6 - 15) + 10);
    }

    private double clamp(double x, double min, double max)
    {
        if (x < min)
        {
            return min;
        }
        if (x > max)
        {
            return max;
        }
        return x;
    }

    @Override
    public void replaceBlocksForBiome(int par1, int par2, Block[] arrayOfIDs, byte[] arrayOfMeta, BiomeGenBase[] par4ArrayOfBiomeGenBase)
    {
        int var5 = 20;
        //float var6 = 0.03125F;
        //this.noiseGen4.setFrequency(var6 * 2);
        for (int var8 = 0; var8 < 16; ++var8)
        {
            for (int var9 = 0; var9 < 16; ++var9)
            {
                int var12 = (int) (this.noiseGen4.getNoise(par1 * 16 + var8, par2 * 16 + var9) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var13 = -1;
                Block var14 = this.topBlockID;
                byte var14m = this.topBlockMeta;
                Block var15 = this.fillBlockID;
                byte var15m = this.fillBlockMeta;

                for (int var16 = ChunkProviderNibiru.CHUNK_SIZE_Y - 1; var16 >= 0; --var16)
                {
                    int index = this.getIndex(var8, var16, var9);

                    if (var16 <= 0 + this.rand.nextInt(5))
                    {
                        arrayOfIDs[index] = Blocks.bedrock;
                    }
                    else
                    {
                        Block var18 = arrayOfIDs[index];

                        if (Blocks.air == var18)
                        {
                            var13 = -1;
                        }
                        else if (var18 == this.lowerBlockID)
                        {
                            arrayOfMeta[index] = this.lowerBlockMeta;

                            if (var13 == -1)
                            {
                                if (var12 <= 0)
                                {
                                    var14 = Blocks.air;
                                    var14m = 0;
                                    var15 = this.lowerBlockID;
                                    var15m = this.lowerBlockMeta;
                                }
                                else if (var16 >= var5 - -16 && var16 <= var5 + 1)
                                {
                                    var14 = this.topBlockID;
                                    var14m = this.topBlockMeta;
                                    var14 = this.fillBlockID;
                                    var14m = this.fillBlockMeta;
                                }

                                var13 = var12;

                                if (var16 >= var5 - 1)
                                {
                                    arrayOfIDs[index] = var14;
                                    arrayOfMeta[index] = var14m;
                                }
                                else
                                {
                                    arrayOfIDs[index] = var15;
                                    arrayOfMeta[index] = var15m;
                                }
                            }
                            else if (var13 > 0)
                            {
                                --var13;
                                arrayOfIDs[index] = var15;
                                arrayOfMeta[index] = var15m;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        Block[] ids = new Block[32768 * 2];
        byte[] meta = new byte[32768 * 2];
        Arrays.fill(ids, Blocks.air);
        this.generateTerrain(par1, par2, ids, meta);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.createCraters(par1, par2, ids, meta);
        this.replaceBlocksForBiome(par1, par2, ids, meta, this.biomesForGeneration);
        this.caveGenerator.generate(this, this.worldObj, par1, par2, ids, meta);
        this.cavernGenerator.generate(this, this.worldObj, par1, par2, ids, meta);
        this.ravineGenerator.func_151539_a(this, this.worldObj, par1, par2, ids);
        this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), par1 * 16, 30, par2 * 16, par1, par2, ids, meta);

        Chunk var4 = new Chunk(this.worldObj, ids, meta, par1, par2);
        byte[] var5 = var4.getBiomeArray();

        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte) this.biomesForGeneration[var6].biomeID;
        }

        var4.generateSkylightMap();
        return var4;
    }

    public void createCraters(int chunkX, int chunkZ, Block[] chunkArray, byte[] metaArray)
    {
        for (int cx = chunkX - 2; cx <= chunkX + 2; cx++)
        {
            for (int cz = chunkZ - 2; cz <= chunkZ + 2; cz++)
            {
                for (int x = 0; x < CHUNK_SIZE_X; x++)
                {
                    for (int z = 0; z < CHUNK_SIZE_Z; z++)
                    {
                        if (Math.abs(this.randFromPoint(cx * 16 + x, (cz * 16 + z) * 1000)) < this.noiseCraterGen.getNoise(x * CHUNK_SIZE_X + x, cz * CHUNK_SIZE_Z + z) / CRATER_PROB)
                        {
                            Random random = new Random(cx * 16 + x + (cz * 16 + z) * 5000);
                            EnumCraterSize cSize = EnumCraterSize.sizeArray[random.nextInt(EnumCraterSize.sizeArray.length)];
                            int size = random.nextInt(cSize.MAX_SIZE - cSize.MIN_SIZE) + cSize.MIN_SIZE;
                            this.makeCrater(cx * 16 + x, cz * 16 + z, chunkX * 16, chunkZ * 16, size, chunkArray, metaArray);
                        }
                    }
                }
            }
        }
    }

    public void makeCrater(int craterX, int craterZ, int chunkX, int chunkZ, int size, Block[] chunkArray, byte[] metaArray)
    {
        for (int x = 0; x < CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < CHUNK_SIZE_Z; z++)
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
                        if (Blocks.air != chunkArray[this.getIndex(x, y, z)] && helper <= yDev)
                        {
                            chunkArray[this.getIndex(x, y, z)] = Blocks.air;
                            metaArray[this.getIndex(x, y, z)] = 0;
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

    private int getIndex(int x, int y, int z)
    {
        return (x * 16 + z) * 256 + y;
    }

    private double randFromPoint(int x, int z)
    {
        int n;
        n = x + z * 57;
        n = n << 13 ^ n;
        return 1.0 - (n * (n * n * 15731 + 789221) + 1376312589 & 0x7fffffff) / 1073741824.0;
    }

    @Override
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void decoratePlanet(World par1World, Random par2Random, int par3, int par4)
    {
        this.biomedecoratorplanet.decorate(par1World, par2Random, par3, par4);
    }

    @Override
    public void populate(IChunkProvider chunk, int chunkX, int chunkZ)
    {
        BlockFalling.fallInstantly = true;
        int var4 = chunkX * 16;
        int var5 = chunkZ * 16;
        this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkX * var7 + chunkZ * var9 ^ this.worldObj.getSeed());
        this.dungeonGenerator.handleTileEntities(this.rand);
        this.decoratePlanet(this.worldObj, this.rand, var4, var5);

        for (int i = 0; i < 8; ++i)
        {
            int x = var4 + this.rand.nextInt(16) + 8;
            int y = this.rand.nextInt(256);
            int z = var5 + this.rand.nextInt(16) + 8;
            new WorldGenSpaceDungeons(NibiruBlocks.nibiru_ancient_chest, NibiruBlocks.nibiru_block, MPBlocks.space_mossy_cobblestone, 2).generate(this.worldObj, this.rand, x, y, z);
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunk, this.worldObj, this.rand, chunkX, chunkZ, false));

        boolean doGen = TerrainGen.populate(chunk, this.worldObj, this.rand, chunkX, chunkZ, false, EventType.FIRE);

        int x;
        int y;
        int z;

        //Oil Rock Gen
        for (int i = 0; doGen && i < 2; i++)
        {
            if (this.rand.nextInt(5) == 0)
            {
                x = var4 + this.rand.nextInt(16) + 8;
                y = this.rand.nextInt(256 - 16) + 16;
                z = var5 + this.rand.nextInt(16) + 8;
                new WorldGenSplashBlock(NibiruBlocks.oil_rock, 0, NibiruBlocks.nibiru_block, 0).generate(this.worldObj, this.rand, x, y, z);
            }
        }
        //Infected Grass Gen
        for (int i = 0; doGen && i < 4; i++)
        {
            if (this.rand.nextInt(5) == 0)
            {
                x = var4 + this.rand.nextInt(16) + 8;
                y = this.rand.nextInt(256 - 16) + 16;
                z = var5 + this.rand.nextInt(16) + 8;
                new WorldGenSplashBlock(NibiruBlocks.infected_grass, 0, NibiruBlocks.nibiru_block, 0).generate(this.worldObj, this.rand, x, y, z);
            }
        }
        //Infected Dirt Gen
        for (int i = 0; doGen && i < 4; i++)
        {
            if (this.rand.nextInt(5) == 0)
            {
                x = var4 + this.rand.nextInt(16) + 8;
                y = this.rand.nextInt(256 - 16) + 16;
                z = var5 + this.rand.nextInt(16) + 8;
                new WorldGenSplashBlock(NibiruBlocks.infected_dirt, 0, NibiruBlocks.nibiru_block, 0).generate(this.worldObj, this.rand, x, y, z);
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunk, this.worldObj, this.rand, chunkX, chunkZ, false));
        boolean tree = TerrainGen.decorate(this.worldObj, this.rand, chunkX, chunkZ, DecorateBiomeEvent.Decorate.EventType.TREE);
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.worldObj, this.rand, chunkX, chunkZ));

        for (int i = 0; tree && i < 100; ++i)
        {
            x = var4 + this.rand.nextInt(16) + 8;
            y = this.rand.nextInt(256);
            z = var5 + this.rand.nextInt(16) + 8;
            new WorldGenNibiruFruitTree(NibiruBlocks.nibiru_log, NibiruBlocks.ancient_dark_leaves, 0, true).generate(this.worldObj, this.rand, x, y, z);

        }
        for (int i = 0; tree && i < 100; ++i)
        {
            x = var4 + this.rand.nextInt(16) + 8;
            y = this.rand.nextInt(256);
            z = var5 + this.rand.nextInt(16) + 8;
            new WorldGenNibiruFruitTree(NibiruBlocks.nibiru_log, NibiruBlocks.orange_leaves, 1, true).generate(this.worldObj, this.rand, x, y, z);
        }
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.worldObj, this.rand, chunkX, chunkZ));
        BlockFalling.fallInstantly = false;
    }

    @Override
    public void recreateStructures(int par1, int par2)
    {
    }

    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    @Override
    public boolean canSave()
    {
        return true;
    }

    @Override
    public String makeString()
    {
        return "NibiruLevelSource";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int i, int j, int k)
    {
        if (par1EnumCreatureType == EnumCreatureType.monster)
        {
            List monsters = new ArrayList();
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityInfectedZombie.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedZombie.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedSpider.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedSkeleton.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedCreeper.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
            monsters.add(new SpawnListEntry(EntityEvolvedWitch.class, 5, 1, 1));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityGiantWorm.class, 100, 2, 4));
            return monsters;
        }
        return null;
    }
}