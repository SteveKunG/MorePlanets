package stevekung.mods.moreplanets.core.worldgen.feature;

import static net.minecraftforge.common.ChestGenHooks.DUNGEON_CHEST;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.core.tileentities.TileEntityAncientChestMP;
import stevekung.mods.moreplanets.core.util.MPLog;

public class WorldGenSpaceDungeons extends WorldGenerator
{
    private Block chest;
    private Block cobblestone;
    private Block moss;
    private int cobbleMeta;
    private int mossMeta;

    public WorldGenSpaceDungeons(Block chest, Block cobblestone, Block moss, int cobbleMeta, int mossMeta)
    {
        this.chest = chest;
        this.cobblestone = cobblestone;
        this.moss = moss;
        this.cobbleMeta = cobbleMeta;
        this.mossMeta = mossMeta;
    }

    public WorldGenSpaceDungeons(Block chest, Block cobblestone, Block moss, int mossMeta)
    {
        this(chest, cobblestone, moss, 3, mossMeta);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        byte b0 = 3;
        int l = rand.nextInt(2) + 2;
        int i1 = rand.nextInt(2) + 2;
        int j1 = 0;
        int k1;
        int l1;
        int i2;

        for (k1 = x - l - 1; k1 <= x + l + 1; ++k1)
        {
            for (l1 = y - 1; l1 <= y + b0 + 1; ++l1)
            {
                for (i2 = z - i1 - 1; i2 <= z + i1 + 1; ++i2)
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
                        ++j1;
                    }
                }
            }
        }

        if (j1 >= 1 && j1 <= 5)
        {
            for (k1 = x - l - 1; k1 <= x + l + 1; ++k1)
            {
                for (l1 = y + b0; l1 >= y - 1; --l1)
                {
                    for (i2 = z - i1 - 1; i2 <= z + i1 + 1; ++i2)
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
                                world.setBlock(k1, l1, i2, this.moss, this.mossMeta, 2);
                            }
                            else
                            {
                                world.setBlock(k1, l1, i2, this.cobblestone, this.cobbleMeta, 2);
                            }
                        }
                    }
                }
            }

            k1 = 0;

            while (k1 < 2)
            {
                l1 = 0;

                while (true)
                {
                    if (l1 < 3)
                    {
                        label101:
                        {
                        i2 = x + rand.nextInt(l * 2 + 1) - l;
                        int j2 = z + rand.nextInt(i1 * 2 + 1) - i1;

                        if (world.isAirBlock(i2, y, j2))
                        {
                            int k2 = 0;

                            if (world.getBlock(i2 - 1, y, j2).getMaterial().isSolid())
                            {
                                ++k2;
                            }
                            if (world.getBlock(i2 + 1, y, j2).getMaterial().isSolid())
                            {
                                ++k2;
                            }
                            if (world.getBlock(i2, y, j2 - 1).getMaterial().isSolid())
                            {
                                ++k2;
                            }
                            if (world.getBlock(i2, y, j2 + 1).getMaterial().isSolid())
                            {
                                ++k2;
                            }

                            if (k2 == 1)
                            {
                                world.setBlock(i2, y, j2, this.chest, 0, 2);

                                if (this.chest == Blocks.chest)
                                {
                                    TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(i2, y, j2);

                                    if (tileentitychest != null)
                                    {
                                        WeightedRandomChestContent.generateChestContents(rand, ChestGenHooks.getItems(DUNGEON_CHEST, rand), tileentitychest, ChestGenHooks.getCount(DUNGEON_CHEST, rand));
                                    }
                                }
                                else
                                {
                                    TileEntityAncientChestMP tileentitychest = (TileEntityAncientChestMP)world.getTileEntity(i2, y, j2);

                                    if (tileentitychest != null)
                                    {
                                        WeightedRandomChestContent.generateChestContents(rand, ChestGenHooks.getItems(DUNGEON_CHEST, rand), tileentitychest, ChestGenHooks.getCount(DUNGEON_CHEST, rand));
                                    }
                                }
                                break label101;
                            }
                        }
                        ++l1;
                        continue;
                        }
                    }
                    ++k1;
                    break;
                }
            }

            world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);

            if (tileentitymobspawner != null)
            {
                tileentitymobspawner.func_145881_a().setEntityName(this.pickMobSpawner(rand));
            }
            else
            {
                MPLog.error("Failed to fetch mob spawner entity at (" + x + ", " + y + ", " + z + ")");
            }
            MPLog.debug("Generate spawner %s at : %s %s %s", tileentitymobspawner.func_145881_a().getEntityNameToSpawn(), x, y, z);
            return true;
        }
        else
        {
            return false;
        }
    }

    private String pickMobSpawner(Random rand)
    {
        if (rand.nextInt(5) == 0)
        {
            return "GalacticraftCore.EvolvedSpider";
        }
        else if (rand.nextInt(10) == 0)
        {
            return "GalacticraftCore.EvolvedSkeleton";
        }
        else if (rand.nextInt(25) == 0)
        {
            return "GalacticraftCore.EvolvedCreeper";
        }
        else if (rand.nextInt(30) == 0)
        {
            return "MorePlanet.EvolvedWitch";
        }
        return "GalacticraftCore.EvolvedZombie";
    }
}