package stevekung.mods.moreplanets.util.client.particle;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleLiquidDrip extends Particle
{
    private int bobTimer;
    private boolean isLavaDrip;
    private float newRed;
    private float newGreen;
    private float newBlue;
    private float newAlpha;

    public ParticleLiquidDrip(World world, double x, double y, double z, float r, float g, float b, float alpha, boolean isLavaDrip)
    {
        super(world, x, y, z);
        this.setParticleTextureIndex(113);
        this.setSize(0.01F, 0.01F);
        this.particleRed = r;
        this.particleGreen = g;
        this.particleBlue = b;
        this.particleAlpha = alpha;
        this.newRed = r;
        this.newGreen = g;
        this.newBlue = b;
        this.newAlpha = alpha;
        this.isLavaDrip = isLavaDrip;
        this.particleGravity = 0.06F;
        this.bobTimer = 40;
        this.particleMaxAge = (int) (64.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks)
    {
        return this.isLavaDrip ? 257 : super.getBrightnessForRender(partialTicks);
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.particleRed = this.newRed;
        this.particleGreen = this.newGreen;
        this.particleBlue = this.newBlue;
        this.particleAlpha = this.newAlpha;
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
            this.setExpired();
        }
        if (this.isCollided)
        {
            if (!this.isLavaDrip)
            {
                this.setExpired();
            }
            else
            {
                this.setParticleTextureIndex(114);
            }
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }

        BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
        IBlockState state = this.worldObj.getBlockState(pos);
        Material material = state.getMaterial();

        if (material.isLiquid() || material.isSolid())
        {
            double d0 = 0.0D;

            if (state.getBlock() instanceof BlockLiquid)
            {
                d0 = BlockLiquid.getLiquidHeightPercent(state.getValue(BlockLiquid.LEVEL).intValue());
            }

            double d1 = MathHelper.floor_double(this.posY) + 1 - d0;

            if (this.posY < d1)
            {
                this.setExpired();
            }
        }
    }
}