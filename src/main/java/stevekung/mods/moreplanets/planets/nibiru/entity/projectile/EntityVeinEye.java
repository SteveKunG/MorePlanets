package stevekung.mods.moreplanets.planets.nibiru.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;

public class EntityVeinEye extends Entity
{
    private double targetX;
    private double targetY;
    private double targetZ;
    private int despawnTimer;
    private boolean shatterOrDrop;

    public EntityVeinEye(World world)
    {
        super(world);
        this.setSize(0.25F, 0.25F);
    }

    public EntityVeinEye(World world, double x, double y, double z)
    {
        super(world);
        this.despawnTimer = 0;
        this.setSize(0.25F, 0.25F);
        this.setPosition(x, y, z);
    }

    public void moveTowards(BlockPos pos)
    {
        double d0 = pos.getX();
        int i = pos.getY();
        double d1 = pos.getZ();
        double d2 = d0 - this.posX;
        double d3 = d1 - this.posZ;
        float f = MathHelper.sqrt(d2 * d2 + d3 * d3);

        if (f > 12.0F)
        {
            this.targetX = this.posX + d2 / f * 12.0D;
            this.targetZ = this.posZ + d3 / f * 12.0D;
            this.targetY = this.posY + 8.0D;
        }
        else
        {
            this.targetX = d0;
            this.targetY = i;
            this.targetZ = d1;
        }
        this.despawnTimer = 0;
        this.shatterOrDrop = this.rand.nextInt(5) > 0;
    }

    @Override
    protected void entityInit() {}

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;

        if (Double.isNaN(d0))
        {
            d0 = 4.0D;
        }
        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z)
    {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt(x * x + z * z);
            this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(y, f) * 180.0D / Math.PI);
        }
    }

    @Override
    public void onUpdate()
    {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        for (this.rotationPitch = (float)(MathHelper.atan2(this.motionY, f) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {}

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }
        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }
        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

        if (!this.world.isRemote)
        {
            double d0 = this.targetX - this.posX;
            double d1 = this.targetZ - this.posZ;
            float f1 = (float)Math.sqrt(d0 * d0 + d1 * d1);
            float f2 = (float)MathHelper.atan2(d1, d0);
            double d2 = f + (f1 - f) * 0.0025D;

            if (f1 < 1.0F)
            {
                d2 *= 0.8D;
                this.motionY *= 0.8D;
            }

            this.motionX = Math.cos(f2) * d2;
            this.motionZ = Math.sin(f2) * d2;

            if (this.posY < this.targetY)
            {
                this.motionY += (1.0D - this.motionY) * 0.014999999664723873D;
            }
            else
            {
                this.motionY += (-1.0D - this.motionY) * 0.014999999664723873D;
            }
        }

        float f3 = 0.25F;

        if (this.isInWater())
        {
            for (int i = 0; i < 4; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * f3, this.posY - this.motionY * f3, this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ, new int[0]);
            }
        }
        else
        {
            this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX - this.motionX * f3 + this.rand.nextDouble() * 0.6D - 0.3D, this.posY - this.motionY * f3 - 0.5D, this.posZ - this.motionZ * f3 + this.rand.nextDouble() * 0.6D - 0.3D, this.motionX, this.motionY, this.motionZ, new int[] { Block.getIdFromBlock(MPBlocks.INFECTED_VINES) });
        }

        if (!this.world.isRemote)
        {
            this.setPosition(this.posX, this.posY, this.posZ);
            ++this.despawnTimer;

            if (this.despawnTimer > 80 && !this.world.isRemote)
            {
                this.setDead();

                if (this.shatterOrDrop)
                {
                    this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(NibiruItems.VEIN_EYE)));
                }
                else
                {
                    double d0 = new BlockPos(this).getX() + 0.5D;
                    double d1 = new BlockPos(this).getY();
                    double d2 = new BlockPos(this).getZ() + 0.5D;

                    for (int j = 0; j < 8; ++j)
                    {
                        this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, d0, d1, d2, this.rand.nextGaussian() * 0.15D, this.rand.nextDouble() * 0.2D, this.rand.nextGaussian() * 0.15D, new int[] {Item.getIdFromItem(NibiruItems.VEIN_EYE)});
                    }
                    for (double d18 = 0.0D; d18 < Math.PI * 2D; d18 += 0.15707963267948966D)
                    {
                        this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, d0 + Math.cos(d18) * 5.0D, d1 - 0.4D, d2 + Math.sin(d18) * 5.0D, Math.cos(d18) * -5.0D, 0.0D, Math.sin(d18) * -5.0D, new int[] { Block.getIdFromBlock(MPBlocks.INFECTED_VINES) });
                        this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, d0 + Math.cos(d18) * 5.0D, d1 - 0.4D, d2 + Math.sin(d18) * 5.0D, Math.cos(d18) * -7.0D, 0.0D, Math.sin(d18) * -7.0D, new int[] { Block.getIdFromBlock(MPBlocks.INFECTED_VINES) });
                    }
                }
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {}

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {}

    @Override
    public float getBrightness()
    {
        return 1.0F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        return 15728880;
    }

    @Override
    public boolean canBeAttackedWithItem()
    {
        return false;
    }
}