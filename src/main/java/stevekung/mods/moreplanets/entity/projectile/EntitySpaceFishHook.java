package stevekung.mods.moreplanets.entity.projectile;

import java.util.List;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.TransformerHooks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.items.ItemSpaceFishingRod;
import stevekung.mods.moreplanets.utils.blocks.IFishableLiquidBlock;

public class EntitySpaceFishHook extends EntityFishHook implements IEntityAdditionalSpawnData, IThrowableEntity
{
    private static final DataParameter<Integer> DATA_HOOKED_ENTITY = EntityDataManager.createKey(EntitySpaceFishHook.class, DataSerializers.VARINT);
    private boolean inGround;
    private int ticksInGround = 0;
    private EntityPlayer angler;
    private int ticksInAir;
    private int ticksCatchable;
    private int ticksCaughtDelay;
    private int ticksCatchableDelay;
    private float fishApproachAngle;
    private State currentState = State.FLYING;
    private int luck;
    private int lureSpeed;

    public EntitySpaceFishHook(World world)
    {
        this(world, null);
    }

    @SideOnly(Side.CLIENT)
    public EntitySpaceFishHook(World world, EntityPlayer player, double x, double y, double z)
    {
        super(world, player, x, y, z);
        this.init(player);
        this.setPosition(x, y, z);
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
    }

    public EntitySpaceFishHook(World world, EntityPlayer fishingPlayer)
    {
        super(world, fishingPlayer);
        this.init(fishingPlayer);
        this.shoot();
    }

    private void init(EntityPlayer player)
    {
        this.setSize(0.25F, 0.25F);
        this.ignoreFrustumCheck = true;
        this.angler = player;
        this.angler.fishEntity = this;
    }

    @Override
    public void setLureSpeed(int lureSpeed)
    {
        this.lureSpeed = lureSpeed;
    }

    @Override
    public void setLuck(int luck)
    {
        this.luck = luck;
    }

