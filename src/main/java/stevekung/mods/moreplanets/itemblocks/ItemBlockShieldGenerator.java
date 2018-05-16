package stevekung.mods.moreplanets.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockDescription;
import stevekung.mods.stevekunglib.utils.ClientUtils;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

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
                ClientUtils.setOverlayMessage(JsonUtils.create(LangUtils.translate("gui.warning.noroom")).setStyle(JsonUtils.red()).getFormattedText());
            }
            return false;
        }
        else if (vecToAdd.getY() > 255)
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
                MPBlocks.SHIELD_GENERATOR_DUMMY.makeFakeBlock(world, vecToAdd, pos);
            }
            return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
        }
    }
}