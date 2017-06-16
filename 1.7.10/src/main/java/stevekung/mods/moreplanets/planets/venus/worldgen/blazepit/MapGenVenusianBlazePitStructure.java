/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.worldgen.blazepit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import net.minecraft.block.Block;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;

public abstract class MapGenVenusianBlazePitStructure extends MapGenBaseMeta
{
    protected Map<Long, StructureStart> structureMap = new HashMap<Long, StructureStart>();

    @Override
    protected void recursiveGenerate(World world, int xChunkCoord, int zChunkCoord, int origXChunkCoord, int origZChunkCoord, Block[] blocks, byte[] metadata)
    {
        this.rand.nextInt();

        if (this.canSpawnStructureAtCoords(xChunkCoord, zChunkCoord))
        {
            final StructureStart var7 = this.getStructureStart(xChunkCoord, zChunkCoord);
            this.structureMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(xChunkCoord, zChunkCoord)), var7);
        }
    }

    public boolean generateStructuresInChunk(World par1World, Random par2Random, int par3, int par4)
    {
        final int var5 = (par3 << 4) + 8;
        final int var6 = (par4 << 4) + 8;
        boolean var7 = false;
        final Iterator<?> var8 = this.structureMap.values().iterator();

        while (var8.hasNext())
        {
            final StructureStart var9 = (StructureStart) var8.next();

            if (var9 != null && var9.isSizeableStructure() && var9.getBoundingBox().intersectsWith(var5, var6, var5 + 15, var6 + 15))
            {
                var9.generateStructure(par1World, par2Random, new StructureBoundingBox(var5, var6, var5 + 15, var6 + 15));
                var7 = true;
            }
        }
        return var7;
    }

    protected abstract boolean canSpawnStructureAtCoords(int var1, int var2);

    protected abstract StructureStart getStructureStart(int var1, int var2);
}
