package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.blocks.BlockGrassBlockMP;

public class BlockGreenVeinGrassBlock extends BlockGrassBlockMP implements IGrowable
{
    public BlockGreenVeinGrassBlock(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public void onPlantGrow(IBlockState state, World world, BlockPos pos, BlockPos source)
    {
        if (world.getBlockState(pos).getBlock() == MPBlocks.GREEN_VEIN_GRASS_BLOCK)
        {
            world.setBlockState(pos, MPBlocks.INFECTED_DIRT.getDefaultState(), 2);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (!world.isAreaLoaded(pos, 3))
            {
                return;
            }
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockLightOpacity(pos.up()) > 2)
            {
                world.setBlockState(pos, MPBlocks.INFECTED_DIRT.getDefaultState());
            }
            else if (world.getLightFromNeighbors(pos.up()) >= 9)
            {
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos pos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                    if (world.getBlockState(pos1) == MPBlocks.INFECTED_DIRT.getDefaultState())
                    {
                        if (world.getLightFromNeighbors(pos1.up()) >= 4 && world.getBlockState(pos1.up()).getLightOpacity(world, pos1.up()) <= 2)
                        {
                            world.setBlockState(pos1, this.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MPBlocks.INFECTED_DIRT);
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    if (world.isAirBlock(blockpos1))
                    {
                        if (rand.nextInt(8) == 0)
                        {
                            if (MPBlocks.TERRAPUFF_HERB.canPlaceBlockAt(world, blockpos1))
                            {
                                world.setBlockState(blockpos1, MPBlocks.TERRAPUFF_HERB.getDefaultState(), 3);
                            }
                        }
                        else
                        {
                            if (MPBlocks.GREEN_VEIN_GRASS.canPlaceBlockAt(world, blockpos1))
                            {
                                world.setBlockState(blockpos1, MPBlocks.GREEN_VEIN_GRASS.getDefaultState(), 3);
                            }
                        }
                    }
                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (world.getBlockState(blockpos1.down()).getBlock() != MPBlocks.GREEN_VEIN_GRASS_BLOCK || world.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }
                ++j;
            }
        }
    }
}