package stevekung.mods.moreplanets.client.renderer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.planets.nibiru.client.model.ModelNuclearWasteTank;
import stevekung.mods.moreplanets.utils.ClientRendererUtils;

public class MultiblockRendererUtils
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/nuclear_waste_tank.png");
    private static final ResourceLocation GLOW = new ResourceLocation("moreplanets:textures/model/nuclear_waste_tank_glow.png");
    private static final ModelNuclearWasteTank MODEL = new ModelNuclearWasteTank();

    public static void renderBlock(double x, double y, double z, BlockPos pos, IBlockState state)
    {
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + pos.getX(), y + pos.getY(), z + pos.getZ() + 1);
        ClientRendererUtils.renderModel(state);
        GlStateManager.popMatrix();
        GlStateManager.enableDepth();
    }

    public static void renderTile(double x, double y, double z, BlockPos pos, TileEntity tile)
    {
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);
        TileEntityRendererDispatcher.instance.render(tile, x + pos.getX(), y + pos.getY(), z + pos.getZ(), 0.0F);
        GlStateManager.enableDepth();
    }

    public static void renderTankTile(double x, double y, double z, BlockPos pos)
    {
        GlStateManager.disableDepth();
        MultiblockRendererUtils.renderTank(x + pos.getX(), y + pos.getY(), z + pos.getZ());
        GlStateManager.enableDepth();
    }

    private static void renderTank(double x, double y, double z)
    {
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.pushMatrix();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        Minecraft.getMinecraft().getTextureManager().bindTexture(GLOW);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);
        MODEL.renderWaste();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);
        GlStateManager.enableLighting();
        GlStateManager.disableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.popMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        MODEL.renderBase();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        MODEL.renderGlass();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}