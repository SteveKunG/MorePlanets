package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockNibiruTallGrass extends BlockBushMP implements IShearable, IBlockVariants, IGrowable
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockNibiruTallGrass(String name)
    {
        super(Material.plants);
        this.setUnlocalizedName(name);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.INFECTED_TALL_GRASS));
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        if (state.getValue(VARIANT) != BlockType.GREEN_VEIN_TALL_GRASS)
        {
            List<ItemStack> ret = Lists.newArrayList();

            if (RANDOM.nextInt(8) != 0)
            {
                return ret;
            }
            ret.add(new ItemStack(NibiruItems.INFECTED_WHEAT_SEEDS));
            return ret;
        }
        else
        {
            List<ItemStack> ret = Lists.newArrayList();

            if (RANDOM.nextInt(24) != 0)
            {
                return ret;
            }
            ret.add(new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 6));
            return ret;
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState soil = world.getBlockState(pos.down());
        int meta = this.getMetaFromState(state);

        if (meta == 0 || meta == 1)
        {
            return soil.getBlock() == NibiruBlocks.INFECTED_GRASS || soil.getBlock() == NibiruBlocks.INFECTED_DIRT;
        }
        else
        {
            return soil.getBlock() == NibiruBlocks.GREEN_VEIN_GRASS || soil.getBlock() == NibiruBlocks.INFECTED_DIRT;
        }
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos)
    {
        return true;
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
    @SideOnly(Side.CLIENT)
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XYZ;
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> ret = Lists.newArrayList();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockType)state.getValue(VARIANT)).ordinal();
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("tall_grass", "fern", "green_vein");
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        BlockNibiruDoublePlant.BlockType type = BlockNibiruDoublePlant.BlockType.DOUBLE_INFECTED_GRASS;

        if (state.getValue(VARIANT) == BlockType.INFECTED_FERN)
        {
            type = BlockNibiruDoublePlant.BlockType.DOUBLE_INFECTED_FERN;
        }
        if (state.getValue(VARIANT) == BlockType.GREEN_VEIN_TALL_GRASS)
        {
            type = BlockNibiruDoublePlant.BlockType.DOUBLE_GREEN_VEIN_GRASS;
        }
        if (NibiruBlocks.NIBIRU_DOUBLE_PLANT.canPlaceBlockAt(world, pos))
        {
            ((BlockNibiruDoublePlant)NibiruBlocks.NIBIRU_DOUBLE_PLANT).placeAt(world, pos, type, 2);
        }
    }

    public static enum BlockType implements IStringSerializable
    {
        INFECTED_TALL_GRASS,
        INFECTED_FERN,
        GREEN_VEIN_TALL_GRASS;

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