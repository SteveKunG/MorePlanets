package stevekung.mods.moreplanets.core.event;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import org.lwjgl.input.Keyboard;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore.EventSpecialRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.renderer.MultiblockRendererUtils;
import stevekung.mods.moreplanets.client.renderer.ShieldRenderer;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.core.capability.AbstractCapabilityDataMP;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.entity.IInfectedPurlonite;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.planets.diona.client.renderer.FakeAlienBeamRenderer;
import stevekung.mods.moreplanets.planets.diona.dimension.WorldProviderDiona;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.IMorePlanetsBoss;
import stevekung.mods.moreplanets.utils.blocks.fluid.LiquidUtils;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;
import stevekung.mods.stevekunglib.utils.client.GLConstants;
import stevekung.mods.stevekunglib.utils.client.event.AddRainParticleEvent;
import stevekung.mods.stevekunglib.utils.client.event.CameraTransformEvent;
import stevekung.mods.stevekunglib.utils.client.event.FirstPersonViewOverlayEvent;
import stevekung.mods.stevekunglib.utils.client.event.RenderEntityOverlayEvent;

public class ClientEventHandler
{
    private final Map<BlockPos, Integer> beamList = new HashMap<>();
    private Minecraft mc;
    private boolean firstWorldJoin;
    private boolean initVersionCheck;
    public static final List<BlockPos> RECEIVER_RENDER_POS = new ArrayList<>();
    public static final List<BlockPos> WASTE_RENDER_POS = new ArrayList<>();
    public static final Set<IMorePlanetsBoss> BOSSES = Collections.newSetFromMap(new WeakHashMap<>());
    private static final ResourceLocation BOSS_BAR = new ResourceLocation("moreplanets:textures/gui/boss_bars.png");

