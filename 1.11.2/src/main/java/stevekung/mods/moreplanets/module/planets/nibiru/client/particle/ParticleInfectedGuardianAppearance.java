package stevekung.mods.moreplanets.module.planets.nibiru.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedElderGuardian;

@SideOnly(Side.CLIENT)
public class ParticleInfectedGuardianAppearance extends Particle
{
    private EntityLivingBase entity;

    public ParticleInfectedGuardianAppearance(World world, double x, double y, double z)
    {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.motionX = this.motionY = this.motionZ = 0.0D;
        this.particleGravity = 0.0F;
        this.particleMaxAge = 30;
    }

    @Override
    public int getFXLayer()
    {
        return 3;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.entity == null)
        {
            EntityInfectedElderGuardian entityelderguardian = new EntityInfectedElderGuardian(this.world);
            entityelderguardian.setGhost();
            this.entity = entityelderguardian;
        }
    }

    @Override
    public void renderParticle(VertexBuffer worldrenderer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        if (this.entity != null)
        {
            RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
            rendermanager.setRenderPosition(Particle.interpPosX, Particle.interpPosY, Particle.interpPosZ);
            float f = 0.42553192F;
            float f1 = (this.particleAge + partialTicks) / this.particleMaxAge;
            GlStateManager.depthMask(true);
            GlStateManager.enableBlend();
            GlStateManager.enableDepth();
            GlStateManager.blendFunc(770, 771);
            float f2 = 240.0F;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f2, f2);
            GlStateManager.pushMatrix();
            float f3 = 0.05F + 0.5F * MathHelper.sin(f1 * (float)Math.PI);
            GlStateManager.color(1.0F, 1.0F, 1.0F, f3);
            GlStateManager.translate(0.0F, 1.8F, 0.0F);
            GlStateManager.rotate(180.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(60.0F - 150.0F * f1 - entity.rotationPitch, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0.0F, -0.4F, -1.5F);
            GlStateManager.scale(f, f, f);
            this.entity.rotationYaw = this.entity.prevRotationYaw = 0.0F;
            this.entity.rotationYawHead = this.entity.prevRotationYawHead = 0.0F;
            rendermanager.doRenderEntity(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, false);
            GlStateManager.popMatrix();
            GlStateManager.enableDepth();
        }
    }
}