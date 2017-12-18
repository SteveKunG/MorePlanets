package stevekung.mods.moreplanets.module.planets.diona.entity;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;

public class EntityAlbetiusWorm extends EntityMob implements IEntityBreathable
{
    private AISummonSilverfish summonSilverfish;

    public EntityAlbetiusWorm(World world)
    {
        super(world);
        this.setSize(0.3F, 0.3F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, this.summonSilverfish = new AISummonSilverfish(this));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new AIHideInStone(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
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
        return 0.1F;
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.silverfish.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.silverfish.hit";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.silverfish.kill";
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
            if (source instanceof EntityDamageSource || source == DamageSource.magic)
            {
                this.summonSilverfish.func_179462_f();
            }
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block)
    {
        this.playSound("mob.silverfish.step", 0.15F, 1.0F);
    }

    @Override
    public void onUpdate()
    {
        this.renderYawOffset = this.rotationYaw;
        super.onUpdate();
    }

    @Override
    public float getBlockPathWeight(BlockPos pos)
    {
        return this.worldObj.getBlockState(pos.down()) == DionaBlocks.DIONA_BLOCK.getStateFromMeta(2) ? 10.0F : super.getBlockPathWeight(pos);
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
            EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 5.0D);
            return entityplayer == null;
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
        private final EntityAlbetiusWorm field_179485_a;
        private EnumFacing facing;
        private boolean field_179484_c;

        public AIHideInStone(EntityAlbetiusWorm p_i45827_1_)
        {
            super(p_i45827_1_, 1.0D, 10);
            this.field_179485_a = p_i45827_1_;
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute()
        {
            if (this.field_179485_a.getAttackTarget() != null)
            {
                return false;
            }
            else if (!this.field_179485_a.getNavigator().noPath())
            {
                return false;
            }
            else
            {
                Random random = this.field_179485_a.getRNG();

                if (random.nextInt(10) == 0)
                {
                    this.facing = EnumFacing.random(random);
                    BlockPos blockpos = new BlockPos(this.field_179485_a.posX, this.field_179485_a.posY + 0.5D, this.field_179485_a.posZ).offset(this.facing);
                    IBlockState iblockstate = this.field_179485_a.worldObj.getBlockState(blockpos);

                    if (iblockstate == DionaBlocks.DIONA_BLOCK.getStateFromMeta(2))
                    {
                        this.field_179484_c = true;
                        return true;
                    }
                }
                this.field_179484_c = false;
                return super.shouldExecute();
            }
        }

        @Override
        public boolean continueExecuting()
        {
            return this.field_179484_c ? false : super.continueExecuting();
        }

        @Override
        public void startExecuting()
        {
            if (!this.field_179484_c)
            {
                super.startExecuting();
            }
            else
            {
                World world = this.field_179485_a.worldObj;
                BlockPos blockpos = new BlockPos(this.field_179485_a.posX, this.field_179485_a.posY + 0.5D, this.field_179485_a.posZ).offset(this.facing);
                IBlockState iblockstate = world.getBlockState(blockpos);

                if (iblockstate == DionaBlocks.DIONA_BLOCK.getStateFromMeta(2))
                {
                    world.setBlockState(blockpos, DionaBlocks.ALBETIUS_WORM_EGG_ROCK.getDefaultState(), 3);
                    this.field_179485_a.spawnExplosionParticle();
                    this.field_179485_a.setDead();
                }
            }
        }
    }

    static class AISummonSilverfish extends EntityAIBase
    {
        private EntityAlbetiusWorm silverfish;
        private int field_179463_b;

        public AISummonSilverfish(EntityAlbetiusWorm p_i45826_1_)
        {
            this.silverfish = p_i45826_1_;
        }

        public void func_179462_f()
        {
            if (this.field_179463_b == 0)
            {
                this.field_179463_b = 20;
            }
        }

        @Override
        public boolean shouldExecute()
        {
            return this.field_179463_b > 0;
        }

        @Override
        public void updateTask()
        {
            --this.field_179463_b;

            if (this.field_179463_b <= 0)
            {
                World world = this.silverfish.worldObj;
                Random random = this.silverfish.getRNG();
                BlockPos blockpos = new BlockPos(this.silverfish);

                for (int i = 0; i <= 5 && i >= -5; i = i <= 0 ? 1 - i : 0 - i)
                {
                    for (int j = 0; j <= 10 && j >= -10; j = j <= 0 ? 1 - j : 0 - j)
                    {
                        for (int k = 0; k <= 10 && k >= -10; k = k <= 0 ? 1 - k : 0 - k)
                        {
                            BlockPos blockpos1 = blockpos.add(j, i, k);
                            IBlockState iblockstate = world.getBlockState(blockpos1);

                            if (iblockstate.getBlock() == DionaBlocks.ALBETIUS_WORM_EGG_ROCK)
                            {
                                if (world.getGameRules().getBoolean("mobGriefing"))
                                {
                                    world.destroyBlock(blockpos1, true);
                                }
                                else
                                {
                                    world.setBlockState(blockpos1, DionaBlocks.ALBETIUS_WORM_EGG_ROCK.getDefaultState(), 3);
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
    }
}