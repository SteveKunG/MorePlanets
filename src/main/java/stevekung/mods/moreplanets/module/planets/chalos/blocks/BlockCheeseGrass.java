package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;

public class BlockCheeseGrass extends BlockBushMP implements IShearable, IGrowable
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public BlockCheeseGrass(String name)
    {
        super(Material.PLANTS);
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BlockCheeseGrass.AABB;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<>();

        if (RANDOM.nextInt(8) != 0)
        {
            return ret;
        }
        ret.add(new ItemStack(ChalosItems.CHEESE_SPORE_SEED));
        return ret;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
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
        return Arrays.asList(new ItemStack(this));
    }

    @Override
    protected boolean validBlock(Block block)
    {
        return block == ChalosBlocks.CHEESE_GRASS_BLOCK || block == ChalosBlocks.CHEESE_DIRT || block == ChalosBlocks.CHEESE_COARSE_DIRT || block == ChalosBlocks.CHEESE_FARMLAND;
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return ChalosBlocks.CHEESE_TALL_GRASS.canPlaceBlockAt(world, pos) && world.isAirBlock(pos.up());
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return ChalosBlocks.CHEESE_TALL_GRASS.canPlaceBlockAt(world, pos) && world.isAirBlock(pos.up());
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if (ChalosBlocks.CHEESE_TALL_GRASS.canPlaceBlockAt(world, pos) && world.isAirBlock(pos.up()))
        {
            ChalosBlocks.CHEESE_TALL_GRASS.placeAt(world, pos, ChalosBlocks.CHEESE_TALL_GRASS, 2);
        }
    }
}