package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteTank;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockNuclearWasteTank extends BlockBaseMP implements ITileEntityProvider
{
    public static PropertyBool DEPLETE = PropertyBool.create("deplete");

    public BlockNuclearWasteTank(String name)
    {
        super(Material.iron);
        this.setHardness(5.0F);
        this.setUnlocalizedName(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(DEPLETE, Boolean.valueOf(false)));
        this.setStepSound(Block.soundTypeMetal);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.inventory.getCurrentItem();
        TileEntity tile = world.getTileEntity(pos);

        if (itemStack != null && state == state.withProperty(DEPLETE, false))
        {
            if (itemStack.getItem() == NibiruItems.WASTE_ROD_PICKER)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    itemStack.damageItem(1, player);
                }
                if (tile instanceof TileEntityNuclearWasteTank)
                {
                    TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) tile;
                    tank.hasRod = false;
                    Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.NUCLEAR_WASTE_ROD));
                    world.setBlockState(pos, this.getDefaultState().withProperty(DEPLETE, true));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);

        if (state == state.withProperty(DEPLETE, false))
        {
            return 4;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        if (world.getTileEntity(pos) instanceof TileEntityNuclearWasteTank)
        {
            TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) world.getTileEntity(pos);
            tank.hasRod = true;
        }
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
        List<ItemStack> ret = Lists.newArrayList();
        boolean flag = false;

        if (state == state.withProperty(DEPLETE, true))
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
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DEPLETE, Boolean.valueOf((meta & 1) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(DEPLETE).booleanValue() ? 1 : 0;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {DEPLETE});
    }

    @Override
    public String getName()
    {
        return "nuclear_waste_tank";
    }
}