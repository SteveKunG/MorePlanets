package stevekung.mods.moreplanets.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockDescriptionTESR;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

public class ItemBlockDarkEnergyReceiver extends ItemBlockDescriptionTESR
{
    public ItemBlockDarkEnergyReceiver(Block block)
    {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, IBlockState state)
    {
        int angle = MathHelper.floor(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = EnumFacing.byHorizontalIndex(angle).getOpposite().getHorizontalIndex();

        if (BlockUtils.isFluid(world, pos))
        {
            if (world.isRemote)
            {
                ClientUtils.setOverlayMessage(JsonUtils.create(LangUtils.translate("gui.place_in_liquid.message")).setStyle(JsonUtils.red()));
            }
            return false;
        }

        if (change == 0 || change == 2)
        {
            BlockPos vecToAdd = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
            BlockPos vecToAdd1 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
            Block block = world.getBlockState(vecToAdd).getBlock();
            Block block1 = world.getBlockState(vecToAdd1).getBlock();

            if (world.getBlockState(vecToAdd).getMaterial() != Material.AIR && !block.isReplaceable(world, vecToAdd))
            {
                if (world.isRemote)
                {
                    ClientUtils.setOverlayMessage(JsonUtils.create(LangUtils.translate("gui.warning.noroom")).setStyle(JsonUtils.red()));
                }
                return false;
            }
            else if (world.getBlockState(vecToAdd1).getMaterial() != Material.AIR && !block1.isReplaceable(world, vecToAdd1))
            {
                if (world.isRemote)
                {
                    ClientUtils.setOverlayMessage(JsonUtils.create(LangUtils.translate("gui.warning.noroom")).setStyle(JsonUtils.red()));
                }
                return false;
            }
            else
            {
                if (!vecToAdd.equals(pos))
                {
                    MPBlocks.DER_SOLAR1_DUMMY.makeFakeBlock(world, vecToAdd, pos);
                }
                if (!vecToAdd1.equals(pos))
                {
                    MPBlocks.DER_SOLAR2_DUMMY.makeFakeBlock(world, vecToAdd1, pos);
                }
                return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
            }
        }
        if (change == 1 || change == 3)
        {
            BlockPos vecToAdd = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
            BlockPos vecToAdd1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
            Block block = world.getBlockState(vecToAdd).getBlock();
            Block block1 = world.getBlockState(vecToAdd1).getBlock();

            if (world.getBlockState(vecToAdd).getMaterial() != Material.AIR && !block.isReplaceable(world, vecToAdd))
            {
                if (world.isRemote)
                {
                    ClientUtils.setOverlayMessage(JsonUtils.create(LangUtils.translate("gui.warning.noroom")).setStyle(JsonUtils.red()));
                }
                return false;
            }
            else if (world.getBlockState(vecToAdd1).getMaterial() != Material.AIR && !block1.isReplaceable(world, vecToAdd1))
            {
                if (world.isRemote)
                {
                    ClientUtils.setOverlayMessage(JsonUtils.create(LangUtils.translate("gui.warning.noroom")).setStyle(JsonUtils.red()));
                }
                return false;
            }
            else
            {
                if (!vecToAdd.equals(pos))
                {
                    MPBlocks.DER_SOLAR3_DUMMY.makeFakeBlock(world, vecToAdd, pos);
                }
                if (!vecToAdd1.equals(pos))
                {
                    MPBlocks.DER_SOLAR4_DUMMY.makeFakeBlock(world, vecToAdd1, pos);
                }
                return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
            }
        }
        return false;
    }
}