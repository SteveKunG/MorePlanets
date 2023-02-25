package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractHugeTrees;

public class WorldGenInfectedMegaPineTree extends WorldGenAbstractHugeTrees
{
    private boolean useBaseHeight;
    private boolean genLeaves;

    public WorldGenInfectedMegaPineTree(boolean genLeaves, boolean useBaseHeight)
    {
        super(13, 15, MPBlocks.INFECTED_SPRUCE_LEAVES.getDefaultState());
        this.useBaseHeight = useBaseHeight;
        this.genLeaves = genLeaves;
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
            this.createCrown(world, pos.getX(), pos.getZ(), pos.getY() + i, rand);

            for (int j = 0; j < i; ++j)
            {
                if (this.isAirLeaves(world, pos.up(j)))
                {
                    this.setBlockAndNotifyAdequately(world, pos.up(j), MPBlocks.INFECTED_SPRUCE_LOG.getDefaultState());
                }
                if (j < i - 1)
                {
                    if (this.isAirLeaves(world, pos.add(1, j, 0)))
                    {
                        this.setBlockAndNotifyAdequately(world, pos.add(1, j, 0), MPBlocks.INFECTED_SPRUCE_LOG.getDefaultState());
                    }
                    if (this.isAirLeaves(world, pos.add(1, j, 1)))
                    {
                        this.setBlockAndNotifyAdequately(world, pos.add(1, j, 1), MPBlocks.INFECTED_SPRUCE_LOG.getDefaultState());
                    }
                    if (this.isAirLeaves(world, pos.add(0, j, 1)))
                    {
                        this.setBlockAndNotifyAdequately(world, pos.add(0, j, 1), MPBlocks.INFECTED_SPRUCE_LOG.getDefaultState());
                    }
                }
            }
            return true;
        }
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;
    }

    @Override
    public void generateSaplings(World world, Random random, BlockPos pos)
    {
        this.placePodzolCircle(world, pos.west().north());
        this.placePodzolCircle(world, pos.east(2).north());
        this.placePodzolCircle(world, pos.west().south(2));
        this.placePodzolCircle(world, pos.east(2).south(2));

        for (int i = 0; i < 5; ++i)
        {
            int j = random.nextInt(64);
            int k = j % 8;
            int l = j / 8;

            if (k == 0 || k == 7 || l == 0 || l == 7)
            {
                this.placePodzolCircle(world, pos.add(-3 + k, 0, -3 + l));
            }
        }
    }

    @Override
    protected void growLeavesLayerStrict(World world, BlockPos layerCenter, int width)
    {
        if (this.genLeaves)
        {
            super.growLeavesLayerStrict(world, layerCenter, width);
        }
    }

    @Override
    protected void growLeavesLayer(World world, BlockPos layerCenter, int width)
    {
        if (this.genLeaves)
        {
            super.growLeavesLayer(world, layerCenter, width);
        }
    }

    private void createCrown(World world, int x, int z, int y, Random rand)
    {
        int i = rand.nextInt(5) + (this.useBaseHeight ? this.baseHeight : 3);
        int j = 0;

        for (int k = y - i; k <= y; ++k)
        {
            int l = y - k;
            int i1 = MathHelper.floor((float)l / (float)i * 3.5F);
            this.growLeavesLayerStrict(world, new BlockPos(x, k, z), i1 + (l > 0 && i1 == j && (k & 1) == 0 ? 1 : 0));
            j = i1;
        }
    }

    private void placePodzolCircle(World world, BlockPos center)
    {
        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                if (Math.abs(i) != 2 || Math.abs(j) != 2)
                {
                    this.placePodzolAt(world, center.add(i, 0, j));
                }
            }
        }
    }

    private void placePodzolAt(World world, BlockPos pos)
    {
        for (int i = 2; i >= -3; --i)
        {
            BlockPos blockpos = pos.up(i);
            IBlockState iblockstate = world.getBlockState(blockpos);

            if (this.isSoil(iblockstate.getBlock()))
            {
                this.setBlockAndNotifyAdequately(world, blockpos, MPBlocks.INFECTED_PODZOL.getDefaultState());
                break;
            }
            if (iblockstate.getMaterial() != Material.AIR && i < 0)
            {
                break;
            }
        }
    }

    private boolean isAirLeaves(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos);
    }
}