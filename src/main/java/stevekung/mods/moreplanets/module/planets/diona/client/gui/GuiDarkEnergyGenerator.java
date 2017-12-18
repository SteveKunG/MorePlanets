package stevekung.mods.moreplanets.module.planets.diona.client.gui;

import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.container.GuiContainerGC;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementInfoRegion;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.diona.inventory.ContainerDarkEnergyGenerator;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyGenerator;

public class GuiDarkEnergyGenerator extends GuiContainerGC
{
    private ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/dark_energy_generator.png");
    private TileEntityDarkEnergyGenerator generator;
    private GuiButton buttonEnable;
    private GuiElementInfoRegion electricInfoRegion = new GuiElementInfoRegion((this.width - this.xSize) / 2 + 107, (this.height - this.ySize) / 2 + 101, 56, 9, Lists.newArrayList(), this.width, this.height, this);

    public GuiDarkEnergyGenerator(InventoryPlayer invPlayer, TileEntityDarkEnergyGenerator generator)
    {
        super(new ContainerDarkEnergyGenerator(invPlayer, generator));
        this.generator = generator;
        this.ySize = 201;
        this.xSize = 176;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.mc.theWorld), new Object[] { this.generator.getPos(), 0 }));
            break;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui()
    {
        super.initGui();
        List<String> electricityDesc = Lists.newArrayList();
        electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
        electricityDesc.add(EnumColor.YELLOW + GCCoreUtil.translate("gui.energy_storage.desc.1") + ((int) Math.floor(this.generator.getEnergyStoredGC()) + " / " + (int) Math.floor(this.generator.getMaxEnergyStoredGC())));
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        this.electricInfoRegion.xPosition = (this.width - this.xSize) / 2 + 96;
        this.electricInfoRegion.yPosition = (this.height - this.ySize) / 2 + 24;
        this.electricInfoRegion.parentWidth = this.width;
        this.electricInfoRegion.parentHeight = this.height;
        this.infoRegions.add(this.electricInfoRegion);
        List<String> batterySlotDesc = Lists.newArrayList();
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.0"));
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.1"));
        this.infoRegions.add(new GuiElementInfoRegion((this.width - this.xSize) / 2 + 22, (this.height - this.ySize) / 2 + 20, 18, 18, batterySlotDesc, this.width, this.height, this));
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 36, this.height / 2 - 17, 72, 20, GCCoreUtil.translate("gui.button.enable.name")));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        int offsetY = 35;
        this.buttonEnable.enabled = this.generator.disableCooldown == 0;
        this.buttonEnable.displayString = !this.generator.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name");
        String displayString = this.generator.getName();
        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 7, 4210752);
        displayString = GCCoreUtil.translate("gui.message.status.name") + ": " + this.getStatus();
        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 40 + 23 - 40 + offsetY, 4210752);
        displayString = GCCoreUtil.translate("gui.message.generating.name") + ": " + (this.generator.generateWatts > 0 ? EnergyDisplayHelper.getEnergyDisplayS(this.generator.generateWatts) + "/t" : GCCoreUtil.translate("gui.status.not_generating.name"));
        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 28 + 23 - 40 + offsetY, 4210752);
        displayString = GCCoreUtil.translate("gui.status.dark_energy_fuel.name") + ": " + String.valueOf(this.generator.darkEnergyFuel * 100 / 1000) + "%";
        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 52 + 23 - 40 + offsetY, 4210752);
        this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 94, 4210752);
    }

    private String getStatus()
    {
        if (this.generator.getDisabled(0))
        {
            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.disabled.name");
        }
        if (this.generator.generateWatts > 0)
        {
            return EnumColor.DARK_GREEN + GCCoreUtil.translate("gui.status.collectingenergy.name");
        }
        return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.unknown.name");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(this.texture);
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(width, height, 0, 0, this.xSize, this.ySize);
        List<String> electricityDesc = Lists.newArrayList();
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.generator.getEnergyStoredGC(), this.generator.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;

        if (this.generator.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(width + 83, height + 24, 176, 0, 11, 10);
        }
        if (this.generator.darkEnergyFuel > 0)
        {
            this.drawTexturedModalRect(width + 46, height + 13, 176, 10, 32, 32);
        }
        this.drawTexturedModalRect(width + 97, height + 25, 187, 0, Math.min(this.generator.getScaledElecticalLevel(54), 54), 7);
    }
}
