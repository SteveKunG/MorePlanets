package stevekung.mods.moreplanets.planets.nibiru.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.EntityEffectUtils;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;

public class EntityGiantWorm extends EntityMob implements IEntityBreathable, ISpaceMob
{
    public EntityGiantWorm(World world)
    {
        super(world);
        this.setSize(1.2F, 0.4F);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3F);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (super.attackEntityAsMob(entity))
        {
            return EntityEffectUtils.addInfectedSpore(entity);
        }
        return false;
    }

    @Override
    public float getEyeHeight()
    {
        return 0.1F;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SILVERFISH_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block)
    {
        this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.15F, 1.0F);
    }

    @Override
    public void onUpdate()
    {
        this.renderYawOffset = this.rotationYaw;
        super.onUpdate();
    }

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.checkNoEntityCollision(this.getEntityBoundingBox()) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox()) && this.world.canSeeSky(this.getPosition()) && this.world.getLightBrightness(this.getPosition()) <= 1.0F;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.NIBIRU;
    }
}