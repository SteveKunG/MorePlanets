package stevekung.mods.moreplanets.core.capability;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface AbstractCapabilityDataMP
{
    static AbstractCapabilityDataMP get(Entity entity)
    {
        return entity.getCapability(CapabilityProviderMP.MORE_PLANETS_CAP, null);
    }

    void writeNBT(NBTTagCompound nbt);

    void readNBT(NBTTagCompound nbt);
}