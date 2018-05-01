package stevekung.mods.moreplanets.utils.debug;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.inventory.ContainerShieldGenerator;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementInfoRegionMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class GuiShieldGeneratorEntityFilter extends GuiContainerMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/gui/shield_generator_config.png");
    private final TileEntityShieldGenerator tile;
    private GuiButton buttonBack;
    private GuiButton buttonAdd;
    private GuiTextField entityTextbox;
    private GuiListEntityFilter selectionList;

    public GuiShieldGeneratorEntityFilter(InventoryPlayer inventory, TileEntityShieldGenerator tile)
    {
        super(new ContainerShieldGenerator(inventory, tile));
        this.tile = tile;
        this.ySize = 212;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SWITCH_SHIELD_GENERATOR_GUI, GCCoreUtil.getDimensionID(this.tile.getWorld()), new Object[] { this.tile.getPos(), 1 }));
            break;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        Keyboard.enableRepeatEvents(true);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 151, y + 77, 18, 18, Arrays.asList(LangUtils.translate("gui.battery_slot.desc.0"), LangUtils.translate("gui.battery_slot.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 60, y + 70, 13, 13, Arrays.asList(LangUtils.translate("gui.shield_visible.desc")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 80, y + 70, 13, 13, Arrays.asList(LangUtils.translate("gui.enable_shield.desc")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 100, y + 70, 13, 13, Arrays.asList(LangUtils.translate("gui.enable_shield_damage.desc")), this.width, this));
        this.buttonList.add(this.buttonBack = new GuiButton(0, this.width / 2 - 76, this.height / 2 - 6, 72, 20, LangUtils.translate("gui.button.back.name")));
        this.buttonList.add(this.buttonAdd = new GuiButton(1, this.width / 2 - 160, this.height / 2 - 6, 72, 20, LangUtils.translate("gui.button.add.name")));
        this.buttonAdd.enabled = false;

        this.entityTextbox = new GuiTextField(0, this.fontRenderer, this.width / 2 - 3, this.height / 2 - 83, 30, 16);
        this.entityTextbox.setMaxStringLength(2);
        this.entityTextbox.setFocused(false);
        this.entityTextbox.setCanLoseFocus(true);
        this.entityTextbox.setEnableBackgroundDrawing(true);
        this.entityTextbox.setTextColor(16777215);
        this.entityTextbox.setText("");

        this.selectionList = new GuiListEntityFilter(this, this.mc, this.width, this.height, 32, this.height - 64, 36);
    }

    @Override
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.selectionList.handleMouseInput();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        // backward compatibility
        String owner = null;

        try
        {
            owner = this.tile.getWorld().getPlayerEntityByUUID(UUID.fromString(this.tile.ownerUUID)).getName() + "'s ";
        }
        catch (Exception e)
        {
            owner = "";
        }
        this.fontRenderer.drawString(owner + this.tile.getName(), 8, 10, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("container.inventory"), 8, this.ySize - 90 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiShieldGeneratorEntityFilter.TEXTURE);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y + 5, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void updateScreen()
    {
        this.entityTextbox.updateCursorCounter();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.entityTextbox.mouseClicked(mouseX, mouseY, mouseButton);
        this.selectionList.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        super.mouseReleased(mouseX, mouseY, state);
        this.selectionList.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        this.entityTextbox.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.selectionList.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        this.entityTextbox.drawTextBox();
        GlStateManager.enableLighting();
        GlStateManager.enableBlend();
    }

    public void selectEntity(@Nullable GuiListEntityFilterEntry entry)
    {
        boolean flag = entry != null;
        this.buttonAdd.enabled = flag;
    }
}