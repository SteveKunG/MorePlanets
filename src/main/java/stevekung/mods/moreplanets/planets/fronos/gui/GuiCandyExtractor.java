/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.inventory.container.ContainerCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCandyExtractor;

@SideOnly(Side.CLIENT)
public class GuiCandyExtractor extends GuiContainer
{
    private static final ResourceLocation candyExtractorGuiTextures = new ResourceLocation("fronos:textures/gui/candy_extractor.png");
    private final TileEntityCandyExtractor candyExtractorInventory;

    public GuiCandyExtractor(InventoryPlayer par1InventoryPlayer, TileEntityCandyExtractor par2TileEntityFurnace)
    {
        super(new ContainerCandyExtractor(par1InventoryPlayer, par2TileEntityFurnace));
        this.candyExtractorInventory = par2TileEntityFurnace;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        final String s = this.candyExtractorInventory.hasCustomInventoryName() ? this.candyExtractorInventory.getInventoryName() : I18n.format(this.candyExtractorInventory.getInventoryName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiCandyExtractor.candyExtractorGuiTextures);
        final int k = (this.width - this.xSize) / 2;
        final int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (this.candyExtractorInventory.isBurning())
        {
            i1 = this.candyExtractorInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.candyExtractorInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
}