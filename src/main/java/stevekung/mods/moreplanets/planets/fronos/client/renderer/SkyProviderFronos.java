package stevekung.mods.moreplanets.planets.fronos.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.client.renderer.sky.SkyProviderBaseMP;
import stevekung.mods.stevekunglib.utils.client.GLConstants;

public class SkyProviderFronos extends SkyProviderBaseMP
{
    private static final ResourceLocation NIBIRU = new ResourceLocation("moreplanets:textures/gui/celestialbodies/nibiru.png");
    private static final ResourceLocation CHALOS = new ResourceLocation("moreplanets:textures/gui/celestialbodies/chalos.png");
    private static final ResourceLocation LAZENDUS = new ResourceLocation("moreplanets:textures/gui/celestialbodies/lazendus.png");

    public SkyProviderFronos(float solarSize)
    {
        this.solarSize = 90.0F * solarSize;
        this.hasRain = true;
    }

    @Override
    protected void renderObjects(float partialTicks, WorldClient world, Minecraft mc)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        float starBrightness = world.getStarBrightness(partialTicks);
        float rainStrength = world.getRainStrength(partialTicks);
        float red = 245 / 255.0F;
        float green = 106 / 255.0F;
        float blue = 0 / 255.0F;
        float alpha = 0.4F - rainStrength;
        float scale;
        float baseValue = 0.15F;
        starBrightness = 1.0F - starBrightness;

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.shadeModel(7425);

        if (alpha < baseValue)
        {
            alpha = baseValue;
        }

        // Render sun aura
        scale = 25.0F;
        buffer.begin(GLConstants.TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(0.0D, 100.0D, 0.0D).color(red * starBrightness, green * starBrightness, blue * starBrightness, alpha * 2 / starBrightness).endVertex();
        buffer.pos(-scale, 100.0D, -scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(0, 100.0D, (double) -scale * 1.5F).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(scale, 100.0D, -scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos((double) scale * 1.5F, 100.0D, 0).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(scale, 100.0D, scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(0, 100.0D, (double) scale * 1.5F).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(-scale, 100.0D, scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos((double) -scale * 1.5F, 100.0D, 0).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(-scale, 100.0D, -scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        tessellator.draw();

        // Render larger sun aura
        scale = 40.0F;
        buffer.begin(GLConstants.TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(0.0D, 100.0D, 0.0D).color(red * starBrightness, green * starBrightness, blue * starBrightness, alpha * starBrightness).endVertex();
        buffer.pos(-scale, 100.0D, -scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(0, 100.0D, (double) -scale * 1.5F).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(scale, 100.0D, -scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos((double) scale * 1.5F, 100.0D, 0).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(scale, 100.0D, scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(0, 100.0D, (double) scale * 1.5F).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(-scale, 100.0D, scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos((double) -scale * 1.5F, 100.0D, 0).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        buffer.pos(-scale, 100.0D, -scale).color(red * starBrightness, green * starBrightness, blue * starBrightness, 0.0F).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.popMatrix();

        this.renderSolar(SkyProviderFronos.LAZENDUS, this.solarSize, false, true, 4.0F, alpha);
        this.renderObject(0.5F, 60.0F, 180.0F, true, SkyProviderFronos.CHALOS, partialTicks, alpha);
        this.renderObject(1.5F, -60.0F, 0.0F, true, SkyProviderFronos.NIBIRU, partialTicks, alpha);
    }

    @Override
    protected void renderStars(float starBrightness)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, this.hasRain ? starBrightness : this.getStarBrightness());
    }

    @Override
    protected float getStarBrightness()
    {
        return 0.4F;
    }

    @Override
    protected int getStarCount()
    {
        return 35000;
    }

    @Override
    protected double getStarSpreadMultiplier()
    {
        return 100.0D;
    }
}