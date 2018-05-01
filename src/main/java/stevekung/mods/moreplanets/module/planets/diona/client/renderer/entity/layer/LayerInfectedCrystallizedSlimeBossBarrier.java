package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.layer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelInfectedCrystallizedSlimeBoss;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.RenderInfectedCrystallizedSlimeBoss;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizedSlimeBoss;

@SideOnly(Side.CLIENT)
public class LayerInfectedCrystallizedSlimeBossBarrier implements LayerRenderer<EntityInfectedCrystallizedSlimeBoss>
{
    private RenderInfectedCrystallizedSlimeBoss render;
    private final ModelInfectedCrystallizedSlimeBoss model = new ModelInfectedCrystallizedSlimeBoss();
    private static final ResourceLocation GLOW = new ResourceLocation("moreplanets:textures/entity/infected_crystallized_slime_boss_glow.png");
    private static final ResourceLocation POWER = new ResourceLocation("galacticraftcore:textures/model/power.png");

    public LayerInfectedCrystallizedSlimeBossBarrier(RenderInfectedCrystallizedSlimeBoss render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityInfectedCrystallizedSlimeBoss entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entity.getBarrier())
        {
            GlStateManager.pushMatrix();
            this.render.bindTexture(LayerInfectedCrystallizedSlimeBossBarrier.GLOW);
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
            this.render.setLightmap(entity);
            GlStateManager.depthMask(true);
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.popMatrix();

            boolean flag = entity.isInvisible();
            GlStateManager.depthMask(!flag);
            this.render.bindTexture(LayerInfectedCrystallizedSlimeBossBarrier.POWER);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f = entity.ticksExisted + partialTicks;
            GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(1, 1);
            this.model.setModelAttributes(this.render.getMainModel());
            Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
            this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(flag);
        }
        else
        {
            GlStateManager.pushMatrix();
            this.render.bindTexture(LayerInfectedCrystallizedSlimeBossBarrier.GLOW);
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
            this.render.setLightmap(entity);
            GlStateManager.depthMask(true);
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}