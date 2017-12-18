package stevekung.mods.moreplanets.module.planets.fronos.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.module.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.entity.EntitySlimeBaseMP;

public class EntityJellySlime extends EntitySlimeBaseMP
{
    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityJellySlime.class, DataSerializers.VARINT);

    public EntityJellySlime(World world)
    {
        super(world);
        this.setJellySlimeType(this.rand.nextInt(8));
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(TYPE, Integer.valueOf(0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("SlimeType", this.getJellySlimeType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.setJellySlimeType(nbt.getInteger("SlimeType"));
    }

    @Override
    public void setDead()
    {
        int i = this.getSlimeSize();

        if (!this.world.isRemote && i > 1 && this.getHealth() <= 0.0F)
        {
            int j = 2 + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                float f = (k % 2 - 0.5F) * i / 4.0F;
                float f1 = (k / 2 - 0.5F) * i / 4.0F;
                EntityJellySlime entityslime = this.createInstance();

                if (this.hasCustomName())
                {
                    entityslime.setCustomNameTag(this.getCustomNameTag());
                }
                if (this.isNoDespawnRequired())
                {
                    entityslime.enablePersistence();
                }
                entityslime.setJellySlimeType(this.getJellySlimeType());
                entityslime.setSlimeSize(i / 2);
                entityslime.setLocationAndAngles(this.posX + f, this.posY + 0.5D, this.posZ + f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.world.spawnEntity(entityslime);
            }
        }
        this.isDead = true;
    }

    public int getJellySlimeType()
    {
        return this.dataManager.get(TYPE);
    }

    public void setJellySlimeType(int type)
    {
        this.dataManager.set(TYPE, Integer.valueOf(type));
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        if (this.getSlimeSize() == 1)
        {
            switch (this.getJellySlimeType())
            {
            case 0:
                return MPLootTables.JELLY_SLIME_GRAPE;
            case 1:
                return MPLootTables.JELLY_SLIME_RASPBERRY;
            case 2:
                return MPLootTables.JELLY_SLIME_STRAWBERRY;
            case 3:
                return MPLootTables.JELLY_SLIME_BERRY;
            case 4:
                return MPLootTables.JELLY_SLIME_LIME;
            case 5:
                return MPLootTables.JELLY_SLIME_ORANGE;
            case 6:
                return MPLootTables.JELLY_SLIME_GREEN;
            case 7:
                return MPLootTables.JELLY_SLIME_LEMON;
            }
        }
        return null;
    }

    @Override
    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.95F;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance diff, IEntityLivingData data)
    {
        int i = this.rand.nextInt(2);

        if (i < 2 && this.rand.nextFloat() < 0.5F * diff.getClampedAdditionalDifficulty())
        {
            ++i;
        }
        int j = 1 << i;
        this.setSlimeSize(j);
        return data;
    }

    @Override
    protected double getDetectRange()
    {
        return 0.25D;
    }

    @Override
    public int getJumpDelay()
    {
        return this.rand.nextInt(50) + 50;
    }

    @Override
    protected EntityJellySlime createInstance()
    {
        return new EntityJellySlime(this.world);
    }

    @Override
    protected void createParticles()
    {
        int i = this.getSlimeSize();

        for (int j = 0; j < i * 8; ++j)
        {
            float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
            float f2 = MathHelper.sin(f) * i * 0.5F * f1;
            float f3 = MathHelper.cos(f) * i * 0.5F * f1;
            double d0 = this.posX + f2;
            double d1 = this.posZ + f3;
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.CUSTOM_BREAKING_META, d0, this.getEntityBoundingBox().minY, d1, new Object[] { FronosItems.JELLY, this.getJellySlimeType() });
        }
    }

    @Override
    protected void overrideHealth()
    {
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getSlimeSize() * this.getSlimeSize());
    }
}