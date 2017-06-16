package stevekung.mods.moreplanets.asteroids.darkasteroids.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityDarkPortalFX extends EntityFX
{
    private float portalParticleScale;
    private double portalPosX;
    private double portalPosY;
    private double portalPosZ;

    public EntityDarkPortalFX(World world, double x, double y, double z, double moX, double moY, double moZ)
    {
        super(world, x, y, z, moX, moY, moZ);
        this.motionX = moX;
        this.motionY = moY;
        this.motionZ = moZ;
        this.portalPosX = this.posX = x;
        this.portalPosY = this.posY = y;
        this.portalPosZ = this.posZ = z;
        this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
        this.particleRed = 0.1F;
        this.particleGreen = 0.1F;
        this.particleBlue = 0.1F;
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
        this.noClip = true;
        this.setParticleTextureIndex((int)(Math.random() * 8.0D));
    }

    @Override
    public void renderParticle(Tessellator p_70539_1_, float partialTicks, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
    {
        float f6 = (this.particleAge + partialTicks) / this.particleMaxAge;
        f6 = 1.0F - f6;
        f6 *= f6;
        f6 = 1.0F - f6;
        this.particleScale = this.portalParticleScale * f6;
        super.renderParticle(p_70539_1_, partialTicks, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
    }

    @Override
    public int getBrightnessForRender(float partialTicks)
    {
        int i = super.getBrightnessForRender(partialTicks);
        float f1 = (float)this.particleAge / (float)this.particleMaxAge;
        f1 *= f1;
        f1 *= f1;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int)(f1 * 15.0F * 16.0F);

        if (k > 240)
        {
            k = 240;
        }
        return j | k << 16;
    }

    @Override
    public float getBrightness(float partialTicks)
    {
        float f1 = super.getBrightness(partialTicks);
        float f2 = (float)this.particleAge / (float)this.particleMaxAge;
        f2 = f2 * f2 * f2 * f2;
        return f1 * (1.0F - f2) + f2;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float f = (float)this.particleAge / (float)this.particleMaxAge;
        float f1 = f;
        f = -f + f * f * 2.0F;
        f = 1.0F - f;
        this.posX = this.portalPosX + this.motionX * f;
        this.posY = this.portalPosY + this.motionY * f + (1.0F - f1);
        this.posZ = this.portalPosZ + this.motionZ * f;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
    }
}