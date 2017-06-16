/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.proxy;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;

public class CommonProxyMP
{
    public void postInit(FMLPostInitializationEvent event)
    {
    }

    public void spawnParticle(String string, double x, double y, double z)
    {
    }

    public void spawnMotionParticle(String string, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
    }

    public int getBlockRender(Block block)
    {
        return -1;
    }

    public EntityPlayer getPlayerFromNetHandler(INetHandler handler)
    {
        if (handler instanceof NetHandlerPlayServer)
        {
            return ((NetHandlerPlayServer) handler).playerEntity;
        }
        else
        {
            return null;
        }
    }

    public void resetPlayerFloatingTick(EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            ObfuscationReflectionHelper.setPrivateValue(NetHandlerPlayServer.class, ((EntityPlayerMP)player).playerNetServerHandler, Integer.valueOf(0), new String[] { "field_147365_f", "floatingTickCount" });
        }
    }
}