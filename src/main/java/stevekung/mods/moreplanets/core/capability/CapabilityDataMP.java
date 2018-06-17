package stevekung.mods.moreplanets.core.capability;

import net.minecraft.nbt.NBTTagCompound;

public class CapabilityDataMP implements AbstractCapabilityDataMP
{
    private boolean inPortal;
    private boolean readyToTeleport;
    private int timeUntilPortal;
    private int portalCounter;
    private float timeInPortal;
    private float prevTimeInPortal;

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setBoolean("InPortal", this.inPortal);
        nbt.setBoolean("ReadyToTeleport", this.readyToTeleport);
        nbt.setInteger("TimeUntilPortal", this.timeUntilPortal);
        nbt.setInteger("PortalCounter", this.portalCounter);
        nbt.setFloat("TimeInPortal", this.timeInPortal);
        nbt.setFloat("PrevTimeInPortal", this.prevTimeInPortal);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.inPortal = nbt.getBoolean("InPortal");
        this.readyToTeleport = nbt.getBoolean("ReadyToTeleport");
        this.timeUntilPortal = nbt.getInteger("TimeUntilPortal");
        this.portalCounter = nbt.getInteger("PortalCounter");
        this.timeInPortal = nbt.getFloat("TimeInPortal");
        this.prevTimeInPortal = nbt.getFloat("PrevTimeInPortal");
    }

    @Override
    public void setInPortal(boolean inPortal)
    {
        this.inPortal = inPortal;
    }

    @Override
    public boolean isInPortal()
    {
        return this.inPortal;
    }

    @Override
    public void setReadyToTeleport(boolean ready)
    {
        this.readyToTeleport = ready;
    }

    @Override
    public boolean isReadyToTeleport()
    {
        return this.readyToTeleport;
    }

    @Override
    public void setTimeUntilPortal(int time)
    {
        this.timeUntilPortal = time;
    }

    @Override
    public int getTimeUntilPortal()
    {
        return this.timeUntilPortal;
    }

    @Override
    public void setPortalCounter(int counter)
    {
        this.portalCounter = counter;
    }

    @Override
    public int getPortalCounter()
    {
        return this.portalCounter;
    }

    @Override
    public void setTimeInPortal(float time)
    {
        this.timeInPortal = time;
    }

    @Override
    public float getTimeInPortal()
    {
        return this.timeInPortal;
    }

    @Override
    public void setPrevTimeInPortal(float time)
    {
        this.prevTimeInPortal = time;
    }

    @Override
    public float getPrevTimeInPortal()
    {
        return this.prevTimeInPortal;
    }
}