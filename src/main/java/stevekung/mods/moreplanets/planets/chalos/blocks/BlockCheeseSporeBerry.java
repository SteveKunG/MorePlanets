package stevekung.mods.moreplanets.planets.chalos.blocks;

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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.blocks.BlockCropsMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockCheeseSporeBerry extends BlockCropsMP
{
    private static final AxisAlignedBB[] CARROT_AABB = { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D) };

    public BlockCheeseSporeBerry(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BlockCheeseSporeBerry.CARROT_AABB[state.getValue(BlockStateProperty.AGE_7)];
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
            for (int i = 0; i < 1 + world.rand.nextInt(2); i++)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(this.getSeed()));
            }
            world.setBlockState(pos, state.withProperty(BlockStateProperty.AGE_7, 0));
            return true;
        }
        return false;
    }

    @Override
    protected Item getCrop()
    {
        return MPItems.CHEESE_SPORE_BERRY;
    }

    @Override
    protected Item getSeed()
    {
        return MPItems.CHEESE_SPORE_SEED;
    }

    @Override
    public boolean validBlock(Block block)
    {
        return block == MPBlocks.CHEESE_FARMLAND;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int age = state.getValue(BlockStateProperty.AGE_7);
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        if (age >= 7)
        {
            for (int i = 0; i < 3 + fortune; ++i)
            {
                if (rand.nextInt(15) <= age)
                {
                    drops.add(new ItemStack(this.getCrop()));
                }
            }
            for (int i = 0; i < 2 + fortune; i++)
            {
                drops.add(new ItemStack(this.getSeed()));
            }
        }
    }
}