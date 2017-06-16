package stevekung.mods.moreplanets.asteroids.darkasteroids.client;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;

public class SkyProviderDarkAsteroids extends IRenderHandler
{
    //private ResourceLocation overworldTexture = new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/gui/celestialbodies/earth.png");
    private ResourceLocation sunTexture = new ResourceLocation("mpcore:textures/gui/celestialbodies/dark_star.png");

    public int starGLCallList = GLAllocation.generateDisplayLists(3);
    public int glSkyList;
    public int glSkyList2;
    private float sunSize;

    public SkyProviderDarkAsteroids(IGalacticraftWorldProvider asteroidsProvider)
    {
        this.sunSize = 17.5F * asteroidsProvider.getSolarSize();

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
        float var12;
        Tessellator var23 = Tessellator.instance;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor3f(1F, 1F, 1F);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(0, 0, 0);
        GL11.glCallList(this.glSkyList);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.disableStandardItemLighting();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(0.7F, 0.7F, 0.7F, 0.7F);
        GL11.glCallList(this.starGLCallList);

        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        // Sun:
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
        var12 = this.sunSize / 5.8F;
        var23.startDrawingQuads();
        var23.addVertex(-var12, 90.0D, -var12);
        var23.addVertex(var12, 90.0D, -var12);
        var23.addVertex(var12, 90.0D, var12);
        var23.addVertex(-var12, 90.0D, var12);
        var23.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        var12 = this.sunSize / 1.8F;
        //110 distance instead of the normal 100, because there is no atmosphere to make the disk seem larger
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.sunTexture);
        var23.startDrawingQuads();
        var23.addVertexWithUV(-var12, 90.0D, -var12, 0.0D, 0.0D);
        var23.addVertexWithUV(var12, 90.0D, -var12, 1.0D, 0.0D);
        var23.addVertexWithUV(var12, 90.0D, var12, 1.0D, 1.0D);
        var23.addVertexWithUV(-var12, 90.0D, var12, 0.0D, 1.0D);
        var23.draw();

        GL11.glPopMatrix();
        GL11.glPushMatrix();

        /*// HOME:
		var12 = 0.5F;
		GL11.glScalef(0.6F, 0.6F, 0.6F);
		GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(200F, 1.0F, 0.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.overworldTexture);
		var23.startDrawingQuads();
		var23.addVertexWithUV(-var12, -100.0D, var12, 0, 1);
		var23.addVertexWithUV(var12, -100.0D, var12, 1, 1);
		var23.addVertexWithUV(var12, -100.0D, -var12, 1, 0);
		var23.addVertexWithUV(-var12, -100.0D, -var12, 0, 0);
		var23.draw();*/

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        GL11.glColor3f(70F / 256F, 70F / 256F, 70F / 256F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glDisable(GL11.GL_FOG);
    }

    private void renderStars()
    {
        Random var1 = new Random(10842L);
        Tessellator var2 = Tessellator.instance;
        var2.startDrawingQuads();

        for (int var3 = 0; var3 < (ConfigManagerCore.moreStars ? 35000 : 6000); ++var3)
        {
            double var4 = var1.nextFloat() * 2.0F - 1.0F;
            double var6 = var1.nextFloat() * 2.0F - 1.0F;
            double var8 = var1.nextFloat() * 2.0F - 1.0F;
            double var10 = 0.08F + var1.nextFloat() * 0.07F;
            double var12 = var4 * var4 + var6 * var6 + var8 * var8;

            if (var12 < 1.0D && var12 > 0.01D)
            {
                var12 = 1.0D / Math.sqrt(var12);
                var4 *= var12;
                var6 *= var12;
                var8 *= var12;
                double pX = var4 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 75D + 65D : 80.0D);
                double pY = var6 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 75D + 65D : 80.0D);
                double pZ = var8 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 75D + 65D : 80.0D);
                double var20 = Math.atan2(var4, var8);
                double var22 = Math.sin(var20);
                double var24 = Math.cos(var20);
                double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
                double var28 = Math.sin(var26);
                double var30 = Math.cos(var26);
                double var32 = var1.nextDouble() * Math.PI * 2.0D;
                double var34 = Math.sin(var32);
                double var36 = Math.cos(var32);

                for (int i = 0; i < 4; ++i)
                {
                    double i1 = ((i & 2) - 1) * var10;
                    double i2 = ((i + 1 & 2) - 1) * var10;
                    double var47 = i1 * var36 - i2 * var34;
                    double var49 = i2 * var36 + i1 * var34;
                    double var55 = -var47 * var30;
                    double dX = var55 * var22 - var49 * var24;
                    double dZ = var49 * var22 + var55 * var24;
                    double dY = var47 * var28;
                    var2.addVertex(pX + dX, pY + dY, pZ + dZ);
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