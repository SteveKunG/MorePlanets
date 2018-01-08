package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockSlabMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockHalfInfectedPrismarineSlab extends BlockSlabMP
{
    public static PropertyEnum<BlockType> VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockHalfInfectedPrismarineSlab(String name)
    {
        super(Material.ROCK);
        this.setUnlocalizedName(name);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.useNeighborBrightness = true;
        IBlockState state = this.blockState.getBaseState();

        if (!this.isDouble())
        {
            state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
        this.setDefaultState(state.withProperty(VARIANT, BlockType.INFECTED_PRISMARINE_SLAB));
    }

    public BlockHalfInfectedPrismarineSlab(Material material)
    {
        super(material);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.useNeighborBrightness = true;
        IBlockState state = this.blockState.getBaseState();

        if (!this.isDouble())
        {
            state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
        this.setDefaultState(state.withProperty(VARIANT, BlockType.INFECTED_PRISMARINE_SLAB));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        for (int i = 0; i < BlockType.valuesCached().length; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack itemStack)
    {
        return BlockType.valuesCached()[itemStack.getMetadata() & 7];
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta & 7]);

        if (!this.isDouble())
        {
            state = state.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | state.getValue(VARIANT).ordinal();

        if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP)
        {
            i |= 8;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.SLAB_STONE;
    }

    @Override
    public BlockSlabMP getHalf()
    {
        return NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB;
    }

    @Override
    public BlockSlabMP getDouble()
    {
        return NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB;
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("prismarine", "bricks", "dark");
    }

    public static enum BlockType implements IStringSerializable
    {
        INFECTED_PRISMARINE_SLAB,
        INFECTED_PRISMARINE_BRICKS_SLAB,
        INFECTED_DARK_PRISMARINE_SLAB;

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