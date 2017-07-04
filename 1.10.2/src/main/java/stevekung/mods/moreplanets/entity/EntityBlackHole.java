package stevekung.mods.moreplanets.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.util.DamageSourceMP;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;

public class EntityBlackHole extends Entity
{
    private int lifeTick;
    private int spawnBlockRadiusTick;

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
    public void onUpdate()
    {
        super.onUpdate();
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.moveEntity(0.0D, 0.0D, 0.0D);

        int range = this.getRange();
        List<Entity> entitiesAroundBH = this.worldObj.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(this.posX - range, this.posY - range, this.posZ - range, this.posX + range, this.posY + range, this.posZ + range));

        for (Entity entity : entitiesAroundBH)
        {
            if (!(entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode) && !(entity instanceof IImmuneBlackHole && ((IImmuneBlackHole)entity).isImmune()))
            {
                double motionX = this.posX - entity.posX;
                double motionY = this.posY - entity.posY;
                double motionZ = this.posZ - entity.posZ;

                if (entity instanceof EntityItem || entity instanceof EntityFallingBlock)
                {
                    motionY = this.posY - entity.posY + 0.5D;
                }

                entity.motionX = motionX * this.getPullSpeed();
                entity.motionY = motionY * this.getPullSpeed();
                entity.motionZ = motionZ * this.getPullSpeed();
                List<Entity> entityNearBH = this.worldObj.getEntitiesWithinAABB(entity.getClass(), new AxisAlignedBB(this.posX - 0.25D, this.posY - 0.25D, this.posZ - 0.25D, this.posX + 0.25D, this.posY + 0.5D, this.posZ + 0.25D));

                for (Entity near : entityNearBH)
                {
                    if (near instanceof EntityLivingBase)
                    {
                        ((EntityLivingBase)near).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 32767, 0, false, false));
                    }
                    if (near instanceof EntityPlayer)
                    {
                        near.attackEntityFrom(DamageSourceMP.BLACK_HOLE, 10.0F);
                    }
                    if (!(near instanceof EntityPlayer) && !(near instanceof EntityBlackHole))
                    {
                        near.setDead();
                    }
                }
            }
        }

        if (this.lifeTick == 0)
        {
            this.worldObj.playSound(null, this.posX, this.posY, this.posZ, MPSounds.BLACK_HOLE_CREATED, SoundCategory.AMBIENT, 2.0F, 1.0F);
        }
        if (this.lifeTick < this.getMaxLife())
        {
            this.lifeTick++;
        }
        if (this.lifeTick % 80 == 0 && this.spawnBlockRadiusTick < 15)
        {
            this.spawnBlockRadiusTick++;
        }
        if (this.lifeTick % 20 == 0)
        {
            this.worldObj.playSound(null, this.posX, this.posY, this.posZ, MPSounds.BLACK_HOLE_AMBIENT, SoundCategory.AMBIENT, 2.0F, 1.0F);
        }

        if (!this.worldObj.isRemote)
        {
            if (this.lifeTick == this.getMaxLife())
            {
                this.setDead();

                if (ConfigManagerMP.enableBlackHoleExplosion)
                {
                    this.worldObj.createExplosion(null, this.posX, this.posY, this.posZ, 128.0F, true);
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
                MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.DARK_PORTAL, d0, d1, d2, d3, d4, d5);
            }
        }
    }

    private void spawnFallingBlock()
    {
        int blockPosX = MathHelper.floor_double(this.posX);
        int blockPosY = MathHelper.floor_double(this.posY);
        int blockPosZ = MathHelper.floor_double(this.posZ);
        int radius = 1 + this.spawnBlockRadiusTick;

        for (int x = -radius; x < radius; x++)
        {
            for (int y = -radius; y < radius; y++)
            {
                for (int z = -radius; z < radius; z++)
                {
                    double dist = MathHelper.sqrt_double(x * x + y * y + z * z);

                    if (dist <= radius)
                    {
                        BlockPos pos = new BlockPos(blockPosX + x, blockPosY + y, blockPosZ + z);
                        IBlockState state = this.worldObj.getBlockState(pos);
                        Block block = state.getBlock();

                        if (block.getExtendedState(state, this.worldObj, pos) != null && radius <= 15)
                        {
                            this.worldObj.setBlockToAir(pos);
                        }
                        if (!block.isAir(state, this.worldObj, pos) && block.getExtendedState(state, this.worldObj, pos) == state)
                        {
                            this.worldObj.setBlockToAir(pos);
                            EntityFallingBlock fallingBlock = new EntityFallingBlock(this.worldObj, blockPosX + x, blockPosY + y, blockPosZ + z, state);
                            fallingBlock.fallTime = 1;
                            fallingBlock.shouldDropItem = false;
                            fallingBlock.motionY += 0.5D;
                            fallingBlock.setLocationAndAngles(blockPosX + x + 0.5D, blockPosY + y, blockPosZ + z + 0.5D, 0.0F, 0.0F);
                            this.worldObj.spawnEntityInWorld(fallingBlock);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setDead()
    {
        this.worldObj.playSound(null, this.posX, this.posY, this.posZ, MPSounds.BLACK_HOLE_DESTROYED, SoundCategory.AMBIENT, 2.0F, 1.0F);
        super.setDead();
    }

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.lifeTick = nbt.getInteger("LifeTick");
        this.spawnBlockRadiusTick = nbt.getInteger("SpawnBlockRadiusTick");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("LifeTick", this.lifeTick);
        nbt.setInteger("SpawnBlockRadiusTick", this.spawnBlockRadiusTick);
    }

    protected int getMaxLife()
    {
        return 6000;
    }

    protected int getRange()
    {
        return 64;
    }

    protected double getPullSpeed()
    {
        return 0.2D;
    }
}