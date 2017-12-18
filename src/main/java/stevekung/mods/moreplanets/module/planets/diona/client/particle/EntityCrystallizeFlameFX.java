package stevekung.mods.moreplanets.module.planets.diona.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.ClientRendererUtils;

@SideOnly(Side.CLIENT)
public class EntityCrystallizeFlameFX extends EntityFX
{
    private float flameScale;

    public EntityCrystallizeFlameFX(World world, double x, double y, double z)
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
        this.noClip = true;
    }

    @Override
    public void renderParticle(WorldRenderer worldrenderer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        Tessellator tessellator = Tessellator.getInstance();
        float f = (this.particleAge + partialTicks) / this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0F - f * f * 1.0F);
        super.renderParticle(worldrenderer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        tessellator.draw();
        ClientRendererUtils.bindTexture("moreplanets:textures/particle/crystallize_flame.png");
        float sizeFactor = 0.1F * this.particleScale;
        float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - EntityFX.interpPosX);
        float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - EntityFX.interpPosY);
        float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - EntityFX.interpPosZ);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
        worldrenderer.pos(var13 - rotationX * sizeFactor - rotationXY * sizeFactor, var14 - rotationZ * sizeFactor, var15 - rotationYZ * sizeFactor - rotationXZ * sizeFactor).tex(0.0D, 1.0D).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 255).endVertex();
        worldrenderer.pos(var13 - rotationX * sizeFactor + rotationXY * sizeFactor, var14 + rotationZ * sizeFactor, var15 - rotationYZ * sizeFactor + rotationXZ * sizeFactor).tex(1.0D, 1.0D).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 255).endVertex();
        worldrenderer.pos(var13 + rotationX * sizeFactor + rotationXY * sizeFactor, var14 + rotationZ * sizeFactor, var15 + rotationYZ * sizeFactor + rotationXZ * sizeFactor).tex(1.0D, 0.0D).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 255).endVertex();
        worldrenderer.pos(var13 + rotationX * sizeFactor - rotationXY * sizeFactor, var14 - rotationZ * sizeFactor, var15 + rotationYZ * sizeFactor - rotationXZ * sizeFactor).tex(0.0D, 0.0D).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 255).endVertex();
        tessellator.draw();
        ClientRendererUtils.drawDefaultParticlesTexture(worldrenderer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks)
    {
        float f = (this.particleAge + partialTicks) / this.particleMaxAge;
        int i = super.getBrightnessForRender(partialTicks);
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        j += (int)(f * 15.0F * 16.0F);

        if (f < 0.0F)
        {
            f = 0.0F;
        }
        if (f > 1.0F)
        {
            f = 1.0F;
        }
        if (j > 240)
        {
            j = 240;
        }
        return j | k << 16;
    }

    @Override
    public float getBrightness(float partialTicks)
    {
        float f1 = (this.particleAge + partialTicks) / this.particleMaxAge;
        float f2 = super.getBrightness(partialTicks);

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }
        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }
        return f2 * f1 + (1.0F - f1);
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}