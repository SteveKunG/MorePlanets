package stevekung.mods.moreplanets.blocks.decoration;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;

public class BlockDoubleWoodenSlab1 extends BlockHalfWoodenSlab1
{
    public BlockDoubleWoodenSlab1(String name)
    {
        super(Material.wood);
        this.setStepSound(soundTypeWood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean isDouble()
    {
        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MPBlocks.HALF_WOODEN_SLAB_1);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(MPBlocks.HALF_WOODEN_SLAB_1, 1, this.getMetaFromState(world.getBlockState(pos)) & 7);
    }
}