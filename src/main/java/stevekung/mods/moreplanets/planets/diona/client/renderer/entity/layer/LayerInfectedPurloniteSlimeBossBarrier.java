package stevekung.mods.moreplanets.planets.diona.client.renderer.entity.layer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelInfectedPurloniteSlimeBoss;
import stevekung.mods.moreplanets.planets.diona.client.renderer.entity.RenderInfectedPurloniteSlimeBoss;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedPurloniteSlimeBoss;

@SideOnly(Side.CLIENT)
public class LayerInfectedPurloniteSlimeBossBarrier implements LayerRenderer<EntityInfectedPurloniteSlimeBoss>
{
    private final RenderInfectedPurloniteSlimeBoss render;
    private final ModelInfectedPurloniteSlimeBoss model = new ModelInfectedPurloniteSlimeBoss();
    private static final ResourceLocation POWER = new ResourceLocation("galacticraftcore:textures/model/power.png");

    public LayerInfectedPurloniteSlimeBossBarrier(RenderInfectedPurloniteSlimeBoss render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityInfectedPurloniteSlimeBoss entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entity.getBarrier())
        {
            GlStateManager.pushMatrix();
            boolean flag = entity.isInvisible();
            GlStateManager.depthMask(!flag);
            this.render.bindTexture(LayerInfectedPurloniteSlimeBossBarrier.POWER);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f = entity.ticksExisted + partialTicks;
            GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
            this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(flag);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}