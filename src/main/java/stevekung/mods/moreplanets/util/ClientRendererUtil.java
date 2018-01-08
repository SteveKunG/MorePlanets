package stevekung.mods.moreplanets.util;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;

public class ClientRendererUtil
{
    public static void renderModel(IBlockState state)
    {
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        int i = mc.getBlockColors().colorMultiplier(state, (IBlockAccess)null, (BlockPos)null, 0);
        IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(state);

        if (EntityRenderer.anaglyphEnable)
        {
            i = TextureUtil.anaglyphColor(i);
        }
        float f = (i >> 16 & 255) / 255.0F;
        float f1 = (i >> 8 & 255) / 255.0F;
        float f2 = (i & 255) / 255.0F;
        ClientRendererUtil.renderModelBrightnessColor(state, model, 1.0F, f, f1, f2);
    }

    public static void renderModelBrightnessColor(IBlockState state, IBakedModel model, float brightness, float red, float green, float blue)
    {
        for (EnumFacing facing : EnumFacing.VALUES)
        {
            ClientRendererUtil.renderModelBrightnessColorQuads(brightness, red, green, blue, model.getQuads(state, facing, 0L));
        }
        ClientRendererUtil.renderModelBrightnessColorQuads(brightness, red, green, blue, model.getQuads(state, (EnumFacing)null, 0L));
    }

    private static void renderModelBrightnessColorQuads(float brightness, float red, float green, float blue, List<BakedQuad> listQuads)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        int i = 0;

        for (int j = listQuads.size(); i < j; ++i)
        {
            BakedQuad bakedquad = listQuads.get(i);
            vertexbuffer.begin(7, DefaultVertexFormats.ITEM);
            vertexbuffer.addVertexData(bakedquad.getVertexData());

            if (bakedquad.hasTintIndex())
            {
                ClientRendererUtil.putColorRGB_F4(vertexbuffer, red * brightness, green * brightness, blue * brightness);
            }
            else
            {
                ClientRendererUtil.putColorRGB_F4(vertexbuffer, brightness, brightness, brightness);
            }
            Vec3i vec3i = bakedquad.getFace().getDirectionVec();
            vertexbuffer.putNormal(vec3i.getX(), vec3i.getY(), vec3i.getZ());
            tessellator.draw();
        }
    }

    private static void putColorRGB_F4(BufferBuilder vertexbuffer, float red, float green, float blue)
    {
        for (int i = 0; i < 4; ++i)
        {
            ClientRendererUtil.putColorRGB_F(vertexbuffer, red, green, blue, i + 1);
        }
    }

    private static void putColorRGB_F(BufferBuilder vertexbuffer, float red, float green, float blue, int vertexIndex)
    {
        int i = vertexbuffer.getColorIndex(vertexIndex);
        int j = MathHelper.clamp((int)(red * 255.0F), 0, 255);
        int k = MathHelper.clamp((int)(green * 255.0F), 0, 255);
        int l = MathHelper.clamp((int)(blue * 255.0F), 0, 255);
        vertexbuffer.putColorRGBA(i, j, k, l, 127);
    }

    public static void bindTexture(String resource)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(resource));
    }

    public static void drawDefaultParticlesTexture(BufferBuilder vertexbuffer)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
    }

    public static void renderLightState(boolean disable)
    {
        if (disable)
        {
            GlStateManager.disableLighting();
            GlStateManager.disableLight(0);
            GlStateManager.disableLight(1);
            GlStateManager.disableColorMaterial();
        }
        else
        {
            GlStateManager.enableLighting();
            GlStateManager.enableLight(0);
            GlStateManager.enableLight(1);
            GlStateManager.enableColorMaterial();
        }
    }
}