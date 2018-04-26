package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.feature.WorldGenCheeseSporeTree;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockCheeseSporeFlower extends BlockBushMP implements IGrowable
{
    protected static AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);

    public BlockCheeseSporeFlower(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BUSH_AABB;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
    {
        Block block = world.getBlockState(pos.down()).getBlock();
        return block == ChalosBlocks.CHEESE_GRASS || block == ChalosBlocks.CHEESE_DIRT;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block soil = world.getBlockState(pos.down()).getBlock();
        return soil == ChalosBlocks.CHEESE_GRASS || soil == ChalosBlocks.CHEESE_DIRT;
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
        Object obj = null;

        if (obj == null)
        {
            obj = new WorldGenCheeseSporeTree(6 + rand.nextInt(4), true);
        }

        if (obj != null)
        {
            world.setBlockToAir(pos);

            if (!((WorldGenerator)obj).generate(world, rand, pos))
            {
                world.setBlockState(pos, this.getDefaultState(), 2);
            }
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

    @Override
    public String getName()
    {
        return "cheese_spore_flower";
    }
}