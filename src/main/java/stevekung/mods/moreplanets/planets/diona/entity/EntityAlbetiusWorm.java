package stevekung.mods.moreplanets.planets.diona.entity;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import stevekung.mods.moreplanets.init.MPBlocks;

public class EntityAlbetiusWorm extends EntityMob implements IEntityBreathable
{
    private AISummonReinforcement summonReinforcement;

    public EntityAlbetiusWorm(World world)
    {
        super(world);
        this.setSize(0.4F, 0.1F);
    }

    @Override
    protected void initEntityAI()
    {
        this.summonReinforcement = new AISummonReinforcement(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, this.summonReinforcement);
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new AIHideInStone(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    }

    @Override
    protected boolean canTriggerWalking()
    {
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
        return 0.05F;
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
            if ((source instanceof EntityDamageSource || source == DamageSource.MAGIC) && this.summonReinforcement != null)
            {
                this.summonReinforcement.notifyHurt();
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
        return this.world.getBlockState(pos.down()).getBlock() == MPBlocks.DIONA_ROCK ? 10.0F : super.getBlockPathWeight(pos);
    }

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        if (super.getCanSpawnHere())
        {
            EntityPlayer player = this.world.getNearestPlayerNotCreative(this, 5.0D);
            return player == null;
        }
        else
        {
            return false;
        }
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

    static class AIHideInStone extends EntityAIWander
    {
        private EnumFacing facing;
        private boolean doMerge;

        public AIHideInStone(EntityAlbetiusWorm entity)
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
                Random rand = this.entity.getRNG();

                if (ForgeEventFactory.getMobGriefingEvent(this.entity.world, this.entity) && rand.nextInt(10) == 0)
                {
                    this.facing = EnumFacing.random(rand);
                    BlockPos blockpos = new BlockPos(this.entity.posX, this.entity.posY + 0.5D, this.entity.posZ).offset(this.facing);
                    IBlockState iblockstate = this.entity.world.getBlockState(blockpos);

                    if (iblockstate == MPBlocks.DIONA_ROCK.getDefaultState())
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
                BlockPos pos = new BlockPos(this.entity.posX, this.entity.posY + 0.5D, this.entity.posZ).offset(this.facing);

                if (world.getBlockState(pos).getBlock() == MPBlocks.DIONA_ROCK)
                {
                    world.setBlockState(pos, MPBlocks.ALBETIUS_WORM_EGG_ROCK.getDefaultState(), 3);
                    this.entity.spawnExplosionParticle();
                    this.entity.setDead();
                }
            }
        }
    }

    static class AISummonReinforcement extends EntityAIBase
    {
        private final EntityAlbetiusWorm entity;
        private int lookForFriends;

        public AISummonReinforcement(EntityAlbetiusWorm entity)
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
                Random rand = this.entity.getRNG();
                BlockPos pos = new BlockPos(this.entity);

                for (int i = 0; i <= 5 && i >= -5; i = i <= 0 ? 1 - i : 0 - i)
                {
                    for (int j = 0; j <= 10 && j >= -10; j = j <= 0 ? 1 - j : 0 - j)
                    {
                        for (int k = 0; k <= 10 && k >= -10; k = k <= 0 ? 1 - k : 0 - k)
                        {
                            BlockPos pos1 = pos.add(j, i, k);
                            IBlockState iblockstate = world.getBlockState(pos1);

                            if (iblockstate.getBlock() == MPBlocks.ALBETIUS_WORM_EGG_ROCK)
                            {
                                if (ForgeEventFactory.getMobGriefingEvent(world, this.entity))
                                {
                                    world.destroyBlock(pos1, true);
                                }
                                else
                                {
                                    world.setBlockState(pos1, MPBlocks.ALBETIUS_WORM_EGG_ROCK.getDefaultState(), 3);
                                }

                                if (rand.nextBoolean())
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