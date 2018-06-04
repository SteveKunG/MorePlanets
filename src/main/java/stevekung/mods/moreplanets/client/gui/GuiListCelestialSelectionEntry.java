package stevekung.mods.moreplanets.client.gui;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.utils.LoggerMP;

@SideOnly(Side.CLIENT)
public class GuiListCelestialSelectionEntry implements GuiListExtended.IGuiListEntry
{
    private final Minecraft mc;
    private final GuiCelestialSelection celestialSelectionGui;
    private final ResourceLocation celestialIcon;
    private final GuiListCelestialSelection gui;
    private final CelestialBody celestial;
    private long lastClickTime;

    public GuiListCelestialSelectionEntry(GuiListCelestialSelection gui, CelestialBody celestial)
    {
        this.mc = Minecraft.getMinecraft();
        this.gui = gui;
        this.celestial = celestial;
        this.celestialSelectionGui = gui.getGui();
        this.celestialIcon = celestial.getBodyIcon();
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
    {
        String name = this.celestial.getLocalizedName();
        String reach = this.celestial.getReachable() ? TextFormatting.DARK_GREEN + "Reachable" : TextFormatting.DARK_RED + "Unreachable";
        String tier = String.valueOf(this.celestial.getTierRequirement());
        this.mc.fontRenderer.drawString(name, x + 35, y + 1, 16777215);
        this.mc.fontRenderer.drawString(reach, x + 35, y + this.mc.fontRenderer.FONT_HEIGHT + 3, 8421504);

        if (this.celestial.getReachable())
        {
            this.mc.fontRenderer.drawString("Tier " + tier, x + 35, y + this.mc.fontRenderer.FONT_HEIGHT + 14, 8421504);
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(this.celestialIcon);
        GlStateManager.enableBlend();
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
        GlStateManager.disableBlend();
    }

    @Override
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
    {
        this.gui.selectCelestial(slotIndex);

        if (relativeX <= 32 && relativeX < 32)
        {
            this.teleport();
            return true;
        }
        else if (Minecraft.getSystemTime() - this.lastClickTime < 250L)
        {
            if (this.isReachable())
            {
                this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            }
            this.teleport();
            return true;
        }
        else
        {
            this.lastClickTime = Minecraft.getSystemTime();
            return false;
        }
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}

    @Override
    public void updatePosition(int slotIndex, int x, int y, float partialTicks) {}

    public void teleport()
    {
        if (!this.isReachable())
        {
            return;
        }

        this.mc.displayGuiScreen(null);

        if (!WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
        {
            String celestialName = this.celestial.getUnlocalizedName();
            LoggerMP.info("Start survival planet at: {}, Dimension: {}", celestialName, WorldUtil.getProviderForNameClient(celestialName).getDimension());
            WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData = true;
            WorldTickEventHandler.survivalPlanetData.survivalPlanetName = celestialName;
            WorldTickEventHandler.survivalPlanetData.setDirty(true);
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_START_SURVIVAL_PLANET, GCCoreUtil.getDimensionID(this.mc.world), new Object[] { this.mc.world.provider.getDimension(), WorldUtil.getProviderForNameServer(celestialName).getDimension() }));
        }
    }

    public boolean isReachable()
    {
        return this.celestial.getReachable();
    }

    public CelestialBody getCelestialBody()
    {
        return this.celestial;
    }
}