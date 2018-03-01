package stevekung.mods.moreplanets.client.renderer.tileentity;

import com.google.common.collect.ImmutableList;

import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.client.model.ModelShieldGenerator;
import stevekung.mods.moreplanets.client.renderer.OBJLoaderMP;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;

public class TileEntityShieldGeneratorRenderer extends TileEntitySpecialRenderer<TileEntityShieldGenerator>
{
    private IBakedModel shield;
    private ModelShieldGenerator model = new ModelShieldGenerator();
    private static final ResourceLocation texture = new ResourceLocation("moreplanets:textures/model/shield_generator.png");
    public static final TileEntityShieldGeneratorRenderer INSTANCE = new TileEntityShieldGeneratorRenderer();

    private void updateModels()
    {
        if (this.shield == null)
        {
            try
            {
                this.shield = OBJLoaderMP.getModelFromOBJ(new ResourceLocation("moreplanets:shield.obj"), ImmutableList.of("Shield"));
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void renderTileEntityAt(TileEntityShieldGenerator tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        float renderPartialTicks = tile.renderTicks + partialTicks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 16) + 1F) / 2F + 0.15F;

        this.updateModels();
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.rotate(tile.facing, 0.0F, 1.0F, 0.0F);

        if (tile.getBubbleVisible())
        {
            GlStateManager.pushMatrix();
            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            GlStateManager.enableBlend();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.depthMask(false);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
            GlStateManager.scale(tile.getBubbleSize(), tile.getBubbleSize(), tile.getBubbleSize());
            int capacity = tile.shieldCapacity * 171 / tile.maxShieldCapacity;
            int color = ColorUtil.to32BitColor(capacity + 84, 255, 255, 255);
            ClientUtil.drawBakedModelColored(this.shield, color);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.depthMask(true);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthFunc(515);
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
        }

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(lightTime, lightTime, lightTime);
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow1.png"));
        this.model.renderBase();
        GlStateManager.pushMatrix();
        GlStateManager.rotate(tile.hasEnoughEnergyToRun && !tile.disabled ? partialTicks + tile.solarRotate : tile.solarRotate, 0.0F, 1.0F, 0.0F);
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow1.png"));
        this.model.renderRod();
        GlStateManager.popMatrix();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow2.png"));
        this.model.renderBase();
        GlStateManager.pushMatrix();
        GlStateManager.rotate(tile.hasEnoughEnergyToRun && !tile.disabled ? partialTicks + tile.solarRotate : tile.solarRotate, 0.0F, 1.0F, 0.0F);
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow2.png"));
        this.model.renderRod();
        GlStateManager.popMatrix();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        this.bindTexture(TileEntityShieldGeneratorRenderer.texture);
        this.model.renderBase();
        GlStateManager.rotate(tile.hasEnoughEnergyToRun && !tile.disabled ? partialTicks + tile.solarRotate : tile.solarRotate, 0.0F, 1.0F, 0.0F);
        this.model.renderRod();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void render(float scale, float y)
    {
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        float renderPartialTicks = ClientEventHandler.itemRendererTicks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 16) + 1F) / 2F + 0.15F;

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate(0.5F, y, 0.5F);
        GlStateManager.scale(-scale, -scale, scale);

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(lightTime, lightTime, lightTime);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow1.png"));
        this.model.renderBase();
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow1.png"));
        this.model.renderRod();
        GlStateManager.popMatrix();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow2.png"));
        this.model.renderBase();
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow2.png"));
        this.model.renderRod();
        GlStateManager.popMatrix();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityShieldGeneratorRenderer.texture);
        this.model.renderBase();
        this.model.renderRod();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
    }
}