package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.ArrayList;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;

public class BlockCheeseTallGrass extends BlockBushMP implements IShearable, IGrowable
{
    protected static AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public BlockCheeseTallGrass(String name)
    {
        super(Material.PLANTS);
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return TALL_GRASS_AABB;
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
        List<ItemStack> ret = new ArrayList<>();
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
        if (ChalosBlocks.CHALOS_DOUBLE_PLANT.canBlockStay(world, pos, ChalosBlocks.CHALOS_DOUBLE_PLANT.getDefaultState()) && world.isAirBlock(pos.up()))
        {
            ChalosBlocks.CHALOS_DOUBLE_PLANT.placeAt(world, pos, BlockChalosDoublePlant.BlockType.DOUBLE_CHEESE_TALL_GRASS, 2);
        }
    }

    @Override
    public String getName()
    {
        return "cheese_tall_grass";
    }
}