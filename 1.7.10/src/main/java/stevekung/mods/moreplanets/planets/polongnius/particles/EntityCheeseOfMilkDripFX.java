/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityCheeseOfMilkDripFX extends EntityFX
{
    private final Material materialType;
    private int bobTimer;

    public EntityCheeseOfMilkDripFX(World par1World, double par2, double par4, double par6, Material par8Material)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.motionX = this.motionY = this.motionZ = 0.0D;
        this.setParticleTextureIndex(113);
        this.setSize(0.01F, 0.01F);
        this.particleGravity = 0.06F;
        this.materialType = par8Material;
        this.bobTimer = 40;
        this.particleMaxAge = (int) (64.0D / (Math.random() * 0.8D + 0.2D));
        this.motionX = this.motionY = this.motionZ = 0.0D;
    }

    @Override
    public int getBrightnessForRender(float par1)
    {
        return super.getBrightnessForRender(par1);
    }

    @Override
    public float getBrightness(float par1)
    {
        return super.getBrightness(par1);
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.materialType == Material.water)
        {
            this.particleGreen = 0.85F;
            this.particleBlue = 0.5F;
            this.particleRed = 1.0F;
            this.particleAlpha = 0.4F;
        }
        else
        {
            this.particleGreen = 0.85F / (40 - this.bobTimer + 16);
            this.particleBlue = 0.5F / (40 - this.bobTimer + 8);
            this.particleRed = 1.0F;
            this.particleAlpha = 0.4F;
        }

        this.motionY -= this.particleGravity;

        if (this.bobTimer-- > 0)
        {
            this.motionX *= 0.02D;
            this.motionY *= 0.02D;
            this.motionZ *= 0.02D;
            this.setParticleTextureIndex(113);
        }
        else
        {
            this.setParticleTextureIndex(112);
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }

        if (this.onGround)
        {
            this.setDead();
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }

        final Material var1 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial();

        if (var1.isLiquid() || var1.isSolid())
        {
            final double var2 = MathHelper.floor_double(this.posY) + 1 - BlockLiquid.getLiquidHeightPercent(this.worldObj.getBlockMetadata(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)));

            if (this.posY < var2)
            {
                this.setDead();
            }
        }
    }
}