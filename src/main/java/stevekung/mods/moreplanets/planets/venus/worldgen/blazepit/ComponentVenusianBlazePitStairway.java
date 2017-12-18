/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.worldgen.blazepit;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.world.gen.StructureComponentGC;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentVenusianBlazePitStairway extends StructureComponentGC
{
    protected ComponentVenusianBlazePitStairway(ComponentVenusianBlazePitRoom origRoom, int type, Random rand, int x, int y, int z)
    {
        super(type);
        this.boundingBox = new StructureBoundingBox(Math.min(x - 5, x - 2), Math.min(y - 20, y - 20), Math.min(z - 5, z - 2), Math.max(x - 5, x - 2), Math.max(y, y), Math.max(z - 5, z - 2));
    }

    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
    }

    @Override
    public boolean addComponentParts(World par1World, Random var2, StructureBoundingBox var3)
    {
        final int x1 = this.getBoundingBox().minX;
        final int y1 = this.getBoundingBox().minY;
        final int z1 = this.getBoundingBox().minZ;
        final int x2 = this.getBoundingBox().maxX;
        final int y2 = this.getBoundingBox().maxY;
        final int z2 = this.getBoundingBox().maxZ;

        this.fillWithBlocks(par1World, var3, x1, y1, z1, x2, y2, z2, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(par1World, var3, x1 + 1, y1, z1 + 1, x2 - 1, y2, z2 - 1, Blocks.air, Blocks.air, false);

        return true;
    }

    @Override
    protected void func_143012_a(NBTTagCompound nbttagcompound)
    {
    }

    @Override
    protected void func_143011_b(NBTTagCompound nbttagcompound)
    {
    }
}