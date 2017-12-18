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
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;

public class ComponentVenusianBlazePitRoom extends StructureComponentGC
{
    public int corridorCount;
    public int originalFourCorridorLength;
    public int bossEntryCorridor;
    public int bossEntryCount;
    private int averageGroundLevel = -1;
    private final int height;
    private final int width;

    public ComponentVenusianBlazePitRoom(int type, World world, Random par2Random, int x, int y, int z, int height, int width, int cbm)
    {
        super(type);
        this.setCoordBaseMode(cbm);
        this.height = height;
        this.width = width;
        this.boundingBox = StructureComponentGC.getComponentToAddBoundingBox(x, 78 - this.height, z, 0, 0, 0, 7, this.height, 7, cbm);
    }

    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
        int var4;

        for (var4 = 0; var4 < 4; ++var4)
        {
            final int[] var5 = this.getValidOpening(par3Random, var4);

            this.makeCorridor(par2List, par3Random, 1, var5[0], var5[1], var5[2], this.width, 7, var4);
        }
    }

    public int[] getValidOpening(Random var1, int var2)
    {
        if (var2 == 0)
        {
            return new int[] {this.width - 1, 0, 1};
        }
        else if (var2 == 1)
        {
            return new int[] {1, 0, this.width - 1};
        }
        else if (var2 == 2)
        {
            return new int[] {0, 0, 1};
        }
        else if (var2 == 3)
        {
            return new int[] {1, 0, 0};
        }
        return new int[] {0, 0, 0};
    }

    public boolean makeCorridor(List list, Random random, int type, int x, int y, int z, int width, int height, int cbm)
    {
        final int var10 = (this.getCoordBaseMode() + cbm) % 4;
        this.offsetCorridorCoords(x, y, z, width, var10);
        return true;
    }

    protected int[] offsetCorridorCoords(int x, int y, int z, int width, int cbm)
    {
        final int var6 = this.getXWithOffset(x, z);
        final int var7 = this.getYWithOffset(y);
        final int var8 = this.getZWithOffset(x, z);
        return cbm == 0 ? new int[] {var6 + 1, var7 - 1, var8 - width / 2}: cbm == 1 ? new int[] {var6 + width / 2, var7 - 1, var8 + 1}: cbm == 2 ? new int[] {var6 - 1, var7 - 1, var8 + width / 2}: cbm == 3 ? new int[] {var6 - width / 2, var7 - 1, var8 - 1}: new int[] {x, y, z};
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
    {
        if (this.averageGroundLevel < 0)
        {
            this.averageGroundLevel = this.getAverageGroundLevel(world, box);

            if (this.averageGroundLevel < 0)
            {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 3, 0);
        }
        this.makeWallsDown(world);
        this.makePlatforms(world, rand);
        this.makeWallsFlat(world);
        return true;
    }

    public void makeWallsDown(World world)
    {
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < 7; x++)
            {
                for (int z = 0; z < 7; z++)
                {
                    if ((x == 0 || x == 6 || z == 0 || z == 6) && (y == 0 || y > 0))
                    {
                        this.placeBlockAtCurrentPosition(world, VenusBlocks.venus_block, 14, x, y, z, this.getBoundingBox());
                    }
                    else
                    {
                        this.placeBlockAtCurrentPosition(world, Blocks.air, 0, x, y, z, this.getBoundingBox());
                    }
                }
            }
        }
    }

    public void makeWallsFlat(World world)
    {
        for (int y = 0; y > -1; y--)
        {
            for (int x = 0; x < 1; x++)
            {
                for (int z = 0; z < 1; z++)
                {
                    for (int i = -2; i < 7; i++)
                    {
                        for (int j = -2; j < 7; j++)
                        {
                            if (world.getBlock(this.getBoundingBox().minX + x + i, this.getBoundingBox().minY + y, this.getBoundingBox().minZ + z + j) == Blocks.air)
                            {
                                this.placeBlockAtCurrentPosition(world, VenusBlocks.venus_block, 14, x + i, y, z + j, this.getBoundingBox());
                            }
                        }
                    }
                }
            }
        }
    }

    public void makePlatforms(World world, Random rand)
    {
        for (int y = this.height - 1; y > 0; y--)
        {
            for (int x = 0; x < this.width; x++)
            {
                for (int z = 0; z < this.width; z++)
                {
                    if (y % 4 == 0 && rand.nextInt(20) == 0)
                    {
                        for (int i = -2; i < 2; i++)
                        {
                            for (int j = -2; j < 2; j++)
                            {
                                if (world.getBlock(this.getBoundingBox().minX + x + i, this.getBoundingBox().minY + y, this.getBoundingBox().minZ + z + j) == Blocks.air)
                                {
                                    this.placeBlockAtCurrentPosition(world, VenusBlocks.venus_block, 14, x + i, y, z + j, this.getBoundingBox());
                                }

                                if (y > 0)
                                {
                                    this.placeBlockAtCurrentPosition(world, Blocks.air, 0, x - 2, y, z - 2, this.getBoundingBox());
                                    this.placeBlockAtCurrentPosition(world, Blocks.air, 0, x + 1, y, z - 2, this.getBoundingBox());
                                    this.placeBlockAtCurrentPosition(world, Blocks.air, 0, x - 2, y, z + 1, this.getBoundingBox());
                                    this.placeBlockAtCurrentPosition(world, Blocks.air, 0, x + 1, y, z + 1, this.getBoundingBox());
                                }

                                if (rand.nextInt(5) == 0 && world.getBlock(this.getBoundingBox().minX + x + i, this.getBoundingBox().minY + y + 1, this.getBoundingBox().minZ + z + j) == Blocks.air && world.getBlock(this.getBoundingBox().minX + x + i, this.getBoundingBox().minY + y, this.getBoundingBox().minZ + z + j) == VenusBlocks.venus_block)
                                {
                                    this.placeBlockAtCurrentPosition(world, VenusBlocks.venusian_blaze_egg, 0, x + i, y + 1, z + j, this.getBoundingBox());
                                }
                            }
                        }

                        if (rand.nextInt(10) == 0)
                        {
                            if (x > 0 && x < 7 && z > 0 && z < 7)
                            {
                                if (world.getBlock(this.getBoundingBox().minX + x, this.getBoundingBox().minY + y + 1, this.getBoundingBox().minZ + z) == Blocks.air)
                                {
                                    this.placeBlockAtCurrentPosition(world, VenusBlocks.venusian_blaze_egg, 0, x, y + 2, z, this.getBoundingBox());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected int getAverageGroundLevel(World par1World, StructureBoundingBox par2StructureBoundingBox)
    {
        int var3 = 0;
        int var4 = 0;

        for (int var5 = this.boundingBox.minZ; var5 <= this.boundingBox.maxZ; ++var5)
        {
            for (int var6 = this.boundingBox.minX; var6 <= this.boundingBox.maxX; ++var6)
            {
                if (par2StructureBoundingBox.isVecInside(var6, 64, var5))
                {
                    var3 += Math.max(par1World.getTopSolidOrLiquidBlock(var6, var5), par1World.provider.getAverageGroundLevel());
                    ++var4;
                }
            }
        }

        if (var4 == 0)
        {
            return -1;
        }
        else
        {
            return var3 / var4;
        }
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