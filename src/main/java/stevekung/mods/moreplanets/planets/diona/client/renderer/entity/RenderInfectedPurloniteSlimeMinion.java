package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelInfectedPurloniteSlimeBoss;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedPurloniteSlimeMinion;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderInfectedPurloniteSlimeMinion extends RenderLiving<EntityInfectedPurloniteSlimeMinion>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_purlonite_slime_boss.png");

    public RenderInfectedPurloniteSlimeMinion(RenderManager manager)
    {
        super(manager, new ModelInfectedPurloniteSlimeBoss(), 1.0F);
        this.addLayer(new LayerGlowingTexture(this, "infected_purlonite_slime_boss_glow", true));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedPurloniteSlimeMinion entity)
    {
        return RenderInfectedPurloniteSlimeMinion.TEXTURE;
    }

    @Override
    public void doRender(EntityInfectedPurloniteSlimeMinion entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 0.25F * entity.getSlimeSize();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityInfectedPurloniteSlimeMinion entity, float partialTicks)
    {
        float f = entity.getSlimeSize();
        float f1 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTicks) / (f * 0.5F + 1.0F);
        float f2 = 1.0F / (f1 + 1.0F);
        GlStateManager.scale(f2 * f, 1.0F / f2 * f, f2 * f);
    }
}