    public ClientEventHandler()
    {
        this.mc = Minecraft.getMinecraft();
    }

    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
    {
        this.firstWorldJoin = false;
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (ConfigManagerMP.moreplanets_general.enableSurvivalPlanetSelection && event.getEntity() == this.mc.player && !this.firstWorldJoin && !WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData && !WorldTickEventHandler.survivalPlanetData.disableMessage)
        {
            ITextComponent component = new TextComponentString(ColorUtils.stringToRGB(IItemRarity.ALIEN).toColoredFont() + "[More Planets] ").appendSibling(new TextComponentTranslation("message.survival_planet.1").appendText(" ").appendSibling(new TextComponentTranslation("message.survival_planet.2").setStyle(new Style().setColor(TextFormatting.AQUA))).appendText(" ").appendSibling(new TextComponentTranslation("message.survival_planet.3")).setStyle(new Style().setColor(TextFormatting.YELLOW)));
            component.getStyle().setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/mpcelestial"));
            ClientUtils.printClientMessage(component);
            ITextComponent component2 = new TextComponentTranslation("message.survival_planet.4").appendText(" ").appendSibling(new TextComponentTranslation("message.survival_planet.5").setStyle(new Style().setColor(TextFormatting.RED))).setStyle(new Style().setColor(TextFormatting.YELLOW));
            component2.getStyle().setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/mpcelestial disable"));
            ClientUtils.printClientMessage(component2);
            this.firstWorldJoin = true;
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderWorldLast(RenderWorldLastEvent event)
    {
        RenderManager manager = this.mc.getRenderManager();

        if (this.mc.world != null)
        {
            if (!ClientEventHandler.RECEIVER_RENDER_POS.isEmpty())
            {
                for (BlockPos renderPos : ClientEventHandler.RECEIVER_RENDER_POS)
                {
                    TileEntity tile = this.mc.world.getTileEntity(renderPos);
                    GlStateManager.pushMatrix();
                    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

                    if (tile != null && tile instanceof TileEntityDarkEnergyReceiver)
                    {
                        TileEntityDarkEnergyReceiver der = (TileEntityDarkEnergyReceiver) tile;
                        der.multiBlockClientLists.forEach((pos, state) -> MultiblockRendererUtils.renderBlock(renderPos.getX() - manager.renderPosX, renderPos.getY() - manager.renderPosY, renderPos.getZ() - manager.renderPosZ, pos, state));
                        der.multiTileClientLists.forEach((pos, tile2) -> MultiblockRendererUtils.renderTile(renderPos.getX() - manager.renderPosX, renderPos.getY() - manager.renderPosY, renderPos.getZ() - manager.renderPosZ, pos, tile2));
                    }
                }
                GlStateManager.popMatrix();
            }
            if (!ClientEventHandler.WASTE_RENDER_POS.isEmpty())
            {
                for (BlockPos renderPos : ClientEventHandler.WASTE_RENDER_POS)
                {
                    TileEntity tile = this.mc.world.getTileEntity(renderPos);
                    GlStateManager.pushMatrix();
                    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

                    if (tile != null && tile instanceof TileEntityNuclearWasteGenerator)
                    {
                        TileEntityNuclearWasteGenerator generator = (TileEntityNuclearWasteGenerator) tile;
                        generator.multiBlockClientLists.forEach((pos, state) -> MultiblockRendererUtils.renderBlock(renderPos.getX() - manager.renderPosX, renderPos.getY() - manager.renderPosY, renderPos.getZ() - manager.renderPosZ, pos, state));
                        generator.multiTileClientLists.forEach(pos -> MultiblockRendererUtils.renderTankTile(renderPos.getX() - manager.renderPosX, renderPos.getY() - manager.renderPosY, renderPos.getZ() - manager.renderPosZ, pos));
                    }
                    GlStateManager.popMatrix();
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTick(ClientTickEvent event)
    {
        if (MorePlanetsMod.isDevelopment)
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_F7))
            {
                try
                {
                    // used for real time debugging item description
                    Object proxy = Class.forName("mezz.jei.JustEnoughItems").getDeclaredMethod("getProxy").invoke(Class.forName("mezz.jei.startup.ProxyCommonClient"));
                    Field pluginsField = proxy.getClass().getDeclaredField("plugins");
                    pluginsField.setAccessible(true);
                    Class<?> starter = Class.forName("mezz.jei.startup.JeiStarter");
                    Object obj = starter.newInstance();
                    Method method = obj.getClass().getDeclaredMethod("start", List.class);
                    method.invoke(obj, (ArrayList<?>) pluginsField.get(proxy));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5) && !this.mc.player.getHeldItemMainhand().isEmpty())
            {
                GuiScreen.setClipboardString(this.mc.player.getHeldItemMainhand().getDisplayName());
            }
        }
        if (this.mc.currentScreen instanceof GuiMainMenu)
        {
            ClientEventHandler.RECEIVER_RENDER_POS.clear();
            ClientEventHandler.WASTE_RENDER_POS.clear();
            ClientEventHandler.BOSSES.clear();
        }
        if (event.phase == Phase.START)
        {
            if (this.mc.player != null)
            {
                if (!this.initVersionCheck)
                {
                    MorePlanetsMod.CHECKER.startCheckIfFailed();

                    if (ConfigManagerMP.moreplanets_general.enableVersionChecker)
                    {
                        MorePlanetsMod.CHECKER.printInfo(this.mc.player);
                    }
                    this.initVersionCheck = true;
                }

                ShieldRenderer.clearShields();

                for (TileEntity tile : this.mc.player.world.tickableTileEntities)
                {
                    if (tile instanceof TileEntityShieldGenerator)
                    {
                        ShieldRenderer.addShield((TileEntityShieldGenerator)tile);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPlayerTick(PlayerTickEvent event)
    {
        this.runAlienBeamTick(event.player);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderSpecial(EventSpecialRender event)
    {
        if (this.mc.player != null)
        {
            Iterator<Map.Entry<BlockPos, Integer>> it = this.beamList.entrySet().iterator();

            while (it.hasNext())
            {
                Map.Entry<BlockPos, Integer> entry = it.next();
                FakeAlienBeamRenderer.INSTANCE.renderBeam(entry.getKey().getX() - ClientProxyCore.playerPosX, entry.getKey().getY() - ClientProxyCore.playerPosY, entry.getKey().getZ() - ClientProxyCore.playerPosZ, event.partialTicks);
            }
            ShieldRenderer.renderShields(this.mc.player, event.partialTicks);
        }
    }

    @SubscribeEvent
    public void onRenderEntityOverlay(RenderEntityOverlayEvent event)
    {
        Entity entity = event.getEntity();

        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase)entity;

            if (((IInfectedPurlonite)living).isInfectedPurlonite())
            {
                GlStateManager.disableLighting();
                TextureMap texturemap = this.mc.getTextureMapBlocks();
                TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite("moreplanets:blocks/infected_purlonite");
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)event.getX(), (float)event.getY(), (float)event.getZ());
                float f = living.width * 1.4F;
                GlStateManager.scale(f, f, f);
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder worldrenderer = tessellator.getBuffer();
                float f1 = 0.5F;
                float f2 = 0.0F;
                float f3 = living.height / f;
                float f4 = (float)(living.posY - living.getEntityBoundingBox().minY);
                GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                GlStateManager.translate(0.0F, 0.0F, -0.3F + (int)f3 * 0.02F);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                float f5 = 0.0F;
                int i = 0;
                worldrenderer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);

                while (f3 > 0.0F)
                {
                    this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                    float f6 = textureatlassprite.getMinU();
                    float f7 = textureatlassprite.getMinV();
                    float f8 = textureatlassprite.getMaxU();
                    float f9 = textureatlassprite.getMaxV();

                    if (i / 2 % 2 == 0)
                    {
                        float f10 = f8;
                        f8 = f6;
                        f6 = f10;
                    }
                    worldrenderer.pos(f1 - f2, 0.0F - f4, f5).tex(f8, f9).endVertex();
                    worldrenderer.pos(-f1 - f2, 0.0F - f4, f5).tex(f6, f9).endVertex();
                    worldrenderer.pos(-f1 - f2, 1.4F - f4, f5).tex(f6, f7).endVertex();
                    worldrenderer.pos(f1 - f2, 1.4F - f4, f5).tex(f8, f7).endVertex();
                    f3 -= 0.45F;
                    f4 -= 0.45F;
                    f1 *= 0.9F;
                    f5 += 0.03F;
                    ++i;
                }
                tessellator.draw();
                GlStateManager.popMatrix();
                GlStateManager.enableLighting();
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGameOverlay(RenderGameOverlayEvent event)
    {
        if (event.getType().equals(RenderGameOverlayEvent.ElementType.PORTAL))
        {
            AbstractCapabilityDataMP data = AbstractCapabilityDataMP.get(this.mc.player);
            float f1 = data.getPrevTimeInPortal() + (data.getTimeInPortal() - data.getPrevTimeInPortal()) * event.getPartialTicks();

            if (f1 > 0.0F)
            {
                this.renderPortal(f1, event.getResolution());
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderFirstPersonViewOverlay(FirstPersonViewOverlayEvent event)
    {
        if (this.mc.player.isPotionActive(MPPotions.INFECTED_PURLONITE))
        {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder worldrenderer = tessellator.getBuffer();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
            GlStateManager.depthFunc(519);
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            float f = 1.0F;

            for (int i = 0; i < 2; ++i)
            {
                GlStateManager.pushMatrix();
                TextureAtlasSprite textureatlassprite = this.mc.getTextureMapBlocks().getAtlasSprite("moreplanets:blocks/infected_purlonite");
                this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                float f1 = textureatlassprite.getMinU();
                float f2 = textureatlassprite.getMaxU();
                float f3 = textureatlassprite.getMinV();
                float f4 = textureatlassprite.getMaxV();
                float f5 = (0.0F - f) / 2.0F;
                float f6 = f5 + f;
                float f7 = 0.0F - f / 2.0F;
                float f8 = f7 + f;
                float f9 = -0.5F;
                GlStateManager.translate(-(i * 2 - 1) * 0.24F, -0.2F, 0.0F);
                GlStateManager.rotate((i * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
                worldrenderer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
                worldrenderer.pos(f5, f7, f9).tex(f2, f4).endVertex();
                worldrenderer.pos(f6, f7, f9).tex(f1, f4).endVertex();
                worldrenderer.pos(f6, f8, f9).tex(f1, f3).endVertex();
                worldrenderer.pos(f5, f8, f9).tex(f2, f3).endVertex();
                tessellator.draw();
                GlStateManager.popMatrix();
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GlStateManager.depthFunc(515);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onBossBarRender(RenderGameOverlayEvent.BossInfo event)
    {
        int y = event.getY();
        int width = event.getResolution().getScaledWidth();
        UUID uuid = event.getBossInfo().getUniqueId();
        int bossBarWidth = 200;
        int bossBarHeight = 16;
        int barX = width / 2 - bossBarWidth / 2;
        int barY = y + 4;
        int percent = (int) (bossBarWidth * event.getBossInfo().getPercent());

        for (IMorePlanetsBoss boss : ClientEventHandler.BOSSES)
        {
            if (boss.getBossUUID().equals(uuid))
            {
                String bossType = boss.getBossType().getName();
                String name = boss.getBossName();

                // start render custom boss bar
                event.setCanceled(true);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.getTextureManager().bindTexture(BOSS_BAR);
                this.mc.ingameGUI.drawTexturedModalRect(barX, barY, 0, 0, bossBarWidth, bossBarHeight);
                this.mc.ingameGUI.drawTexturedModalRect(barX, barY, 0, 16, percent, bossBarHeight);
                this.mc.ingameGUI.getFontRenderer().drawStringWithShadow(bossType, width / 2 - this.mc.ingameGUI.getFontRenderer().getStringWidth(bossType) / 2, y - 8, 16777215);
                this.mc.ingameGUI.getFontRenderer().drawStringWithShadow(TextFormatting.ITALIC + name, width / 2 - this.mc.ingameGUI.getFontRenderer().getStringWidth(name) / 2, y + 8, boss.getBossTextColor());
                event.setIncrement(bossBarHeight * 2);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event)
    {
        float partialTicks = event.getRenderPartialTicks();
        EntityPlayer player = this.mc.player;

        if (event.getOverlayType() == OverlayType.WATER)
        {
            if (LiquidUtils.checkInsideBlock(player, MPBlocks.INFECTED_PURLONITE_WATER_FLUID_BLOCK))
            {
                event.setCanceled(true);
                this.renderOverlay("infected_purlonite_water", this.mc.player.getBrightness(), 0.75F, partialTicks, -0.5D);
            }
            if (LiquidUtils.checkInsideBlock(player, MPBlocks.CHEESE_MILK_FLUID_BLOCK))
            {
                event.setCanceled(true);
                this.renderOverlay("cheese_milk", this.mc.player.getBrightness(), 0.75F, partialTicks, -0.5D);
            }
            if (LiquidUtils.checkInsideBlock(player, MPBlocks.INFECTED_WATER_FLUID_BLOCK))
            {
                event.setCanceled(true);
                this.renderOverlay("infected_water", this.mc.player.getBrightness(), 0.5F, partialTicks, -0.5D);
            }
            if (LiquidUtils.checkInsideBlock(player, MPBlocks.GASEOUS_CHEESE_MILK_BLOCK))
            {
                event.setCanceled(true);
                this.renderOverlay("gaseous_cheese_milk", this.mc.player.getBrightness(), 0.75F, partialTicks, -0.25D);
            }
            if (LiquidUtils.checkInsideBlock(player, MPBlocks.HELIUM_GAS_BLOCK))
            {
                event.setCanceled(true);
                this.renderOverlay("helium_gas", this.mc.player.getBrightness(), 0.75F, partialTicks, -0.25D);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderFog(FogColors event)
    {
        EntityPlayer player = this.mc.player;

        if (LiquidUtils.checkInsideBlock(player, MPBlocks.INFECTED_PURLONITE_WATER_FLUID_BLOCK))
        {
            event.setRed(0.5F);
            event.setGreen(0.375F);
            event.setBlue(0.8F);
        }
        if (LiquidUtils.checkInsideBlock(player, MPBlocks.INFECTED_PURLONITE_LAVA_FLUID_BLOCK))
        {
            event.setRed(0.35F);
            event.setGreen(0.25F);
            event.setBlue(0.55F);
        }
        if (LiquidUtils.checkInsideBlock(player, MPBlocks.CHEESE_MILK_FLUID_BLOCK))
        {
            event.setRed(0.85F);
            event.setGreen(0.8F);
            event.setBlue(0.6F);
        }
        if (LiquidUtils.checkInsideBlock(player, MPBlocks.INFECTED_WATER_FLUID_BLOCK))
        {
            event.setRed(0.4F);
            event.setGreen(0.15F);
            event.setBlue(0.1F);
        }
        if (LiquidUtils.checkInsideBlock(player, MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK))
        {
            event.setRed(0.25F);
            event.setGreen(0.7F);
            event.setBlue(0.05F);
        }
        if (LiquidUtils.checkInsideBlock(player, MPBlocks.PURIFIED_WATER_FLUID_BLOCK))
        {
            event.setRed(0.4F);
            event.setGreen(0.625F);
            event.setBlue(0.75F);
        }
    }

    @SubscribeEvent
    public void onRenderCelestialPost(CelestialBodyRenderEvent.Post event)
    {
        if (this.mc.currentScreen instanceof GuiCelestialSelection)
        {
            GuiCelestialSelection gui = (GuiCelestialSelection) this.mc.currentScreen;
            boolean enable = true;
            float partialTicks = stevekung.mods.stevekunglib.client.event.ClientEventHandler.renderPartialTicks;

            if (event.celestialBody == GalacticraftCore.planetOverworld && enable)
            {
                float size = gui.getWidthForCelestialBody(event.celestialBody) / 16.0F;
                float orbitTick = MathHelper.sin(partialTicks * 0.2F) / 10.0F + 0.5F;
                GlStateManager.translate(6.0F, orbitTick + -6.5F, 0.0F);
                this.mc.renderEngine.bindTexture(new ResourceLocation("moreplanets:textures/gui/celestialbodies/ion_cannon.png"));
                gui.drawTexturedModalRect(-7.5F * size, -1.75F * size, 2.0F, 2.0F, 0, 0, 32, 32, false, false, 32, 32);
            }
        }
    }

    @SubscribeEvent
    public void onCameraTransform(CameraTransformEvent event)
    {
        AbstractCapabilityDataMP data = AbstractCapabilityDataMP.get(this.mc.player);
        int rendererUpdateCount = event.getRendererUpdateCount();
        float partialTicks = event.getPartialTicks();
        float f1 = data.getPrevTimeInPortal() + (data.getTimeInPortal() - data.getPrevTimeInPortal()) * partialTicks;

        if (f1 > 0.0F)
        {
            int i = 20;

            if (this.mc.player.isPotionActive(MobEffects.NAUSEA))
            {
                i = 7;
            }

            float f2 = 5.0F / (f1 * f1 + 5.0F) - f1 * 0.04F;
            f2 = f2 * f2;
            GlStateManager.rotate((rendererUpdateCount + partialTicks) * i, 0.0F, 1.0F, 1.0F);
            GlStateManager.scale(1.0F / f2, 1.0F, 1.0F);
            GlStateManager.rotate(-(rendererUpdateCount + partialTicks) * i, 0.0F, 1.0F, 1.0F);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onAddRainParticle(AddRainParticleEvent event)
    {
        World world = event.getWorld();
        BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
        Biome biome = world.getBiome(pos);

        if (world.provider instanceof WorldProviderNibiru)
        {
            event.setCanceled(true);

            if (biome == MPBiomes.GREEN_VEIN_FIELDS || biome == MPBiomes.GREEN_VEIN_FIELD_SHORE)
            {
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.PURIFIED_RAIN, event.getX(), event.getY(), event.getZ());
            }
            else
            {
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.INFECTED_RAIN, event.getX(), event.getY(), event.getZ());
            }
        }
    }

    private void renderOverlay(String texture, float brightness, float alpha, float partialTicks, double zoom)
    {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("moreplanets:textures/misc/" + texture + ".png"));
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        GlStateManager.color(brightness, brightness, brightness, alpha);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        float f7 = -this.mc.player.rotationYaw / 64.0F;
        float f8 = this.mc.player.rotationPitch / 64.0F;
        worldrenderer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(-1.0D, -1.0D, zoom).tex(4.0F + f7, 4.0F + f8).endVertex();
        worldrenderer.pos(1.0D, -1.0D, zoom).tex(0.0F + f7, 4.0F + f8).endVertex();
        worldrenderer.pos(1.0D, 1.0D, zoom).tex(0.0F + f7, 0.0F + f8).endVertex();
        worldrenderer.pos(-1.0D, 1.0D, zoom).tex(4.0F + f7, 0.0F + f8).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
    }

    private void runAlienBeamTick(EntityPlayer player)
    {
        Iterator<Map.Entry<BlockPos, Integer>> it = this.beamList.entrySet().iterator();

        while (it.hasNext())
        {
            Map.Entry<BlockPos, Integer> entry = it.next();
            int val = entry.getValue();

            if (val - 1 <= 0)
            {
                it.remove();
            }
            else
            {
                entry.setValue(val - 1);
            }
        }

        if (player.getRNG().nextInt(512) == 0 && player.world.provider instanceof WorldProviderDiona)
        {
            if (player.world.getSunBrightness(1.0F) < 0.1F)
            {
                double freq = player.getRNG().nextDouble() * Math.PI * 2.0F;
                double dist = 64.0F;
                double dX = dist * Math.cos(freq);
                double dZ = dist * Math.sin(freq);
                double posX = player.posX + dX;
                double posY = 48;
                double posZ = player.posZ + dZ;
                this.mc.world.playSound(player, posX, player.posY, posZ, MPSounds.ALIEN_BEAM, SoundCategory.WEATHER, 100.0F, 1.0F + player.getRNG().nextFloat() * 0.8F);
                this.beamList.put(new BlockPos(posX, posY, posZ), 40);
            }
        }
    }

    private void renderPortal(float timeInPortal, ScaledResolution res)
    {
        if (timeInPortal < 1.0F)
        {
            timeInPortal = timeInPortal * timeInPortal;
            timeInPortal = timeInPortal * timeInPortal;
            timeInPortal = timeInPortal * 0.8F + 0.2F;
        }
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(1.0F, 1.0F, 1.0F, timeInPortal);
        this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        TextureAtlasSprite textureatlassprite = this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(Blocks.PORTAL.getDefaultState());
        float f = textureatlassprite.getMinU();
        float f1 = textureatlassprite.getMinV();
        float f2 = textureatlassprite.getMaxU();
        float f3 = textureatlassprite.getMaxV();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0.0D, res.getScaledHeight(), -90.0D).tex(f, f3).endVertex();
        bufferbuilder.pos(res.getScaledWidth(), res.getScaledHeight(), -90.0D).tex(f2, f3).endVertex();
        bufferbuilder.pos(res.getScaledWidth(), 0.0D, -90.0D).tex(f2, f1).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(f, f1).endVertex();
        tessellator.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}