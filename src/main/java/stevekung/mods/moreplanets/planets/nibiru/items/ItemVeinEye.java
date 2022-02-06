package stevekung.mods.moreplanets.planets.nibiru.items;

import com.google.common.base.Predicates;

import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.BlockVeinFrame;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityVeinEye;
import stevekung.mods.moreplanets.utils.items.ItemBaseMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class ItemVeinEye extends ItemBaseMP
{
    private static final BlockPattern PORTAL_SHAPE = FactoryBlockPattern.start().aisle("?vvv?", ">???<", ">???<", ">???<", "?^^^?").where('?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(MPBlocks.VEIN_FRAME).where(BlockVeinFrame.EYE, Predicates.equalTo(true)).where(BlockStateProperty.FACING_HORIZON, Predicates.equalTo(EnumFacing.SOUTH)))).where('>', BlockWorldState.hasState(BlockStateMatcher.forBlock(MPBlocks.VEIN_FRAME).where(BlockVeinFrame.EYE, Predicates.equalTo(true)).where(BlockStateProperty.FACING_HORIZON, Predicates.equalTo(EnumFacing.WEST)))).where('v', BlockWorldState.hasState(BlockStateMatcher.forBlock(MPBlocks.VEIN_FRAME).where(BlockVeinFrame.EYE, Predicates.equalTo(true)).where(BlockStateProperty.FACING_HORIZON, Predicates.equalTo(EnumFacing.NORTH)))).where('<', BlockWorldState.hasState(BlockStateMatcher.forBlock(MPBlocks.VEIN_FRAME).where(BlockVeinFrame.EYE, Predicates.equalTo(true)).where(BlockStateProperty.FACING_HORIZON, Predicates.equalTo(EnumFacing.EAST)))).build();

    public ItemVeinEye(String name)
    {
        this.setTranslationKey(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        if (player.canPlayerEdit(pos.offset(facing), facing, itemStack) && state.getBlock() == MPBlocks.VEIN_FRAME && !state.getValue(BlockVeinFrame.EYE))
        {
            if (world.isRemote)
            {
                for (int i = 0; i < 16; ++i)
                {
                    double d0 = pos.getX() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
                    double d1 = pos.getY() + 0.8125F;
                    double d2 = pos.getZ() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
                return EnumActionResult.SUCCESS;
            }
            else
            {
                world.setBlockState(pos, state.withProperty(BlockVeinFrame.EYE, true), 2);
                world.updateComparatorOutputLevel(pos, MPBlocks.VEIN_FRAME);
                itemStack.shrink(1);
                BlockPattern.PatternHelper helper = ItemVeinEye.PORTAL_SHAPE.match(world, pos);

                if (helper != null)
                {
                    BlockPos blockpos = helper.getFrontTopLeft().add(-3, 0, -3);

                    for (int j = 0; j < 3; ++j)
                    {
                        for (int k = 0; k < 3; ++k)
                        {
                            world.setBlockState(blockpos.add(j, 0, k), MPBlocks.VEIN_PORTAL.getDefaultState(), 2);
                        }
                    }
                }
                return EnumActionResult.SUCCESS;
            }
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        RayTraceResult result = this.rayTrace(world, player, false);

        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK && world.getBlockState(result.getBlockPos()).getBlock() == MPBlocks.VEIN_FRAME && !world.getBlockState(result.getBlockPos()).getValue(BlockVeinFrame.EYE))
        {
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        }
        else
        {
            if (!world.isRemote)
            {
                BlockPos pos = ((WorldServer)world).getChunkProvider().getNearestStructurePos(world, "NibiruStronghold", new BlockPos(player), false);

                if (pos != null)
                {
                    EntityVeinEye eye = new EntityVeinEye(world, player.posX, player.posY + player.height / 2.0F, player.posZ);
                    eye.moveTowards(pos);
                    world.spawnEntity(eye);
                    world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDEREYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    world.playEvent(null, 1003, player.getPosition(), 0);

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.shrink(1);
                    }
                    player.addStat(StatList.getObjectUseStats(this));
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
                }
            }
            return new ActionResult<>(EnumActionResult.FAIL, itemStack);
        }
    }
}