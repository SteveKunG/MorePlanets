package stevekung.mods.moreplanets.module.planets.nibiru.client.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.IRenderHandler;

public class CloudRendererNibiru extends IRenderHandler
{
    private static ResourceLocation locationCloudsPng = new ResourceLocation("textures/environment/clouds.png");
    private int cloudTickCounter = 0;
    public static CloudRendererNibiru INSTANCE = new CloudRendererNibiru();

    public CloudRendererNibiru()
    {
        CloudRendererNibiru.INSTANCE = this;
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc)
    {
        GlStateManager.disableCull();
        float f1 = (float)(mc.getRenderViewEntity().lastTickPosY + (mc.getRenderViewEntity().posY - mc.getRenderViewEntity().lastTickPosY) * partialTicks);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldrenderer = tessellator.getBuffer();
        float f2 = 12.0F;
        float f3 = 4.0F;
        mc.renderEngine.bindTexture(locationCloudsPng);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        byte b0 = 8;
        byte b1 = 4;
        float f13 = 9.765625E-4F;

        for (int count = 0; count < 2; ++count)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(f2 + count * 5, 1.0F, f2 + count * 5);

            for (int k = 0; k < 2; ++k)
            {
                if (k == 0)
                {
                    GlStateManager.colorMask(false, false, false, false);
                }
                else if (mc.gameSettings.anaglyph)
                {
                    if (EntityRenderer.anaglyphField == 0)
                    {
                        GlStateManager.colorMask(false, true, true, true);
                    }
                    else
                    {
                        GlStateManager.colorMask(true, false, false, true);
                    }
                }
                else
                {
                    GlStateManager.colorMask(true, true, true, true);
                }

                double d0 = this.cloudTickCounter * (1.0F - count / 3.0F) + count / 5.0F * partialTicks + count * 300000;
                double d1 = (mc.getRenderViewEntity().prevPosX + (mc.getRenderViewEntity().posX - mc.getRenderViewEntity().prevPosX) * partialTicks + d0 * 0.029999999329447746D) / f2;
                double d2 = (mc.getRenderViewEntity().prevPosZ + (mc.getRenderViewEntity().posZ - mc.getRenderViewEntity().prevPosZ) * partialTicks) / f2 + 0.33000001311302185D;
                float f4 = mc.theWorld.provider.getCloudHeight() - f1 + 0.33F + count * 20.0F;
                int i = MathHelper.floor_double(d1 / 2048.0D);
                int j = MathHelper.floor_double(d2 / 2048.0D);
                d1 -= i * 2048;
                d2 -= j * 2048;
                float celes = 1.0F - mc.theWorld.provider.getStarBrightness(partialTicks);
                float f5 = 216.0F / (400.0F + count * 200.0F) * celes;
                float f6 = 105.0F / (400.0F + count * 200.0F) * celes;
                float f7 = 39.0F / (400.0F + count * 200.0F) * celes;
                float f8;
                float f9;
                float f10;

                if (mc.gameSettings.anaglyph)
                {
                    f8 = (f5 * 30.0F + f6 * 59.0F + f7 * 11.0F) / 100.0F;
                    f9 = (f5 * 30.0F + f6 * 70.0F) / 100.0F;
                    f10 = (f5 * 30.0F + f7 * 70.0F) / 100.0F;
                    f5 = f8;
                    f6 = f9;
                    f7 = f10;
                }

                f8 = (float)(d1 * 0.0D);
                f9 = (float)(d2 * 0.0D);
                f10 = 0.00390625F;
                f8 = MathHelper.floor_double(d1) * f10;
                f9 = MathHelper.floor_double(d2) * f10;
                float f11 = (float)(d1 - MathHelper.floor_double(d1));
                float f12 = (float)(d2 - MathHelper.floor_double(d2));

                for (int l = -b1 + 1; l <= b1; ++l)
                {
                    for (int i1 = -b1 + 1; i1 <= b1; ++i1)
                    {
                        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
                        float f14 = l * b0;
                        float f15 = i1 * b0;
                        float f16 = f14 - f11;
                        float f17 = f15 - f12;

                        if (f4 > -f3 - 1.0F)
                        {
                            worldrenderer.pos(f16 + 0.0F, f4 + 0.0F, f17 + b0).tex((f14 + 0.0F) * f10 + f8, (f15 + b0) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, -1.0F, 0.0F).endVertex();
                            worldrenderer.pos(f16 + b0, f4 + 0.0F, f17 + b0).tex((f14 + b0) * f10 + f8, (f15 + b0) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, -1.0F, 0.0F).endVertex();
                            worldrenderer.pos(f16 + b0, f4 + 0.0F, f17 + 0.0F).tex((f14 + b0) * f10 + f8, (f15 + 0.0F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, -1.0F, 0.0F).endVertex();
                            worldrenderer.pos(f16 + 0.0F, f4 + 0.0F, f17 + 0.0F).tex((f14 + 0.0F) * f10 + f8, (f15 + 0.0F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, -1.0F, 0.0F).endVertex();
                        }

                        if (f4 <= f3 + 1.0F)
                        {
                            worldrenderer.pos(f16 + 0.0F, f4 + f3 - f13, f17 + b0).tex((f14 + 0.0F) * f10 + f8, (f15 + b0) * f10 + f9).color(f5, f6, f7, 0.9F).normal(0.0F, 1.0F, 0.0F).endVertex();
                            worldrenderer.pos(f16 + b0, f4 + f3 - f13, f17 + b0).tex((f14 + b0) * f10 + f8, (f15 + b0) * f10 + f9).color(f5, f6, f7, 0.9F).normal(0.0F, 1.0F, 0.0F).endVertex();
                            worldrenderer.pos(f16 + b0, f4 + f3 - f13, f17 + 0.0F).tex((f14 + b0) * f10 + f8, (f15 + 0.0F) * f10 + f9).color(f5, f6, f7, 0.9F).normal(0.0F, 1.0F, 0.0F).endVertex();
                            worldrenderer.pos(f16 + 0.0F, f4 + f3 - f13, f17 + 0.0F).tex((f14 + 0.0F) * f10 + f8, (f15 + 0.0F) * f10 + f9).color(f5, f6, f7, 0.9F).normal(0.0F, 1.0F, 0.0F).endVertex();
                        }

                        int j1;

                        if (l > -1)
                        {
                            for (j1 = 0; j1 < b0; ++j1)
                            {
                                worldrenderer.pos(f16 + j1 + 0.0F, f4 + 0.0F, f17 + b0).tex((f14 + j1 + 0.5F) * f10 + f8, (f15 + b0) * f10 + f9).color(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.9F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                                worldrenderer.pos(f16 + j1 + 0.0F, f4 + f3, f17 + b0).tex((f14 + j1 + 0.5F) * f10 + f8, (f15 + b0) * f10 + f9).color(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.9F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                                worldrenderer.pos(f16 + j1 + 0.0F, f4 + f3, f17 + 0.0F).tex((f14 + j1 + 0.5F) * f10 + f8, (f15 + 0.0F) * f10 + f9).color(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.9F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                                worldrenderer.pos(f16 + j1 + 0.0F, f4 + 0.0F, f17 + 0.0F).tex((f14 + j1 + 0.5F) * f10 + f8, (f15 + 0.0F) * f10 + f9).color(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.9F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            }
                        }

                        if (l <= 1)
                        {
                            for (j1 = 0; j1 < b0; ++j1)
                            {
                                worldrenderer.pos(f16 + j1 + 1.0F - f13, f4 + 0.0F, f17 + b0).tex((f14 + j1 + 0.5F) * f10 + f8, (f15 + b0) * f10 + f9).color(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.9F).normal(1.0F, 0.0F, 0.0F).endVertex();
                                worldrenderer.pos(f16 + j1 + 1.0F - f13, f4 + f3, f17 + b0).tex((f14 + j1 + 0.5F) * f10 + f8, (f15 + b0) * f10 + f9).color(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.9F).normal(1.0F, 0.0F, 0.0F).endVertex();
                                worldrenderer.pos(f16 + j1 + 1.0F - f13, f4 + f3, f17 + 0.0F).tex((f14 + j1 + 0.5F) * f10 + f8, (f15 + 0.0F) * f10 + f9).color(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.9F).normal(1.0F, 0.0F, 0.0F).endVertex();
                                worldrenderer.pos(f16 + j1 + 1.0F - f13, f4 + 0.0F, f17 + 0.0F).tex((f14 + j1 + 0.5F) * f10 + f8, (f15 + 0.0F) * f10 + f9).color(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.9F).normal(1.0F, 0.0F, 0.0F).endVertex();
                            }
                        }

                        if (i1 > -1)
                        {
                            for (j1 = 0; j1 < b0; ++j1)
                            {
                                worldrenderer.pos(f16 + 0.0F, f4 + f3, f17 + j1 + 0.0F).tex((f14 + 0.0F) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, 0.0F, -1.0F).endVertex();
                                worldrenderer.pos(f16 + b0, f4 + f3, f17 + j1 + 0.0F).tex((f14 + b0) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, 0.0F, -1.0F).endVertex();
                                worldrenderer.pos(f16 + b0, f4 + 0.0F, f17 + j1 + 0.0F).tex((f14 + b0) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, 0.0F, -1.0F).endVertex();
                                worldrenderer.pos(f16 + 0.0F, f4 + 0.0F, f17 + j1 + 0.0F).tex((f14 + 0.0F) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, 0.0F, -1.0F).endVertex();
                            }
                        }

                        if (i1 <= 1)
                        {
                            for (j1 = 0; j1 < b0; ++j1)
                            {
                                worldrenderer.pos(f16 + 0.0F, f4 + f3, f17 + j1 + 1.0F - f13).tex((f14 + 0.0F) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, 0.0F, 1.0F).endVertex();
                                worldrenderer.pos(f16 + b0, f4 + f3, f17 + j1 + 1.0F - f13).tex((f14 + b0) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, 0.0F, 1.0F).endVertex();
                                worldrenderer.pos(f16 + b0, f4 + 0.0F, f17 + j1 + 1.0F - f13).tex((f14 + b0) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, 0.0F, 1.0F).endVertex();
                                worldrenderer.pos(f16 + 0.0F, f4 + 0.0F, f17 + j1 + 1.0F - f13).tex((f14 + 0.0F) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9).color(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.9F).normal(0.0F, 0.0F, 1.0F).endVertex();
                            }
                        }
                        tessellator.draw();
                    }
                }
            }
            GlStateManager.popMatrix();
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
    }

    public void runRenderTick()
    {
        Minecraft mc = Minecraft.getMinecraft();

        if (!mc.isGamePaused())
        {
            if (mc.theWorld != null)
            {
                if (mc.theWorld.isThundering())
                {
                    this.cloudTickCounter += 6;
                }
                else
                {
                    this.cloudTickCounter += 4;
                }
            }
        }
    }
}