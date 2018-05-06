package stevekung.mods.moreplanets.planets.chalos.items;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.items.ItemBaseMP;

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
            if (world.getBlockState(pos).getBlock() == MPBlocks.CHEESE_GRASS_BLOCK && !world.getBlockState(pos).isSideSolid(world, pos.up(), EnumFacing.UP) || world.getBlockState(pos.up()).getBlock() == MPBlocks.CHEESE_SPORE_FLOWER)
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
                            IBlockState iblockstate1 = MPBlocks.CHEESE_SPORE_FLOWER.getDefaultState();

                            if (rand.nextInt(5) == 0)
                            {
                                if (iblockstate1.getBlock().canPlaceBlockAt(world, blockpos1))
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

                if (world.getBlockState(blockpos1.down()).getBlock() != MPBlocks.CHEESE_GRASS_BLOCK || world.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }
                ++j;
            }
        }
    }
}