/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.render.tileentities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import micdoodle8.mods.galacticraft.core.client.model.block.ModelSolarPanel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityUltraVioletSolarPanel;

public class TileEntityUltraVioletSolarPanelRenderer extends TileEntitySpecialRenderer
{
    private static final ResourceLocation solarPanelAdvTexture = new ResourceLocation("polongnius:textures/model/ultra_violet_solar_panel.png");
    public ModelSolarPanel model = new ModelSolarPanel();

    @Override
    public void renderTileEntityAt(TileEntity var1, double par2, double par4, double par6, float var8)
    {
        final TileEntityUltraVioletSolarPanel panel = (TileEntityUltraVioletSolarPanel) var1;

        if (var1.getBlockType() == PolongniusBlocks.ultra_violet_solar_panel)
        {
            this.bindTexture(TileEntityUltraVioletSolarPanelRenderer.solarPanelAdvTexture);
        }

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);

        GL11.glTranslatef(0.5F, 1.0F, 0.5F);
        this.model.renderPole();
        GL11.glTranslatef(0.0F, 1.5F, 0.0F);

        GL11.glRotatef(180.0F, 0, 0, 1);
        GL11.glRotatef(-90.0F, 0, 1, 0);

        final float celestialAngle = (panel.getWorldObj().getCelestialAngle(1.0F) - 0.784690560F) * 360.0F;
        final float celestialAngle2 = panel.getWorldObj().getCelestialAngle(1.0F) * 360.0F;

        GL11.glRotatef(panel.currentAngle - (celestialAngle - celestialAngle2), 1.0F, 0.0F, 0.0F);

        this.model.renderPanel();

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}