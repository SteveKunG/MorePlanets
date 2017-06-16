/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.dimension.sky;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;
import stevekung.mods.moreplanets.planets.pluto.dimension.WorldProviderPluto;

public class SkyProviderPluto extends IRenderHandler
{
    private ResourceLocation sunTexture = new ResourceLocation("galacticraftcore:textures/gui/planets/sun.png");
    private ResourceLocation charonTexture = new ResourceLocation("pluto:textures/gui/celestialbodies/charon.png");

    public int starGLCallList = GLAllocation.generateDisplayLists(3);
    public int glSkyList;
    public int glSkyList2;
    private float sunSize;

    public SkyProviderPluto(IGalacticraftWorldProvider gcProvider)
    {
        this.sunSize = 17.5F * gcProvider.getSolarSize();
        GL11.glPushMatrix();
        GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
        this.renderStars();
        GL11.glEndList();
        GL11.glPopMatrix();
        Tessellator tessellator = Tessellator.instance;
        this.glSkyList = this.starGLCallList + 1;
        GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
        byte byte2 = 64;
        int i = 256 / byte2 + 2;
        float f = 16F;

        for (int j = -byte2 * i; j <= byte2 * i; j += byte2)
        {
            for (int l = -byte2 * i; l <= byte2 * i; l += byte2)
            {
                tessellator.startDrawingQuads();
                tessellator.addVertex(j + 0, f, l + 0);
                tessellator.addVertex(j + byte2, f, l + 0);
                tessellator.addVertex(j + byte2, f, l + byte2);
                tessellator.addVertex(j + 0, f, l + byte2);
                tessellator.draw();
            }
        }

        GL11.glEndList();
        this.glSkyList2 = this.starGLCallList + 2;
        GL11.glNewList(this.glSkyList2, GL11.GL_COMPILE);
        f = -16F;
        tessellator.startDrawingQuads();

        for (int k = -byte2 * i; k <= byte2 * i; k += byte2)
        {
            for (int i1 = -byte2 * i; i1 <= byte2 * i; i1 += byte2)
            {
                tessellator.addVertex(k + byte2, f, i1 + 0);
                tessellator.addVertex(k + 0, f, i1 + 0);
                tessellator.addVertex(k + 0, f, i1 + byte2);
                tessellator.addVertex(k + byte2, f, i1 + byte2);
            }
        }

        tessellator.draw();
        GL11.glEndList();
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc)
    {
        WorldProviderPluto gcProvider = null;

        if (world.provider instanceof WorldProviderPluto)
        {
            gcProvider = (WorldProviderPluto) world.provider;
        }

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.enableStandardItemLighting();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Vec3 vec3 = world.getSkyColor(mc.renderViewEntity, partialTicks);
        float f1 = (float) vec3.xCoord;
        float f2 = (float) vec3.yCoord;
        float f3 = (float) vec3.zCoord;
        float f6;

        if (mc.gameSettings.anaglyph)
        {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }

        GL11.glColor3f(1F, 1F, 1F);
        Tessellator var23 = Tessellator.instance;
        Tessellator tessellator1 = Tessellator.instance;
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(0, 0, 0);
        GL11.glCallList(this.glSkyList);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.disableStandardItemLighting();
        float var10;
        float var11;
        float var12;

        float var20 = 0;

        if (gcProvider != null)
        {
            var20 = gcProvider.getStarBrightness(partialTicks);
        }

        if (var20 > 0.0F)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-19.0F, 0, 1.0F, 0);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var20);
            GL11.glCallList(this.starGLCallList);
            GL11.glPopMatrix();
        }

        float f7;
        float f8;
        float f9;
        float f10;
        float f18 = 0;

        float[] afloat = new float[4];
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glPushMatrix();
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        afloat[0] = 255 / 255.0F;
        afloat[1] = 255 / 255.0F;
        afloat[2] = 235 / 255.0F;
        afloat[3] = 0.3F;
        f6 = afloat[0];
        f7 = afloat[1];
        f8 = afloat[2];
        float f11;

        if (mc.gameSettings.anaglyph)
        {
            f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
            f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
            f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
            f6 = f9;
            f7 = f10;
            f8 = f11;
        }

        f18 = 1.0F - f18;

        tessellator1.startDrawing(GL11.GL_TRIANGLE_FAN);
        tessellator1.setColorRGBA_F(f6 * f18, f7 * f18, f8 * f18, afloat[3] * 2 / f18);
        tessellator1.addVertex(0.0D, 100.0D, 0.0D);
        tessellator1.setColorRGBA_F(afloat[0] * f18, afloat[1] * f18, afloat[2] * f18, 0.0F);

        // Render sun aura
        f10 = 3.5F;
        tessellator1.addVertex(-f10, 100.0D, -f10);
        tessellator1.addVertex(0, 100.0D, (double) -f10 * 1.5F);
        tessellator1.addVertex(f10, 100.0D, -f10);
        tessellator1.addVertex((double) f10 * 1.5F, 100.0D, 0);
        tessellator1.addVertex(f10, 100.0D, f10);
        tessellator1.addVertex(0, 100.0D, (double) f10 * 1.5F);
        tessellator1.addVertex(-f10, 100.0D, f10);
        tessellator1.addVertex((double) -f10 * 1.5F, 100.0D, 0);
        tessellator1.addVertex(-f10, 100.0D, -f10);

        tessellator1.draw();
        tessellator1.startDrawing(GL11.GL_TRIANGLE_FAN);
        tessellator1.setColorRGBA_F(f6 * f18, f7 * f18, f8 * f18, afloat[3] * f18);
        tessellator1.addVertex(0.0D, 100.0D, 0.0D);
        tessellator1.setColorRGBA_F(afloat[0] * f18, afloat[1] * f18, afloat[2] * f18, 0.0F);

        // Render larger sun aura
        f10 = 5.0F;
        tessellator1.addVertex(-f10, 100.0D, -f10);
        tessellator1.addVertex(0, 100.0D, (double) -f10 * 1.5F);
        tessellator1.addVertex(f10, 100.0D, -f10);
        tessellator1.addVertex((double) f10 * 1.5F, 100.0D, 0);
        tessellator1.addVertex(f10, 100.0D, f10);
        tessellator1.addVertex(0, 100.0D, (double) f10 * 1.5F);
        tessellator1.addVertex(-f10, 100.0D, f10);
        tessellator1.addVertex((double) -f10 * 1.5F, 100.0D, 0);
        tessellator1.addVertex(-f10, 100.0D, -f10);

        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glShadeModel(GL11.GL_FLAT);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ZERO);
        GL11.glPushMatrix();
        f7 = 0.0F;
        f8 = 0.0F;
        f9 = 0.0F;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(f7, f8, f9);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glPushMatrix();

        GL11.glPopMatrix();

        GL11.glPushMatrix();

        // Render sun
        GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        f10 = this.sunSize - 6.5F;
        mc.renderEngine.bindTexture(this.sunTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV(-f10, 100.0D, -f10, 0.0D, 0.0D);
        tessellator1.addVertexWithUV(f10, 100.0D, -f10, 1.0D, 0.0D);
        tessellator1.addVertexWithUV(f10, 100.0D, f10, 1.0D, 1.0D);
        tessellator1.addVertexWithUV(-f10, 100.0D, f10, 0.0D, 1.0D);
        tessellator1.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        GL11.glPushMatrix();

        // Charon
        f10 = 5.25F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(130.0F, 8.0F, 0.4F, 1.0F);
        GL11.glRotatef(10F, 1.0F, 0.0F, 5.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
        mc.renderEngine.bindTexture(this.charonTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV(-f10, -100.0D, f10, 0, 1);
        tessellator1.addVertexWithUV(f10, -100.0D, f10, 1, 1);
        tessellator1.addVertexWithUV(f10, -100.0D, -f10, 1, 0);
        tessellator1.addVertexWithUV(-f10, -100.0D, -f10, 0, 0);
        tessellator1.draw();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        double var25 = mc.thePlayer.getPosition(partialTicks).yCoord - world.getHorizon();

        if (var25 < 0.0D)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 12.0F, 0.0F);
            GL11.glCallList(this.glSkyList2);
            GL11.glPopMatrix();
            var10 = 1.0F;
            var11 = -((float) (var25 + 65.0D));
            var12 = -var10;
            var23.startDrawingQuads();
            var23.setColorRGBA_I(0, 255);
            var23.addVertex(-var10, var11, var10);
            var23.addVertex(var10, var11, var10);
            var23.addVertex(var10, var12, var10);
            var23.addVertex(-var10, var12, var10);
            var23.addVertex(-var10, var12, -var10);
            var23.addVertex(var10, var12, -var10);
            var23.addVertex(var10, var11, -var10);
            var23.addVertex(-var10, var11, -var10);
            var23.addVertex(var10, var12, -var10);
            var23.addVertex(var10, var12, var10);
            var23.addVertex(var10, var11, var10);
            var23.addVertex(var10, var11, -var10);
            var23.addVertex(-var10, var11, -var10);
            var23.addVertex(-var10, var11, var10);
            var23.addVertex(-var10, var12, var10);
            var23.addVertex(-var10, var12, -var10);
            var23.addVertex(-var10, var12, -var10);
            var23.addVertex(-var10, var12, var10);
            var23.addVertex(var10, var12, var10);
            var23.addVertex(var10, var12, -var10);
            var23.draw();
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -((float) (var25 - 16.0D)), 0.0F);
        GL11.glCallList(this.glSkyList2);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
    }

    private void renderStars()
    {
        Random var1 = new Random(10842L);
        Tessellator var2 = Tessellator.instance;
        var2.startDrawingQuads();

        for (int var3 = 0; var3 < (ConfigManagerCore.moreStars ? 60000 : 6000); ++var3)
        {
            double var4 = var1.nextFloat() * 2.0F - 1.0F;
            double var6 = var1.nextFloat() * 2.0F - 1.0F;
            double var8 = var1.nextFloat() * 2.0F - 1.0F;
            double var10 = 0.15F + var1.nextFloat() * 0.1F;
            double var12 = var4 * var4 + var6 * var6 + var8 * var8;

            if (var12 < 1.0D && var12 > 0.01D)
            {
                var12 = 1.0D / Math.sqrt(var12);
                var4 *= var12;
                var6 *= var12;
                var8 *= var12;
                double var14 = var4 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 100D + 150D : 100.0D);
                double var16 = var6 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 100D + 150D : 100.0D);
                double var18 = var8 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 100D + 150D : 100.0D);
                double var20 = Math.atan2(var4, var8);
                double var22 = Math.sin(var20);
                double var24 = Math.cos(var20);
                double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
                double var28 = Math.sin(var26);
                double var30 = Math.cos(var26);
                double var32 = var1.nextDouble() * Math.PI * 2.0D;
                double var34 = Math.sin(var32);
                double var36 = Math.cos(var32);

                for (int var38 = 0; var38 < 4; ++var38)
                {
                    double var39 = 0.0D;
                    double var41 = ((var38 & 2) - 1) * var10;
                    double var43 = ((var38 + 1 & 2) - 1) * var10;
                    double var47 = var41 * var36 - var43 * var34;
                    double var49 = var43 * var36 + var41 * var34;
                    double var53 = var47 * var28 + var39 * var30;
                    double var55 = var39 * var28 - var47 * var30;
                    double var57 = var55 * var22 - var49 * var24;
                    double var61 = var49 * var22 + var55 * var24;
                    var2.addVertex(var14 + var57, var16 + var53, var18 + var61);
                }
            }
        }
        var2.draw();
    }

    public float getSkyBrightness(float par1)
    {
        float var2 = FMLClientHandler.instance().getClient().theWorld.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.sin(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (var3 < 0.0F)
        {
            var3 = 0.0F;
        }
        if (var3 > 1.0F)
        {
            var3 = 1.0F;
        }
        return var3 * var3 * 1F;
    }
}