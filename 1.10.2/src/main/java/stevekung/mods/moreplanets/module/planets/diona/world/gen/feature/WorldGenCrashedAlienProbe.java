package stevekung.mods.moreplanets.module.planets.diona.world.gen.feature;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.module.planets.diona.blocks.BlockCrashedAlienProbe;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityCrashedAlienProbe;

public class WorldGenCrashedAlienProbe extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (pos = pos.add(-8, 0, -8); pos.getY() > 3 && world.isAirBlock(pos); pos = pos.down()) {}

        if (pos.getY() <= 2)
        {
            return false;
        }

        int radius = 1 + rand.nextInt(4);
        int radiusSq = radius * radius;

        for (int poolX = -radius - 1; poolX <= radius + 1; poolX++)
        {
            for (int poolY = -radius - 1; poolY <= 256 - pos.getY(); poolY++)
            {
                for (int poolZ = -radius - 1; poolZ <= radius + 1; poolZ++)
                {
                    int distance = poolX * poolX + Math.min(0, poolY) * Math.min(0, poolY) + poolZ * poolZ;
                    BlockPos posnew = new BlockPos(poolX + pos.getX(), poolY + pos.getY(), poolZ + pos.getZ());

                    if (distance <= radiusSq)
                    {
                        world.setBlockState(posnew, Blocks.AIR.getDefaultState(), 2);
                    }
                    else if (world.getBlockState(posnew).getBlock() == DionaBlocks.DIONA_BLOCK && poolY < 0 && rand.nextInt(4) == 0)
                    {
                        world.setBlockState(posnew, DionaBlocks.GLOWING_IRON_BLOCK.getDefaultState(), 2);
                    }
                }
            }
        }

        BlockPos blockpos = pos.add(0, -radius + 1, 0);

        if (rand.nextInt(5) == 0)
        {
            EntityAlienMiner miner = new EntityAlienMiner(world);
            miner.setLocationAndAngles(blockpos.getX() + 0.5D + rand.nextDouble(), blockpos.getY() + 0.5D, blockpos.getZ() + 0.5D, rand.nextFloat() * 360.0F, 0.0F);
            miner.setHealth(5.0F + rand.nextInt(5));
            miner.enablePersistence();
            world.spawnEntityInWorld(miner);
        }
        if (rand.nextInt(5) == 0)
        {
            EntityAlienMiner miner = new EntityAlienMiner(world);
            miner.setLocationAndAngles(blockpos.getX() + 0.5D, blockpos.getY() + 0.5D, blockpos.getZ() + 0.5D + rand.nextDouble(), rand.nextFloat() * 360.0F, 0.0F);
            miner.setHealth(5.0F + rand.nextInt(5));
            miner.enablePersistence();
            world.spawnEntityInWorld(miner);
        }

        boolean alien = rand.nextInt(5) == 0;
        BlockPos tilepos = blockpos.down();
        System.out.println(tilepos);
        world.setBlockState(tilepos, DionaBlocks.CRASHED_ALIEN_PROBE.getDefaultState().withProperty(BlockCrashedAlienProbe.HAS_ALIEN, alien), 3);
        TileEntityCrashedAlienProbe probe = (TileEntityCrashedAlienProbe) world.getTileEntity(tilepos);

        if (probe != null)
        {
            probe.setLootTable(MPLootTables.CRASHED_ALIEN_PROBE, rand.nextLong());
        }
        return true;
    }
}