package stevekung.mods.moreplanets.moons.europa.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.moons.europa.world.EuropaWaterExplosion;

public class EntityEuropaWaterBomb extends Entity
{
    public int fuse;
    private EntityLivingBase tntPlacedBy;

    public EntityEuropaWaterBomb(World world)
    {
        super(world);
        this.fuse = 80;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityEuropaWaterBomb(World world, double x, double y, double z, EntityLivingBase entityLiving)
    {
        this(world);
        this.setPosition(x, y, z);
        float f = (float)(Math.random() * 3.141592741012573D * 2.0D);
        this.motionX = -(float)Math.sin(f) * 0.02F;
        this.motionY = 0.2000000029802322D;
        this.motionZ = -(float)Math.cos(f) * 0.02F;
        this.fuse = 80;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.tntPlacedBy = entityLiving;
    }

    @Override
    protected void entityInit() {}

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }
        if (this.fuse-- <= 0)
        {
            this.setDead();

            if (!this.worldObj.isRemote)
            {
                this.explode();
            }
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
            this.worldObj.spawnParticle("splash", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.25D, 0.0D);
        }
    }

    private void explode()
    {
        float f = 4.0F;
        EuropaWaterExplosion explosion = new EuropaWaterExplosion(this.worldObj, this, this.posX, this.posY, this.posZ, f);
        explosion.isFlaming = false;
        explosion.isSmoking = true;
        explosion.doExplosionA();
        explosion.doExplosionB(true);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("Fuse", this.fuse);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.fuse = nbt.getInteger("Fuse");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    public EntityLivingBase getTntPlacedBy()
    {
        return this.tntPlacedBy;
    }
}