package stevekung.mods.moreplanets.module.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.BlockFluidBase;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class ItemBlockSporelily extends ItemBlock
{
    public ItemBlockSporelily(Block block)
    {
        super(block);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

        if (movingobjectposition == null)
        {
            return itemStack;
        }
        else
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                BlockPos blockpos = movingobjectposition.getBlockPos();

                if (!world.isBlockModifiable(player, blockpos))
                {
                    return itemStack;
                }
                if (!player.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, itemStack))
                {
                    return itemStack;
                }

                BlockPos blockpos1 = blockpos.up();
                IBlockState iblockstate = world.getBlockState(blockpos);

                if (iblockstate.getBlock() == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK && iblockstate.getValue(BlockFluidBase.LEVEL).intValue() == 0 && world.isAirBlock(blockpos1))
                {
                    BlockSnapshot blocksnapshot = BlockSnapshot.getBlockSnapshot(world, blockpos1);
                    world.playSound(blockpos.getX(), blockpos.getY(), blockpos.getZ(), "moreplanets:block.lily.place", 1.0F, 0.8F, false);
                    world.setBlockState(blockpos1, NibiruBlocks.SPORELILY.getDefaultState());

                    if (world.isRemote)
                    {
                        player.swingItem();
                    }
                    if (ForgeEventFactory.onPlayerBlockPlace(player, blocksnapshot, EnumFacing.UP).isCanceled())
                    {
                        blocksnapshot.restore(true, false);
                        return itemStack;
                    }
                    if (!player.capabilities.isCreativeMode)
                    {
                        --itemStack.stackSize;
                    }
                }
            }
            return itemStack;
        }
    }
}