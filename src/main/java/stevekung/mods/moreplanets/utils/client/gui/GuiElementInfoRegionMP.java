package stevekung.mods.moreplanets.utils.client.gui;

import java.util.Iterator;
import java.util.List;

import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.utils.RenderUtils;

@SideOnly(Side.CLIENT)
public class GuiElementInfoRegionMP extends Gui
{
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    private boolean enabled;
    private boolean drawRegion;
    private boolean withinRegion;
    public List<String> tooltipStrings;
    private int parentWidth;
    private GuiContainerMP parentGui;

    public GuiElementInfoRegionMP(int xPos, int yPos, int width, int height, List<String> tooltipStrings, int parentWidth, GuiContainerMP parentGui)
    {
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.width = width;
        this.height = height;
        this.tooltipStrings = tooltipStrings;
        this.parentWidth = parentWidth;
        this.parentGui = parentGui;
    }

    public void drawRegion(int mouseX, int mouseZ)
    {
        GlStateManager.disableRescaleNormal();
        RenderUtils.disableLighting();
        GlStateManager.disableDepth();

        this.withinRegion = mouseX >= this.xPosition && mouseZ >= this.yPosition && mouseX < this.xPosition + this.width && mouseZ < this.yPosition + this.height;

        if (this.drawRegion)
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            int k = this.getHoverState(this.withinRegion);
            Gui.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, ColorUtil.to32BitColor(100 * k, 255, 0, 0));
        }

        if (this.tooltipStrings != null && !this.tooltipStrings.isEmpty() && this.withinRegion)
        {
            int k = 0;
            Iterator<String> iterator = this.tooltipStrings.iterator();

            while (iterator.hasNext())
            {
                String s = iterator.next();
                int l = Minecraft.getMinecraft().fontRenderer.getStringWidth(s);

                if (l > k)
                {
                    k = l;
                }
            }

            int i1 = mouseX + 12;
            int j1 = mouseZ - 12;
            int k1 = 8;

            if (this.tooltipStrings.size() > 1)
            {
                k1 += (this.tooltipStrings.size() - 1) * 10;
            }
            if (i1 + k > this.parentWidth)
            {
                i1 -= 28 + k;
            }
            if (this.parentGui.getTooltipOffset(mouseX, mouseZ) > 0)
            {
                j1 -= k1 + 9;
            }

            this.zLevel = 300.0F;
            int l1 = -267386864;
            this.drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
            this.drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
            this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
            this.drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
            this.drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
            int i2 = 1347420415;
            int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
            this.drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
            this.drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
            this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
            this.drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

            for (int k2 = 0; k2 < this.tooltipStrings.size(); ++k2)
            {
                String s1 = this.tooltipStrings.get(k2);
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s1, i1, j1, -1);
                j1 += 10;
            }
            this.zLevel = 0.0F;
        }
        GlStateManager.enableDepth();
        RenderUtils.enableLighting();
        GlStateManager.enableRescaleNormal();
    }

    private int getHoverState(boolean withinRegion)
    {
        byte state = 1;

        if (!this.enabled)
        {
            state = 0;
        }
        else if (withinRegion)
        {
            state = 2;
        }
        return state;
    }
}