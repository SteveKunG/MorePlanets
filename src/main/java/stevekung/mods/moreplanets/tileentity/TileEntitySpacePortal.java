package stevekung.mods.moreplanets.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySpacePortal extends TileEntity
{
    private int dimension;

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.dimension = nbt.getInteger("Dimension");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("Dimension", this.dimension);
        return nbt;
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public void setDimension(int id)
    {
        this.dimension = id;
    }

    public int getDimension()
    {
        return this.dimension;
    }
}