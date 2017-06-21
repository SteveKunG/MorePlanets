package stevekung.mods.moreplanets.module.planets.nibiru.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = world.getBlockState(pos);

        if (player.canPlayerEdit(pos.offset(side), side, itemStack) && iblockstate.getBlock() == NibiruBlocks.VEIN_FRAME && !iblockstate.getValue(BlockVeinFrame.EYE).booleanValue())
        {
            if (world.isRemote)
            {
                for (int i = 0; i < 16; ++i)
                {
                    double d0 = pos.getX() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
                    double d1 = pos.getY() + 0.8125F;
                    double d2 = pos.getZ() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
                }
                return true;
            }
            else
            {
                world.setBlockState(pos, iblockstate.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(true)), 2);
                world.updateComparatorOutputLevel(pos, NibiruBlocks.VEIN_FRAME);
                --itemStack.stackSize;

                EnumFacing enumfacing = iblockstate.getValue(BlockVeinFrame.FACING);
                int l = 0;
                int j = 0;
                boolean flag1 = false;
                boolean flag = true;
                EnumFacing enumfacing1 = enumfacing.rotateY();

                for (int k = -2; k <= 2; ++k)
                {
                    BlockPos blockpos1 = pos.offset(enumfacing1, k);
                    IBlockState iblockstate1 = world.getBlockState(blockpos1);

                    if (iblockstate1.getBlock() == NibiruBlocks.VEIN_FRAME)
                    {
                        if (!iblockstate1.getValue(BlockVeinFrame.EYE).booleanValue())
                        {
                            flag = false;
                            break;
                        }

                        j = k;

                        if (!flag1)
                        {
                            l = k;
                            flag1 = true;
                        }
                    }
                }

                if (flag && j == l + 2)
                {
                    BlockPos blockpos = pos.offset(enumfacing, 4);

                    for (int i1 = l; i1 <= j; ++i1)
                    {
                        BlockPos blockpos2 = blockpos.offset(enumfacing1, i1);
                        IBlockState iblockstate3 = world.getBlockState(blockpos2);

                        if (iblockstate3.getBlock() != NibiruBlocks.VEIN_FRAME || !iblockstate3.getValue(BlockVeinFrame.EYE).booleanValue())
                        {
                            flag = false;
                            break;
                        }
                    }

                    for (int j1 = l - 1; j1 <= j + 1; j1 += 4)
                    {
                        blockpos = pos.offset(enumfacing1, j1);

                        for (int l1 = 1; l1 <= 3; ++l1)
                        {
                            BlockPos blockpos3 = blockpos.offset(enumfacing, l1);
                            IBlockState iblockstate2 = world.getBlockState(blockpos3);

                            if (iblockstate2.getBlock() != NibiruBlocks.VEIN_FRAME || !iblockstate2.getValue(BlockVeinFrame.EYE).booleanValue())
                            {
                                flag = false;
                                break;
                            }
                        }
                    }

                    if (flag)
                    {
                        for (int k1 = l; k1 <= j; ++k1)
                        {
                            blockpos = pos.offset(enumfacing1, k1);

                            for (int i2 = 1; i2 <= 3; ++i2)
                            {
                                BlockPos blockpos4 = blockpos.offset(enumfacing, i2);
                                world.setBlockState(blockpos4, NibiruBlocks.VEIN_PORTAL.getDefaultState(), 2);

                            }
                        }
                    }
                }
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, false);

        if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && world.getBlockState(movingobjectposition.getBlockPos()).getBlock() == NibiruBlocks.VEIN_FRAME && !world.getBlockState(movingobjectposition.getBlockPos()).getValue(BlockVeinFrame.EYE).booleanValue())
        {
            return itemStack;
        }
        else
        {
            if (!world.isRemote)
            {
                BlockPos blockpos = world.getStrongholdPos("NibiruStronghold", new BlockPos(player));

                if (blockpos != null)
                {
                    EntityVeinEye entityendereye = new EntityVeinEye(world, player.posX, player.posY, player.posZ);
                    entityendereye.moveTowards(blockpos);
                    world.spawnEntityInWorld(entityendereye);
                    world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    world.playAuxSFXAtEntity((EntityPlayer)null, 1002, new BlockPos(player), 0);

                    if (!player.capabilities.isCreativeMode)
                    {
                        --itemStack.stackSize;
                    }
                }
            }
            return itemStack;
        }
    }

    @Override
    public String getName()
    {
        return "vein_eye";
    }
}