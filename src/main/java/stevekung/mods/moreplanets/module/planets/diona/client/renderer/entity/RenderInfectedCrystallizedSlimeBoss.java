package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelInfectedCrystallizedSlimeBoss;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.layer.LayerInfectedCrystallizeSlimeBossDeath;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.layer.LayerInfectedCrystallizedSlimeBossBarrier;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizedSlimeBoss;
import stevekung.mods.moreplanets.util.ClientRendererUtil;

public class RenderInfectedCrystallizedSlimeBoss extends RenderLiving<EntityInfectedCrystallizedSlimeBoss>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_crystallized_slime_boss.png");

    public RenderInfectedCrystallizedSlimeBoss(RenderManager manager)
    {
        super(manager, new ModelInfectedCrystallizedSlimeBoss(), 1.0F);
        this.addLayer(new LayerInfectedCrystallizedSlimeBossBarrier(this));
        this.addLayer(new LayerInfectedCrystallizeSlimeBossDeath());
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizedSlimeBoss entity)
    {
        return RenderInfectedCrystallizedSlimeBoss.TEXTURE;
    }

    @Override
    public void doRender(EntityInfectedCrystallizedSlimeBoss entity, double x, double y, double z, float entityYaw, float partialTicks)
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
    protected void preRenderCallback(EntityInfectedCrystallizedSlimeBoss entity, float partialTicks)
    {
        float f = entity.getSlimeSize();
        float f1 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTicks) / (f * 0.5F + 1.0F);
        float f2 = 1.0F / (f1 + 1.0F);
        GlStateManager.scale(f2 * f, 1.0F / f2 * f, f2 * f);
    }
}