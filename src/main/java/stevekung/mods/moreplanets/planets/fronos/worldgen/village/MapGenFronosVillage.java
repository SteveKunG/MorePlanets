/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.village;

import java.util.Random;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.core.util.MPLog;

public class MapGenFronosVillage extends MapGenStructure
{
    private int terrainType;
    private static boolean initialized;

    static
    {
        try
        {
            MapGenFronosVillage.initiateStructures();
        }
        catch (Throwable e)
        {
        }
    }

    public static void initiateStructures() throws Throwable
    {
        if (!MapGenFronosVillage.initialized)
        {
            MapGenStructureIO.registerStructure(StructureFronosVillageStart.class, "FronosVillage");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageHouse.class, "FronosHouse");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageField.class, "FronosField1");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageField2.class, "FronosField2");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageField3.class, "FronosField3");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageTorch.class, "FronosTorch");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageHall.class, "FronosHall");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageHouse4.class, "FronosHouse4");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageHut.class, "FronosHut");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageRoadPiece.class, "FronosRoadPiece");
            MapGenStructureIO.func_143031_a(ComponentFronosVillagePathGen.class, "FronosPath");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageHouse3.class, "FronosHouse3");
            MapGenStructureIO.func_143031_a(ComponentFronosVillageStartPiece.class, "FronosWell");
        }
        MapGenFronosVillage.initialized = true;
    }

    public MapGenFronosVillage()
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
        MPLog.debug("Generating Fronos Village at x : " + par1 * 16 + " z : " + par2 * 16);
        return new StructureFronosVillageStart(this.worldObj, this.rand, par1, par2, this.terrainType);
    }

    @Override
    public String func_143025_a()
    {
        return "FronosVillage";
    }
}