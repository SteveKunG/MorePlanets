package stevekung.mods.moreplanets.module.planets.fronos.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockLogMP;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper.EnumAxis;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper.SwitchEnumAxis;

public class BlockCandyCane1 extends BlockLogMP implements IBlockVariants
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockCandyCane1(String name)
    {
        super(Material.cloth);
        this.setHardness(0.55F);
        this.setResistance(3.0F);
        this.setStepSound(soundTypeCloth);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.RED_CANDY_CANE).withProperty(BlockStateHelper.AXIS, EnumAxis.Y));
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockType.valuesCached().length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta & 3]);

        switch (meta & 12)
        {
        case 0:
            state = state.withProperty(BlockStateHelper.AXIS, EnumAxis.Y);
            break;
        case 4:
            state = state.withProperty(BlockStateHelper.AXIS, EnumAxis.X);
            break;
        case 8:
            state = state.withProperty(BlockStateHelper.AXIS, EnumAxis.Z);
            break;
        default:
            state = state.withProperty(BlockStateHelper.AXIS, EnumAxis.NONE);
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        byte b = 0;
        int i = b | ((BlockType)state.getValue(VARIANT)).ordinal();

        switch (SwitchEnumAxis.AXIS_LOOKUP[((EnumAxis)state.getValue(BlockStateHelper.AXIS)).ordinal()])
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
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT, BlockStateHelper.AXIS });
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(this, 1, ((BlockType)state.getValue(VARIANT)).ordinal());
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return ((BlockType)state.getValue(VARIANT)).ordinal();
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("red", "green", "blue", "orange");
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, BlockPos pos)
    {
        return false;
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos)
    {
        return false;
    }

    public static enum BlockType implements IStringSerializable
    {
        RED_CANDY_CANE,
        GREEN_CANDY_CANE,
        BLUE_CANDY_CANE,
        ORANGE_CANDY_CANE;

        private static BlockType[] values = BlockType.values();

        public static BlockType[] valuesCached()
        {
            return BlockType.values;
        }

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
    }
}