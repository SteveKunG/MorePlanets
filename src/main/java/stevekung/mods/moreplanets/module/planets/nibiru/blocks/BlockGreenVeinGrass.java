package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.util.blocks.BlockGrassMP;

public class BlockGreenVeinGrass extends BlockGrassMP implements IGrowable
{
    public BlockGreenVeinGrass(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public void onPlantGrow(IBlockState state, World world, BlockPos pos, BlockPos source)
    {
        if (world.getBlockState(pos).getBlock() == NibiruBlocks.GREEN_VEIN_GRASS)
        {
            world.setBlockState(pos, NibiruBlocks.INFECTED_DIRT.getDefaultState(), 2);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockLightOpacity(pos.up()) > 2)
            {
                world.setBlockState(pos, NibiruBlocks.INFECTED_DIRT.getDefaultState());
            }
            else if (world.getLightFromNeighbors(pos.up()) >= 9)
            {
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos pos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                    if (world.getBlockState(pos1) == NibiruBlocks.INFECTED_DIRT.getDefaultState())
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
        return Item.getItemFromBlock(NibiruBlocks.INFECTED_DIRT);
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
                            if (NibiruBlocks.NIBIRU_FLOWER.canBlockStay(world, blockpos1, NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.TERRAPUFF_HURB)))
                            {
                                world.setBlockState(blockpos1, NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.TERRAPUFF_HURB), 3);
                            }
                        }
                        else
                        {
                            IBlockState iblockstate1 = NibiruBlocks.NIBIRU_TALL_GRASS.getDefaultState().withProperty(BlockNibiruTallGrass.VARIANT, BlockNibiruTallGrass.BlockType.GREEN_VEIN_TALL_GRASS);

                            if (NibiruBlocks.NIBIRU_TALL_GRASS.canBlockStay(world, blockpos1, iblockstate1))
                            {
                                world.setBlockState(blockpos1, iblockstate1, 3);
                            }
                        }
                    }
                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (world.getBlockState(blockpos1.down()).getBlock() != NibiruBlocks.GREEN_VEIN_GRASS || world.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }
                ++j;
            }
        }
    }

    @Override
    public String getName()
    {
        return "green_vein_grass";
    }
}