package stevekung.mods.moreplanets.client.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

@SideOnly(Side.CLIENT)
public class GuiChangeLogSlot extends GuiSlot
{
    private List<String> stringList;
    private GuiFullChangeLog parent;
    private boolean textureType;
    private static final ResourceLocation POLISHED_TIN = new ResourceLocation("moreplanets:textures/blocks/polished_tin_decoration_block.png");
    private static final ResourceLocation POLISHED_ALUMINUM = new ResourceLocation("moreplanets:textures/blocks/polished_aluminum_decoration_block.png");
    private static final ResourceLocation TRANSPARENT = new ResourceLocation("moreplanets:textures/gui/change_log_overlay.png");

    public GuiChangeLogSlot(GuiFullChangeLog parent, List<String> stringList, int width, int height, boolean texture)
    {
        super(Minecraft.getMinecraft(), width, height, 32, height - 64, 13);
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
    protected void drawSlot(int entryID, int insideLeft, int yPos, int insideSlotHeight, int mouseX, int mouseY, float partialTicks)
    {
        String test = this.stringList.get(entryID);
        this.mc.fontRenderer.drawStringWithShadow(test, insideLeft - 20, yPos + 2, 16777215);
    }

    @Override
    protected void overlayBackground(int startY, int endY, int startAlpha, int endAlpha)
    {
        this.parent.drawCenteredString(this.mc.fontRenderer, "More Planets " + MorePlanetsMod.VERSION + " Change Log", this.width / 2, 16, 16777215);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
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
        BufferBuilder buffer = tessellator.getBuffer();
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

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.drawBackground();
            int i = this.getScrollBarX();
            int j = i + 6;
            this.bindAmountScrolled();
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder vertexbuffer = tessellator.getBuffer();
            this.drawContainerBackground(tessellator);
            int k = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
            int l = this.top + 4 - (int)this.amountScrolled;

            if (this.hasListHeader)
            {
                this.drawListHeader(k, l, tessellator);
            }

            this.drawSelectionBox(k, l, mouseX, mouseY, partialTicks);
            GlStateManager.disableDepth();
            this.overlayBackground(0, this.top, 255, 255);
            this.overlayBackground(this.bottom, this.height, 255, 255);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
            GlStateManager.disableAlpha();
            GlStateManager.shadeModel(7425);
            GlStateManager.disableTexture2D();
            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            vertexbuffer.pos(this.left, this.top + 4, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 0).endVertex();
            vertexbuffer.pos(this.right, this.top + 4, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 0).endVertex();
            vertexbuffer.pos(this.right, this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 100).endVertex();
            vertexbuffer.pos(this.left, this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 100).endVertex();
            tessellator.draw();
            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            vertexbuffer.pos(this.left, this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 100).endVertex();
            vertexbuffer.pos(this.right, this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 100).endVertex();
            vertexbuffer.pos(this.right, this.bottom - 4, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 0).endVertex();
            vertexbuffer.pos(this.left, this.bottom - 4, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 0).endVertex();
            tessellator.draw();
            int j1 = this.getMaxScroll();

            if (j1 > 0)
            {
                int k1 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
                k1 = MathHelper.clamp(k1, 32, this.bottom - this.top - 8);
                int l1 = (int)this.amountScrolled * (this.bottom - this.top - k1) / j1 + this.top;

                if (l1 < this.top)
                {
                    l1 = this.top;
                }
                vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                vertexbuffer.pos(i, l1 + k1, 0.0D).tex(0.0D, 1.0D).color(192, 192, 192, 100).endVertex();
                vertexbuffer.pos(j, l1 + k1, 0.0D).tex(1.0D, 1.0D).color(192, 192, 192, 100).endVertex();
                vertexbuffer.pos(j, l1, 0.0D).tex(1.0D, 0.0D).color(192, 192, 192, 100).endVertex();
                vertexbuffer.pos(i, l1, 0.0D).tex(0.0D, 0.0D).color(192, 192, 192, 100).endVertex();
                tessellator.draw();
                vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                vertexbuffer.pos(i, l1 + k1 - 1, 0.0D).tex(0.0D, 1.0D).color(192, 192, 192, 100).endVertex();
                vertexbuffer.pos(j - 1, l1 + k1 - 1, 0.0D).tex(1.0D, 1.0D).color(192, 192, 192, 100).endVertex();
                vertexbuffer.pos(j - 1, l1, 0.0D).tex(1.0D, 0.0D).color(192, 192, 192, 100).endVertex();
                vertexbuffer.pos(i, l1, 0.0D).tex(0.0D, 0.0D).color(192, 192, 192, 100).endVertex();
                tessellator.draw();
            }
            this.renderDecorations(mouseX, mouseY);
            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel(7424);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
        }
    }

    @Override
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {}

    @Override
    protected void drawBackground() {}
}