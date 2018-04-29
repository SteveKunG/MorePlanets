package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedGuardian;
import stevekung.mods.moreplanets.utils.client.model.ModelGuardianMP;

@SideOnly(Side.CLIENT)
public class RenderInfectedGuardian extends RenderLiving<EntityInfectedGuardian>
{
    public RenderInfectedGuardian(RenderManager manager)
    {
        super(manager, new ModelGuardianMP(), 0.5F);
    }

    @Override
    public boolean shouldRender(EntityInfectedGuardian entity, ICamera camera, double camX, double camY, double camZ)
    {
        if (super.shouldRender(entity, camera, camX, camY, camZ))
        {
            return true;
        }
        else
        {
            if (entity.hasTargetedEntity())
            {
                EntityLivingBase entitylivingbase = entity.getTargetedEntity();

                if (entitylivingbase != null)
                {
                    Vec3d vec3 = this.getPosition(entitylivingbase, entitylivingbase.height * 0.5D, 1.0F);
                    Vec3d vec31 = this.getPosition(entity, entity.getEyeHeight(), 1.0F);

                    if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(vec31.x, vec31.y, vec31.z, vec3.x, vec3.y, vec3.z)))
                    {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private Vec3d getPosition(EntityLivingBase entity, double height, float partialTicks)
    {
        double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks;
        double d1 = height + entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks;
        double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks;
        return new Vec3d(d0, d1, d2);
    }

    @Override
    public void doRender(EntityInfectedGuardian entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        EntityLivingBase entitylivingbase = entity.getTargetedEntity();

        if (entitylivingbase != null)
        {
            float f = entity.getAttackAnimationScale(partialTicks);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder worldrenderer = tessellator.getBuffer();
            this.bindTexture(new ResourceLocation("textures/entity/guardian_beam.png"));
            GlStateManager.glTexParameterf(3553, 10242, 10497.0F);
            GlStateManager.glTexParameterf(3553, 10243, 10497.0F);
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            float f1 = 240.0F;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f1, f1);
            GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
            float f2 = entity.world.getTotalWorldTime() + partialTicks;
            float f3 = f2 * 0.5F % 1.0F;
            float f4 = entity.getEyeHeight();
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)x, (float)y + f4, (float)z);
            Vec3d vec3 = this.getPosition(entitylivingbase, entitylivingbase.height * 0.5D, partialTicks);
            Vec3d vec31 = this.getPosition(entity, f4, partialTicks);
            Vec3d vec32 = vec3.subtract(vec31);
            double d0 = vec32.lengthVector() + 1.0D;
            vec32 = vec32.normalize();
            float f5 = (float)Math.acos(vec32.y);
            float f6 = (float)Math.atan2(vec32.z, vec32.x);
            GlStateManager.rotate(((float)Math.PI / 2F + -f6) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(f5 * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            int i = 1;
            double d1 = f2 * 0.05D * (1.0D - (i & 1) * 2.5D);
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            float f7 = f * f;
            double d2 = i * 0.2D;
            double d3 = d2 * 1.41D;
            double d4 = 0.0D + Math.cos(d1 + 2.356194490192345D) * d3;
            double d5 = 0.0D + Math.sin(d1 + 2.356194490192345D) * d3;
            double d6 = 0.0D + Math.cos(d1 + Math.PI / 4D) * d3;
            double d7 = 0.0D + Math.sin(d1 + Math.PI / 4D) * d3;
            double d8 = 0.0D + Math.cos(d1 + 3.9269908169872414D) * d3;
            double d9 = 0.0D + Math.sin(d1 + 3.9269908169872414D) * d3;
            double d10 = 0.0D + Math.cos(d1 + 5.497787143782138D) * d3;
            double d11 = 0.0D + Math.sin(d1 + 5.497787143782138D) * d3;
            double d12 = 0.0D + Math.cos(d1 + Math.PI) * d2;
            double d13 = 0.0D + Math.sin(d1 + Math.PI) * d2;
            double d14 = 0.0D + Math.cos(d1 + 0.0D) * d2;
            double d15 = 0.0D + Math.sin(d1 + 0.0D) * d2;
            double d16 = 0.0D + Math.cos(d1 + Math.PI / 2D) * d2;
            double d17 = 0.0D + Math.sin(d1 + Math.PI / 2D) * d2;
            double d18 = 0.0D + Math.cos(d1 + Math.PI * 3D / 2D) * d2;
            double d19 = 0.0D + Math.sin(d1 + Math.PI * 3D / 2D) * d2;
            double d22 = -1.0F + f3;
            double d23 = d0 * (0.5D / d2) + d22;
            double d24 = 0.0D;

            if (entity.ticksExisted % 2 == 0)
            {
                d24 = 0.5D;
            }
            if (f7 > 0.9F)
            {
                f7 = 0.9F;
            }
            int red = 144 + (int)(f7 * 60.0F);
            int green = 56 + (int)(f7 * 50.0F);
            int blue = 34 + (int)(f7 * 50.0F);
            worldrenderer.pos(d12, d0, d13).tex(0.4999D, d23).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d12, 0.0D, d13).tex(0.4999D, d22).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d14, 0.0D, d15).tex(0.0D, d22).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d14, d0, d15).tex(0.0D, d23).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d16, d0, d17).tex(0.4999D, d23).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d16, 0.0D, d17).tex(0.4999D, d22).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d18, 0.0D, d19).tex(0.0D, d22).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d18, d0, d19).tex(0.0D, d23).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d4, d0, d5).tex(0.5D, d24 + 0.5D).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d6, d0, d7).tex(1.0D, d24 + 0.5D).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d10, d0, d11).tex(1.0D, d24).color(red, green, blue, 255).endVertex();
            worldrenderer.pos(d8, d0, d9).tex(0.5D, d24).color(red, green, blue, 255).endVertex();
            tessellator.draw();
            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            GlStateManager.depthMask(false);
            GlStateManager.popMatrix();
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedGuardian entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/infected_guardian.png");
    }
}