package stevekung.mods.moreplanets.client.gui;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import stevekung.mods.moreplanets.inventory.ContainerSpaceWarpPad;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPadFull;
import stevekung.mods.moreplanets.util.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.util.client.gui.GuiElementInfoRegionMP;

public class GuiSpaceWarpPad extends GuiContainerMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/gui/space_warp_pad.png");
    private final TileEntitySpaceWarpPadFull tile;
    private GuiElementInfoRegionMP electricInfoRegion;
    private GuiButton buttonEnable;

    public GuiSpaceWarpPad(InventoryPlayer inv, TileEntitySpaceWarpPadFull tile)
    {
        super(new ContainerSpaceWarpPad(inv, tile));
        this.tile = tile;
        this.ySize = 189;
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
        List<String> batterySlotDesc = new ArrayList<>();
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.0"));
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.1"));
        this.electricInfoRegion = new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 7, (this.height - this.ySize) / 2 + 23, 9, 57, new ArrayList<>(), this.width, this.height, this);
        this.infoRegions.add(new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 21, (this.height - this.ySize) / 2 + 71, 18, 18, batterySlotDesc, this.width, this.height, this));
        this.infoRegions.add(this.electricInfoRegion);
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 45, this.height / 2 - 16, 72, 20, !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name")));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String dimension = TextFormatting.RED + GCCoreUtil.translate("gui.status.unknown.name");
        String name = TextFormatting.RED + GCCoreUtil.translate("gui.status.unknown.name");
        String dest = TextFormatting.RED + GCCoreUtil.translate("gui.status.unknown.name");
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name");
        this.fontRenderer.drawString(this.tile.getName(), 8, 10, 4210752);
        this.fontRenderer.drawSplitString(GCCoreUtil.translate("gui.message.status.name") + ": " + this.tile.getGUIStatus(), 46, 56, 120, 2536735);
        this.fontRenderer.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 90 + 2, 4210752);

        if (this.tile.hasWarpCore() && this.tile.containingItems.get(1).hasTagCompound())
        {
            NBTTagCompound compound = this.tile.containingItems.get(1).getTagCompound();
            dimension = TextFormatting.GREEN + String.valueOf(compound.getInteger("DimensionID"));
            name = TextFormatting.GREEN + String.valueOf(compound.getString("DimensionName"));
            dest = TextFormatting.GREEN + "" + compound.getInteger("X") + " " + compound.getInteger("Y") + " " + compound.getInteger("Z");
        }
        this.fontRenderer.drawString(GCCoreUtil.translate("gui.status.dimension.name") + ": " + dimension + " ", 46, 26, 2536735);
        this.fontRenderer.drawString(GCCoreUtil.translate("gui.status.name.name") + ": " + name, 46, 36, 2536735);
        this.fontRenderer.drawString(GCCoreUtil.translate("gui.status.destination.name") + ": " + dest, 46, 46, 2536735);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(width, height + 5, 0, 0, this.xSize, this.ySize);

        int scale = this.tile.getScaledElecticalLevel(54);
        this.drawTexturedModalRect(width + 8, height + 78 - scale, 176, 54 - scale + 10, 7, scale);

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(width + 6, height + 80, 176, 0, 11, 10);
        }
        List<String> electricityDesc = new ArrayList<>();
        electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;
    }
}