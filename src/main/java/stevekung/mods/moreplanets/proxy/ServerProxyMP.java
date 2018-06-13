package stevekung.mods.moreplanets.proxy;

import com.google.common.base.Function;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.internal.FMLMessage.EntitySpawnMessage;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import stevekung.mods.moreplanets.entity.projectile.EntitySpaceFishHook;
import stevekung.mods.moreplanets.planets.chalos.entity.projectile.EntityCheeseSpore;
import stevekung.mods.moreplanets.planets.chalos.entity.projectile.EntitySmallCheeseSpore;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityVeinBall;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.IMorePlanetsBoss;

public class ServerProxyMP
{
    public void preInit(FMLPreInitializationEvent event) {}

    public void init(FMLInitializationEvent event)
    {
        ServerProxyMP.handleCustomSpawning();
    }

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

    public void registerVariant() {}

    private static void handleCustomSpawning()
    {
        WorldClient world = FMLClientHandler.instance().getWorldClient();
        EntityRegistration entityRegistration = EntityRegistry.instance().lookupModSpawn(EntitySpaceFishHook.class, false);

        Function<EntitySpawnMessage, Entity> handler = input ->
        {
            int entityID = 0;
            double posX = 0;
            double posY = 0;
            double posZ = 0;

            try
            {
                entityID = ReflectionHelper.findField(EntitySpawnMessage.class, "throwerId").getInt(input);
                posX = ReflectionHelper.findField(EntitySpawnMessage.class, "rawX").getDouble(input);
                posY = ReflectionHelper.findField(EntitySpawnMessage.class, "rawY").getDouble(input);
                posZ = ReflectionHelper.findField(EntitySpawnMessage.class, "rawZ").getDouble(input);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            Entity angler = world.getEntityByID(entityID);

            if (angler instanceof EntityPlayer)
            {
                Entity entity = new EntitySpaceFishHook(world, (EntityPlayer) angler, posX, posY, posZ);
                return entity;
            }
            return null;
        };
        entityRegistration.setCustomSpawning(handler, false);
        entityRegistration = EntityRegistry.instance().lookupModSpawn(EntityCheeseSpore.class, false);

        handler = input ->
        {
            double posX = 0;
            double posY = 0;
            double posZ = 0;
            double speedScaledX = 0;
            double speedScaledY = 0;
            double speedScaledZ = 0;

            try
            {
                posX = ReflectionHelper.findField(EntitySpawnMessage.class, "rawX").getDouble(input);
                posY = ReflectionHelper.findField(EntitySpawnMessage.class, "rawY").getDouble(input);
                posZ = ReflectionHelper.findField(EntitySpawnMessage.class, "rawZ").getDouble(input);
                speedScaledX = ReflectionHelper.findField(EntitySpawnMessage.class, "speedScaledX").getDouble(input);
                speedScaledY = ReflectionHelper.findField(EntitySpawnMessage.class, "speedScaledY").getDouble(input);
                speedScaledZ = ReflectionHelper.findField(EntitySpawnMessage.class, "speedScaledZ").getDouble(input);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            Entity entity = new EntityCheeseSpore(world, posX, posY, posZ, speedScaledX, speedScaledY, speedScaledZ);
            return entity;
        };
        entityRegistration.setCustomSpawning(handler, false);
        entityRegistration = EntityRegistry.instance().lookupModSpawn(EntitySmallCheeseSpore.class, false);

        handler = input ->
        {
            double posX = 0;
            double posY = 0;
            double posZ = 0;
            double speedScaledX = 0;
            double speedScaledY = 0;
            double speedScaledZ = 0;

            try
            {
                posX = ReflectionHelper.findField(EntitySpawnMessage.class, "rawX").getDouble(input);
                posY = ReflectionHelper.findField(EntitySpawnMessage.class, "rawY").getDouble(input);
                posZ = ReflectionHelper.findField(EntitySpawnMessage.class, "rawZ").getDouble(input);
                speedScaledX = ReflectionHelper.findField(EntitySpawnMessage.class, "speedScaledX").getDouble(input);
                speedScaledY = ReflectionHelper.findField(EntitySpawnMessage.class, "speedScaledY").getDouble(input);
                speedScaledZ = ReflectionHelper.findField(EntitySpawnMessage.class, "speedScaledZ").getDouble(input);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            Entity entity = new EntitySmallCheeseSpore(world, posX, posY, posZ, speedScaledX, speedScaledY, speedScaledZ);
            return entity;
        };
        entityRegistration.setCustomSpawning(handler, false);
        entityRegistration = EntityRegistry.instance().lookupModSpawn(EntityVeinBall.class, false);

        handler = input ->
        {
            double posX = 0;
            double posY = 0;
            double posZ = 0;
            double speedScaledX = 0;
            double speedScaledY = 0;
            double speedScaledZ = 0;

            try
            {
                posX = ReflectionHelper.findField(EntitySpawnMessage.class, "rawX").getDouble(input);
                posY = ReflectionHelper.findField(EntitySpawnMessage.class, "rawY").getDouble(input);
                posZ = ReflectionHelper.findField(EntitySpawnMessage.class, "rawZ").getDouble(input);
                speedScaledX = ReflectionHelper.findField(EntitySpawnMessage.class, "speedScaledX").getDouble(input);
                speedScaledY = ReflectionHelper.findField(EntitySpawnMessage.class, "speedScaledY").getDouble(input);
                speedScaledZ = ReflectionHelper.findField(EntitySpawnMessage.class, "speedScaledZ").getDouble(input);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            Entity entity = new EntityVeinBall(world, posX, posY, posZ, speedScaledX, speedScaledY, speedScaledZ);
            return entity;
        };
        entityRegistration.setCustomSpawning(handler, false);
    }
}