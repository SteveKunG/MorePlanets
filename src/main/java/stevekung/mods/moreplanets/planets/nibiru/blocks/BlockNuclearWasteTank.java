package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteTank;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;

public class BlockNuclearWasteTank extends BlockBaseMP implements ITileEntityProvider
{
    public static final PropertyEnum<BlockType> STATE = PropertyEnum.create("state", BlockType.class);

    public BlockNuclearWasteTank(String name)
    {
        super(Material.IRON);
        this.setHardness(5.0F);
        this.setUnlocalizedName(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STATE, BlockType.NONE));
        this.setSoundType(SoundType.METAL);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (!itemStack.isEmpty())
        {
            if (state.getValue(STATE) == BlockType.NONE)
            {
                if (itemStack.getItem() == MPItems.WASTE_ROD_PICKER)
                {
                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.damageItem(1, player);
                    }
                    Block.spawnAsEntity(world, pos, new ItemStack(MPItems.NUCLEAR_WASTE_ROD));
                    world.setBlockState(pos, state.withProperty(STATE, BlockType.NO_ROD));
                    return true;
                }
            }
            //            if (world.getTileEntity(pos) instanceof TileEntityNuclearWasteTank)
            //            {
            //                world.getTileEntity(pos);
            //                int count = state.getValue(FLUID_COUNT) + 1;
            //
            //                if (state.getValue(FLUID_COUNT) < 3 && world.getBlockState(pos) == state.withProperty(STATE, BlockNuclearWasteTank.BlockType.NO_ROD))
            //                {
            //                    if (itemStack.getItem() == FluidUtil.getFilledBucket(FluidRegistry.getFluidStack("nuclear_waste_fluid", 1000)).getItem())
            //                    {
            //                        if (!player.capabilities.isCreativeMode)
            //                        {
            //                            player.setHeldItem(hand, new ItemStack(Items.BUCKET));
            //                        }
            //                        world.setBlockState(pos, state.withProperty(FLUID_COUNT, count));
            //                        world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F);
            //                        return true;
            //                    }
            //                }
            //            }
        }
        return false;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (state.getValue(STATE) == BlockType.DEPLETE || state.getValue(STATE) == BlockType.NO_ROD)
        {
            return 0;
        }
        else
        {
            return 4;
        }
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
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityNuclearWasteTank)
        {
            ((TileEntityNuclearWasteTank) tile).onDestroy(tile);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<>();
        boolean flag = false;

        if (state.getValue(STATE) == BlockType.DEPLETE || state.getValue(STATE) == BlockType.NO_ROD)
        {
            flag = true;
        }
        if (!flag)
        {
            ret.add(new ItemStack(this, 1, 0));
        }
        return ret;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityNuclearWasteTank();
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, STATE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STATE, BlockType.valuesCached()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(STATE).ordinal();
    }

    public static enum BlockType implements IStringSerializable
    {
        NONE,
        DEPLETE,
        NO_ROD;

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