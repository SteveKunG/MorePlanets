package stevekung.mods.moreplanets.module.planets.nibiru.entity.weather;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class EntityNibiruLightningBolt extends Entity
{
    private int lightningState;
    public long boltVertex;
    private int boltLivingTime;
    private boolean fire;

    public EntityNibiruLightningBolt(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
        this.isImmuneToFire = true;
        this.lightningState = 2;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(8) + 2;
        this.fire = true;
    }

    public EntityNibiruLightningBolt(World world, double x, double y, double z, boolean fire)
    {
        this(world);
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
        this.isImmuneToFire = true;
        this.lightningState = 2;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(8) + 2;
        this.fire = fire;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.lightningState == 2)
        {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, this.worldObj.rand.nextInt(5) == 0 ? "ambient.weather.thunder" : "moreplanets:weather.thunder", 20.0F, 0.8F + this.rand.nextFloat() * 0.2F);
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
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
                BlockPos blockpos = new BlockPos(this);

                if (!this.worldObj.isRemote && this.fire && this.worldObj.getGameRules().getBoolean("doFireTick"))
                {
                    if (this.worldObj.isAreaLoaded(blockpos, 10) && this.worldObj.getBlockState(blockpos).getBlock().getMaterial() == Material.air && NibiruBlocks.ELECTRICAL_FIRE.canPlaceBlockAt(this.worldObj, blockpos))
                    {
                        this.worldObj.setBlockState(blockpos, NibiruBlocks.ELECTRICAL_FIRE.getDefaultState());
                    }
                    if (this.worldObj.rand.nextInt(4) == 0)
                    {
                        for (int i = 0; i < 4; ++i)
                        {
                            BlockPos blockpos1 = blockpos.add(this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1);

                            if (this.worldObj.getBlockState(blockpos1).getBlock().getMaterial() == Material.air && NibiruBlocks.ELECTRICAL_FIRE.canPlaceBlockAt(this.worldObj, blockpos1))
                            {
                                this.worldObj.setBlockState(blockpos1, NibiruBlocks.ELECTRICAL_FIRE.getDefaultState());
                            }
                        }
                    }
                }
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
                double d0 = 3.0D;
                List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - d0, this.posY - d0, this.posZ - d0, this.posX + d0, this.posY + 6.0D + d0, this.posZ + d0));

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity = list.get(i);
                    entity.attackEntityFrom(DamageSource.lightningBolt, 8.0F);
                    entity.setFire(8);
                }
            }
        }
    }

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {}
}