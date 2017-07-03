package stevekung.mods.moreplanets.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
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
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        float red = 0.0F, green = 0.0F, blue = 0.0F;
        this.bindEntityTexture(entity);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 90.0F, 0.0F, 1.0F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);

        switch (entity.getLaserType())
        {
        case 0:
            red = 255.0F;
            green = 0.0F;
            blue = 0.0F;
            break;
        case 1:
            red = 120.0F;
            green = 120.0F;
            blue = 230.0F;
            break;
        }

        GlStateManager.color(red / 255.0F, green / 255.0F, blue / 255.0F, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindEntityTexture(entity);
        GlStateManager.enableBlend();
        this.model.renderLaser();
        GlStateManager.disableBlend();

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLaserBullet entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/laser.png");
    }
}