package stevekung.mods.moreplanets.client.gui.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.MPLog;

@SideOnly(Side.CLIENT)
public class GuiListEntityFilter extends GuiListExtended
{
    private final GuiShieldGeneratorEntityFilter parent;
    private final List<GuiListEntityFilterEntry> entries = new ArrayList<>();
    private int selectedIdx = -1;

    public GuiListEntityFilter(GuiShieldGeneratorEntityFilter parent, Minecraft mc, int width, int height, int top, int bottom, int slotHeight)
    {
        super(mc, width, height, top, bottom, slotHeight);
        this.parent = parent;
        this.initEntityList();
    }

    public void initEntityList()
    {
        List<String> list = null;

        try
        {
            list = EntityList.getEntityNameList();
        }
        catch (Exception e)
        {
            MPLog.error("Unable to collect entities name list!");
        }

        Collections.sort(list);

        for (String name : list)
        {
            this.entries.add(new GuiListEntityFilterEntry(this, name));
        }
    }

    @Override
    public GuiListEntityFilterEntry getListEntry(int index)
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
        return super.getScrollBarX() + 20;
    }

    @Override
    public int getListWidth()
    {
        return super.getListWidth() + 50;
    }

    @Override
    protected boolean isSelected(int index)
    {
        return index == this.selectedIdx;
    }

    public void selectEntity(int index)
    {
        this.selectedIdx = index;
        this.parent.selectEntity(this.getSelectedEntity());
    }

    @Nullable
    public GuiListEntityFilterEntry getSelectedEntity()
    {
        return this.selectedIdx >= 0 && this.selectedIdx < this.getSize() ? this.getListEntry(this.selectedIdx) : null;
    }

    public GuiShieldGeneratorEntityFilter getGuiSelection()
    {
        return this.parent;
    }
}