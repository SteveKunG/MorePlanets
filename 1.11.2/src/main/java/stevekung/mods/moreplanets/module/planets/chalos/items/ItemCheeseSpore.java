package stevekung.mods.moreplanets.module.planets.chalos.items;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemCheeseSpore extends ItemBaseMP
{
    public ItemCheeseSpore(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemStack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if (world.getBlockState(pos).getBlock() == ChalosBlocks.CHEESE_GRASS && !world.getBlockState(pos).isSideSolid(world, pos.up(), EnumFacing.UP) || world.getBlockState(pos.up()).getBlock() == ChalosBlocks.CHEESE_SPORE_FLOWER)
            {
                this.growCheeseSporeFlower(world, pos, world.rand);
                itemStack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.PASS;
    }

    private void growCheeseSporeFlower(World world, BlockPos pos, Random rand)
    {
        if (!world.isRemote)
        {
            BlockPos blockpos = pos.up();

            for (int i = 0; i < 128; ++i)
            {
                BlockPos blockpos1 = blockpos;
                int j = 0;

                while (true)
                {
                    if (j >= i / 16)
                    {
                        if (world.isAirBlock(blockpos1))
                        {
                            IBlockState iblockstate1 = ChalosBlocks.CHEESE_SPORE_FLOWER.getDefaultState();

                            if (rand.nextInt(5) == 0)
                            {
                                if (ChalosBlocks.CHEESE_SPORE_FLOWER.canBlockStay(world, blockpos1, iblockstate1))
                                {
                                    world.playEvent(2005, blockpos1, 0);
                                    world.setBlockState(blockpos1, iblockstate1, 3);
                                }
                            }
                            else
                            {
                                world.playEvent(2005, pos, 0);
                            }
                        }
                    }
                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (world.getBlockState(blockpos1.down()).getBlock() != ChalosBlocks.CHEESE_GRASS || world.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }
                ++j;
            }
        }
    }

    @Override
    public String getName()
    {
        return "cheese_spore_item";
    }
}