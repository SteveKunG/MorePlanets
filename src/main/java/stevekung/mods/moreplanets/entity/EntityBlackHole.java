package stevekung.mods.moreplanets.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.utils.DamageSourceMP;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;

public class EntityBlackHole extends Entity
{
    private int lifeTick;
    private int spawnBlockRadiusTick;
    private static final DataParameter<Integer> RANGE = EntityDataManager.createKey(EntityBlackHole.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> MAX_LIFE = EntityDataManager.createKey(EntityBlackHole.class, DataSerializers.VARINT);

    public EntityBlackHole(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public EntityBlackHole(World world, double x, double y, double z)
    {
        this(world);
        this.setPosition(x, y, z);
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(RANGE, 0);
        this.dataManager.register(MAX_LIFE, 0);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.move(MoverType.SELF, 0.0D, 0.0D, 0.0D);
        int range = this.getRange();
        double nearestRange = 0.1D;

        for (Entity entity : this.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(this.posX - range, this.posY - range, this.posZ - range, this.posX + range, this.posY + range, this.posZ + range)))
        {
            this.applyGravityToEntities(0.4F, 5000, 2500, 0.3F, range, entity, this.posX - 0.5D, this.posY, this.posZ - 0.5D);

            for (Entity nearest : this.world.getEntitiesWithinAABB(entity.getClass(), new AxisAlignedBB(this.posX - nearestRange, this.posY - nearestRange, this.posZ - nearestRange, this.posX + nearestRange, this.posY + nearestRange, this.posZ + nearestRange)))
            {
                if (nearest instanceof EntityPlayer && ((EntityPlayer)nearest).capabilities.isCreativeMode || nearest instanceof IImmuneBlackHole && ((IImmuneBlackHole)nearest).isImmune())
                {
                    return;
                }
                if (nearest instanceof EntityPlayer)
                {
                    nearest.attackEntityFrom(DamageSourceMP.BLACK_HOLE, 10.0F);
                }
                if (!(nearest instanceof EntityPlayer) && !(nearest instanceof EntityBlackHole))
                {
                    nearest.setDead();
                }
            }
        }

        int fallingBlockSize = this.world.getEntitiesWithinAABB(EntityFallingBlock.class, new AxisAlignedBB(this.posX - range, this.posY - range, this.posZ - range, this.posX + range, this.posY + range, this.posZ + range)).size();

        if (this.lifeTick == 0)
        {
            this.world.playSound(null, this.getPosition(), MPSounds.BLACK_HOLE_CREATED, SoundCategory.AMBIENT, 2.0F, 1.0F);
        }
        if (this.lifeTick < this.getMaxLife())
        {
            this.lifeTick++;
        }
        if (this.lifeTick % 80 == 0 && this.spawnBlockRadiusTick < 15 && fallingBlockSize < 32)
        {
            this.spawnBlockRadiusTick++;
        }
        if (this.lifeTick % 20 == 0)
        {
            this.world.playSound(null, this.getPosition(), MPSounds.BLACK_HOLE_AMBIENT, SoundCategory.AMBIENT, 2.0F, 1.0F);
        }

        if (!this.world.isRemote)
        {
            if (this.lifeTick == this.getMaxLife())
            {
                this.setDead();

                if (ConfigManagerMP.moreplanets_general.enableBlackHoleExplosion)
                {
                    this.world.createExplosion(null, this.posX, this.posY, this.posZ, 128.0F, true);
                }
            }
            this.spawnFallingBlock();
        }
        else
        {
            for (int i = 0; i < 16; ++i)
            {
                double d0 = this.posX + this.rand.nextFloat();
                double d1 = this.posY - 0.5D + this.rand.nextFloat();
                double d2 = this.posZ + this.rand.nextFloat();
                double d3 = (this.rand.nextFloat() - 0.5D) * 0.5D;
                double d4 = (this.rand.nextFloat() - 0.5D) * 0.5D;
                double d5 = (this.rand.nextFloat() - 0.5D) * 0.5D;
                int j = this.rand.nextInt(2) * 2 - 1;
                d0 = this.posX + 0.25D * j;
                d3 = this.rand.nextFloat() * 2.0F * j;
                d2 = this.posZ + 0.25D * j;
                d5 = this.rand.nextFloat() * 2.0F * j;
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.DARK_PORTAL, d0, d1, d2, d3, d4, d5);
            }
        }
    }

