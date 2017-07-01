package stevekung.mods.moreplanets.module.planets.fronos.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.module.planets.fronos.entity.ai.EntityAIFaceTexture;
import stevekung.mods.moreplanets.module.planets.fronos.entity.ai.EntityAIFronosPanic;
import stevekung.mods.moreplanets.module.planets.fronos.entity.ai.EntityAIFronosTempt;
import stevekung.mods.moreplanets.module.planets.fronos.items.FronosItems;

public class EntityMarshmallow extends EntityFronosPet
{
    public EntityMarshmallow(World world)
    {
        super(world);
        this.setSize(0.5F, 0.4F);
        this.setTamed(false);
        this.initEntityAI();
    }

    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.aiTexture = new EntityAIFaceTexture(this);
        this.aiPanic = new EntityAIFronosPanic(this, 2.5D);
        this.aiTempt = new EntityAIFronosTempt(this, 1.4D, new ItemStack(FronosItems.FRONOS_FOOD, 1, 0), false);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiPanic);
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(5, this.aiTempt);
        this.tasks.addTask(6, this.aiTexture);
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!this.world.isRemote)
        {
            if (this.getRidingEntity() instanceof EntityBearry || this.getRidingEntity() instanceof EntityGiantBlueberry || this.getRidingEntity() instanceof EntityMarshmallow)
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
        if (this.isChild())
        {
            return this.isSitting() ? this.height - 0.35D : this.height - 0.305D;
        }
        return this.isSitting() ? 0.0D : this.height * 0.225D;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.MARSHMALLOW;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        if (this.world.rand.nextInt(10) == 0)
        {
            EntityMarshmallow marshmallow = new EntityMarshmallow(this.world);
            marshmallow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            marshmallow.setGrowingAge(-24000);
            this.world.spawnEntity(marshmallow);
            marshmallow.startRiding(this);
        }
        return super.onInitialSpawn(difficulty, data);
    }

    @Override
    public EntityMarshmallow createChild(EntityAgeable entity)
    {
        EntityMarshmallow pet = new EntityMarshmallow(this.world);
        UUID owner = this.getOwnerId();

        if (owner != null)
        {
            pet.setOwnerId(owner);
            pet.setTamed(true);
        }
        return pet;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityMarshmallow))
        {
            return false;
        }
        else
        {
            EntityMarshmallow pet = (EntityMarshmallow)otherAnimal;
            return !pet.isTamed() ? false : pet.isSitting() ? false : this.isInLove() && pet.isInLove();
        }
    }

    @Override
    protected ItemStack getLayItem()
    {
        return null;
    }
}