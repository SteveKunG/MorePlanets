package stevekung.mods.moreplanets.module.planets.diona.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import stevekung.mods.moreplanets.module.planets.diona.inventory.ContainerDarkEnergyGenerator;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementInfoRegionMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

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
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.mc.world), new Object[] { this.tile.getPos(), 0 }));
            break;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        List<String> electricityDesc = new ArrayList<>();
        electricityDesc.add(LangUtils.translate("gui.energy_storage.desc.0"));
        electricityDesc.add(TextFormatting.YELLOW + LangUtils.translate("gui.energy_storage.desc.1") + ((int) Math.floor(this.tile.getEnergyStoredGC()) + " / " + (int) Math.floor(this.tile.getMaxEnergyStoredGC())));
        this.electricInfoRegion = new GuiElementInfoRegionMP(x + 51, y + 24, 54, 9, electricityDesc, this.width, this);
        this.infoRegions.add(this.electricInfoRegion);
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 7, y + 84, 18, 18, Arrays.asList(LangUtils.translate("gui.battery_slot.desc.0"), LangUtils.translate("gui.battery_slot.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 25, y + 84, 18, 18, Arrays.asList(LangUtils.translate("gui.battery_slot.desc.0"), LangUtils.translate("gui.battery_slot.desc.1")), this.width, this));
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 + 5, this.height / 2 - 18, 72, 20, !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name")));
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int offsetY = 33;
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name");
        String displayString = this.tile.getName();
        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 7, 4210752);
        displayString = LangUtils.translate("gui.message.status.name") + ": " + this.getStatus();
        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 40 + 23 - 40 + offsetY, 4210752);
        displayString = LangUtils.translate("gui.message.generating.name") + ": " + (this.tile.generateWatts > 0 ? EnergyDisplayHelper.getEnergyDisplayS(this.tile.generateWatts) + "/t" : LangUtils.translate("gui.status.not_generating.name"));
        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 28 + 23 - 40 + offsetY, 4210752);
        displayString = LangUtils.translate("gui.status.dark_energy_fuel.name") + ": " + String.valueOf(this.tile.darkEnergyFuel * 100 / 1000) + "%";

        if (this.tile.darkEnergyFuel == 0)
        {
            displayString = LangUtils.translate("gui.status.dark_energy_fuel.name") + ": " + TextFormatting.GOLD + LangUtils.translate("gui.status.empty.name");
        }

        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 52 + 23 - 40 + offsetY, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("container.inventory"), 8, this.ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        List<String> electricityDesc = new ArrayList<>(Arrays.asList(LangUtils.translate("gui.energy_storage.desc.0")));
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(x + 37, y + 24, 176, 0, 11, 10);
        }
        if (this.tile.darkEnergyFuel > 0)
        {
            int i = this.getFuelBurnLeftScaled(19);
            this.drawTexturedModalRect(x + 119, y + 37 - i, 176, 19 - i + 10, 32, i);
        }
        this.drawTexturedModalRect(x + 51, y + 25, 187, 0, Math.min(this.tile.getScaledElecticalLevel(54), 54), 7);
    }

    private String getStatus()
    {
        if (this.tile.getDisabled(0))
        {
            return TextFormatting.GOLD + LangUtils.translate("gui.status.disabled.name");
        }
        if (this.tile.generateWatts > 0)
        {
            return TextFormatting.DARK_GREEN + LangUtils.translate("gui.status.collectingenergy.name");
        }
        return TextFormatting.GOLD + LangUtils.translate("gui.status.unknown.name");
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