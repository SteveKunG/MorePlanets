package stevekung.mods.moreplanets.module.planets.nibiru.entity;

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
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruSilverfish;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;
import stevekung.mods.moreplanets.util.helper.EntityEffectHelper;

public class EntityInfectedWorm extends EntityMob implements IEntityBreathable, ISpaceMob
{
    private EntityInfectedWorm.AISummonSilverfish summonSilverfish;

    public EntityInfectedWorm(World worldIn)
    {
        super(worldIn);
        this.setSize(0.4F, 0.3F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, this.summonSilverfish = new EntityInfectedWorm.AISummonSilverfish(this));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityInfectedWorm.AIHideInStone(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotionID() == MPPotions.INFECTED_SPORE.id ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (super.attackEntityAsMob(entity))
        {
            return EntityEffectHelper.addInfectedSpore(entity);
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
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
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound("mob.silverfish.step", 0.15F, 1.0F);
    }

    @Override
    protected Item getDropItem()
    {
        return null;
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
        return this.worldObj.getBlockState(pos.down()) == NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_ROCK) ? 10.0F : super.getBlockPathWeight(pos);
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
            EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 5.0D);
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
        return state == NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_ROCK) || state == NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE) || state == NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS) || state == NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_VEIN_STONE_BRICKS) || state == NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_CRACKED_STONE_BRICKS) || state == NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_CHISELED_STONE_BRICKS);
    }

    static class AIHideInStone extends EntityAIWander
    {
        private final EntityInfectedWorm field_179485_a;
        private EnumFacing facing;
        private boolean field_179484_c;

        public AIHideInStone(EntityInfectedWorm p_i45827_1_)
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

                    if (this.field_179485_a.canContainSilverfish(iblockstate))
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

                if (this.field_179485_a.canContainSilverfish(iblockstate))
                {
                    world.setBlockState(blockpos, NibiruBlocks.NIBIRU_SILVERFISH_STONE.getDefaultState().withProperty(BlockNibiruSilverfish.VARIANT, BlockNibiruSilverfish.BlockType.getParentBlock(iblockstate)), 3);
                    this.field_179485_a.spawnExplosionParticle();
                    this.field_179485_a.setDead();
                }
            }
        }
    }

    static class AISummonSilverfish extends EntityAIBase
    {
        private EntityInfectedWorm silverfish;
        private int field_179463_b;

        public AISummonSilverfish(EntityInfectedWorm p_i45826_1_)
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

                            if (iblockstate.getBlock() == NibiruBlocks.NIBIRU_SILVERFISH_STONE)
                            {
                                if (world.getGameRules().getBoolean("mobGriefing"))
                                {
                                    world.destroyBlock(blockpos1, true);
                                }
                                else
                                {
                                    world.setBlockState(blockpos1, iblockstate.withProperty(BlockNibiruSilverfish.VARIANT, BlockNibiruSilverfish.BlockType.getParentBlock(iblockstate)), 3);
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