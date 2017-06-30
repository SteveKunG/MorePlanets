package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.blocks.BlockCropsMP;

public class BlockCheeseSporeBerryCrops extends BlockCropsMP
{
    private static AxisAlignedBB[] CARROT_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};

    public BlockCheeseSporeBerryCrops(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return CARROT_AABB[state.getValue(BlockCropsMP.AGE).intValue()];
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
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