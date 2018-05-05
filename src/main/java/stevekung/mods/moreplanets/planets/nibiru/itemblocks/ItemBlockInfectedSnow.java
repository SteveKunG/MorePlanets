package stevekung.mods.moreplanets.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class ItemBlockInfectedSnow extends ItemBlock
{
    public ItemBlockInfectedSnow(Block block)
    {
        super(block);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (itemStack.getCount() != 0 && player.canPlayerEdit(pos, facing, itemStack))
        {
            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();
            BlockPos blockpos = pos;

            if ((facing != EnumFacing.UP || block != this.block) && !block.isReplaceable(world, pos))
            {
                blockpos = pos.offset(facing);
                iblockstate = world.getBlockState(blockpos);
                block = iblockstate.getBlock();
            }

            if (block == this.block)
            {
                int i = iblockstate.getValue(BlockStateProperty.LAYERS).intValue();

                if (i <= 7)
                {
                    IBlockState iblockstate1 = iblockstate.withProperty(BlockStateProperty.LAYERS, Integer.valueOf(i + 1));
                    AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(world, blockpos);

                    if (axisalignedbb != Block.NULL_AABB && world.checkNoEntityCollision(axisalignedbb.offset(blockpos)) && world.setBlockState(blockpos, iblockstate1, 10))
                    {
                        SoundType soundtype = this.block.getSoundType(iblockstate1, world, blockpos, player);
                        world.playSound(player, blockpos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        itemStack.shrink(1);
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
            return super.onItemUse(player, world, blockpos, hand, facing, hitX, hitY, hitZ);
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return "tile.infected_snow";
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock() != NibiruBlocks.INFECTED_SNOW_LAYER || state.getValue(BlockStateProperty.LAYERS) > 7 ? super.canPlaceBlockOnSide(world, pos, side, player, stack) : true;
    }
}