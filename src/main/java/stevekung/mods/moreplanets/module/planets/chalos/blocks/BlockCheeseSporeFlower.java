package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.feature.WorldGenCheeseSporeTree;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockCheeseSporeFlower extends BlockBushMP implements IGrowable
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);

    public BlockCheeseSporeFlower(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BlockCheeseSporeFlower.AABB;
    }

    @Override
    public boolean validBlock(Block block)
    {
        return block == ChalosBlocks.CHEESE_GRASS_BLOCK || block == ChalosBlocks.CHEESE_DIRT || block == ChalosBlocks.CHEESE_COARSE_DIRT || block == ChalosBlocks.CHEESE_FARMLAND;
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return world.rand.nextFloat() < 0.25D;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        WorldGenCheeseSporeTree worldGen = new WorldGenCheeseSporeTree(6 + rand.nextInt(4), true);
        world.setBlockToAir(pos);

        if (!worldGen.generate(world, rand, pos))
        {
            world.setBlockState(pos, this.getDefaultState(), 2);
        }
    }

    @Override
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XYZ;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.SAPLING;
    }
}