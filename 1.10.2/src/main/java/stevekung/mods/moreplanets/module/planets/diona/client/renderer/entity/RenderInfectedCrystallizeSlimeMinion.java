package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelInfectedCrystallizeSlimeBoss;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizeSlimeMinion;
import stevekung.mods.moreplanets.util.client.renderer.entity.layer.LayerGlowingTexture;

public class RenderInfectedCrystallizeSlimeMinion extends RenderLiving<EntityInfectedCrystallizeSlimeMinion>
{
    public RenderInfectedCrystallizeSlimeMinion(RenderManager manager)
    {
        super(manager, new ModelInfectedCrystallizeSlimeBoss(), 1.0F);
        this.addLayer(new LayerGlowingTexture(this, "infected_crystallize_slime_boss_glow", true));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizeSlimeMinion entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/infected_crystallize_slime_boss.png");
    }

    @Override
    public void doRender(EntityInfectedCrystallizeSlimeMinion entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 0.25F * entity.getSlimeSize();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityInfectedCrystallizeSlimeMinion entity, float partialTicks)
    {
        float f = entity.getSlimeSize();
        float f1 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTicks) / (f * 0.5F + 1.0F);
        float f2 = 1.0F / (f1 + 1.0F);
        GlStateManager.scale(f2 * f, 1.0F / f2 * f, f2 * f);
    }
}