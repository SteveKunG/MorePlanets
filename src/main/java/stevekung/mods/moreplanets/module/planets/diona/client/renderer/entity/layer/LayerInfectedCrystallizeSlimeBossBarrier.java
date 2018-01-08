package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.layer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelInfectedCrystallizeSlimeBoss;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.RenderInfectedCrystallizeSlimeBoss;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizeSlimeBoss;

@SideOnly(Side.CLIENT)
public class LayerInfectedCrystallizeSlimeBossBarrier implements LayerRenderer<EntityInfectedCrystallizeSlimeBoss>
{
    private RenderInfectedCrystallizeSlimeBoss render;
    private ModelInfectedCrystallizeSlimeBoss model = new ModelInfectedCrystallizeSlimeBoss();

    public LayerInfectedCrystallizeSlimeBossBarrier(RenderInfectedCrystallizeSlimeBoss render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityInfectedCrystallizeSlimeBoss entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entity.getBarrier())
        {
            GlStateManager.pushMatrix();
            this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/infected_crystallize_slime_boss_glow.png"));
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
            this.render.bindTexture(new ResourceLocation("galacticraftcore:textures/model/power.png"));
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
            this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/infected_crystallize_slime_boss_glow.png"));
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