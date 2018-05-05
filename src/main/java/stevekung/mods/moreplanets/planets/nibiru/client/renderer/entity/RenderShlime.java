package stevekung.mods.moreplanets.planets.nibiru.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.client.model.ModelShlime;
import stevekung.mods.moreplanets.planets.nibiru.client.renderer.entity.layer.LayerShlimeWool;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityShlime;

@SideOnly(Side.CLIENT)
public class RenderShlime extends RenderLiving<EntityShlime>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/shlime.png");

    public RenderShlime(RenderManager manager)
    {
        super(manager, new ModelShlime(), 0.3F);
        this.addLayer(new LayerShlimeWool(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityShlime entity)
    {
        return RenderShlime.TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityShlime entity, float partialTicks)
    {
        float f = 1.0F;
        float f1 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTicks) / (f * 0.5F + 1.0F);
        float f2 = 1.0F / (f1 + 1.0F);
        GlStateManager.scale(f2 * f, 1.5F / f2 * f, f2 * f);
    }
}