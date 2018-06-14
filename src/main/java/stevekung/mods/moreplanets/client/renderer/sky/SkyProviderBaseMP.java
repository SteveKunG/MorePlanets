package stevekung.mods.moreplanets.client.renderer.sky;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.client.GLConstants;

public abstract class SkyProviderBaseMP extends IRenderHandler
{
    private static final String[] starColorHexTable = {"#9bb2ff", "#9eb5ff", "#a3b9ff", "#aabffir", "#b2c5ff", "#bbccfr", "#c4d2ff", "#ccd8ff", "#d3ddff", "#dae2ff", "#dfe5ff", "#e4e9ff", "#e9ecif", "#eeefff", "#f3f2ff", "#f8f6ff", "#fef9ff", "Off9fb", "#fff7f5", "#ffif5ef", "#fff3ea", "#fffle5", "#ffefe0", "#ffeddb", "#ffebd6", "#ffe9d2", "#ffe8ce", "#ffe6ca", "#ffe5c6", "#ffe3c3", "#ffe2bf", "#ffeObb", "#ffdfb8", "#ffddb4", "#ffdbb0", "#ffdaad", "#ffd8a9", "#ffd6a5", "#ffd5a1", "#ffd29c", "#ffd096", "#ffcc8f", "#ffc885", "#ffc178", "#ffb765", "#ffa94b", "#ff9523"};
    private int starList;
    private int glSkyList;
    private int glSkyList2;
    protected float solarSize;
    protected boolean hasRain;

