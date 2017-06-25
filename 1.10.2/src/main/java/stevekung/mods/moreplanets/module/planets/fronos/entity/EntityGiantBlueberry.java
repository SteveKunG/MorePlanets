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

public class EntityGiantBlueberry extends EntityFronosPet
{
    public EntityGiantBlueberry(World world)
    {
        super(world);
        this.setSize(0.7F, 1.0F);
        this.timeUntilToDropItem = this.rand.nextInt(6000) + 2000;
        this.setTamed(false);
        this.initEntityAI();
    }

    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.aiTexture = new EntityAIFaceTexture(this);
        this.aiPanic = new EntityAIFronosPanic(this, 1.75D);
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
    public float getEyeHeight()
    {
        if (this.isChild())
        {
            return this.height - 0.15F;
        }
        return this.height - 0.3F;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
    }

    @Override
    public double getMountedYOffset()
    {
        if (this.isChild())
        {
            return this.isSitting() ? this.height * 0.1D : this.height * 0.4D;
        }
        return this.isSitting() ? this.height * 0.45D : this.height * 0.65D;
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.GIANT_BLUEBERRY;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        if (this.worldObj.rand.nextInt(10) == 0)
        {
            EntityMarshmallow marshmallow = new EntityMarshmallow(this.worldObj);
            marshmallow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            marshmallow.onInitialSpawn(difficulty, (IEntityLivingData)null);
            marshmallow.setGrowingAge(-24000);
            this.worldObj.spawnEntityInWorld(marshmallow);
            marshmallow.startRiding(this);
        }
        return super.onInitialSpawn(difficulty, data);
    }

    @Override
    public EntityGiantBlueberry createChild(EntityAgeable entity)
    {
        EntityGiantBlueberry pet = new EntityGiantBlueberry(this.worldObj);
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
        else if (!(otherAnimal instanceof EntityGiantBlueberry))
        {
            return false;
        }
        else
        {
            EntityGiantBlueberry pet = (EntityGiantBlueberry)otherAnimal;
            return !pet.isTamed() ? false : pet.isSitting() ? false : this.isInLove() && pet.isInLove();
        }
    }

    @Override
    protected ItemStack getLayItem()
    {
        return new ItemStack(FronosItems.FRONOS_FRUITS, 1, 1);
    }
}