package stevekung.mods.moreplanets.planets.chalos.entity.projectile;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseSlime;

public class EntitySmallCheeseSpore extends EntityFireball
{
    private boolean inGround;
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int ticksAlive;
    private int ticksInAir;

    public EntitySmallCheeseSpore(World world)
    {
        super(world);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntitySmallCheeseSpore(World world, EntityLivingBase living, double x, double y, double z)
    {
        super(world, living, x, y, z);
        this.setSize(0.3125F, 0.3125F);
    }

    @Override
    public void onUpdate()
    {
        if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.isDead) && this.world.isBlockLoaded(new BlockPos(this)))
        {
            super.onUpdate();

            if (this.inGround)
            {
                if (this.world.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile)
                {
                    ++this.ticksAlive;

                    if (this.ticksAlive == 600)
                    {
                        this.setDead();
                    }
                    return;
                }
                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2F;
                this.motionY *= this.rand.nextFloat() * 0.2F;
                this.motionZ *= this.rand.nextFloat() * 0.2F;
                this.ticksAlive = 0;
                this.ticksInAir = 0;
            }
            else
            {
                ++this.ticksInAir;
            }

            Vec3d vec3 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec31 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec3, vec31);
            vec3 = new Vec3d(this.posX, this.posY, this.posZ);
            vec31 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null)
            {
                vec31 = new Vec3d(movingobjectposition.hitVec.x, movingobjectposition.hitVec.y, movingobjectposition.hitVec.z);
            }

            Entity entity = null;
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            for (int i = 0; i < list.size(); ++i)
            {
                Entity entity1 = list.get(i);

                if (entity1.canBeCollidedWith() && (!entity1.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25))
                {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(f, f, f);
                    RayTraceResult movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null)
                    {
                        double d1 = vec3.squareDistanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new RayTraceResult(entity);
            }
            if (movingobjectposition != null)
            {
                this.onImpact(movingobjectposition);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(MathHelper.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;

            for (this.rotationPitch = (float)(MathHelper.atan2(f1, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {}

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
            float f2 = this.getMotionFactor();

            if (this.isInWater())
            {
                for (int j = 0; j < 4; ++j)
                {
                    float f3 = 0.25F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * f3, this.posY - this.motionY * f3, this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ, new int[0]);
                }
                f2 = 0.8F;
            }
            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= f2;
            this.motionY *= f2;
            this.motionZ *= f2;
            this.setPosition(this.posX, this.posY, this.posZ);
        }
        else
        {
            this.setDead();
        }
    }

    @Override
    protected void onImpact(RayTraceResult moving)
    {
        if (!this.world.isRemote)
        {
            if (moving.entityHit != null)
            {
                moving.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 2.5F);
                this.applyEnchantments(this.shootingEntity, moving.entityHit);
            }
            if (this.rand.nextInt(5) == 0)
            {
                byte b0 = 1;

                if (this.rand.nextInt(16) == 0)
                {
                    b0 = 2;
                }

                for (int i = 0; i < b0; ++i)
                {
                    EntityCheeseSlime slime = new EntityCheeseSlime(this.world);
                    slime.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    slime.setSlimeSize(1, true);
                    slime.setAbsorptionAmount(2.0F);
                    this.world.spawnEntity(slime);
                }
            }
            this.setDead();
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setShort("xTile", (short)this.xTile);
        nbt.setShort("yTile", (short)this.yTile);
        nbt.setShort("zTile", (short)this.zTile);
        ResourceLocation resourcelocation = Block.REGISTRY.getNameForObject(this.inTile);
        nbt.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
        nbt.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        nbt.setTag("direction", this.newDoubleNBTList(new double[] {this.motionX, this.motionY, this.motionZ}));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.xTile = nbt.getShort("xTile");
        this.yTile = nbt.getShort("yTile");
        this.zTile = nbt.getShort("zTile");

        if (nbt.hasKey("inTile", 8))
        {
            this.inTile = Block.getBlockFromName(nbt.getString("inTile"));
        }
        else
        {
            this.inTile = Block.getBlockById(nbt.getByte("inTile") & 255);
        }

        this.inGround = nbt.getByte("inGround") == 1;

        if (nbt.hasKey("direction", 9))
        {
            NBTTagList nbttaglist = nbt.getTagList("direction", 6);
            this.motionX = nbttaglist.getDoubleAt(0);
            this.motionY = nbttaglist.getDoubleAt(1);
            this.motionZ = nbttaglist.getDoubleAt(2);
        }
        else
        {
            this.setDead();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));

        if (this.world.isBlockLoaded(blockpos$mutableblockpos))
        {
            blockpos$mutableblockpos.setY(MathHelper.floor(this.posY + this.getEyeHeight()));
            return this.world.getCombinedLight(blockpos$mutableblockpos, 0);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public float getBrightness()
    {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));

        if (this.world.isBlockLoaded(blockpos$mutableblockpos))
        {
            blockpos$mutableblockpos.setY(MathHelper.floor(this.posY + this.getEyeHeight()));
            return this.world.getLightBrightness(blockpos$mutableblockpos);
        }
        else
        {
            return 0.0F;
        }
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return false;
    }

    @Override
    protected boolean isFireballFiery()
    {
        return false;
    }
}