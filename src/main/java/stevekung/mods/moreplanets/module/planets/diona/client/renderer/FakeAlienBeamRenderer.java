package stevekung.mods.moreplanets.module.planets.diona.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.utils.RenderUtils;

@SideOnly(Side.CLIENT)
public class FakeAlienBeamRenderer
{
    public static final FakeAlienBeamRenderer INSTANCE = new FakeAlienBeamRenderer();
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/beacon_beam.png");
    private float prevTime;
    private float time;

    public void renderBeam(double x, double y, double z, float partialTicks)
    {
        this.prevTime = this.time;
        this.time += 0.0125F;

        if (this.time >= 1.0F)
        {
            this.time = 0.0F;
        }
        if (this.time < 0.0F)
        {
            this.time = 0.0F;
        }

        float beamTime = this.prevTime + (this.time - this.prevTime) * partialTicks;

        if (beamTime > 0.0F)
        {
            if (beamTime < 1.0F)
            {
                beamTime = beamTime * beamTime;
                beamTime = beamTime * beamTime;
                beamTime = beamTime * 0.8F + 0.2F;
            }
        }

        int k = 256;
        GlStateManager.alphaFunc(516, 0.1F);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        GlStateManager.disableFog();
        RenderUtils.bindTexture(FakeAlienBeamRenderer.TEXTURE);
        GlStateManager.glTexParameterf(3553, 10242, 10497.0F);
        GlStateManager.glTexParameterf(3553, 10243, 10497.0F);
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.blendFunc(770, 1);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        double d0 = (double)Minecraft.getMinecraft().world.getTotalWorldTime() + (double)partialTicks;
        double d1 = MathHelper.frac(-d0 * 0.2D - MathHelper.floor(-d0 * 0.1D));
        float red = 0.4F;
        float green = 0.6F;
        float blue = 1.0F;
        float alpha = beamTime;
        double d2 = d0 * 0.025D * -1.5D;
        double d4 = 0.5D + Math.cos(d2 + 2.356194490192345D) * 0.2D;
        double d5 = 0.5D + Math.sin(d2 + 2.356194490192345D) * 0.2D;
        double d6 = 0.5D + Math.cos(d2 + Math.PI / 4D) * 0.2D;
        double d7 = 0.5D + Math.sin(d2 + Math.PI / 4D) * 0.2D;
        double d8 = 0.5D + Math.cos(d2 + 3.9269908169872414D) * 0.2D;
        double d9 = 0.5D + Math.sin(d2 + 3.9269908169872414D) * 0.2D;
        double d10 = 0.5D + Math.cos(d2 + 5.497787143782138D) * 0.2D;
        double d11 = 0.5D + Math.sin(d2 + 5.497787143782138D) * 0.2D;
        double d12 = 0.0D;
        double d13 = 1.0D;
        double d14 = -1.0D + d1;
        double d15 = (float)512 * 512 * 2.5D + d14;
        GlStateManager.enableBlend();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldrenderer.pos(x + d4, y + k, z + d5).tex(1.0D, d15).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d4, y, z + d5).tex(1.0D, d14).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d6, y, z + d7).tex(0.0D, d14).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d6, y + k, z + d7).tex(0.0D, d15).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d10, y + k, z + d11).tex(1.0D, d15).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d10, y, z + d11).tex(1.0D, d14).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d8, y, z + d9).tex(0.0D, d14).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d8, y + k, z + d9).tex(0.0D, d15).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d6, y + k, z + d7).tex(1.0D, d15).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d6, y, z + d7).tex(1.0D, d14).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d10, y, z + d11).tex(0.0D, d14).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d10, y + k, z + d11).tex(0.0D, d15).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d8, y + k, z + d9).tex(1.0D, d15).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d8, y, z + d9).tex(1.0D, d14).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d4, y, z + d5).tex(0.0D, d14).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(x + d4, y + k, z + d5).tex(0.0D, d15).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.depthMask(false);
        d2 = 0.2D;
        d4 = 0.8D;
        d5 = 0.2D;
        d6 = 0.2D;
        d7 = 0.8D;
        d8 = 0.8D;
        d9 = 0.8D;
        d10 = 0.0D;
        d11 = 1.0D;
        d12 = -1.0D + d1;
        d13 = (float)512 * 512 + d12;
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldrenderer.pos(x + 0.2D, y + k, z + 0.2D).tex(1.0D, d13).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.2D, y, z + 0.2D).tex(1.0D, d12).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.8D, y, z + 0.2D).tex(0.0D, d12).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.8D, y + k, z + 0.2D).tex(0.0D, d13).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.8D, y + k, z + 0.8D).tex(1.0D, d13).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.8D, y, z + 0.8D).tex(1.0D, d12).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.2D, y, z + 0.8D).tex(0.0D, d12).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.2D, y + k, z + 0.8D).tex(0.0D, d13).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.8D, y + k, z + 0.2D).tex(1.0D, d13).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.8D, y, z + 0.2D).tex(1.0D, d12).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.8D, y, z + 0.8D).tex(0.0D, d12).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.8D, y + k, z + 0.8D).tex(0.0D, d13).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.2D, y + k, z + 0.8D).tex(1.0D, d13).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.2D, y, z + 0.8D).tex(1.0D, d12).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.2D, y, z + 0.2D).tex(0.0D, d12).color(red, green, blue, 0.125F).endVertex();
        worldrenderer.pos(x + 0.2D, y + k, z + 0.2D).tex(0.0D, d13).color(red, green, blue, 0.125F).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        GlStateManager.enableFog();
    }
}