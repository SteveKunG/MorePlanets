package stevekung.mods.moreplanets.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.container.GuiContainerGC;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox.ICheckBoxCallback;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementInfoRegion;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.inventory.ContainerShieldGenerator;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.moreplanets.util.client.gui.GuiElementCheckboxMP;

public class GuiShieldGenerator extends GuiContainerGC implements ICheckBoxCallback
{
    private static ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/shield_generator.png");
    private TileEntityShieldGenerator tile;
    private GuiElementInfoRegion electricInfoRegion = new GuiElementInfoRegion((this.width - this.xSize) / 2 + 112, (this.height - this.ySize) / 2 + 37, 56, 9, new ArrayList<String>(), this.width, this.height, this);
    private GuiElementCheckbox checkboxRenderShield;
    private GuiElementCheckboxMP checkboxIncreaseKnock;
    private GuiElementCheckboxMP checkboxDecreaseKnock;
    private GuiButton buttonEnable;
    private GuiTextField shieldDamageText;
    private GuiTextField shieldMaxSizeText;

    public GuiShieldGenerator(InventoryPlayer inventory, TileEntityShieldGenerator tile)
    {
        super(new ContainerShieldGenerator(inventory, tile));
        this.tile = tile;
        this.ySize = 206;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.tile.getWorld()), new Object[] { this.tile.getPos(), 0 }));
            break;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        Keyboard.enableRepeatEvents(true);
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        List<String> batterySlotDesc = new ArrayList<>();
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.0"));
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.1"));
        this.infoRegions.add(new GuiElementInfoRegion((this.width - this.xSize) / 2 + 10, (this.height - this.ySize) / 2 + 23, 18, 18, batterySlotDesc, this.width, this.height, this));
        List<String> electricityDesc = new ArrayList<>();
        electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
        electricityDesc.add(EnumColor.YELLOW + GCCoreUtil.translate("gui.energy_storage.desc.1") + ((int) Math.floor(this.tile.getEnergyStoredGC()) + " / " + (int) Math.floor(this.tile.getMaxEnergyStoredGC())));
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        this.electricInfoRegion.xPosition = (this.width - this.xSize) / 2 + 44;
        this.electricInfoRegion.yPosition = (this.height - this.ySize) / 2 + 27;
        this.electricInfoRegion.parentWidth = this.width;
        this.electricInfoRegion.parentHeight = this.height;
        this.infoRegions.add(this.electricInfoRegion);
        List<String> renderDesc = new ArrayList<>();
        renderDesc.add(GCCoreUtil.translate("gui.shield_visible.desc"));
        this.infoRegions.add(new GuiElementInfoRegion(width + 118, height + 98, 13, 13, renderDesc, this.width, this.height, this));
        renderDesc = new ArrayList<>();
        renderDesc.add(GCCoreUtil.translate("gui.increase_knock.desc"));
        this.infoRegions.add(new GuiElementInfoRegion(width + 103, height + 98, 13, 13, renderDesc, this.width, this.height, this));
        renderDesc = new ArrayList<>();
        renderDesc.add(GCCoreUtil.translate("gui.decrease_knock.desc"));
        this.infoRegions.add(new GuiElementInfoRegion(width + 88, height + 98, 13, 13, renderDesc, this.width, this.height, this));
        this.checkboxRenderShield = new GuiElementCheckbox(100, this, width + 118, height + 98, "");
        this.checkboxIncreaseKnock = new GuiElementCheckboxMP(101, this, width + 103, height + 98, "");
        this.checkboxDecreaseKnock = new GuiElementCheckboxMP(102, this, width + 88, height + 98, "");
        this.buttonList.add(this.checkboxRenderShield);
        this.buttonList.add(this.checkboxIncreaseKnock);
        this.buttonList.add(this.checkboxDecreaseKnock);
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 80, this.height / 2 - 8, 72, 20, !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name")));
        this.buttonList.add(new GuiButton(5, this.width / 2 + 52, this.height / 2 - 8, 25, 20, "WIP"));

        this.shieldDamageText = new GuiTextField(1, this.fontRendererObj, this.width / 2 + 60, this.height / 4 + 2, 20, 16);
        this.shieldDamageText.setMaxStringLength(2);
        this.shieldDamageText.setFocused(false);
        this.shieldDamageText.setCanLoseFocus(true);
        this.shieldDamageText.setEnableBackgroundDrawing(true);
        this.shieldDamageText.setTextColor(16777215);
        this.shieldDamageText.setText(String.valueOf(this.tile.shieldDamage));

        this.shieldMaxSizeText = new GuiTextField(2, this.fontRendererObj, this.width / 2 + 60, this.height / 4 + 22, 20, 16);
        this.shieldMaxSizeText.setMaxStringLength(2);
        this.shieldMaxSizeText.setFocused(false);
        this.shieldMaxSizeText.setCanLoseFocus(true);
        this.shieldMaxSizeText.setEnableBackgroundDrawing(true);
        this.shieldMaxSizeText.setTextColor(16777215);
        this.shieldMaxSizeText.setText(String.valueOf(this.tile.maxShieldSize));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name");

        this.fontRendererObj.drawString(this.tile.getName(), 8, 10, 4210752);
        String status = GCCoreUtil.translate("gui.message.status.name") + ": " + this.getStatus();
        this.fontRendererObj.drawString(status, this.xSize / 2 - 80, 50, 4210752);

        int width = this.fontRendererObj.getStringWidth(GCCoreUtil.translate("gui.status.set_shield_damage.name") + ":");
        this.fontRendererObj.drawString(GCCoreUtil.translate("gui.status.set_shield_damage.name") + ":", this.xSize / 2 - width + 58, 19, 4210752);
        width = this.fontRendererObj.getStringWidth(GCCoreUtil.translate("gui.status.set_max_shield_size.name") + ":");
        this.fontRendererObj.drawString(GCCoreUtil.translate("gui.status.set_max_shield_size.name") + ":", this.xSize / 2 - width + 58, 39, 4210752);

        this.fontRendererObj.drawString(GCCoreUtil.translate("gui.status.knock_amount.name") + ": " + this.tile.knockAmount / 10.0D, this.xSize / 2 - 80, 60, 4210752);
        this.fontRendererObj.drawString(GCCoreUtil.translate("gui.status.shield_damage.name") + ": " + this.tile.shieldDamage, this.xSize / 2 - 80, 70, 4210752);
        this.fontRendererObj.drawString(GCCoreUtil.translate("gui.status.shield_size.name") + ": " + this.tile.maxShieldSize, this.xSize / 2 - 80, 80, 4210752);
        this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 90 + 2, 4210752);
    }

    private String getStatus()
    {
        return this.tile.getGUIstatus();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiShieldGenerator.texture);
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(width, height + 5, 0, 0, this.xSize, this.ySize);
        int scale = this.tile.getScaledElecticalLevel(54);
        this.drawTexturedModalRect(width + 45, height + 28, 197, 0, Math.min(scale, 54), 7);

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(width + 31, height + 27, 176, 0, 11, 10);
        }
        List<String> electricityDesc = new ArrayList<>();
        electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        this.checkboxRenderShield.isSelected = this.tile.shouldRender;
    }

    @Override
    public void updateScreen()
    {
        this.shieldDamageText.updateCursorCounter();
        this.shieldMaxSizeText.updateCursorCounter();
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
        this.shieldDamageText.drawTextBox();
        this.shieldMaxSizeText.drawTextBox();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        super.keyTyped(typedChar, keyCode);

        if (this.shieldDamageText.textboxKeyTyped(typedChar, keyCode))
        {
            int damage = 0;

            try
            {
                damage = Integer.parseInt(this.shieldDamageText.getText().isEmpty() ? "0" : this.shieldDamageText.getText());
            }
            catch (Exception e) {}

            if (damage > 10)
            {
                damage = 10;
                this.shieldDamageText.setText(String.valueOf(10));
            }
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), damage, "damage"));
        }
        if (this.shieldMaxSizeText.textboxKeyTyped(typedChar, keyCode))
        {
            int size = 1;

            try
            {
                size = Integer.parseInt(this.shieldMaxSizeText.getText().isEmpty() ? "1" : this.shieldMaxSizeText.getText());
            }
            catch (Exception e) {}

            if (size > 20)
            {
                size = 20;
                this.shieldMaxSizeText.setText(String.valueOf(20));
            }
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), size, "size"));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.shieldDamageText.mouseClicked(mouseX, mouseY, mouseButton);
        this.shieldMaxSizeText.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected)
    {
        if (checkbox.equals(this.checkboxRenderShield))
        {
            this.tile.setBubbleVisible(newSelected);
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_VISIBLE, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), this.tile.shouldRender));
        }
        else if (checkbox.equals(this.checkboxIncreaseKnock))
        {
            if (this.tile.knockAmount < 10)
            {
                GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), 1, "knock"));
            }
        }
        else if (checkbox.equals(this.checkboxDecreaseKnock))
        {
            if (this.tile.knockAmount > 0)
            {
                GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), -1, "knock"));
            }
        }
    }

    @Override
    public boolean canPlayerEdit(GuiElementCheckbox checkbox, EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean getInitiallySelected(GuiElementCheckbox checkbox)
    {
        return this.tile.shouldRender;
    }

    @Override
    public void onIntruderInteraction() {}
}