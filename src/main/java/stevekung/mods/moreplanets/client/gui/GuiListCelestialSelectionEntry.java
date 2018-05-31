package stevekung.mods.moreplanets.client.gui;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        String reach = this.celestial.getReachable() ? "Reachable" : "Unreachable";
        this.mc.fontRenderer.drawString(name, x + 32 + 3, y + 1, 16777215);
        this.mc.fontRenderer.drawString(reach, x + 32 + 3, y + this.mc.fontRenderer.FONT_HEIGHT + 3, 8421504);
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
            this.teleportToPlanet();
            return true;
        }
        else if (Minecraft.getSystemTime() - this.lastClickTime < 250L)
        {
            this.teleportToPlanet();
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

    public void teleportToPlanet()
    {
        if (!this.isReachable())
        {
            return;
        }
        this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(PacketSimple.EnumSimplePacket.S_TELEPORT_ENTITY, GCCoreUtil.getDimensionID(this.mc.world), new Object[] { WorldUtil.getDimensionName(WorldUtil.getProviderForDimensionClient(this.celestial.getDimensionID())) }));
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