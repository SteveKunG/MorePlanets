package stevekung.mods.moreplanets.module.planets.nibiru.entity.weather;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityVeinFloater;

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
            this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, this.world.rand.nextInt(5) == 0 ? SoundEvents.ENTITY_LIGHTNING_THUNDER : MPSounds.LOUD_THUNDER, SoundCategory.WEATHER, 20.0F, 0.8F + this.rand.nextFloat() * 0.2F);
            this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LIGHTNING_IMPACT, SoundCategory.WEATHER, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
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

                if (!this.world.isRemote && this.fire && this.world.getGameRules().getBoolean("doFireTick"))
                {
                    if (this.world.isAreaLoaded(blockpos, 10) && this.world.getBlockState(blockpos).getMaterial() == Material.AIR && NibiruBlocks.ELECTRICAL_FIRE.canPlaceBlockAt(this.world, blockpos))
                    {
                        this.world.setBlockState(blockpos, NibiruBlocks.ELECTRICAL_FIRE.getDefaultState());
                    }
                    if (this.world.rand.nextInt(4) == 0)
                    {
                        for (int i = 0; i < 4; ++i)
                        {
                            BlockPos blockpos1 = blockpos.add(this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1);

                            if (this.world.getBlockState(blockpos1).getMaterial() == Material.AIR && NibiruBlocks.ELECTRICAL_FIRE.canPlaceBlockAt(this.world, blockpos1))
                            {
                                this.world.setBlockState(blockpos1, NibiruBlocks.ELECTRICAL_FIRE.getDefaultState());
                            }
                        }
                    }
                }
            }
        }

        if (this.lightningState >= 0)
        {
            if (this.world.isRemote)
            {
                this.world.setLastLightningBolt(2);
            }
            else
            {
                double d0 = 3.0D;
                List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - d0, this.posY - d0, this.posZ - d0, this.posX + d0, this.posY + 6.0D + d0, this.posZ + d0));

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity = list.get(i);

                    if (!(entity instanceof EntityVeinFloater))
                    {
                        entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 8.0F);
                        entity.setFire(8);
                    }
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