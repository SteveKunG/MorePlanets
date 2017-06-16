/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.proxy;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import micdoodle8.mods.galacticraft.core.client.render.block.BlockRendererMachine;
import micdoodle8.mods.galacticraft.core.client.render.block.BlockRendererMeteor;
import micdoodle8.mods.galacticraft.planets.mars.client.render.block.BlockRendererEgg;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.network.NetHandlerPlayServer;
import stevekung.mods.moreplanets.asteroids.darkasteroids.particles.EntityDarkPortalFX;
import stevekung.mods.moreplanets.core.particles.EntityMCLavaFX;
import stevekung.mods.moreplanets.core.particles.EntitySmokeFXMP;
import stevekung.mods.moreplanets.core.renderer.EntityRendererMP;
import stevekung.mods.moreplanets.core.renderer.ItemRendererMP;
import stevekung.mods.moreplanets.core.renderer.TileEntityRendererMP;
import stevekung.mods.moreplanets.core.renderer.blocks.BlockRendererAncientChest;
import stevekung.mods.moreplanets.core.renderer.blocks.BlockRendererFence;
import stevekung.mods.moreplanets.core.renderer.blocks.BlockRendererSlime;
import stevekung.mods.moreplanets.core.renderer.blocks.BlockRendererTreasureChest;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.moons.koentus.particles.EntityCrystalSmokeFX;
import stevekung.mods.moreplanets.moons.koentus.particles.EntityKoentusDripParticleFX;
import stevekung.mods.moreplanets.moons.koentus.particles.EntityMeteorSmokeFX;
import stevekung.mods.moreplanets.moons.koentus.render.blocks.BlockRendererCrystalSegment;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityKoentusAncientChest;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityKoentusTreasureChest;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.diona.particles.EntityBluePortalFX;
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaAncientChest;
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaTreasureChest;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.particles.*;
import stevekung.mods.moreplanets.planets.fronos.render.blocks.BlockRendererFilledCup;
import stevekung.mods.moreplanets.planets.fronos.render.blocks.BlockRendererOyster;
import stevekung.mods.moreplanets.planets.fronos.tileentities.*;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.particles.EntityFrozenWaterDripFX;
import stevekung.mods.moreplanets.planets.kapteynb.particles.EntityGeyserFX;
import stevekung.mods.moreplanets.planets.kapteynb.render.blocks.BlockRendererIcyPoisonCrystal;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityKapteynBAncientChestTemp;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityKapteynBTreasureChest;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;
import stevekung.mods.moreplanets.planets.mercury.tileentities.TileEntityMercuryAncientChest;
import stevekung.mods.moreplanets.planets.mercury.tileentities.TileEntityMercuryTreasureChest;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.particles.EntityGeneratorSmokeFX;
import stevekung.mods.moreplanets.planets.nibiru.particles.EntityInfectedSporeFX;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityNibiruAncientChest;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityNibiruTreasureChest;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;
import stevekung.mods.moreplanets.planets.pluto.particles.EntityXeoniumSmokeFX;
import stevekung.mods.moreplanets.planets.pluto.tileentities.TileEntityPlutoAncientChest;
import stevekung.mods.moreplanets.planets.pluto.tileentities.TileEntityPlutoTreasureChest;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.polongnius.particles.EntityCheeseBubbleFX;
import stevekung.mods.moreplanets.planets.polongnius.particles.EntityCheeseOfMilkDripFX;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityPolongniusAncientChest;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityPolongniusTreasureChest;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.siriusb.particles.EntitySiriusFlameFX;
import stevekung.mods.moreplanets.planets.siriusb.particles.EntitySiriusLavaDripFX;
import stevekung.mods.moreplanets.planets.siriusb.tileentities.TileEntitySiriusBAncientChest;
import stevekung.mods.moreplanets.planets.siriusb.tileentities.TileEntitySiriusBTreasureChest;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;
import stevekung.mods.moreplanets.planets.venus.model.ModelJetpack;
import stevekung.mods.moreplanets.planets.venus.particles.EntityVenusSmokeFX;
import stevekung.mods.moreplanets.planets.venus.tileentities.TileEntityVenusAncientChest;
import stevekung.mods.moreplanets.planets.venus.tileentities.TileEntityVenusTreasureChest;

