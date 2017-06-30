package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedSquid;

@SideOnly(Side.CLIENT)
public class RenderInfectedSquid extends RenderLiving<EntityInfectedSquid>
{
    public RenderInfectedSquid(RenderManager manager)
    {
        super(manager, new ModelSquid(), 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedSquid entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/infected_squid.png");
    }

    @Override
    protected void rotateCorpse(EntityInfectedSquid entity, float rotation, float interpolateRot, float partialTicks)
    {
        float f = entity.prevSquidPitch + (entity.squidPitch - entity.prevSquidPitch) * partialTicks;
        float f1 = entity.prevSquidYaw + (entity.squidYaw - entity.prevSquidYaw) * partialTicks;
        GlStateManager.translate(0.0F, 0.5F, 0.0F);
        GlStateManager.rotate(180.0F - interpolateRot, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, -1.2F, 0.0F);
    }

    @Override
    protected float handleRotationFloat(EntityInfectedSquid entity, float partialTicks)
    {
        return entity.lastTentacleAngle + (entity.tentacleAngle - entity.lastTentacleAngle) * partialTicks;
    }
}