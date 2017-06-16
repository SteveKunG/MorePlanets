/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.worldgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.perlin.NoiseModule;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.worldgen.MapGenCaveMP;
import stevekung.mods.moreplanets.core.worldgen.dungeon.RoomEmptyMP;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenSpaceDungeons;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenSplashBlock;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusCreeper;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.worldgen.blazepit.MapGenSiriusBlazePit;
import stevekung.mods.moreplanets.planets.siriusb.worldgen.dungeon.RoomBossSiriusB;
import stevekung.mods.moreplanets.planets.siriusb.worldgen.dungeon.RoomChestsSiriusB;
import stevekung.mods.moreplanets.planets.siriusb.worldgen.dungeon.RoomSpawnerSiriusB;
import stevekung.mods.moreplanets.planets.siriusb.worldgen.dungeon.RoomTreasureSiriusB;

public class ChunkProviderSiriusB extends ChunkProviderGenerate
{
    Block topBlockID = SiriusBBlocks.sirius_b_block;
    byte topBlockMeta = 0;
    Block fillBlockID = SiriusBBlocks.sirius_b_block;
    byte fillBlockMeta = 1;
    Block lowerBlockID = SiriusBBlocks.sirius_b_block;
    byte lowerBlockMeta = 2;

    private Random rand;

    private NoiseModule noiseGen1;
    private NoiseModule noiseGen2;
    private NoiseModule noiseGen3;
    private NoiseModule noiseGen4;

    public BiomeDecoratorSiriusB biomedecoratorplanet = new BiomeDecoratorSiriusB();
    private MapGenCaveMP caveGenerator = new MapGenCaveMP(SiriusBBlocks.sirius_b_block);
    private MapGenSiriusBlazePit blazePit = new MapGenSiriusBlazePit();

    private World worldObj;
    private MapGenDungeon dungeonGenerator = new MapGenDungeon(SiriusBBlocks.sirius_b_block, 9, 8, 24, 4);

