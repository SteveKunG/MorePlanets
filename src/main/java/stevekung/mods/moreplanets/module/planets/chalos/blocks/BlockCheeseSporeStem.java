package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import stevekung.mods.moreplanets.util.blocks.BlockLogMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockCheeseSporeStem extends BlockLogMP
{
    public BlockCheeseSporeStem(String name)
    {
        super(Material.WOOD);
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.AXIS, BlockStateProperty.EnumAxis.Y));
        this.setUnlocalizedName(name);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState();

        switch (meta & 12)
        {
        case 0:
            state = state.withProperty(BlockStateProperty.AXIS, BlockStateProperty.EnumAxis.Y);
            break;
        case 4:
            state = state.withProperty(BlockStateProperty.AXIS, BlockStateProperty.EnumAxis.X);
            break;
        case 8:
            state = state.withProperty(BlockStateProperty.AXIS, BlockStateProperty.EnumAxis.Z);
            break;
        default:
            state = state.withProperty(BlockStateProperty.AXIS, BlockStateProperty.EnumAxis.NONE);
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        switch (BlockStateProperty.SwitchEnumAxis.AXIS_LOOKUP[state.getValue(BlockStateProperty.AXIS).ordinal()])
        {
        case 1:
            i |= 4;
            break;
        case 2:
            i |= 8;
            break;
        case 3:
            i |= 12;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BlockStateProperty.AXIS);
    }

    @Override
    public String getName()
    {
        return "cheese_spore_stem";
    }
}