public class ClientProxyMP extends CommonProxyMP
{
    private static int meteorRenderID;
    private static int machineRenderID;
    private static int eledosEggRenderID;
    private static int slimeBlockRenderID;
    private static int fenceBlockRenderID;
    private static int crystalSegmentRenderID;
    private static int mineralWaterCupRenderID;
    private static int icyPoisonCrystalRenderID;

    private static int spaceOysterOpenRenderID;
    private static int spaceOysterCloseRenderID;
    private static int cavernOysterOpenRenderID;
    private static int cavernOysterCloseRenderID;

    private static int dionaTreasureChestRenderID;
    private static int polongniusTreasureChestRenderID;
    private static int nibiruTreasureChestRenderID;
    private static int koentusTreasureChestRenderID;
    private static int fronosTreasureChestRenderID;
    private static int kapteynBTreasureChestRenderID;
    private static int siriusBTreasureChestRenderID;
    private static int mercuryTreasureChestRenderID;
    private static int venusTreasureChestRenderID;
    private static int plutoTreasureChestRenderID;

    private static int dionaAncientChestRenderID;
    private static int polongniusAncientChestRenderID;
    private static int nibiruAncientChestRenderID;
    private static int koentusAncientChestRenderID;
    private static int fronosAncientChestRenderID;
    private static int kapteynBAncientChestRenderID;
    private static int siriusBAncientChestRenderID;
    private static int mercuryAncientChestRenderID;
    private static int venusAncientChestRenderID;
    private static int plutoAncientChestRenderID;

    public static Map<Item, ModelBiped> jetpackModel = new HashMap<Item, ModelBiped>();

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        EntityRendererMP.init();
        ItemRendererMP.registerItemRenderers();
        TileEntityRendererMP.registerTileEntityRenderers();
        ClientProxyMP.registerBlockRenderers();

