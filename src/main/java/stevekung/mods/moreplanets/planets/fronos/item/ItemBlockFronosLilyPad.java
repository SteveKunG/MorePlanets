package stevekung.mods.moreplanets.planets.fronos.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockMP;

public class ItemBlockFronosLilyPad extends ItemBlockMP
{
    public ItemBlockFronosLilyPad(Block block)
    {
        super(block);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        RayTraceResult result = this.rayTrace(world, player, true);

        if (result == null)
        {
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        }
        else
        {
            if (result.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos pos = result.getBlockPos();

                if (!world.isBlockModifiable(player, pos) || !player.canPlayerEdit(pos.offset(result.sideHit), result.sideHit, itemStack))
                {
                    return new ActionResult<>(EnumActionResult.FAIL, itemStack);
                }

                BlockPos pos1 = pos.up();
                IBlockState state = world.getBlockState(pos);

                if (state.getMaterial() == Material.WATER && ((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0 && world.isAirBlock(pos1))
                {
                    BlockSnapshot blocksnapshot = BlockSnapshot.getBlockSnapshot(world, pos1);
                    world.setBlockState(pos1, MPBlocks.FRONOS_LILY_PAD.getDefaultState());

                    if (ForgeEventFactory.onPlayerBlockPlace(player, blocksnapshot, EnumFacing.UP, hand).isCanceled())
                    {
                        blocksnapshot.restore(true, false);
                        return new ActionResult<>(EnumActionResult.FAIL, itemStack);
                    }

                    world.setBlockState(pos1, MPBlocks.FRONOS_LILY_PAD.getDefaultState(), 11);

                    if (player instanceof EntityPlayerMP)
                    {
                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos1, itemStack);
                    }

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.shrink(1);
                    }
                    player.swingArm(hand);
                    player.addStat(StatList.getObjectUseStats(this));
                    world.playSound(player, pos, SoundEvents.BLOCK_WATERLILY_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
                }
            }
            return new ActionResult<>(EnumActionResult.FAIL, itemStack);
        }
    }
}