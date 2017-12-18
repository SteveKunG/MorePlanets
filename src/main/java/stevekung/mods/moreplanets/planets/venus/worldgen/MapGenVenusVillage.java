/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.worldgen;

import java.util.Random;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.core.util.MPLog;
import stevekung.mods.moreplanets.planets.venus.worldgen.village.*;

public class MapGenVenusVillage extends MapGenStructure
{
    private int terrainType;

    static
    {
        MapGenStructureIO.registerStructure(StructureVenusVillageStart.class, "VenusVillage");
        MapGenStructureIO.func_143031_a(ComponentVenusVillageField.class, "VenusField1");
        MapGenStructureIO.func_143031_a(ComponentVenusVillageField2.class, "VenusField2");
        MapGenStructureIO.func_143031_a(ComponentVenusVillageHouse.class, "VenusHouse");
        MapGenStructureIO.func_143031_a(ComponentVenusVillageRoadPiece.class, "VenusRoadPiece");
        MapGenStructureIO.func_143031_a(ComponentVenusVillagePathGen.class, "VenusPath");
        MapGenStructureIO.func_143031_a(ComponentVenusVillageTorch.class, "VenusTorch");
        MapGenStructureIO.func_143031_a(ComponentVenusVillageStartPiece.class, "VenusWell");
        MapGenStructureIO.func_143031_a(ComponentVenusVillageHut.class, "VenusHut");
    }

    public MapGenVenusVillage()
    {
        this.terrainType = 0;
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int i, int j)
    {
        byte numChunks = 32;
        byte offsetChunks = 8;
        int oldi = i;
        int oldj = j;

        if (i < 0)
        {
            i -= numChunks - 1;
        }
        if (j < 0)
        {
            j -= numChunks - 1;
        }

        int randX = i / numChunks;
        int randZ = j / numChunks;
        Random var7 = this.worldObj.setRandomSeed(i, j, 10387312);
        randX *= numChunks;
        randZ *= numChunks;
        randX += var7.nextInt(numChunks - offsetChunks);
        randZ += var7.nextInt(numChunks - offsetChunks);
        return oldi == randX && oldj == randZ;
    }

    @Override
    protected StructureStart getStructureStart(int par1, int par2)
    {
        MPLog.debug("Generating Venus Village at x : " + par1 * 16 + " z : " + par2 * 16);
        return new StructureVenusVillageStart(this.worldObj, this.rand, par1, par2, this.terrainType);
    }

    @Override
    public String func_143025_a()
    {
        return "VenusVillage";
    }
}