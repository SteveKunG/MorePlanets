package stevekung.mods.moreplanets.client.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

@SideOnly(Side.CLIENT)
public class GuiChangeLogSlot extends GuiSlot
{
    private List<String> stringList;
    private GuiFullChangeLog parent;
    private boolean textureType;
    private static final ResourceLocation POLISHED_TIN = new ResourceLocation("moreplanets:textures/blocks/polished_tin_decoration_block.png");
    private static final ResourceLocation POLISHED_ALUMINUM = new ResourceLocation("moreplanets:textures/blocks/polished_aluminum_decoration_block.png");
    private static final ResourceLocation TRANSPARENT = new ResourceLocation("moreplanets:textures/gui/change_log_overlay.png");

    public GuiChangeLogSlot(Minecraft mc, GuiFullChangeLog parent, List<String> stringList, int width, int height, boolean texture)
    {
        super(mc, width, height, 32, height - 64, 13);
        this.stringList = stringList;
        this.parent = parent;
        this.textureType = texture;
        this.setShowSelectionBox(false);
    }

    @Override
    protected int getSize()
    {
        return this.stringList.size();
    }

    @Override
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {}

    @Override
    protected boolean isSelected(int slotIndex)
    {
        return false;
    }

    @Override
    protected int getContentHeight()
    {
        return 5 + this.getSize() * 13;
    }

    @Override
    protected void drawBackground() {}

    @Override
    protected void drawSlot(int entryID, int insideLeft, int yPos, int insideSlotHeight, int mouseXIn, int mouseYIn)
    {
        String test = this.stringList.get(entryID);
        this.mc.fontRendererObj.drawStringWithShadow(test, insideLeft - 20, yPos + 2, 16777215);
    }

    @Override
    protected void overlayBackground(int startY, int endY, int startAlpha, int endAlpha)
    {
        this.parent.drawCenteredString(this.mc.fontRendererObj, "More Planets " + MorePlanetsCore.VERSION + " Change Log", this.width / 2, 16, 16777215);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        this.mc.getTextureManager().bindTexture(this.textureType ? GuiChangeLogSlot.POLISHED_TIN : GuiChangeLogSlot.POLISHED_ALUMINUM);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        float size = 32.0F;
        int color = 150;
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        vertexbuffer.pos(this.left, endY, 0.0D).tex(0.0D, endY / size).color(color, color, color, endAlpha).endVertex();
        vertexbuffer.pos(this.left + this.width, endY, 0.0D).tex(this.width / size, endY / size).color(color, color, color, endAlpha).endVertex();
        vertexbuffer.pos(this.left + this.width, startY, 0.0D).tex(this.width / size, startY / size).color(color, color, color, startAlpha).endVertex();
        vertexbuffer.pos(this.left, startY, 0.0D).tex(0.0D, startY / size).color(color, color, color, startAlpha).endVertex();
        tessellator.draw();
    }

    @Override
    protected void drawContainerBackground(Tessellator tessellator)
    {
        VertexBuffer buffer = tessellator.getBuffer();
        this.mc.getTextureManager().bindTexture(GuiChangeLogSlot.TRANSPARENT);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        float size = 32.0F;
        int color = 255;
        int alpha = 75;
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(this.left, this.bottom, 0.0D).tex(this.left / size, (this.bottom + (int)this.amountScrolled) / size).color(color, color, color, alpha).endVertex();
        buffer.pos(this.right, this.bottom, 0.0D).tex(this.right / size, (this.bottom + (int)this.amountScrolled) / size).color(color, color, color, alpha).endVertex();
        buffer.pos(this.right, this.top, 0.0D).tex(this.right / size, (this.top + (int)this.amountScrolled) / size).color(color, color, color, alpha).endVertex();
        buffer.pos(this.left, this.top, 0.0D).tex(this.left / size, (this.top + (int)this.amountScrolled) / size).color(color, color, color, alpha).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
    }
}