package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;

public class BlockCheeseTallGrass extends BlockBushMP implements IShearable, IGrowable
{
    public BlockCheeseTallGrass(String name)
    {
        super(Material.plants);
        this.setUnlocalizedName(name);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = Lists.newArrayList();

        if (RANDOM.nextInt(8) != 0)
        {
            return ret;
        }
        ret.add(new ItemStack(ChalosItems.CHEESE_SPORE_SEED));
        return ret;
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XYZ;
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> ret = Lists.newArrayList();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block block = world.getBlockState(pos.down()).getBlock();
        return block == ChalosBlocks.CHEESE_GRASS || block == ChalosBlocks.CHEESE_DIRT || block == ChalosBlocks.CHEESE_FARMLAND;
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
        if (ChalosBlocks.CHEESE_DOUBLE_TALL_GRASS.canBlockStay(world, pos, ChalosBlocks.CHEESE_DOUBLE_TALL_GRASS.getDefaultState()) && world.isAirBlock(pos.up()))
        {
            world.setBlockState(pos, ChalosBlocks.CHEESE_DOUBLE_TALL_GRASS.getDefaultState().withProperty(BlockChalosDoubleTallGrass.HALF, BlockChalosDoubleTallGrass.EnumBlockHalf.LOWER).withProperty(BlockChalosDoubleTallGrass.VARIANT, BlockChalosDoubleTallGrass.BlockType.CHEESE_DOUBLE_TALL_GRASS), 2);
            world.setBlockState(pos.up(), ChalosBlocks.CHEESE_DOUBLE_TALL_GRASS.getDefaultState().withProperty(BlockChalosDoubleTallGrass.HALF, BlockChalosDoubleTallGrass.EnumBlockHalf.UPPER).withProperty(BlockChalosDoubleTallGrass.VARIANT, BlockChalosDoubleTallGrass.BlockType.CHEESE_DOUBLE_TALL_GRASS), 2);
        }
    }

    @Override
    public String getName()
    {
        return "cheese_tall_grass";
    }
}