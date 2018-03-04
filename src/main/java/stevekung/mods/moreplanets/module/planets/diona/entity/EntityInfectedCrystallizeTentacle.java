package stevekung.mods.moreplanets.module.planets.diona.entity;

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
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;

public class EntityInfectedCrystallizeTentacle extends Entity
{
    private static DataParameter<Float> DAMAGE = EntityDataManager.createKey(EntityInfectedCrystallizeTentacle.class, DataSerializers.FLOAT);
    public int innerRotation;
    public int tentacleRod;

    public EntityInfectedCrystallizeTentacle(World world)
    {
        super(world);
        this.preventEntitySpawning = true;
        this.setSize(0.55F, 2.0F);
        this.innerRotation = this.rand.nextInt(50000);
    }

    @SideOnly(Side.CLIENT)
    public EntityInfectedCrystallizeTentacle(World world, double x, double y, double z)
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
            List<EntityInfectedCrystallizeSlimeBoss> boss = this.world.getEntitiesWithinAABB(EntityInfectedCrystallizeSlimeBoss.class, this.getEntityBoundingBox().grow(32.0F, 32.0F, 32.0F));

            if (boss.isEmpty())
            {
                this.setDead();
            }

            int size = this.world.getEntitiesWithinAABB(EntityInfectedCrystallizeWorm.class, this.getEntityBoundingBox().grow(5.0F, 5.0F, 5.0F)).size();

            if (size > 16)
            {
                return;
            }

            if (this.ticksExisted % 100 == 0)
            {
                for (int i = 0; i < 1 + this.rand.nextInt(2); i++)
                {
                    EntityInfectedCrystallizeWorm worm = new EntityInfectedCrystallizeWorm(this.world);
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

    public void setDamage(float damage)
    {
        this.dataManager.set(DAMAGE, damage);
    }

    public float getDamage()
    {
        return this.dataManager.get(DAMAGE);
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
                this.world.playSound(null, this.posX, this.posY, this.posZ, MPSounds.INFECTED_MOB_ATTACK, SoundCategory.HOSTILE, 1.0F, 1.0F);

                if (this.world instanceof WorldServer)
                {
                    for (int i = 0; i < 8; i++)
                    {
                        ((WorldServer)this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY + this.height / 1.5D, this.posZ, 10, this.width / 4.0F, this.height / 4.0F, this.width / 4.0F, 0.05D, new int[] {Block.getStateId(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState())});
                    }
                }
                if (source.isCreativePlayer() || this.getDamage() > 2048.0F)
                {
                    this.setDead();
                    List<EntityLivingBase> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D));
                    this.world.createExplosion(null, this.posX, this.posY, this.posZ, 1.0F + this.rand.nextFloat(), true);
                    this.world.playSound(null, this.posX, this.posY, this.posZ, MPSounds.ALIEN_EGG_DESTROYED, SoundCategory.HOSTILE, 1.0F, 1.0F);
                    int j = 1 + this.rand.nextInt(4);

                    for (int k = 0; k < j; ++k)
                    {
                        this.entityDropItem(new ItemStack(DionaItems.DIONA_ITEM, 1, 4), 0.0F);
                    }
                    for (EntityLivingBase living : list)
                    {
                        living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZE, 120, 0));
                    }
                }
            }
            return true;
        }
    }
}