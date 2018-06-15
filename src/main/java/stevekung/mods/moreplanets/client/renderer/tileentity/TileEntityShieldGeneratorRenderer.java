package stevekung.mods.moreplanets.client.renderer.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.model.ModelShieldGenerator;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.stevekunglib.client.event.ClientEventHandler;

@SideOnly(Side.CLIENT)
public class TileEntityShieldGeneratorRenderer extends TileEntitySpecialRenderer<TileEntityShieldGenerator>
{
    private final ModelShieldGenerator model = new ModelShieldGenerator();
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/shield_generator.png");
    private static final ResourceLocation GLOW0 = new ResourceLocation("moreplanets:textures/model/shield_generator_glow1.png");
    private static final ResourceLocation GLOW1 = new ResourceLocation("moreplanets:textures/model/shield_generator_glow2.png");
    public static final TileEntityShieldGeneratorRenderer INSTANCE = new TileEntityShieldGeneratorRenderer();

    @Override
    public void render(TileEntityShieldGenerator tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        int meta;

        if (!tile.hasWorld())
        {
            meta = 0;
        }
        else
        {
            meta = tile.getBlockMetadata();
        }

        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        float renderPartialTicks = tile.renderTicks + partialTicks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 16) + 1F) / 2F + 0.15F;

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        switch (meta)
        {
        case 0:
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            break;
        case 1:
            GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            break;
        case 2:
            GlStateManager.rotate(-360.0F, 0.0F, 1.0F, 0.0F);
            break;
        case 3:
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            break;
        }

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(lightTime, lightTime, lightTime);
        this.bindTexture(TileEntityShieldGeneratorRenderer.GLOW0);
        this.model.renderBase();
        GlStateManager.pushMatrix();
        GlStateManager.rotate(tile.hasEnoughEnergyToRun && !tile.disabled ? partialTicks + tile.solarRotate : tile.solarRotate, 0.0F, 1.0F, 0.0F);
        this.bindTexture(TileEntityShieldGeneratorRenderer.GLOW0);
        this.model.renderRod();
        GlStateManager.popMatrix();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.bindTexture(TileEntityShieldGeneratorRenderer.GLOW1);
        this.model.renderBase();
        GlStateManager.pushMatrix();
        GlStateManager.rotate(tile.hasEnoughEnergyToRun && !tile.disabled ? partialTicks + tile.solarRotate : tile.solarRotate, 0.0F, 1.0F, 0.0F);
        this.bindTexture(TileEntityShieldGeneratorRenderer.GLOW1);
        this.model.renderRod();
        GlStateManager.popMatrix();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        this.bindTexture(TileEntityShieldGeneratorRenderer.TEXTURE);
        this.model.renderBase();
        GlStateManager.rotate(tile.hasEnoughEnergyToRun && !tile.disabled ? partialTicks + tile.solarRotate : tile.solarRotate, 0.0F, 1.0F, 0.0F);
        this.model.renderRod();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void render()
    {
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        float renderPartialTicks = ClientEventHandler.renderPartialTicks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 16) + 1F) / 2F + 0.15F;

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate(0.5F, 1.5F, 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(lightTime, lightTime, lightTime);
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityShieldGeneratorRenderer.GLOW0);
        this.model.renderBase();
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityShieldGeneratorRenderer.GLOW0);
        this.model.renderRod();
        GlStateManager.popMatrix();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityShieldGeneratorRenderer.GLOW1);
        this.model.renderBase();
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityShieldGeneratorRenderer.GLOW1);
        this.model.renderRod();
        GlStateManager.popMatrix();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityShieldGeneratorRenderer.TEXTURE);
        this.model.renderBase();
        this.model.renderRod();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
    }
}