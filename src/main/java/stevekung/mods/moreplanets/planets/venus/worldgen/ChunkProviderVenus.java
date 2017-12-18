/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.worldgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
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
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;
import stevekung.mods.moreplanets.core.entities.EntityEvolvedWitch;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.worldgen.MapGenCaveMP;
import stevekung.mods.moreplanets.core.worldgen.dungeon.RoomEmptyMP;
import stevekung.mods.moreplanets.core.worldgen.dungeon.RoomSpawnerMP;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenSpaceDungeons;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenSplashBlock;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianBlaze;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianSlime;
import stevekung.mods.moreplanets.planets.venus.worldgen.blazepit.MapGenVenusianBlazePit;
import stevekung.mods.moreplanets.planets.venus.worldgen.dungeon.RoomBossVenus;
import stevekung.mods.moreplanets.planets.venus.worldgen.dungeon.RoomChestsVenus;
import stevekung.mods.moreplanets.planets.venus.worldgen.dungeon.RoomTreasureVenus;

public class ChunkProviderVenus extends ChunkProviderGenerate
{
    Block topBlockID = VenusBlocks.venus_block;
    byte topBlockMeta = 0;
    Block fillBlockID = VenusBlocks.venus_block;
    byte fillBlockMeta = 1;
    Block lowerBlockID = VenusBlocks.venus_block;
    byte lowerBlockMeta = 2;

    private Random rand;

    private Gradient noiseGen1;
    private Gradient noiseGen2;
    private Gradient noiseGen3;
    private Gradient noiseGen4;
    private Gradient noiseGen5;
    private Gradient noiseGen6;
    private Gradient noiseGen7;

    private World worldObj;
    public BiomeDecoratorVenus biomedecoratorplanet = new BiomeDecoratorVenus();
    private MapGenCaveMP caveGenerator = new MapGenCaveMP(VenusBlocks.venus_block);
    private MapGenVenusRavine ravineGenerator = new MapGenVenusRavine();
    private MapGenVenusVillage villageGenerator = new MapGenVenusVillage();
    private MapGenVenusianBlazePit blazePit = new MapGenVenusianBlazePit();
    private MapGenDungeon dungeonGenerator = new MapGenDungeon(VenusBlocks.venus_block, 14, 8, 24, 4);

