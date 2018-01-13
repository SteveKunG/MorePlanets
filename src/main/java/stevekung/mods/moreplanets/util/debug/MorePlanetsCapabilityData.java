package stevekung.mods.moreplanets.util.debug;

import net.minecraft.nbt.NBTTagCompound;

public interface MorePlanetsCapabilityData
{
    public abstract void writeNBT(NBTTagCompound nbt);

    public abstract void readNBT(NBTTagCompound nbt);

    public void setStartCelestial(String object);

    public String getStartCelestial();
}