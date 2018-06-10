package stevekung.mods.moreplanets.moons.koentus.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleGravityHarvester extends Particle
{
    private final float portalParticleScale;
    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;
    private final boolean upward;

    public ParticleGravityHarvester(World world, double xCoord, double yCoord, double zCoord, boolean upward)
    {
        super(world, xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
        this.posX = xCoord;
        this.posY = yCoord;
        this.posZ = zCoord;
        this.portalPosX = this.posX;
        this.portalPosY = this.posY;
        this.portalPosZ = this.posZ;
        float f = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
        this.portalParticleScale = this.particleScale;

        if (this.rand.nextInt(3) == 0)
        {
            this.particleRed = 88 / 255.0F * f;
            this.particleGreen = 88 / 255.0F * f;
            this.particleBlue = 88 / 255.0F * f;
        }
        else if (this.rand.nextInt(2) == 0)
        {
            this.particleRed = 206 / 255.0F * f;
            this.particleGreen = 105 / 255.0F * f;
            this.particleBlue = 10 / 255.0F * f;
        }
        else
        {
            this.particleRed = 16 / 255.0F * f;
            this.particleGreen = 136 / 255.0F * f;
            this.particleBlue = 207 / 255.0F * f;
        }
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 120;
        this.upward = upward;
        this.setParticleTextureIndex((int)(Math.random() * 8.0D));
    }

    @Override
    public void renderParticle(BufferBuilder worldRenderer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float f = (this.particleAge + partialTicks) / this.particleMaxAge;
        f = 1.0F - f;
        f = f * f;
        f = 1.0F - f;
        this.particleScale = this.portalParticleScale * f;
        super.renderParticle(worldRenderer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    @Override
    public void move(double x, double y, double z)
    {
        this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float f = (float)this.particleAge / (float)this.particleMaxAge;
        float f1 = -f + f * f * 2.0F;
        float f2 = 1.0F - f1;
        this.posX = this.portalPosX + this.motionX * f2;
        this.posY = this.upward ? this.portalPosY + this.motionY * f2 + (1.0F + f) : this.portalPosY + this.motionY * f2 + (1.0F - f);
        this.posZ = this.portalPosZ + this.motionZ * f2;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }
    }
}