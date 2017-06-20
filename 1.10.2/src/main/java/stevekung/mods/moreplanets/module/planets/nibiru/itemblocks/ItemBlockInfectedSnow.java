package stevekung.mods.moreplanets.module.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class ItemBlockInfectedSnow extends ItemBlock
{
    public ItemBlockInfectedSnow(Block block)
    {
        super(block);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (itemStack.stackSize == 0)
        {
            return false;
        }
        else if (!player.canPlayerEdit(pos, side, itemStack))
        {
            return false;
        }
        else
        {
            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();
            BlockPos blockpos = pos;

            if ((side != EnumFacing.UP || block != this.block) && !block.isReplaceable(world, pos))
            {
                blockpos = pos.offset(side);
                iblockstate = world.getBlockState(blockpos);
                block = iblockstate.getBlock();
            }

            if (block == this.block)
            {
                int i = iblockstate.getValue(BlockStateHelper.LAYERS).intValue();

                if (i <= 7)
                {
                    IBlockState iblockstate1 = iblockstate.withProperty(BlockStateHelper.LAYERS, Integer.valueOf(i + 1));
                    AxisAlignedBB axisalignedbb = this.block.getCollisionBoundingBox(world, blockpos, iblockstate1);

                    if (axisalignedbb != null && world.checkNoEntityCollision(axisalignedbb) && world.setBlockState(blockpos, iblockstate1, 2))
                    {
                        world.playSoundEffect(blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F, this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
                        --itemStack.stackSize;
                        return true;
                    }
                }
            }
            return super.onItemUse(itemStack, player, world, blockpos, side, hitX, hitY, hitZ);
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
        return state.getBlock() != NibiruBlocks.INFECTED_SNOW_LAYER || state.getValue(BlockStateHelper.LAYERS) > 7 ? super.canPlaceBlockOnSide(world, pos, side, player, stack) : true;
    }
}