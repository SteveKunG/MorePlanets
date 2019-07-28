package stevekung.mods.moreplanets.utils.client.renderer.entity.layer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerGlowingTexture implements LayerRenderer<EntityLiving>
{
    private final RenderLiving render;
    private final String textureToRender;
    private final boolean light;
    private final ResourceLocation texture;

    public LayerGlowingTexture(RenderLiving render, String textureToRender, boolean light)
    {
        this.render = render;
        this.textureToRender = textureToRender;
        this.light = light;
        this.texture = new ResourceLocation("moreplanets:textures/entity/" + this.textureToRender + ".png");
    }

    @Override
    public void doRenderLayer(EntityLiving entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.render.bindTexture(this.texture);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.enableLighting();

        if (this.light)
        {
            float time = entity.ticksExisted + partialTicks;
            float sin = (MathHelper.sin(time / 4) + 1F) / 2F + 0.15F;
            GlStateManager.color(sin, sin, sin, sin);
        }
        else
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }

        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        this.render.setLightmap(entity);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}