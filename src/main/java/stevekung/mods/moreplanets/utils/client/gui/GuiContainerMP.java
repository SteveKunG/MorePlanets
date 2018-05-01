package stevekung.mods.moreplanets.utils.client.gui;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class GuiContainerMP extends GuiContainer
{
    protected List<GuiElementInfoRegionMP> infoRegions = new ArrayList<>();
    protected boolean renderInfo = true;

    public GuiContainerMP(Container container)
    {
        super(container);
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height)
    {
        this.infoRegions.clear();
        super.setWorldAndResolution(mc, width, height);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        if (this.renderInfo)
        {
            this.renderInfo(mouseX, mouseY);
        }
    }

    protected void renderInfo(int mouseX, int mouseY)
    {
        this.infoRegions.forEach(info ->
        {
            info.drawRegion(mouseX, mouseY);
        });
    }

    public int getTooltipOffset(int mouseX, int mouseY)
    {
        for (Slot slot : this.inventorySlots.inventorySlots)
        {
            if (slot.isEnabled() && this.isPointInRegion(slot.xPos, slot.yPos, 16, 16, mouseX, mouseY))
            {
                ItemStack itemStack = slot.getStack();

                if (!itemStack.isEmpty())
                {
                    List<String> list = itemStack.getTooltip(this.mc.player, this.mc.gameSettings.advancedItemTooltips ? TooltipFlags.ADVANCED : TooltipFlags.NORMAL);
                    int size = list.size();

                    if (CompatibilityManager.isWailaLoaded())
                    {
                        size++;
                    }
                    return size * 10 + 10;
                }
            }
        }
        return 0;
    }
}