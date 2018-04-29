package stevekung.mods.moreplanets.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.IMorePlanetsBoss;

public class ServerProxyMP
{
    public void preInit(FMLPreInitializationEvent event) {}

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}

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

    @Deprecated
    public void registerVariant() {}
}