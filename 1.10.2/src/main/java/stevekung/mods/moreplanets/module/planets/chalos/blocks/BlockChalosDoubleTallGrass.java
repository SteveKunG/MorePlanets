package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;

public class BlockChalosDoubleTallGrass extends BlockBushMP implements IShearable
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);
    public static PropertyEnum HALF = PropertyEnum.create("half", EnumBlockHalf.class);

    public BlockChalosDoubleTallGrass(String name)
    {
        super(Material.vine);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockType.CHEESE_DOUBLE_TALL_GRASS).withProperty(HALF, EnumBlockHalf.LOWER));
        this.setHardness(0.0F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setStepSound(soundTypeGrass);
        this.setUnlocalizedName(name);
    }

    private BlockType getVariant(IBlockAccess world, BlockPos pos)
    {
        IBlockState iblockstate = world.getBlockState(pos);

        if (iblockstate.getBlock() == this)
        {
            iblockstate = this.getActualState(iblockstate, world, pos);
            return (BlockType)iblockstate.getValue(VARIANT);
        }
        else
        {
            return BlockType.CHEESE_DOUBLE_TALL_GRASS;
        }
    }

    @Override
    public boolean canReplace(World world, BlockPos pos, EnumFacing side, ItemStack stack)
    {
        return super.canReplace(world, pos, side, stack) && world.isAirBlock(pos.up());
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos)
    {
        IBlockState iblockstate = world.getBlockState(pos);

        if (iblockstate.getBlock() != this)
        {
            return true;
        }
        else
        {
            return true;
        }
    }

    @Override
    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(world, pos, state))
        {
            boolean flag = state.getValue(HALF) == EnumBlockHalf.UPPER;
            BlockPos blockpos = flag ? pos : pos.up();
            BlockPos blockpos1 = flag ? pos.down() : pos;
            Block block = flag ? this : world.getBlockState(blockpos).getBlock();
            Block block1 = flag ? world.getBlockState(blockpos1).getBlock() : this;

            if (!flag)
            {
                this.dropBlockAsItem(world, pos, state, 0);
            }
            if (block == this)
            {
                world.setBlockState(blockpos, Blocks.air.getDefaultState(), 2);
            }
            if (block1 == this)
            {
                world.setBlockState(blockpos1, Blocks.air.getDefaultState(), 3);
            }
        }
        else
        {
            boolean flag = state.getValue(HALF) == EnumBlockHalf.UPPER;

            if (!flag && world.getBlockState(pos.up()).getBlock() != this)
            {
                this.dropBlockAsItem(world, pos, state, 0);
                world.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        {
            return null;
        }
        else
        {
            return rand.nextInt(8) == 0 ? ChalosItems.CHEESE_SPORE_SEED : null;
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(HALF) != EnumBlockHalf.UPPER && state.getValue(VARIANT) == BlockType.CHEESE_DOUBLE_TALL_GRASS ? 0 : ((BlockType)state.getValue(VARIANT)).ordinal();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        world.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER), 2);
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState block = world.getBlockState(pos.down());

        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        {
            return world.getBlockState(pos.down()).getBlock() == this;
        }
        if (state == state.withProperty(VARIANT, BlockType.CHEESE_DOUBLE_TALL_GRASS))
        {
            return block.getBlock() == ChalosBlocks.CHEESE_GRASS || block.getBlock() == ChalosBlocks.CHEESE_DIRT || block.getBlock() == ChalosBlocks.CHEESE_FARMLAND;
        }
        else
        {
            IBlockState iblockstate1 = world.getBlockState(pos.up());
            return iblockstate1.getBlock() == this && super.canBlockStay(world, pos, iblockstate1);
        }
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        {
            if (world.getBlockState(pos.down()).getBlock() == this)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    IBlockState iblockstate1 = world.getBlockState(pos.down());
                    world.destroyBlock(pos.down(), true);

                    if (!world.isRemote)
                    {
                        if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears)
                        {
                            this.onHarvest(world, pos, iblockstate1, player);
                            world.setBlockToAir(pos.down());
                        }
                        else
                        {
                            world.destroyBlock(pos.down(), true);
                        }
                    }
                    else
                    {
                        world.setBlockToAir(pos.down());
                    }
                }
                else
                {
                    world.setBlockToAir(pos.down());
                }
            }
        }
        else if (player.capabilities.isCreativeMode && world.getBlockState(pos.up()).getBlock() == this)
        {
            world.setBlockState(pos.up(), Blocks.air.getDefaultState(), 2);
        }
        super.onBlockHarvested(world, pos, state, player);
    }

    private boolean onHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        BlockType[] aBlockType = BlockType.valuesCached();
        int i = aBlockType.length;

        for (int j = 0; j < i; ++j)
        {
            BlockType BlockType = aBlockType[j];
            list.add(new ItemStack(item, 1, BlockType.ordinal()));
        }
    }

    @Override
    public int getDamageValue(World world, BlockPos pos)
    {
        return this.getVariant(world, pos).ordinal();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER) : this.getDefaultState().withProperty(HALF, EnumBlockHalf.LOWER).withProperty(VARIANT, BlockType.valuesCached()[meta & 7]);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        {
            IBlockState iblockstate1 = world.getBlockState(pos.down());

            if (iblockstate1.getBlock() == this)
            {
                state = state.withProperty(VARIANT, iblockstate1.getValue(VARIANT));
            }
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(HALF) == EnumBlockHalf.UPPER ? 8 : ((BlockType)state.getValue(VARIANT)).ordinal();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {HALF, VARIANT});
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getValue(HALF) == EnumBlockHalf.LOWER;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> ret = Lists.newArrayList();
        BlockType type = (BlockType)world.getBlockState(pos).getValue(VARIANT);

        if (type == BlockType.CHEESE_DOUBLE_TALL_GRASS)
        {
            ret.add(new ItemStack(ChalosBlocks.CHEESE_TALL_GRASS, 2));
        }
        return ret;
    }

    @Override
    public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() ==  this && state.getValue(HALF) == EnumBlockHalf.LOWER && world.getBlockState(pos.up()).getBlock() == this)
        {
            world.setBlockToAir(pos.up());
        }
        return world.setBlockToAir(pos);
    }

    public static enum EnumBlockHalf implements IStringSerializable
    {
        UPPER,
        LOWER;

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        @Override
        public String getName()
        {
            return this == UPPER ? "upper" : "lower";
        }
    }

    public static enum BlockType implements IStringSerializable
    {
        CHEESE_DOUBLE_TALL_GRASS;

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