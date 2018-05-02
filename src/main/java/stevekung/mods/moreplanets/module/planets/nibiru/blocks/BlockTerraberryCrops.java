package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

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
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.utils.blocks.BlockCropsMP;

public class BlockTerraberryCrops extends BlockCropsMP
{
    public BlockTerraberryCrops(String name)
    {
        super();
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
        if (state.getValue(AGE).intValue() == 7)
        {
            for (int i = 0; i < 2 + world.rand.nextInt(2); i++)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(this.getCrop(), 1, 6));
            }
            world.setBlockState(pos, state.withProperty(AGE, 0));
            return true;
        }
        return false;
    }

    @Override
    protected Item getCrop()
    {
        return NibiruItems.TERRABERRY;
    }

    @Override
    protected Item getSeed()
    {
        return NibiruItems.TERRABERRY;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block block = world.getBlockState(pos.down()).getBlock();
        return super.canBlockStay(world, pos, state) && block == NibiruBlocks.INFECTED_FARMLAND;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int age = state.getValue(AGE).intValue();
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
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this.getSeed());
    }
}