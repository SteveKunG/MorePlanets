package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.feature.WorldGenCheeseSporeTree;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockCheeseSporeFlower extends BlockBushMP implements IGrowable
{
    public BlockCheeseSporeFlower(String name)
    {
        super();
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypeGrass);
        this.setUnlocalizedName(name);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        float f = 0.275F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
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
    @SideOnly(Side.CLIENT)
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XYZ;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.SAPLING;
    }

    @Override
    public String getName()
    {
        return "cheese_spore_flower";
    }
}