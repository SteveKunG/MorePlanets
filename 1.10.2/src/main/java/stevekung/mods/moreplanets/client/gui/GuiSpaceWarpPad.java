package stevekung.mods.moreplanets.client.gui;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.container.GuiContainerGC;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementInfoRegion;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.inventory.ContainerSpaceWarpPad;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPadFull;

public class GuiSpaceWarpPad extends GuiContainerGC
{
    private static ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/space_warp_pad.png");
    private TileEntitySpaceWarpPadFull warpPad;
    private GuiElementInfoRegion electricInfoRegion = new GuiElementInfoRegion((this.width - this.xSize) / 2 + 112, (this.height - this.ySize) / 2 + 37, 56, 9, new ArrayList<>(), this.width, this.height, this);
    private GuiButton buttonEnable;

    public GuiSpaceWarpPad(InventoryPlayer inv, TileEntitySpaceWarpPadFull tile)
    {
        super(new ContainerSpaceWarpPad(inv, tile));
        this.warpPad = tile;
        this.ySize = 180;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.warpPad.getWorld()), new Object[] { this.warpPad.getPos(), 0 }));
            break;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        List<String> batterySlotDesc = new ArrayList<>();
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.0"));
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.1"));
        this.infoRegions.add(new GuiElementInfoRegion((this.width - this.xSize) / 2 + 31, (this.height - this.ySize) / 2 + 26, 18, 18, batterySlotDesc, this.width, this.height, this));
        this.electricInfoRegion.xPosition = (this.width - this.xSize) / 2 + 106;
        this.electricInfoRegion.yPosition = (this.height - this.ySize) / 2 + 30;
        this.electricInfoRegion.parentWidth = this.width;
        this.electricInfoRegion.parentHeight = this.height;
        this.infoRegions.add(this.electricInfoRegion);
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 36, this.height / 2 - 19, 72, 20, !this.warpPad.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name")));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.buttonEnable.enabled = this.warpPad.disableCooldown == 0;
        this.buttonEnable.displayString = !this.warpPad.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name");
        this.fontRendererObj.drawString(this.warpPad.getName(), 8, 10, 4210752);
        GCCoreUtil.drawStringCentered(GCCoreUtil.translate("gui.message.status.name") + ": " + this.warpPad.getGUIStatus(), this.xSize / 2, 55, 4210752, this.fontRendererObj);
        this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 90 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiSpaceWarpPad.texture);
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(width, height + 5, 0, 0, this.xSize, 181);

        if (this.warpPad != null)
        {
            int scale = this.warpPad.getScaledElecticalLevel(54);
            this.drawTexturedModalRect(width + 107, height + 31, 197, 0, Math.min(scale, 54), 7);

            if (this.warpPad.getEnergyStoredGC() > 0)
            {
                this.drawTexturedModalRect(width + 94, height + 30, 176, 0, 11, 10);
            }
            if (this.warpPad.getHasReceiveData())
            {
                this.drawTexturedModalRect(width + 57, height + 19, 176, 10, 32, 32);
            }
            List<String> electricityDesc = new ArrayList<>();
            electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
            EnergyDisplayHelper.getEnergyDisplayTooltip(this.warpPad.getEnergyStoredGC(), this.warpPad.getMaxEnergyStoredGC(), electricityDesc);
            this.electricInfoRegion.tooltipStrings = electricityDesc;
        }
    }
}