package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockNibiruDoublePlant extends BlockBushMP implements IGrowable, IShearable, IBlockVariants
{
    public static PropertyEnum<BlockType> VARIANT = PropertyEnum.create("variant", BlockType.class);
    public static PropertyEnum<EnumBlockHalf> HALF = PropertyEnum.create("half", EnumBlockHalf.class);

    public BlockNibiruDoublePlant(String name)
    {
        super(Material.VINE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockType.INFECTED_ORANGE_ROSE_BUSH).withProperty(HALF, EnumBlockHalf.LOWER));
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean canReplace(World world, BlockPos pos, EnumFacing side, ItemStack itemStack)
    {
        return super.canReplace(world, pos, side, itemStack) && world.getBlockState(pos.up()).getBlock() == Blocks.AIR;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() != this)
        {
            return true;
        }
        else
        {
            BlockType type = this.getActualState(state, world, pos).getValue(VARIANT);
            return type == BlockType.DOUBLE_INFECTED_FERN || type == BlockType.DOUBLE_INFECTED_GRASS || type == BlockType.DOUBLE_GREEN_VEIN_GRASS;
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
        IBlockState block = world.getBlockState(pos.down());

        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        {
            return block.getBlock() == this;
        }
        else
        {
            BlockType type = state.getValue(VARIANT);

            if (type != BlockType.DOUBLE_GREEN_VEIN_GRASS)
            {
                return block.getBlock() == NibiruBlocks.INFECTED_GRASS || block.getBlock() == NibiruBlocks.INFECTED_DIRT;
            }
            else
            {
                return block.getBlock() == NibiruBlocks.GREEN_VEIN_GRASS || block.getBlock() == NibiruBlocks.INFECTED_DIRT;
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
            BlockType type = state.getValue(VARIANT);

            if (type == BlockType.DOUBLE_INFECTED_FERN)
            {
                return null;
            }
            else if (type == BlockType.DOUBLE_INFECTED_GRASS)
            {
                return rand.nextInt(8) == 0 ? NibiruItems.INFECTED_WHEAT_SEEDS : null;
            }
            else if (type == BlockType.DOUBLE_GREEN_VEIN_GRASS)
            {
                return rand.nextInt(8) == 0 ? NibiruItems.NIBIRU_FRUITS : null;
            }
            else
            {
                return Item.getItemFromBlock(this);
            }
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        if (state.getValue(HALF) != EnumBlockHalf.UPPER && state.getValue(VARIANT) != BlockType.DOUBLE_INFECTED_GRASS && state.getValue(VARIANT) != BlockType.DOUBLE_INFECTED_FERN && state.getValue(VARIANT) != BlockType.DOUBLE_GREEN_VEIN_GRASS)
        {
            return state.getValue(VARIANT).ordinal();
        }
        else if (state.getValue(VARIANT) == BlockType.DOUBLE_GREEN_VEIN_GRASS)
        {
            return 6;
        }
        return 0;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        world.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER), 2);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (state.getValue(HALF) == EnumBlockHalf.UPPER)
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

                    if (type != BlockType.DOUBLE_INFECTED_FERN && type != BlockType.DOUBLE_INFECTED_GRASS && type != BlockType.DOUBLE_GREEN_VEIN_GRASS)
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
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> list)
    {
        for (BlockType type : BlockType.valuesCached())
        {
            list.add(new ItemStack(item, 1, type.ordinal()));
        }
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        BlockType type = this.getVariant(world, pos);
        return type != BlockType.DOUBLE_INFECTED_GRASS && type != BlockType.DOUBLE_INFECTED_FERN && type != BlockType.DOUBLE_GREEN_VEIN_GRASS;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        Block.spawnAsEntity(world, pos, new ItemStack(this, 1, this.getVariant(world, pos).ordinal()));
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
        return state.getValue(HALF) == EnumBlockHalf.UPPER ? 8 : state.getValue(VARIANT).ordinal();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XZ;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        BlockType type = state.getValue(VARIANT);
        return state.getValue(HALF) == EnumBlockHalf.LOWER && (type == BlockType.DOUBLE_INFECTED_FERN || type == BlockType.DOUBLE_INFECTED_GRASS || type == BlockType.DOUBLE_GREEN_VEIN_GRASS);
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> ret = Lists.newArrayList();
        BlockType type = world.getBlockState(pos).getValue(VARIANT);

        if (type == BlockType.DOUBLE_INFECTED_GRASS)
        {
            ret.add(new ItemStack(NibiruBlocks.NIBIRU_TALL_GRASS, 2, 0));
        }
        if (type == BlockType.DOUBLE_INFECTED_FERN)
        {
            ret.add(new ItemStack(NibiruBlocks.NIBIRU_TALL_GRASS, 2, 1));
        }
        if (type == BlockType.DOUBLE_GREEN_VEIN_GRASS)
        {
            ret.add(new ItemStack(NibiruBlocks.NIBIRU_TALL_GRASS, 2, 2));
        }
        return ret;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        if (state.getBlock() == this && state.getValue(HALF) == EnumBlockHalf.LOWER && world.getBlockState(pos.up()).getBlock() == this)
        {
            world.setBlockToAir(pos.up());
        }
        return world.setBlockToAir(pos);
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("orange_rose_bush", "infected_grass", "infected_fern", "green_vein_grass");
    }

    public void placeAt(World world, BlockPos lowerPos, BlockType variant, int flags)
    {
        world.setBlockState(lowerPos, this.getDefaultState().withProperty(HALF, EnumBlockHalf.LOWER).withProperty(VARIANT, variant), flags);
        world.setBlockState(lowerPos.up(), this.getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER), flags);
    }

    public BlockType getVariant(IBlockAccess world, BlockPos pos)
    {
        IBlockState iblockstate = world.getBlockState(pos);

        if (iblockstate.getBlock() == this)
        {
            iblockstate = this.getActualState(iblockstate, world, pos);
            return iblockstate.getValue(VARIANT);
        }
        else
        {
            return BlockType.DOUBLE_INFECTED_FERN;
        }
    }

    private boolean onHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BlockType type = state.getValue(VARIANT);

        if (type != BlockType.DOUBLE_INFECTED_FERN && type != BlockType.DOUBLE_INFECTED_GRASS && type != BlockType.DOUBLE_GREEN_VEIN_GRASS)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static enum EnumBlockHalf implements IStringSerializable
    {
        UPPER,
        LOWER;

        @Override
        public String toString()
        {
            return this.getName();
        }

        @Override
        public String getName()
        {
            return this == UPPER ? "upper" : "lower";
        }
    }

    public static enum BlockType implements IStringSerializable
    {
        INFECTED_ORANGE_ROSE_BUSH,
        DOUBLE_INFECTED_GRASS,
        DOUBLE_INFECTED_FERN,
        DOUBLE_GREEN_VEIN_GRASS;

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