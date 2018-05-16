package stevekung.mods.moreplanets.planets.nibiru.tileentity;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.tile.FluidTankGC;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.wrappers.FluidHandlerWrapper;
import micdoodle8.mods.galacticraft.core.wrappers.IFluidHandlerWrapper;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntityDummy;

public class TileEntityNuclearWasteTank extends TileEntityDummy implements IMultiBlock, IFluidHandlerWrapper
{
    public int renderTicks;
    @NetworkedField(targetSide = Side.CLIENT)
    public int rodCreateTime = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public int time = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean hasRod = true;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean createRod = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public FluidTankGC fluidTank = new FluidTankGC(3000, this);

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.rodCreateTime = nbt.getInteger("RodCreateTime");
        this.time = nbt.getInteger("Time");
        this.hasRod = nbt.getBoolean("HasRod");
        this.createRod = nbt.getBoolean("CreateRod");
        this.fluidTank.readFromNBT(nbt.getCompoundTag("FluidTank"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("RodCreateTime", this.rodCreateTime);
        nbt.setInteger("Time", this.time);
        nbt.setBoolean("HasRod", this.hasRod);
        nbt.setBoolean("CreateRod", this.createRod);

        if (this.fluidTank.getFluid() != null)
        {
            nbt.setTag("FluidTank", this.fluidTank.writeToNBT(new NBTTagCompound()));
        }
        return nbt;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("RodCreateTime", this.rodCreateTime);
        nbt.setInteger("Time", this.time);
        nbt.setBoolean("HasRod", this.hasRod);
        nbt.setBoolean("CreateRod", this.createRod);

        if (this.fluidTank.getFluid() != null)
        {
            nbt.setTag("FluidTank", this.fluidTank.writeToNBT(new NBTTagCompound()));
        }
        SPacketUpdateTileEntity tileUpdate = new SPacketUpdateTileEntity(this.getPos(), -1, nbt);
        return tileUpdate;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        NBTTagCompound nbt = pkt.getNbtCompound();
        this.rodCreateTime = nbt.getInteger("RodCreateTime");
        this.time = nbt.getInteger("Time");
        this.hasRod = nbt.getBoolean("HasRod");
        this.createRod = nbt.getBoolean("CreateRod");
        this.fluidTank.readFromNBT(nbt.getCompoundTag("FluidTank"));
    }

    @Override
    public void update()
    {
        super.update();
        this.renderTicks++;

        if (!this.world.isRemote && this.createRod)
        {
            if (this.fluidTank.getFluidAmount() == 3000 && this.rodCreateTime < this.time)
            {
                this.rodCreateTime++;

                if (this.rodCreateTime == this.time)
                {
                    this.createRod = false;
                    this.hasRod = true;
                    this.rodCreateTime = 0;
                    this.time = 0;
                    this.drain(null, 3000, true);
                }
            }
        }
    }

    @Override
    public boolean onActivated(EntityPlayer player)
    {
        return false;
    }

    @Override
    public void onCreate(World world, BlockPos pos)
    {
        this.mainBlockPosition = pos;
    }

    @Override
    public void onDestroy(TileEntity tile)
    {
        BlockPos thisBlock = this.getPos();

        if (this.world.isRemote && this.world.rand.nextDouble() < 0.1D)
        {
            FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.up(), MPBlocks.NWT_MIDDLE_DUMMY.getDefaultState());
            FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.up(2), MPBlocks.NWT_TOP_DUMMY.getDefaultState());
        }
        this.destroyBlock(this.getPos(), true);
        this.destroyBlock(thisBlock.up(), false);
        this.destroyBlock(thisBlock.up(2), false);
    }

    @Override
    public void getPositions(BlockPos placedPosition, List<BlockPos> positions) {}

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return new AxisAlignedBB(this.pos, this.pos.add(1, 3, 1));
    }

    @Override
    public EnumBlockMultiType getMultiType()
    {
        return null;
    }

    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill)
    {
        return this.fluidTank.fill(resource.copy(), doFill);
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain)
    {
        if (resource == null)
        {
            return null;
        }
        else
        {
            return this.drain(from, resource.amount, doDrain);
        }
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain)
    {
        return this.fluidTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid)
    {
        return fluid == null || fluid.getName().equals("nuclear_waste_fluid");
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid)
    {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from)
    {
        FluidTank compositeTank = new FluidTank(this.fluidTank.getCapacity());
        int capacity = this.fluidTank.getCapacity();
        compositeTank.setCapacity(capacity);
        return new FluidTankInfo[] { compositeTank.getInfo() };
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return (T) new FluidHandlerWrapper(this, facing);
        }
        return super.getCapability(capability, facing);
    }

    public boolean isCreateRod()
    {
        return this.createRod && this.fluidTank.getFluidAmount() > 0;
    }

    public int getAmount()
    {
        return this.fluidTank.getFluidAmount();
    }

    public void setTime()
    {
        this.time = 1200 + this.world.rand.nextInt(1200);
    }

    private boolean destroyBlock(BlockPos pos, boolean dropBlock)
    {
        IBlockState state = this.world.getBlockState(pos);
        Block block = state.getBlock();

        if (block.isAir(state, this.world, pos))
        {
            return false;
        }
        else
        {
            ItemStack itemStack = new ItemStack(MPBlocks.NUCLEAR_WASTE_TANK);

            if (dropBlock)
            {
                if (!this.hasRod)
                {
                    itemStack.setTagCompound(new NBTTagCompound());
                    itemStack.getTagCompound().setInteger("RodCreateTime", this.rodCreateTime);
                    itemStack.getTagCompound().setInteger("Time", this.time);
                    itemStack.getTagCompound().setBoolean("HasRod", this.hasRod);
                    itemStack.getTagCompound().setBoolean("CreateRod", this.createRod);

                    if (this.fluidTank.getFluid() != null)
                    {
                        itemStack.getTagCompound().setTag("FluidTank", this.fluidTank.writeToNBT(new NBTTagCompound()));
                    }
                }
                Block.spawnAsEntity(this.world, pos, itemStack);
            }
            this.world.playEvent(2001, pos, Block.getStateId(state));
            return this.world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }
}