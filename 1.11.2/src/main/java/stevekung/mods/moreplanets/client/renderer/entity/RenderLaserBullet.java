package stevekung.mods.moreplanets.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.model.ModelLaser;
import stevekung.mods.moreplanets.entity.projectile.EntityLaserBullet;

@SideOnly(Side.CLIENT)
public class RenderLaserBullet extends Render<EntityLaserBullet>
{
    private ModelLaser model = new ModelLaser();

    public RenderLaserBullet(RenderManager manager)
    {
        super(manager);
    }

    @Override
    public void doRender(EntityLaserBullet entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.bindEntityTexture(entity);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 90.0F, 0.0F, 1.0F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 0.0F, 0.0F);
        GlStateManager.blendFunc(770, 771);
        this.model.renderLaser();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLaserBullet entity)
    {
        ResourceLocation res;

        switch (entity.getLaserType())
        {
        case 0:
        default:
            res = new ResourceLocation("moreplanets:textures/entity/laser.png");
            break;
        }
        return res;
    }
}