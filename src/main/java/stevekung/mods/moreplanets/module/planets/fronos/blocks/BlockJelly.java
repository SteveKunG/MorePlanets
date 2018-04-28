package stevekung.mods.moreplanets.module.planets.fronos.blocks;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBreakableMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockJelly extends BlockBreakableMP implements IBlockVariants
{
    public static PropertyEnum<BlockType> VARIANT = PropertyEnum.create("variant", BlockType.class);
    private static final Map<BlockType, float[]> JELLY_RGB = new EnumMap<>(BlockType.class);

    static
    {
        BlockJelly.JELLY_RGB.put(BlockType.GRAPE_JELLY_BLOCK, ColorUtils.rgbToFloatArray(230, 77, 201));
        BlockJelly.JELLY_RGB.put(BlockType.RASPBERRY_JELLY_BLOCK, ColorUtils.rgbToFloatArray(250, 68, 112));
        BlockJelly.JELLY_RGB.put(BlockType.STRAWBERRY_JELLY_BLOCK, ColorUtils.rgbToFloatArray(247, 102, 103));
        BlockJelly.JELLY_RGB.put(BlockType.BERRY_JELLY_BLOCK, ColorUtils.rgbToFloatArray(122, 47, 198));
        BlockJelly.JELLY_RGB.put(BlockType.LIME_JELLY_BLOCK, ColorUtils.rgbToFloatArray(197, 254, 136));
        BlockJelly.JELLY_RGB.put(BlockType.ORANGE_JELLY_BLOCK, ColorUtils.rgbToFloatArray(255, 144, 32));
        BlockJelly.JELLY_RGB.put(BlockType.GREEN_JELLY_BLOCK, ColorUtils.rgbToFloatArray(47, 151, 47));
        BlockJelly.JELLY_RGB.put(BlockType.LEMON_JELLY_BLOCK, ColorUtils.rgbToFloatArray(183, 221, 67));
    }

    public BlockJelly(String name)
    {
        super(Material.CLOTH);
        this.setSoundType(MPSounds.SMALL_SLIME);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.GRAPE_JELLY_BLOCK));
        this.setUnlocalizedName(name);
    }

    @Override
    @Nullable
    public float[] getBeaconColorMultiplier(IBlockState state, World world, BlockPos pos, BlockPos beaconPos)
    {
        return BlockJelly.JELLY_RGB.get(state.getValue(VARIANT));
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
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
    {
        if (entity.isSneaking())
        {
            super.onFallenUpon(world, pos, entity, fallDistance);
        }
        else
        {
            entity.fall(fallDistance, 0.0F);
        }
    }

    @Override
    public void onLanded(World world, Entity entity)
    {
        if (entity.isSneaking())
        {
            super.onLanded(world, entity);
        }
        else if (entity.motionY < 0.0D)
        {
            entity.motionY = -entity.motionY;

            if (!(entity instanceof EntityLivingBase))
            {
                entity.motionY *= 0.8D;
            }
        }
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity)
    {
        if (Math.abs(entity.motionY) < 0.1D && !entity.isSneaking())
        {
            double d0 = 0.4D + Math.abs(entity.motionY) * 0.2D;
            entity.motionX *= d0;
            entity.motionZ *= d0;
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        for (int i = 0; i < BlockType.valuesCached().length; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).ordinal();
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("grape", "raspberry", "strawberry", "berry", "lime", "orange", "green", "lemon");
    }

    @Override
    protected boolean isTranslucent()
    {
        return true;
    }

    @Override
    protected boolean renderSideWithState()
    {
        return true;
    }

    @Override
    public boolean isStickyBlock(IBlockState state)
    {
        return true;
    }

    public static enum BlockType implements IStringSerializable
    {
        GRAPE_JELLY_BLOCK,
        RASPBERRY_JELLY_BLOCK,
        STRAWBERRY_JELLY_BLOCK,
        BERRY_JELLY_BLOCK,
        LIME_JELLY_BLOCK,
        ORANGE_JELLY_BLOCK,
        GREEN_JELLY_BLOCK,
        LEMON_JELLY_BLOCK;

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