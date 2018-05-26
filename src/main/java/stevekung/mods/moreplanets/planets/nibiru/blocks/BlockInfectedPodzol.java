package stevekung.mods.moreplanets.planets.nibiru.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;

public class BlockInfectedPodzol extends BlockBaseMP
{
    private static final PropertyBool BLOCK_ABOVE = PropertyBool.create("block_above");
    private static final PropertyBool SNOWY = PropertyBool.create("snowy");

    public BlockInfectedPodzol(String name)
    {
        super(Material.GROUND);
        this.setUnlocalizedName(name);
        this.setHardness(0.55F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, false).withProperty(BLOCK_ABOVE, false));
        this.setSoundType(SoundType.GROUND);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        Block block = world.getBlockState(pos.up()).getBlock();

        if (block == MPBlocks.INFECTED_SNOW || block == MPBlocks.INFECTED_SNOW_LAYER)
        {
            state = state.withProperty(SNOWY, true);
        }
        else
        {
            if (world.getBlockState(pos.up()).isSideSolid(world, pos, EnumFacing.UP))
            {
                state = state.withProperty(BLOCK_ABOVE, true);
            }
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, SNOWY, BLOCK_ABOVE);
    }
}