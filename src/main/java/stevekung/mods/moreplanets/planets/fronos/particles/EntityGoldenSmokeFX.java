/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityGoldenSmokeFX extends EntityFX
{
    float smokeParticleScale;

    public EntityGoldenSmokeFX(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.motionX *= 0.1000000014901161D;
        this.motionY *= 0.1000000014901161D;
        this.motionZ *= 0.1000000014901161D;
        this.particleRed = 1.0F;
        this.particleGreen = 0.85F;
        this.particleBlue = 0.0F;
        this.particleScale *= 0.75F;
        this.smokeParticleScale = this.particleScale;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.noClip = false;
    }

    @Override
    public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f = (this.particleAge + par2) / this.particleMaxAge * 32.0F;

        if (f < 0.0F)
        {
            f = 0.0F;
        }
        if (f > 1.0F)
        {
            f = 1.0F;
        }
        this.particleScale = this.smokeParticleScale * f;
        super.renderParticle(tessellator, par2, par3, par4, par5, par6, par7);
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

        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.motionY += 0.004D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.posY == this.prevPosY)
        {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }

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