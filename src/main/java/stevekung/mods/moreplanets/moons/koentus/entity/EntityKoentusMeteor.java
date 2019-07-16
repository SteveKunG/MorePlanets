package stevekung.mods.moreplanets.moons.koentus.entity;

import java.util.List;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class EntityKoentusMeteor extends Entity
{
    private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(EntityKoentusMeteor.class, DataSerializers.VARINT);
    private EntityLiving shootingEntity;
    private int size = 1;

    public EntityKoentusMeteor(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    public EntityKoentusMeteor(World world, double x, double y, double z, double motX, double motY, double motZ, int size)
    {
        this(world);
        this.size = size;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        this.motionX = motX;
        this.motionY = motY;
        this.motionZ = motZ;
        this.setSize(size);
    }

    @Override
    public void onUpdate()
    {
        this.setRotation(this.rotationYaw + 2.0F, this.rotationPitch + 2.0F);
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

        if (this.world.isRemote)
        {
            this.spawnParticles();
        }

        Vec3d vec1 = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec2 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult ray = this.world.rayTraceBlocks(vec1, vec2, true, true, false);
        vec1 = new Vec3d(this.posX, this.posY, this.posZ);
        vec2 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (ray != null)
        {
            vec2 = new Vec3d(ray.hitVec.x, ray.hitVec.y, ray.hitVec.z);
        }

        Entity entity = null;
        List<Entity> entityList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(2.0D));
        double distance = 0.0D;

        for (Entity entity2 : entityList)
        {
            if (entity2.canBeCollidedWith())
            {
                AxisAlignedBB aabb = entity2.getEntityBoundingBox().grow(0.01D);
                RayTraceResult result2 = aabb.calculateIntercept(vec1, vec2);

                if (result2 != null)
                {
                    double maxDistance = vec1.distanceTo(result2.hitVec);

                    if (maxDistance < distance || distance == 0.0D)
                    {
                        entity = entity2;
                        distance = maxDistance;
                    }
                }
            }
        }

        if (entity != null)
        {
            ray = new RayTraceResult(entity);
        }
        if (ray != null)
        {
            this.onImpact(ray);
        }
        if (this.posY <= -20 || this.posY >= 400)
        {
            this.setDead();
        }
    }

    @Override
    public boolean canExplosionDestroyBlock(Explosion explosion, World world, BlockPos pos, IBlockState state, float range)
    {
        return ConfigManagerCore.meteorBlockDamageEnabled;
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(SIZE, this.size);
        this.noClip = true;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.setSize(nbt.getInteger("Size"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("Size", this.size);
    }

    public int getSize()
    {
        return this.dataManager.get(SIZE);
    }

    private void setSize(int size)
    {
        this.dataManager.set(SIZE, size);
    }

    private DamageSource causeMeteorDamage(EntityKoentusMeteor meteor, Entity entity)
    {
        if (entity != null && entity instanceof EntityPlayer)
        {
            LangUtils.translate("death.meteor", PlayerUtil.getName((EntityPlayer)entity) + " was hit by a meteor! That's gotta hurt!");
        }
        return new EntityDamageSourceIndirect("explosion", meteor, entity).setProjectile();
    }

    private void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result != null)
            {
                BlockPos pos = result.getBlockPos();

                if (pos == null)
                {
                    if (result.entityHit != null)
                    {
                        pos = this.world.getTopSolidOrLiquidBlock(result.entityHit.getPosition());
                    }
                    else
                    {
                        pos = this.world.getTopSolidOrLiquidBlock(this.getPosition());
                    }
                }

                BlockPos above = pos.up();

                if (this.world.getBlockState(above).getBlock() instanceof BlockAir)
                {
                    this.world.setBlockState(above, MPBlocks.FALLEN_KOENTUS_METEOR.getDefaultState(), 3);
                }
                if (result.entityHit != null)
                {
                    result.entityHit.attackEntityFrom(this.causeMeteorDamage(this, this.shootingEntity), ConfigManagerCore.hardMode ? 12.0F : 6.0F);
                }
            }
            this.world.playEvent(2001, this.getPosition(), Block.getStateId(MPBlocks.FALLEN_KOENTUS_METEOR.getDefaultState()));
            this.world.newExplosion(this, this.posX, this.posY, this.posZ, this.size / 3 + 2, false, true);
        }
        this.setDead();
    }

    private void spawnParticles()
    {
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX, this.posY + 1D + Math.random(), this.posZ), new Vector3(0.0D, 0.0D, 0.0D), new Object[] {});
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX + Math.random() / 2, this.posY + 1D + Math.random() / 2, this.posZ), new Vector3(0.0D, 0.0D, 0.0D), new Object[] {});
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX, this.posY + 1D + Math.random(), this.posZ + Math.random()), new Vector3(0.0D, 0.0D, 0.0D), new Object[] {});
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX - Math.random() / 2, this.posY + 1D + Math.random() / 2, this.posZ), new Vector3(0.0D, 0.0D, 0.0D), new Object[] {});
        GalacticraftCore.proxy.spawnParticle("distanceSmoke", new Vector3(this.posX, this.posY + 1D + Math.random(), this.posZ - Math.random()), new Vector3(0.0D, 0.0D, 0.0D), new Object[] {});
    }
}
