package stevekung.mods.moreplanets.module.planets.nibiru.client.gui;

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
import stevekung.mods.moreplanets.module.planets.nibiru.inventory.ContainerNuclearWasteGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;

public class GuiNuclearWasteGenerator extends GuiContainerGC
{
    private ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/nuclear_waste_generator.png");
    private TileEntityNuclearWasteGenerator generator;
    private GuiButton buttonEnable;
    private GuiElementInfoRegion electricInfoRegion = new GuiElementInfoRegion((this.width - this.xSize) / 2 + 107, (this.height - this.ySize) / 2 + 101, 56, 9, Lists.newArrayList(), this.width, this.height, this);

    public GuiNuclearWasteGenerator(InventoryPlayer invPlayer, TileEntityNuclearWasteGenerator generator)
    {
        super(new ContainerNuclearWasteGenerator(invPlayer, generator));
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
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.mc.world), new Object[] { this.generator.getPos(), 0 }));
            break;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        List<String> electricityDesc = Lists.newArrayList();
        electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
        electricityDesc.add(EnumColor.YELLOW + GCCoreUtil.translate("gui.energy_storage.desc.1") + ((int) Math.floor(this.generator.getEnergyStoredGC()) + " / " + (int) Math.floor(this.generator.getMaxEnergyStoredGC())));
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        this.electricInfoRegion.xPosition = (this.width - this.xSize) / 2 + 46;
        this.electricInfoRegion.yPosition = (this.height - this.ySize) / 2 + 24;
        this.electricInfoRegion.parentWidth = this.width;
        this.electricInfoRegion.parentHeight = this.height;
        this.infoRegions.add(this.electricInfoRegion);
        List<String> batterySlotDesc = Lists.newArrayList();
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.0"));
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.1"));
        this.infoRegions.add(new GuiElementInfoRegion((this.width - this.xSize) / 2 + 121, (this.height - this.ySize) / 2 + 19, 18, 18, batterySlotDesc, this.width, this.height, this));
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 36, this.height / 2 - 19, 72, 20, !this.generator.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name")));
        this.buttonEnable.enabled = this.generator.disableCooldown == 0;
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
        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 45 + 23 - 46 + offsetY, 4210752);
        displayString = GCCoreUtil.translate("gui.message.generating.name") + ": " + (this.generator.generateTick > 0 ? EnergyDisplayHelper.getEnergyDisplayS(this.generator.generateTick) + "/t" : GCCoreUtil.translate("gui.status.not_generating.name"));
        this.fontRendererObj.drawString(displayString, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayString) / 2, 34 + 23 - 46 + offsetY, 4210752);
        this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 94, 4210752);
    }

    private String getStatus()
    {
        if (this.generator.getDisabled(0))
        {
            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.disabled.name");
        }
        if (this.generator.missingTank && this.generator.missingWaste)
        {
            return EnumColor.RED + GCCoreUtil.translate("gui.status.waste_and_tank_depleted.name");
        }
        if (this.generator.missingTank)
        {
            return EnumColor.RED + GCCoreUtil.translate("gui.status.waste_tank_depleted.name");
        }
        if (this.generator.missingWaste)
        {
            return EnumColor.RED + GCCoreUtil.translate("gui.status.waste_depleted.name");
        }
        if (this.generator.generateTick > 0)
        {
            return EnumColor.DARK_GREEN + GCCoreUtil.translate("gui.status.collectingenergy.name");
        }
        return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.no_waste_sources.name");
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
            this.drawTexturedModalRect(width + 33, height + 24, 176, 0, 11, 10);
        }
        this.drawTexturedModalRect(width + 47, height + 25, 187, 0, Math.min(this.generator.getScaledElecticalLevel(54), 54), 7);
    }
}