package stevekung.mods.moreplanets.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import stevekung.mods.moreplanets.inventory.ContainerSpaceWarpPad;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPadFull;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementInfoRegionMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

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
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.electricInfoRegion = new GuiElementInfoRegionMP(x + 7, y + 23, 9, 57, null, this.width, this);
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 21, y + 71, 18, 18, Arrays.asList(LangUtils.translate("gui.battery_slot.desc.0"), LangUtils.translate("gui.battery_slot.desc.1")), this.width, this));
        this.infoRegions.add(this.electricInfoRegion);
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 45, this.height / 2 - 16, 72, 20, !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name")));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String dimension = TextFormatting.RED + LangUtils.translate("gui.status.unknown.name");
        String name = TextFormatting.RED + LangUtils.translate("gui.status.unknown.name");
        String dest = TextFormatting.RED + LangUtils.translate("gui.status.unknown.name");
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name");
        this.fontRenderer.drawString(this.tile.getName(), 8, 10, 4210752);
        this.fontRenderer.drawSplitString(LangUtils.translate("gui.message.status.name") + ": " + this.tile.getGUIStatus(), 46, 56, 120, 2536735);
        this.fontRenderer.drawString(LangUtils.translate("container.inventory"), 8, this.ySize - 90 + 2, 4210752);

        if (this.tile.hasWarpCore() && this.tile.containingItems.get(1).hasTagCompound())
        {
            NBTTagCompound compound = this.tile.containingItems.get(1).getTagCompound();
            dimension = TextFormatting.GREEN + String.valueOf(compound.getInteger("DimensionID"));
            name = TextFormatting.GREEN + WorldUtil.getProviderForDimensionClient(compound.getInteger("DimensionID")).getDimensionType().getName();
            dest = TextFormatting.GREEN + "" + compound.getInteger("X") + " " + compound.getInteger("Y") + " " + compound.getInteger("Z");
        }
        this.fontRenderer.drawString(LangUtils.translate("gui.status.dimension.name") + ": " + dimension + " ", 46, 26, 2536735);
        this.fontRenderer.drawString(LangUtils.translate("gui.status.name.name") + ": " + name, 46, 36, 2536735);
        this.fontRenderer.drawString(LangUtils.translate("gui.status.destination.name") + ": " + dest, 46, 46, 2536735);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y + 5, 0, 0, this.xSize, this.ySize);

        int scale = this.tile.getScaledElecticalLevel(54);
        this.drawTexturedModalRect(x + 8, y + 78 - scale, 176, 54 - scale + 10, 7, scale);

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(x + 6, y + 80, 176, 0, 11, 10);
        }
        List<String> electricityDesc = new ArrayList<>(Arrays.asList(LangUtils.translate("gui.energy_storage.desc.0")));
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;
    }
}