package stevekung.mods.moreplanets.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.inventory.ContainerRocketCrusher;
import stevekung.mods.moreplanets.tileentity.TileEntityRocketCrusher;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementInfoRegionMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

@SideOnly(Side.CLIENT)
public class GuiRocketCrusher extends GuiContainerMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/gui/rocket_crusher.png");
    private final TileEntityRocketCrusher tile;
    private GuiElementInfoRegionMP electricInfoRegion;
    private GuiElementInfoRegionMP processInfoRegion;
    private GuiElementInfoRegionMP batteryInfoRegion;
    private GuiElementInfoRegionMP machineUpgradeInfoRegion;

    public GuiRocketCrusher(InventoryPlayer inv, TileEntityRocketCrusher tile)
    {
        super(new ContainerRocketCrusher(inv, tile));
        this.tile = tile;
        this.ySize = 195;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.electricInfoRegion = new GuiElementInfoRegionMP(x + 6, y + 31, 9, 57, null, this.width, this);
        this.batteryInfoRegion = new GuiElementInfoRegionMP(x + 18, y + 79, 18, 18, Arrays.asList(LangUtils.translate("gui.battery_slot.desc.0"), LangUtils.translate("gui.battery_slot.desc.1")), this.width, this);
        this.machineUpgradeInfoRegion = new GuiElementInfoRegionMP(x + 37, y + 79, 18, 18, Arrays.asList(LangUtils.translate("gui.machine_upgrade_slot.desc.0"), LangUtils.translate("gui.machine_upgrade_slot.desc.1")), this.width, this);
        this.processInfoRegion = new GuiElementInfoRegionMP(x + 77, y + 30, 52, 25, null, this.width, this);
        this.infoRegions.add(this.electricInfoRegion);
        this.infoRegions.add(this.batteryInfoRegion);
        this.infoRegions.add(this.machineUpgradeInfoRegion);
        this.infoRegions.add(this.processInfoRegion);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.tile.getName(), 8, 6, 4210752);
        String text;

        if (this.tile.processTicks > 0)
        {
            text = TextFormatting.GREEN + LangUtils.translate("gui.status.running.name");
        }
        else
        {
            text = TextFormatting.GOLD + LangUtils.translate("gui.status.idle.name");
        }

        String status = LangUtils.translate("gui.message.status.name") + ": " + text;

        if (this.tile.processTicks > 0)
        {
            int scale = (int) ((double) this.tile.processTicks / (double) this.tile.processTimeRequired * 100);
            this.fontRenderer.drawString(LangUtils.translate("gui.electric_compressor.desc.0") + ": " + scale + "%", 80, 62, 4210752);
        }
        this.fontRenderer.drawString(status, 76, 84, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("container.inventory"), 8, this.ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        int scale;
        this.mc.renderEngine.bindTexture(TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        List<String> electricityDesc = new ArrayList<>(Arrays.asList(LangUtils.translate("gui.energy_storage.desc.0")));
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        int speed = !this.tile.containingItems.get(2).isEmpty() ? 1 + this.tile.containingItems.get(2).getCount() : 1;

        if (this.tile.getEnergyStoredGC() > 0)
        {
            scale = this.tile.getScaledElecticalLevel(54);
            this.drawTexturedModalRect(x + 7, y + 86 - scale, 176, 54 - scale + 40, 7, scale);
            this.drawTexturedModalRect(x + 4, y + 88, 176, 30, 11, 10);
        }
        if (this.tile.processTicks > 0)
        {
            scale = (int) ((double) this.tile.processTicks / (double) this.tile.processTimeRequired * 54);
            this.drawTexturedModalRect(x + 80, y + 39, 176, 13, scale, 17);
        }
        if (this.tile.processTicks % 45 - speed >= 15 / speed)
        {
            this.drawTexturedModalRect(x + 83, y + 30, 176, 0, 15, 13);
        }
        if (this.tile.processTicks % 45 - speed >= 25 / speed)
        {
            this.drawTexturedModalRect(x + 96, y + 30, 176, 0, 15, 13);
        }
        if (this.tile.processTicks % 45 - speed >= 35 / speed)
        {
            this.drawTexturedModalRect(x + 109, y + 30, 176, 0, 15, 13);
        }
    }
}