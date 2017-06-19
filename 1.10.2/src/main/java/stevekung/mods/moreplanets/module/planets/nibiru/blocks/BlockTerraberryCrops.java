package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.blocks.BlockCropsMP;

public class BlockTerraberryCrops extends BlockCropsMP
{
    public BlockTerraberryCrops(String name)
    {
        super();
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
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
        return NibiruItems.NIBIRU_FRUITS;
    }

    @Override
    protected Item getSeed()
    {
        return NibiruItems.NIBIRU_FRUITS;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 6;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block block = world.getBlockState(pos.down()).getBlock();
        return super.canBlockStay(world, pos, state) && block == NibiruBlocks.INFECTED_FARMLAND;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
        int age = state.getValue(AGE).intValue();
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (age >= 7)
        {
            for (int i = 0; i < 2 + rand.nextInt(2); ++i)
            {
                if (rand.nextInt(15) <= age)
                {
                    ret.add(new ItemStack(this.getCrop(), 1, 6));
                }
            }
        }
        return ret;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this.getSeed(), 1, 6);
    }
}