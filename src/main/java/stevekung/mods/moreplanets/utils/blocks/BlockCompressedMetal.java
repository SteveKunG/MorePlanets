package stevekung.mods.moreplanets.utils.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.entity.EntityAntiGravFallingBlock;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.KoentusBlocks;

public class BlockCompressedMetal extends BlockBaseMP
{
    public BlockCompressedMetal(String name)
    {
        super(Material.IRON);
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.INGOT_BLOCK;
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
    {
        return true;
    }

    @Override
    public BlockCompressedMetal setSoundType(SoundType sound)
    {
        this.blockSoundType = sound;
        return this;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        if (this == KoentusBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK)
        {
            world.scheduleUpdate(pos, this, 2);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        if (this == KoentusBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK)
        {
            world.scheduleUpdate(pos, this, 2);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote && this == KoentusBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK)
        {
            this.checkFallable(world, pos);
        }
    }

    private void checkFallable(World world, BlockPos pos)
    {
        if ((world.isAirBlock(pos.up()) || BlockFalling.canFallThrough(world.getBlockState(pos.up()))) && pos.getY() < 256)
        {
            if (!BlockFalling.fallInstantly && world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                if (!world.isRemote)
                {
                    EntityAntiGravFallingBlock block = new EntityAntiGravFallingBlock(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, world.getBlockState(pos));
                    world.spawnEntity(block);
                }
            }
            else
            {
                IBlockState state = world.getBlockState(pos);
                world.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.up(); (world.isAirBlock(blockpos) || BlockFalling.canFallThrough(world.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.up()) {}

                if (blockpos.getY() < 256)
                {
                    world.setBlockState(blockpos.up(), state);
                }
            }
        }
    }
}