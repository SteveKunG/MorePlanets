package stevekung.mods.moreplanets.module.planets.diona.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import stevekung.mods.moreplanets.module.planets.diona.inventory.ContainerDarkEnergyGenerator;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.moreplanets.util.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.util.client.gui.GuiElementInfoRegionMP;

public class GuiDarkEnergyGenerator extends GuiContainerMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/gui/dark_energy_generator.png");
    private final TileEntityDarkEnergyGenerator tile;
    private GuiButton buttonEnable;
    private GuiElementInfoRegionMP electricInfoRegion;

    public GuiDarkEnergyGenerator(InventoryPlayer invPlayer, TileEntityDarkEnergyGenerator tile)
    {
        super(new ContainerDarkEnergyGenerator(invPlayer, tile));
        this.tile = tile;
        this.ySize = 201;
        this.xSize = 176;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.mc.theWorld), new Object[] { this.tile.getPos(), 0 }));
            break;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        List<String> electricityDesc = new ArrayList<>();
        electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
        electricityDesc.add(EnumColor.YELLOW + GCCoreUtil.translate("gui.energy_storage.desc.1") + ((int) Math.floor(this.tile.getEnergyStoredGC()) + " / " + (int) Math.floor(this.tile.getMaxEnergyStoredGC())));
        this.electricInfoRegion = new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 51, (this.height - this.ySize) / 2 + 24, 54, 9, electricityDesc, this.width, this.height, this);
        this.infoRegions.add(this.electricInfoRegion);
        this.infoRegions.add(new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 7, (this.height - this.ySize) / 2 + 84, 18, 18, Arrays.asList(GCCoreUtil.translate("gui.battery_slot.desc.0"), GCCoreUtil.translate("gui.battery_slot.desc.1")), this.width, this.height, this));
        this.infoRegions.add(new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 25, (this.height - this.ySize) / 2 + 84, 18, 18, Arrays.asList(GCCoreUtil.translate("gui.battery_slot.desc.0"), GCCoreUtil.translate("gui.battery_slot.desc.1")), this.width, this.height, this));
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 + 5, this.height / 2 - 18, 72, 20, !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name")));
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int offsetY = 33;
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name");
        String displayString = this.tile.getName();
        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 7, 4210752);
        displayString = GCCoreUtil.translate("gui.message.status.name") + ": " + this.getStatus();
        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 40 + 23 - 40 + offsetY, 4210752);
        displayString = GCCoreUtil.translate("gui.message.generating.name") + ": " + (this.tile.generateWatts > 0 ? EnergyDisplayHelper.getEnergyDisplayS(this.tile.generateWatts) + "/t" : GCCoreUtil.translate("gui.status.not_generating.name"));
        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 28 + 23 - 40 + offsetY, 4210752);
        displayString = GCCoreUtil.translate("gui.status.dark_energy_fuel.name") + ": " + String.valueOf(this.tile.darkEnergyFuel * 100 / 1000) + "%";

        if (this.tile.darkEnergyFuel == 0)
        {
            displayString = GCCoreUtil.translate("gui.status.dark_energy_fuel.name") + ": " + TextFormatting.GOLD + GCCoreUtil.translate("gui.status.empty.name");
        }

        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 52 + 23 - 40 + offsetY, 4210752);
        this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(width, height, 0, 0, this.xSize, this.ySize);
        List<String> electricityDesc = new ArrayList<>();
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(width + 37, height + 24, 176, 0, 11, 10);
        }
        if (this.tile.darkEnergyFuel > 0)
        {
            int i = this.getFuelBurnLeftScaled(19);
            this.drawTexturedModalRect(width + 119, height + 37 - i, 176, 19 - i + 10, 32, i);
        }
        this.drawTexturedModalRect(width + 51, height + 25, 187, 0, Math.min(this.tile.getScaledElecticalLevel(54), 54), 7);
    }

    private String getStatus()
    {
        if (this.tile.getDisabled(0))
        {
            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.disabled.name");
        }
        if (this.tile.generateWatts > 0)
        {
            return EnumColor.DARK_GREEN + GCCoreUtil.translate("gui.status.collectingenergy.name");
        }
        return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.unknown.name");
    }

    private int getFuelBurnLeftScaled(int pixels)
    {
        int i = this.tile.prevDarkEnergyFuel;

        if (i == 0)
        {
            i = 1000;
        }
        return this.tile.darkEnergyFuel * pixels / i;
    }
}