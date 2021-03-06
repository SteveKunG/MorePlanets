package stevekung.mods.moreplanets.planets.chalos.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.chalos.client.model.ModelCheeseCubeEyeBoss;
import stevekung.mods.moreplanets.planets.chalos.client.renderer.entity.layer.LayerCheeseCubeEyeBoss;
import stevekung.mods.moreplanets.planets.chalos.client.renderer.entity.layer.LayerCheeseCubeEyeBossDeath;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseCubeEyeBoss;

@SideOnly(Side.CLIENT)
public class RenderCheeseCubeEyeBoss extends RenderLiving<EntityCheeseCubeEyeBoss>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/cheese_cube_eye_boss.png");

    public RenderCheeseCubeEyeBoss(RenderManager manager)
    {
        super(manager, new ModelCheeseCubeEyeBoss(), 1.0F);
        this.addLayer(new LayerCheeseCubeEyeBoss(this));
        this.addLayer(new LayerCheeseCubeEyeBossDeath());
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCheeseCubeEyeBoss entity)
    {
        return RenderCheeseCubeEyeBoss.TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityCheeseCubeEyeBoss entity, float partialTicks)
    {
        GlStateManager.translate(0.0F, -0.25F, 0.0F);
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }
}