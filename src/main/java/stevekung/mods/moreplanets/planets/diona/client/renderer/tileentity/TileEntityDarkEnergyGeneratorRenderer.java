package stevekung.mods.moreplanets.planets.diona.client.renderer.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelDarkEnergyGenerator;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.stevekunglib.client.event.ClientEventHandler;

@SideOnly(Side.CLIENT)
public class TileEntityDarkEnergyGeneratorRenderer extends TileEntitySpecialRenderer<TileEntityDarkEnergyGenerator>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/dark_energy_generator.png");
    private static final ResourceLocation GLOW0 = new ResourceLocation("moreplanets:textures/model/dark_energy_generator_glow1.png");
    private static final ResourceLocation GLOW1 = new ResourceLocation("moreplanets:textures/model/dark_energy_generator_glow2.png");
    private final ModelDarkEnergyGenerator model = new ModelDarkEnergyGenerator();
    public static final TileEntityDarkEnergyGeneratorRenderer INSTANCE = new TileEntityDarkEnergyGeneratorRenderer();

    @Override
    public void render(TileEntityDarkEnergyGenerator tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
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

        float renderPartialTicks = tile.renderTicks + partialTicks;
        float ticks = MathHelper.sin(renderPartialTicks / 12) / 10.0F + 0.75F;
        ticks = ticks * ticks + ticks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 3) + 0.5F) / 2F + 0.1F;
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

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
        GlStateManager.disableLighting();
        this.bindTexture(TileEntityDarkEnergyGeneratorRenderer.GLOW0);
        this.model.renderBaseSide();

        GlStateManager.color(lightTime, lightTime, lightTime);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        this.bindTexture(TileEntityDarkEnergyGeneratorRenderer.GLOW1);
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
            GlStateManager.enableBlend();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            this.bindTexture(TileEntityDarkEnergyGeneratorRenderer.TEXTURE);
        }

        switch (meta)
        {
        case 0:
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            break;
        case 1:
            GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
            break;
        case 2:
            GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            break;
        case 3:
            GlStateManager.rotate(-180.0F, 0.0F, 1.0F, 0.0F);
            break;
        }

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
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityDarkEnergyGenerator tile)
    {
        return true;
    }

    public void render()
    {
        float time = ClientEventHandler.renderPartialTicks;
        float ticks = MathHelper.sin(time / 8) / 1.0F + 0.75F;
        ticks = ticks * ticks + ticks;
        float sinOfTheTime = (MathHelper.sin(time / 4) + 1F) / 2F + 0.15F;
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5F, 1.5F, 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityDarkEnergyGeneratorRenderer.GLOW0);
        this.model.renderBaseSide();

        GlStateManager.color(sinOfTheTime, sinOfTheTime, sinOfTheTime, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityDarkEnergyGeneratorRenderer.GLOW1);
        this.model.renderRod();

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityDarkEnergyGeneratorRenderer.TEXTURE);
        this.model.renderBase();
        ticks = MathHelper.sin(time / 12) / 10.0F + 0.75F;
        ticks = ticks * ticks + ticks;
        this.model.renderBall(ticks * 0.275F);
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
    }
}