package stevekung.mods.moreplanets.utils.debug;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.stevekunglib.utils.CommonUtils;

public class CapabilityHandlerMP
{
    private static final ResourceLocation CONSTANT = new ResourceLocation(MorePlanetsMod.MOD_ID, "more_planets_data");

    @SubscribeEvent
    public void onAttachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            event.addCapability(CapabilityHandlerMP.CONSTANT, new CapabilityProviderMP());
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        if (event.isWasDeath())
        {
            EntityPlayer player = event.getEntityPlayer();
            MorePlanetsCapabilityData currentData = player.getCapability(CapabilityProviderMP.MORE_PLANETS_CAP, null);
            MorePlanetsCapabilityData oldData = event.getOriginal().getCapability(CapabilityProviderMP.MORE_PLANETS_CAP, null);
            currentData.setStartCelestial(oldData.getStartCelestial());
        }
    }

    public static void register()
    {
        CapabilityManager.INSTANCE.register(MorePlanetsCapabilityData.class, new Capability.IStorage<MorePlanetsCapabilityData>()
        {
            @Override
            public NBTBase writeNBT(Capability<MorePlanetsCapabilityData> capability, MorePlanetsCapabilityData instance, EnumFacing side)
            {
                NBTTagCompound nbt = new NBTTagCompound();
                instance.writeNBT(nbt);
                return nbt;
            }

            @Override
            public void readNBT(Capability<MorePlanetsCapabilityData> capability, MorePlanetsCapabilityData instance, EnumFacing side, NBTBase nbt)
            {
                instance.readNBT((NBTTagCompound) nbt);
            }
        }, MPCapData::new);
        CommonUtils.registerEventHandler(new CapabilityHandlerMP());
    }
}