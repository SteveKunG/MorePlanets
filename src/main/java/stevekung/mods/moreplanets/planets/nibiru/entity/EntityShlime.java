package stevekung.mods.moreplanets.planets.nibiru.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.*;
import stevekung.mods.moreplanets.planets.nibiru.entity.ai.EntityAIFleeNibiruThunder;
import stevekung.mods.moreplanets.planets.nibiru.entity.ai.EntityAIShlimeEatGrass;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;
import stevekung.mods.moreplanets.utils.entity.ai.PathNavigateGroundMP;

public class EntityShlime extends EntityAnimal implements IShearable, ISpaceMob, IEntityBreathable
{
    private static final DataParameter<Byte> DYE_COLOR = EntityDataManager.createKey(EntityShlime.class, DataSerializers.BYTE);

    private final InventoryCrafting inventoryCrafting = new InventoryCrafting(new Container()
    {
        @Override
        public boolean canInteractWith(EntityPlayer player)
        {
            return false;
        }
    }, 2, 1);

    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;
    private EntityAIShlimeEatGrass entityAIEatGrass;
    private int sheepTimer;
    private float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;

    public EntityShlime(World world)
    {
        super(world);
        this.setSize(0.675F, 0.75F);
        this.jumpHelper = new ShlimeJumpHelper(this);
        this.moveHelper = new ShlimeMoveHelper(this);
        this.inventoryCrafting.setInventorySlotContents(0, new ItemStack(Items.DYE, 1, 0));
        this.inventoryCrafting.setInventorySlotContents(1, new ItemStack(Items.DYE, 1, 0));
        this.setMovementSpeed(0.0D);
    }