    @Override
    public void setDead()
    {
        this.world.playSound(null, this.getPosition(), MPSounds.BLACK_HOLE_DESTROYED, SoundCategory.AMBIENT, 2.0F, 1.0F);
        super.setDead();
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.lifeTick = nbt.getInteger("LifeTick");
        this.spawnBlockRadiusTick = nbt.getInteger("SpawnBlockRadiusTick");
        this.setRange(nbt.getInteger("Range"));
        this.setMaxLife(nbt.getInteger("MaxLife"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("LifeTick", this.lifeTick);
        nbt.setInteger("SpawnBlockRadiusTick", this.spawnBlockRadiusTick);
        nbt.setInteger("Range", this.getRange());
        nbt.setInteger("MaxLife", this.getMaxLife());
    }

    public void setRange(int range)
    {
        this.dataManager.set(RANGE, range);
    }

    public void setMaxLife(int maxLife)
    {
        this.dataManager.set(MAX_LIFE, maxLife);
    }

    private int getRange()
    {
        return this.dataManager.get(RANGE);
    }

    private int getMaxLife()
    {
        return this.dataManager.get(MAX_LIFE);
    }

    private void spawnFallingBlock()
    {
        int blockPosX = MathHelper.floor(this.posX);
        int blockPosY = MathHelper.floor(this.posY);
        int blockPosZ = MathHelper.floor(this.posZ);
        int radius = 1 + this.spawnBlockRadiusTick;

        for (int x = -radius; x < radius; x++)
        {
            for (int y = -radius; y < radius; y++)
            {
                for (int z = -radius; z < radius; z++)
                {
                    double dist = MathHelper.sqrt(x * x + y * y + z * z);

                    if (dist <= radius)
                    {
                        BlockPos pos = new BlockPos(blockPosX + x, blockPosY + y, blockPosZ + z);
                        IBlockState state = this.world.getBlockState(pos);
                        Block block = state.getBlock();

                        if (!block.isAir(state, this.world, pos))
                        {
                            this.world.setBlockToAir(pos);
                            EntityFallingBlock fallingBlock = new EntityFallingBlock(this.world, blockPosX + x, blockPosY + y, blockPosZ + z, state);
                            fallingBlock.fallTime = 1;
                            fallingBlock.shouldDropItem = false;
                            fallingBlock.motionY += 0.5D;
                            fallingBlock.setLocationAndAngles(blockPosX + x + 0.5D, blockPosY + y, blockPosZ + z + 0.5D, 0.0F, 0.0F);
                            this.world.spawnEntity(fallingBlock);
                        }
                    }
                }
            }
        }
    }

    private void applyGravityToEntities(float gravStrength, float maxGravXZ, float maxGravY, float minGrav, float range, Entity entity, double xCoord, double yCoord, double zCoord)
    {
        // distance formula
        double dist = Math.sqrt(Math.pow(xCoord + 0.5 - entity.posX, 2) + Math.pow(zCoord + 0.5 - entity.posZ, 2) + Math.pow(yCoord + 0.5 - entity.posY, 2));

        if (dist > range)
        {
            return;
        }

        double xDisplacment = entity.posX - (xCoord + 0.5);
        double yDisplacment = entity.posY - (yCoord + 0.5);
        double zDisplacment = entity.posZ - (zCoord + 0.5);
        // http://en.wikipedia.org/wiki/Spherical_coordinate_system#Coordinate_system_conversions
        double theta = Math.acos(zDisplacment / dist);
        double phi = Math.atan2(yDisplacment, xDisplacment);
        // Gravity decreases linearly
        double gravForce = gravStrength * (1 - dist / range);

        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode || entity instanceof IImmuneBlackHole && ((IImmuneBlackHole)entity).isImmune())
        {
            return;
        }

        gravForce *= 2.0D;

        double vecX = -gravForce * Math.sin(theta) * Math.cos(phi);
        double vecY = -gravForce * Math.sin(theta) * Math.sin(phi);
        double vecZ = -gravForce * Math.cos(theta);

        // trims gravity above max
        if (Math.abs(vecX) > maxGravXZ)
        {
            vecX *= maxGravXZ / Math.abs(vecX);
        }
        if (Math.abs(vecY) > maxGravY)
        {
            vecY *= maxGravY / Math.abs(vecY);
        }
        if (Math.abs(vecZ) > maxGravXZ)
        {
            vecZ *= maxGravXZ / Math.abs(vecZ);
        }

        // trims gravity below min
        if (Math.abs(vecX) < minGrav)
        {
            vecX = 0;
        }
        if (Math.abs(vecY) < minGrav)
        {
            vecY = 0;
        }
        if (Math.abs(vecZ) < minGrav)
        {
            vecZ = 0;
        }
        entity.setVelocity(entity.motionX + vecX, entity.motionY + vecY, entity.motionZ + vecZ);
    }
}