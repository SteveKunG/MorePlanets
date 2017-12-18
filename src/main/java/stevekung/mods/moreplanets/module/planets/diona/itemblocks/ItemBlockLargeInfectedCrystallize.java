package stevekung.mods.moreplanets.module.planets.diona.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityLargeInfectedCrystallize;

public class ItemBlockLargeInfectedCrystallize extends ItemBlock
{
    public ItemBlockLargeInfectedCrystallize(Block block)
    {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, IBlockState state)
    {
        if (super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state))
        {
            TileEntityLargeInfectedCrystallize ts = (TileEntityLargeInfectedCrystallize)world.getTileEntity(pos);
            ts.facing = facing.getIndex();
        }

        world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, Block.soundTypeGlass.getPlaceSound(), (Block.soundTypeGlass.getVolume() + 1.0F) / 2.0F, Block.soundTypeGlass.getFrequency() * 0.8F);

        if (!player.capabilities.isCreativeMode)
        {
            itemStack.stackSize--;
        }
        return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
    }
}