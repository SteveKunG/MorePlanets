package stevekung.mods.moreplanets.module.planets.fronos.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.fronos.entity.ai.EntityAIFaceTexture;
import stevekung.mods.moreplanets.module.planets.fronos.entity.ai.EntityAIFronosPanic;
import stevekung.mods.moreplanets.module.planets.fronos.entity.ai.EntityAIFronosTempt;
import stevekung.mods.moreplanets.module.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.util.entity.ai.PathNavigateGroundMP;

public class EntityMarshmallow extends EntityFronosPet
{
    public EntityMarshmallow(World world)
    {
        super(world);
        this.setSize(0.5F, 0.4F);
        this.aiTexture = new EntityAIFaceTexture(this);
        this.aiPanic = new EntityAIFronosPanic(this, 2.5D);
        this.aiTempt = new EntityAIFronosTempt(this, 1.4D, new ItemStack(FronosItems.FRONOS_FOOD, 1, 0), false);
        ((PathNavigateGroundMP)this.getNavigator()).setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiPanic);
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(5, this.aiTempt);
        this.tasks.addTask(5, this.aiTexture);
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.setTamed(false);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!this.worldObj.isRemote)
        {
            if (this.ridingEntity instanceof EntityBearry || this.ridingEntity instanceof EntityGiantBlueberry || this.ridingEntity instanceof EntityMarshmallow)
            {
                if (this.getGrowingAge() < 0)
                {
                    this.setGrowingAge(-24000);
                }
            }
        }
    }

    @Override
    public float getEyeHeight()
    {
        if (this.isChild())
        {
            return this.height - 0.05F;
        }
        return this.height - 0.125F;
    }

    @Override
    public double getMountedYOffset()
    {
        return this.height * 0.95D;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15D);
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int j = this.rand.nextInt(1) + 1 + this.rand.nextInt(1 + fortune);

        for (int k = 0; k < j; ++k)
        {
            if (this.isBurning())
            {
                this.entityDropItem(new ItemStack(FronosItems.FRONOS_FOOD, 1, 3), 0.0F);
            }
            else
            {
                this.entityDropItem(new ItemStack(FronosItems.FRONOS_FOOD, 1, 2), 0.0F);
            }
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        if (this.worldObj.rand.nextInt(10) == 0)
        {
            EntityMarshmallow marshmallow = new EntityMarshmallow(this.worldObj);
            marshmallow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.worldObj.spawnEntityInWorld(marshmallow);
            marshmallow.mountEntity(this);
            marshmallow.setGrowingAge(-24000);
        }
        return super.onInitialSpawn(difficulty, data);
    }

    @Override
    public EntityMarshmallow createChild(EntityAgeable entity)
    {
        EntityMarshmallow pet = new EntityMarshmallow(this.worldObj);
        String owner = this.getOwnerId();

        if (owner != null && owner.trim().length() > 0)
        {
            pet.setOwnerId(owner);
            pet.setTamed(true);
        }
        return pet;
    }

    @Override
    protected ItemStack getLayItem()
    {
        return null;
    }
}