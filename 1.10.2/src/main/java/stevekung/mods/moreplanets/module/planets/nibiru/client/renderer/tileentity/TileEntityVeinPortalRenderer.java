package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity;

import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityVeinPortal;

@SideOnly(Side.CLIENT)
public class TileEntityVeinPortalRenderer extends TileEntitySpecialRenderer<TileEntityVeinPortal>
{
    private static ResourceLocation END_SKY_TEXTURE = new ResourceLocation("textures/environment/end_sky.png");
    private static ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");
    private static ResourceLocation END_GATEWAY_BEAM_TEXTURE = new ResourceLocation("textures/entity/beacon_beam.png");
    private static Random RANDOM = new Random(31100L);
    private static FloatBuffer MODELVIEW = GLAllocation.createDirectFloatBuffer(16);
    private static FloatBuffer PROJECTION = GLAllocation.createDirectFloatBuffer(16);
    FloatBuffer buffer = GLAllocation.createDirectFloatBuffer(16);

    @Override
    public void renderTileEntityAt(TileEntityVeinPortal tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        GlStateManager.disableFog();

        if (tile.isMiddle)
        {
            int i = 0;
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer worldrenderer = tessellator.getBuffer();
            int k = i + 66;
            this.bindTexture(END_GATEWAY_BEAM_TEXTURE);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
            double d0 = tile.getWorld().getTotalWorldTime() + (double)partialTicks;
            double d1 = MathHelper.frac(-d0 * 0.2D - MathHelper.floor_double(-d0 * 0.1D));
            float f1 = 0.85F;
            float f2 = 0.35F;
            float f3 = 0.2F;
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
            double d15 = 8 * 8 * 2.5D + d14;
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos(x + d4, y + k, z + d5).tex(1.0D, d15).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d4, y + i, z + d5).tex(1.0D, d14).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d6, y + i, z + d7).tex(0.0D, d14).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d6, y + k, z + d7).tex(0.0D, d15).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d10, y + k, z + d11).tex(1.0D, d15).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d10, y + i, z + d11).tex(1.0D, d14).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d8, y + i, z + d9).tex(0.0D, d14).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d8, y + k, z + d9).tex(0.0D, d15).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d6, y + k, z + d7).tex(1.0D, d15).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d6, y + i, z + d7).tex(1.0D, d14).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d10, y + i, z + d11).tex(0.0D, d14).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d10, y + k, z + d11).tex(0.0D, d15).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d8, y + k, z + d9).tex(1.0D, d15).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d8, y + i, z + d9).tex(1.0D, d14).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d4, y + i, z + d5).tex(0.0D, d14).color(f1, f2, f3, 1.0F).endVertex();
            worldrenderer.pos(x + d4, y + k, z + d5).tex(0.0D, d15).color(f1, f2, f3, 1.0F).endVertex();
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
            d13 = 8 * 8 + d12;
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos(x + 0.2D, y + k, z + 0.2D).tex(1.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + i, z + 0.2D).tex(1.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + i, z + 0.2D).tex(0.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + k, z + 0.2D).tex(0.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + k, z + 0.8D).tex(1.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + i, z + 0.8D).tex(1.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + i, z + 0.8D).tex(0.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + k, z + 0.8D).tex(0.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + k, z + 0.2D).tex(1.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + i, z + 0.2D).tex(1.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + i, z + 0.8D).tex(0.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + k, z + 0.8D).tex(0.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + k, z + 0.8D).tex(1.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + i, z + 0.8D).tex(1.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + i, z + 0.2D).tex(0.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + k, z + 0.2D).tex(0.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            tessellator.draw();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.depthMask(true);
            i = k;
        }

        GlStateManager.disableLighting();
        RANDOM.setSeed(31100L);
        GlStateManager.getFloat(2982, MODELVIEW);
        GlStateManager.getFloat(2983, PROJECTION);
        double d1 = x * x + y * y + z * z;
        int i;

        if (d1 > 36864.0D)
        {
            i = 2;
        }
        else if (d1 > 25600.0D)
        {
            i = 4;
        }
        else if (d1 > 16384.0D)
        {
            i = 6;
        }
        else if (d1 > 9216.0D)
        {
            i = 8;
        }
        else if (d1 > 4096.0D)
        {
            i = 10;
        }
        else if (d1 > 1024.0D)
        {
            i = 12;
        }
        else if (d1 > 576.0D)
        {
            i = 14;
        }
        else if (d1 > 256.0D)
        {
            i = 15;
        }
        else
        {
            i = 16;
        }

        for (int k = 0; k < i; ++k)
        {
            GlStateManager.pushMatrix();
            float f5 = 2.0F / (18 - k);

            if (k == 0)
            {
                this.bindTexture(END_SKY_TEXTURE);
                f5 = 0.15F;
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(770, 771);
            }
            if (k >= 1)
            {
                this.bindTexture(END_PORTAL_TEXTURE);
            }
            if (k == 1)
            {
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(1, 1);
            }

            GlStateManager.texGen(GlStateManager.TexGen.S, 9216);
            GlStateManager.texGen(GlStateManager.TexGen.T, 9216);
            GlStateManager.texGen(GlStateManager.TexGen.R, 9216);
            GlStateManager.texGen(GlStateManager.TexGen.S, 9474, this.getBuffer(1.0F, 0.0F, 0.0F, 0.0F));
            GlStateManager.texGen(GlStateManager.TexGen.T, 9474, this.getBuffer(0.0F, 1.0F, 0.0F, 0.0F));
            GlStateManager.texGen(GlStateManager.TexGen.R, 9474, this.getBuffer(0.0F, 0.0F, 1.0F, 0.0F));
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.S);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.T);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.R);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.5F, 0.5F, 0.0F);
            GlStateManager.scale(0.5F, 0.5F, 1.0F);
            float f1 = k + 1;
            GlStateManager.translate(17.0F / f1, (2.0F + f1 / 1.5F) * (Minecraft.getSystemTime() % 800000.0F / 800000.0F), 0.0F);
            GlStateManager.rotate((f1 * f1 * 4321.0F + f1 * 9.0F) * 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.scale(4.5F - f1 / 4.0F, 4.5F - f1 / 4.0F, 1.0F);
            GlStateManager.multMatrix(PROJECTION);
            GlStateManager.multMatrix(MODELVIEW);
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer worldRenderer = tessellator.getBuffer();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            float f2 = RANDOM.nextFloat() * 0.85F * f5;
            float f3 = RANDOM.nextFloat() * 0.35F * f5;
            float f4 = RANDOM.nextFloat() * 0.2F * f5;

            if (k == 0)
            {
                f2 = f3 = f4 = 1.0F * f5;
            }

            worldRenderer.pos(x, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x + 1.0D, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
            worldRenderer.pos(x, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
            this.bindTexture(END_SKY_TEXTURE);
        }
        GlStateManager.disableBlend();
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.S);
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.T);
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.R);
        GlStateManager.enableLighting();
        GlStateManager.enableFog();
    }

    @Override
    public boolean isGlobalRenderer(TileEntityVeinPortal tile)
    {
        return true;
    }

    private FloatBuffer getBuffer(float p_188193_1_, float p_188193_2_, float p_188193_3_, float p_188193_4_)
    {
        this.buffer.clear();
        this.buffer.put(p_188193_1_).put(p_188193_2_).put(p_188193_3_).put(p_188193_4_);
        this.buffer.flip();
        return this.buffer;
    }
}