package stevekung.mods.moreplanets.util.client.gui;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class GuiContainerMP extends GuiContainer
{
    protected List<GuiElementInfoRegionMP> infoRegions = new ArrayList<GuiElementInfoRegionMP>();
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
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (this.renderInfo)
        {
            this.renderInfo(mouseX, mouseY);
        }
    }

    protected void renderInfo(int mouseX, int mouseY)
    {
        for (int i = 0; i < this.infoRegions.size(); ++i)
        {
            GuiElementInfoRegionMP info = this.infoRegions.get(i);
            info.drawRegion(mouseX, mouseY);
        }
    }

    public int getTooltipOffset(int mouseX, int mouseY)
    {
        for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1)
        {
            Slot slot = this.inventorySlots.inventorySlots.get(i1);

            if (slot.canBeHovered() && this.isPointInRegion(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, mouseX, mouseY))
            {
                ItemStack itemStack = slot.getStack();

                if (itemStack != null)
                {
                    List<String> list = itemStack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);
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