package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.blocks.BlockCropsMP;

public class BlockCheeseSporeBerryCrops extends BlockCropsMP
{
    public BlockCheeseSporeBerryCrops(String name)
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
                Block.spawnAsEntity(world, pos, new ItemStack(this.getCrop(), 1, 3));
            }
            for (int i = 0; i < 1 + world.rand.nextInt(2); i++)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(this.getSeed()));
            }
            world.setBlockState(pos, state.withProperty(AGE, 0));
            return true;
        }
        return false;
    }

    @Override
    protected Item getCrop()
    {
        return ChalosItems.CHEESE_FOOD;
    }

    @Override
    protected Item getSeed()
    {
        return ChalosItems.CHEESE_SPORE_SEED;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block block = world.getBlockState(pos.down()).getBlock();
        return super.canBlockStay(world, pos, state) && block == ChalosBlocks.CHEESE_FARMLAND;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
        int age = state.getValue(AGE).intValue();
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (age >= 7)
        {
            for (int i = 0; i < 3 + fortune; ++i)
            {
                if (rand.nextInt(15) <= age)
                {
                    ret.add(new ItemStack(this.getCrop(), 1, 3));
                }
            }
            for (int i = 0; i < 2 + fortune; i++)
            {
                ret.add(new ItemStack(this.getSeed()));
            }
        }
        return ret;
    }
}