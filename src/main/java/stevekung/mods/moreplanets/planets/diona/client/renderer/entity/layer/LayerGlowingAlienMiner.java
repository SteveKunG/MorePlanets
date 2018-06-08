package stevekung.mods.moreplanets.planets.diona.client.renderer.entity.layer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.renderer.entity.RenderAlienMiner;
import stevekung.mods.moreplanets.planets.diona.entity.EntityAlienMiner;

@SideOnly(Side.CLIENT)
public class LayerGlowingAlienMiner implements LayerRenderer<EntityAlienMiner>
{
    private static final ResourceLocation TEXTURE0 = new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_0.png");
    private static final ResourceLocation TEXTURE1 = new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_3.png");
    private static final ResourceLocation TEXTURE4 = new ResourceLocation("moreplanets:textures/entity/alien_miner_glow_4.png");
    private RenderAlienMiner render;

    public LayerGlowingAlienMiner(RenderAlienMiner render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityAlienMiner entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.render.bindTexture(LayerGlowingAlienMiner.TEXTURE0);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.render.setLightmap(entity);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();

        this.render.bindTexture(LayerGlowingAlienMiner.TEXTURE1);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.enableLighting();
        float time = entity.ticksExisted + partialTicks;
        float sin = (MathHelper.sin(time / 4) + 1F) / 2F + 0.15F;
        GlStateManager.color(sin, sin, sin, sin);
        this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.render.setLightmap(entity);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();

        this.render.bindTexture(LayerGlowingAlienMiner.TEXTURE2);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.enableLighting();
        time = entity.ticksExisted + partialTicks;
        sin = (MathHelper.sin(time / 8) + 1F) / 2F + 0.15F;
        GlStateManager.color(sin, sin, sin, sin);
        this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.render.setLightmap(entity);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();

        this.render.bindTexture(LayerGlowingAlienMiner.TEXTURE3);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.render.setLightmap(entity);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();

        this.render.bindTexture(LayerGlowingAlienMiner.TEXTURE4);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.enableLighting();
        time = entity.ticksExisted + partialTicks;
        sin = (MathHelper.sin(time / 16) + 1F) / 1F + 0.15F;
        GlStateManager.color(sin, sin, sin, sin);
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