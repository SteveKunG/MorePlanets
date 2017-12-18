package stevekung.mods.moreplanets.util.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemDoorMP extends ItemBaseMP
{
    private Block door;

    public ItemDoorMP(String name, Block door)
    {
        this.name = name;
        this.door = door;
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.DOOR;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (facing != EnumFacing.UP)
        {
            return false;
        }
        else
        {
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            block = this.door;

            if (!block.isReplaceable(world, pos))
            {
                pos = pos.offset(facing);
            }

            if (!player.canPlayerEdit(pos, facing, itemStack))
            {
                return false;
            }
            else if (!block.canPlaceBlockAt(world, pos))
            {
                return false;
            }
            else
            {
                this.placeDoor(world, pos, EnumFacing.fromAngle(player.rotationYaw), block);
                --itemStack.stackSize;
                return true;
            }
        }
    }

    private void placeDoor(World world, BlockPos pos, EnumFacing facing, Block door)
    {
        BlockPos blockpos1 = pos.offset(facing.rotateY());
        BlockPos blockpos2 = pos.offset(facing.rotateYCCW());
        int i = (world.getBlockState(blockpos2).getBlock().isNormalCube() ? 1 : 0) + (world.getBlockState(blockpos2.up()).getBlock().isNormalCube() ? 1 : 0);
        int j = (world.getBlockState(blockpos1).getBlock().isNormalCube() ? 1 : 0) + (world.getBlockState(blockpos1.up()).getBlock().isNormalCube() ? 1 : 0);
        boolean flag = world.getBlockState(blockpos2).getBlock() == door || world.getBlockState(blockpos2.up()).getBlock() == door;
        boolean flag1 = world.getBlockState(blockpos1).getBlock() == door || world.getBlockState(blockpos1.up()).getBlock() == door;
        boolean flag2 = false;

        if (flag && !flag1 || j > i)
        {
            flag2 = true;
        }
        BlockPos blockpos3 = pos.up();
        IBlockState iblockstate = door.getDefaultState().withProperty(BlockDoor.FACING, facing).withProperty(BlockDoor.HINGE, flag2 ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT);
        world.setBlockState(pos, iblockstate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER), 2);
        world.setBlockState(blockpos3, iblockstate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2);
        world.notifyNeighborsOfStateChange(pos, door);
        world.notifyNeighborsOfStateChange(blockpos3, door);
        world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, door.stepSound.getPlaceSound(), (door.stepSound.getVolume() + 1.0F) / 2.0F, door.stepSound.getFrequency() * 0.8F);
    }
}