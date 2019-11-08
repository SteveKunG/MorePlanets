package stevekung.mods.moreplanets.planets.diona.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.init.MPSounds;

public class EntityInfectedPurloniteTentacle extends Entity
{
    private static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(EntityInfectedPurloniteTentacle.class, DataSerializers.FLOAT);
    public int innerRotation;

    public EntityInfectedPurloniteTentacle(World world)
    {
        super(world);
        this.setSize(0.55F, 2.0F);
        this.preventEntitySpawning = true;
        this.innerRotation = this.rand.nextInt(50000);
    }

    @SideOnly(Side.CLIENT)
    public EntityInfectedPurloniteTentacle(World world, double x, double y, double z)
    {
        this(world);
        this.setPosition(x, y, z);
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        entity.applyEntityCollision(this);
        return entity.getEntityBoundingBox();
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(DAMAGE, 0.0F);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        ++this.innerRotation;
        this.motionY -= 0.03999999910593033D;
        this.move(MoverType.SELF, 0.0D, this.motionY, 0.0D);

        if (this.getDamage() > 0.0F)
        {
            this.setDamage(this.getDamage() - 1.0F);
        }

        if (!this.world.isRemote)
        {
            List<EntityInfectedPurloniteSlimeBoss> boss = this.world.getEntitiesWithinAABB(EntityInfectedPurloniteSlimeBoss.class, this.getEntityBoundingBox().grow(32.0D));

            if (boss.isEmpty())
            {
                this.setDead();
            }

            int size = this.world.getEntitiesWithinAABB(EntityInfectedPurloniteWorm.class, this.getEntityBoundingBox().grow(5.0D)).size();

            if (size > 16)
            {
                return;
            }

            if (this.ticksExisted % 100 == 0)
            {
                for (int i = 0; i < 1 + this.rand.nextInt(2); i++)
                {
                    EntityInfectedPurloniteWorm worm = new EntityInfectedPurloniteWorm(this.world);
                    double x = this.posX + (this.rand.nextDouble() - this.rand.nextDouble());
                    double y = this.posY + this.rand.nextInt(3);
                    double z = this.posZ + (this.rand.nextDouble() - this.rand.nextDouble());
                    worm.setLocationAndAngles(x, y, z, 0.0F, 0.0F);

                    if (worm.getCanSpawnHere() && worm.isNotColliding())
                    {
                        this.world.spawnEntity(worm);
                    }

                    if (worm != null)
                    {
                        worm.spawnExplosionParticle();
                    }
                }
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {}

    @Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.world.isRemote)
            {
                this.markVelocityChanged();
                this.setDamage(this.getDamage() + amount * 10.0F);
                this.world.playSound(null, this.getPosition(), MPSounds.INFECTED_MOB_ATTACK, SoundCategory.HOSTILE, 1.0F, 1.0F);

                if (this.world instanceof WorldServer)
                {
                    for (int i = 0; i < 8; i++)
                    {
                        ((WorldServer)this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY + this.height / 1.5D, this.posZ, 10, this.width / 4.0F, this.height / 4.0F, this.width / 4.0F, 0.05D, new int[] {Block.getStateId(MPBlocks.INFECTED_PURLONITE_SEGMENT.getDefaultState())});
                    }
                }
                if (source.isCreativePlayer() || this.getDamage() > 2048.0F)
                {
                    this.setDead();
                    this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D)).forEach(entity -> entity.addPotionEffect(new PotionEffect(MPPotions.INFECTED_PURLONITE, 120, 0)));
                    this.world.createExplosion(null, this.posX, this.posY, this.posZ, 1.0F + this.rand.nextFloat(), true);
                    this.world.playSound(null, this.getPosition(), MPSounds.ALIEN_EGG_DESTROYED, SoundCategory.HOSTILE, 1.0F, 1.0F);
                    int j = 1 + this.rand.nextInt(4);

                    for (int k = 0; k < j; ++k)
                    {
                        this.entityDropItem(new ItemStack(MPItems.INFECTED_PURLONITE_SHARD), 0.0F);
                    }
                }
            }
            return true;
        }
    }

    public void setDamage(float damage)
    {
        this.dataManager.set(DAMAGE, damage);
    }

    public float getDamage()
    {
        return this.dataManager.get(DAMAGE);
    }
}