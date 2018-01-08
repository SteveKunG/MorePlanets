package stevekung.mods.moreplanets.module.planets.chalos.client.sky;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.client.renderer.sky.SkyProviderBaseMP;

public class SkyProviderChalos extends SkyProviderBaseMP
{
    private ResourceLocation siriusTexture = new ResourceLocation("moreplanets:textures/gui/celestialbodies/lazendus.png");
    private ResourceLocation dionaTexture = new ResourceLocation("moreplanets:textures/gui/celestialbodies/diona.png");

    public SkyProviderChalos(IGalacticraftWorldProvider provider)
    {
        super();
        this.sunSize = 17.5F * provider.getSolarSize();
    }

    @Override
    protected void renderPlanetInSky(float partialTicks, WorldClient world, Minecraft mc)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        float red;
        float green;
        float blue;
        float scale;
        float starBrightness = world.getStarBrightness(partialTicks);
        float[] color = new float[4];
        GlStateManager.disableTexture2D();
        GlStateManager.shadeModel(7425);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        color[0] = 217 / 255.0F;
        color[1] = 123 / 255.0F;
        color[2] = 38 / 255.0F;
        color[3] = 0.4F;
        red = color[0];
        green = color[1];
        blue = color[2];
        starBrightness = 1.0F - starBrightness;

        worldrenderer.begin(6, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(0.0D, 100.0D, 0.0D).color(red * starBrightness, green * starBrightness, blue * starBrightness, color[3] * 2 / starBrightness).endVertex();

        // Render sun aura
        scale = 14.0F;
        worldrenderer.pos(-scale, 100.0D, -scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(0, 100.0D, (double) -scale * 1.5F).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(scale, 100.0D, -scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos((double) scale * 1.5F, 100.0D, 0).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(scale, 100.0D, scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(0, 100.0D, (double) scale * 1.5F).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(-scale, 100.0D, scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos((double) -scale * 1.5F, 100.0D, 0).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(-scale, 100.0D, -scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();

        tessellator.draw();
        worldrenderer.begin(6, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(0.0D, 100.0D, 0.0D).color(red * starBrightness, green * starBrightness, blue * starBrightness, color[3] * starBrightness).endVertex();

        // Render larger sun aura
        scale = 30.0F;
        worldrenderer.pos(-scale, 100.0D, -scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(0, 100.0D, (double) -scale * 1.5F).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(scale, 100.0D, -scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos((double) scale * 1.5F, 100.0D, 0).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(scale, 100.0D, scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(0, 100.0D, (double) scale * 1.5F).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(-scale, 100.0D, scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos((double) -scale * 1.5F, 100.0D, 0).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        worldrenderer.pos(-scale, 100.0D, -scale).color(color[0] * starBrightness, color[1] * starBrightness, color[2] * starBrightness, 0.0F).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.disableTexture2D();
        GlStateManager.color(0.0F, 0.0F, 0.0F, 1.0F);

        // Render Sirius
        GlStateManager.disableTexture2D();
        GlStateManager.blendFunc(770, 1);
        GlStateManager.color(0.0F, 0.0F, 0.0F, 1.0F);

        //Some blanking to conceal the stars
        scale = this.sunSize / 3.5F;
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(-scale, 99.9D, -scale).endVertex();
        worldrenderer.pos(scale, 99.9D, -scale).endVertex();
        worldrenderer.pos(scale, 99.9D, scale).endVertex();
        worldrenderer.pos(-scale, 99.9D, scale).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        scale = this.sunSize + 6.5F;
        mc.getTextureManager().bindTexture(this.siriusTexture);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(-scale, 100.0D, -scale).tex(0.0D, 0.0D).endVertex();
        worldrenderer.pos(scale, 100.0D, -scale).tex(1.0D, 0.0D).endVertex();
        worldrenderer.pos(scale, 100.0D, scale).tex(1.0D, 1.0D).endVertex();
        worldrenderer.pos(-scale, 100.0D, scale).tex(0.0D, 1.0D).endVertex();
        tessellator.draw();

        // Render Diona
        scale = 0.75F;
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
        GlStateManager.rotate(0.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(220F, 1.0F, -0.3F, 0.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1F);
        mc.getTextureManager().bindTexture(this.dionaTexture);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(-scale, 100.0D, -scale).tex(0.0D, 0.0D).endVertex();
        worldrenderer.pos(scale, 100.0D, -scale).tex(1.0D, 0.0D).endVertex();
        worldrenderer.pos(scale, 100.0D, scale).tex(1.0D, 1.0D).endVertex();
        worldrenderer.pos(-scale, 100.0D, scale).tex(0.0D, 1.0D).endVertex();
        tessellator.draw();
    }

    @Override
    protected double[] getMaxStarCount()
    {
        return new double[] { 50000D, 150D, 130D };
    }

    @Override
    protected float[] getStarBrightness()
    {
        return new float[] { 0.2F, 0.2F };
    }

    @Override
    protected boolean useDefaultStarBrightness()
    {
        return false;
    }

    @Override
    protected float getRainStrength(World world, float partialTicks)
    {
        return 0.0F;
    }
}