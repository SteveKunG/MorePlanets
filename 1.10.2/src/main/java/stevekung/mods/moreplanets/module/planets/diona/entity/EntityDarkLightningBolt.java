package stevekung.mods.moreplanets.module.planets.diona.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDarkLightningBolt extends Entity
{
    private int lightningState;
    public long boltVertex;
    private int boltLivingTime;

    public EntityDarkLightningBolt(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
        this.isImmuneToFire = true;
        this.lightningState = 2;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(3) + 1;
    }

    public EntityDarkLightningBolt(World world, double x, double y, double z)
    {
        this(world);
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
        this.isImmuneToFire = true;
        this.lightningState = 2;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(3) + 1;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.lightningState == 2)
        {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "moreplanets:weather.thunder", 5000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
        }

        --this.lightningState;

        if (this.lightningState < 0)
        {
            if (this.boltLivingTime == 0)
            {
                this.setDead();
            }
            else if (this.lightningState < -this.rand.nextInt(10))
            {
                --this.boltLivingTime;
                this.lightningState = 1;
                this.boltVertex = this.rand.nextLong();
            }
        }

        if (this.lightningState >= 0)
        {
            if (this.worldObj.isRemote)
            {
                this.worldObj.setLastLightningBolt(2);
            }
            else
            {
                double d0 = 1.0D;
                List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - d0, this.posY - d0, this.posZ - d0, this.posX + d0, this.posY + 6.0D + d0, this.posZ + d0));

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity = list.get(i);

                    if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
                    {
                        return;
                    }
                    if (entity instanceof EntityLivingBase)
                    {
                        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 60, 0, false, false));
                    }
                    entity.attackEntityFrom(DamageSource.lightningBolt, 10.0F);
                }
            }
        }
    }

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {}
}