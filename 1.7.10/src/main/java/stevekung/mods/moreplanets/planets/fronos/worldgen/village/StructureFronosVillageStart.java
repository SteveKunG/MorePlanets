/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureFronosVillageStart extends StructureStart
{
    public StructureFronosVillageStart()
    {
    }

    @SuppressWarnings("unchecked")
    public StructureFronosVillageStart(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        super(par3, par4);

        ArrayList<StructureFronosVillagePieceWeight> var6 = StructureFronosVillagePieces.getStructureVillageWeightedPieceList(par2Random, par5);
        ComponentFronosVillageStartPiece var7 = new ComponentFronosVillageStartPiece(par1World.getWorldChunkManager(), 0, par2Random, (par3 << 4) + 2, (par4 << 4) + 2, var6, par5);
        this.components.add(var7);
        var7.buildComponent(var7, this.components, par2Random);
        ArrayList<Object> var8 = var7.field_74930_j;
        ArrayList<Object> var9 = var7.field_74932_i;
        int var10;

        while (!var8.isEmpty() || !var9.isEmpty())
        {
            StructureComponent var11;

            if (var8.isEmpty())
            {
                var10 = par2Random.nextInt(var9.size());
                var11 = (StructureComponent) var9.remove(var10);
                var11.buildComponent(var7, this.components, par2Random);
            }
            else
            {
                var10 = par2Random.nextInt(var8.size());
                var11 = (StructureComponent) var8.remove(var10);
                var11.buildComponent(var7, this.components, par2Random);
            }
        }

        this.updateBoundingBox();
        var10 = 0;
        Iterator<StructureComponent> var13 = this.components.iterator();

        while (var13.hasNext())
        {
            StructureComponent var12 = var13.next();

            if (!(var12 instanceof ComponentFronosVillageRoadPiece))
            {
                ++var10;
            }
        }
    }

    @Override
    public boolean isSizeableStructure()
    {
        return true;
    }
}