package stevekung.mods.moreplanets.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;

public class ClientRendererUtils
{
    public static void renderModel(IBlockState state)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(state);
        Block block = state.getBlock();
        block.setBlockBoundsForItemRender();
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        int i = block.getRenderColor(block.getStateForEntityRender(state));

        if (EntityRenderer.anaglyphEnable)
        {
            i = TextureUtil.anaglyphColor(i);
        }
        float f = (i >> 16 & 255) / 255.0F;
        float f1 = (i >> 8 & 255) / 255.0F;
        float f2 = (i & 255) / 255.0F;
        ClientRendererUtils.renderModelBrightnessColor(model, 1.0F, f, f1, f2);
    }

    public static void bindTexture(String resource)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(resource));
    }

    public static void drawDefaultParticlesTexture(VertexBuffer worldrenderer)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
    }

    private static void renderModelBrightnessColor(IBakedModel bakedModel, float brightness, float red, float green, float blue)
    {
        for (EnumFacing enumfacing : EnumFacing.VALUES)
        {
            ClientRendererUtils.renderModelBrightnessColorQuads(brightness, red, green, blue, bakedModel.getFaceQuads(enumfacing));
        }
        ClientRendererUtils.renderModelBrightnessColorQuads(brightness, red, green, blue, bakedModel.getGeneralQuads());
    }

    private static void renderModelBrightnessColorQuads(float brightness, float red, float green, float blue, List<BakedQuad> listQuads)
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        for (BakedQuad bakedquad : listQuads)
        {
            worldrenderer.begin(7, DefaultVertexFormats.ITEM);
            worldrenderer.addVertexData(bakedquad.getVertexData());

            if (bakedquad.hasTintIndex())
            {
                ClientRendererUtils.putColorRGB_F4(worldrenderer, red * brightness, green * brightness, blue * brightness);
            }
            else
            {
                ClientRendererUtils.putColorRGB_F4(worldrenderer, brightness, brightness, brightness);
            }
            Vec3i vec3i = bakedquad.getFace().getDirectionVec();
            worldrenderer.putNormal(vec3i.getX(), vec3i.getY(), vec3i.getZ());
            tessellator.draw();
        }
    }

    private static void putColorRGB_F4(WorldRenderer worldrenderer, float red, float green, float blue)
    {
        for (int i = 0; i < 4; ++i)
        {
            ClientRendererUtils.putColorRGB_F(worldrenderer, red, green, blue, i + 1);
        }
    }

    private static void putColorRGB_F(WorldRenderer worldrenderer, float red, float green, float blue, int index)
    {
        int i = worldrenderer.getColorIndex(index);
        int j = MathHelper.clamp_int((int)(red * 255.0F), 0, 255);
        int k = MathHelper.clamp_int((int)(green * 255.0F), 0, 255);
        int l = MathHelper.clamp_int((int)(blue * 255.0F), 0, 255);
        worldrenderer.putColorRGBA(i, j, k, l, 127);
    }
}