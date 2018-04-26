package stevekung.mods.moreplanets.client.gui;

import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.inventory.ContainerEnergyStorageCluster;
import stevekung.mods.moreplanets.util.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.util.tileentity.TileEntityEnergyStorageMP;

@SideOnly(Side.CLIENT)
public class GuiDarkEnergyStorage extends GuiContainerMP
{
    private static ResourceLocation texture;
    private final TileEntityEnergyStorageMP tile;

    public GuiDarkEnergyStorage(InventoryPlayer invPlayer, TileEntityEnergyStorageMP tile)
    {
        super(new ContainerEnergyStorageCluster(invPlayer, tile));
        this.tile = tile;
        this.ySize = 171;
        texture = new ResourceLocation("moreplanets:textures/gui/" + tile.containerName + ".png");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.tile.getName(), this.xSize / 2 - this.fontRenderer.getStringWidth(this.tile.getName()) / 2, 6, 4210752);
        float energy = this.tile.getEnergyStoredGC();

        if (energy + 49 > this.tile.getMaxEnergyStoredGC())
        {
            energy = this.tile.getMaxEnergyStoredGC();
        }

        String displayStr = EnergyDisplayHelper.getEnergyDisplayS(energy);
        this.fontRenderer.drawString(displayStr, 122 - this.fontRenderer.getStringWidth(displayStr) / 2, 25, 4210752);
        displayStr = GCCoreUtil.translate("gui.message.of.name") + " " + EnergyDisplayHelper.getEnergyDisplayS(this.tile.getMaxEnergyStoredGC());
        this.fontRenderer.drawString(displayStr, 122 - this.fontRenderer.getStringWidth(displayStr) / 2, 34, 4210752);
        displayStr = GCCoreUtil.translate("gui.max_output.desc") + ": " + EnergyDisplayHelper.getEnergyDisplayS(this.tile.storage.getMaxExtract()) + "/t";
        this.fontRenderer.drawString(displayStr, 114 - this.fontRenderer.getStringWidth(displayStr) / 2, 68, 4210752);
        this.fontRenderer.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        this.mc.renderEngine.bindTexture(texture);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int containerWidth = (this.width - this.xSize) / 2;
        int containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(containerWidth, containerHeight, 0, 0, this.xSize, this.ySize);
        int scale = (int) ((this.tile.getEnergyStoredGC() + 49) / this.tile.getMaxEnergyStoredGC() * 72);
        this.drawTexturedModalRect(containerWidth + 87, containerHeight + 52, 176, 0, scale, 6);
    }
}