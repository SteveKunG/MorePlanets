package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.blocks.BlockVinesMP;

public class WorldGenInfectedMegaJungle extends WorldGenHugeTreesMP
{
    public WorldGenInfectedMegaJungle(boolean genLeaves, int baseHeight, int extraRandomHeight)
    {
        super(genLeaves, baseHeight, extraRandomHeight);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = this.getHeight(rand);

        if (!this.ensureGrowable(world, pos, i))
        {
            return false;
        }
        else
        {
            this.createCrown(world, pos.up(i), 2);

            for (int j = pos.getY() + i - 2 - rand.nextInt(4); j > pos.getY() + i / 2; j -= 2 + rand.nextInt(4))
            {
                float f = rand.nextFloat() * (float)Math.PI * 2.0F;
                int k = pos.getX() + (int)(0.5F + MathHelper.cos(f) * 4.0F);
                int l = pos.getZ() + (int)(0.5F + MathHelper.sin(f) * 4.0F);

                for (int i1 = 0; i1 < 5; ++i1)
                {
                    k = pos.getX() + (int)(1.5F + MathHelper.cos(f) * i1);
                    l = pos.getZ() + (int)(1.5F + MathHelper.sin(f) * i1);
                    this.setBlockAndNotifyAdequately(world, new BlockPos(k, j - 3 + i1 / 2, l), NibiruBlocks.NIBIRU_LOG.getStateFromMeta(2));
                }

                int j2 = 1 + rand.nextInt(2);
                int j1 = j;

                for (int k1 = j - j2; k1 <= j1; ++k1)
                {
                    int l1 = k1 - j1;
                    this.growLeavesLayer(world, new BlockPos(k, k1, l), 1 - l1);
                }
            }

            for (int i2 = 0; i2 < i; ++i2)
            {
                BlockPos blockpos = pos.up(i2);

                if (this.isAirLeaves(world, blockpos))
                {
                    this.setBlockAndNotifyAdequately(world, blockpos, NibiruBlocks.NIBIRU_LOG.getStateFromMeta(2));

                    if (i2 > 0)
                    {
                        this.placeVine(world, rand, blockpos.west(), BlockVinesMP.EAST);
                        this.placeVine(world, rand, blockpos.north(), BlockVinesMP.SOUTH);
                    }
                }

                if (i2 < i - 1)
                {
                    BlockPos blockpos1 = blockpos.east();

                    if (this.isAirLeaves(world,blockpos1))
                    {
                        this.setBlockAndNotifyAdequately(world, blockpos1, NibiruBlocks.NIBIRU_LOG.getStateFromMeta(2));

                        if (i2 > 0)
                        {
                            this.placeVine(world, rand, blockpos1.east(), BlockVinesMP.WEST);
                            this.placeVine(world, rand, blockpos1.north(), BlockVinesMP.SOUTH);
                        }
                    }

                    BlockPos blockpos2 = blockpos.south().east();

                    if (this.isAirLeaves(world,blockpos2))
                    {
                        this.setBlockAndNotifyAdequately(world, blockpos2, NibiruBlocks.NIBIRU_LOG.getStateFromMeta(2));

                        if (i2 > 0)
                        {
                            this.placeVine(world, rand, blockpos2.east(), BlockVinesMP.WEST);
                            this.placeVine(world, rand, blockpos2.south(), BlockVinesMP.NORTH);
                        }
                    }

                    BlockPos blockpos3 = blockpos.south();

                    if (this.isAirLeaves(world,blockpos3))
                    {
                        this.setBlockAndNotifyAdequately(world, blockpos3, NibiruBlocks.NIBIRU_LOG.getStateFromMeta(2));

                        if (i2 > 0)
                        {
                            this.placeVine(world, rand, blockpos3.west(), BlockVinesMP.EAST);
                            this.placeVine(world, rand, blockpos3.south(), BlockVinesMP.NORTH);
                        }
                    }
                }
            }
            return true;
        }
    }

    private void placeVine(World world, Random rand, BlockPos pos, PropertyBool property)
    {
        if (rand.nextInt(3) > 0 && world.isAirBlock(pos))
        {
            this.setBlockAndNotifyAdequately(world, pos, NibiruBlocks.INFECTED_VINES.getDefaultState().withProperty(property, Boolean.valueOf(true)));
        }
    }

    private void createCrown(World world, BlockPos pos, int width)
    {
        int i = 2;

        for (int j = -i; j <= 0; ++j)
        {
            this.growLeavesLayerStrict(world, pos.up(j), width + 1 - j);
        }
    }

    private boolean isAirLeaves(World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();
        return block.isAir(world.getBlockState(pos), world, pos) || block.isLeaves(world.getBlockState(pos), world, pos);
    }
}