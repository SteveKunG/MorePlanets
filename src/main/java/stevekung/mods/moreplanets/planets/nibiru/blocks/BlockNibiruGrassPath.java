package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFarmlandMP;

public class BlockNibiruGrassPath extends BlockBaseMP
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

    public BlockNibiruGrassPath(String name)
    {
        super(Material.GROUND);
        this.setTranslationKey(name);
        this.setLightOpacity(255);
        this.setHardness(0.65F);
        this.setSoundType(SoundType.PLANT);
    }

    @SuppressWarnings("deprecation")
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        switch (side)
        {
        case UP:
            return true;
        case NORTH:
        case SOUTH:
        case WEST:
        case EAST:
            IBlockState iblockstate = world.getBlockState(pos.offset(side));
            Block block = iblockstate.getBlock();
            return !iblockstate.isOpaqueCube() && !(block instanceof BlockFarmlandMP) && block != this;
        default:
            return super.shouldSideBeRendered(state, world, pos, side);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BlockNibiruGrassPath.AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MPBlocks.INFECTED_DIRT);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        if (world.getBlockState(pos.up()).getMaterial().isSolid())
        {
            world.setBlockState(pos, MPBlocks.INFECTED_DIRT.getDefaultState());
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        if (world.getBlockState(pos.up()).getMaterial().isSolid())
        {
            world.setBlockState(pos, MPBlocks.INFECTED_DIRT.getDefaultState());
        }
    }
}