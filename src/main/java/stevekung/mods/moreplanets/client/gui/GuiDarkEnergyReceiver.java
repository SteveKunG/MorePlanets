package stevekung.mods.moreplanets.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox.ICheckBoxCallback;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.inventory.ContainerDarkEnergyReceiver;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementInfoRegionMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

@SideOnly(Side.CLIENT)
public class GuiDarkEnergyReceiver extends GuiContainerMP implements ICheckBoxCallback
{
    private static final ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/dark_energy_receiver.png");
    private final TileEntityDarkEnergyReceiver tile;
    private GuiElementInfoRegionMP electricInfoRegion;
    private GuiButton buttonEnable;
    private GuiElementCheckbox checkboxRender;

    public GuiDarkEnergyReceiver(InventoryPlayer inv, TileEntityDarkEnergyReceiver tile)
    {
        super(new ContainerDarkEnergyReceiver(inv, tile));
        this.tile = tile;
        this.ySize = 180;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.tile.getWorld()), new Object[] { this.tile.getPos(), 0 }));
            break;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 31, y + 26, 18, 18, Arrays.asList(LangUtils.translate("gui.battery_slot.desc.0"), LangUtils.translate("gui.battery_slot.desc.1")), this.width, this));

        if (!this.tile.successful && !this.tile.activated)
        {
            this.infoRegions.add(new GuiElementInfoRegionMP(x + 155, y + 87, 13, 13, Arrays.asList(LangUtils.translate("gui.multiblock_guide.desc")), this.width, this));
        }

        this.electricInfoRegion = new GuiElementInfoRegionMP(x + 107, y + 31, 56, 9, new ArrayList<>(), this.width, this);
        this.infoRegions.add(this.electricInfoRegion);
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 36, this.height / 2 - 19, 72, 20, !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name")));
        this.buttonEnable.enabled = this.tile.disableCooldown == 0 && !this.tile.successful;
        this.checkboxRender = new GuiElementCheckbox(1, this, x + 155, y + 87, "");
        this.checkboxRender.visible = !this.tile.successful && !this.tile.activated;
        this.buttonList.add(this.checkboxRender);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.buttonEnable.enabled = this.tile.disableCooldown == 0 && !this.tile.successful;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name");
        this.fontRenderer.drawString(this.tile.getName(), 8, 10, 4210752);
        this.drawCenteredString(this.fontRenderer, LangUtils.translate("gui.message.status.name") + ": " + this.tile.getGUIStatus(), this.xSize / 2, 50, 4210752);
        this.drawCenteredString(this.fontRenderer, LangUtils.translate("gui.status.machine_status.name") + ": " + this.getStatus(), this.xSize / 2, 60, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("container.inventory"), 8, this.ySize - 90 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiDarkEnergyReceiver.texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y + 5, 0, 0, this.xSize, 181);
        int scale = this.tile.getScaledElecticalLevel(54);
        this.drawTexturedModalRect(x + 107, y + 31, 197, 0, Math.min(scale, 54), 7);

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(x + 93, y + 30, 176, 0, 11, 10);
        }
        if (this.tile.activated && !this.tile.successful)
        {
            this.drawTexturedModalRect(x + 64, y + 16, 176, 10, 16, 16);
        }
        List<String> desc = new ArrayList<>(Arrays.asList(LangUtils.translate("gui.energy_storage.desc.0")));
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), desc);
        this.electricInfoRegion.tooltipStrings = desc;
        this.checkboxRender.isSelected = ClientEventHandler.receiverRenderPos.contains(this.tile.getPos());
    }

    @Override
    public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected)
    {
        if (ClientEventHandler.receiverRenderPos.contains(this.tile.getPos()))
        {
            this.tile.initMultiBlock = true;
            this.tile.multiTileClientLists.clear();
            this.tile.multiBlockClientLists.clear();
            ClientEventHandler.receiverRenderPos.remove(this.tile.getPos());
        }
        else
        {
            ClientEventHandler.receiverRenderPos.add(this.tile.getPos());
        }
    }

    @Override
    public boolean canPlayerEdit(GuiElementCheckbox checkbox, EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean getInitiallySelected(GuiElementCheckbox checkbox)
    {
        return ClientEventHandler.receiverRenderPos.contains(this.tile.getPos());
    }

    @Override
    public void onIntruderInteraction() {}

    @Override
    public void drawCenteredString(FontRenderer fontRenderer, String text, int x, int y, int color)
    {
        fontRenderer.drawString(text, x - fontRenderer.getStringWidth(text) / 2, y, color, false);
    }

    private String getStatus()
    {
        if (this.tile.activated && !this.tile.successful)
        {
            if (this.tile.activatedTick > 0 && this.tile.activatedTick < this.tile.getSuccessfulTick())
            {
                return LangUtils.translate("gui.status.dark_energy_percent0.name", this.tile.activatedTick * 100 / this.tile.getSuccessfulTick()) + LangUtils.translate("gui.status.dark_energy_percent1.name");
            }
        }
        if (this.tile.successful)
        {
            return TextFormatting.DARK_GREEN + LangUtils.translate("gui.status.dark_energy_core_created.name");
        }
        return this.tile.getEnergyStoredGC() > 0.0F ? TextFormatting.DARK_GREEN + LangUtils.translate("gui.status.ready.name") : TextFormatting.DARK_RED + LangUtils.translate("gui.status.dark_energy_offline.name");
    }
}