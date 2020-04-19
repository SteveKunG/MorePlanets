package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockDoubleInfectedStoneBricksSlab extends BlockHalfInfectedStoneBricksSlab
{
    public BlockDoubleInfectedStoneBricksSlab(String name)
    {
        super(Material.ROCK);
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
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, 1, this.getMetaFromState(state) & 3);
    }
}