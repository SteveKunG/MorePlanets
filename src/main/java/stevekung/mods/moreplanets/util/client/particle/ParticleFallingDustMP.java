package stevekung.mods.moreplanets.util.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleFallingDustMP extends Particle
{
    float oSize;
    final float rotSpeed;

    public ParticleFallingDustMP(World world, double x, double y, double z, int color)
    {
        super(world, x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        this.particleRed = r;
        this.particleGreen = g;
        this.particleBlue = b;
        this.particleScale *= 0.75F;
        this.particleScale *= 0.9F;
        this.oSize = this.particleScale;
        this.particleMaxAge = (int)(32.0D / (Math.random() * 0.8D + 0.2D));
        this.particleMaxAge = (int)(this.particleMaxAge * 0.9F);
        this.rotSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.particleAngle = (float)Math.random() * ((float)Math.PI * 2F);
    }

    @Override
    public void renderParticle(BufferBuilder worldRenderer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float f = (this.particleAge + partialTicks) / this.particleMaxAge * 32.0F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        this.particleScale = this.oSize * f;
        super.renderParticle(worldRenderer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
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

        this.prevParticleAngle = this.particleAngle;
        this.particleAngle += (float)Math.PI * this.rotSpeed * 2.0F;

        if (this.canCollide)
        {
            this.prevParticleAngle = this.particleAngle = 0.0F;
        }

        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionY -= 0.003000000026077032D;
        this.motionY = Math.max(this.motionY, -0.14000000059604645D);
    }
}