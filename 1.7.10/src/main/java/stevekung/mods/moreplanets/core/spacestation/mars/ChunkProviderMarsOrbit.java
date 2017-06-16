/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.spacestation.mars;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import stevekung.mods.moreplanets.core.spacestation.BiomeGenBaseOrbitMP;

public class ChunkProviderMarsOrbit extends ChunkProviderGenerate
{
    private Random rand;
    private World worldObj;

    public ChunkProviderMarsOrbit(World world, long seed, boolean genFeature)
    {
        super(world, seed, genFeature);
        this.rand = new Random(seed);
        this.worldObj = world;
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

    @Override
    public boolean saveChunks(boolean save, IProgressUpdate update)
    {
        return true;
    }

    @Override
    public boolean canSave()
    {
        return true;
    }

    @Override
    public Chunk provideChunk(int x, int z)
    {
        this.rand.setSeed(x * 341873128712L + z * 132897987541L);
        Block[] ids = new Block[32768];
        Arrays.fill(ids, Blocks.air);
        byte[] meta = new byte[32768];
        Chunk var4 = new Chunk(this.worldObj, ids, meta, x, z);
        byte[] biomesArray = var4.getBiomeArray();

        for (int i = 0; i < biomesArray.length; ++i)
        {
            biomesArray[i] = (byte) BiomeGenBaseOrbitMP.spaceStation.biomeID;
        }
        var4.generateSkylightMap();
        return var4;
    }

    @Override
    public boolean chunkExists(int x, int z)
    {
        return true;
    }

    @Override
    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ)
    {
        BlockFalling.fallInstantly = true;
        int x = chunkX * 16;
        int z = chunkZ * 16;
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkX * i1 + chunkZ * j1 ^ this.worldObj.getSeed());

        if (x == 0 && z == 0)
        {
            this.worldObj.setBlock(x, 64, z, GCBlocks.spaceStationBase, 0, 3);

            TileEntity tile = this.worldObj.getTileEntity(x, 64, z);

            if (tile instanceof IMultiBlock)
            {
                ((IMultiBlock)tile).onCreate(new BlockVec3(x, 64, z));
            }
            new WorldGenMarsSpaceStation().generate(this.worldObj, this.rand, x - 10, 62, z - 3);
        }
        BlockFalling.fallInstantly = false;
    }

    @Override
    public String makeString()
    {
        return "MarsOrbitLevelSource";
    }

    @Override
    public List<?> getPossibleCreatures(EnumCreatureType type, int i, int j, int k)
    {
        return null;
    }
}