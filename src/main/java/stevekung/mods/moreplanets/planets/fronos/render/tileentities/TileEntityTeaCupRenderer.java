/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.render.tileentities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.models.items.ModelFilledCup;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityTeaCup;

@SideOnly(Side.CLIENT)
public class TileEntityTeaCupRenderer extends TileEntitySpecialRenderer
{
    private static ResourceLocation oysterTexture = new ResourceLocation("fronos:textures/model/cup/tea.png");
    public ModelFilledCup model = new ModelFilledCup();

    public void renderTileEntityChestAt(TileEntityTeaCup oyster, double par2, double par4, double par6, float par8)
    {
        int i;

        if (!oyster.hasWorldObj())
        {
            i = 0;
        }
        else
        {
            oyster.getBlockType();
            i = oyster.getBlockMetadata();
        }

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.bindTexture(TileEntityTeaCupRenderer.oysterTexture);
        GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.0F, -1.0F, 0.0F);
        float short1 = 0;

        switch (i)
        {
        case 0:
            short1 = 180;
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            break;
        case 1:
            short1 = 0;
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            break;
        case 2:
            short1 = 90;
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            break;
        case 3:
            short1 = -90;
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            break;
        }

        GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
        this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityChestAt((TileEntityTeaCup)par1TileEntity, par2, par4, par6, par8);
    }
}