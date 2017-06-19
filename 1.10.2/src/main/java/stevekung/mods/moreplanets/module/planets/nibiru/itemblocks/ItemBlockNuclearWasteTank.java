package stevekung.mods.moreplanets.module.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import stevekung.mods.moreplanets.blocks.BlockDummy;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.util.JsonUtils;

public class ItemBlockNuclearWasteTank extends ItemBlock
{
    public ItemBlockNuclearWasteTank(Block block)
    {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, IBlockState state)
    {
        BlockPos vecToAdd = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        BlockPos vecToAdd1 = new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ());
        Block block = world.getBlockState(vecToAdd).getBlock();
        Block block1 = world.getBlockState(vecToAdd1).getBlock();

        if (block.getMaterial() != Material.air && !block.isReplaceable(world, vecToAdd))
        {
            if (world.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.warning.noroom")).setChatStyle(new JsonUtils().red()).getFormattedText(), false);
            }
            return false;
        }
        else if (block1.getMaterial() != Material.air && !block1.isReplaceable(world, vecToAdd1))
        {
            if (world.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.warning.noroom")).setChatStyle(new JsonUtils().red()).getFormattedText(), false);
            }
            return false;
        }
        else
        {
            if (!vecToAdd.equals(pos))
            {
                MPBlocks.DUMMY_BLOCK.makeFakeBlock(world, vecToAdd, pos, BlockDummy.BlockType.NUCLEAR_WASTE_TANK_MIDDLE);
            }
            if (!vecToAdd1.equals(pos))
            {
                MPBlocks.DUMMY_BLOCK.makeFakeBlock(world, vecToAdd1, pos, BlockDummy.BlockType.NUCLEAR_WASTE_TANK_TOP);
            }
            return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
        }
    }
}