    private void shoot()
    {
        float f = this.angler.prevRotationPitch + (this.angler.rotationPitch - this.angler.prevRotationPitch);
        float f1 = this.angler.prevRotationYaw + (this.angler.rotationYaw - this.angler.prevRotationYaw);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        double d0 = this.angler.prevPosX + (this.angler.posX - this.angler.prevPosX) - f3 * 0.3D;
        double d1 = this.angler.prevPosY + (this.angler.posY - this.angler.prevPosY) + this.angler.getEyeHeight();
        double d2 = this.angler.prevPosZ + (this.angler.posZ - this.angler.prevPosZ) - f2 * 0.3D;
        this.setLocationAndAngles(d0, d1, d2, f1, f);
        this.motionX = -f3;
        this.motionY = MathHelper.clamp(-(f5 / f4), -5.0F, 5.0F);
        this.motionZ = -f2;
        float f6 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        this.motionX *= 0.6D / f6 + 0.5D + this.rand.nextGaussian() * 0.0045D;
        this.motionY *= 0.6D / f6 + 0.5D + this.rand.nextGaussian() * 0.0045D;
        this.motionZ *= 0.6D / f6 + 0.5D + this.rand.nextGaussian() * 0.0045D;
        float f7 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(this.motionY, f7) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    @Override
    protected void entityInit()
    {
        this.getDataManager().register(DATA_HOOKED_ENTITY, 0);
    }

    @Override
    public void notifyDataManagerChange(DataParameter key)
    {
        if (DATA_HOOKED_ENTITY.equals(key))
        {
            int i = this.getDataManager().get(DATA_HOOKED_ENTITY).intValue();
            this.caughtEntity = i > 0 ? this.world.getEntityByID(i - 1) : null;
        }
    }

    @Override
    public void onUpdate()
    {
        if (!this.world.isRemote)
        {
            this.setFlag(6, this.isGlowing());
        }

        this.onEntityUpdate();

        if (this.angler == null)
        {
            this.setDead();
        }
        else if (this.world.isRemote || !this.shouldStopFishing())
        {
            if (this.inGround)
            {
                ++this.ticksInGround;

                if (this.ticksInGround >= 1200)
                {
                    this.setDead();
                    return;
                }
            }

            float f = 0.0F;
            BlockPos blockpos = new BlockPos(this);
            IBlockState iblockstate = this.world.getBlockState(blockpos);

            if (iblockstate.getMaterial() == Material.WATER)
            {
                f = BlockLiquid.getBlockLiquidHeight(iblockstate, this.world, blockpos);
            }
            if (this.currentState == State.FLYING)
            {
                if (this.caughtEntity != null)
                {
                    this.motionX = 0.0D;
                    this.motionY = 0.0D;
                    this.motionZ = 0.0D;
                    this.currentState = State.HOOKED_IN_ENTITY;
                    return;
                }
                if (f > 0.0F)
                {
                    this.motionX *= 0.3D;
                    this.motionY *= 0.2D;
                    this.motionZ *= 0.3D;
                    this.currentState = State.BOBBING;
                    return;
                }
                if (!this.world.isRemote)
                {
                    this.checkCollision();
                }

                if (!this.inGround && !this.onGround && !this.collidedHorizontally)
                {
                    ++this.ticksInAir;
                }
                else
                {
                    this.ticksInAir = 0;
                    this.motionX = 0.0D;
                    this.motionY = 0.0D;
                    this.motionZ = 0.0D;
                }
            }
            else
            {
                if (this.currentState == State.HOOKED_IN_ENTITY)
                {
                    if (this.caughtEntity != null)
                    {
                        if (this.caughtEntity.isDead)
                        {
                            this.caughtEntity = null;
                            this.currentState = State.FLYING;
                        }
                        else
                        {
                            this.posX = this.caughtEntity.posX;
                            double d2 = this.caughtEntity.height;
                            this.posY = this.caughtEntity.getEntityBoundingBox().minY + d2 * 0.8D;
                            this.posZ = this.caughtEntity.posZ;
                            this.setPosition(this.posX, this.posY, this.posZ);
                        }
                    }
                    return;
                }

                if (this.currentState == State.BOBBING)
                {
                    this.motionX *= 0.9D;
                    this.motionZ *= 0.9D;
                    double d0 = this.posY + this.motionY - blockpos.getY() - f;

                    if (Math.abs(d0) < 0.01D)
                    {
                        d0 += Math.signum(d0) * 0.1D;
                    }

                    this.motionY -= d0 * this.rand.nextFloat() * 0.2D;

                    if (!this.world.isRemote && f > 0.0F)
                    {
                        this.catchingFish(blockpos);
                    }
                }
            }
            if (iblockstate.getMaterial() != Material.WATER)
            {
                this.motionY -= 0.03D;
            }
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.updateRotation();
            this.motionX *= 0.92D;
            this.motionY *= 0.92D;
            this.motionZ *= 0.92D;

            if (!this.onGround)
            {
                this.motionY -= this.world.provider instanceof IGalacticraftWorldProvider ? TransformerHooks.getGravityForEntity(this) * -1.0D : 0.0D;
            }
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    private boolean shouldStopFishing()
    {
        ItemStack itemStack = this.angler.getHeldItemMainhand();
        ItemStack itemStack1 = this.angler.getHeldItemOffhand();
        boolean flag = itemStack.getItem() instanceof ItemSpaceFishingRod && itemStack.hasTagCompound() && itemStack.getTagCompound().getBoolean("Cast");
        boolean flag1 = itemStack1.getItem() instanceof ItemSpaceFishingRod && itemStack1.hasTagCompound() && itemStack1.getTagCompound().getBoolean("Cast");

        if (!this.angler.isDead && this.angler.isEntityAlive() && (flag || flag1) && this.getDistanceSq(this.angler) <= 1024.0D)
        {
            return false;
        }
        else
        {
            this.setDead();
            return true;
        }
    }

    private void updateRotation()
    {
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
        for (this.rotationPitch = (float)(MathHelper.atan2(this.motionY, f) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {}

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }
        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }
        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }
        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
    }

    private void checkCollision()
    {
        Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
        vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (raytraceresult != null)
        {
            vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
        }

        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
        double d0 = 0.0D;

        for (Entity entity1 : list)
        {
            if (this.canBeHooked(entity1) && (entity1 != this.angler || this.ticksInAir >= 5))
            {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);

                if (raytraceresult1 != null)
                {
                    double d1 = vec3d.squareDistanceTo(raytraceresult1.hitVec);

                    if (d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        if (entity != null)
        {
            raytraceresult = new RayTraceResult(entity);
        }
        if (raytraceresult != null && raytraceresult.typeOfHit != RayTraceResult.Type.MISS)
        {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.ENTITY)
            {
                this.caughtEntity = raytraceresult.entityHit;
                this.setHookedEntity();
            }
            else
            {
                this.inGround = true;
            }
        }
    }

    private void setHookedEntity()
    {
        this.getDataManager().set(DATA_HOOKED_ENTITY, this.caughtEntity.getEntityId() + 1);
    }

    private void catchingFish(BlockPos pos)
    {
        WorldServer worldserver = (WorldServer)this.world;
        int i = 1;
        BlockPos blockpos = pos.up();

        if (this.rand.nextFloat() < 0.25F && this.world.isRainingAt(blockpos))
        {
            ++i;
        }
        if (this.rand.nextFloat() < 0.5F && !this.world.canSeeSky(blockpos))
        {
            --i;
        }

        if (this.ticksCatchable > 0)
        {
            --this.ticksCatchable;

            if (this.ticksCatchable <= 0)
            {
                this.ticksCaughtDelay = 0;
                this.ticksCatchableDelay = 0;
            }
            else
            {
                this.motionY -= 0.2D * this.rand.nextFloat() * this.rand.nextFloat();
            }
        }
        else if (this.ticksCatchableDelay > 0)
        {
            this.ticksCatchableDelay -= i;

            if (this.ticksCatchableDelay > 0)
            {
                this.fishApproachAngle = (float)(this.fishApproachAngle + this.rand.nextGaussian() * 4.0D);
                float f = this.fishApproachAngle * 0.017453292F;
                float f1 = MathHelper.sin(f);
                float f2 = MathHelper.cos(f);
                double d0 = this.posX + f1 * this.ticksCatchableDelay * 0.1F;
                double d1 = MathHelper.floor(this.getEntityBoundingBox().minY) + 1.0F;
                double d2 = this.posZ + f2 * this.ticksCatchableDelay * 0.1F;
                Block block = worldserver.getBlockState(new BlockPos(d0, d1 - 1.0D, d2)).getBlock();

                if (block == Blocks.WATER || block == Blocks.FLOWING_WATER || block instanceof IFishableLiquidBlock)
                {
                    if (this.rand.nextFloat() < 0.15F)
                    {
                        worldserver.spawnParticle(EnumParticleTypes.WATER_BUBBLE, d0, d1 - 0.10000000149011612D, d2, 1, f1, 0.1D, f2, 0.0D, new int[0]);
                    }
                    float f3 = f1 * 0.04F;
                    float f4 = f2 * 0.04F;
                    worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, d0, d1, d2, 0, f4, 0.01D, -f3, 1.0D, new int[0]);
                    worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, d0, d1, d2, 0, -f4, 0.01D, f3, 1.0D, new int[0]);
                }
            }
            else
            {
                this.motionY = -0.4F * MathHelper.nextFloat(this.rand, 0.6F, 1.0F);
                this.playSound(SoundEvents.ENTITY_BOBBER_SPLASH, 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                double d3 = this.getEntityBoundingBox().minY + 0.5D;
                worldserver.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, d3, this.posZ, (int)(1.0F + this.width * 20.0F), this.width, 0.0D, this.width, 0.20000000298023224D, new int[0]);
                worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, this.posX, d3, this.posZ, (int)(1.0F + this.width * 20.0F), this.width, 0.0D, this.width, 0.20000000298023224D, new int[0]);
                this.ticksCatchable = MathHelper.getInt(this.rand, 20, 40);
            }
        }
        else if (this.ticksCaughtDelay > 0)
        {
            this.ticksCaughtDelay -= i;
            float f5 = 0.15F;

            if (this.ticksCaughtDelay < 20)
            {
                f5 = (float)(f5 + (20 - this.ticksCaughtDelay) * 0.05D);
            }
            else if (this.ticksCaughtDelay < 40)
            {
                f5 = (float)(f5 + (40 - this.ticksCaughtDelay) * 0.02D);
            }
            else if (this.ticksCaughtDelay < 60)
            {
                f5 = (float)(f5 + (60 - this.ticksCaughtDelay) * 0.01D);
            }

            if (this.rand.nextFloat() < f5)
            {
                float f6 = MathHelper.nextFloat(this.rand, 0.0F, 360.0F) * 0.017453292F;
                float f7 = MathHelper.nextFloat(this.rand, 25.0F, 60.0F);
                double d4 = this.posX + MathHelper.sin(f6) * f7 * 0.1F;
                double d5 = MathHelper.floor(this.getEntityBoundingBox().minY) + 1.0F;
                double d6 = this.posZ + MathHelper.cos(f6) * f7 * 0.1F;
                Block block1 = worldserver.getBlockState(new BlockPos((int)d4, (int)d5 - 1, (int)d6)).getBlock();

                if (block1 == Blocks.WATER || block1 == Blocks.FLOWING_WATER || block1 instanceof IFishableLiquidBlock)
                {
                    worldserver.spawnParticle(EnumParticleTypes.WATER_SPLASH, d4, d5, d6, 2 + this.rand.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D, new int[0]);
                }
            }
            if (this.ticksCaughtDelay <= 0)
            {
                this.fishApproachAngle = MathHelper.nextFloat(this.rand, 0.0F, 360.0F);
                this.ticksCatchableDelay = MathHelper.getInt(this.rand, 20, 80);
            }
        }
        else
        {
            this.ticksCaughtDelay = MathHelper.getInt(this.rand, 100, 600);
            this.ticksCaughtDelay -= this.lureSpeed * 20 * 5;
        }
    }

    @Override
    public int handleHookRetraction()
    {
        if (!this.world.isRemote && this.angler != null)
        {
            int i = 0;

            if (this.caughtEntity != null)
            {
                this.bringInHookedEntity();
                this.world.setEntityState(this, (byte)31);
                i = this.caughtEntity instanceof EntityItem ? 3 : 5;
            }
            else if (this.ticksCatchable > 0)
            {
                LootContext.Builder lootBuilder = new LootContext.Builder((WorldServer)this.world);
                lootBuilder.withLuck(this.luck + this.angler.getLuck());
                double x = MathHelper.floor(this.posX);
                double y = MathHelper.floor(this.getEntityBoundingBox().minY) + 1.0F;
                double z = MathHelper.floor(this.posZ);
                Block block = this.world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();
                ResourceLocation resource = block instanceof IFishableLiquidBlock ? ((IFishableLiquidBlock)block).getLootTable() : this.world.provider instanceof IGalacticraftWorldProvider ? MPLootTables.SPACE_FISHING : LootTableList.GAMEPLAY_FISHING;

                for (ItemStack itemStack : this.world.getLootTableManager().getLootTableFromLocation(resource).generateLootForPools(this.rand, lootBuilder.build()))
                {
                    EntityItem entityItem = new EntityItem(this.world, this.posX, this.posY, this.posZ, itemStack);
                    double d0 = this.angler.posX - this.posX;
                    double d1 = this.angler.posY - this.posY;
                    double d2 = this.angler.posZ - this.posZ;
                    double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    entityItem.motionX = d0 * 0.1D;
                    entityItem.motionY = d1 * 0.1D + MathHelper.sqrt(d3) * 0.08D;
                    entityItem.motionZ = d2 * 0.1D;
                    this.world.spawnEntity(entityItem);
                    this.angler.world.spawnEntity(new EntityXPOrb(this.angler.world, this.angler.posX, this.angler.posY + 0.5D, this.angler.posZ + 0.5D, this.rand.nextInt(6) + 1));
                    Item item = itemStack.getItem();

                    if (item == Items.FISH || item == Items.COOKED_FISH)
                    {
                        this.angler.addStat(StatList.FISH_CAUGHT, 1);
                    }
                }
                i = 1;
            }
            if (this.inGround)
            {
                i = 2;
            }
            this.setDead();
            return i;
        }
        else
        {
            return 0;
        }
    }

    @Override
    protected void bringInHookedEntity()
    {
        if (this.angler != null)
        {
            double d0 = this.angler.posX - this.posX;
            double d1 = this.angler.posY - this.posY;
            double d2 = this.angler.posZ - this.posZ;
            this.caughtEntity.motionX += d0 * 0.1D;
            this.caughtEntity.motionY += d1 * 0.1D;
            this.caughtEntity.motionZ += d2 * 0.1D;
        }
    }

    @Override
    public void setDead()
    {
        this.isDead = true;

        if (this.angler != null)
        {
            this.angler.fishEntity = null;
        }
    }

    @Override
    public EntityPlayer getAngler()
    {
        return this.angler;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeInt(this.angler != null ? this.angler.getEntityId() : 0);
    }

    @Override
    public void readSpawnData(ByteBuf buffer)
    {
        Entity angler = this.world.getEntityByID(buffer.readInt());

        if (angler instanceof EntityPlayer)
        {
            this.angler = (EntityPlayer) angler;
        }
    }

    @Override
    public Entity getThrower()
    {
        return this.angler;
    }

    @Override
    public void setThrower(Entity entity)
    {
        if (entity instanceof EntityPlayer)
        {
            this.angler = (EntityPlayer) entity;
        }
    }

    static enum State
    {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING;
    }
}