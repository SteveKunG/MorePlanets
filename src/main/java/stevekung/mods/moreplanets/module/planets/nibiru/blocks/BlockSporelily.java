package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.utils.blocks.BlockBushMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;

public class BlockSporelily extends BlockBushMP
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);

    public BlockSporelily(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setSoundType(MPSounds.LILYPAD);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean push)
    {
        if (!(entity instanceof EntityBoat))
        {
            Block.addCollisionBoxToList(pos, entityBox, collidingBoxes, BlockSporelily.AABB);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (entity instanceof EntityBoat)
        {
            world.destroyBlock(pos, true);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BlockSporelily.AABB;
    }

    @Override
    public boolean validBlock(Block block)
    {
        return block == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos.down());
        return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && state.getBlock() == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK && state.getValue(BlockFluidBase.LEVEL) == 0;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        if (pos.getY() >= 0 && pos.getY() < 256)
        {
            IBlockState iblockstate = world.getBlockState(pos.down());
            return iblockstate.getBlock() == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK && iblockstate.getValue(BlockFluidBase.LEVEL) == 0;
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }
}