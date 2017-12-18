/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.worldgen.village;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.core.util.MPLog;
import stevekung.mods.moreplanets.moons.koentus.worldgen.BiomeGenBaseKoentus;

public class MapGenKoentusVillage extends MapGenStructure
{
    public static List<BiomeGenBase> koentusVillageSpawnBiomes = Arrays.asList(new BiomeGenBase[] { BiomeGenBaseKoentus.koentus });
    private int terrainType;
    private static boolean initialized;

    static
    {
        try
        {
            MapGenKoentusVillage.initiateStructures();
        }
        catch (Throwable e)
        {
        }
    }

    public static void initiateStructures() throws Throwable
    {
        if (!MapGenKoentusVillage.initialized)
        {
            MapGenStructureIO.registerStructure(StructureKoentusVillageStart.class, "KoentusVillage");
            MapGenStructureIO.func_143031_a(ComponentKoentusVillageField.class, "KoentusField1");
            MapGenStructureIO.func_143031_a(ComponentKoentusVillageHouse.class, "KoentusHouse");
            MapGenStructureIO.func_143031_a(ComponentKoentusVillageRoadPiece.class, "KoentusRoadPiece");
            MapGenStructureIO.func_143031_a(ComponentKoentusVillagePathGen.class, "KoentusPath");
            MapGenStructureIO.func_143031_a(ComponentKoentusVillageTorch.class, "KoentusTorch");
            MapGenStructureIO.func_143031_a(ComponentKoentusVillageStartPiece.class, "KoentusWell");
            MapGenStructureIO.func_143031_a(ComponentKoentusVillageHut.class, "KoentusHut");
        }
        MapGenKoentusVillage.initialized = true;
    }

    public MapGenKoentusVillage()
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
        MPLog.debug("Generating Koentus Village at x : " + par1 * 16 + " z : " + par2 * 16);
        return new StructureKoentusVillageStart(this.worldObj, this.rand, par1, par2, this.terrainType);
    }

    @Override
    public String func_143025_a()
    {
        return "KoentusVillage";
    }
}