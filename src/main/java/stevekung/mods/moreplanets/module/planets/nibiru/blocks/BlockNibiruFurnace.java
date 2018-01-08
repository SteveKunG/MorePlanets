package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNibiruFurnace;
import stevekung.mods.moreplanets.util.blocks.BlockContainerMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.ISingleBlockRender;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class BlockNibiruFurnace extends BlockContainerMP implements ISingleBlockRender
{
    private boolean isBurning;
    private static boolean keepInventory;

    protected BlockNibiruFurnace(String name, boolean isBurning)
    {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockStateHelper.FACING_HORIZON, EnumFacing.NORTH));
        this.setUnlocalizedName(name);
        this.setHardness(3.5F);
        this.isBurning = isBurning;

        if (this.isBurning)
        {
            this.setLightLevel(0.875F);
        }
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return !this.isBurning ? MorePlanetsCore.BLOCK_TAB : null;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(NibiruBlocks.NIBIRU_FURNACE);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(world, pos, state);
    }

    private void setDefaultFacing(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            IBlockState block = world.getBlockState(pos.north());
            IBlockState block1 = world.getBlockState(pos.south());
            IBlockState block2 = world.getBlockState(pos.west());
            IBlockState block3 = world.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(BlockStateHelper.FACING_HORIZON);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }
            world.setBlockState(pos, state.withProperty(BlockStateHelper.FACING_HORIZON, enumfacing), 2);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (this.isBurning)
        {
            EnumFacing enumfacing = state.getValue(BlockStateHelper.FACING_HORIZON);
            double d0 = pos.getX() + 0.5D;
            double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            switch (enumfacing)
            {
            case WEST:
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                break;
            case EAST:
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                break;
            case NORTH:
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                break;
            case SOUTH:
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
            default:
                break;
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityNibiruFurnace)
            {
                player.displayGUIChest((TileEntityNibiruFurnace)tileentity);
            }
            return true;
        }
    }

    public static void setState(boolean active, World world, BlockPos pos)
    {
        IBlockState iblockstate = world.getBlockState(pos);
        TileEntity tileentity = world.getTileEntity(pos);
        keepInventory = true;

        if (active)
        {
            world.setBlockState(pos, NibiruBlocks.NIBIRU_LIT_FURNACE.getDefaultState().withProperty(BlockStateHelper.FACING_HORIZON, iblockstate.getValue(BlockStateHelper.FACING_HORIZON)), 3);
            world.setBlockState(pos, NibiruBlocks.NIBIRU_LIT_FURNACE.getDefaultState().withProperty(BlockStateHelper.FACING_HORIZON, iblockstate.getValue(BlockStateHelper.FACING_HORIZON)), 3);
        }
        else
        {
            world.setBlockState(pos, NibiruBlocks.NIBIRU_FURNACE.getDefaultState().withProperty(BlockStateHelper.FACING_HORIZON, iblockstate.getValue(BlockStateHelper.FACING_HORIZON)), 3);
            world.setBlockState(pos, NibiruBlocks.NIBIRU_FURNACE.getDefaultState().withProperty(BlockStateHelper.FACING_HORIZON, iblockstate.getValue(BlockStateHelper.FACING_HORIZON)), 3);
        }

        keepInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityNibiruFurnace();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(BlockStateHelper.FACING_HORIZON, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        world.setBlockState(pos, state.withProperty(BlockStateHelper.FACING_HORIZON, placer.getHorizontalFacing().getOpposite()), 2);

        if (itemStack.hasDisplayName())
        {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityNibiruFurnace)
            {
                ((TileEntityNibiruFurnace)tileentity).setCustomInventoryName(itemStack.getDisplayName());
            }
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!keepInventory)
        {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityNibiruFurnace)
            {
                InventoryHelper.dropInventoryItems(world, pos, (TileEntityNibiruFurnace)tileentity);
                world.updateComparatorOutputLevel(pos, this);
            }
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
    {
        return Container.calcRedstone(world.getTileEntity(pos));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(NibiruBlocks.NIBIRU_FURNACE);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(BlockStateHelper.FACING_HORIZON, rot.rotate(state.getValue(BlockStateHelper.FACING_HORIZON)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirror)
    {
        return state.withRotation(mirror.toRotation(state.getValue(BlockStateHelper.FACING_HORIZON)));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(BlockStateHelper.FACING_HORIZON, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateHelper.FACING_HORIZON).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BlockStateHelper.FACING_HORIZON);
    }

    @Override
    public String getName()
    {
        return "nibiru_furnace";
    }
}