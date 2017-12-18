/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.spacestation.jupiter;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;

public class SkyProviderJupiterOrbit extends IRenderHandler
{
    private ResourceLocation moonTexture = new ResourceLocation("mpcore:textures/jupiter_phases.png");
    private ResourceLocation sunTexture = new ResourceLocation("galacticraftcore:textures/gui/planets/sun.png");

    public int starGLCallList = GLAllocation.generateDisplayLists(3);
    public int glSkyList;
    public int glSkyList2;
    private boolean renderSun;
    private float spinAngle = 0;
    public float spinDeltaPerTick = 0;
    private float prevPartialTicks = 0;
    private long prevTick;

    public SkyProviderJupiterOrbit()
    {
        this.renderSun = true;
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
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.enableStandardItemLighting();
        Vec3 var2 = mc.theWorld.getSkyColor(mc.renderViewEntity, partialTicks);
        float var3 = (float) var2.xCoord;
        float var4 = (float) var2.yCoord;
        float var5 = (float) var2.zCoord;
        float var8;

        if (mc.gameSettings.anaglyph)
        {
            float var6 = (var3 * 30.0F + var4 * 59.0F + var5 * 11.0F) / 100.0F;
            float var7 = (var3 * 30.0F + var4 * 70.0F) / 100.0F;
            var8 = (var3 * 30.0F + var5 * 70.0F) / 100.0F;
            var3 = var6;
            var4 = var7;
            var5 = var8;
        }

        GL11.glColor3f(var3, var4, var5);
        Tessellator var23 = Tessellator.instance;
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(var3, var4, var5);
        GL11.glCallList(this.glSkyList);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.disableStandardItemLighting();
        float[] var24 = mc.theWorld.provider.calcSunriseSunsetColors(mc.theWorld.getCelestialAngle(partialTicks), partialTicks);
        float var9;
        float var10;
        float var11;
        float var12;

        if (var24 != null)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(MathHelper.sin(mc.theWorld.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            var8 = var24[0];
            var9 = var24[1];
            var10 = var24[2];
            float var13;

            if (mc.gameSettings.anaglyph)
            {
                var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
                var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
                var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
                var8 = var11;
                var9 = var12;
                var10 = var13;
            }

            var23.startDrawing(6);
            var23.setColorRGBA_F(var8, var9, var10, var24[3]);
            var23.addVertex(0.0D, 100.0D, 0.0D);
            byte var26 = 16;
            var23.setColorRGBA_F(var24[0], var24[1], var24[2], 0.0F);

            for (int var27 = 0; var27 <= var26; ++var27)
            {
                var13 = var27 * (float) Math.PI * 2.0F / var26;
                float var14 = MathHelper.sin(var13);
                float var15 = MathHelper.cos(var13);
                var23.addVertex(var14 * 120.0F, var15 * 120.0F, -var15 * 40.0F * var24[3]);
            }
            var23.draw();
            GL11.glPopMatrix();
            GL11.glShadeModel(GL11.GL_FLAT);
        }

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glPushMatrix();
        var8 = 1.0F - mc.theWorld.getRainStrength(partialTicks);
        var9 = 0.0F;
        var10 = 0.0F;
        var11 = 0.0F;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, var8);
        GL11.glTranslatef(var9, var10, var11);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);

        //Code for rendering spinning space stations
        float deltaTick = partialTicks - this.prevPartialTicks;
        this.prevPartialTicks = partialTicks;
        long curTick = mc.theWorld.getTotalWorldTime();
        int tickDiff = (int) (curTick - this.prevTick);
        this.prevTick = curTick;

        if (tickDiff > 0 && tickDiff < 20)
        {
            deltaTick += tickDiff;
        }

        this.spinAngle = this.spinAngle - this.spinDeltaPerTick * deltaTick;

        while (this.spinAngle < -180F)
        {
            this.spinAngle += 360F;
        }
        GL11.glRotatef(this.spinAngle, 0.0F, 1.0F, 0.0F);

        //At 0.8, these will look bright against a black sky - allows some headroom for them to
        //look even brighter in outer dimensions (further from the sun)
        GL11.glColor4f(0.8F, 0.8F, 0.8F, 0.8F);
        GL11.glCallList(this.starGLCallList);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPushMatrix();
        GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);

        if (this.renderSun)
        {
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
            var12 = 3.8F;
            var23.startDrawingQuads();
            var23.addVertex(-var12, 99.9D, -var12);
            var23.addVertex(var12, 99.9D, -var12);
            var23.addVertex(var12, 99.9D, var12);
            var23.addVertex(-var12, 99.9D, var12);
            var23.draw();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            var12 = 2.0F;
            mc.renderEngine.bindTexture(this.sunTexture);
            var23.startDrawingQuads();
            var23.addVertexWithUV(-var12, 100.0D, -var12, 0.0D, 0.0D);
            var23.addVertexWithUV(var12, 100.0D, -var12, 1.0D, 0.0D);
            var23.addVertexWithUV(var12, 100.0D, var12, 1.0D, 1.0D);
            var23.addVertexWithUV(-var12, 100.0D, var12, 0.0D, 1.0D);
            var23.draw();
        }

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);

        // Jupiter
        float size = 80.0F;
        GL11.glTranslatef(70.0F, 0.0F, 0.0F);
        GL11.glRotatef(-90.0F, 0.0F, 90.0F, 1.0F);
        GL11.glRotatef(95F, 1.0F, 0.0F, 0.015F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
        mc.renderEngine.bindTexture(this.moonTexture);
        float alpha = 0.75F;
        GL11.glColor4f(Math.min(alpha, 1.0F), Math.min(alpha, 1.0F), Math.min(alpha, 1.0F), Math.min(alpha, 1.0F));
        float var28 = (int)(mc.theWorld.getWorldTime() / 512L % 8L + 8L) % 8;/*mc.theWorld.getMoonPhase();*/
        int var30 = (int) (var28 % 4);
        int var29 = (int) (var28 / 4 % 2);
        float var16 = (var30 + 0) / 4.0F;
        float var17 = (var29 + 0) / 2.0F;
        float var18 = (var30 + 1) / 4.0F;
        float var19 = (var29 + 1) / 2.0F;
        var23.startDrawingQuads();
        var23.addVertexWithUV(-size, -100.0D, size, var18, var19);
        var23.addVertexWithUV(size, -100.0D, size, var16, var19);
        var23.addVertexWithUV(size, -100.0D, -size, var16, var17);
        var23.addVertexWithUV(-size, -100.0D, -size, var18, var17);
        var23.draw();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
    }

    private void renderStars()
    {
        Random var1 = new Random(10842L);
        Tessellator var2 = Tessellator.instance;
        var2.startDrawingQuads();

        for (int var3 = 0; var3 < (ConfigManagerCore.moreStars ? 40000 : 6000); ++var3)
        {
            double var4 = var1.nextFloat() * 2.0F - 1.0F;
            double var6 = var1.nextFloat() * 2.0F - 1.0F;
            double var8 = var1.nextFloat() * 2.0F - 1.0F;
            double var10 = 0.07F + var1.nextFloat() * 0.06F;
            double var12 = var4 * var4 + var6 * var6 + var8 * var8;

            if (var12 < 1.0D && var12 > 0.01D)
            {
                var12 = 1.0D / Math.sqrt(var12);
                var4 *= var12;
                var6 *= var12;
                var8 *= var12;
                double var14 = var4 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 50D + 75D : 50.0D);
                double var16 = var6 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 50D + 75D : 50.0D);
                double var18 = var8 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 50D + 75D : 50.0D);
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
}