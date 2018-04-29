package stevekung.mods.moreplanets.utils.debug;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityProviderMP implements ICapabilitySerializable<NBTTagCompound>
{
    @CapabilityInject(MorePlanetsCapabilityData.class)
    public static final Capability<MorePlanetsCapabilityData> MORE_PLANETS_CAP = null;
    private MorePlanetsCapabilityData instance = CapabilityProviderMP.MORE_PLANETS_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == CapabilityProviderMP.MORE_PLANETS_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == CapabilityProviderMP.MORE_PLANETS_CAP ? CapabilityProviderMP.MORE_PLANETS_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.instance.writeNBT(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        this.instance.readNBT(nbt);
    }
}