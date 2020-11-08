package stevekung.mods.moreplanets.planets.nibiru.client.renderer.entity;

import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityTerrasquid;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderTerrasquid extends RenderLiving<EntityTerrasquid>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/terrasquid.png");

    public RenderTerrasquid(RenderManager manager)
    {
        super(manager, new ModelSquid(), 0.7F);
        this.addLayer(new LayerGlowingTexture<>(this, "terrasquid_glow_0", true));
        this.addLayer(new LayerGlowingTexture<>(this, "terrasquid_glow_1", false));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTerrasquid entity)
    {
        return RenderTerrasquid.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityTerrasquid entity, float rotation, float interpolateRot, float partialTicks)
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
    protected float handleRotationFloat(EntityTerrasquid entity, float partialTicks)
    {
        return entity.lastTentacleAngle + (entity.tentacleAngle - entity.lastTentacleAngle) * partialTicks;
    }
}