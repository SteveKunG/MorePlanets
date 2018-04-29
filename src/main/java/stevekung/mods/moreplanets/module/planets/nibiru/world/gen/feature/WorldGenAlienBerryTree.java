package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenAlienBerryTree extends WorldGenAbstractTree
{
    public WorldGenAlienBerryTree()
    {
        super(false);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(3) + 5;
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256)
        {
            for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j)
            {
                int k = 1;

                if (j == pos.getY())
                {
                    k = 0;
                }
                if (j >= pos.getY() + 1 + i - 2)
                {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = pos.getX() - k; l <= pos.getX() + k && flag; ++l)
                {
                    for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < 256)
                        {
                            if (!this.isReplaceable(world,blockpos$mutableblockpos.setPos(l, j, i1)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = pos.down();
                Block block1 = world.getBlockState(down).getBlock();

                if (block1 == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK && pos.getY() < 256 - i - 1)
                {
                    block1.onPlantGrow(world.getBlockState(down), world, down, pos);
                    int k2 = 3;
                    int l2 = 0;

                    for (int i3 = pos.getY() - k2 + i; i3 <= pos.getY() + i; ++i3)
                    {
                        int i4 = i3 - (pos.getY() + i);
                        int j1 = l2 + 1 - i4 / 2;

                        for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1)
                        {
                            int l1 = k1 - pos.getX();

                            for (int i2 = pos.getZ() - j1; i2 <= pos.getZ() + j1; ++i2)
                            {
                                int j2 = i2 - pos.getZ();

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0)
                                {
                                    BlockPos blockpos = new BlockPos(k1, i3, i2);
                                    Block block = world.getBlockState(blockpos).getBlock();

                                    if (block.isAir(world.getBlockState(blockpos), world, blockpos) || block.isLeaves(world.getBlockState(blockpos), world, blockpos) || world.getBlockState(blockpos).getMaterial() == Material.VINE)
                                    {
                                        this.setBlockAndNotifyAdequately(world, blockpos, NibiruBlocks.NIBIRU_LEAVES.getStateFromMeta(3));
                                    }
                                }
                            }
                        }
                    }

                    for (int j3 = 0; j3 < i; ++j3)
                    {
                        BlockPos upN = pos.up(j3);
                        Block block2 = world.getBlockState(upN).getBlock();

                        if (block2.isAir(world.getBlockState(upN), world, upN) || block2.isLeaves(world.getBlockState(upN), world, upN) || world.getBlockState(upN).getMaterial() == Material.VINE)
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(j3), NibiruBlocks.NIBIRU_LOG.getStateFromMeta(3));
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}