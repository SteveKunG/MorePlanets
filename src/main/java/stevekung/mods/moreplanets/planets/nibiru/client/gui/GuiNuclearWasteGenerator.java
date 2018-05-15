package stevekung.mods.moreplanets.planets.nibiru.client.gui;

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
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.planets.nibiru.inventory.ContainerNuclearWasteGenerator;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementInfoRegionMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

@SideOnly(Side.CLIENT)
public class GuiNuclearWasteGenerator extends GuiContainerMP implements ICheckBoxCallback
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/gui/nuclear_waste_generator.png");
    private final TileEntityNuclearWasteGenerator tile;
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
        electricityDesc.add(LangUtils.translate("gui.energy_storage.desc.0"));
        electricityDesc.add(TextFormatting.YELLOW + LangUtils.translate("gui.energy_storage.desc.1") + ((int) Math.floor(this.tile.getEnergyStoredGC()) + " / " + (int) Math.floor(this.tile.getMaxEnergyStoredGC())));
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.electricInfoRegion = new GuiElementInfoRegionMP(x + 46, y + 24, 56, 9, new ArrayList<>(), this.width, this);
        this.infoRegions.add(this.electricInfoRegion);
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 121, y + 19, 18, 18, Arrays.asList(LangUtils.translate("gui.battery_slot.desc.0"), LangUtils.translate("gui.battery_slot.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 155, y + 87, 13, 13, Arrays.asList(LangUtils.translate("gui.multiblock_guide.desc")), this.width, this));
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 36, this.height / 2 - 19, 72, 20, !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name")));
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        this.checkboxRender = new GuiElementCheckbox(1, this, x + 155, y + 87, "");
        this.buttonList.add(this.checkboxRender);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int offsetY = 35;
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name");
        String displayString = this.tile.getName();
        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 7, 4210752);
        displayString = LangUtils.translate("gui.message.status.name") + ": " + this.tile.getStatus();
        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 45 + 23 - 46 + offsetY, 4210752);
        displayString = LangUtils.translate("gui.message.generating.name") + ": " + (this.tile.generateTick > 0 ? EnergyDisplayHelper.getEnergyDisplayS(this.tile.generateTick) + "/t" : LangUtils.translate("gui.status.not_generating.name"));
        this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 34 + 23 - 46 + offsetY, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("container.inventory"), 8, this.ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiNuclearWasteGenerator.TEXTURE);
        List<String> electricityDesc = new ArrayList<>();
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(x + 33, y + 24, 176, 0, 11, 10);
        }
        this.drawTexturedModalRect(x + 47, y + 25, 187, 0, Math.min(this.tile.getScaledElecticalLevel(54), 54), 7);
        this.checkboxRender.isSelected = ClientEventHandler.wasteRenderPos.contains(this.tile.getPos());
    }

    @Override
    public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected)
    {
        if (ClientEventHandler.wasteRenderPos.contains(this.tile.getPos()))
        {
            this.tile.initMultiBlock = true;
            this.tile.multiTileClientLists.clear();
            this.tile.multiBlockClientLists.clear();
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