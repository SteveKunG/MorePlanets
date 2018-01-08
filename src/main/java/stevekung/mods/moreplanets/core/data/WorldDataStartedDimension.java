package stevekung.mods.moreplanets.core.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class WorldDataStartedDimension extends WorldSavedData
{
    public static String saveDataID = "StartedDimensionData";
    public boolean startedDimension;
    public String planetToBack = "";

    public WorldDataStartedDimension(String id)
    {
        super(id);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        this.startedDimension = nbt.getBoolean("StartedDimension");
        this.planetToBack = nbt.getString("PlanetToBack");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setBoolean("StartedDimension", this.startedDimension);
        nbt.setString("PlanetToBack", this.planetToBack);
        return nbt;
    }

    @Override
    public boolean isDirty()
    {
        return true;
    }
}