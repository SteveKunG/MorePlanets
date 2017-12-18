package stevekung.mods.moreplanets.blocks;

import java.util.List;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.ISortableBlock;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class BlockTintedGlassPane extends BlockPane implements IPartialSealableBlock, ISortableBlock
{
    public BlockTintedGlassPane(String name)
    {
        super(Material.glass, false);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(BlockStateHelper.COLOR, EnumDyeColor.WHITE));
        this.setHardness(0.5F);
        this.setResistance(20.0F);
        this.setStepSound(soundTypeGlass);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.BLOCK_TAB;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(BlockStateHelper.COLOR)).getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < EnumDyeColor.values().length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockStateHelper.COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(BlockStateHelper.COLOR)).getMetadata();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {NORTH, EAST, WEST, SOUTH, BlockStateHelper.COLOR});
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            BlockBeacon.updateColorAsync(world, pos);
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            BlockBeacon.updateColorAsync(world, pos);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        float f = 0.4375F;
        float f1 = 0.5625F;
        float f2 = 0.4375F;
        float f3 = 0.5625F;
        boolean flag = this.canGlassPaneConnectToBlock(world.getBlockState(pos.north()).getBlock());
        boolean flag1 = this.canGlassPaneConnectToBlock(world.getBlockState(pos.south()).getBlock());
        boolean flag2 = this.canGlassPaneConnectToBlock(world.getBlockState(pos.west()).getBlock());
        boolean flag3 = this.canGlassPaneConnectToBlock(world.getBlockState(pos.east()).getBlock());

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
        {
            if (flag2)
            {
                f = 0.0F;
            }
            else if (flag3)
            {
                f1 = 1.0F;
            }
        }
        else
        {
            f = 0.0F;
            f1 = 1.0F;
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
        {
            if (flag)
            {
                f2 = 0.0F;
            }
            else if (flag1)
            {
                f3 = 1.0F;
            }
        }
        else
        {
            f2 = 0.0F;
            f3 = 1.0F;
        }
        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }

    private boolean canGlassPaneConnectToBlock(Block block)
    {
        return this.canPaneConnectToBlock(block) || block == MPBlocks.TINTED_GLASS ;
    }

    @Override
    public boolean canPaneConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos off = pos.offset(facing);
        Block block = world.getBlockState(off).getBlock();
        return block == MPBlocks.TINTED_GLASS || super.canPaneConnectTo(world, pos, facing);
    }

    @Override
    public boolean isSealed(World world, BlockPos pos, EnumFacing facing)
    {
        return true;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }
}