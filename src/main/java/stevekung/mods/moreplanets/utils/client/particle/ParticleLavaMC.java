package stevekung.mods.moreplanets.utils.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleLavaMC extends Particle
{
    private float lavaParticleScale;

    public ParticleLavaMC(World world, double x, double y, double z, String texture)
    {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("moreplanets:particle/" + texture));
        this.motionX *= 0.800000011920929D;
        this.motionY *= 0.800000011920929D;
        this.motionZ *= 0.800000011920929D;
        this.motionY = this.rand.nextFloat() * 0.4F + 0.05F;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleScale *= this.rand.nextFloat() * 1.8F + 0.2F;
        this.lavaParticleScale = this.particleScale;
        this.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    public int getFXLayer()
    {
        return 1;
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
    public void renderParticle(BufferBuilder buffer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float scale = (this.particleAge + partialTicks) / this.particleMaxAge;
        this.particleScale = this.lavaParticleScale * (0.8F - scale * scale);
        super.renderParticle(buffer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
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
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
        }

        this.motionY -= 0.03D;
        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9990000128746033D;
        this.motionY *= 0.9990000128746033D;
        this.motionZ *= 0.9990000128746033D;

        if (this.canCollide)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}