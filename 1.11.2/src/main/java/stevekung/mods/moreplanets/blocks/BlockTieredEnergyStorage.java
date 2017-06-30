package stevekung.mods.moreplanets.blocks;

import java.util.List;

import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import micdoodle8.mods.galacticraft.core.tile.IMachineSides;
import micdoodle8.mods.galacticraft.core.tile.IMachineSidesProperties;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyStorageCluster;
import stevekung.mods.moreplanets.tileentity.TileEntityNuclearWasteStorageCluster;
import stevekung.mods.moreplanets.util.ItemDescription;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.*;
import stevekung.mods.moreplanets.util.helper.ItemDescriptionHelper;

public class BlockTieredEnergyStorage extends BlockTileMP implements IBlockDescription, ISortableBlock, IBlockVariants
{
    public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static PropertyEnum VARIANT = PropertyEnum.create("variants", BlockType.class);
    public static PropertyInteger VALUE = PropertyInteger.create("value", 0, 33);
    public static IMachineSidesProperties MACHINESIDES_RENDERTYPE = IMachineSidesProperties.TWOFACES_HORIZ;
    public static PropertyEnum SIDES = MACHINESIDES_RENDERTYPE.asProperty;

    public BlockTieredEnergyStorage(String name)
    {
        super(Material.IRON);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.METAL);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockType.DARK_ENERGY_STORAGE_MODULE).withProperty(VALUE, 0).withProperty(FACING, EnumFacing.NORTH));
        this.setUnlocalizedName(name);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        int meta = this.getMetaFromState(state);
        int angle = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = EnumFacing.getHorizontal(angle).getOpposite().getHorizontalIndex();

        if (meta >= 4)
        {
            world.setBlockState(pos, this.getStateFromMeta(4 + change), 3);
        }
        else if (meta >= 0)
        {
            world.setBlockState(pos, this.getStateFromMeta(change), 3);
        }

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("EnergyStored"))
        {
            if (world.getTileEntity(pos) instanceof TileEntityDarkEnergyStorageCluster)
            {
                TileEntityDarkEnergyStorageCluster electric = (TileEntityDarkEnergyStorageCluster) world.getTileEntity(pos);
                electric.storage.setEnergyStored(itemStack.getTagCompound().getFloat("EnergyStored"));
            }
            if (world.getTileEntity(pos) instanceof TileEntityNuclearWasteStorageCluster)
            {
                TileEntityNuclearWasteStorageCluster electric = (TileEntityNuclearWasteStorageCluster) world.getTileEntity(pos);
                electric.storage.setEnergyStored(itemStack.getTagCompound().getFloat("EnergyStored"));
            }
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        player.addExhaustion(0.025F);

        if (tile instanceof TileEntityDarkEnergyStorageCluster)
        {
            ItemStack machine = new ItemStack(this, 1, 0);
            TileEntityDarkEnergyStorageCluster electric = (TileEntityDarkEnergyStorageCluster) tile;

            if (electric.getEnergyStoredGC() > 0)
            {
                machine.setTagCompound(new NBTTagCompound());
                machine.getTagCompound().setFloat("EnergyStored", electric.getEnergyStoredGC());
            }
            Block.spawnAsEntity(world, pos, machine);
        }
        if (tile instanceof TileEntityNuclearWasteStorageCluster)
        {
            ItemStack machine = new ItemStack(this, 1, 4);
            TileEntityNuclearWasteStorageCluster electric = (TileEntityNuclearWasteStorageCluster) tile;

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
            player.openGui(MorePlanetsCore.INSTANCE, -1, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        if (meta >= 0 && meta <= 3)
        {
            return new TileEntityDarkEnergyStorageCluster();
        }
        else
        {
            return new TileEntityNuclearWasteStorageCluster();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 4));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state) & 12;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(state));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(meta % 4);
        BlockType type = BlockType.valuesCached()[(int) Math.floor(meta / 4)];
        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(VARIANT, type);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getHorizontalIndex() + ((BlockType)state.getValue(VARIANT)).ordinal() * 4;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, VARIANT, VALUE, SIDES);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.MACHINE_BLOCK;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        TileEntity tile = world.getTileEntity(pos);
        state = IMachineSides.addPropertyForTile(state, tile, MACHINESIDES_RENDERTYPE, SIDES);

        if (tile instanceof TileEntityDarkEnergyStorageCluster)
        {
            TileEntityDarkEnergyStorageCluster storageModule = (TileEntityDarkEnergyStorageCluster) tile;
            return state.withProperty(VALUE, storageModule.scaledEnergyLevel);
        }
        else if (tile instanceof TileEntityNuclearWasteStorageCluster)
        {
            TileEntityNuclearWasteStorageCluster storageModule = (TileEntityNuclearWasteStorageCluster) tile;
            return state.withProperty(VALUE, storageModule.scaledEnergyLevel + 17);
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
        return new ItemDescription()
        {
            @Override
            public void addDescription(ItemStack itemStack, List list)
            {
                if (itemStack.getItemDamage() == 0)
                {
                    list.addAll(ItemDescriptionHelper.getDescription(BlockTieredEnergyStorage.this.getUnlocalizedName() + "_0.description"));
                }
                else
                {
                    list.addAll(ItemDescriptionHelper.getDescription(BlockTieredEnergyStorage.this.getUnlocalizedName() + "_1.description"));
                }
            }
        };
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("dark_energy", "nuclear_waste");
    }

    public enum BlockType implements IStringSerializable
    {
        DARK_ENERGY_STORAGE_MODULE,
        NUCLEAR_WASTE_STORAGE_MODULE;

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