    public SkyProviderBaseMP()
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
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
                buffer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION);
                buffer.pos(j + 0, f, l + 0).endVertex();
                buffer.pos(j + byte2, f, l + 0).endVertex();
                buffer.pos(j + byte2, f, l + byte2).endVertex();
                buffer.pos(j + 0, f, l + byte2).endVertex();
                tessellator.draw();
            }
        }
        GlStateManager.glEndList();

        this.glSkyList2 = this.starList + 2;
        GlStateManager.glNewList(this.glSkyList2, 4864);
        f = -16F;
        buffer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION);

        for (int k = -byte2 * i; k <= byte2 * i; k += byte2)
        {
            for (int i1 = -byte2 * i; i1 <= byte2 * i; i1 += byte2)
            {
                buffer.pos(k + byte2, f, i1 + 0).endVertex();
                buffer.pos(k + 0, f, i1 + 0).endVertex();
                buffer.pos(k + 0, f, i1 + byte2).endVertex();
                buffer.pos(k + byte2, f, i1 + byte2).endVertex();
            }
        }
        tessellator.draw();
        GlStateManager.glEndList();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(float partialTicks, WorldClient world, Minecraft mc)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        Vec3d vec3 = world.getSkyColor(mc.getRenderViewEntity(), partialTicks);
        float red = (float)vec3.x;
        float green = (float)vec3.y;
        float blue = (float)vec3.z;

        GlStateManager.disableTexture2D();
        GlStateManager.disableRescaleNormal();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.color(red, green, blue);
        GlStateManager.depthMask(false);
        GlStateManager.enableFog();
        GlStateManager.color(red, green, blue);
        GlStateManager.callList(this.glSkyList);
        GlStateManager.disableFog();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderHelper.disableStandardItemLighting();

        // sunset colors
        float[] sunsetColors = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);

        if (sunsetColors != null)
        {
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(MathHelper.sin(world.getCelestialAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            float sunRed = sunsetColors[0];
            float sunGreen = sunsetColors[1];
            float sunBlue = sunsetColors[2];
            float sunAlpha = sunsetColors[3];
            buffer.begin(GLConstants.TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
            buffer.pos(0.0D, 100.0D, 0.0D).color(sunRed, sunGreen, sunBlue, sunAlpha).endVertex();

            for (int j2 = 0; j2 <= 16; ++j2)
            {
                float f21 = j2 * ((float)Math.PI * 2F) / 16.0F;
                float f12 = MathHelper.sin(f21);
                float f13 = MathHelper.cos(f21);
                buffer.pos(f12 * 120.0F, f13 * 120.0F, -f13 * 40.0F * sunAlpha).color(sunRed, sunGreen, sunBlue, 0.0F).endVertex();
            }
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.shadeModel(7424);
        }

        GlStateManager.pushMatrix();

        float rainStrength = 0.0F;

        // rain strength
        if (this.hasRain)
        {
            rainStrength = 1.0F - world.getRainStrength(partialTicks);
            GlStateManager.color(1.0F, 1.0F, 1.0F, rainStrength);
        }

        // star brightness
        float starBrightness = this.hasRain ? world.getStarBrightness(partialTicks) * rainStrength : world.getStarBrightness(partialTicks);

        if (starBrightness > 0.0F)
        {
            GlStateManager.pushMatrix();
            GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-19.0F, 0.0F, 1.0F, 0.0F);
            this.renderStars(starBrightness);//TODO Fix star alpha
            GlStateManager.callList(this.starList);
            GlStateManager.popMatrix();
        }

        // sunset facing
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);

        // sky rotation
        GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);

        // render additional objects
        this.renderObjects(partialTicks, world, mc);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableFog();
        GlStateManager.popMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.color(0.0F, 0.0F, 0.0F);
        double playerHorizon = mc.player.getPositionEyes(partialTicks).y - world.getHorizon();

        if (playerHorizon < 0.0D)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 12.0F, 0.0F);
            GlStateManager.callList(this.glSkyList2);
            GlStateManager.popMatrix();
            float f19 = -((float)(playerHorizon + 65.0D));
            buffer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_COLOR);
            buffer.pos(-1.0D, f19, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, f19, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, f19, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, f19, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, f19, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, f19, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, f19, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, f19, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
        }

        GlStateManager.color(red, green, blue);//sky color
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, -((float)(playerHorizon - 16.0D)), 0.0F);
        GlStateManager.callList(this.glSkyList2);
        GlStateManager.popMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    }

    private void renderStars()
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        Random rand = new Random(10842L);
        buffer.begin(GLConstants.QUADS, ConfigManagerMP.moreplanets_general.useColoredStar || ConfigManagerMP.moreplanets_general.useFancyStar ? DefaultVertexFormats.POSITION_COLOR : DefaultVertexFormats.POSITION);

        for (int i = 0; i < this.getStarCount(); ++i)
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
                double d5 = d0 * (rand.nextDouble() * this.getStarSpreadMultiplier() + 100D);
                double d6 = d1 * (rand.nextDouble() * this.getStarSpreadMultiplier() + 100D);
                double d7 = d2 * (rand.nextDouble() * this.getStarSpreadMultiplier() + 100D);
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

                    if (ConfigManagerMP.moreplanets_general.useColoredStar)
                    {
                        try
                        {
                            int color = ColorUtils.hexToRgb(SkyProviderBaseMP.starColorHexTable[rand.nextInt(SkyProviderBaseMP.starColorHexTable.length)]);
                            int red = ColorUtils.toRGB(color).packedRed();
                            int green = ColorUtils.toRGB(color).packedGreen();
                            int blue = ColorUtils.toRGB(color).packedBlue();
                            buffer.pos(d5 + d25, d6 + d23, d7 + d26).color(red, green, blue, rand.nextInt(255)).endVertex();
                        }
                        catch (Exception e)
                        {
                            buffer.pos(d5 + d25, d6 + d23, d7 + d26).color(255, 255, 255, rand.nextInt(255)).endVertex();
                        }
                    }
                    else
                    {
                        if (!ConfigManagerMP.moreplanets_general.useFancyStar)
                        {
                            buffer.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
                        }
                    }
                    if (!ConfigManagerMP.moreplanets_general.useColoredStar && ConfigManagerMP.moreplanets_general.useFancyStar)
                    {
                        buffer.pos(d5 + d25, d6 + d23, d7 + d26).color(255, 255, 255, rand.nextInt(255)).endVertex();
                    }
                }
            }
        }
        tessellator.draw();
    }

    protected void renderSolar(ResourceLocation resource, float scale, boolean renderBlack, boolean renderConceal, float toDivide)
    {
        this.renderSolar(resource, scale, renderBlack, renderConceal, toDivide, 1.0F);
    }

    protected void renderSolar(ResourceLocation resource, float scale, boolean renderBlack, boolean renderConceal, float toDivide, float alpha)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        GlStateManager.pushMatrix();

        if (renderBlack)
        {
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.disableTexture2D();
            GlStateManager.color(0.0F, 0.0F, 0.0F, 1.0F);
            float blackScale = scale / toDivide;
            buffer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION);
            buffer.pos(-blackScale, 99.9D, -blackScale).endVertex();
            buffer.pos(blackScale, 99.9D, -blackScale).endVertex();
            buffer.pos(blackScale, 99.9D, blackScale).endVertex();
            buffer.pos(-blackScale, 99.9D, blackScale).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
        if (renderConceal)
        {
            float blackScale2 = scale / toDivide;// Some blanking to conceal the stars
            GlStateManager.disableTexture2D();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            GlStateManager.color(0.0F, 0.0F, 0.0F, 1.0F);
            buffer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION);
            buffer.pos(-blackScale2, 99.9D, -blackScale2).endVertex();
            buffer.pos(blackScale2, 99.9D, -blackScale2).endVertex();
            buffer.pos(blackScale2, 99.9D, blackScale2).endVertex();
            buffer.pos(-blackScale2, 99.9D, blackScale2).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
        }

        // render solar
        mc.getTextureManager().bindTexture(resource);
        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        buffer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-scale, 100.0D, -scale).tex(0.0D, 0.0D).endVertex();
        buffer.pos(scale, 100.0D, -scale).tex(1.0D, 0.0D).endVertex();
        buffer.pos(scale, 100.0D, scale).tex(1.0D, 1.0D).endVertex();
        buffer.pos(-scale, 100.0D, scale).tex(0.0D, 1.0D).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    protected void renderSolarAura(float smallAuraScale, float largeAuraScale, float starBrightness, ColorUtils.RGB rgb, float partialTicks)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        float red = rgb.floatRed();
        float green = rgb.floatGreen();
        float blue = rgb.floatBlue();
        float alpha = rgb.floatAlpha();
        starBrightness = 1.0F - starBrightness;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.shadeModel(7425);

        buffer.begin(GLConstants.TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(0.0D, 100.0D, 0.0D).color(red * starBrightness, green * starBrightness, blue * starBrightness, alpha * 2 / starBrightness).endVertex();

        // Render small aura
        buffer.pos(-smallAuraScale, 100.0D, -smallAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(0, 100.0D, (double) -smallAuraScale * 1.5F).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(smallAuraScale, 100.0D, -smallAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos((double) smallAuraScale * 1.5F, 100.0D, 0).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(smallAuraScale, 100.0D, smallAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(0, 100.0D, (double) smallAuraScale * 1.5F).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(-smallAuraScale, 100.0D, smallAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos((double) -smallAuraScale * 1.5F, 100.0D, 0).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(-smallAuraScale, 100.0D, -smallAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();

        tessellator.draw();
        buffer.begin(GLConstants.TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(0.0D, 100.0D, 0.0D).color(red * starBrightness, green * starBrightness, blue * starBrightness, alpha * starBrightness).endVertex();

        // Render larger aura
        buffer.pos(-largeAuraScale, 100.0D, -largeAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(0, 100.0D, (double) -largeAuraScale * 1.5F).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(largeAuraScale, 100.0D, -largeAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos((double) largeAuraScale * 1.5F, 100.0D, 0).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(largeAuraScale, 100.0D, largeAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(0, 100.0D, (double) largeAuraScale * 1.5F).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(-largeAuraScale, 100.0D, largeAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos((double) -largeAuraScale * 1.5F, 100.0D, 0).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(-largeAuraScale, 100.0D, -largeAuraScale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    }

    protected void renderObject(float scale, float rot1, float rot2, boolean rotate, ResourceLocation resource, float partialTicks)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        GlStateManager.pushMatrix();
        GlStateManager.rotate(rot1, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(rot2, 1.0F, 0.0F, 0.0F);

        if (rotate)
        {
            GlStateManager.rotate(mc.world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        }

        mc.getTextureManager().bindTexture(resource);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        buffer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-scale, 100.0D, -scale).tex(0.0D, 0.0D).endVertex();
        buffer.pos(scale, 100.0D, -scale).tex(1.0D, 0.0D).endVertex();
        buffer.pos(scale, 100.0D, scale).tex(1.0D, 1.0D).endVertex();
        buffer.pos(-scale, 100.0D, scale).tex(0.0D, 1.0D).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
    }

    protected void renderObject(float scale, float rot1, float rot2, boolean rotate, ResourceLocation resource, float partialTicks, float alpha)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        GlStateManager.pushMatrix();
        GlStateManager.rotate(rot1, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(rot2, 1.0F, 0.0F, 0.0F);

        if (rotate)
        {
            GlStateManager.rotate(mc.world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        }

        mc.getTextureManager().bindTexture(resource);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        buffer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-scale, 100.0D, -scale).tex(0.0D, 0.0D).endVertex();
        buffer.pos(scale, 100.0D, -scale).tex(1.0D, 0.0D).endVertex();
        buffer.pos(scale, 100.0D, scale).tex(1.0D, 1.0D).endVertex();
        buffer.pos(-scale, 100.0D, scale).tex(0.0D, 1.0D).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
    }

    protected boolean useDefaultStarBrightness()
    {
        return false;
    }

    protected abstract void renderObjects(float partialTicks, WorldClient world, Minecraft mc);
    protected abstract void renderStars(float starBrightness);
    protected abstract int getStarCount();
    protected abstract double getStarSpreadMultiplier();
    protected abstract float getStarBrightness();
}