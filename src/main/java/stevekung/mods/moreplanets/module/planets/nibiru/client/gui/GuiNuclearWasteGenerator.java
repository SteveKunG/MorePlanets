package stevekung.mods.moreplanets.module.planets.nibiru.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox.ICheckBoxCallback;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.module.planets.nibiru.inventory.ContainerNuclearWasteGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;
import stevekung.mods.moreplanets.util.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.util.client.gui.GuiElementInfoRegionMP;

public class GuiNuclearWasteGenerator extends GuiContainerMP implements ICheckBoxCallback
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/gui/nuclear_waste_generator.png");
    private TileEntityNuclearWasteGenerator tile;
    private GuiButton buttonEnable;
    private GuiElementInfoRegionMP electricInfoRegion;
    private GuiElementCheckbox checkboxRender;

    public GuiNuclearWasteGenerator(InventoryPlayer invPlayer, TileEntityNuclearWasteGenerator tile)
    {
        super(new ContainerNuclearWasteGenerator(invPlayer, tile));
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
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.mc.world), new Object[] { this.tile.getPos(), 0 }));
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
        this.electricInfoRegion = new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 46, (this.height - this.ySize) / 2 + 24, 56, 9, new ArrayList<>(), this.width, this.height, this);
        this.infoRegions.add(this.electricInfoRegion);
        List<String> batterySlotDesc = new ArrayList<>();
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.0"));
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.1"));
        this.infoRegions.add(new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 121, (this.height - this.ySize) / 2 + 19, 18, 18, batterySlotDesc, this.width, this.height, this));
        this.infoRegions.add(new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 155, (this.height - this.ySize) / 2 + 87, 13, 13, Arrays.asList(GCCoreUtil.translate("gui.multiblock_guide.desc")), this.width, this.height, this));
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 36, this.height / 2 - 19, 72, 20, !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name")));
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        this.checkboxRender = new GuiElementCheckbox(1, this, width + 155, height + 87, "");
        this.buttonList.add(this.checkboxRender);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int offsetY = 35;
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name");
        String displayString = this.tile.getName();
        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 7, 4210752);
        displayString = GCCoreUtil.translate("gui.message.status.name") + ": " + this.tile.getStatus();
        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 45 + 23 - 46 + offsetY, 4210752);
        displayString = GCCoreUtil.translate("gui.message.generating.name") + ": " + (this.tile.generateTick > 0 ? EnergyDisplayHelper.getEnergyDisplayS(this.tile.generateTick) + "/t" : GCCoreUtil.translate("gui.status.not_generating.name"));
        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 34 + 23 - 46 + offsetY, 4210752);
        this.fontRenderer.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiNuclearWasteGenerator.TEXTURE);
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(width, height, 0, 0, this.xSize, this.ySize);

        List<String> electricityDesc = new ArrayList<>();
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(width + 33, height + 24, 176, 0, 11, 10);
        }
        this.drawTexturedModalRect(width + 47, height + 25, 187, 0, Math.min(this.tile.getScaledElecticalLevel(54), 54), 7);
        this.checkboxRender.isSelected = ClientEventHandler.wasteRenderPos.contains(this.tile.getPos());
    }

    @Override
    public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected)
    {
        if (ClientEventHandler.wasteRenderPos.contains(this.tile.getPos()))
        {
            ClientEventHandler.wasteRenderPos.remove(this.tile.getPos());
        }
        else
        {
            ClientEventHandler.wasteRenderPos.add(this.tile.getPos());
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
        return ClientEventHandler.wasteRenderPos.contains(this.tile.getPos());
    }

    @Override
    public void onIntruderInteraction() {}
}