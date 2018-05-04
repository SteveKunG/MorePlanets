package stevekung.mods.moreplanets.client.gui;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox.ICheckBoxCallback;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.inventory.ContainerShieldGeneratorConfig;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementInfoRegionMP;
import stevekung.mods.stevekunglib.client.gui.GuiNumberField;
import stevekung.mods.stevekunglib.utils.LangUtils;

@SideOnly(Side.CLIENT)
public class GuiShieldGeneratorConfig extends GuiContainerMP implements ICheckBoxCallback
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/gui/shield_generator_config.png");
    private final TileEntityShieldGenerator tile;
    private GuiElementCheckbox checkboxRenderShield;
    private GuiElementCheckbox checkboxEnableShield;
    private GuiElementCheckbox checkboxEnableDamage;
    private GuiButton buttonBack;
    private GuiButton buttonDone;
    private GuiNumberField shieldDamageText;
    private GuiNumberField shieldSizeText;
    private int tempDamage;
    private int tempSize;
    private int messageTicks;

    public GuiShieldGeneratorConfig(InventoryPlayer inventory, TileEntityShieldGenerator tile)
    {
        super(new ContainerShieldGeneratorConfig(inventory, tile));
        this.tile = tile;
        this.ySize = 212;
        this.renderInfo = false;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SWITCH_SHIELD_GENERATOR_GUI, GCCoreUtil.getDimensionID(this.tile.getWorld()), new Object[] { this.tile.getPos(), false }));
            break;
        case 1:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), this.tempDamage, "damage"));
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), this.tempSize, "size"));
            this.messageTicks = 60;
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
        this.tempDamage = this.tile.shieldDamage;
        this.tempSize = this.tile.maxShieldSize;
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 151, y + 77, 18, 18, Arrays.asList(LangUtils.translate("gui.battery_slot.desc.0"), LangUtils.translate("gui.battery_slot.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 151, y + 59, 18, 18, Arrays.asList(LangUtils.translate("gui.shield_capacity_upgrade.desc.0"), LangUtils.translate("gui.shield_capacity_upgrade.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 151, y + 41, 18, 18, Arrays.asList(LangUtils.translate("gui.shield_size_upgrade.desc.0"), LangUtils.translate("gui.shield_size_upgrade.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 151, y + 23, 18, 18, Arrays.asList(LangUtils.translate("gui.shield_damage_upgrade.desc.0"), LangUtils.translate("gui.shield_damage_upgrade.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 60, y + 70, 13, 13, Arrays.asList(LangUtils.translate("gui.shield_visible.desc")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 80, y + 70, 13, 13, Arrays.asList(LangUtils.translate("gui.enable_shield.desc")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 100, y + 70, 13, 13, Arrays.asList(LangUtils.translate("gui.enable_shield_damage.desc")), this.width, this));
        this.checkboxRenderShield = new GuiElementCheckbox(100, this, x + 60, y + 70, "");
        this.checkboxEnableShield = new GuiElementCheckbox(101, this, x + 80, y + 70, "");
        this.checkboxEnableDamage = new GuiElementCheckbox(102, this, x + 100, y + 70, "");

        this.buttonList.add(this.checkboxRenderShield);
        this.buttonList.add(this.checkboxEnableShield);
        this.buttonList.add(this.checkboxEnableDamage);
        this.buttonList.add(this.buttonBack = new GuiButton(0, this.width / 2 - 76, this.height / 2 - 6, 72, 20, LangUtils.translate("gui.button.back.name")));
        this.buttonList.add(this.buttonDone = new GuiButton(1, this.width / 2 + 4, this.height / 2 - 6, 72, 20, LangUtils.translate("gui.done")));

        this.shieldDamageText = new GuiNumberField(1, this.fontRenderer, this.width / 2 - 3, this.height / 2 - 83, 30, 16);
        this.shieldDamageText.setMaxStringLength(2);
        this.shieldDamageText.setFocused(false);
        this.shieldDamageText.setCanLoseFocus(true);
        this.shieldDamageText.setEnableBackgroundDrawing(true);
        this.shieldDamageText.setTextColor(16777215);
        this.shieldDamageText.setText(String.valueOf(this.tile.shieldDamage));

        this.shieldSizeText = new GuiNumberField(2, this.fontRenderer, this.width / 2 - 3, this.height / 2 - 62, 30, 16);
        this.shieldSizeText.setMaxStringLength(2);
        this.shieldSizeText.setFocused(false);
        this.shieldSizeText.setCanLoseFocus(true);
        this.shieldSizeText.setEnableBackgroundDrawing(true);
        this.shieldSizeText.setTextColor(16777215);
        this.shieldSizeText.setText(String.valueOf(this.tile.maxShieldSize));

        if (this.tempDamage > this.tile.maxShieldDamage)
        {
            this.tempDamage = this.tile.maxShieldDamage;
            this.shieldDamageText.setText(String.valueOf(this.tempDamage));
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), this.tempDamage, "damage"));
        }
        if (this.tempSize > this.tile.maxShieldSizeUpgrade)
        {
            this.tempSize = this.tile.maxShieldSizeUpgrade;
            this.shieldSizeText.setText(String.valueOf(this.tempSize));
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), this.tempSize, "size"));
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String owner = "";

        try
        {
            owner = this.tile.getWorld().getPlayerEntityByUUID(UUID.fromString(this.tile.ownerUUID)).getName() + "'s ";
        }
        catch (Exception e) {}

        this.fontRenderer.drawString(owner + this.tile.getName(), 8, 10, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("container.inventory"), 8, this.ySize - 90 + 2, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("gui.status.shield_damage.name") + ":", 10, 27, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("gui.status.shield_size.name") + ":", 10, 48, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("gui.status.settings.name") + ":", 10, 73, 4210752);

        if (this.messageTicks > 0)
        {
            float ticks = this.messageTicks - this.mc.getRenderPartialTicks();
            int alpha = (int)(ticks * 255.0F / 60);

            if (alpha > 255)
            {
                alpha = 255;
            }
            if (alpha > 4)
            {
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                this.fontRenderer.drawString(LangUtils.translate("gui.status.config_save.name"), 10, 87, 4210752 + (alpha << 24 & -4210753));
                GlStateManager.disableBlend();
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiShieldGeneratorConfig.TEXTURE);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y + 5, 0, 0, this.xSize, this.ySize);
        this.checkboxRenderShield.isSelected = this.tile.shouldRender;
        this.checkboxEnableShield.isSelected = this.tile.enableShield;
        this.checkboxEnableDamage.isSelected = this.tile.enableDamage;
    }

    @Override
    public void updateScreen()
    {
        this.shieldDamageText.updateCursorCounter();
        this.shieldSizeText.updateCursorCounter();

        if (this.messageTicks > 0)
        {
            this.messageTicks--;
        }
        if (this.shieldDamageText.getText().isEmpty() || this.shieldSizeText.getText().isEmpty())
        {
            this.buttonDone.enabled = false;
        }
        else
        {
            this.buttonDone.enabled = true;
        }

        if (this.tempDamage > this.tile.maxShieldDamage)
        {
            this.tempDamage = this.tile.maxShieldDamage;
            this.shieldDamageText.setText(String.valueOf(this.tempDamage));
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), this.tempDamage, "damage"));
        }
        if (this.tempSize > this.tile.maxShieldSizeUpgrade)
        {
            this.tempSize = this.tile.maxShieldSizeUpgrade;
            this.shieldSizeText.setText(String.valueOf(this.tempSize));
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_GENERATOR_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), this.tempSize, "size"));
        }
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
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        this.shieldDamageText.drawTextBox();
        this.shieldSizeText.drawTextBox();
        GlStateManager.enableLighting();
        GlStateManager.enableBlend();
        this.renderInfo(mouseX, mouseY);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1 || !this.shieldDamageText.isFocused() && !this.shieldSizeText.isFocused())
        {
            super.keyTyped(typedChar, keyCode);
        }

        if (this.shieldDamageText.textboxKeyTyped(typedChar, keyCode))
        {
            try
            {
                this.tempDamage = Integer.parseInt(this.shieldDamageText.getText());
            }
            catch (Exception e)
            {
                this.tempDamage = 0;
            }

            if (this.tempDamage < 0)
            {
                this.tempDamage = 0;
                this.shieldDamageText.setText(String.valueOf(this.tempDamage));
            }
            if (this.tempDamage > this.tile.maxShieldDamage)
            {
                this.tempDamage = this.tile.maxShieldDamage;
                this.shieldDamageText.setText(String.valueOf(this.tempDamage));
            }
        }
        if (this.shieldSizeText.textboxKeyTyped(typedChar, keyCode))
        {
            try
            {
                this.tempSize = Integer.parseInt(this.shieldSizeText.getText());
            }
            catch (Exception e)
            {
                this.tempSize = 1;
            }

            if (this.tempSize < 1)
            {
                this.tempSize = 1;
                this.shieldSizeText.setText(String.valueOf(this.tempSize));
            }
            if (this.tempSize > this.tile.maxShieldSizeUpgrade)
            {
                this.tempSize = this.tile.maxShieldSizeUpgrade;
                this.shieldSizeText.setText(String.valueOf(this.tempSize));
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.shieldDamageText.mouseClicked(mouseX, mouseY, mouseButton);
        this.shieldSizeText.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected)
    {
        if (checkbox.equals(this.checkboxRenderShield))
        {
            this.tile.setBubbleVisible(newSelected);
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SHIELD_VISIBLE, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), this.tile.shouldRender));
        }
        else if (checkbox.equals(this.checkboxEnableShield))
        {
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_ENABLE_SHIELD, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos()));
        }
        else if (checkbox.equals(this.checkboxEnableDamage))
        {
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_ENABLE_SHIELD_DAMAGE, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos()));
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
        if (checkbox.equals(this.checkboxRenderShield))
        {
            return this.tile.shouldRender;
        }
        else if (checkbox.equals(this.checkboxEnableShield))
        {
            return this.tile.enableShield;
        }
        else if (checkbox.equals(this.checkboxEnableDamage))
        {
            return this.tile.enableDamage;
        }
        return false;
    }

    @Override
    public void onIntruderInteraction() {}
}