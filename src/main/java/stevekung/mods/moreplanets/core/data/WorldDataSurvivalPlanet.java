package stevekung.mods.moreplanets.core.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class WorldDataSurvivalPlanet extends WorldSavedData
{
    public static final String saveDataID = "SurvivalPlanetData";
    public boolean hasSurvivalPlanetData;
    public boolean disableMessage;
    public String survivalPlanetName = "";

    public WorldDataSurvivalPlanet(String data)
    {
        super(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        this.hasSurvivalPlanetData = nbt.getBoolean("HasSurvivalPlanetData");
        this.disableMessage = nbt.getBoolean("DisableMessage");
        this.survivalPlanetName = nbt.getString("SurvivalPlanetName");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setBoolean("HasSurvivalPlanetData", this.hasSurvivalPlanetData);
        nbt.setBoolean("DisableMessage", this.disableMessage);
        nbt.setString("SurvivalPlanetName", this.survivalPlanetName);
        return nbt;
    }

    @Override
    public boolean isDirty()
    {
        return true;
    }
}