package stevekung.mods.moreplanets.planets.diona.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPSounds;

public class EntityAlienBeam extends Entity
{
    private int lightningState;
    private long boltVertex;
    private int boltLivingTime;

    public EntityAlienBeam(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
        this.ignoreFrustumCheck = true;
        this.isImmuneToFire = true;
        this.lightningState = 2;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(5) + 2;
    }

    public EntityAlienBeam(World world, double x, double y, double z)
    {
        this(world);
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
        this.ignoreFrustumCheck = true;
        this.isImmuneToFire = true;
        this.lightningState = 2;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(5) + 2;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.lightningState == 2)
        {
            this.world.playSound(null, this.getPosition(), MPSounds.ALIEN_BEAM, SoundCategory.WEATHER, 100.0F, 0.8F + this.rand.nextFloat() * 0.2F);
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
            if (!this.world.isRemote)
            {
                double d0 = 1.0D;
                List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - d0, this.posY - d0, this.posZ - d0, this.posX + d0, this.posY + 6.0D + d0, this.posZ + d0));

                for (Entity entity : list)
                {
                    entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 10.0F);
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

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength();

        if (Double.isNaN(d0))
        {
            d0 = 1.0D;
        }
        d0 = d0 * 128.0D * getRenderDistanceWeight();
        return distance < d0 * d0;
    }

    public void spawnWeather()
    {
        this.world.addWeatherEffect(this);
        this.world.loadedEntityList.add(this);
        this.world.onEntityAdded(this);
    }
}