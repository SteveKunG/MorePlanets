package stevekung.mods.moreplanets.planets.fronos.entity;

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
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.planets.fronos.entity.ai.EntityAIFaceTexture;
import stevekung.mods.moreplanets.planets.fronos.entity.ai.EntityAIFronosPanic;
import stevekung.mods.moreplanets.planets.fronos.entity.ai.EntityAIFronosTempt;

public class EntityBearry extends EntityFronosPet
{
    public EntityBearry(World world)
    {
        super(world);
        this.setSize(0.7F, 1.2F);
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
        this.aiTempt = new EntityAIFronosTempt(this, 1.4D, new ItemStack(MPItems.CHOCOLATE_BAR), false);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiPanic);
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 8.0F, 2.0F));
        this.tasks.addTask(5, this.aiTempt);
        this.tasks.addTask(9, this.aiTexture);
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
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
            return this.isSitting() ? this.height * 0.6D : this.height * 0.8D;
        }
        return this.isSitting() ? this.height * 0.715D : this.height * 0.925D;
    }

    @Override
    public float getEyeHeight()
    {
        if (this.isChild())
        {
            return this.height - 0.25F;
        }
        return this.height - 0.5F;
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.BEARRY;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        if (this.world.rand.nextInt(10) == 0)
        {
            EntityMarshmallow marshmallow = new EntityMarshmallow(this.world);
            marshmallow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            marshmallow.onInitialSpawn(difficulty, (IEntityLivingData)null);
            marshmallow.setGrowingAge(-24000);
            this.world.spawnEntity(marshmallow);
            marshmallow.startRiding(this);
        }
        return super.onInitialSpawn(difficulty, data);
    }

    @Override
    public EntityBearry createChild(EntityAgeable ageable)
    {
        EntityBearry pet = new EntityBearry(this.world);
        UUID uuid = this.getOwnerId();

        if (uuid != null)
        {
            pet.setOwnerId(uuid);
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
        else if (!(otherAnimal instanceof EntityBearry))
        {
            return false;
        }
        else
        {
            EntityBearry pet = (EntityBearry)otherAnimal;
            return !pet.isTamed() ? false : pet.isSitting() ? false : this.isInLove() && pet.isInLove();
        }
    }

    @Override
    protected ItemStack getLayItem()
    {
        return new ItemStack(MPItems.STRAWBERRY);
    }
}