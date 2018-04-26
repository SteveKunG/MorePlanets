package stevekung.mods.moreplanets.util.debug;

import net.minecraft.nbt.NBTTagCompound;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class MPCapData implements MorePlanetsCapabilityData
{
    private String startedCelestial = ConfigManagerMP.moreplanets_general.startedPlanet;

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setString("StartedCelestial", this.startedCelestial);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.startedCelestial = nbt.getString("StartedCelestial");
    }

    @Override
    public void setStartCelestial(String object)
    {
        this.startedCelestial = object;
    }

    @Override
    public String getStartCelestial()
    {
        return this.startedCelestial;
    }
}