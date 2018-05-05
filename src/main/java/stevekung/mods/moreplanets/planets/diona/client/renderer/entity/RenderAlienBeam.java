package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.renderer.FakeAlienBeamRenderer;
import stevekung.mods.moreplanets.planets.diona.entity.EntityAlienBeam;

@SideOnly(Side.CLIENT)
public class RenderAlienBeam extends Render<EntityAlienBeam>
{
    public RenderAlienBeam(RenderManager manager)
    {
        super(manager);
    }

    @Override
    public void doRender(EntityAlienBeam entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        FakeAlienBeamRenderer.INSTANCE.renderBeam(x, y, z, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAlienBeam entity)
    {
        return null;
    }
}