        ModelJetpack jetpack = new ModelJetpack(0.75F);
        jetpackModel.put(VenusItems.jetpack, jetpack);
    }

    private static void registerBlockRenderers()
    {
        ClientProxyMP.meteorRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.machineRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.eledosEggRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.slimeBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.fenceBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.crystalSegmentRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.mineralWaterCupRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.icyPoisonCrystalRenderID = RenderingRegistry.getNextAvailableRenderId();

        ClientProxyMP.spaceOysterOpenRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.spaceOysterCloseRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.cavernOysterOpenRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.cavernOysterCloseRenderID = RenderingRegistry.getNextAvailableRenderId();

        ClientProxyMP.dionaTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.polongniusTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.nibiruTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.koentusTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.fronosTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.kapteynBTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.siriusBTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.mercuryTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.venusTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.plutoTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();

        ClientProxyMP.dionaAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.polongniusAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.nibiruAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.koentusAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.fronosAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.kapteynBAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.siriusBAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.mercuryAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.venusAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
        ClientProxyMP.plutoAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.dionaTreasureChestRenderID, DionaBlocks.diona_treasure_chest, new TileEntityDionaTreasureChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.polongniusTreasureChestRenderID, PolongniusBlocks.polongnius_treasure_chest, new TileEntityPolongniusTreasureChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.nibiruTreasureChestRenderID, NibiruBlocks.nibiru_treasure_chest, new TileEntityNibiruTreasureChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.koentusTreasureChestRenderID, KoentusBlocks.koentus_treasure_chest, new TileEntityKoentusTreasureChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.fronosTreasureChestRenderID, FronosBlocks.fronos_treasure_chest, new TileEntityFronosTreasureChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.kapteynBTreasureChestRenderID, KapteynBBlocks.kapteyn_b_treasure_chest, new TileEntityKapteynBTreasureChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.siriusBTreasureChestRenderID, SiriusBBlocks.sirius_b_treasure_chest, new TileEntitySiriusBTreasureChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.mercuryTreasureChestRenderID, MercuryBlocks.mercury_treasure_chest, new TileEntityMercuryTreasureChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.venusTreasureChestRenderID, VenusBlocks.venus_treasure_chest, new TileEntityVenusTreasureChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererTreasureChest(ClientProxyMP.plutoTreasureChestRenderID, PlutoBlocks.pluto_treasure_chest, new TileEntityPlutoTreasureChest()));

        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.dionaAncientChestRenderID, DionaBlocks.diona_ancient_chest, new TileEntityDionaAncientChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.polongniusAncientChestRenderID, PolongniusBlocks.polongnius_ancient_chest, new TileEntityPolongniusAncientChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.nibiruAncientChestRenderID, NibiruBlocks.nibiru_ancient_chest, new TileEntityNibiruAncientChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.koentusAncientChestRenderID, KoentusBlocks.koentus_ancient_chest, new TileEntityKoentusAncientChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.fronosAncientChestRenderID, FronosBlocks.fronos_ancient_chest, new TileEntityFronosAncientChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.kapteynBAncientChestRenderID, KapteynBBlocks.kapteyn_b_ancient_chest, new TileEntityKapteynBAncientChestTemp()));
        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.siriusBAncientChestRenderID, SiriusBBlocks.sirius_b_ancient_chest, new TileEntitySiriusBAncientChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.mercuryAncientChestRenderID, MercuryBlocks.mercury_ancient_chest, new TileEntityMercuryAncientChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.venusAncientChestRenderID, VenusBlocks.venus_ancient_chest, new TileEntityVenusAncientChest()));
        RenderingRegistry.registerBlockHandler(new BlockRendererAncientChest(ClientProxyMP.plutoAncientChestRenderID, PlutoBlocks.pluto_ancient_chest, new TileEntityPlutoAncientChest()));

        RenderingRegistry.registerBlockHandler(new BlockRendererMeteor(ClientProxyMP.meteorRenderID));
        RenderingRegistry.registerBlockHandler(new BlockRendererMachine(ClientProxyMP.machineRenderID));
        RenderingRegistry.registerBlockHandler(new BlockRendererEgg(ClientProxyMP.eledosEggRenderID));
        RenderingRegistry.registerBlockHandler(new BlockRendererSlime(ClientProxyMP.slimeBlockRenderID));
        RenderingRegistry.registerBlockHandler(new BlockRendererFence(ClientProxyMP.fenceBlockRenderID));
        RenderingRegistry.registerBlockHandler(new BlockRendererCrystalSegment(ClientProxyMP.crystalSegmentRenderID));
        RenderingRegistry.registerBlockHandler(new BlockRendererFilledCup(ClientProxyMP.mineralWaterCupRenderID, new TileEntityMineralWaterCup()));
        RenderingRegistry.registerBlockHandler(new BlockRendererIcyPoisonCrystal(ClientProxyMP.icyPoisonCrystalRenderID));

        RenderingRegistry.registerBlockHandler(new BlockRendererOyster(ClientProxyMP.spaceOysterOpenRenderID, new TileEntitySpaceOysterOpen()));
        RenderingRegistry.registerBlockHandler(new BlockRendererOyster(ClientProxyMP.spaceOysterCloseRenderID, new TileEntitySpaceOysterClose()));
        RenderingRegistry.registerBlockHandler(new BlockRendererOyster(ClientProxyMP.cavernOysterOpenRenderID, new TileEntityCavernOysterOpen()));
        RenderingRegistry.registerBlockHandler(new BlockRendererOyster(ClientProxyMP.cavernOysterCloseRenderID, new TileEntityCavernOysterClose()));
    }

    @Override
    public int getBlockRender(Block block)
    {
        if (block == KoentusBlocks.fallen_koentus_meteor || block == PolongniusBlocks.fallen_polongnius_meteor || block == KapteynBBlocks.fallen_ice_crystal_meteor)
        {
            return ClientProxyMP.meteorRenderID;
        }
        else if (block == DionaBlocks.diona_treasure_chest)
        {
            return ClientProxyMP.dionaTreasureChestRenderID;
        }
        else if (block == NibiruBlocks.nibiru_ancient_chest)
        {
            return ClientProxyMP.nibiruAncientChestRenderID;
        }
        else if (block == PolongniusBlocks.polongnius_treasure_chest)
        {
            return ClientProxyMP.polongniusTreasureChestRenderID;
        }
        else if (block == NibiruBlocks.nibiru_treasure_chest)
        {
            return ClientProxyMP.nibiruTreasureChestRenderID;
        }
        else if (block == NibiruBlocks.power_crystal_generator || block == FronosBlocks.candy_extractor_idle || block == FronosBlocks.candy_extractor_active || block == FronosBlocks.mineral_water_generator)
        {
            return ClientProxyMP.machineRenderID;
        }
        else if (block == PolongniusBlocks.polongnius_ancient_chest)
        {
            return ClientProxyMP.polongniusAncientChestRenderID;
        }
        else if (block == KoentusBlocks.koentus_treasure_chest)
        {
            return ClientProxyMP.koentusTreasureChestRenderID;
        }
        else if (block == KoentusBlocks.eledos_egg)
        {
            return ClientProxyMP.eledosEggRenderID;
        }
        else if (block == FronosBlocks.space_oyster_open)
        {
            return ClientProxyMP.spaceOysterOpenRenderID;
        }
        else if (block == FronosBlocks.space_oyster_close)
        {
            return ClientProxyMP.spaceOysterCloseRenderID;
        }
        else if (block == FronosBlocks.fronos_treasure_chest)
        {
            return ClientProxyMP.fronosTreasureChestRenderID;
        }
        else if (block == KapteynBBlocks.kapteyn_b_treasure_chest)
        {
            return ClientProxyMP.kapteynBTreasureChestRenderID;
        }
        else if (block == DionaBlocks.diona_ancient_chest)
        {
            return ClientProxyMP.dionaAncientChestRenderID;
        }
        else if (block == KoentusBlocks.koentus_ancient_chest)
        {
            return ClientProxyMP.koentusAncientChestRenderID;
        }
        else if (block == FronosBlocks.fronos_ancient_chest)
        {
            return ClientProxyMP.fronosAncientChestRenderID;
        }
        else if (block == FronosBlocks.cavern_oyster_open)
        {
            return ClientProxyMP.cavernOysterOpenRenderID;
        }
        else if (block == FronosBlocks.cavern_oyster_close)
        {
            return ClientProxyMP.cavernOysterCloseRenderID;
        }
        else if (block == PolongniusBlocks.cheese_slime_block || block == FronosBlocks.jelly_block)
        {
            return ClientProxyMP.slimeBlockRenderID;
        }
        else if (block == KapteynBBlocks.kapteyn_b_ancient_chest)
        {
            return ClientProxyMP.kapteynBAncientChestRenderID;
        }
        else if (block == SiriusBBlocks.sirius_b_treasure_chest)
        {
            return ClientProxyMP.siriusBTreasureChestRenderID;
        }
        else if (block == SiriusBBlocks.sirius_b_ancient_chest)
        {
            return ClientProxyMP.siriusBAncientChestRenderID;
        }
        else if (block == FronosBlocks.fronos_fence || block == NibiruBlocks.nibiru_fence)
        {
            return ClientProxyMP.fenceBlockRenderID;
        }
        else if (block == KoentusBlocks.crystal_segment)
        {
            return ClientProxyMP.crystalSegmentRenderID;
        }
        else if (block == FronosBlocks.mineral_water_cup)
        {
            return ClientProxyMP.mineralWaterCupRenderID;
        }
        else if (block == MercuryBlocks.mercury_ancient_chest)
        {
            return ClientProxyMP.mercuryAncientChestRenderID;
        }
        else if (block == MercuryBlocks.mercury_treasure_chest)
        {
            return ClientProxyMP.mercuryTreasureChestRenderID;
        }
        else if (block == VenusBlocks.venus_ancient_chest)
        {
            return ClientProxyMP.venusAncientChestRenderID;
        }
        else if (block == VenusBlocks.venus_treasure_chest)
        {
            return ClientProxyMP.venusTreasureChestRenderID;
        }
        else if (block == KapteynBBlocks.icy_poison_crystal)
        {
            return ClientProxyMP.icyPoisonCrystalRenderID;
        }
        else if (block == PlutoBlocks.pluto_ancient_chest)
        {
            return ClientProxyMP.plutoAncientChestRenderID;
        }
        else if (block == PlutoBlocks.pluto_treasure_chest)
        {
            return ClientProxyMP.plutoTreasureChestRenderID;
        }
        return -1;
    }

    @Override
    public void spawnMotionParticle(String string, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        EntityFX entityfx = null;
        Minecraft mc = Minecraft.getMinecraft();

        if (mc.theWorld != null && mc.renderViewEntity != null && mc.effectRenderer != null)
        {
            int i = mc.gameSettings.particleSetting;
            double d6 = mc.renderViewEntity.posX - x;
            double d7 = mc.renderViewEntity.posY - y;
            double d8 = mc.renderViewEntity.posZ - z;
            double d9 = 16.0D;

            if (i == 1 && mc.theWorld.rand.nextInt(3) == 0)
            {
                i = 2;
            }
            if (d6 * d6 + d7 * d7 + d8 * d8 > d9 * d9)
            {
                return;
            }
            else if (i > 1)
            {
                return;
            }

            if (string == "koentusSmoke")
            {
                entityfx = new EntityMeteorSmokeFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
            }
            else if (string == "cavernOyster")
            {
                entityfx = new EntityCavernOysterFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
            }
            else if (string == "infectedSupended")
            {
                entityfx = new EntityInfectedSporeFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
            }
            else if (string == "bluePortal")
            {
                entityfx = new EntityBluePortalFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
            }
            else if (string == "jungleIris")
            {
                entityfx = new EntityJungleIrisFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
            }
            else if (string == "mcLargeSmoke")
            {
                entityfx = new EntitySmokeFXMP(mc.theWorld, x, y, z, motionX, motionY, motionZ);
            }
            else if (string == "waterGeyser")
            {
                entityfx = new EntityGeyserFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
            }
            else if (string == "darkPortal")
            {
                entityfx = new EntityDarkPortalFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
            }
            mc.effectRenderer.addEffect(entityfx);
        }
    }

    @Override
    public void spawnParticle(String string, double x, double y, double z)
    {
        EntityFX entityfx = null;
        Minecraft mc = Minecraft.getMinecraft();

        if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null)
        {
            int i = mc.gameSettings.particleSetting;
            double d6 = mc.renderViewEntity.posX - x;
            double d7 = mc.renderViewEntity.posY - y;
            double d8 = mc.renderViewEntity.posZ - z;
            double d9 = 16.0D;

            if (i == 1 && mc.theWorld.rand.nextInt(3) == 0)
            {
                i = 2;
            }
            if (d6 * d6 + d7 * d7 + d8 * d8 > d9 * d9)
            {
                return;
            }
            else if (i > 1)
            {
                return;
            }

            if (string == "koentusBricksDrip")
            {
                entityfx = new EntityKoentusDripParticleFX(mc.theWorld, x, y, z, Material.water);
            }
            else if (string == "coconutMilkDrip")
            {
                entityfx = new EntityCoconutMilkDripFX(mc.theWorld, x, y, z, Material.water);
            }
            else if (string == "mineralWaterDrip")
            {
                entityfx = new EntityMineralWaterDripFX(mc.theWorld, x, y, z, Material.water);
            }
            else if (string == "ovantineDrip")
            {
                entityfx = new EntityOvantineDripFX(mc.theWorld, x, y, z, Material.water);
            }
            else if (string == "cheeseOfMilkDrip")
            {
                entityfx = new EntityCheeseOfMilkDripFX(mc.theWorld, x, y, z, Material.water);
            }
            else if (string == "frozenWaterDrip")
            {
                entityfx = new EntityFrozenWaterDripFX(mc.theWorld, x, y, z, Material.water);
            }
            else if (string == "teaDrip")
            {
                entityfx = new EntityTeaDripFX(mc.theWorld, x, y, z, Material.water);
            }
            else if (string == "caramelDrip")
            {
                entityfx = new EntityCaramelDripFX(mc.theWorld, x, y, z, Material.water);
            }
            else if (string == "siriusLavaDrip")
            {
                entityfx = new EntitySiriusLavaDripFX(mc.theWorld, x, y, z, Material.lava);
            }
            else if (string == "cheeseSlime")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, PolongniusItems.cheese_slimeball);
            }
            else if (string == "vanillaBall")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.cream_ball, 0);
            }
            else if (string == "chocolateBall")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.cream_ball, 1);
            }
            else if (string == "strawberryBall")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.cream_ball, 2);
            }
            else if (string == "orangeBall")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.cream_ball, 3);
            }
            else if (string == "teaBall")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.cream_ball, 4);
            }
            else if (string == "lemonBall")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.cream_ball, 5);
            }
            else if (string == "jellyGrape")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 0);
            }
            else if (string == "jellyRaspberry")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 1);
            }
            else if (string == "jellyStrawberry")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 2);
            }
            else if (string == "jellyBerry")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 3);
            }
            else if (string == "jellyLime")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 4);
            }
            else if (string == "jellyOrange")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 5);
            }
            else if (string == "jellyGreen")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 6);
            }
            else if (string == "jellyLemon")
            {
                entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 7);
            }
            else if (string == "orangeDandelion")
            {
                entityfx = new EntityOrangeDandelionFX(mc.theWorld, x, y, z, 2.0F);
            }
            else if (string == "pinkDandelion")
            {
                entityfx = new EntityPinkDandelionFX(mc.theWorld, x, y, z, 2.0F);
            }
            else if (string == "purpleDandelion")
            {
                entityfx = new EntityPurpleDandelionFX(mc.theWorld, x, y, z, 2.0F);
            }
            else if (string == "cheeseBubble")
            {
                entityfx = new EntityCheeseBubbleFX(mc.theWorld, x, y, z);
            }
            else if (string == "crystalSmoke")
            {
                entityfx = new EntityCrystalSmokeFX(mc.theWorld, x, y, z);
            }
            else if (string == "ichoriusSmoke")
            {
                entityfx = new EntityGeneratorSmokeFX(mc.theWorld, x, y, z);
            }
            else if (string == "purpleSpike")
            {
                entityfx = new EntityPurpleFlowerFX(mc.theWorld, x, y, z);
            }
            else if (string == "coconutMilk")
            {
                entityfx = new EntityCoconutMilkFX(mc.theWorld, x, y, z);
            }
            else if (string == "mineralWater")
            {
                entityfx = new EntityMineralWaterFX(mc.theWorld, x, y, z);
            }
            else if (string == "ovantineSmoke")
            {
                entityfx = new EntityOvantineSmokeFX(mc.theWorld, x, y, z);
            }
            else if (string == "goldDust")
            {
                entityfx = new EntityGoldenGrassFX(mc.theWorld, x, y, z);
            }
            else if (string == "goldSmoke")
            {
                entityfx = new EntityGoldenSmokeFX(mc.theWorld, x, y, z);
            }
            else if (string == "tea")
            {
                entityfx = new EntityTeaFluidFX(mc.theWorld, x, y, z);
            }
            else if (string == "siriusFlame")
            {
                entityfx = new EntitySiriusFlameFX(mc.theWorld, x, y, z, "siriusb:textures/particles/sirius_flame.png");
            }
            else if (string == "siriusLava")
            {
                entityfx = new EntityMCLavaFX(mc.theWorld, x, y, z, "siriusb:textures/particles/sirius_lava");
            }
            else if (string == "uraniumLava")
            {
                entityfx = new EntityMCLavaFX(mc.theWorld, x, y, z, "kapteynb:textures/particles/uranium_smoke");
            }
            else if (string == "blueFlame")
            {
                entityfx = new EntityBlueFlameFX(mc.theWorld, x, y, z);
            }
            else if (string == "venusSmoke")
            {
                entityfx = new EntityVenusSmokeFX(mc.theWorld, x, y, z);
            }
            else if (string == "xeoniumSmoke")
            {
                entityfx = new EntityXeoniumSmokeFX(mc.theWorld, x, y, z);
            }
            mc.effectRenderer.addEffect(entityfx);
        }
    }

    @Override
    public void resetPlayerFloatingTick(EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            ObfuscationReflectionHelper.setPrivateValue(NetHandlerPlayServer.class, ((EntityPlayerMP)player).playerNetServerHandler, Integer.valueOf(0), new String[] { "field_147365_f", "floatingTickCount" });
        }
    }
}