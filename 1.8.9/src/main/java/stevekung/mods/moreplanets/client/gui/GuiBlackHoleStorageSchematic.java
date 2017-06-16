package stevekung.mods.moreplanets.client.gui;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicResultPage;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.core.client.gui.container.GuiPositionedContainer;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.inventory.ContainerBlackHoleStorageSchematic;

@SideOnly(Side.CLIENT)
public class GuiBlackHoleStorageSchematic extends GuiPositionedContainer implements ISchematicResultPage
{
    private ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/black_hole_storage_schematic.png");
    private int pageIndex;

    public GuiBlackHoleStorageSchematic(InventoryPlayer player, BlockPos pos)
    {
        super(new ContainerBlackHoleStorageSchematic(player, pos), pos);
        this.ySize = 238;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 130, this.height / 2 - 110, 40, 20, GCCoreUtil.translate("gui.button.back.name")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 130, this.height / 2 - 110 + 25, 40, 20, GCCoreUtil.translate("gui.button.next.name")));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.schematic.black_hole_storage"), 7, -20 + 27, 4210752);
        this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, 220 - 104 + 2 + 27, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(this.texture);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.enabled)
        {
            switch (button.id)
            {
            case 0:
                SchematicRegistry.flipToLastPage(this, this.pageIndex);
                break;
            case 1:
                SchematicRegistry.flipToNextPage(this, this.pageIndex);
                break;
            }
        }
    }

    @Override
    public void setPageIndex(int index)
    {
        this.pageIndex = index;
    }
}