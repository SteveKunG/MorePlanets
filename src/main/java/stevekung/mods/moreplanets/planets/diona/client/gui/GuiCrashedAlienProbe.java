package stevekung.mods.moreplanets.planets.diona.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;

@SideOnly(Side.CLIENT)
public class GuiCrashedAlienProbe extends GuiContainerMP
{
    private ResourceLocation texture = new ResourceLocation("textures/gui/container/hopper.png");
    private IInventory playerInventory;
    private IInventory hopperInventory;

    public GuiCrashedAlienProbe(InventoryPlayer playerInv, IInventory hopperInv)
    {
        super(new ContainerHopper(playerInv, hopperInv, Minecraft.getMinecraft().player));
        this.playerInventory = playerInv;
        this.hopperInventory = hopperInv;
        this.allowUserInput = false;
        this.ySize = 133;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.hopperInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(this.texture);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}