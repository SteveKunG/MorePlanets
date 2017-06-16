package stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.chalos.client.model.ModelCheeseCubeEyeBoss;
import stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity.layer.LayerCheeseCubeEyeBoss;
import stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity.layer.LayerCheeseCubeEyeBossDeath;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseCubeEyeBoss;
import stevekung.mods.moreplanets.util.MorePlanetsBossStatus;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class RenderCheeseCubeEyeBoss extends RenderLiving<EntityCheeseCubeEyeBoss>
{
    public RenderCheeseCubeEyeBoss(RenderManager manager)
    {
        super(manager, new ModelCheeseCubeEyeBoss(), 1.0F);
        this.addLayer(new LayerCheeseCubeEyeBoss(this));
        this.addLayer(new LayerCheeseCubeEyeBossDeath());
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCheeseCubeEyeBoss entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/cheese_cube_eye_boss.png");
    }

    @Override
    public void doRender(EntityCheeseCubeEyeBoss entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        MorePlanetsBossStatus.setBossStatus(entity, "Chalos Boss", ColorHelper.rgbToDecimal(246, 220, 160));
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityCheeseCubeEyeBoss entity, float partialTicks)
    {
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }
}