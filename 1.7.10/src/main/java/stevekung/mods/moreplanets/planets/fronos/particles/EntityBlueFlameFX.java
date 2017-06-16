/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.particles;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityBlueFlameFX extends EntityFX
{
    private String texture = "fronos:textures/particles/blue_flame.png";
    private ResourceLocation particles = new ResourceLocation("textures/particle/particles.png");
    private float flameScale;

    public EntityBlueFlameFX(World world, double x, double y, double z)
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
    public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f = (this.particleAge + par2) / this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0F - f * f * 1.0F);
        super.renderParticle(tessellator, par2, par3, par4, par5, par6, par7);

        tessellator.draw();
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(this.texture));
        float sizeFactor = 0.1F * this.particleScale;
        float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - EntityFX.interpPosX);
        float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - EntityFX.interpPosY);
        float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - EntityFX.interpPosZ);
        tessellator.startDrawingQuads();
        tessellator.setBrightness(240);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 1.0F);
        tessellator.addVertexWithUV(var13 - par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 - par5 * sizeFactor - par7 * sizeFactor, 0.0D, 1.0D);
        tessellator.addVertexWithUV(var13 - par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 - par5 * sizeFactor + par7 * sizeFactor, 1.0D, 1.0D);
        tessellator.addVertexWithUV(var13 + par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 + par5 * sizeFactor + par7 * sizeFactor, 1.0D, 0.0D);
        tessellator.addVertexWithUV(var13 + par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 + par5 * sizeFactor - par7 * sizeFactor, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.particles);
        tessellator.startDrawingQuads();
    }

    @Override
    public int getBrightnessForRender(float light)
    {
        float f = (this.particleAge + light) / this.particleMaxAge;
        int i = super.getBrightnessForRender(light);
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
    public float getBrightness(float light)
    {
        float f1 = (this.particleAge + light) / this.particleMaxAge;
        float f2 = super.getBrightness(light);

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