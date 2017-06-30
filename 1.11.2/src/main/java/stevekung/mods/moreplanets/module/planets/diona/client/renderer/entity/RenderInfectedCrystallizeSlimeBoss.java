package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelInfectedCrystallizeSlimeBoss;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.layer.LayerInfectedCrystallizeSlimeBossBarrier;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.layer.LayerInfectedCrystallizeSlimeBossDeath;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizeSlimeBoss;

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

        if (entity.tentacle != null)
        {
            this.bindTexture(RenderDragon.ENDERCRYSTAL_BEAM_TEXTURES);
            RenderDragon.renderCrystalBeams(x, y + 1.0D, z, partialTicks, entity.posX + (entity.prevPosX - entity.posX) * (1.0F - partialTicks), entity.posY + (entity.prevPosY - entity.posY) * (1.0F - partialTicks), entity.posZ + (entity.prevPosZ - entity.posZ) * (1.0F - partialTicks), entity.ticksExisted, entity.tentacle.posX, entity.tentacle.posY, entity.tentacle.posZ);
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
}