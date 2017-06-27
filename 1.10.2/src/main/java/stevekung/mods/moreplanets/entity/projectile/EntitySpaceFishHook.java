package stevekung.mods.moreplanets.entity.projectile;

import java.util.List;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.TransformerHooks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.util.blocks.IFishableLiquidBlock;

public class EntitySpaceFishHook extends EntityFishHook implements IEntityAdditionalSpawnData
{
    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private boolean inGround;
    private int ticksInGround;
    private int ticksInAir;
    private int ticksCatchable;
    private int ticksCaughtDelay;
    private int ticksCatchableDelay;
    private float fishApproachAngle;
    private int fishPosRotationIncrements;
    private double fishX;
    private double fishY;
    private double fishZ;
    private double fishYaw;
    private double fishPitch;
    @SideOnly(Side.CLIENT)
    private double clientMotionX;
    @SideOnly(Side.CLIENT)
    private double clientMotionY;
    @SideOnly(Side.CLIENT)
    private double clientMotionZ;

    public EntitySpaceFishHook(World world)
    {
        super(world);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.setSize(0.25F, 0.25F);
        this.ignoreFrustumCheck = true;
    }

    @SideOnly(Side.CLIENT)
    public EntitySpaceFishHook(World world, double x, double y, double z, EntityPlayer angler)
    {
        super(world, x, y, z, angler);
        this.setPosition(x, y, z);
        this.ignoreFrustumCheck = true;
        this.angler = angler;
        angler.fishEntity = this;
    }

