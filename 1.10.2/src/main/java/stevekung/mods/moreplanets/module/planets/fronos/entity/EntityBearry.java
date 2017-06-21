package stevekung.mods.moreplanets.module.planets.fronos.entity;

import java.util.UUID;

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

public class EntityBearry extends EntityFronosPet
{
    public EntityBearry(World world)
    {
        super(world);
        this.setSize(0.7F, 1.2F);
        this.aiTexture = new EntityAIFaceTexture(this);
        this.aiPanic = new EntityAIFronosPanic(this, 1.75D);
        this.aiTempt = new EntityAIFronosTempt(this, 1.4D, new ItemStack(FronosItems.FRONOS_FOOD, 1, 0), false);
        this.timeUntilToDropItem = this.rand.nextInt(6000) + 2000;
        this.setTamed(false);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiPanic);
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 8.0F, 2.0F));
        this.tasks.addTask(5, this.aiTempt);
        this.tasks.addTask(5, this.aiTexture);
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
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
        return this.isSitting() ? this.height * 0.75D : this.height * 1.0D;
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
    protected void dropFewItems(boolean drop, int fortune)
    {
        int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + fortune);

        for (int i = 0; i < j; ++i)
        {
            this.entityDropItem(new ItemStack(FronosItems.FRONOS_FRUITS, 1, 0), 0.0F);
        }
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
            this.startRiding(marshmallow);
        }
        return super.onInitialSpawn(difficulty, data);
    }

    @Override
    public EntityBearry createChild(EntityAgeable ageable)
    {
        EntityBearry pet = new EntityBearry(this.worldObj);
        UUID uuid = this.getOwnerId();

        if (uuid != null)
        {
            pet.setOwnerId(uuid);
            pet.setTamed(true);
        }
        return pet;
    }

    @Override
    protected ItemStack getLayItem()
    {
        return new ItemStack(FronosItems.FRONOS_FRUITS, 1, 0);
    }
}