package stevekung.mods.stevekunglib.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderUtils
{
    private static final ResourceLocation PARTICLE_TEXTURE = new ResourceLocation("textures/particle/particles.png");

    public static void bindTexture(ResourceLocation resource)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
    }

    public static void bindTexture(String resource)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(resource));
    }

    public static void drawDefaultParticlesTexture(BufferBuilder bufferbuilder)
    {
        RenderUtils.bindTexture(RenderUtils.PARTICLE_TEXTURE);
        bufferbuilder.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
    }

    public static void disableLighting()
    {
        GlStateManager.disableLighting();
        GlStateManager.disableLight(0);
        GlStateManager.disableLight(1);
        GlStateManager.disableColorMaterial();
    }

    public static void enableLighting()
    {
        GlStateManager.enableLighting();
        GlStateManager.enableLight(0);
        GlStateManager.enableLight(1);
        GlStateManager.enableColorMaterial();
    }
}