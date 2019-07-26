package stevekung.mods.moreplanets.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockDescriptionTESR;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

public class ItemBlockShieldGenerator extends ItemBlockDescriptionTESR
{
    public ItemBlockShieldGenerator(Block block)
    {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, IBlockState state)
    {
        for (int y = 0; y < 2; y++)
        {
            IBlockState stateAt = world.getBlockState(pos.add(0, y, 0));
            int buildHeight = world.getHeight() - 1;

            if (!stateAt.getMaterial().isReplaceable() || pos.getY() + y > buildHeight)
            {
                if (world.isRemote)
                {
                    ClientUtils.setOverlayMessage(JsonUtils.create(LangUtils.translate("gui.warning.noroom")).setStyle(JsonUtils.red()).getFormattedText());
                }
                return false;
            }
        }
        return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
    }
}