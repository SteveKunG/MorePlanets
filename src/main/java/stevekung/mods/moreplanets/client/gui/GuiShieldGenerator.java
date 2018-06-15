package stevekung.mods.moreplanets.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.inventory.ContainerShieldGenerator;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementInfoRegionMP;
import stevekung.mods.stevekunglib.utils.LangUtils;
import stevekung.mods.stevekunglib.utils.NumberUtils;

@SideOnly(Side.CLIENT)
public class GuiShieldGenerator extends GuiContainerMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/gui/shield_generator.png");
    private final TileEntityShieldGenerator tile;
    private GuiElementInfoRegionMP electricInfoRegion;
    private GuiButton buttonEnable;
    private GuiButton buttonConfig;

    public GuiShieldGenerator(InventoryPlayer inventory, TileEntityShieldGenerator tile)
    {
        super(new ContainerShieldGenerator(inventory, tile));
        this.tile = tile;
        this.xSize = 201;
        this.ySize = 212;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.tile.getWorld()), new Object[] { this.tile.getPos(), 0 }));
            break;
        case 1:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_SWITCH_SHIELD_GENERATOR_GUI, GCCoreUtil.getDimensionID(this.tile.getWorld()), new Object[] { this.tile.getPos(), true }));
            break;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        x += 13;
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 151, y + 77, 18, 18, Arrays.asList(LangUtils.translate("gui.battery_slot.desc.0"), LangUtils.translate("gui.battery_slot.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 176, y + 59, 18, 18, Arrays.asList(LangUtils.translate("gui.shield_capacity_upgrade.desc.0"), LangUtils.translate("gui.shield_capacity_upgrade.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 176, y + 41, 18, 18, Arrays.asList(LangUtils.translate("gui.shield_size_upgrade.desc.0"), LangUtils.translate("gui.shield_size_upgrade.desc.1")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 176, y + 23, 18, 18, Arrays.asList(LangUtils.translate("gui.shield_damage_upgrade.desc.0"), LangUtils.translate("gui.shield_damage_upgrade.desc.1")), this.width, this));
        List<String> electricityDesc = new ArrayList<>();
        electricityDesc.add(LangUtils.translate("gui.energy_storage.desc.0"));
        electricityDesc.add(TextFormatting.YELLOW + LangUtils.translate("gui.energy_storage.desc.1") + ((int) Math.floor(this.tile.getEnergyStoredGC()) + " / " + (int) Math.floor(this.tile.getMaxEnergyStoredGC())));
        this.electricInfoRegion = new GuiElementInfoRegionMP(x + 156, y + 21, 8, 43, electricityDesc, this.width, this);
        this.infoRegions.add(this.electricInfoRegion);
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 76, this.height / 2 - 6, 72, 20, !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name")));
        this.buttonList.add(this.buttonConfig = new GuiButton(1, this.width / 2 + 4, this.height / 2 - 6, 72, 20, LangUtils.translate("gui.button.config.name")));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.buttonEnable.enabled = this.tile.disableCooldown == 0;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? LangUtils.translate("gui.button.disable.name") : LangUtils.translate("gui.button.enable.name");

        String owner = "";

        try
        {
            owner = this.tile.getWorld().getPlayerEntityByUUID(UUID.fromString(this.tile.ownerUUID)).getName() + "'s ";
        }
        catch (Exception e) {}

        boolean half = this.tile.shieldCapacity < this.tile.maxShieldCapacity / 1.25F;
        boolean half1 = this.tile.shieldCapacity < this.tile.maxShieldCapacity / 4F;
        TextFormatting color = half1 ? TextFormatting.DARK_RED : half ? TextFormatting.GOLD : TextFormatting.GREEN;

        List<String> list = new ArrayList<>();
        int y = 24;
        list.add(LangUtils.translate("gui.message.status.name") + ": " + this.tile.getStatus());
        list.add(LangUtils.translate("gui.status.shield_damage.name") + ": " + TextFormatting.GREEN + this.tile.shieldDamage);
        list.add(LangUtils.translate("gui.status.shield_size.name") + ": " + TextFormatting.GREEN + this.tile.maxShieldSize);
        list.add(LangUtils.translate("gui.status.shield_capacity.name") + ": " + color + NumberUtils.format(this.tile.shieldCapacity) + "/" + NumberUtils.format(this.tile.maxShieldCapacity));

        if (this.tile.shieldChargeCooldown > 0)
        {
            list.add(LangUtils.translate("gui.status.shield_charge_cooldown.name") + ": " + TextFormatting.AQUA + this.tile.shieldChargeCooldown / 20);
        }
        for (String text : list)
        {
            this.fontRenderer.drawString(text, this.xSize / 2 - 79, y, 2536735);
            y += 10;
        }
        this.fontRenderer.drawString(owner + this.tile.getName(), 21, 10, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("container.inventory"), 21, this.ySize - 90 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiShieldGenerator.TEXTURE);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        x += 13;
        this.drawTexturedModalRect(x, y + 5, 0, 0, this.xSize, this.ySize);
        int scale = this.tile.getScaledElecticalLevel(42);
        this.drawTexturedModalRect(x + 156, y + 64 - scale, 201, 42 - scale + 10, 8, scale);

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(x + 154, y + 66, 201, 0, 11, 10);
        }

        List<String> electricityDesc = new ArrayList<>(Arrays.asList(LangUtils.translate("gui.energy_storage.desc.0")));
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;
    }
}