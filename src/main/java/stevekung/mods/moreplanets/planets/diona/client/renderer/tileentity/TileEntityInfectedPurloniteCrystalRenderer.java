package stevekung.mods.moreplanets.planets.diona.client.renderer.tileentity;

import java.awt.Color;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityInfectedPurloniteCrystal;
import stevekung.mods.moreplanets.utils.client.model.ModelCrystal;
import stevekung.mods.stevekunglib.client.event.ClientEventHandler;
import stevekung.mods.stevekunglib.utils.ColorUtils;

@SideOnly(Side.CLIENT)
public class TileEntityInfectedPurloniteCrystalRenderer extends TileEntitySpecialRenderer<TileEntityInfectedPurloniteCrystal>
{
    private final ModelCrystal model = new ModelCrystal();
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/infected_purlonite_crystal.png");
    public static final TileEntityInfectedPurloniteCrystalRenderer INSTANCE = new TileEntityInfectedPurloniteCrystalRenderer();

    @Override
    public void render(TileEntityInfectedPurloniteCrystal tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
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

        Random rand = new Random(tile.getPos().getX() + tile.getPos().getY() * tile.getPos().getZ());

        for (int i = 0; i < 6; i++)
        {
            int[] colorList = new int[] { ColorUtils.rgbToDecimal(148, 114, 214), ColorUtils.rgbToDecimal(106, 75, 165), ColorUtils.rgbToDecimal(120, 86, 188), ColorUtils.rgbToDecimal(111, 78, 172), ColorUtils.rgbToDecimal(112, 80, 174), ColorUtils.rgbToDecimal(82, 58, 128) };

            for (int spike = 0; spike < 6; spike++)
            {
                int angle1 = rand.nextInt(32) + 72 * spike;
                int angle2 = 16 + rand.nextInt(16);
                this.drawCrystal(tile, (float)x, (float)y, (float)z, angle1, angle2, rand, colorList[i], 1.15F, meta);
            }
        }
    }

    private void drawCrystal(TileEntityInfectedPurloniteCrystal tile, float x, float y, float z, float angle1, float angle2, Random rand, int color, float size, int facing)
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
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.translate(x + 0.5F, y, z + 0.5F);
        this.translateFromDirection(facing);
        GlStateManager.rotate(angle1, 0.1F, 1.0F, 0.0F);
        GlStateManager.rotate(angle2, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale((0.15F + rand.nextFloat() * 0.075F) * size, (0.5F + rand.nextFloat() * 0.1F) * size, (0.15F + rand.nextFloat() * 0.05F) * size);
        if (tile.renderTicks > 0)
        {
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightX / 1.0F, lightZ / 1.0F);
        }
        GlStateManager.color(r, g, b, 1.0F);
        this.bindTexture(TileEntityInfectedPurloniteCrystalRenderer.TEXTURE);
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

    private void translateFromDirection(int facing)
    {
        switch (EnumFacing.getFront(facing))
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
        Random rand = new Random(8);

        for (int i = 0; i < 6; i++)
        {
            int[] colorList = new int[] { ColorUtils.rgbToDecimal(148, 114, 214), ColorUtils.rgbToDecimal(106, 75, 165), ColorUtils.rgbToDecimal(120, 86, 188), ColorUtils.rgbToDecimal(111, 78, 172), ColorUtils.rgbToDecimal(112, 80, 174), ColorUtils.rgbToDecimal(82, 58, 128) };

            for (int spike = 0; spike < 6; spike++)
            {
                float shade = MathHelper.sin((ClientEventHandler.renderPartialTicks + rand.nextInt(1)) / (16.0F + rand.nextFloat())) * 0.075F + 0.925F;
                int angle1 = rand.nextInt(32) + 72 * spike;
                int angle2 = 16 + rand.nextInt(16);
                float size = 1.15F;
                Color c = new Color(colorList[i]);
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
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.translate(0.45F, -0.3F, 0.5F);
                GlStateManager.rotate(angle1, 0.1F, 1.0F, 0.0F);
                GlStateManager.rotate(angle2, 1.0F, 0.0F, 0.0F);
                GlStateManager.scale((0.15F + rand.nextFloat() * 0.075F) * size, (0.5F + rand.nextFloat() * 0.1F) * size, (0.15F + rand.nextFloat() * 0.05F) * size);
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightX / 1.0F, lightZ / 1.0F);
                GlStateManager.color(r, g, b, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityInfectedPurloniteCrystalRenderer.TEXTURE);
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
                GlStateManager.enableBlend();
            }
        }
    }
}