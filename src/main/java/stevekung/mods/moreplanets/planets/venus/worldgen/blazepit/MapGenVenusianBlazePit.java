/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.worldgen.blazepit;

import net.minecraft.world.gen.structure.StructureStart;

public class MapGenVenusianBlazePit extends MapGenVenusianBlazePitStructure
{
    @Override
    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        return this.rand.nextInt(100) == 0;
    }

    @Override
    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new StructureVenusianBlazePitStart(this.worldObj, this.rand, par1, par2);
    }
}