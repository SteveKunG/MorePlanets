package stevekung.mods.moreplanets.planets.nibiru.client.renderer.entity;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedChicken;

@SideOnly(Side.CLIENT)
public class RenderInfectedChicken extends RenderLiving<EntityInfectedChicken>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_chicken.png");

    public RenderInfectedChicken(RenderManager manager)
    {
        super(manager, new ModelChicken(), 0.3F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedChicken entity)
    {
        return RenderInfectedChicken.TEXTURE;
    }

    @Override
    protected float handleRotationFloat(EntityInfectedChicken entity, float partialTicks)
    {
        float f = entity.oFlap + (entity.wingRotation - entity.oFlap) * partialTicks;
        float f1 = entity.oFlapSpeed + (entity.destPos - entity.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}