    @Override
    protected void initEntityAI()
    {
        this.entityAIEatGrass = new EntityAIShlimeEatGrass(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new AIPanic(this, 2.2D));
        this.tasks.addTask(2, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(3, new EntityAITempt(this, 0.6D, false, Sets.newHashSet(MPItems.INFECTED_WHEAT, MPItems.TERRABERRY)));
        this.tasks.addTask(5, this.entityAIEatGrass);
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIFleeNibiruThunder(this, 2.2D));
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    @Override
    protected void updateFallState(double y, boolean onGround, IBlockState state, BlockPos pos)
    {
        if (!this.isInWater())
        {
            this.handleWaterMovement();
        }
        if (onGround)
        {
            if (this.fallDistance > 0.0F)
            {
                state.getBlock().onFallenUpon(this.world, pos, this, this.fallDistance);
            }
            this.fallDistance = 0.0F;
        }
        else if (y < 0.0D)
        {
            this.fallDistance = (float)(this.fallDistance - y);
        }
    }

    @Override
    protected PathNavigate createNavigator(World world)
    {
        return new PathNavigateGroundMP(this, world);
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
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

    @Override
    public boolean getCanSpawnHere()
    {
        Block block = this.world.getBlockState(this.getPosition().down()).getBlock();
        return (block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.GREEN_VEIN_GRASS_BLOCK) && this.world.getLight(this.getPosition()) > 8 && this.getBlockPathWeight(this.getPosition()) >= 0.0F;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(DYE_COLOR, (byte)0);
    }

    @Override
    public void onUpdate()
    {
        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        super.onUpdate();

        if (this.onGround && !this.wasOnGround)
        {
            this.squishAmount = -0.5F;
        }
        else if (!this.onGround && this.wasOnGround)
        {
            this.squishAmount = 1.0F;
        }
        if (this.sheepTimer > 0 && this.sheepTimer <= 40)
        {
            Block blockDown = this.world.getBlockState(this.getPosition().down()).getBlock();
            IBlockState state = this.world.getBlockState(this.getPosition());

            if (blockDown == MPBlocks.INFECTED_GRASS_BLOCK)
            {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextFloat() - 0.5D) * this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D, (this.rand.nextFloat() - 0.5D) * 4.0D, new int[] {Block.getStateId(MPBlocks.INFECTED_GRASS_BLOCK.getDefaultState())});
            }
            else if (blockDown == MPBlocks.GREEN_VEIN_GRASS_BLOCK)
            {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextFloat() - 0.5D) * this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D, (this.rand.nextFloat() - 0.5D) * 4.0D, new int[] {Block.getStateId(MPBlocks.GREEN_VEIN_GRASS_BLOCK.getDefaultState())});
            }
            else if (state == MPBlocks.INFECTED_GRASS.getDefaultState())
            {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextFloat() - 0.5D) * this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D, (this.rand.nextFloat() - 0.5D) * 4.0D, new int[] {Block.getStateId(MPBlocks.INFECTED_GRASS.getDefaultState())});
            }
            else if (state == MPBlocks.GREEN_VEIN_GRASS.getDefaultState())
            {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextFloat() - 0.5D) * this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D, (this.rand.nextFloat() - 0.5D) * 4.0D, new int[] {Block.getStateId(MPBlocks.GREEN_VEIN_GRASS.getDefaultState())});
            }
        }
        this.alterSquishAmount();
    }

    @Override
    protected float getJumpUpwardsMotion()
    {
        if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.posY + 0.5D))
        {
            Path path = this.navigator.getPath();

            if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
            {
                Vec3d vec3d = path.getPosition(this);

                if (vec3d.y > this.posY + 0.5D)
                {
                    return 0.7F;
                }
            }
            return this.moveHelper.getSpeed() <= 0.6D ? 0.4F : 0.7F;
        }
        else
        {
            return 0.7F;
        }
    }

    @Override
    protected void jump()
    {
        super.jump();
        double d0 = this.moveHelper.getSpeed();

        if (d0 > 0.0D)
        {
            double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;

            if (d1 < 0.010000000000000002D)
            {
                this.moveRelative(0.0F, 0.0F, 1.0F, 0.2F);
            }
        }
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)1);
        }
    }

    @Override
    public void setJumping(boolean jumping)
    {
        super.setJumping(jumping);

        if (jumping)
        {
            this.playSound(SoundEvents.ENTITY_SMALL_SLIME_HURT, 0.3F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.6F) * 0.8F);
        }
    }

    @Override
    public void updateAITasks()
    {
        if (this.currentMoveTypeDuration > 0)
        {
            --this.currentMoveTypeDuration;
        }
        if (this.onGround)
        {
            if (!this.wasOnGround)
            {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            ShlimeJumpHelper helper = (ShlimeJumpHelper)this.jumpHelper;

            if (!helper.getIsJumping())
            {
                if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0)
                {
                    Path path = this.navigator.getPath();
                    Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());

                    if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
                    {
                        vec3d = path.getPosition(this);
                    }
                    this.calculateRotationYaw(vec3d.x, vec3d.z);
                    this.startJumping();
                }
            }
            else if (!helper.canJump())
            {
                this.enableJumpControl();
            }
        }
        this.wasOnGround = this.onGround;
        this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
        super.updateAITasks();
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.jumpTicks != this.jumpDuration)
        {
            ++this.jumpTicks;
        }
        else if (this.jumpDuration != 0)
        {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
        if (this.world.isRemote)
        {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return MPSounds.SHLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return MPSounds.SHLIME_DEATH;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        if (this.getSheared())
        {
            return MPLootTables.SHLIME;
        }
        else
        {
            switch (this.getFleeceColor())
            {
            case WHITE:
            default:
                return MPLootTables.SHLIME_WOOL_WHITE;
            case ORANGE:
                return MPLootTables.SHLIME_WOOL_ORANGE;
            case MAGENTA:
                return MPLootTables.SHLIME_WOOL_MAGENTA;
            case LIGHT_BLUE:
                return MPLootTables.SHLIME_WOOL_LIGHT_BLUE;
            case YELLOW:
                return MPLootTables.SHLIME_WOOL_YELLOW;
            case LIME:
                return MPLootTables.SHLIME_WOOL_LIME;
            case PINK:
                return MPLootTables.SHLIME_WOOL_PINK;
            case GRAY:
                return MPLootTables.SHLIME_WOOL_GRAY;
            case SILVER:
                return MPLootTables.SHLIME_WOOL_SILVER;
            case CYAN:
                return MPLootTables.SHLIME_WOOL_CYAN;
            case PURPLE:
                return MPLootTables.SHLIME_WOOL_PURPLE;
            case BLUE:
                return MPLootTables.SHLIME_WOOL_BLUE;
            case BROWN:
                return MPLootTables.SHLIME_WOOL_BROWN;
            case GREEN:
                return MPLootTables.SHLIME_WOOL_GREEN;
            case RED:
                return MPLootTables.SHLIME_WOOL_RED;
            case BLACK:
                return MPLootTables.SHLIME_WOOL_BLACK;
            }
        }
    }

    @Override
    public EntityShlime createChild(EntityAgeable ageable)
    {
        EntityShlime parent = (EntityShlime)ageable;
        EntityShlime child = new EntityShlime(this.world);
        child.setFleeceColor(this.getDyeColorMixFromParents(this, parent));
        return child;
    }

    @Override
    public void eatGrassBonus()
    {
        this.setSheared(false);

        if (this.isChild())
        {
            this.addGrowth(60);
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        this.setFleeceColor(this.getRandomSheepColor(this.world.rand));
        return super.onInitialSpawn(difficulty, data);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack)
    {
        return !itemStack.isEmpty() && (itemStack.getItem() == MPItems.INFECTED_WHEAT || itemStack.getItem() == MPItems.TERRABERRY);
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        return !this.getSheared() && !this.isChild();
    }

    @Override
    public List<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        this.setSheared(true);
        int i = 1 + this.rand.nextInt(3);
        List<ItemStack> ret = new ArrayList<>();

        for (int j = 0; j < i; ++j)
        {
            ret.add(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, this.getFleeceColor().getMetadata()));
        }
        this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
        return ret;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setBoolean("Sheared", this.getSheared());
        tagCompound.setByte("Color", (byte)this.getFleeceColor().getMetadata());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.setSheared(tagCompund.getBoolean("Sheared"));
        this.setFleeceColor(EnumDyeColor.byMetadata(tagCompund.getByte("Color")));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 1)
        {
            this.createRunningParticles();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        }
        else if (id == 10)
        {
            this.sheepTimer = 40;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotationAngleX(float partialTicks)
    {
        if (this.sheepTimer > 4 && this.sheepTimer <= 36)
        {
            float f = (this.sheepTimer - 4 - partialTicks) / 16.0F;
            return -(float)Math.PI / 5F + (float)Math.PI * 7F / 50F * MathHelper.sin(f * 28.7F);
        }
        else
        {
            return this.sheepTimer > 0 ? (float)Math.PI / 5F : -(240F / (float)Math.PI);
        }
    }

    public EnumDyeColor getFleeceColor()
    {
        return EnumDyeColor.byMetadata(this.dataManager.get(DYE_COLOR).byteValue() & 15);
    }

    public void setFleeceColor(EnumDyeColor color)
    {
        byte b0 = this.dataManager.get(DYE_COLOR).byteValue();
        this.dataManager.set(DYE_COLOR, (byte)(b0 & 240 | color.getMetadata() & 15));
    }

    public boolean getSheared()
    {
        return (this.dataManager.get(DYE_COLOR).byteValue() & 16) != 0;
    }

    private void setMovementSpeed(double newSpeed)
    {
        this.getNavigator().setSpeed(newSpeed);
        this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
    }

    private void startJumping()
    {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    private void setSheared(boolean sheared)
    {
        byte b0 = this.dataManager.get(DYE_COLOR).byteValue();

        if (sheared)
        {
            this.dataManager.set(DYE_COLOR, (byte)(b0 | 16));
        }
        else
        {
            this.dataManager.set(DYE_COLOR, (byte)(b0 & -17));
        }
    }

    private EnumDyeColor getRandomSheepColor(Random random)
    {
        int i = random.nextInt(100);
        return i < 5 ? EnumDyeColor.BLACK : i < 10 ? EnumDyeColor.GRAY : i < 15 ? EnumDyeColor.SILVER : i < 18 ? EnumDyeColor.BROWN : random.nextInt(500) == 0 ? EnumDyeColor.PINK : EnumDyeColor.WHITE;
    }

    private EnumDyeColor getDyeColorMixFromParents(EntityAnimal father, EntityAnimal mother)
    {
        int i = ((EntityShlime)father).getFleeceColor().getDyeDamage();
        int j = ((EntityShlime)mother).getFleeceColor().getDyeDamage();
        this.inventoryCrafting.getStackInSlot(0).setItemDamage(i);
        this.inventoryCrafting.getStackInSlot(1).setItemDamage(j);
        ItemStack itemStack = CraftingManager.findMatchingResult(this.inventoryCrafting, ((EntityShlime)father).world);
        int k;

        if (!itemStack.isEmpty() && itemStack.getItem() == Items.DYE)
        {
            k = itemStack.getMetadata();
        }
        else
        {
            k = this.world.rand.nextBoolean() ? i : j;
        }
        return EnumDyeColor.byDyeDamage(k);
    }

    private void alterSquishAmount()
    {
        this.squishAmount *= 0.6F;
    }

    private void calculateRotationYaw(double x, double z)
    {
        this.rotationYaw = (float)(MathHelper.atan2(z - this.posZ, x - this.posX) * (180D / Math.PI)) - 90.0F;
    }

    private void enableJumpControl()
    {
        ((ShlimeJumpHelper)this.jumpHelper).setCanJump(true);
    }

    private void disableJumpControl()
    {
        ((ShlimeJumpHelper)this.jumpHelper).setCanJump(false);
    }

    private void updateMoveTypeDuration()
    {
        if (this.moveHelper.getSpeed() < 2.2D)
        {
            this.currentMoveTypeDuration = 10;
        }
        else
        {
            this.currentMoveTypeDuration = 1;
        }
    }

    private void checkLandingDelay()
    {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }

    static class ShlimeJumpHelper extends EntityJumpHelper
    {
        private final EntityShlime entity;
        private boolean canJump;

        public ShlimeJumpHelper(EntityShlime entity)
        {
            super(entity);
            this.entity = entity;
        }

        @Override
        public void doJump()
        {
            if (this.isJumping)
            {
                this.entity.startJumping();
                this.isJumping = false;
            }
        }

        public boolean getIsJumping()
        {
            return this.isJumping;
        }

        public boolean canJump()
        {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn)
        {
            this.canJump = canJumpIn;
        }
    }

    static class ShlimeMoveHelper extends EntityMoveHelper
    {
        private final EntityShlime entity;
        private double nextJumpSpeed;

        public ShlimeMoveHelper(EntityShlime entity)
        {
            super(entity);
            this.entity = entity;
        }

        @Override
        public void onUpdateMoveHelper()
        {
            if (this.entity.onGround && !this.entity.isJumping && !((ShlimeJumpHelper)this.entity.jumpHelper).getIsJumping())
            {
                this.entity.setMovementSpeed(0.0D);
            }
            else if (this.isUpdating())
            {
                this.entity.setMovementSpeed(this.nextJumpSpeed);
            }
            super.onUpdateMoveHelper();
        }

        @Override
        public void setMoveTo(double x, double y, double z, double speed)
        {
            if (this.entity.isInWater())
            {
                speed = 1.5D;
            }

            super.setMoveTo(x, y, z, speed);

            if (speed > 0.0D)
            {
                this.nextJumpSpeed = speed;
            }
        }
    }

    static class AIPanic extends EntityAIPanic
    {
        private final EntityShlime entity;

        public AIPanic(EntityShlime entity, double speed)
        {
            super(entity, speed);
            this.entity = entity;
        }

        @Override
        public void updateTask()
        {
            super.updateTask();
            this.entity.setMovementSpeed(this.speed);
        }
    }
}