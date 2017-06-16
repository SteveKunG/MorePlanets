/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.render.tileentities;

import java.awt.Color;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.BlockIcyPoisonCrystal;
import stevekung.mods.moreplanets.planets.kapteynb.models.ModelIcyPoisonCrystal;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityIcyPoisonCrystal;

public class TileEntityIcyPoisonCrystalRenderer extends TileEntitySpecialRenderer
{
    private ModelIcyPoisonCrystal model;

    public TileEntityIcyPoisonCrystalRenderer()
    {
        this.model = new ModelIcyPoisonCrystal();
    }

    private void translateFromOrientation(float x, float y, float z, int orientation)
    {
        if (orientation == 0)
        {
            GL11.glTranslatef(x + 0.5F, y + 1.3F, z + 0.5F);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        }
        else if (orientation == 1)
        {
            GL11.glTranslatef(x + 0.5F, y - 0.3F, z + 0.5F);
        }
        else if (orientation == 2)
        {
            GL11.glTranslatef(x + 0.5F, y + 0.5F, z + 1.3F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        }
        else if (orientation == 3)
        {
            GL11.glTranslatef(x + 0.5F, y + 0.5F, z - 0.3F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        }
        else if (orientation == 4)
        {
            GL11.glTranslatef(x + 1.3F, y + 0.5F, z + 0.5F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        }
        else if (orientation == 5)
        {
            GL11.glTranslatef(x - 0.3F, y + 0.5F, z + 0.5F);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
        }
    }

    private void drawCrystal(int ori, float x, float y, float z, float a1, float a2, Random rand, int color, float size)
    {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        float shade = MathHelper.sin((player.ticksExisted + rand.nextInt(2)) / (5.0F + rand.nextFloat())) * 0.100F + 1.022F;
        Color c = new Color(color);
        float r = c.getRed() / 180.0F;
        float g = c.getGreen() / 180.0F;
        float b = c.getBlue() / 180.0F;

        GL11.glPushMatrix();
        GL11.glEnable(2977);
        GL11.glEnable(3042);
        GL11.glEnable(32826);
        GL11.glBlendFunc(770, 771);
        this.translateFromOrientation(x, y, z, ori);
        GL11.glRotatef(a1, 0.1F, 1.0F, 0.0F);
        GL11.glRotatef(a2, 1.0F, 0.0F, 0.0F);
        GL11.glScalef((0.15F + rand.nextFloat() * 0.075F) * size, (0.5F + rand.nextFloat() * 0.1F) * size, (0.15F + rand.nextFloat() * 0.05F) * size);
        int var19 = (int)(215.0F * shade);
        int var20 = var19 % 65536;
        int var21 = var19 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var20 / 1.0F, var21 / 1.0F);
        GL11.glColor4f(r, g, b, 1.0F);
        this.model.render();
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        TileEntityIcyPoisonCrystal crystal = (TileEntityIcyPoisonCrystal)tile;
        int color;
        Random rand = new Random(crystal.getBlockMetadata() + crystal.xCoord + crystal.yCoord * crystal.zCoord);
        this.bindTexture(new ResourceLocation("kapteynb:textures/model/icy_poison_crystal.png"));

        for (int i = 0; i < 6; i++)
        {
            color = BlockIcyPoisonCrystal.colors[i];

            for (int spike = 0; spike < 6; spike++)
            {
                int angle1 = rand.nextInt(36) + 72 * spike;
                int angle2 = 15 + rand.nextInt(15);
                this.drawCrystal(crystal.orientation, (float)x, (float)y, (float)z, angle1, angle2, rand, color, 1.1F);
            }
        }
        GL11.glPopMatrix();
        GL11.glDisable(3042);
    }
}