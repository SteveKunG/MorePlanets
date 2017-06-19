package stevekung.mods.moreplanets.module.planets.nibiru.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityInfectedSporeFX extends EntityFX
{
    public EntityInfectedSporeFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.particleRed = 0.4F;
        this.particleGreen = 0.1F;
        this.particleBlue = 0.0F;
        this.setParticleTextureIndex(0);
        this.setSize(0.01F, 0.01F);
        this.particleScale *= this.rand.nextFloat() * 0.8F + 0.2F;
        this.motionX = motionX * 0.0D;
        this.motionY = motionY * 0.0D;
        this.motionZ = motionZ * 0.0D;
        this.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionY += 0.001D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }
    }
}