    {
        this.dungeonGenerator.otherRooms.add(new RoomEmptyMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomChestsVenus(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomChestsVenus(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.bossRooms.add(new RoomBossVenus(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.treasureRooms.add(new RoomTreasureVenus(null, 0, 0, 0, ForgeDirection.UNKNOWN));
    }

    private BiomeGenBase[] biomesForGeneration = { BiomeGenBaseVenus.venus };

    private static double TERRAIN_HEIGHT_MOD = 18;
    private static double SMALL_FEATURE_HEIGHT_MOD = 36;
    private static double MOUNTAIN_HEIGHT_MOD = 120;
    private static double VALLEY_HEIGHT_MOD = 64;
    private static int CRATER_PROB = 2000;

    // DO NOT CHANGE
    private static int MID_HEIGHT = 86;
    private static int CHUNK_SIZE_X = 16;
    private static int CHUNK_SIZE_Y = 256;
    private static int CHUNK_SIZE_Z = 16;
    private static double MAIN_FEATURE_FILTER_MOD = 6;
    private static double LARGE_FEATURE_FILTER_MOD = 10;
    private static double SMALL_FEATURE_FILTER_MOD = 10;

    public ChunkProviderVenus(World par1World, long par2, boolean par4)
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

        for (int x = 0; x < ChunkProviderVenus.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderVenus.CHUNK_SIZE_Z; z++)
            {
                double baseHeight = this.noiseGen1.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderVenus.TERRAIN_HEIGHT_MOD;
                double smallHillHeight = this.noiseGen2.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderVenus.SMALL_FEATURE_HEIGHT_MOD;
                double mountainHeight = Math.abs(this.noiseGen3.getNoise(chunkX * 16 + x, chunkZ * 16 + z));
                double valleyHeight = Math.abs(this.noiseGen4.getNoise(chunkX * 16 + x, chunkZ * 16 + z));
                double featureFilter = this.noiseGen5.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderVenus.MAIN_FEATURE_FILTER_MOD;
                double largeFilter = this.noiseGen6.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderVenus.LARGE_FEATURE_FILTER_MOD;
                double smallFilter = this.noiseGen7.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderVenus.SMALL_FEATURE_FILTER_MOD - 0.5;
                mountainHeight = this.lerp(smallHillHeight, mountainHeight * ChunkProviderVenus.MOUNTAIN_HEIGHT_MOD, this.fade(this.clamp(mountainHeight * 2, 0, 1)));
                valleyHeight = this.lerp(smallHillHeight, valleyHeight * ChunkProviderVenus.VALLEY_HEIGHT_MOD - ChunkProviderVenus.VALLEY_HEIGHT_MOD + 9, this.fade(this.clamp((valleyHeight + 2) * 4, 0, 1)));

                double yDev = this.lerp(valleyHeight, mountainHeight, this.fade(largeFilter));
                yDev = this.lerp(smallHillHeight, yDev, smallFilter);
                yDev = this.lerp(baseHeight, yDev, featureFilter);

                for (int y = 0; y < ChunkProviderVenus.CHUNK_SIZE_Y; y++)
                {
                    if (y < ChunkProviderVenus.MID_HEIGHT + yDev)
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
        float var6 = 0.03125F;
        this.noiseGen4.setFrequency(var6 * 2);
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

                for (int var16 = ChunkProviderVenus.CHUNK_SIZE_Y - 1; var16 >= 0; --var16)
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
        this.createCraters(par1, par2, ids, meta);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, ids, meta, this.biomesForGeneration);
        this.caveGenerator.generate(this, this.worldObj, par1, par2, ids, meta);
        this.ravineGenerator.func_151539_a(this, this.worldObj, par1, par2, ids);
        this.blazePit.generate(this, this.worldObj, par1, par2, ids, meta);
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
                for (int x = 0; x < ChunkProviderVenus.CHUNK_SIZE_X; x++)
                {
                    for (int z = 0; z < ChunkProviderVenus.CHUNK_SIZE_Z; z++)
                    {
                        if (Math.abs(this.randFromPoint(cx * 16 + x, (cz * 16 + z) * 1000)) < this.noiseGen4.getNoise(x * ChunkProviderVenus.CHUNK_SIZE_X + x, cz * ChunkProviderVenus.CHUNK_SIZE_Z + z) / ChunkProviderVenus.CRATER_PROB)
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
        for (int x = 0; x < ChunkProviderVenus.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderVenus.CHUNK_SIZE_Z; z++)
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
        this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
        this.blazePit.generateStructuresInChunk(this.worldObj, new Random(), chunkX, chunkZ);
        this.decoratePlanet(this.worldObj, this.rand, var4, var5);

        for (int i = 0; i < 8; ++i)
        {
            int x1 = var4 + this.rand.nextInt(16) + 8;
            int y1 = this.rand.nextInt(256);
            int z1 = var5 + this.rand.nextInt(16) + 8;
            new WorldGenSpaceDungeons(VenusBlocks.venus_ancient_chest, VenusBlocks.venus_block, MPBlocks.space_mossy_cobblestone, 6).generate(this.worldObj, this.rand, x1, y1, z1);
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunk, this.worldObj, this.rand, chunkX, chunkZ, false));

        boolean doGen = TerrainGen.populate(chunk, this.worldObj, this.rand, chunkX, chunkZ, false, EventType.FIRE);

        for (int i = 0; doGen && i < 4; i++)
        {
            if (this.rand.nextInt(5) == 0)
            {
                int x = var4 + this.rand.nextInt(16) + 8;
                int y = this.rand.nextInt(128 - 32) + 32;
                int z = var5 + this.rand.nextInt(16) + 8;
                new WorldGenSplashBlock(VenusBlocks.venus_smoke_geyser, 0, VenusBlocks.venus_block, 0).generate(this.worldObj, this.rand, x, y, z);
            }
        }
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunk, this.worldObj, this.rand, chunkX, chunkZ, false));
        BlockFalling.fallInstantly = false;
    }

    @Override
    public void recreateStructures(int par1, int par2)
    {
        this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[]) null);
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
        return "VenusLevelSource";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int i, int j, int k)
    {
        if (par1EnumCreatureType == EnumCreatureType.monster)
        {
            List monsters = new ArrayList();
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedZombie.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedSpider.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedSkeleton.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedCreeper.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityVenusianSlime.class, 50, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntityVenusianBlaze.class, 100, 4, 4));
            monsters.add(new SpawnListEntry(EntityEvolvedWitch.class, 5, 1, 1));
            return monsters;
        }
        return null;
    }
}