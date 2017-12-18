package stevekung.mods.moreplanets.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;

public class ServerProxyMP
{
    public void registerPreRendering() {}

    public void registerInitRendering() {}

    public void registerRendering() {}

    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z) {}

    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z, Object[] objects) {}

    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z, double motionX, double motionY, double motionZ) {}

    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z, double motionX, double motionY, double motionZ, Object[] objects) {}

    public void resetFloatingTick(EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            ObfuscationReflectionHelper.setPrivateValue(NetHandlerPlayServer.class, ((EntityPlayerMP)player).playerNetServerHandler, Integer.valueOf(0), new String[] { "field_147365_f", "floatingTickCount" });
        }
    }
}