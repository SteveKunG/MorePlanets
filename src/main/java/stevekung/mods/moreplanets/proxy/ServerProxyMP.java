package stevekung.mods.moreplanets.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.IMorePlanetsBoss;

public class ServerProxyMP
{
    public void registerPreRendering() {}

    public void registerPostRendering() {}

    public void registerInitRendering() {}

    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z) {}

    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z, Object[] objects) {}

    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z, double motionX, double motionY, double motionZ) {}

    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z, double motionX, double motionY, double motionZ, Object[] objects) {}

    public void resetFloatingTick(EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP)player).connection.floatingTickCount = 0;
        }
    }

    public void addBoss(IMorePlanetsBoss boss) {}

    public void removeBoss(IMorePlanetsBoss boss) {}

    public void registerVariant() {}
}