    public EntitySpaceFishHook(World world, EntityPlayer fishingPlayer)
    {
        super(world, fishingPlayer);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.ignoreFrustumCheck = true;
        this.angler = fishingPlayer;
        this.angler.fishEntity = this;
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(fishingPlayer.posX, fishingPlayer.posY + fishingPlayer.getEyeHeight(), fishingPlayer.posZ, fishingPlayer.rotationYaw, fishingPlayer.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        this.posY -= 0.10000000149011612D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        float f = 0.4F;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f;
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f;
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI) * f;
        this.handleHookCasting(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block) {}

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;

        if (Double.isNaN(d0))
        {
            d0 = 4.0D;
        }
        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    @Override
    public void handleHookCasting(double motionX, double motionY, double motionZ, float p_146035_7_, float p_146035_8_)
    {
        float f = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
        motionX = motionX / f;
        motionY = motionY / f;
        motionZ = motionZ / f;
        motionX = motionX + this.rand.nextGaussian() * 0.007499999832361937D * p_146035_8_;
        motionY = motionY + this.rand.nextGaussian() * 0.007499999832361937D * p_146035_8_;
        motionZ = motionZ + this.rand.nextGaussian() * 0.007499999832361937D * p_146035_8_;
        motionX = motionX * p_146035_7_;
        motionY = motionY * p_146035_7_;
        motionZ = motionZ * p_146035_7_;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        float f1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(motionX, motionZ) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(motionY, f1) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
    {
        this.fishX = x;
        this.fishY = y;
        this.fishZ = z;
        this.fishYaw = yaw;
        this.fishPitch = pitch;
        this.fishPosRotationIncrements = posRotationIncrements;
        this.motionX = this.clientMotionX;
        this.motionY = this.clientMotionY;
        this.motionZ = this.clientMotionZ;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z)
    {
        this.clientMotionX = this.motionX = x;
        this.clientMotionY = this.motionY = y;
        this.clientMotionZ = this.motionZ = z;
    }

    @Override
    public void onUpdate()
    {
        this.onEntityUpdate();

        if (this.fishPosRotationIncrements > 0)
        {
            double d7 = this.posX + (this.fishX - this.posX) / this.fishPosRotationIncrements;
            double d8 = this.posY + (this.fishY - this.posY) / this.fishPosRotationIncrements;
            double d9 = this.posZ + (this.fishZ - this.posZ) / this.fishPosRotationIncrements;
            double d1 = MathHelper.wrapDegrees(this.fishYaw - this.rotationYaw);
            this.rotationYaw = (float)(this.rotationYaw + d1 / this.fishPosRotationIncrements);
            this.rotationPitch = (float)(this.rotationPitch + (this.fishPitch - this.rotationPitch) / this.fishPosRotationIncrements);
            --this.fishPosRotationIncrements;
            this.setPosition(d7, d8, d9);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                if (this.angler != null)
                {
                    ItemStack itemStack = this.angler.getHeldItemMainhand();

                    if (this.angler == null || this.angler.isDead || !this.angler.isEntityAlive() || itemStack == null || itemStack.getItem() == null || itemStack.getItem() != MPItems.SPACE_FISHING_ROD || !itemStack.hasTagCompound() || itemStack.hasTagCompound() && !itemStack.getTagCompound().getBoolean("Cast") || this.getDistanceSqToEntity(this.angler) > 1024.0D)
                    {
                        this.setDead();
                        this.angler.fishEntity = null;
                        return;
                    }
                }
                else
                {
                    this.setDead();
                    return;
                }

                if (this.caughtEntity != null)
                {
                    if (!this.caughtEntity.isDead)
                    {
                        this.posX = this.caughtEntity.posX;
                        double d17 = this.caughtEntity.height;
                        this.posY = this.caughtEntity.getEntityBoundingBox().minY + d17 * 0.8D;
                        this.posZ = this.caughtEntity.posZ;
                        return;
                    }
                    this.caughtEntity = null;
                }
            }

            if (this.inGround)
            {
                if (this.worldObj.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile)
                {
                    ++this.ticksInGround;

                    if (this.ticksInGround == 1200)
                    {
                        this.setDead();
                    }
                    return;
                }

                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2F;
                this.motionY *= this.rand.nextFloat() * 0.2F;
                this.motionZ *= this.rand.nextFloat() * 0.2F;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
            else
            {
                ++this.ticksInAir;
            }

            Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3);
            vec31 = new Vec3d(this.posX, this.posY, this.posZ);
            vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null)
            {
                vec3 = new Vec3d(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            Entity entity = null;
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            for (int i = 0; i < list.size(); ++i)
            {
                Entity entity1 = list.get(i);

                if (entity1.canBeCollidedWith() && (entity1 != this.angler || this.ticksInAir >= 5))
                {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f, f, f);
                    RayTraceResult movingobjectposition1 = axisalignedbb.calculateIntercept(vec31, vec3);

                    if (movingobjectposition1 != null)
                    {
                        double d2 = vec31.squareDistanceTo(movingobjectposition1.hitVec);

                        if (d2 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d2;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new RayTraceResult(entity);
            }

            if (movingobjectposition != null)
            {
                if (movingobjectposition.entityHit != null)
                {
                    if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.angler), 0.0F))
                    {
                        this.caughtEntity = movingobjectposition.entityHit;
                    }
                }
                else
                {
                    this.inGround = true;
                }
            }

            if (!this.inGround)
            {
                this.moveEntity(this.motionX, this.motionY, this.motionZ);
                float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
                this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

                for (this.rotationPitch = (float)(MathHelper.atan2(this.motionY, f5) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {}

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
                float f6 = 0.92F;

                if (this.onGround || this.isCollidedHorizontally)
                {
                    f6 = 0.5F;
                }

                int j = 5;
                double d10 = 0.0D;

                for (int k = 0; k < j; ++k)
                {
                    AxisAlignedBB axisalignedbb1 = this.getEntityBoundingBox();
                    double d3 = axisalignedbb1.maxY - axisalignedbb1.minY;
                    double d4 = axisalignedbb1.minY + d3 * k / j;
                    double d5 = axisalignedbb1.minY + d3 * (k + 1) / j;
                    AxisAlignedBB axisalignedbb2 = new AxisAlignedBB(axisalignedbb1.minX, d4, axisalignedbb1.minZ, axisalignedbb1.maxX, d5, axisalignedbb1.maxZ);

                    if (this.worldObj.isAABBInMaterial(axisalignedbb2, Material.WATER))
                    {
                        d10 += 1.0D / j;
                    }
                }

                if (!this.worldObj.isRemote && d10 > 0.0D)
                {
                    WorldServer worldserver = (WorldServer)this.worldObj;
                    int l = 1;
                    BlockPos blockpos = new BlockPos(this).up();

                    if (this.rand.nextFloat() < 0.25F && this.worldObj.isRainingAt(blockpos))
                    {
                        l = 2;
                    }
                    if (this.rand.nextFloat() < 0.5F && !this.worldObj.canSeeSky(blockpos))
                    {
                        --l;
                    }

                    if (this.ticksCatchable > 0)
                    {
                        --this.ticksCatchable;

                        if (this.ticksCatchable <= 0)
                        {
                            this.ticksCaughtDelay = 0;
                            this.ticksCatchableDelay = 0;
                        }
                    }
                    else if (this.ticksCatchableDelay > 0)
                    {
                        this.ticksCatchableDelay -= l;

                        if (this.ticksCatchableDelay <= 0)
                        {
                            this.motionY -= 0.20000000298023224D;
                            this.playSound(SoundEvents.ENTITY_BOBBER_SPLASH, 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                            float f8 = MathHelper.floor_double(this.getEntityBoundingBox().minY);
                            worldserver.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, f8 + 1.0F, this.posZ, (int)(1.0F + this.width * 20.0F), this.width, 0.0D, this.width, 0.20000000298023224D, new int[0]);
                            worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, this.posX, f8 + 1.0F, this.posZ, (int)(1.0F + this.width * 20.0F), this.width, 0.0D, this.width, 0.20000000298023224D, new int[0]);
                            this.ticksCatchable = MathHelper.getRandomIntegerInRange(this.rand, 10, 30);
                        }
                        else
                        {
                            this.fishApproachAngle = (float)(this.fishApproachAngle + this.rand.nextGaussian() * 4.0D);
                            float f7 = this.fishApproachAngle * 0.017453292F;
                            float f10 = MathHelper.sin(f7);
                            float f11 = MathHelper.cos(f7);
                            double d13 = this.posX + f10 * this.ticksCatchableDelay * 0.1F;
                            double d15 = MathHelper.floor_double(this.getEntityBoundingBox().minY) + 1.0F;
                            double d16 = this.posZ + f11 * this.ticksCatchableDelay * 0.1F;
                            Block block1 = worldserver.getBlockState(new BlockPos((int)d13, (int)d15 - 1, (int)d16)).getBlock();

                            if (block1 == Blocks.WATER || block1 == Blocks.FLOWING_WATER || block1 instanceof IFishableLiquidBlock)
                            {
                                if (this.rand.nextFloat() < 0.15F)
                                {
                                    worldserver.spawnParticle(EnumParticleTypes.WATER_BUBBLE, d13, d15 - 0.10000000149011612D, d16, 1, f10, 0.1D, f11, 0.0D, new int[0]);
                                }
                                float f3 = f10 * 0.04F;
                                float f4 = f11 * 0.04F;
                                worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, d13, d15, d16, 0, f4, 0.01D, -f3, 1.0D, new int[0]);
                                worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, d13, d15, d16, 0, -f4, 0.01D, f3, 1.0D, new int[0]);
                            }
                        }
                    }
                    else if (this.ticksCaughtDelay > 0)
                    {
                        this.ticksCaughtDelay -= l;
                        float f1 = 0.15F;

                        if (this.ticksCaughtDelay < 20)
                        {
                            f1 = (float)(f1 + (20 - this.ticksCaughtDelay) * 0.05D);
                        }
                        else if (this.ticksCaughtDelay < 40)
                        {
                            f1 = (float)(f1 + (40 - this.ticksCaughtDelay) * 0.02D);
                        }
                        else if (this.ticksCaughtDelay < 60)
                        {
                            f1 = (float)(f1 + (60 - this.ticksCaughtDelay) * 0.01D);
                        }

                        if (this.rand.nextFloat() < f1)
                        {
                            float f9 = MathHelper.randomFloatClamp(this.rand, 0.0F, 360.0F) * 0.017453292F;
                            float f2 = MathHelper.randomFloatClamp(this.rand, 25.0F, 60.0F);
                            double d12 = this.posX + MathHelper.sin(f9) * f2 * 0.1F;
                            double d14 = MathHelper.floor_double(this.getEntityBoundingBox().minY) + 1.0F;
                            double d6 = this.posZ + MathHelper.cos(f9) * f2 * 0.1F;
                            Block block = worldserver.getBlockState(new BlockPos((int)d12, (int)d14 - 1, (int)d6)).getBlock();

                            if (block == Blocks.WATER || block == Blocks.FLOWING_WATER || block instanceof IFishableLiquidBlock)
                            {
                                worldserver.spawnParticle(EnumParticleTypes.WATER_SPLASH, d12, d14, d6, 2 + this.rand.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D, new int[0]);
                            }
                        }

                        if (this.ticksCaughtDelay <= 0)
                        {
                            this.fishApproachAngle = MathHelper.randomFloatClamp(this.rand, 0.0F, 360.0F);
                            this.ticksCatchableDelay = MathHelper.getRandomIntegerInRange(this.rand, 20, 80);
                        }
                    }
                    else
                    {
                        this.ticksCaughtDelay = MathHelper.getRandomIntegerInRange(this.rand, 100, 900);
                        this.ticksCaughtDelay -= EnchantmentHelper.getLureModifier(this.angler) * 20 * 5;
                    }

                    if (this.ticksCatchable > 0)
                    {
                        this.motionY -= this.rand.nextFloat() * this.rand.nextFloat() * this.rand.nextFloat() * 0.2D;
                    }
                }

                double d11 = d10 * 2.0D - 1.0D;
                this.motionY += 0.03999999910593033D * d11;

                if (d10 > 0.0D)
                {
                    f6 = (float)(f6 * 0.9D);
                    this.motionY *= 0.8D;
                }
                this.motionX *= f6;
                this.motionY *= f6;
                this.motionZ *= f6;
                this.motionY += this.worldObj.provider instanceof IGalacticraftWorldProvider ? TransformerHooks.getGravityForEntity(this) : 0.0D;
                this.setPosition(this.posX, this.posY, this.posZ);
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setShort("xTile", (short)this.xTile);
        tagCompound.setShort("yTile", (short)this.yTile);
        tagCompound.setShort("zTile", (short)this.zTile);
        ResourceLocation resourcelocation = Block.REGISTRY.getNameForObject(this.inTile);
        tagCompound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
        tagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        this.xTile = tagCompund.getShort("xTile");
        this.yTile = tagCompund.getShort("yTile");
        this.zTile = tagCompund.getShort("zTile");

        if (tagCompund.hasKey("inTile", 8))
        {
            this.inTile = Block.getBlockFromName(tagCompund.getString("inTile"));
        }
        else
        {
            this.inTile = Block.getBlockById(tagCompund.getByte("inTile") & 255);
        }
        this.inGround = tagCompund.getByte("inGround") == 1;
    }

    @Override
    public int handleHookRetraction()
    {
        if (this.worldObj.isRemote)
        {
            return 0;
        }
        else
        {
            int i = 0;

            if (this.caughtEntity != null)
            {
                double d0 = this.angler.posX - this.posX;
                double d2 = this.angler.posY - this.posY;
                double d4 = this.angler.posZ - this.posZ;
                double d6 = MathHelper.sqrt_double(d0 * d0 + d2 * d2 + d4 * d4);
                double d8 = 0.1D;
                this.caughtEntity.motionX += d0 * d8;
                this.caughtEntity.motionY += d2 * d8 + MathHelper.sqrt_double(d6) * 0.08D;
                this.caughtEntity.motionZ += d4 * d8;
                i = 3;
            }
            else if (this.ticksCatchable > 0)
            {
                float f9 = MathHelper.randomFloatClamp(this.rand, 0.0F, 360.0F) * 0.017453292F;
                float f2 = MathHelper.randomFloatClamp(this.rand, 25.0F, 60.0F);
                double x = this.posX + MathHelper.sin(f9) * f2 * 0.1F;
                double y = MathHelper.floor_double(this.getEntityBoundingBox().minY) + 1.0F;
                double z = this.posZ + MathHelper.cos(f9) * f2 * 0.1F;
                Block block = ((WorldServer)this.worldObj).getBlockState(new BlockPos((int)x, (int)y - 1, (int)z)).getBlock();
                LootContext.Builder context = new LootContext.Builder((WorldServer)this.worldObj);
                context.withLuck(EnchantmentHelper.getLuckOfSeaModifier(this.angler) + this.angler.getLuck());
                ItemStack lootStack = null;

                if (block instanceof IFishableLiquidBlock)
                {
                    IFishableLiquidBlock liquid = (IFishableLiquidBlock) block;

                    for (ItemStack itemStack : this.worldObj.getLootTableManager().getLootTableFromLocation(liquid.getLootTable()).generateLootForPools(this.rand, context.build()))//TODO Custom Fishing Loot Table
                    {
                        lootStack = itemStack;
                    }
                }
                if (this.worldObj.provider instanceof IGalacticraftWorldProvider)
                {
                    for (ItemStack itemStack : this.worldObj.getLootTableManager().getLootTableFromLocation(MPLootTables.SPACE_FISHING).generateLootForPools(this.rand, context.build()))
                    {
                        lootStack = itemStack;
                    }
                }
                else
                {
                    for (ItemStack itemStack : this.worldObj.getLootTableManager().getLootTableFromLocation(LootTableList.GAMEPLAY_FISHING).generateLootForPools(this.rand, context.build()))
                    {
                        lootStack = itemStack;
                    }
                }

                EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, lootStack);
                double d0 = this.angler.posX - this.posX;
                double d1 = this.angler.posY - this.posY;
                double d2 = this.angler.posZ - this.posZ;
                double d3 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                entityitem.motionX = d0 * 0.1D;
                entityitem.motionY = d1 * 0.1D + MathHelper.sqrt_double(d3) * 0.08D;
                entityitem.motionZ = d2 * 0.1D;
                this.worldObj.spawnEntityInWorld(entityitem);
                this.angler.worldObj.spawnEntityInWorld(new EntityXPOrb(this.angler.worldObj, this.angler.posX, this.angler.posY + 0.5D, this.angler.posZ + 0.5D, this.rand.nextInt(6) + 1));
                i = 1;
            }
            if (this.inGround)
            {
                i = 2;
            }
            this.setDead();
            this.angler.fishEntity = null;
            return i;
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
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeInt(this.angler != null ? this.angler.getEntityId() : 0);
    }

    @Override
    public void readSpawnData(ByteBuf buffer)
    {
        Entity angler = this.worldObj.getEntityByID(buffer.readInt());

        if (angler instanceof EntityPlayer)
        {
            this.angler = (EntityPlayer) angler;
        }
    }
}