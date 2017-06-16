/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.particles;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityMCLavaFX extends EntityFX
{
    private String texture;
    private float lavaParticleScale;

    public EntityMCLavaFX(World world, double x, double y, double z, String texture)
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
        this.noClip = false;
        this.texture = texture + ".png";
    }

    @Override
    public int getBrightnessForRender(float partialTicks)
    {
        float f1 = (this.particleAge + partialTicks) / this.particleMaxAge;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }
        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }
        int i = super.getBrightnessForRender(partialTicks);
        short short1 = 240;
        int j = i >> 16 & 255;
        return short1 | j << 16;
    }

    @Override
    public float getBrightness(float partialTicks)
    {
        return 1.0F;
    }

    @Override
    public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7)
    {
        float f6 = (this.particleAge + partialTicks) / this.particleMaxAge;
        this.particleScale = this.lavaParticleScale * (1.0F - f6 * f6);
        super.renderParticle(tessellator, partialTicks, par3, par4, par5, par6, par7);

        tessellator.draw();
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.texture));
        float sizeFactor = 0.1F * this.particleScale;
        float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - EntityFX.interpPosX);
        float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - EntityFX.interpPosY);
        float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - EntityFX.interpPosZ);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 1.0F);
        tessellator.addVertexWithUV(var13 - par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 - par5 * sizeFactor - par7 * sizeFactor, 0.0D, 1.0D);
        tessellator.addVertexWithUV(var13 - par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 - par5 * sizeFactor + par7 * sizeFactor, 1.0D, 1.0D);
        tessellator.addVertexWithUV(var13 + par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 + par5 * sizeFactor + par7 * sizeFactor, 1.0D, 0.0D);
        tessellator.addVertexWithUV(var13 + par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 + par5 * sizeFactor - par7 * sizeFactor, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/particle/particles.png"));
        tessellator.startDrawingQuads();
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

        float f = (float)this.particleAge / (float)this.particleMaxAge;

        if (this.rand.nextFloat() > f)
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
        }

        this.motionY -= 0.03D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9990000128746033D;
        this.motionY *= 0.9990000128746033D;
        this.motionZ *= 0.9990000128746033D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}