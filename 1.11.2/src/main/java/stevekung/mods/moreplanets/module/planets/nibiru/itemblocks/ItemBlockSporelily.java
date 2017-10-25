package stevekung.mods.moreplanets.module.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class ItemBlockSporelily extends ItemBlock
{
    public ItemBlockSporelily(Block block)
    {
        super(block);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        RayTraceResult raytraceresult = this.rayTrace(world, player, true);

        if (raytraceresult == null)
        {
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        }
        else
        {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos blockpos = raytraceresult.getBlockPos();

                if (!world.isBlockModifiable(player, blockpos) || !player.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemStack))
                {
                    return new ActionResult<>(EnumActionResult.FAIL, itemStack);
                }

                BlockPos blockpos1 = blockpos.up();
                IBlockState iblockstate = world.getBlockState(blockpos);

                if (iblockstate.getBlock() == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK && iblockstate.getValue(BlockFluidBase.LEVEL).intValue() == 0 && world.isAirBlock(blockpos1))
                {
                    world.setBlockState(blockpos1, NibiruBlocks.SPORELILY.getDefaultState(), 11);

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.shrink(1);
                    }
                    player.swingArm(hand);
                    player.addStat(StatList.getObjectUseStats(this));
                    world.playSound(player, blockpos, SoundEvents.BLOCK_WATERLILY_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
                }
            }
            return new ActionResult<>(EnumActionResult.FAIL, itemStack);
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        ItemStack itemStack = player.getHeldItem(hand);

        if (!block.isReplaceable(world, pos))
        {
            pos = pos.offset(facing);
        }
        if (!itemStack.isEmpty() && player.canPlayerEdit(pos, facing, itemStack) && world.mayPlace(this.block, pos, false, facing, (Entity)null))
        {
            int i = this.getMetadata(itemStack.getMetadata());
            IBlockState iblockstate1 = this.block.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, i, player, hand);

            if (this.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, iblockstate1))
            {
                SoundType sound = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
                world.playSound(player, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
                itemStack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.PASS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
}