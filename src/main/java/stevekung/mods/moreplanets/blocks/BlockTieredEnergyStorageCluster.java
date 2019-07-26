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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyStorageCluster;
import stevekung.mods.moreplanets.tileentity.TileEntityNuclearWasteEnergyStorageCluster;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.IDescription;
import stevekung.mods.moreplanets.utils.ItemDescription;
import stevekung.mods.moreplanets.utils.blocks.BlockTileMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityEnergyStorageClusterMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockTieredEnergyStorageCluster extends BlockTileMP implements IDescription
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static final PropertyInteger VALUE = PropertyInteger.create("value", 0, 16);
    public static final IMachineSidesProperties MACHINESIDES_RENDERTYPE = IMachineSidesProperties.TWOFACES_HORIZ;
    private static final PropertyEnum<MachineSidesModel> SIDES = MACHINESIDES_RENDERTYPE.asProperty;
    private final BlockType type;

    public BlockTieredEnergyStorageCluster(String name, BlockType type)
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
            if (world.getTileEntity(pos) instanceof TileEntityEnergyStorageClusterMP)
            {
                TileEntityEnergyStorageClusterMP electric = (TileEntityEnergyStorageClusterMP) world.getTileEntity(pos);
                electric.storage.setEnergyStored(itemStack.getTagCompound().getFloat("EnergyStored"));
            }
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        player.addExhaustion(0.025F);

        if (tile instanceof TileEntityEnergyStorageClusterMP)
        {
            ItemStack machine = new ItemStack(this, 1, 0);
            TileEntityEnergyStorageClusterMP electric = (TileEntityEnergyStorageClusterMP) tile;

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
        if (this.type == BlockType.DARK_ENERGY_STORAGE_CLUSTER)
        {
            return new TileEntityDarkEnergyStorageCluster();
        }
        else
        {
            return new TileEntityNuclearWasteEnergyStorageCluster();
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
        EnumFacing facing = EnumFacing.getHorizontal(meta % 4);
        return this.getDefaultState().withProperty(FACING, facing);
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

        if (tile instanceof TileEntityEnergyStorageClusterMP)
        {
            TileEntityEnergyStorageClusterMP storageModule = (TileEntityEnergyStorageClusterMP) tile;
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
        return (itemStack, list) -> list.addAll(BlocksItemsRegistry.getDescription(this.type.toString() + ".description"));
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return ColorUtils.stringToRGB(IItemRarity.MACHINE);
    }

    public static enum BlockType
    {
        DARK_ENERGY_STORAGE_CLUSTER,
        NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER;

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }
    }
}