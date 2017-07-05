package stevekung.mods.moreplanets.client.renderer.tileentity;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.obj.OBJModel.OBJBakedModel;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;

public class TileEntityShieldGeneratorRenderer extends TileEntitySpecialRenderer<TileEntityShieldGenerator>
{
    private OBJBakedModel sphere;

    private void updateModels()
    {
        if (this.sphere == null)
        {
            try
            {
                OBJModel model = (OBJModel) ModelLoaderRegistry.getModel(new ResourceLocation("moreplanets:obj/sphere.obj"));
                model = (OBJModel) model.process(ImmutableMap.of("flip-v", "true"));
                Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
                this.sphere = (OBJModel.OBJBakedModel) model.bake(new OBJModel.OBJState(ImmutableList.of("Sphere"), false), DefaultVertexFormats.ITEM, spriteFunction);
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
        if (!tile.getBubbleVisible())
        {
            return;
        }

        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        float test = 1.75F;
        int color = ColorUtil.to32BitColor(1, (int)(144 / test * 255), (int)(249 / test * 255), (int)(210 / test * 255));

        this.updateModels();
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.0F, (float) z + 0.5F);
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
        ClientUtil.drawBakedModelColored(this.sphere, color);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.matrixMode(5890);
        GlStateManager.depthMask(true);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.depthFunc(515);
        GlStateManager.enableCull();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}