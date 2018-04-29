package stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelInfectedCrystallizedEnderCore;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityInfectedCrystallizedEnderCore;

public class TileEntityInfectedCrystallizedEnderCoreRenderer extends TileEntitySpecialRenderer<TileEntityInfectedCrystallizedEnderCore>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/infected_crystallized_ender_core.png");
    private static final ResourceLocation LIGHT = new ResourceLocation("moreplanets:textures/model/infected_crystallized_ender_core_glow.png");
    private final ModelInfectedCrystallizedEnderCore model = new ModelInfectedCrystallizedEnderCore();

    @Override
    public void render(TileEntityInfectedCrystallizedEnderCore tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        float renderPartialTicks = tile.renderTicks + partialTicks;
        float ticks = MathHelper.sin(renderPartialTicks / 8) / 10.0F + 0.75F;
        ticks = ticks * ticks + ticks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 3) + 1F) / 2F + 0.15F;
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        this.bindTexture(TileEntityInfectedCrystallizedEnderCoreRenderer.TEXTURE);
        this.model.renderAll(ticks * 0.2F);
        GlStateManager.color(lightTime, lightTime, lightTime);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(TileEntityInfectedCrystallizedEnderCoreRenderer.LIGHT);
        this.model.renderAll(ticks * 0.2F);
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