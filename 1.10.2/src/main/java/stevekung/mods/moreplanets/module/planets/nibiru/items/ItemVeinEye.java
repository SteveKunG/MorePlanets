package stevekung.mods.moreplanets.module.planets.nibiru.items;

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
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockVeinFrame;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityVeinEye;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemVeinEye extends ItemBaseMP
{
    public ItemVeinEye(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = world.getBlockState(pos);

        if (player.canPlayerEdit(pos.offset(facing), facing, itemStack) && iblockstate.getBlock() == NibiruBlocks.VEIN_FRAME && !iblockstate.getValue(BlockVeinFrame.EYE).booleanValue())
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
                world.setBlockState(pos, iblockstate.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(true)), 2);
                world.updateComparatorOutputLevel(pos, NibiruBlocks.VEIN_FRAME);
                --itemStack.stackSize;
                BlockPattern.PatternHelper blockpattern$patternhelper = this.getOrCreatePortalShape().match(world, pos);

                if (blockpattern$patternhelper != null)
                {
                    BlockPos blockpos = blockpattern$patternhelper.getFrontTopLeft().add(-3, 0, -3);

                    for (int j = 0; j < 3; ++j)
                    {
                        for (int k = 0; k < 3; ++k)
                        {
                            world.setBlockState(blockpos.add(j, 0, k), NibiruBlocks.VEIN_PORTAL.getDefaultState(), 2);
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
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
    {
        RayTraceResult raytraceresult = this.rayTrace(world, player, false);

        if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK && world.getBlockState(raytraceresult.getBlockPos()).getBlock() == NibiruBlocks.VEIN_FRAME && !world.getBlockState(raytraceresult.getBlockPos()).getValue(BlockVeinFrame.EYE).booleanValue())
        {
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        }
        else
        {
            if (!world.isRemote)
            {
                BlockPos blockpos = ((WorldServer)world).getChunkProvider().getStrongholdGen(world, "NibiruStronghold", new BlockPos(player));

                if (blockpos != null)
                {
                    EntityVeinEye entityendereye = new EntityVeinEye(world, player.posX, player.posY + player.height / 2.0F, player.posZ);
                    entityendereye.moveTowards(blockpos);
                    world.spawnEntityInWorld(entityendereye);
                    world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDEREYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    world.playEvent((EntityPlayer)null, 1003, new BlockPos(player), 0);

                    if (!player.capabilities.isCreativeMode)
                    {
                        --itemStack.stackSize;
                    }
                    player.addStat(StatList.getObjectUseStats(this));
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
                }
            }
            return new ActionResult<>(EnumActionResult.FAIL, itemStack);
        }
    }

    private BlockPattern getOrCreatePortalShape()
    {
        return FactoryBlockPattern.start().aisle(new String[] {"?vvv?", ">???<", ">???<", ">???<", "?^^^?"}).where('?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(NibiruBlocks.VEIN_FRAME).where(BlockVeinFrame.EYE, Predicates.equalTo(Boolean.valueOf(true))).where(BlockVeinFrame.FACING, Predicates.equalTo(EnumFacing.SOUTH)))).where('>', BlockWorldState.hasState(BlockStateMatcher.forBlock(NibiruBlocks.VEIN_FRAME).where(BlockVeinFrame.EYE, Predicates.equalTo(Boolean.valueOf(true))).where(BlockVeinFrame.FACING, Predicates.equalTo(EnumFacing.WEST)))).where('v', BlockWorldState.hasState(BlockStateMatcher.forBlock(NibiruBlocks.VEIN_FRAME).where(BlockVeinFrame.EYE, Predicates.equalTo(Boolean.valueOf(true))).where(BlockVeinFrame.FACING, Predicates.equalTo(EnumFacing.NORTH)))).where('<', BlockWorldState.hasState(BlockStateMatcher.forBlock(NibiruBlocks.VEIN_FRAME).where(BlockVeinFrame.EYE, Predicates.equalTo(Boolean.valueOf(true))).where(BlockVeinFrame.FACING, Predicates.equalTo(EnumFacing.EAST)))).build();
    }

    @Override
    public String getName()
    {
        return "vein_eye";
    }
}