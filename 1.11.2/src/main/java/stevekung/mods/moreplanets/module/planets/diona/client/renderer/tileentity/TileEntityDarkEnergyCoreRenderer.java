package stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelDarkEnergyBlock;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyCore;

public class TileEntityDarkEnergyCoreRenderer extends TileEntitySpecialRenderer<TileEntityDarkEnergyCore>
{
    private static ResourceLocation texture = new ResourceLocation("moreplanets:textures/model/dark_energy_core.png");
    private static ResourceLocation textureLight1 = new ResourceLocation("moreplanets:textures/model/dark_energy_core_glow.png");
    private ModelDarkEnergyBlock model = new ModelDarkEnergyBlock();

    @Override
    public void renderTileEntityAt(TileEntityDarkEnergyCore tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        float time = tile.age + 100 + partialTicks;
        float f1 = MathHelper.sin(time / 512) / 10.0F + 1F;
        f1 = f1 * f1 + f1;
        float sinOfTheTime = (MathHelper.sin(time / 128) + 1F) / 2F + 0.15F;
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        this.bindTexture(TileEntityDarkEnergyCoreRenderer.texture);
        this.model.renderAll(f1 * 0.2F);
        GlStateManager.color(sinOfTheTime, sinOfTheTime, sinOfTheTime, sinOfTheTime);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(TileEntityDarkEnergyCoreRenderer.textureLight1);
        this.model.renderAll(f1 * 0.2F);
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