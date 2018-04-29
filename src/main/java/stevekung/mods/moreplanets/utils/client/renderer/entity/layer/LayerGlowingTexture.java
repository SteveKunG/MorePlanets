package stevekung.mods.moreplanets.utils.client.renderer.entity.layer;

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
    private RenderLiving render;
    private String textureToRender;
    private boolean light;

    public LayerGlowingTexture(RenderLiving render, String textureToRender, boolean light)
    {
        this.render = render;
        this.textureToRender = textureToRender;
        this.light = light;
    }

    @Override
    public void doRenderLayer(EntityLiving entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/" + this.textureToRender + ".png"));
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(1, 1);
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

        this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
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