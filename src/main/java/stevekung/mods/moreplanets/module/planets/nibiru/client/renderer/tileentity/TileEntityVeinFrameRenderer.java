package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockVeinFrame;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelVeinFrame;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityVeinFrame;

public class TileEntityVeinFrameRenderer extends TileEntitySpecialRenderer<TileEntityVeinFrame>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/vein_frame.png");
    private static final ResourceLocation GLOW = new ResourceLocation("moreplanets:textures/model/vein_frame_glow.png");
    private final ModelVeinFrame model = new ModelVeinFrame();

    @Override
    public void render(TileEntityVeinFrame tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        if (tile.getWorld() != null)
        {
            EnumFacing facing = tile.getWorld().getBlockState(tile.getPos()).getValue(BlockVeinFrame.FACING);

            switch (facing.getHorizontalIndex())
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
            if (tile.getWorld().getBlockState(tile.getPos()).getValue(BlockVeinFrame.EYE).booleanValue())
            {
                this.bindTexture(TileEntityVeinFrameRenderer.TEXTURE);
                this.model.renderEye();
            }
        }

        this.bindTexture(TileEntityVeinFrameRenderer.TEXTURE);
        this.model.renderAll();
        GlStateManager.enableRescaleNormal();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 150F, 150F);
        GlStateManager.disableLighting();
        this.bindTexture(TileEntityVeinFrameRenderer.GLOW);
        this.model.renderAll();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }
}