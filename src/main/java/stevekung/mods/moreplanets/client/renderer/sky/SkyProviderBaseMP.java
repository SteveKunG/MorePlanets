package stevekung.mods.moreplanets.client.renderer.sky;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class SkyProviderBaseMP extends IRenderHandler
{
    protected int starList;
    protected int glSkyList;
    protected int glSkyList2;
    protected float sunSize;

    public SkyProviderBaseMP()
    {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldrenderer = tessellator.getBuffer();
        int displayLists = GLAllocation.generateDisplayLists(3);
        this.starList = displayLists;
        this.glSkyList = displayLists + 1;
        this.glSkyList2 = displayLists + 2;

        GlStateManager.pushMatrix();
        GlStateManager.glNewList(this.starList, 4864);
        this.renderStars();
        GlStateManager.glEndList();
        GlStateManager.popMatrix();

        this.glSkyList = this.starList + 1;
        GlStateManager.glNewList(this.glSkyList, 4864);
        byte byte2 = 64;
        int i = 256 / byte2 + 2;
        float f = 16F;

        for (int j = -byte2 * i; j <= byte2 * i; j += byte2)
        {
            for (int l = -byte2 * i; l <= byte2 * i; l += byte2)
            {
                worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                worldrenderer.pos(j + 0, f, l + 0).endVertex();
                worldrenderer.pos(j + byte2, f, l + 0).endVertex();
                worldrenderer.pos(j + byte2, f, l + byte2).endVertex();
                worldrenderer.pos(j + 0, f, l + byte2).endVertex();
                tessellator.draw();
            }
        }
        GlStateManager.glEndList();

        this.glSkyList2 = this.starList + 2;
        GlStateManager.glNewList(this.glSkyList2, 4864);
        f = -16F;
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);

        for (int k = -byte2 * i; k <= byte2 * i; k += byte2)
        {
            for (int i1 = -byte2 * i; i1 <= byte2 * i; i1 += byte2)
            {
                worldrenderer.pos(k + byte2, f, i1 + 0).endVertex();
                worldrenderer.pos(k + 0, f, i1 + 0).endVertex();
                worldrenderer.pos(k + 0, f, i1 + byte2).endVertex();
                worldrenderer.pos(k + byte2, f, i1 + byte2).endVertex();
            }
        }
        tessellator.draw();
        GlStateManager.glEndList();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(float partialTicks, WorldClient world, Minecraft mc)
    {
        Vec3d vec3 = world.getSkyColor(mc.getRenderViewEntity(), partialTicks);
        float red = (float)vec3.xCoord;
        float green = (float)vec3.yCoord;
        float blue = (float)vec3.zCoord;

        GlStateManager.disableTexture2D();
        GlStateManager.disableRescaleNormal();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.color(red, green, blue);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldrenderer = tessellator.getBuffer();
        GlStateManager.depthMask(false);
        GlStateManager.enableFog();
        GlStateManager.color(red, green, blue);
        GlStateManager.callList(this.glSkyList);
        GlStateManager.disableFog();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.disableStandardItemLighting();
        float star = world.getStarBrightness(partialTicks);
        float[] custom = this.getStarBrightness();
        float rain = this.getRainStrength(world, partialTicks);

        if (star > 0.0F)
        {
            GlStateManager.pushMatrix();
            GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-19.0F, 0.0F, 1.0F, 0.0F);

            if (this.useDefaultStarBrightness() && this.getStarBrightness() == null)
            {
                GlStateManager.color(star - rain, star - rain, star - rain, star - rain);
            }
            else
            {
                GlStateManager.color(custom[0] - rain, custom[0] - rain, custom[0] - rain, custom[1] * world.getStarBrightness(partialTicks) / 0.25F - rain);
            }
            GlStateManager.callList(this.starList);
            GlStateManager.popMatrix();
        }

        GlStateManager.pushMatrix();
        this.renderPlanetInSky(partialTicks, world, mc);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableFog();
        GlStateManager.popMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.color(0.0F, 0.0F, 0.0F);
        double playerEyes = mc.thePlayer.getPositionEyes(partialTicks).yCoord - world.getHorizon();

        if (playerEyes < 0.0D)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 12.0F, 0.0F);
            GlStateManager.callList(this.glSkyList2);
            GlStateManager.popMatrix();
            float f = -((float)(playerEyes + 65.0D));
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldrenderer.pos(-1.0D, f, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, f, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, f, -1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(-1.0D, f, -1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, f, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, f, -1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(-1.0D, f, -1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(-1.0D, f, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
        }
        GlStateManager.color(red, green, blue);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, -((float)(playerEyes - 16.0D)), 0.0F);
        GlStateManager.callList(this.glSkyList2);
        GlStateManager.popMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.disableFog();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.depthMask(true);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.disableBlend();
    }

    private void renderStars()
    {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldrenderer = tessellator.getBuffer();
        Random rand = new Random(10842L);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);

        for (int starIndex = 0; starIndex < (ConfigManagerCore.moreStars ? this.getMaxStarCount()[0] : 6000); ++starIndex)
        {
            double d0 = rand.nextFloat() * 2.0F - 1.0F;
            double d1 = rand.nextFloat() * 2.0F - 1.0F;
            double d2 = rand.nextFloat() * 2.0F - 1.0F;
            double d3 = 0.15F + rand.nextFloat() * 0.1F;
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d4 < 1.0D && d4 > 0.01D)
            {
                d4 = 1.0D / Math.sqrt(d4);
                d0 *= d4;
                d1 *= d4;
                d2 *= d4;
                double d5 = d0 * (ConfigManagerCore.moreStars ? rand.nextDouble() * this.getMaxStarCount()[1] + this.getMaxStarCount()[2] : 100.0D);
                double d6 = d1 * (ConfigManagerCore.moreStars ? rand.nextDouble() * this.getMaxStarCount()[1] + this.getMaxStarCount()[2] : 100.0D);
                double d7 = d2 * (ConfigManagerCore.moreStars ? rand.nextDouble() * this.getMaxStarCount()[1] + this.getMaxStarCount()[2] : 100.0D);
                double d8 = Math.atan2(d0, d2);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = rand.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                for (int j = 0; j < 4; ++j)
                {
                    double d18 = ((j & 2) - 1) * d3;
                    double d19 = ((j + 1 & 2) - 1) * d3;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12 + 0.0D * d13;
                    double d24 = 0.0D * d12 - d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    worldrenderer.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
                }
            }
        }
        tessellator.draw();
    }

    protected abstract void renderPlanetInSky(float partialTicks, WorldClient world, Minecraft mc);
    protected abstract double[] getMaxStarCount();
    protected abstract float[] getStarBrightness();
    protected abstract float getRainStrength(World world, float partialTicks);
    protected abstract boolean useDefaultStarBrightness();
}