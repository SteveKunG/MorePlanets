package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockDoubleInfectedStoneBricksSlab extends BlockHalfInfectedStoneBricksSlab
{
    public BlockDoubleInfectedStoneBricksSlab(String name)
    {
        super(Material.rock);
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
        return Item.getItemFromBlock(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, 1, this.getMetaFromState(world.getBlockState(pos)) & 7);
    }
}