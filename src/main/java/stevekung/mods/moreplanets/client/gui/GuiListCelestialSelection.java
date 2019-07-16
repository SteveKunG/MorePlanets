package stevekung.mods.moreplanets.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.utils.client.GLConstants;

@SideOnly(Side.CLIENT)
public class GuiListCelestialSelection extends GuiListExtended
{
    private final GuiCelestialSelection selection;
    private final List<GuiListCelestialSelectionEntry> entries = new ArrayList<>();
    public final List<CelestialBody> listCelestial;
    private int selectedId = -1;

    public GuiListCelestialSelection(GuiCelestialSelection gui, List<CelestialBody> listCelestial, int width, int height, int top, int bottom, int slotHeight)
    {
        super(Minecraft.getMinecraft(), width, height, top, bottom, slotHeight);
        this.selection = gui;
        this.listCelestial = listCelestial;
        this.refreshList(GuiCelestialSelection.SortType.A_TO_Z);
    }

    public void refreshList(GuiCelestialSelection.SortType type)
    {
        this.entries.clear();
        Collections.sort(this.listCelestial, type);

        for (CelestialBody celestial : this.listCelestial)
        {
            this.entries.add(new GuiListCelestialSelectionEntry(this, celestial));
        }
    }

    public void refreshListSearch(String text)
    {
        this.entries.clear();
        List<CelestialBody> listCelestialTemp = new ArrayList<>();

        for (CelestialBody body : this.listCelestial)
        {
            if (body.getName().toLowerCase().contains(text.toLowerCase()))
            {
                listCelestialTemp.add(body);
            }
        }

        for (CelestialBody celestial : listCelestialTemp)
        {
            this.entries.add(new GuiListCelestialSelectionEntry(this, celestial));
        }
    }

    @Override
    public GuiListCelestialSelectionEntry getListEntry(int index)
    {
        return this.entries.get(index);
    }

    @Override
    protected int getSize()
    {
        return this.entries.size();
    }

    @Override
    protected int getScrollBarX()
    {
        return super.getScrollBarX() - 165;
    }

    @Override
    public int getListWidth()
    {
        return super.getListWidth() + 150;
    }

    @Override
    protected boolean isSelected(int slotIndex)
    {
        return slotIndex == this.selectedId;
    }

    @Override
    protected void drawSelectionBox(int insideLeft, int insideTop, int mouseX, int mouseY, float partialTicks)
    {
        int i = this.getSize();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        for (int j = 0; j < i; ++j)
        {
            int k = insideTop + j * this.slotHeight + this.headerPadding;
            int l = this.slotHeight - 4;

            if (k > this.bottom || k + l < this.top)
            {
                this.updateItemPos(j, insideLeft, k, partialTicks);
            }

            if (this.showSelectionBox && this.isSelected(j))
            {
                int left = this.left + this.width / 2 - this.getListWidth() / 2;
                int right = this.left + this.width / 2 - 230 + this.getListWidth() / 2;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.disableTexture2D();
                bufferbuilder.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                bufferbuilder.pos(left, k + l + 2, 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos(right, k + l + 2, 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos(right, k - 2, 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos(left, k - 2, 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos(left + 1, k + l + 1, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(right - 1, k + l + 1, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(right - 1, k - 1, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(left + 1, k - 1, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
                GlStateManager.enableTexture2D();
            }
            this.drawSlot(j, insideLeft, k, l, mouseX, mouseY, partialTicks);
        }
    }

    @Nullable
    public GuiListCelestialSelectionEntry getSelectedCelestial()
    {
        return this.selectedId >= 0 && this.selectedId < this.getSize() ? this.getListEntry(this.selectedId) : null;
    }

    public void selectCelestial(int idx)
    {
        this.selectedId = idx;
        this.selection.selectCelestial(this.getSelectedCelestial());
    }

    public GuiCelestialSelection getGui()
    {
        return this.selection;
    }

    public List<CelestialBody> getCelestialList()
    {
        return this.listCelestial;
    }
}