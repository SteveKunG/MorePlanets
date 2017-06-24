package stevekung.mods.moreplanets.module.planets.chalos.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.entity.EntitySlimeBaseMP;

public class EntityCheeseSlime extends EntitySlimeBaseMP
{
    public EntityCheeseSlime(World world)
    {
        super(world);
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return this.getSlimeSize() == 1 ? MPLootTables.CHEESE_SLIME : null;
    }

    @Override
    protected EntitySlimeBaseMP createInstance()
    {
        return new EntityCheeseSlime(this.worldObj);
    }

    @Override
    public int getJumpDelay()
    {
        return this.rand.nextInt(50) + 25;
    }

    @Override
    protected double getDetectRange()
    {
        return 0.6D;
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
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.CHEESE_SLIME, d0, this.getEntityBoundingBox().minY, d1);
        }
    }

    @Override
    protected void overrideHealth()
    {
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getSlimeSize() * this.getSlimeSize());
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox()) && this.worldObj.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.containsAnyLiquid(this.getEntityBoundingBox()) && this.worldObj.getLightBrightness(this.getPosition()) >= 0.0F;
    }
}