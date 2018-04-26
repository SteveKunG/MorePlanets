package stevekung.mods.moreplanets.blocks;

import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import micdoodle8.mods.galacticraft.core.tile.IMachineSides;
import micdoodle8.mods.galacticraft.core.tile.IMachineSidesProperties;
import micdoodle8.mods.galacticraft.core.tile.IMachineSidesProperties.MachineSidesModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.util.ItemDescription;
import stevekung.mods.moreplanets.util.blocks.*;
import stevekung.mods.moreplanets.util.helper.ItemDescriptionHelper;
import stevekung.mods.moreplanets.util.tileentity.TileEntityEnergyStorageMP;

public class BlockTieredEnergyStorage extends BlockTileMP implements IBlockDescription, ISortableBlock, ISingleBlockRender
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static final PropertyInteger VALUE = PropertyInteger.create("value", 0, 16);
    public static final IMachineSidesProperties MACHINESIDES_RENDERTYPE = IMachineSidesProperties.TWOFACES_HORIZ;
    private static final PropertyEnum<MachineSidesModel> SIDES = MACHINESIDES_RENDERTYPE.asProperty;
    private final BlockType type;

    public BlockTieredEnergyStorage(String name, BlockType type)
    {
        super(Material.IRON);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.METAL);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VALUE, 0).withProperty(FACING, EnumFacing.NORTH));
        this.setUnlocalizedName(name);
        this.type = type;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        int angle = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = EnumFacing.getHorizontal(angle).getOpposite().getHorizontalIndex();
        world.setBlockState(pos, this.getStateFromMeta(change), 3);

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("EnergyStored"))
        {
            if (world.getTileEntity(pos) instanceof TileEntityEnergyStorageMP)
            {
                TileEntityEnergyStorageMP electric = (TileEntityEnergyStorageMP) world.getTileEntity(pos);
                electric.storage.setEnergyStored(itemStack.getTagCompound().getFloat("EnergyStored"));
            }
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        player.addExhaustion(0.025F);

        if (tile instanceof TileEntityEnergyStorageMP)
        {
            ItemStack machine = new ItemStack(this, 1, 0);
            TileEntityEnergyStorageMP electric = (TileEntityEnergyStorageMP) tile;

            if (electric.getEnergyStoredGC() > 0)
            {
                machine.setTagCompound(new NBTTagCompound());
                machine.getTagCompound().setFloat("EnergyStored", electric.getEnergyStoredGC());
            }
            Block.spawnAsEntity(world, pos, machine);
        }
    }

    @Override
    public boolean onUseWrench(World world, BlockPos pos, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        IBlockState state = world.getBlockState(pos);
        TileBaseUniversalElectrical.onUseWrenchBlock(state, world, pos, state.getValue(FACING));
        return true;
    }

    @Override
    public boolean onMachineActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            player.openGui(MorePlanetsMod.INSTANCE, -1, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        if (this.type == BlockType.DARK_ENERGY_STORAGE_MODULE)
        {
            return new TileEntityEnergyStorageMP(12500000.0F, 2500.0F, 4, BlockType.DARK_ENERGY_STORAGE_MODULE.getName());
        }
        else
        {
            return new TileEntityEnergyStorageMP(50000000.0F, 7500.0F, 4, BlockType.NUCLEAR_WASTE_STORAGE_MODULE.getName());
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(meta % 4);
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, VALUE, SIDES);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.MACHINE_BLOCK;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        TileEntity tile = world.getTileEntity(pos);
        state = IMachineSides.addPropertyForTile(state, tile, MACHINESIDES_RENDERTYPE, SIDES);

        if (tile instanceof TileEntityEnergyStorageMP)
        {
            TileEntityEnergyStorageMP storageModule = (TileEntityEnergyStorageMP) tile;
            return state.withProperty(VALUE, storageModule.scaledEnergyLevel);
        }
        else
        {
            return state.withProperty(VALUE, 0);
        }
    }

    @Override
    public boolean onSneakUseWrench(World world, BlockPos pos, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof IMachineSides)
        {
            ((IMachineSides)tile).nextSideConfiguration(tile);
            return true;
        }
        return false;
    }

    @Override
    public ItemDescription getDescription()
    {
        return (itemStack, list) -> {
            if (this.type == BlockType.DARK_ENERGY_STORAGE_MODULE)
            {
                list.addAll(ItemDescriptionHelper.getDescription(BlockTieredEnergyStorage.this.getUnlocalizedName() + "_0.description"));
            }
            else
            {
                list.addAll(ItemDescriptionHelper.getDescription(BlockTieredEnergyStorage.this.getUnlocalizedName() + "_1.description"));
            }
        };
    }

    @Override
    public String getName()
    {
        return this.type.getName();
    }

    public static enum BlockType implements IStringSerializable
    {
        DARK_ENERGY_STORAGE_MODULE,
        NUCLEAR_WASTE_STORAGE_MODULE;

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