    {
        this.dungeonGenerator.otherRooms.add(new RoomEmptyMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomChestsSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.otherRooms.add(new RoomChestsSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.bossRooms.add(new RoomBossSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
        this.dungeonGenerator.treasureRooms.add(new RoomTreasureSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
    }

    private BiomeGenBase[] biomesForGeneration = { BiomeGenBaseSiriusB.siriusB };

    // DO NOT CHANGE
    private static int MID_HEIGHT = 64;
    private static int CHUNK_SIZE_X = 16;
    private static int CHUNK_SIZE_Y = 128;
    private static int CHUNK_SIZE_Z = 16;

    public ChunkProviderSiriusB(World par1World, long par2, boolean par4)
    {
        super(par1World, par2, par4);
        this.worldObj = par1World;
        this.rand = new Random(par2);
        this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noiseGen3 = new Gradient(this.rand.nextLong(), 1, 0.25F);
        this.noiseGen4 = new Gradient(this.rand.nextLong(), 1, 0.25F);
    }

    public void generateTerrain(int chunkX, int chunkZ, Block[] idArray, byte[] metaArray)
    {
        this.noiseGen1.setFrequency(0.0125F);
        this.noiseGen2.setFrequency(0.015F);
        this.noiseGen3.setFrequency(0.01F);
        this.noiseGen4.setFrequency(0.02F);

        for (int x = 0; x < ChunkProviderSiriusB.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderSiriusB.CHUNK_SIZE_Z; z++)
            {
                double d = this.noiseGen1.getNoise(x + chunkX * 16, z + chunkZ * 16) * 8;
                double d2 = this.noiseGen2.getNoise(x + chunkX * 16, z + chunkZ * 16) * 24;
                double d3 = this.noiseGen3.getNoise(x + chunkX * 16, z + chunkZ * 16) - 0.1;
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

                for (int y = 0; y < ChunkProviderSiriusB.CHUNK_SIZE_Y; y++)
                {
                    if (y < ChunkProviderSiriusB.MID_HEIGHT + yDev)
                    {
                        idArray[this.getIndex(x, y, z)] = this.lowerBlockID;
                        metaArray[this.getIndex(x, y, z)] = this.lowerBlockMeta;
                    }
                }
            }
        }
    }

    @Override
    public void replaceBlocksForBiome(int par1, int par2, Block[] arrayOfIDs, byte[] arrayOfMeta, BiomeGenBase[] par4ArrayOfBiomeGenBase)
    {
        int var5 = 20;
        for (int var8 = 0; var8 < 16; ++var8)
        {
            for (int var9 = 0; var9 < 16; ++var9)
            {
                int var12 = (int) (this.noiseGen4.getNoise(var8 + par1 * 16, var9 * par2 * 16) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var13 = -1;
                Block var14 = this.topBlockID;
                byte var14m = this.topBlockMeta;
                Block var15 = this.fillBlockID;
                byte var15m = this.fillBlockMeta;

                for (int var16 = 127; var16 >= 0; --var16)
                {
                    int index = this.getIndex(var8, var16, var9);
                    arrayOfMeta[index] = 0;

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
                                else if (var16 < var5 - 1 && var16 >= var5 - 2)
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
        Block[] ids = new Block[16 * 16 * 256];
        byte[] meta = new byte[16 * 16 * 256];
        Arrays.fill(ids, Blocks.air);
        this.generateTerrain(par1, par2, ids, meta);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, ids, meta, this.biomesForGeneration);
        this.caveGenerator.generate(this, this.worldObj, par1, par2, ids, meta);
        this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), par1 * 16, 25, par2 * 16, par1, par2, ids, meta);
        this.blazePit.generate(this, this.worldObj, par1, par2, ids, meta);
        Chunk var4 = new Chunk(this.worldObj, ids, meta, par1, par2);
        var4.generateSkylightMap();
        return var4;
    }

    @Override
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }

    private int getIndex(int x, int y, int z)
    {
        return (x * 16 + z) * 256 + y;
    }

    public void decoratePlanet(World par1World, Random par2Random, int par3, int par4)
    {
        this.biomedecoratorplanet.decorate(par1World, par2Random, par3, par4);
    }

    @Override
    public void populate(IChunkProvider chunk, int par2, int par3)
    {
        BlockFalling.fallInstantly = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(par2 * var7 + par3 * var9 ^ this.worldObj.getSeed());
        this.dungeonGenerator.handleTileEntities(this.rand);
        this.blazePit.generateStructuresInChunk(this.worldObj, new Random(), par2, par3);
        this.decoratePlanet(this.worldObj, this.rand, var4, var5);

        for (int i = 0; i < 8; ++i)
        {
            int x = var4 + this.rand.nextInt(16) + 8;
            int y = this.rand.nextInt(256);
            int z = var5 + this.rand.nextInt(16) + 8;
            new WorldGenSpaceDungeons(SiriusBBlocks.sirius_b_ancient_chest, SiriusBBlocks.sirius_b_block, MPBlocks.space_mossy_cobblestone, 5).generate(this.worldObj, this.rand, x, y, z);
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunk, this.worldObj, this.rand, par2, par3, false));

        boolean doGen = TerrainGen.populate(chunk, this.worldObj, this.rand, par2, par3, false, EventType.FIRE);

        //Spot Gen
        for (int i = 0; doGen && i < 4; i++)
        {
            if (this.rand.nextInt(5) == 0)
            {
                int x = var4 + this.rand.nextInt(16) + 8;
                int y = this.rand.nextInt(128 - 32) + 32;
                int z = var5 + this.rand.nextInt(16) + 8;
                new WorldGenSplashBlock(SiriusBBlocks.sirius_b_block, 7, SiriusBBlocks.sirius_b_block, 0).generate(this.worldObj, this.rand, x, y, z);
            }
        }
        //Magma Gen
        for (int i = 0; doGen && i < 4; i++)
        {
            if (this.rand.nextInt(5) == 0)
            {
                int x = var4 + this.rand.nextInt(16) + 8;
                int y = this.rand.nextInt(128 - 32) + 32;
                int z = var5 + this.rand.nextInt(16) + 8;
                new WorldGenSplashBlock(SiriusBBlocks.magma_rock, 0, SiriusBBlocks.sirius_b_block, 0).generate(this.worldObj, this.rand, x, y, z);
            }
        }
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunk, this.worldObj, this.rand, par2, par3, false));
        BlockFalling.fallInstantly = false;
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
        return "SiriusBLevelSource";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int i, int j, int k)
    {
        if (par1EnumCreatureType == EnumCreatureType.monster)
        {
            List monsters = new ArrayList();
            monsters.add(new BiomeGenBase.SpawnListEntry(EntitySiriusBlaze.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntitySiriusCreeper.class, 100, 4, 4));
            monsters.add(new BiomeGenBase.SpawnListEntry(EntitySiriusMagmaCube.class, 100, 4, 4));
            return monsters;
        }
        return null;
    }

    @Override
    public void recreateStructures(int par1, int par2)
    {
    }
}