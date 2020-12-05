package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.blocks.BlockCropsMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockTerraberry extends BlockCropsMP
{
    public BlockTerraberry(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.95D, 0.9D);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (state.getValue(BlockStateProperty.AGE_7).intValue() == 7)
        {
            for (int i = 0; i < 2 + world.rand.nextInt(2); i++)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(this.getCrop()));
            }
            world.setBlockState(pos, state.withProperty(BlockStateProperty.AGE_7, 0));
            return true;
        }
        return false;
    }

    @Override
    protected Item getCrop()
    {
        return MPItems.TERRABERRY;
    }

    @Override
    protected Item getSeed()
    {
        return MPItems.TERRABERRY;
    }

    @Override
    protected boolean validBlock(Block block)
    {
        return block == MPBlocks.INFECTED_FARMLAND;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int age = state.getValue(BlockStateProperty.AGE_7);
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (age >= 7)
        {
            for (int i = 0; i < 2 + rand.nextInt(2); ++i)
            {
                if (rand.nextInt(15) <= age)
                {
                    drops.add(new ItemStack(this.getCrop()));
                }
            }
        }
        drops.add(new ItemStack(this.getCrop()));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this.getSeed());
    }
}