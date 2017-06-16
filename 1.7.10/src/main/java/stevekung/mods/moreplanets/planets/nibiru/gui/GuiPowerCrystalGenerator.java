/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.nibiru.inventory.container.ContainerPowerCrystalGenerator;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityPowerCrystalGenerator;

@SideOnly(Side.CLIENT)
public class GuiPowerCrystalGenerator extends GuiContainer
{
    private static final ResourceLocation powerCrystalGeneratorTexture = new ResourceLocation("nibiru:textures/gui/power_crystal_generator.png");

    private final TileEntityPowerCrystalGenerator tileEntity;

    public GuiPowerCrystalGenerator(InventoryPlayer par1InventoryPlayer, TileEntityPowerCrystalGenerator tileEntity)
    {
        super(new ContainerPowerCrystalGenerator(par1InventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString(this.tileEntity.getInventoryName(), 29, 6, 4210752);
        String displayText;

        if (this.tileEntity.heatGJperTick <= 0)
        {
            displayText = GCCoreUtil.translate("gui.status.power.name") + ": 0%";
        }
        else if (this.tileEntity.heatGJperTick < TileEntityPowerCrystalGenerator.MIN_GENERATE_GJ_PER_TICK)
        {
            displayText = GCCoreUtil.translate("gui.status.power.name") + ": " + (int) (this.tileEntity.heatGJperTick / TileEntityPowerCrystalGenerator.MIN_GENERATE_GJ_PER_TICK * 100) + "%";
        }
        else
        {
            displayText = EnergyDisplayHelper.getEnergyDisplayS(this.tileEntity.heatGJperTick - TileEntityPowerCrystalGenerator.MIN_GENERATE_GJ_PER_TICK) + "/t";
        }
        this.fontRendererObj.drawString(displayText, 122 - this.fontRendererObj.getStringWidth(displayText) / 2, 38, 4210752);
        this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        this.mc.renderEngine.bindTexture(GuiPowerCrystalGenerator.powerCrystalGeneratorTexture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        final int containerWidth = (this.width - this.xSize) / 2;
        final int containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(containerWidth, containerHeight, 0, 0, this.xSize, this.ySize);
    }
}