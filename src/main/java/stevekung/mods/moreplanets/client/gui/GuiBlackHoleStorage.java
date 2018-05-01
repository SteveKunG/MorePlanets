package stevekung.mods.moreplanets.client.gui;

import java.util.Arrays;
import java.util.UUID;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox.ICheckBoxCallback;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.inventory.ContainerBlackHoleStorage;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.utils.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementCheckboxMP;
import stevekung.mods.moreplanets.utils.client.gui.GuiElementInfoRegionMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

@SideOnly(Side.CLIENT)
public class GuiBlackHoleStorage extends GuiContainerMP implements ICheckBoxCallback
{
    private static final ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/black_hole_storage.png");
    private static final ResourceLocation textureXP = new ResourceLocation("moreplanets:textures/gui/black_hole_storage_xp.png");
    private GuiElementCheckbox disableBlackHoleCheckbox;
    private GuiElementCheckbox useHopperCheckbox;
    private GuiElementCheckboxMP collectModeCheckbox;
    private GuiElementInfoRegionMP xpValueInfo;
    private final TileEntityBlackHoleStorage tile;

    public GuiBlackHoleStorage(IInventory player, TileEntityBlackHoleStorage tile)
    {
        super(new ContainerBlackHoleStorage(player, tile));
        this.xSize = 256;
        this.ySize = 256;
        this.tile = tile;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 178, y + 192, 13, 13, Arrays.asList(LangUtils.translate("gui.disable_black_hole.desc")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 198, y + 192, 13, 13, Arrays.asList(LangUtils.translate("gui.use_hopper.desc")), this.width, this));
        this.infoRegions.add(new GuiElementInfoRegionMP(x + 218, y + 192, 13, 13, Arrays.asList(LangUtils.translate("gui.collect_mode.desc")), this.width, this));
        this.infoRegions.add(this.xpValueInfo = new GuiElementInfoRegionMP(x + 238, y + 170, 5, 37, null, this.width, this));
        this.disableBlackHoleCheckbox = new GuiElementCheckbox(0, this, x + 178, y + 192, "");
        this.useHopperCheckbox = new GuiElementCheckbox(1, this, x + 198, y + 192, "");
        this.collectModeCheckbox = new GuiElementCheckboxMP(2, this, x + 218, y + 192, "");
        this.buttonList.add(this.disableBlackHoleCheckbox);
        this.buttonList.add(this.useHopperCheckbox);
        this.buttonList.add(this.collectModeCheckbox);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.tile.getWorld().getPlayerEntityByUUID(UUID.fromString(this.tile.ownerUUID)).getName() + "'s", this.xSize - 92 + 8, this.ySize - 97 + 12, 4210752);
        this.fontRenderer.drawString(LangUtils.translate("container.black_hole_storage_short.name"), this.xSize - 92 + 8, this.ySize - 87 + 12, 4210752);

        String bhStatus = this.tile.disableBlackHole ? TextFormatting.RED + LangUtils.translate("gui.button.disable.name") : TextFormatting.GREEN + LangUtils.translate("gui.button.enable.name");
        this.fontRenderer.drawString(LangUtils.translate("gui.message.status.name") + ": " + bhStatus, this.xSize - 91 + 8, this.ySize - 86 + 44, 2536735);

        String hopperStatus = !this.tile.useHopper ? TextFormatting.RED + LangUtils.translate("gui.button.disable.name") : TextFormatting.GREEN + LangUtils.translate("gui.button.enable.name");
        this.fontRenderer.drawString(LangUtils.translate("gui.status.use_hopper.name") + ": " + hopperStatus, this.xSize - 91 + 8, this.ySize - 86 + 54, 2536735);

        String collectMode = this.tile.collectMode.equals("item") ? TextFormatting.AQUA + LangUtils.translate("gui.status.collect_item.name") : this.tile.collectMode.equals("item_and_xp") ? TextFormatting.AQUA + LangUtils.translate("gui.status.collect_item_and_xp.name") : TextFormatting.AQUA + LangUtils.translate("gui.status.collect_xp.name");
        this.fontRenderer.drawString(LangUtils.translate("gui.status.collect_mode.name") + ": " + collectMode, this.xSize - 91 + 8, this.ySize - 86 + 64, 2536735);

        this.xpValueInfo.tooltipStrings = Arrays.asList(LangUtils.translate("gui.xp_value.desc") + ": " + this.tile.xpTemp + "/" + this.tile.getMaxXP());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiBlackHoleStorage.texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        this.mc.getTextureManager().bindTexture(GuiBlackHoleStorage.textureXP);
        int level = Math.min((int) Math.floor(this.tile.fluidTank.getFluidAmount() * 37 / this.tile.getMaxXP()), 37);
        this.drawTexturedModalRect(x + 238, y + 170 + 37 - level, 238, 170 - level + 37, 5, level);
        this.disableBlackHoleCheckbox.isSelected = this.tile.disableBlackHole;
        this.useHopperCheckbox.isSelected = this.tile.useHopper;
    }

    @Override
    public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected)
    {
        if (checkbox.equals(this.disableBlackHoleCheckbox))
        {
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_BLACK_HOLE_STORAGE_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), "disable"));
        }
        else if (checkbox.equals(this.collectModeCheckbox))
        {
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_BLACK_HOLE_STORAGE_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), "collect_mode"));
        }
        else
        {
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_BLACK_HOLE_STORAGE_OPTION, GCCoreUtil.getDimensionID(this.tile.getWorld()), this.tile.getPos(), "use_hopper"));
        }
    }

    @Override
    public boolean canPlayerEdit(GuiElementCheckbox checkbox, EntityPlayer player)
    {
        return player.getGameProfile().getId().toString().equals(this.tile.ownerUUID);
    }

    @Override
    public boolean getInitiallySelected(GuiElementCheckbox checkbox)
    {
        if (checkbox.equals(this.disableBlackHoleCheckbox))
        {
            return this.tile.disableBlackHole;
        }
        else
        {
            return this.tile.useHopper;
        }
    }

    @Override
    public void onIntruderInteraction() {}
}