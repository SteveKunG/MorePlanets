package stevekung.mods.moreplanets.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import stevekung.mods.moreplanets.blocks.BlockDummy;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.util.JsonUtil;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;

public class ItemBlockShieldGenerator extends ItemBlockDescription
{
    public ItemBlockShieldGenerator(Block block)
    {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, IBlockState state)
    {
        BlockPos vecToAdd = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        Block block = world.getBlockState(vecToAdd).getBlock();

        if (world.getBlockState(vecToAdd).getMaterial() != Material.AIR && !block.isReplaceable(world, vecToAdd))
        {
            if (world.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtil().text(I18n.format("gui.warning.noroom")).setStyle(new JsonUtil().red()).getFormattedText(), false);
            }
            return false;
        }
        else
        {
            if (!vecToAdd.equals(pos))
            {
                MPBlocks.DUMMY_BLOCK.makeFakeBlock(world, vecToAdd, pos, BlockDummy.BlockType.SHIELD_GENERATOR_TOP);
            }
            return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
        }
    }
}