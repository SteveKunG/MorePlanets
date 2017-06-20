package stevekung.mods.moreplanets.util.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.ClientRendererUtils;

@SideOnly(Side.CLIENT)
public class ParticleLavaMC extends Particle
{
    private String texture;
    private float lavaParticleScale;

    public ParticleLavaMC(World world, double x, double y, double z, String texture)
    {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.800000011920929D;
        this.motionY *= 0.800000011920929D;
        this.motionZ *= 0.800000011920929D;
        this.motionY = this.rand.nextFloat() * 0.4F + 0.05F;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
        this.lavaParticleScale = this.particleScale;
        this.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.texture = "moreplanets:textures/particle/" + texture + ".png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks)
    {
        int i = super.getBrightnessForRender(partialTicks);
        int j = 240;
        int k = i >> 16 & 255;
        return 240 | k << 16;
    }

    @Override
    public void renderParticle(VertexBuffer worldrenderer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        Tessellator tessellator = Tessellator.getInstance();
        float f6 = (this.particleAge + partialTicks) / this.particleMaxAge;
        this.particleScale = this.lavaParticleScale * (1.0F - f6 * f6);
        super.renderParticle(worldrenderer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        tessellator.draw();
        ClientRendererUtils.bindTexture(this.texture);
        float sizeFactor = 0.1F * this.particleScale;
        float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - Particle.interpPosX);
        float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - Particle.interpPosY);
        float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - Particle.interpPosZ);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
        worldrenderer.pos(var13 - rotationX * sizeFactor - rotationXY * sizeFactor, var14 - rotationZ * sizeFactor, var15 - rotationYZ * sizeFactor - rotationXZ * sizeFactor).tex(0.0D, 1.0D).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 255).endVertex();
        worldrenderer.pos(var13 - rotationX * sizeFactor + rotationXY * sizeFactor, var14 + rotationZ * sizeFactor, var15 - rotationYZ * sizeFactor + rotationXZ * sizeFactor).tex(1.0D, 1.0D).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 255).endVertex();
        worldrenderer.pos(var13 + rotationX * sizeFactor + rotationXY * sizeFactor, var14 + rotationZ * sizeFactor, var15 + rotationYZ * sizeFactor + rotationXZ * sizeFactor).tex(1.0D, 0.0D).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 255).endVertex();
        worldrenderer.pos(var13 + rotationX * sizeFactor - rotationXY * sizeFactor, var14 - rotationZ * sizeFactor, var15 + rotationYZ * sizeFactor - rotationXZ * sizeFactor).tex(0.0D, 0.0D).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 255).endVertex();
        tessellator.draw();
        ClientRendererUtils.drawDefaultParticlesTexture(worldrenderer);
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }

        float f = (float)this.particleAge / (float)this.particleMaxAge;

        if (this.rand.nextFloat() > f)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
        }

        this.motionY -= 0.03D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9990000128746033D;
        this.motionY *= 0.9990000128746033D;
        this.motionZ *= 0.9990000128746033D;

        if (this.isCollided)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}