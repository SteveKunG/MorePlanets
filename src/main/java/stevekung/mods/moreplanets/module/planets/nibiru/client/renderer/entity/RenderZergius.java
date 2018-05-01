package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelZergius;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityZergius;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderZergius extends RenderLiving<EntityZergius>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/zergius.png");

    public RenderZergius(RenderManager manager)
    {
        super(manager, new ModelZergius(), 0.15F);
        this.addLayer(new LayerGlowingTexture(this, "zergius_glow", false));
    }

    @Override
    public void doRender(EntityZergius entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y - 0.5F, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityZergius entity)
    {
        return RenderZergius.TEXTURE;
    }

    @Override
    protected float handleRotationFloat(EntityZergius entity, float partialTicks)
    {
        float f = entity.oFlap + (entity.wingRotation - entity.oFlap) * partialTicks;
        float f1 = entity.oFlapSpeed + (entity.destPos - entity.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 0.1F) * f1;
    }
}