package stevekung.mods.moreplanets.planets.nibiru.client.particle;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;

@SideOnly(Side.CLIENT)
public class ParticleAlienBerryLeavesSpark extends Particle
{
    public ParticleAlienBerryLeavesSpark(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("moreplanets:particle/alien_berry_leaves_spark"));
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
        this.motionX *= 0.01999999955296516D;
        this.motionY *= 0.01999999955296516D;
        this.motionZ *= 0.01999999955296516D;
        this.particleMaxAge = (int)(100.0D / (Math.random() * 0.8D + 0.2D));
        this.particleAlpha = this.particleMaxAge / 255.0F;
        this.setSize(0.02F, 0.02F);
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        GlStateManager.depthMask(true);
        super.renderParticle(buffer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        GlStateManager.depthMask(false);
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
    public void onUpdate()
    {
        this.particleAlpha = this.particleMaxAge / 255.0F;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.99D;
        this.motionY *= 0.99D;
        this.motionZ *= 0.99D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setExpired();
        }

        BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
        IBlockState state = this.world.getBlockState(pos.up());

        if (state.getBlock() != MPBlocks.ALIEN_BERRY_OAK_LEAVES)
        {
            this.setExpired();
        }
    }
}