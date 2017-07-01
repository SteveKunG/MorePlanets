package stevekung.mods.moreplanets.module.planets.nibiru.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.ClientRendererUtils;

@SideOnly(Side.CLIENT)
public class ParticleAlienBerry extends Particle
{
    public ParticleAlienBerry(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
        this.motionX *= 0.01999999955296516D;
        this.motionY *= 0.01999999955296516D;
        this.motionZ *= 0.01999999955296516D;
        this.particleMaxAge = (int)(20.0D / (Math.random() * 0.8D + 0.2D));
        this.setSize(0.02F, 0.02F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks)
    {
        int i = super.getBrightnessForRender(partialTicks);
        int k = i >> 16 & 255;
        return 240 | k << 16;
    }

    @Override
    public void renderParticle(VertexBuffer worldrenderer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        Tessellator tessellator = Tessellator.getInstance();
        super.renderParticle(worldrenderer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        tessellator.draw();
        ClientRendererUtils.bindTexture("moreplanets:textures/particle/alien_berry.png");
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
        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.99D;
        this.motionY *= 0.99D;
        this.motionZ *= 0.99D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setExpired();
        }
    }
}