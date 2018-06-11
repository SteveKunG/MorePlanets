package stevekung.mods.moreplanets.planets.nibiru.blocks;

import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteTank;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockNuclearWasteTank extends BlockBaseMP implements ITileEntityProvider
{
    public BlockNuclearWasteTank(String name)
    {
        super(Material.IRON);
        this.setHardness(5.0F);
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.METAL);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state)
    {
        return true;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityNuclearWasteTank)
        {
            TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) tile;

            if (!itemStack.isEmpty())
            {
                if (tank.hasRod && !tank.createRod)
                {
                    if (itemStack.getItem() == MPItems.WASTE_ROD_PICKER)
                    {
                        if (!player.capabilities.isCreativeMode)
                        {
                            itemStack.damageItem(1, player);
                        }
                        Block.spawnAsEntity(world, pos, new ItemStack(MPItems.NUCLEAR_WASTE_ROD));
                        tank.hasRod = false;
                        tank.createRod = false;
                        return true;
                    }
                }
                else
                {
                    int slot = player.inventory.currentItem;
                    FluidActionResult result = FluidUtil.interactWithFluidHandler(player.inventory.getCurrentItem(), tank.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null), player);

                    if (result.isSuccess())
                    {
                        tank.createRod = true;
                        tank.setTime();
                        player.inventory.setInventorySlotContents(slot, result.result);

                        if (player.inventoryContainer != null)
                        {
                            player.inventoryContainer.detectAndSendChanges();
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityNuclearWasteTank)
        {
            TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) tile;

            if (tank.hasRod)
            {
                return 4;
            }
            else if (tank.createRod)
            {
                return 8;
            }
            else
            {
                return 0;
            }
        }
        return 0;
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
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityNuclearWasteTank)
        {
            TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) tile;
            tank.onCreate(world, pos);

            if (itemStack.hasTagCompound())
            {
                NBTTagCompound nbt = itemStack.getTagCompound();
                tank.rodCreateTime = nbt.getInteger("RodCreateTime");
                tank.time = nbt.getInteger("Time");
                tank.hasRod = nbt.getBoolean("HasRod");
                tank.createRod = nbt.getBoolean("CreateRod");
                tank.fluidTank.readFromNBT(nbt.getCompoundTag("FluidTank"));
            }
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        player.addExhaustion(0.025F);

        if (tile instanceof TileEntityNuclearWasteTank)
        {
            ItemStack machine = new ItemStack(this);
            TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) tile;
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("RodCreateTime", tank.rodCreateTime);
            nbt.setInteger("Time", tank.time);
            nbt.setBoolean("HasRod", tank.hasRod);
            nbt.setBoolean("CreateRod", tank.createRod);

            if (tank.fluidTank.getFluid() != null)
            {
                nbt.setTag("FluidTank", tank.fluidTank.writeToNBT(new NBTTagCompound()));
            }
            if (!tank.hasRod)
            {
                machine.setTagCompound(nbt);
            }
            Block.spawnAsEntity(world, pos, machine);
        }
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
    public ColorUtils.RGB getRarity()
    {
        return ColorUtils.stringToFullRGB(IItemRarity.MACHINE);
    }
}