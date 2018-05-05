package stevekung.mods.moreplanets.planets.diona.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.utils.RenderUtils;

@SideOnly(Side.CLIENT)
public class ParticleCrystallizedFlame extends Particle
{
    private float flameScale;

    public ParticleCrystallizedFlame(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.motionX = this.motionX * 0.009999999776482582D + this.motionX;
        this.motionY = this.motionY * 0.009999999776482582D + this.motionY;
        this.motionZ = this.motionZ * 0.009999999776482582D + this.motionZ;
        x += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        y += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        z += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        this.flameScale = this.particleScale;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
    }

    @Override
    public void move(double x, double y, double z)
    {
        this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }

    @Override
    public void renderParticle(BufferBuilder worldrenderer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        Tessellator tessellator = Tessellator.getInstance();
        float f = (this.particleAge + partialTicks) / this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0F - f * f * 1.0F);
        super.renderParticle(worldrenderer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        tessellator.draw();
        RenderUtils.bindTexture("moreplanets:textures/particle/crystallized_flame.png");
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
        RenderUtils.drawDefaultParticlesTexture(worldrenderer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks)
    {
        float f = (this.particleAge + partialTicks) / this.particleMaxAge;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        int i = super.getBrightnessForRender(partialTicks);
        int j = i & 255;
        int k = i >> 16 & 255;
        j = j + (int)(f * 15.0F * 16.0F);

        if (j > 240)
        {
            j = 240;
        }
        return j | k << 16;
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

        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.canCollide)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}