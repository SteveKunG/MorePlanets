package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockDoublePlant.EnumBlockHalf;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;

public class BlockChalosDoublePlant extends BlockBushMP implements IGrowable, IShearable
{
    public static PropertyEnum<BlockType> VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockChalosDoublePlant(String name)
    {
        super(Material.VINE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockType.DOUBLE_CHEESE_TALL_GRASS).withProperty(BlockDoublePlant.HALF, EnumBlockHalf.LOWER));
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setUnlocalizedName(name);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        if (this.canPlaceBlockAt(world, pos))
        {
            return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
        }
        return world.getBlockState(pos);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        boolean canPlace = world.getBlockState(pos.down()).getBlock() == ChalosBlocks.CHEESE_GRASS || world.getBlockState(pos.down()).getBlock() == ChalosBlocks.CHEESE_DIRT || world.getBlockState(pos.down()).getBlock() == ChalosBlocks.CHEESE_FARMLAND;
        return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && canPlace && world.isAirBlock(pos.up());
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        IBlockState iblockstate = world.getBlockState(pos);

        if (iblockstate.getBlock() != this)
        {
            return true;
        }
        else
        {
            BlockType type = iblockstate.getActualState(world, pos).getValue(VARIANT);
            return type == BlockType.DOUBLE_CHEESE_TALL_GRASS;
        }
    }

    @Override
    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(world, pos, state))
        {
            boolean flag = state.getValue(BlockDoublePlant.HALF) == EnumBlockHalf.UPPER;
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
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
            }
            if (block1 == this)
            {
                world.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
            }
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        boolean canPlace = world.getBlockState(pos.down()).getBlock() == ChalosBlocks.CHEESE_GRASS || world.getBlockState(pos.down()).getBlock() == ChalosBlocks.CHEESE_DIRT || world.getBlockState(pos.down()).getBlock() == ChalosBlocks.CHEESE_FARMLAND;

        if (state.getBlock() != this)
        {
            return canPlace;
        }
        if (state.getValue(BlockDoublePlant.HALF) == EnumBlockHalf.UPPER)
        {
            return world.getBlockState(pos.down()).getBlock() == this;
        }
        else
        {
            IBlockState iblockstate = world.getBlockState(pos.up());
            return iblockstate.getBlock() == this && canPlace;
        }
    }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(BlockDoublePlant.HALF) == EnumBlockHalf.UPPER)
        {
            return null;
        }
        else
        {
            BlockType type = state.getValue(VARIANT);
            return type == BlockType.DOUBLE_CHEESE_TALL_GRASS ? rand.nextInt(8) == 0 ? ChalosItems.CHEESE_SPORE_SEED : null : Item.getItemFromBlock(this);
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(BlockDoublePlant.HALF) != EnumBlockHalf.UPPER && state.getValue(VARIANT) != BlockType.DOUBLE_CHEESE_TALL_GRASS ? state.getValue(VARIANT).ordinal() : 0;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        world.setBlockState(pos.up(), this.getDefaultState().withProperty(BlockDoublePlant.HALF, EnumBlockHalf.UPPER), 2);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (state.getValue(BlockDoublePlant.HALF) == EnumBlockHalf.UPPER)
        {
            if (world.getBlockState(pos.down()).getBlock() == this)
            {
                if (player.capabilities.isCreativeMode)
                {
                    world.setBlockToAir(pos.down());
                }
                else
                {
                    IBlockState iblockstate = world.getBlockState(pos.down());
                    BlockType type = iblockstate.getValue(VARIANT);

                    if (type != BlockType.DOUBLE_CHEESE_TALL_GRASS)
                    {
                        world.destroyBlock(pos.down(), true);
                    }
                    else if (world.isRemote)
                    {
                        world.setBlockToAir(pos.down());
                    }
                    else if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == Items.SHEARS)
                    {
                        this.onHarvest(world, pos, iblockstate, player);
                        world.setBlockToAir(pos.down());
                    }
                    else
                    {
                        world.destroyBlock(pos.down(), true);
                    }
                }
            }
        }
        else if (world.getBlockState(pos.up()).getBlock() == this)
        {
            world.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getType(world, pos, state).ordinal());
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        BlockType type = this.getType(world, pos, state);
        return type != BlockType.DOUBLE_CHEESE_TALL_GRASS;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        Block.spawnAsEntity(world, pos, new ItemStack(this, 1, this.getType(world, pos, state).ordinal()));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(BlockDoublePlant.HALF, EnumBlockHalf.UPPER) : this.getDefaultState().withProperty(BlockDoublePlant.HALF, EnumBlockHalf.LOWER).withProperty(VARIANT, BlockType.valuesCached()[meta & 7]);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (state.getValue(BlockDoublePlant.HALF) == EnumBlockHalf.UPPER)
        {
            IBlockState iblockstate = world.getBlockState(pos.down());

            if (iblockstate.getBlock() == this)
            {
                state = state.withProperty(VARIANT, iblockstate.getValue(VARIANT));
            }
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockDoublePlant.HALF) == EnumBlockHalf.UPPER ? 8 : state.getValue(VARIANT).ordinal();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BlockDoublePlant.HALF, VARIANT});
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XZ;
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        BlockType type = state.getValue(VARIANT);
        return state.getValue(BlockDoublePlant.HALF) == EnumBlockHalf.LOWER && type == BlockType.DOUBLE_CHEESE_TALL_GRASS;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> ret = Lists.newArrayList();
        BlockType type = world.getBlockState(pos).getValue(VARIANT);

        if (type == BlockType.DOUBLE_CHEESE_TALL_GRASS)
        {
            ret.add(new ItemStack(ChalosBlocks.CHEESE_TALL_GRASS, 2));
        }
        return ret;
    }

    private BlockType getType(IBlockAccess world, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() == this)
        {
            state = state.getActualState(world, pos);
            return state.getValue(VARIANT);
        }
        else
        {
            return BlockType.DOUBLE_CHEESE_TALL_GRASS;
        }
    }

    public void placeAt(World world, BlockPos lowerPos, BlockType variant, int flags)
    {
        world.setBlockState(lowerPos, this.getDefaultState().withProperty(BlockDoublePlant.HALF, EnumBlockHalf.LOWER).withProperty(VARIANT, variant), flags);
        world.setBlockState(lowerPos.up(), this.getDefaultState().withProperty(BlockDoublePlant.HALF, EnumBlockHalf.UPPER), flags);
    }

    private boolean onHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BlockType type = state.getValue(VARIANT);

        if (type != BlockType.DOUBLE_CHEESE_TALL_GRASS)
        {
            return false;
        }
        else
        {
            player.addStat(StatList.getBlockStats(this));
            return true;
        }
    }

    public static enum BlockType implements IStringSerializable
    {
        DOUBLE_CHEESE_TALL_GRASS;

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