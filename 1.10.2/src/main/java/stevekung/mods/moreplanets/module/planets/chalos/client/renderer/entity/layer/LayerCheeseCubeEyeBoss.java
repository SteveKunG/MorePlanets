package stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity.layer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity.RenderCheeseCubeEyeBoss;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseCubeEyeBoss;

@SideOnly(Side.CLIENT)
public class LayerCheeseCubeEyeBoss implements LayerRenderer<EntityCheeseCubeEyeBoss>
{
    private RenderCheeseCubeEyeBoss render;

    public LayerCheeseCubeEyeBoss(RenderCheeseCubeEyeBoss render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityCheeseCubeEyeBoss entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entity.getHealth() <= entity.getMaxHealth() / 2.5F)
        {
            this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/cheese_cube_eye_boss_glow.png"));
            GlStateManager.enableBlend();
            GlStateManager.disableAlpha();
            GlStateManager.blendFunc(1, 1);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(!entity.isInvisible());
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
            GlStateManager.enableLighting();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            this.render.func_177105_a(entity, partialTicks);
            GlStateManager.depthMask(true);
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}