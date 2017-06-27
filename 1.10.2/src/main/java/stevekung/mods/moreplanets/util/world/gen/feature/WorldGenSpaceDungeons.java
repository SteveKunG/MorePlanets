package stevekung.mods.moreplanets.util.world.gen.feature;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.util.MPLog;
import stevekung.mods.moreplanets.util.blocks.BlockAncientChestMP;
import stevekung.mods.moreplanets.util.tileentity.TileEntityAncientChestMP;

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
        this.cobbleMeta = cobbleMeta;
        this.moss = moss;
        this.mossMeta = mossMeta;
    }

    public WorldGenSpaceDungeons(Block chest, Block cobblestone, Block moss, int mossMeta)
    {
        this(chest, cobblestone, moss, 3, mossMeta);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(2) + 2;
        int j = -i - 1;
        int k = i + 1;
        int l = rand.nextInt(2) + 2;
        int i1 = -l - 1;
        int j1 = l + 1;
        int k1 = 0;
        int l1;
        int i2;
        int j2;
        BlockPos blockpos1;

        for (l1 = j; l1 <= k; ++l1)
        {
            for (i2 = -1; i2 <= 4; ++i2)
            {
                for (j2 = i1; j2 <= j1; ++j2)
                {
                    blockpos1 = pos.add(l1, i2, j2);
                    Material material = world.getBlockState(blockpos1).getMaterial();
                    boolean flag3 = material.isSolid();

                    if (i2 == -1 && !flag3)
                    {
                        return false;
                    }
                    if (i2 == 4 && !flag3)
                    {
                        return false;
                    }

                    if ((l1 == j || l1 == k || j2 == i1 || j2 == j1) && i2 == 0 && world.isAirBlock(blockpos1) && world.isAirBlock(blockpos1.up()))
                    {
                        ++k1;
                    }
                }
            }
        }

        if (k1 >= 1 && k1 <= 5)
        {
            for (l1 = j; l1 <= k; ++l1)
            {
                for (i2 = 3; i2 >= -1; --i2)
                {
                    for (j2 = i1; j2 <= j1; ++j2)
                    {
                        blockpos1 = pos.add(l1, i2, j2);

                        if (l1 != j && i2 != -1 && j2 != i1 && l1 != k && i2 != 4 && j2 != j1)
                        {
                            if (world.getBlockState(blockpos1).getBlock() != this.chest)
                            {
                                world.setBlockToAir(blockpos1);
                            }
                        }
                        else if (blockpos1.getY() >= 0 && !world.getBlockState(blockpos1.down()).getMaterial().isSolid())
                        {
                            world.setBlockToAir(blockpos1);
                        }
                        else if (world.getBlockState(blockpos1).getMaterial().isSolid() && world.getBlockState(blockpos1).getBlock() != this.chest)
                        {
                            if (i2 == -1 && rand.nextInt(4) != 0)
                            {
                                world.setBlockState(blockpos1, this.moss.getStateFromMeta(this.mossMeta), 2);
                            }
                            else
                            {
                                world.setBlockState(blockpos1, this.cobblestone.getStateFromMeta(this.cobbleMeta), 2);
                            }
                        }
                    }
                }
            }

            l1 = 0;

            while (l1 < 2)
            {
                i2 = 0;

                while (true)
                {
                    if (i2 < 3)
                    {
                        label100:
                        {
                        j2 = pos.getX() + rand.nextInt(i * 2 + 1) - i;
                        int l2 = pos.getY();
                        int i3 = pos.getZ() + rand.nextInt(l * 2 + 1) - l;
                        BlockPos blockpos2 = new BlockPos(j2, l2, i3);

                        if (world.isAirBlock(blockpos2))
                        {
                            int k2 = 0;
                            Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

                            while (iterator.hasNext())
                            {
                                EnumFacing enumfacing = (EnumFacing)iterator.next();

                                if (world.getBlockState(blockpos2.offset(enumfacing)).getMaterial().isSolid())
                                {
                                    ++k2;
                                }
                            }

                            if (k2 == 1)
                            {
                                world.setBlockState(blockpos2, ((BlockAncientChestMP)this.chest).correctFacing(world, blockpos2, this.chest.getDefaultState()), 2);
                                TileEntity tileentity1 = world.getTileEntity(blockpos2);

                                if (tileentity1 instanceof TileEntityAncientChestMP)
                                {
                                    ((TileEntityAncientChestMP)tileentity1).setLootTable(MPLootTables.COMMON_SPACE_DUNGEON, rand.nextLong());
                                }
                                break label100;
                            }
                        }
                        ++i2;
                        continue;
                        }
                    }
                    ++l1;
                    break;
                }
            }

            world.setBlockState(pos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner)
            {
                ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(rand));
            }
            else
            {
                MPLog.error("Failed to fetch mob spawner entity at (" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")");
            }
            MPLog.debug("Generate %s spawner at : %s %s %s", ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().getEntityNameToSpawn(), pos.getX(), pos.getY(), pos.getZ());
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
            return "galacticraftcore.evolved_spider";
        }
        else if (rand.nextInt(10) == 0)
        {
            return "galacticraftcore.evolved_skeleton";
        }
        else if (rand.nextInt(25) == 0)
        {
            return "galacticraftcore.evolved_creeper";
        }
        return "galacticraftcore.evolved_zombie";
    }
}