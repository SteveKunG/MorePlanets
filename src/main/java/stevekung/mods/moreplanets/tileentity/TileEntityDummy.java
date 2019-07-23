package stevekung.mods.moreplanets.tileentity;

import java.util.ArrayList;

import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

public class TileEntityDummy extends TileBaseElectricBlock
{
    @NetworkedField(targetSide = Side.CLIENT)
    public BlockPos mainBlockPosition;

    public TileEntityDummy()
    {
        super("");
    }

    public TileEntityDummy(String tileName)
    {
        super(tileName);
    }

    @Override
    protected boolean handleInventory()
    {
        return false;
    }

    public void setMainBlock(BlockPos mainBlock)
    {
        this.mainBlockPosition = mainBlock;

        if (!this.world.isRemote)
        {
            this.world.notifyBlockUpdate(this.getPos(), this.world.getBlockState(this.getPos()), this.world.getBlockState(this.mainBlockPosition), 3);
        }
    }

    public void onBlockRemoval()
    {
        if (this.mainBlockPosition != null)
        {
            TileEntity tileEntity = this.world.getTileEntity(this.mainBlockPosition);

            if (tileEntity instanceof IMultiBlock)
            {
                IMultiBlock mainBlock = (IMultiBlock) tileEntity;
                mainBlock.onDestroy(this);
            }
        }
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, EntityPlayer player)
    {
        if (this.mainBlockPosition != null)
        {
            TileEntity tileEntity = this.world.getTileEntity(this.mainBlockPosition);

            if (tileEntity instanceof IMultiBlock)
            {
                return ((IMultiBlock) tileEntity).onActivated(player);
            }
        }
        return false;
    }

    public TileEntity getMainBlockTile()
    {
        if (this.mainBlockPosition != null)
        {
            return this.world.getTileEntity(this.mainBlockPosition);
        }
        return null;
    }

    @Override
    public void slowDischarge() {}

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagCompound tag = nbt.getCompoundTag("mainBlockPosition");
        this.mainBlockPosition = new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if (this.mainBlockPosition != null)
        {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("x", this.mainBlockPosition.getX());
            tag.setInteger("y", this.mainBlockPosition.getY());
            tag.setInteger("z", this.mainBlockPosition.getZ());
            nbt.setTag("mainBlockPosition", tag);
        }
        return nbt;
    }

    @Override
    public boolean isNetworkedTile()
    {
        return true;
    }

    @Override
    public void getNetworkedData(ArrayList<Object> sendData)
    {
        if (this.mainBlockPosition == null)
        {
            return;
        }
        super.getNetworkedData(sendData);
    }

    @Override
    public boolean shouldUseEnergy()
    {
        return false;
    }

    @Override
    public EnumFacing getElectricInputDirection()
    {
        return null;
    }

    @Override
    public ItemStack getBatteryInSlot()
    {
        return ItemStack.EMPTY;
    }

    @Override
    public EnumFacing getFront()
    {
        return null;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[0];
    }
}