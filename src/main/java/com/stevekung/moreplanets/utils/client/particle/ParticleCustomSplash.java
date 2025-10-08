package com.stevekung.moreplanets.utils.client.particle;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleCustomSplash extends ParticleCustomRain
{
    public ParticleCustomSplash(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, String name)
    {
        super(world, x, y, z, name);
        this.particleGravity = 0.04F;
        this.nextTextureIndexX();

        if (ySpeed == 0.0D && (xSpeed != 0.0D || zSpeed != 0.0D))
        {
            this.motionX = xSpeed;
            this.motionY = ySpeed + 0.1D;
            this.motionZ = zSpeed;
        }
    }
}