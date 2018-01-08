package stevekung.mods.moreplanets.client.renderer.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.client.model.ModelBlackHoleStorage;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;

public class TileEntityBlackHoleStorageRenderer extends TileEntitySpecialRenderer<TileEntityBlackHoleStorage>
{
    private static ResourceLocation texture = new ResourceLocation("moreplanets:textures/model/black_hole_storage.png");
    private static ResourceLocation textureGlow1 = new ResourceLocation("moreplanets:textures/model/black_hole_storage_glow1.png");
    private static ResourceLocation textureGlow2 = new ResourceLocation("moreplanets:textures/model/black_hole_storage_glow2.png");
    private ModelBlackHoleStorage model = new ModelBlackHoleStorage();

    @Override
    public void render(TileEntityBlackHoleStorage tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        float renderPartialTicks = tile.renderTicks + partialTicks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 3) + 1F) / 2F + 0.15F;
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            this.bindTexture(TileEntityBlackHoleStorageRenderer.texture);
        }

        this.model.renderBase();
        this.model.renderRod();
        this.model.renderTop();
        GlStateManager.popMatrix();

        GlStateManager.color(lightTime, lightTime, lightTime);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        this.bindTexture(TileEntityBlackHoleStorageRenderer.textureGlow2);
        this.model.renderRod();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(TileEntityBlackHoleStorageRenderer.textureGlow1);
        this.model.renderTop();

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityBlackHoleStorage tile)
    {
        return true;
    }
}