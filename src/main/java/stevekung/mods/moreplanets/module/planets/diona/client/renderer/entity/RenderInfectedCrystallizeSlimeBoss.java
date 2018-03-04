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
import stevekung.mods.moreplanets.util.ClientRendererUtil;

public class RenderInfectedCrystallizeSlimeBoss extends RenderLiving<EntityInfectedCrystallizeSlimeBoss>
{
    public RenderInfectedCrystallizeSlimeBoss(RenderManager manager)
    {
        super(manager, new ModelInfectedCrystallizeSlimeBoss(), 1.0F);
        this.addLayer(new LayerInfectedCrystallizeSlimeBossBarrier(this));
        this.addLayer(new LayerInfectedCrystallizeSlimeBossDeath());
    }

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
            ClientRendererUtil.renderBeam(x, y + 1.0D, z, partialTicks, entity.posX + (entity.prevPosX - entity.posX) * (1.0F - partialTicks), entity.posY + (entity.prevPosY - entity.posY) * (1.0F - partialTicks), entity.posZ + (entity.prevPosZ - entity.posZ) * (1.0F - partialTicks), entity.ticksExisted, entity.tentacle.posX, entity.tentacle.posY, entity.tentacle.posZ);
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