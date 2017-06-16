package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelInfectedCrystallizeSlimeBoss;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.layer.LayerInfectedCrystallizeSlimeBossBarrier;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.layer.LayerInfectedCrystallizeSlimeBossDeath;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizeSlimeBoss;
import stevekung.mods.moreplanets.util.MorePlanetsBossStatus;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class RenderInfectedCrystallizeSlimeBoss extends RenderLiving<EntityInfectedCrystallizeSlimeBoss>
{
    //    private ModelInfectedCrystallizeSlimeBoss model = new ModelInfectedCrystallizeSlimeBoss();

    public RenderInfectedCrystallizeSlimeBoss(RenderManager manager)
    {
        super(manager, new ModelInfectedCrystallizeSlimeBoss(), 1.0F);
        this.addLayer(new LayerInfectedCrystallizeSlimeBossBarrier(this));
        this.addLayer(new LayerInfectedCrystallizeSlimeBossDeath());
    }

    //    @Override
    //    protected void renderModel(EntityInfectedCrystallizeSlimeBoss entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    //    {
    //        if (entity.deathTicks > 0)
    //        {
    //            float f = entity.deathTicks / 200.0F;
    //            GlStateManager.depthFunc(515);
    //            GlStateManager.enableAlpha();
    //            GlStateManager.alphaFunc(516, f);
    //            this.bindTexture(new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png"));
    //            this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    //            GlStateManager.alphaFunc(516, 0.1F);
    //            GlStateManager.depthFunc(514);
    //        }
    //
    //        this.bindEntityTexture(entity);
    //        this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    //
    //        if (entity.hurtTime > 0)
    //        {
    //            GlStateManager.depthFunc(514);
    //            GlStateManager.disableTexture2D();
    //            GlStateManager.enableBlend();
    //            GlStateManager.blendFunc(770, 771);
    //            GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F);
    //            this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    //            GlStateManager.enableTexture2D();
    //            GlStateManager.disableBlend();
    //            GlStateManager.depthFunc(515);
    //        }
    //    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizeSlimeBoss entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/infected_crystallize_slime_boss.png");
    }

    @Override
    public void doRender(EntityInfectedCrystallizeSlimeBoss entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 0.25F * entity.getSlimeSize();
        MorePlanetsBossStatus.setBossStatus(entity, "Diona Boss", ColorHelper.rgbToDecimal(157, 147, 183));

        if (entity.tentacle != null)
        {
            this.drawRechargeRay(entity, x, y, z, partialTicks);
        }
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityInfectedCrystallizeSlimeBoss entity, float partialTicks)
    {
        float f = entity.getSlimeSize();
        float f1 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTicks) / (f * 0.5F + 1.0F);
        float f2 = 1.0F / (f1 + 1.0F);
        GlStateManager.scale(f2 * f, 1.0F / f2 * f, f2 * f);
    }

    protected void drawRechargeRay(EntityInfectedCrystallizeSlimeBoss entity, double x, double y, double z, float partialTicks)
    {
        float f1 = MathHelper.sin(0.0F * 0.2F) / 2.0F + 0.5F;
        f1 = (f1 * f1 + f1) * 0.2F;
        float f2 = (float)(entity.tentacle.posX - entity.posX - (entity.prevPosX - entity.posX) * (1.0F - partialTicks));
        float f3 = (float)(f1 + entity.tentacle.posY - entity.posY - (entity.prevPosY - entity.posY) * (1.0F - partialTicks));
        float f4 = (float)(entity.tentacle.posZ - entity.posZ - (entity.prevPosZ - entity.posZ) * (1.0F - partialTicks));
        float f5 = MathHelper.sqrt_float(f2 * f2 + f4 * f4);
        float f6 = MathHelper.sqrt_float(f2 * f2 + f3 * f3 + f4 * f4);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + 2.0F, (float)z);
        GlStateManager.rotate((float)-Math.atan2(f4, f2) * 180.0F / (float)Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)-Math.atan2(f5, f3) * 180.0F / (float)Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        this.bindTexture(new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png"));
        GlStateManager.shadeModel(7425);
        float f7 = 0.0F - (entity.ticksExisted + partialTicks) * 0.01F;
        float f8 = MathHelper.sqrt_float(f2 * f2 + f3 * f3 + f4 * f4) / 32.0F - (entity.ticksExisted + partialTicks) * 0.01F;
        worldrenderer.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);

        for (int j = 0; j <= 8; ++j)
        {
            float f9 = MathHelper.sin(j % 8 * (float)Math.PI * 2.0F / 8.0F) * 0.75F;
            float f10 = MathHelper.cos(j % 8 * (float)Math.PI * 2.0F / 8.0F) * 0.75F;
            float f11 = j % 8 * 1.0F / 8.0F;
            worldrenderer.pos(f9 * 0.2F, f10 * 0.2F, 0.0D).tex(f11, f8).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(f9, f10, f6).tex(f11, f7).color(255, 255, 255, 255).endVertex();
        }
        tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.enableBlend();
        GlStateManager.shadeModel(7424);
        GlStateManager.popMatrix();
    }
}