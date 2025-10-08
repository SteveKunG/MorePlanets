package com.stevekung.moreplanets.utils.client.gui;

import com.stevekung.lib.utils.client.GLConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;

@SideOnly(Side.CLIENT)
public class GuiElementCheckboxMP extends GuiElementCheckbox
{
    private static final ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/gadget.png");
    public Boolean isSelected;
    private final ICheckBoxCallback parentGui;
    private final int textColor;
    private final int texWidth;
    private final int texHeight;
    private final int texX;
    private final int texY;
    private final boolean shiftOnHover;

    public GuiElementCheckboxMP(int id, ICheckBoxCallback parentGui, int x, int y, String text)
    {
        this(id, parentGui, x, y, text, 4210752);
    }

    public GuiElementCheckboxMP(int id, ICheckBoxCallback parentGui, int x, int y, String text, int textColor)
    {
        this(id, parentGui, x, y, 13, 13, 20, 24, text, textColor);
    }

    public GuiElementCheckboxMP(int id, ICheckBoxCallback parentGui, int x, int y, int width, int height, int texX, int texY, String text, int textColor)
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
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.isSelected == null)
        {
            this.isSelected = this.parentGui.getInitiallySelected(this);
        }
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(GuiElementCheckboxMP.texture);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            this.drawTexturedModalRect(this.x, this.y, this.hovered ? this.texX + this.texWidth : this.texX, this.hovered ? this.shiftOnHover ? this.texY + this.texHeight : this.texY : this.texY, this.width, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            mc.fontRenderer.drawString(this.displayString, this.x + this.width + 3, this.y + (this.height - 6) / 2.0f, this.textColor, false);
        }
    }

    @Override
    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldRenderer = tessellator.getBuffer();
        worldRenderer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(x, y + height, this.zLevel).tex(textureX * f, (textureY + this.texHeight) * f1).endVertex();
        worldRenderer.pos(x + width, y + height, this.zLevel).tex((textureX + this.texWidth) * f, (textureY + this.texHeight) * f1).endVertex();
        worldRenderer.pos(x + width, y, this.zLevel).tex((textureX + this.texWidth) * f, textureY * f1).endVertex();
        worldRenderer.pos(x, y, this.zLevel).tex(textureX * f, textureY * f1).endVertex();
        tessellator.draw();
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height)
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