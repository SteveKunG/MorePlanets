package stevekung.mods.moreplanets.core.capability;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface AbstractCapabilityDataMP
{
    public static AbstractCapabilityDataMP get(Entity entity)
    {
        return entity.getCapability(CapabilityProviderMP.MORE_PLANETS_CAP, null);
    }

    public abstract void writeNBT(NBTTagCompound nbt);

    public abstract void readNBT(NBTTagCompound nbt);

    public void setInPortal(boolean inPortal);

    public boolean isInPortal();

    public void setTimeUntilPortal(int time);

    public int getTimeUntilPortal();

    public void setPortalCounter(int counter);

    public int getPortalCounter();

    public void setTimeInPortal(float time);

    public float getTimeInPortal();

    public void setPrevTimeInPortal(float time);

    public float getPrevTimeInPortal();
}