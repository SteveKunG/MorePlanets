package stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelZeliusEgg;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityZeliusEgg;

public class TileEntityZeliusEggRenderer extends TileEntitySpecialRenderer<TileEntityZeliusEgg>
{
    private static ResourceLocation texture = new ResourceLocation("moreplanets:textures/model/zelius_egg.png");
    private static ResourceLocation textureLight1 = new ResourceLocation("moreplanets:textures/model/zelius_egg_glow.png");
    private ModelZeliusEgg model = new ModelZeliusEgg();

    @Override
    public void render(TileEntityZeliusEgg tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        float renderPartialTicks = tile.renderTicks + partialTicks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 3) + 1F) / 2F + 0.15F;
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        this.bindTexture(TileEntityZeliusEggRenderer.texture);
        this.model.renderAll();
        GlStateManager.color(lightTime, lightTime, lightTime);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(TileEntityZeliusEggRenderer.textureLight1);
        this.model.renderAll();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}