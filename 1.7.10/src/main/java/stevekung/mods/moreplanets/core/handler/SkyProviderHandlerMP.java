/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStatsClient;
import micdoodle8.mods.galacticraft.planets.mars.client.SkyProviderMars;
import micdoodle8.mods.galacticraft.planets.mars.dimension.WorldProviderMars;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import stevekung.mods.moreplanets.asteroids.darkasteroids.client.SkyProviderDarkAsteroids;
import stevekung.mods.moreplanets.asteroids.darkasteroids.dimension.WorldProviderDarkAsteroids;
import stevekung.mods.moreplanets.core.event.CloudRendererVenus;
import stevekung.mods.moreplanets.core.spacestation.jupiter.SkyProviderJupiterOrbit;
import stevekung.mods.moreplanets.core.spacestation.jupiter.WorldProviderJupiterOrbit;
import stevekung.mods.moreplanets.core.spacestation.mars.SkyProviderMarsOrbit;
import stevekung.mods.moreplanets.core.spacestation.mars.WorldProviderMarsOrbit;
import stevekung.mods.moreplanets.moons.deimos.dimension.WorldProviderDeimos;
import stevekung.mods.moreplanets.moons.deimos.dimension.sky.SkyProviderDeimos;
import stevekung.mods.moreplanets.moons.koentus.dimension.WorldProviderKoentus;
import stevekung.mods.moreplanets.moons.koentus.dimension.sky.SkyProviderKoentus;
import stevekung.mods.moreplanets.moons.phobos.dimension.WorldProviderPhobos;
import stevekung.mods.moreplanets.moons.phobos.dimension.sky.SkyProviderPhobos;
import stevekung.mods.moreplanets.planets.diona.dimension.WorldProviderDiona;
import stevekung.mods.moreplanets.planets.diona.dimension.sky.SkyProviderDiona;
import stevekung.mods.moreplanets.planets.fronos.dimension.WorldProviderFronos;
import stevekung.mods.moreplanets.planets.fronos.dimension.sky.SkyProviderFronos;
import stevekung.mods.moreplanets.planets.kapteynb.dimension.WorldProviderKapteynB;
import stevekung.mods.moreplanets.planets.kapteynb.dimension.sky.SkyProviderKapteynB;
import stevekung.mods.moreplanets.planets.mercury.dimension.WorldProviderMercury;
import stevekung.mods.moreplanets.planets.mercury.dimension.sky.SkyProviderMercury;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.planets.nibiru.dimension.sky.SkyProviderNibiru;
import stevekung.mods.moreplanets.planets.pluto.dimension.WorldProviderPluto;
import stevekung.mods.moreplanets.planets.pluto.dimension.sky.SkyProviderPluto;
import stevekung.mods.moreplanets.planets.polongnius.dimension.WorldProviderPolongnius;
import stevekung.mods.moreplanets.planets.polongnius.dimension.sky.SkyProviderPolongnius;
import stevekung.mods.moreplanets.planets.siriusb.dimension.WorldProviderSiriusB;
import stevekung.mods.moreplanets.planets.siriusb.dimension.sky.SkyProviderSiriusB;
import stevekung.mods.moreplanets.planets.venus.dimension.WorldProviderVenus;
import stevekung.mods.moreplanets.planets.venus.dimension.sky.SkyProviderVenus;

public class SkyProviderHandlerMP
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onSkyRendererTick(ClientTickEvent event)
    {
        Minecraft minecraft = FMLClientHandler.instance().getClient();
        WorldClient world = minecraft.theWorld;
        EntityClientPlayerMP player = minecraft.thePlayer;

        if (world != null)
        {
            if (world.provider instanceof WorldProviderMars)
            {
                if (world.provider.getSkyRenderer() instanceof SkyProviderMars)
                {
                    world.provider.setSkyRenderer(new SkyProviderMarsMP((IGalacticraftWorldProvider) world.provider));
                }
            }
            if (world.provider instanceof WorldProviderDiona)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderDiona((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderPolongnius)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderPolongnius((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderNibiru)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderNibiru((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderFronos)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderFronos((IGalacticraftWorldProvider) world.provider));
                }
            }
            else if (world.provider instanceof WorldProviderKoentus)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderKoentus((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderKapteynB)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderKapteynB((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderSiriusB)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderSiriusB((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderMercury)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderMercury((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderPhobos)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderPhobos((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderDeimos)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderDeimos((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderVenus)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderVenus((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    CloudRendererVenus cloudRendererVenus = new CloudRendererVenus();
                    FMLCommonHandler.instance().bus().register(cloudRendererVenus);
                    world.provider.setCloudRenderer(cloudRendererVenus);
                }
            }
            else if (world.provider instanceof WorldProviderPluto)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderPluto((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderJupiterOrbit)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderJupiterOrbit());
                    ((SkyProviderJupiterOrbit) world.provider.getSkyRenderer()).spinDeltaPerTick = ((WorldProviderJupiterOrbit) world.provider).getSpinManager().getSpinRate();
                    GCPlayerStatsClient.get(player).inFreefallFirstCheck = false;
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderMarsOrbit)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderMarsOrbit());
                    ((SkyProviderMarsOrbit) world.provider.getSkyRenderer()).spinDeltaPerTick = ((WorldProviderMarsOrbit) world.provider).getSpinManager().getSpinRate();
                    GCPlayerStatsClient.get(player).inFreefallFirstCheck = false;
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            else if (world.provider instanceof WorldProviderDarkAsteroids)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderDarkAsteroids((IGalacticraftWorldProvider) world.provider));
                }
                if (world.provider.getCloudRenderer() == null)
                {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
        }
    }
}