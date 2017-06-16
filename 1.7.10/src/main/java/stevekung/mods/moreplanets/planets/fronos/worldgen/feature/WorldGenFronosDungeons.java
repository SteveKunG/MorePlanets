/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityFronosAncientChest;

public class WorldGenFronosDungeons extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        byte b0 = 3;
        int l = rand.nextInt(2) + 2;
        int i1 = rand.nextInt(2) + 2;
        int j1 = 0;
        int k1;

        for (k1 = x - l - 1; k1 <= x + l + 1; k1++)
        {
            for (int l1 = y - 1; l1 <= y + b0 + 1; l1++)
            {
                for (int i2 = z - i1 - 1; i2 <= z + i1 + 1; i2++)
                {
                    Material material = world.getBlock(k1, l1, i2).getMaterial();

                    if (l1 == y - 1 && !material.isSolid())
                    {
                        return false;
                    }
                    if (l1 == y + b0 + 1 && !material.isSolid())
                    {
                        return false;
                    }
                    if ((k1 == x - l - 1 || k1 == x + l + 1 || i2 == z - i1 - 1 || i2 == z + i1 + 1) && l1 == y && world.isAirBlock(k1, l1, i2) && world.isAirBlock(k1, l1 + 1, i2))
                    {
                        j1++;
                    }
                }
            }
        }
        if (j1 >= 1 && j1 <= 5)
        {
            for (k1 = x - l - 1; k1 <= x + l + 1; k1++)
            {
                for (int l1 = y + b0; l1 >= y - 1; l1--)
                {
                    for (int i2 = z - i1 - 1; i2 <= z + i1 + 1; i2++)
                    {
                        if (k1 != x - l - 1 && l1 != y - 1 && i2 != z - i1 - 1 && k1 != x + l + 1 && l1 != y + b0 + 1 && i2 != z + i1 + 1)
                        {
                            world.setBlockToAir(k1, l1, i2);
                        }
                        else if (l1 >= 0 && !world.getBlock(k1, l1 - 1, i2).getMaterial().isSolid())
                        {
                            world.setBlockToAir(k1, l1, i2);
                        }
                        else if (world.getBlock(k1, l1, i2).getMaterial().isSolid())
                        {
                            if (l1 == y - 1 && rand.nextInt(4) != 0)
                            {
                                world.setBlock(k1, l1, i2, FronosBlocks.mossy_fronos_cobblestone, 0, 2);
                            }
                            else
                            {
                                world.setBlock(k1, l1, i2, FronosBlocks.fronos_block, 1, 2);
                            }
                        }
                    }
                }
            }

            k1 = 0;

            while (k1 < 2)
            {
                int l1 = 0;

                while (l1 < 3)
                {
                    int i2 = x + rand.nextInt(l * 2 + 1) - l;
                    int j2 = z + rand.nextInt(i1 * 2 + 1) - i1;

                    if (world.isAirBlock(i2, y, j2))
                    {
                        int k2 = 0;

                        if (world.getBlock(i2 - 1, y, j2).getMaterial().isSolid())
                        {
                            k2++;
                        }
                        if (world.getBlock(i2 + 1, y, j2).getMaterial().isSolid())
                        {
                            k2++;
                        }
                        if (world.getBlock(i2, y, j2 - 1).getMaterial().isSolid())
                        {
                            k2++;
                        }
                        if (world.getBlock(i2, y, j2 + 1).getMaterial().isSolid())
                        {
                            k2++;
                        }
                        if (k2 == 1)
                        {
                            world.setBlock(i2, y, j2, FronosBlocks.fronos_ancient_chest, 0, 2);
                            TileEntityFronosAncientChest tileentitychest = (TileEntityFronosAncientChest)world.getTileEntity(i2, y, j2);

                            if (tileentitychest == null)
                            {
                                break;
                            }
                            WeightedRandomChestContent.generateChestContents(rand, ChestGenHooks.getItems("dungeonChest", rand), tileentitychest, ChestGenHooks.getCount("dungeonChest", rand));
                            break;
                        }
                    }
                    l1++;
                }
                k1++;
            }
            world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);

            if (tileentitymobspawner != null)
            {
                tileentitymobspawner.func_145881_a().setEntityName(this.pickMobSpawner(rand));
            }
            else
            {
                System.err.println("Failed to fetch mob spawner entity at (" + x + ", " + y + ", " + z + ")");
            }
            return true;
        }
        return false;
    }

    private String pickMobSpawner(Random rand)
    {
        if (rand.nextInt(5) == 0)
        {
            return "MorePlanet.JellySlime";
        }
        return "MorePlanet.CreamSlime";
    }
}