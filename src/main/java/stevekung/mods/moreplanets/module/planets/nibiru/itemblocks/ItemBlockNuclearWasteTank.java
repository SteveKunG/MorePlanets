package stevekung.mods.moreplanets.module.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.itemblocks.ItemBlockTESRMP;
import stevekung.mods.stevekunglib.utils.ClientUtils;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemBlockNuclearWasteTank extends ItemBlockTESRMP
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
                MPBlocks.NWT_MIDDLE_DUMMY.makeFakeBlock(world, vecToAdd, pos);
            }
            if (!vecToAdd1.equals(pos))
            {
                MPBlocks.NWT_TOP_DUMMY.makeFakeBlock(world, vecToAdd1, pos);
            }
            return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
        }
    }
}