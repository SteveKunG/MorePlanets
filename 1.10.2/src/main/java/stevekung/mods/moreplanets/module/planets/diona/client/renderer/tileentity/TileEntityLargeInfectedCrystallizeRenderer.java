package stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity;

import java.awt.Color;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityLargeInfectedCrystallize;
import stevekung.mods.moreplanets.util.client.model.ModelCrystal;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class TileEntityLargeInfectedCrystallizeRenderer extends TileEntitySpecialRenderer<TileEntityLargeInfectedCrystallize>
{
    private ModelCrystal model = new ModelCrystal();

    @Override
    public void renderTileEntityAt(TileEntityLargeInfectedCrystallize tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        Random rand = new Random(tile.getPos().getX() + tile.getPos().getY() * tile.getPos().getZ());

        for (int i = 0; i < 6; i++)
        {
            int[] colorList = new int[] { ColorHelper.rgbToDecimal(148, 114, 214), ColorHelper.rgbToDecimal(106, 75, 165), ColorHelper.rgbToDecimal(120, 86, 188), ColorHelper.rgbToDecimal(111, 78, 172), ColorHelper.rgbToDecimal(112, 80, 174), ColorHelper.rgbToDecimal(82, 58, 128) };

            for (int spike = 0; spike < 6; spike++)
            {
                int angle1 = rand.nextInt(32) + 72 * spike;
                int angle2 = 16 + rand.nextInt(16);
                this.drawCrystal(tile, tile.facing, (float)x, (float)y, (float)z, angle1, angle2, rand, colorList[i], 1.15F);
            }
        }
    }

    private void drawCrystal(TileEntityLargeInfectedCrystallize tile, int facing, float x, float y, float z, float angle1, float angle2, Random rand, int color, float size)
    {
        float shade = MathHelper.sin((Minecraft.getMinecraft().thePlayer.ticksExisted + rand.nextInt(20)) / (16.0F + rand.nextFloat())) * 0.075F + 0.925F;
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
        this.translateFromDirection(x, y, z, facing);
        GlStateManager.rotate(angle1, 0.1F, 1.0F, 0.0F);
        GlStateManager.rotate(angle2, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale((0.15F + rand.nextFloat() * 0.075F) * size, (0.5F + rand.nextFloat() * 0.1F) * size, (0.15F + rand.nextFloat() * 0.05F) * size);
        if (tile.renderTicks > 0)
        {
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightX / 1.0F, lightZ / 1.0F);
        }
        GlStateManager.color(r, g, b, 1.0F);
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/infected_crystallize_crystal.png"));
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

    private void translateFromDirection(float x, float y, float z, int facing)
    {
        if (facing == 0)
        {
            GlStateManager.translate(x + 0.425F, y + 1.3F, z + 0.5F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        }
        else if (facing == 1)
        {
            GlStateManager.translate(x + 0.425F, y - 0.3F, z + 0.5F);
        }
        else if (facing == 2)
        {
            GlStateManager.translate(x + 0.425F, y + 0.5F, z + 1.3F);
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
        }
        else if (facing == 3)
        {
            GlStateManager.translate(x + 0.425F, y + 0.5F, z - 0.3F);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
        }
        else if (facing == 4)
        {
            GlStateManager.translate(x + 1.3F, y + 0.425F, z + 0.5F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        }
        else if (facing == 5)
        {
            GlStateManager.translate(x - 0.3F, y + 0.575F, z + 0.5F);
            GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
        }
    }
}