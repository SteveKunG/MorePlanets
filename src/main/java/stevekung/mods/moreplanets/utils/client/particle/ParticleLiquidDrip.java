package stevekung.mods.moreplanets.utils.client.particle;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.utils.ColorUtils;

@SideOnly(Side.CLIENT)
public class ParticleLiquidDrip extends Particle
{
    private int bobTimer;
    private final boolean isLavaDrip;

    public ParticleLiquidDrip(World world, double x, double y, double z, ColorUtils.RGB rgb, boolean isLavaDrip)
    {
        super(world, x, y, z);
        this.setParticleTextureIndex(113);
        this.setSize(0.01F, 0.01F);
        this.particleRed = rgb.floatRed();
        this.particleGreen = rgb.floatGreen();
        this.particleBlue = rgb.floatBlue();
        this.particleAlpha = rgb.floatAlpha();
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

        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setExpired();
        }
        if (this.onGround)
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
        IBlockState state = this.world.getBlockState(pos);
        Material material = state.getMaterial();

        if (material.isLiquid() || material.isSolid())
        {
            double percent = 0.0D;

            if (state.getBlock() instanceof BlockLiquid)
            {
                percent = BlockLiquid.getLiquidHeightPercent(state.getValue(BlockLiquid.LEVEL));
            }

            double posUnder = MathHelper.floor(this.posY) + 1 - percent;

            if (this.posY < posUnder)
            {
                this.setExpired();
            }
        }
    }
}