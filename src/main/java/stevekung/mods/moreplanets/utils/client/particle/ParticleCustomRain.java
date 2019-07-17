package stevekung.mods.moreplanets.utils.client.particle;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleCustomRain extends Particle
{
    public ParticleCustomRain(World world, double x, double y, double z, String name)
    {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.30000001192092896D;
        this.motionY = Math.random() * 0.20000000298023224D + 0.10000000149011612D;
        this.motionZ *= 0.30000001192092896D;
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("moreplanets:particle/" + name + "_" + this.rand.nextInt(4)));
        this.setSize(0.01F, 0.01F);
        this.particleGravity = 0.06F;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    public int getFXLayer()
    {
        return 1;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= this.particleGravity;
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
            if (Math.random() < 0.5D)
            {
                this.setExpired();
            }
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }

        BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
        IBlockState state = this.world.getBlockState(pos);
        Material material = state.getMaterial();

        if (material.isLiquid() || material.isSolid())
        {
            double d0;

            if (state.getBlock() instanceof BlockLiquid)
            {
                d0 = 1.0F - BlockLiquid.getLiquidHeightPercent(state.getValue(BlockLiquid.LEVEL).intValue());
            }
            else
            {
                d0 = state.getBoundingBox(this.world, pos).maxY;
            }

            double d1 = MathHelper.floor(this.posY) + d0;

            if (this.posY < d1)
            {
                this.setExpired();
            }
        }
    }
}