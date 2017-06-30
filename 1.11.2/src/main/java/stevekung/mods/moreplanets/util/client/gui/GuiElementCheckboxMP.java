package stevekung.mods.moreplanets.util.client.gui;

import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiElementCheckboxMP extends GuiElementCheckbox
{
    protected static final ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/gadget.png");
    public Boolean isSelected;
    private ICheckBoxCallback parentGui;
    private int textColor;
    private int texWidth;
    private int texHeight;
    private int texX;
    private int texY;
    private boolean shiftOnHover;

    public GuiElementCheckboxMP(int id, ICheckBoxCallback parentGui, int x, int y, String text)
    {
        this(id, parentGui, x, y, text, 4210752);
    }

    public GuiElementCheckboxMP(int id, ICheckBoxCallback parentGui, int x, int y, String text, int textColor)
    {
        this(id, parentGui, x, y, 13, 13, 20, 24, text, textColor);
    }

    private GuiElementCheckboxMP(int id, ICheckBoxCallback parentGui, int x, int y, int width, int height, int texX, int texY, String text, int textColor)
    {
        this(id, parentGui, x, y, width, height, width, height, texX, texY, text, textColor, true);
    }

    public GuiElementCheckboxMP(int id, ICheckBoxCallback parentGui, int x, int y, int width, int height, int texWidth, int texHeight, int texX, int texY, String text, int textColor, boolean shiftOnHover)
    {
        super(id, parentGui, x, y, width, height, texWidth, texHeight, texX, texY, text, textColor, shiftOnHover);
        this.parentGui = parentGui;
        this.textColor = textColor;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.shiftOnHover = shiftOnHover;
        this.texX = texX;
        this.texY = texY;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.isSelected == null)
        {
            this.isSelected = this.parentGui.getInitiallySelected(this);
        }
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(GuiElementCheckboxMP.texture);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.isSelected ? this.texX + this.texWidth : this.texX, this.hovered ? this.shiftOnHover ? this.texY + this.texHeight : this.texY : this.texY, this.width, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            mc.fontRendererObj.drawString(this.displayString, this.xPosition + this.width + 3, this.yPosition + (this.height - 6) / 2, this.textColor, false);
        }
    }

    @Override
    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldRenderer = tessellator.getBuffer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(x + 0, y + height, this.zLevel).tex((textureX + 0) * f, (textureY + this.texHeight) * f1).endVertex();
        worldRenderer.pos(x + width, y + height, this.zLevel).tex((textureX + this.texWidth) * f, (textureY + this.texHeight) * f1).endVertex();
        worldRenderer.pos(x + width, y + 0, this.zLevel).tex((textureX + this.texWidth) * f, (textureY + 0) * f1).endVertex();
        worldRenderer.pos(x + 0, y + 0, this.zLevel).tex((textureX + 0) * f, (textureY + 0) * f1).endVertex();
        tessellator.draw();
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height)
        {
            if (this.parentGui.canPlayerEdit(this, mc.player))
            {
                this.isSelected = !this.isSelected;
                this.parentGui.onSelectionChanged(this, this.isSelected);
                return true;
            }
            else
            {
                this.parentGui.onIntruderInteraction();
            }
        }
        return false;
    }
}