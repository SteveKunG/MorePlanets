package stevekung.mods.moreplanets.planets.nibiru.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.client.model.ModelVeinFloater;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityVeinFloaterMinion;

@SideOnly(Side.CLIENT)
public class RenderVeinFloaterMinion extends RenderLiving<EntityVeinFloaterMinion>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/vein_floater.png");

    public RenderVeinFloaterMinion(RenderManager manager)
    {
        super(manager, new ModelVeinFloater(), 0.5F);
    }

    @Override
    protected void preRenderCallback(EntityVeinFloaterMinion entity, float partialTicks)
    {
        GlStateManager.translate(0.0F, 0.85F, 0.0F);
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityVeinFloaterMinion entity)
    {
        return RenderVeinFloaterMinion.TEXTURE;
    }
}