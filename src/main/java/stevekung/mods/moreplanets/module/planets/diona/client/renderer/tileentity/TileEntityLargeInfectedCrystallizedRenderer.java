package stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity;

import java.awt.Color;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityLargeInfectedCrystallized;
import stevekung.mods.moreplanets.util.client.model.ModelCrystal;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class TileEntityLargeInfectedCrystallizedRenderer extends TileEntitySpecialRenderer<TileEntityLargeInfectedCrystallized>
{
    private ModelCrystal model = new ModelCrystal();
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/infected_crystallized_crystal.png");
    public static final TileEntityLargeInfectedCrystallizedRenderer INSTANCE = new TileEntityLargeInfectedCrystallizedRenderer();

    @Override
    public void render(TileEntityLargeInfectedCrystallized tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        if (tile.facing == null)
        {
            return;
        }

        Random rand = new Random(tile.getPos().getX() + tile.getPos().getY() * tile.getPos().getZ());

        for (int i = 0; i < 6; i++)
        {
            int[] colorList = new int[] { ColorUtils.rgbToDecimal(148, 114, 214), ColorUtils.rgbToDecimal(106, 75, 165), ColorUtils.rgbToDecimal(120, 86, 188), ColorUtils.rgbToDecimal(111, 78, 172), ColorUtils.rgbToDecimal(112, 80, 174), ColorUtils.rgbToDecimal(82, 58, 128) };

            for (int spike = 0; spike < 6; spike++)
            {
                int angle1 = rand.nextInt(32) + 72 * spike;
                int angle2 = 16 + rand.nextInt(16);
                this.drawCrystal(tile, (float)x, (float)y, (float)z, angle1, angle2, rand, colorList[i], 1.15F);
            }
        }
    }

    private void drawCrystal(TileEntityLargeInfectedCrystallized tile, float x, float y, float z, float angle1, float angle2, Random rand, int color, float size)
    {
        float shade = MathHelper.sin((tile.renderTicks + rand.nextInt(20)) / (16.0F + rand.nextFloat())) * 0.075F + 0.925F;
        Color c = new Color(color);
        float r = c.getRed() / 220.0F;
        float g = c.getGreen() / 200.0F;
        float b = c.getBlue() / 200.0F;
        int shadeLight = (int)(243.0F * shade);
        int lightX = shadeLight % 65536;
        int lightZ = shadeLight / 65536;
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.enableNormalize();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.translate(x + 0.5F, y, z + 0.5F);
        this.translateFromDirection(tile);
        GlStateManager.rotate(angle1, 0.1F, 1.0F, 0.0F);
        GlStateManager.rotate(angle2, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale((0.15F + rand.nextFloat() * 0.075F) * size, (0.5F + rand.nextFloat() * 0.1F) * size, (0.15F + rand.nextFloat() * 0.05F) * size);
        if (tile.renderTicks > 0)
        {
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightX / 1.0F, lightZ / 1.0F);
        }
        GlStateManager.color(r, g, b, 1.0F);
        this.bindTexture(TileEntityLargeInfectedCrystallizedRenderer.TEXTURE);
        this.model.render();
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
    }

    private void translateFromDirection(TileEntityLargeInfectedCrystallized tile)
    {
        switch (tile.facing)
        {
        case WEST:
            GlStateManager.translate(0.0F, 0.5F, 0.7F);
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            break;
        case SOUTH:
            GlStateManager.translate(0.0F, 0.5F, -0.7F);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            break;
        case UP:
            GlStateManager.translate(-0.7F, 0.575F, 0.0F);
            GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            break;
        case NORTH:
            GlStateManager.translate(0.7F, 0.5F, 0.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            break;
        case EAST:
            GlStateManager.translate(0.0F, -0.3F, 0.0F);
            break;
        case DOWN:
        default:
            GlStateManager.translate(0.0F, 1.3F, 0.0F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            break;
        }
    }

    public void render()
    {
        Random rand = new Random(5);

        for (int i = 0; i < 6; i++)
        {
            int[] colorList = new int[] { ColorUtils.rgbToDecimal(148, 114, 214), ColorUtils.rgbToDecimal(106, 75, 165), ColorUtils.rgbToDecimal(120, 86, 188), ColorUtils.rgbToDecimal(111, 78, 172), ColorUtils.rgbToDecimal(112, 80, 174), ColorUtils.rgbToDecimal(82, 58, 128) };

            for (int spike = 0; spike < 6; spike++)
            {
                int angle1 = rand.nextInt(32) + 72 * spike;
                int angle2 = 16 + rand.nextInt(16);
                float size = 1.15F;
                Color c = new Color(colorList[i]);
                float r = c.getRed() / 220.0F;
                float g = c.getGreen() / 200.0F;
                float b = c.getBlue() / 200.0F;
                GlStateManager.pushMatrix();
                GlStateManager.enableRescaleNormal();
                GlStateManager.enableBlend();
                GlStateManager.enableNormalize();
                GlStateManager.blendFunc(770, 771);
                GlStateManager.translate(0.45F, -0.3F, 0.5F);
                GlStateManager.rotate(angle1, 0.1F, 1.0F, 0.0F);
                GlStateManager.rotate(angle2, 1.0F, 0.0F, 0.0F);
                GlStateManager.scale((0.15F + rand.nextFloat() * 0.075F) * size, (0.5F + rand.nextFloat() * 0.1F) * size, (0.15F + rand.nextFloat() * 0.05F) * size);
                GlStateManager.color(r, g, b, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityLargeInfectedCrystallizedRenderer.TEXTURE);
                this.model.render();
                GlStateManager.scale(1.0F, 1.0F, 1.0F);
                GlStateManager.disableBlend();
                GlStateManager.disableRescaleNormal();
                GlStateManager.popMatrix();
                GlStateManager.enableBlend();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.disableBlend();
                GlStateManager.enableLighting();
                GlStateManager.enableBlend();
            }
        }
    }
}