package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.layer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.RenderAlienMiner;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlienMiner;

@SideOnly(Side.CLIENT)
public class LayerGlowingAlienMiner implements LayerRenderer<EntityAlienMiner>
{
    private RenderAlienMiner render;

    public LayerGlowingAlienMiner(RenderAlienMiner render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityAlienMiner entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_0.png"));
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

        this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_1.png"));
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(1, 1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.enableLighting();
        float time = entity.ticksExisted + partialTicks;
        float sin = (MathHelper.sin(time / 4) + 1F) / 2F + 0.15F;
        GlStateManager.color(sin, sin, sin, sin);
        this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.render.func_177105_a(entity, partialTicks);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();

        this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_2.png"));
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(1, 1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.enableLighting();
        time = entity.ticksExisted + partialTicks;
        sin = (MathHelper.sin(time / 8) + 1F) / 2F + 0.15F;
        GlStateManager.color(sin, sin, sin, sin);
        this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.render.func_177105_a(entity, partialTicks);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();

        this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_3.png"));
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

        this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_4.png"));
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(1, 1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.enableLighting();
        time = entity.ticksExisted + partialTicks;
        sin = (MathHelper.sin(time / 16) + 1F) / 1F + 0.15F;
        GlStateManager.color(sin, sin, sin, sin);
        this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.render.func_177105_a(entity, partialTicks);
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