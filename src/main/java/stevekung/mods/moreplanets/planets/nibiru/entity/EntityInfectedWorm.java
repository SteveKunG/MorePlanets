package stevekung.mods.moreplanets.planets.nibiru.entity;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.planets.nibiru.blocks.BlockNibiruInfested;
import stevekung.mods.moreplanets.utils.EntityEffectUtils;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;

public class EntityInfectedWorm extends EntityMob implements IEntityBreathable, ISpaceMob
{
    private AISummonWorm summonSilverfish;

    public EntityInfectedWorm(World world)
    {
        super(world);
        this.setSize(0.4F, 0.3F);
    }

    @Override
    protected void initEntityAI()
    {
        this.summonSilverfish = new AISummonWorm(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, this.summonSilverfish);
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new AIHideInStone(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
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
    public double getYOffset()
    {
        return 0.2D;
    }

    @Override
    public float getEyeHeight()
    {
        return 0.1F;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
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
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if ((source instanceof EntityDamageSource || source == DamageSource.MAGIC) && this.summonSilverfish != null)
            {
                this.summonSilverfish.notifyHurt();
            }
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public void onUpdate()
    {
        this.renderYawOffset = this.rotationYaw;
        super.onUpdate();
    }

    @Override
    public void setRenderYawOffset(float offset)
    {
        this.rotationYaw = offset;
        super.setRenderYawOffset(offset);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos)
    {
        return this.world.getBlockState(pos.down()) == MPBlocks.INFESTED_NIBIRU_ROCK.getDefaultState() ? 10.0F : super.getBlockPathWeight(pos);
    }

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.NIBIRU;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        if (super.getCanSpawnHere())
        {
            EntityPlayer entityplayer = this.world.getNearestPlayerNotCreative(this, 5.0D);
            return entityplayer == null;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    private boolean canContainSilverfish(IBlockState state)
    {
        return state == MPBlocks.NIBIRU_ROCK.getDefaultState() || state == MPBlocks.NIBIRU_COBBLESTONE.getDefaultState() || state == MPBlocks.NIBIRU_VEIN_COBBLESTONE.getDefaultState() || state == MPBlocks.INFECTED_STONE_BRICKS.getDefaultState() || state == MPBlocks.INFECTED_VEIN_STONE_BRICKS.getDefaultState() || state == MPBlocks.INFECTED_CRACKED_STONE_BRICKS.getDefaultState() || state == MPBlocks.INFECTED_CHISELED_STONE_BRICKS.getDefaultState();
    }

    static class AIHideInStone extends EntityAIWander
    {
        private EnumFacing facing;
        private boolean doMerge;

        public AIHideInStone(EntityInfectedWorm entity)
        {
            super(entity, 1.0D, 10);
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute()
        {
            if (this.entity.getAttackTarget() != null)
            {
                return false;
            }
            else if (!this.entity.getNavigator().noPath())
            {
                return false;
            }
            else
            {
                Random random = this.entity.getRNG();

                if (random.nextInt(10) == 0)
                {
                    this.facing = EnumFacing.random(random);
                    BlockPos blockpos = new BlockPos(this.entity.posX, this.entity.posY + 0.5D, this.entity.posZ).offset(this.facing);
                    IBlockState iblockstate = this.entity.world.getBlockState(blockpos);

                    if (((EntityInfectedWorm)this.entity).canContainSilverfish(iblockstate))
                    {
                        this.doMerge = true;
                        return true;
                    }
                }
                this.doMerge = false;
                return super.shouldExecute();
            }
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            return this.doMerge ? false : super.shouldContinueExecuting();
        }

        @Override
        public void startExecuting()
        {
            if (!this.doMerge)
            {
                super.startExecuting();
            }
            else
            {
                World world = this.entity.world;
                BlockPos blockpos = new BlockPos(this.entity.posX, this.entity.posY + 0.5D, this.entity.posZ).offset(this.facing);
                IBlockState state = world.getBlockState(blockpos);

                if (((EntityInfectedWorm)this.entity).canContainSilverfish(state))
                {
                    world.setBlockState(blockpos, BlockNibiruInfested.getInfested(state.getBlock()).getDefaultState(), 3);
                    this.entity.spawnExplosionParticle();
                    this.entity.setDead();
                }
            }
        }
    }

    static class AISummonWorm extends EntityAIBase
    {
        private final EntityInfectedWorm entity;
        private int lookForFriends;

        public AISummonWorm(EntityInfectedWorm entity)
        {
            this.entity = entity;
        }

        @Override
        public boolean shouldExecute()
        {
            return this.lookForFriends > 0;
        }

        @Override
        public void updateTask()
        {
            --this.lookForFriends;

            if (this.lookForFriends <= 0)
            {
                World world = this.entity.world;
                Random random = this.entity.getRNG();
                BlockPos blockpos = new BlockPos(this.entity);

                for (int i = 0; i <= 5 && i >= -5; i = i <= 0 ? 1 - i : 0 - i)
                {
                    for (int j = 0; j <= 10 && j >= -10; j = j <= 0 ? 1 - j : 0 - j)
                    {
                        for (int k = 0; k <= 10 && k >= -10; k = k <= 0 ? 1 - k : 0 - k)
                        {
                            BlockPos blockpos1 = blockpos.add(j, i, k);
                            IBlockState iblockstate = world.getBlockState(blockpos1);

                            if (iblockstate.getBlock() instanceof BlockNibiruInfested)
                            {
                                if (world.getGameRules().getBoolean("mobGriefing"))
                                {
                                    world.destroyBlock(blockpos1, true);
                                }
                                else
                                {
                                    world.setBlockState(blockpos1, BlockNibiruInfested.getParent(iblockstate.getBlock()).getDefaultState(), 3);
                                }

                                if (random.nextBoolean())
                                {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }

        public void notifyHurt()
        {
            if (this.lookForFriends == 0)
            {
                this.lookForFriends = 20;
            }
        }
    }
}