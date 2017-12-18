package stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelDarkEnergyGenerator;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyGenerator;

public class TileEntityDarkEnergyGeneratorRenderer extends TileEntitySpecialRenderer<TileEntityDarkEnergyGenerator>
{
    private static ResourceLocation texture = new ResourceLocation("moreplanets:textures/model/dark_energy_generator.png");
    private static ResourceLocation textureGlow1 = new ResourceLocation("moreplanets:textures/model/dark_energy_generator_glow1.png");
    private static ResourceLocation textureGlow2 = new ResourceLocation("moreplanets:textures/model/dark_energy_generator_glow2.png");
    private ModelDarkEnergyGenerator model = new ModelDarkEnergyGenerator();
    public static TileEntityDarkEnergyGeneratorRenderer INSTANCE;

    public TileEntityDarkEnergyGeneratorRenderer()
    {
        TileEntityDarkEnergyGeneratorRenderer.INSTANCE = this;
    }

    @Override
    public void renderTileEntityAt(TileEntityDarkEnergyGenerator tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        float renderPartialTicks = tile.renderTicks + partialTicks;
        float ticks = MathHelper.sin(renderPartialTicks / 12) / 10.0F + 0.75F;
        ticks = ticks * ticks + ticks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 3) + 0.5F) / 2F + 0.1F;
        int facing = 0;

        if (tile != null && tile.hasWorldObj() && tile.getWorld().getBlockState(tile.getPos()).getBlock() == DionaBlocks.DARK_ENERGY_GENERATOR)
        {
            facing = tile.facing;
        }

        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(facing, 0.0F, 1.0F, 0.0F);

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(TileEntityDarkEnergyGeneratorRenderer.textureGlow1);
        this.model.renderBaseSide();

        GlStateManager.color(lightTime, lightTime, lightTime);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        this.bindTexture(TileEntityDarkEnergyGeneratorRenderer.textureGlow2);
        this.model.renderRod();

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

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
            this.bindTexture(TileEntityDarkEnergyGeneratorRenderer.texture);
        }

        GlStateManager.rotate(facing, 0.0F, 1.0F, 0.0F);
        this.model.renderBase();

        if (tile.darkEnergyFuel > 0)
        {
            this.model.renderBall(ticks * 0.2F);
        }

        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityDarkEnergyGenerator tile)
    {
        return true;
    }

    public void renderItem()
    {
        float time = 100;
        float f1 = MathHelper.sin(time / 512) / 10.0F + 0.75F;
        f1 = f1 * f1 + f1;
        float sinOfTheTime = (MathHelper.sin(time / 128) + 1F) / 2F + 0.15F;

        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5F, 1.5F, 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(TileEntityDarkEnergyGeneratorRenderer.textureGlow1);
        this.model.renderBaseSide();

        GlStateManager.color(sinOfTheTime, sinOfTheTime, sinOfTheTime, sinOfTheTime);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        this.bindTexture(TileEntityDarkEnergyGeneratorRenderer.textureGlow2);
        this.model.renderRod();

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        this.bindTexture(TileEntityDarkEnergyGeneratorRenderer.texture);
        this.model.renderBase();
        this.model.renderBall(0.35F);
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
    }
}