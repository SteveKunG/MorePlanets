package stevekung.mods.moreplanets.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import stevekung.mods.moreplanets.blocks.BlockDummy;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.util.JsonUtils;
import stevekung.mods.moreplanets.util.helper.BlockEventHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;

public class ItemBlockDarkEnergyReceiver extends ItemBlockDescription
{
    public ItemBlockDarkEnergyReceiver(Block block)
    {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, IBlockState state)
    {
        int angle = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = EnumFacing.getHorizontal(angle).getOpposite().getHorizontalIndex();

        if (BlockEventHelper.isLiquidBlock(world, pos))
        {
            if (world.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.place_in_liquid.message")).setStyle(new JsonUtils().red()).getFormattedText(), false);
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
                    FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.warning.noroom")).setStyle(new JsonUtils().red()).getFormattedText(), false);
                }
                return false;
            }
            else if (world.getBlockState(vecToAdd1).getMaterial() != Material.AIR && !block1.isReplaceable(world, vecToAdd1))
            {
                if (world.isRemote)
                {
                    FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.warning.noroom")).setStyle(new JsonUtils().red()).getFormattedText(), false);
                }
                return false;
            }
            else
            {
                if (!vecToAdd.equals(pos))
                {
                    MPBlocks.DUMMY_BLOCK.makeFakeBlock(world, vecToAdd, pos, BlockDummy.BlockType.DARK_ENERGY_SOLAR1);
                }
                if (!vecToAdd1.equals(pos))
                {
                    MPBlocks.DUMMY_BLOCK.makeFakeBlock(world, vecToAdd1, pos, BlockDummy.BlockType.DARK_ENERGY_SOLAR2);
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
                    FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.warning.noroom")).setStyle(new JsonUtils().red()).getFormattedText(), false);
                }
                return false;
            }
            else if (world.getBlockState(vecToAdd1).getMaterial() != Material.AIR && !block1.isReplaceable(world, vecToAdd1))
            {
                if (world.isRemote)
                {
                    FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.warning.noroom")).setStyle(new JsonUtils().red()).getFormattedText(), false);
                }
                return false;
            }
            else
            {
                if (!vecToAdd.equals(pos))
                {
                    MPBlocks.DUMMY_BLOCK.makeFakeBlock(world, vecToAdd, pos, BlockDummy.BlockType.DARK_ENERGY_SOLAR3);
                }
                if (!vecToAdd1.equals(pos))
                {
                    MPBlocks.DUMMY_BLOCK.makeFakeBlock(world, vecToAdd1, pos, BlockDummy.BlockType.DARK_ENERGY_SOLAR4);
                }
                return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
            }
        }
        return false;
    }
}