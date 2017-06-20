package stevekung.mods.moreplanets.module.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityMultalicCrystal;

public class ItemBlockMultalicCrystal extends ItemBlock
{
    public ItemBlockMultalicCrystal(Block block)
    {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, IBlockState state)
    {
        if (super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state))
        {
            TileEntityMultalicCrystal ts = (TileEntityMultalicCrystal)world.getTileEntity(pos);
            ts.facing = facing.getIndex();
        }

        world.playSound(player, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, SoundType.GLASS.getPlaceSound(), SoundCategory.BLOCKS, (SoundType.GLASS.getVolume() + 1.0F) / 2.0F, SoundType.GLASS.getPitch() * 0.8F);

        if (!player.capabilities.isCreativeMode)
        {
            itemStack.stackSize--;
        }
        return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
    }
}