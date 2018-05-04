package stevekung.mods.moreplanets.client.renderer.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.model.ModelSpaceWarpPad;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPadFull;

@SideOnly(Side.CLIENT)
public class TileEntitySpaceWarpPadFullRenderer extends TileEntitySpecialRenderer<TileEntitySpaceWarpPadFull>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/space_warp_pad.png");
    private static final ResourceLocation LIGHT0 = new ResourceLocation("moreplanets:textures/model/space_warp_pad_light1.png");
    private static final ResourceLocation LIGHT1 = new ResourceLocation("moreplanets:textures/model/space_warp_pad_light2.png");
    private final ModelSpaceWarpPad model = new ModelSpaceWarpPad();

    @Override
    public void render(TileEntitySpaceWarpPadFull tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.translate(0.5F, 1.5F, 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();

        if (tile.ticks / 10 % 2 < 1)
        {
            GlStateManager.color(0.15F, 0.15F, 0.15F);
        }
        else
        {
            GlStateManager.color(0.0F, 1.0F, 0.0F);
        }

        this.bindTexture(TileEntitySpaceWarpPadFullRenderer.LIGHT0);
        this.model.renderLight();

        if (tile.ticks / 9 % 2 < 1)
        {
            GlStateManager.color(0.0F, 0.25F, 1.0F);
        }
        else
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
        }
        this.bindTexture(TileEntitySpaceWarpPadFullRenderer.LIGHT1);
        this.model.renderRod();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask(false);
        GlStateManager.blendFunc(771, 1);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.enableAlpha();
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        this.bindTexture(TileEntitySpaceWarpPadFullRenderer.TEXTURE);
        this.model.render();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }
}