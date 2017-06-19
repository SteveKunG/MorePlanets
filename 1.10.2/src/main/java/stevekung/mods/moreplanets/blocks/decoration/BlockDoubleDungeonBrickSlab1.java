package stevekung.mods.moreplanets.blocks.decoration;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;

public class BlockDoubleDungeonBrickSlab1 extends BlockHalfDungeonBrickSlab1
{
    public BlockDoubleDungeonBrickSlab1(String name)
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
        return Item.getItemFromBlock(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, 1, this.getMetaFromState(world.getBlockState(pos)) & 7);
    }
}