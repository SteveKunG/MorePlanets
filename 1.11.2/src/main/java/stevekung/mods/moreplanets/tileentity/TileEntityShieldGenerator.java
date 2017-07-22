package stevekung.mods.moreplanets.tileentity;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3Dim;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.entities.IBubbleProvider;
import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.blocks.BlockDummy;
import stevekung.mods.moreplanets.blocks.BlockShieldGenerator;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class TileEntityShieldGenerator extends TileEntityDummy implements IMultiBlock, IBubbleProvider, IInventoryDefaults, ISidedInventory
{
    @NetworkedField(targetSide = Side.CLIENT)
    public int facing;
    public int renderTicks;
    public int solarRotate;
    private NonNullList<ItemStack> containingItems = NonNullList.withSize(1, ItemStack.EMPTY);
    @NetworkedField(targetSide = Side.CLIENT)
    public float shieldSize;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean shouldRender = true;
    @NetworkedField(targetSide = Side.CLIENT)
    public int maxShieldSize = 20;
    @NetworkedField(targetSide = Side.CLIENT)
    public int knockAmount = 5;
    @NetworkedField(targetSide = Side.CLIENT)
    public int shieldDamage = 10;
    @NetworkedField(targetSide = Side.CLIENT)
    public String ownerUUID = "";
    private static HashSet<BlockVec3Dim> loadedTiles = new HashSet();
    private boolean initialize = true;
    //private static BlockPos shieldPos;

    public TileEntityShieldGenerator()
    {
        this.storage.setMaxExtract(250);
        this.storage.setCapacity(100000.0F);
    }

    @Override
    public void onLoad()
    {
        if (!this.world.isRemote)
        {
            TileEntityShieldGenerator.loadedTiles.add(new BlockVec3Dim(this));
        }
    }

    @Override
    public void onChunkUnload()
    {
        TileEntityShieldGenerator.loadedTiles.remove(new BlockVec3Dim(this));
        super.onChunkUnload();
    }

    @Override
    public void invalidate()
    {
        if (!this.world.isRemote)
        {
            TileEntityShieldGenerator.loadedTiles.remove(new BlockVec3Dim(this));
        }
        super.invalidate();
    }

    @Override
    public void addExtraNetworkedData(List<Object> networkedList)
    {
        if (!this.world.isRemote && !this.isInvalid())
        {
            if (this.world.getMinecraftServer().isDedicatedServer())
            {
                networkedList.add(TileEntityShieldGenerator.loadedTiles.size());

                for (BlockVec3Dim tile : TileEntityShieldGenerator.loadedTiles)
                {
                    if (tile == null)
                    {
                        networkedList.add(-1);
                        networkedList.add(-1);
                        networkedList.add(-1);
                        networkedList.add(-1);
                    }
                    else
                    {
                        networkedList.add(tile.x);
                        networkedList.add(tile.y);
                        networkedList.add(tile.z);
                        networkedList.add(tile.dim);
                    }
                }
            }
            else
            {
                networkedList.add(-1);
            }
        }
    }

    @Override
    public void readExtraNetworkedData(ByteBuf dataStream)
    {
        if (this.world.isRemote)
        {
            int size = dataStream.readInt();

            if (size >= 0)
            {
                TileEntityShieldGenerator.loadedTiles.clear();

                for (int i = 0; i < size; ++i)
                {
                    int i1 = dataStream.readInt();
                    int i2 = dataStream.readInt();
                    int i3 = dataStream.readInt();
                    int i4 = dataStream.readInt();

                    if (i1 == -1 && i2 == -1 && i3 == -1 && i4 == -1)
                    {
                        continue;
                    }
                    TileEntityShieldGenerator.loadedTiles.add(new BlockVec3Dim(i1, i2, i3, i4));
                }
            }
        }
    }

    @Override
    public void update()
    {
        super.update();
        this.renderTicks++;

        if (this.initialize)
        {
            this.renderTicks = this.renderTicks + this.world.rand.nextInt(100);
            this.solarRotate = this.solarRotate + this.world.rand.nextInt(360);
            this.initialize = false;
        }
        if (this.hasEnoughEnergyToRun && !this.disabled)
        {
            this.solarRotate++;
            this.solarRotate %= 360;
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.ALIEN_MINER_SPARK, this.pos.getX() + 0.5D, this.pos.getY() + 1.75D, this.pos.getZ() + 0.5D, new Object[] { -0.5F });

            if (this.ticks % 33 == 0)
            {
                this.world.playSound(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), MPSounds.MACHINE_GENERATOR_AMBIENT, SoundCategory.BLOCKS, 0.075F, 1.0F);
            }
        }
        if (!this.world.isRemote)
        {
            if (this.getEnergyStoredGC() > 0.0F && this.hasEnoughEnergyToRun)
            {
                this.shieldSize += 0.05F;
            }
            else
            {
                this.shieldSize -= 1.0F;
            }
            this.shieldSize = Math.min(Math.max(this.shieldSize, 0.0F), this.maxShieldSize);
            //TileEntityShieldGenerator.shieldPos = this.pos;
        }

        if (this.knockAmount > 10)
        {
            this.knockAmount = 10;
        }
        if (this.knockAmount < 0)
        {
            this.knockAmount = 0;
        }

        float range = this.shieldSize;
        List<Entity> entitiesMob = this.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(this.pos.getX() - range, this.pos.getY() - range, this.pos.getZ() - range, this.pos.getX() + range, this.pos.getY() + range, this.pos.getZ() + range));

        for (Entity entity : entitiesMob)
        {
            if (!this.disabled)
            {
                if (entity instanceof IMob || entity instanceof EntityArrow && !(((EntityArrow)entity).shootingEntity instanceof EntityPlayer) || entity instanceof EntityPotion && !(((EntityPotion)entity).getThrower() instanceof EntityPlayer) || entity instanceof EntityFireball)
                {
                    double d4 = entity.getDistance(this.pos.getX(), this.pos.getY(), this.pos.getZ());
                    double d6 = entity.posX - this.pos.getX();
                    double d8 = entity.posY - this.pos.getY();
                    double d10 = entity.posZ - this.pos.getZ();
                    double d11 = MathHelper.sqrt(d6 * d6 + d8 * d8 + d10 * d10);
                    d6 /= d11;
                    d8 /= d11;
                    d10 /= d11;
                    double d13 = (0.0D - d4) * this.knockAmount / 10.0D;
                    double d14 = d13;
                    double knockSpeed = 10.0D;
                    entity.motionX -= d6 * d14 / knockSpeed;
                    entity.motionY -= d8 * d14 / knockSpeed;
                    entity.motionZ -= d10 * d14 / knockSpeed;

                    if (this.world.getPlayerEntityByUUID(UUID.fromString(this.ownerUUID)) != null)
                    {
                        entity.attackEntityFrom(DamageSource.causePlayerDamage(this.world.getPlayerEntityByUUID(UUID.fromString(this.ownerUUID))), this.shieldDamage);
                    }
                    else
                    {
                        entity.attackEntityFrom(DamageSource.GENERIC, this.shieldDamage);
                    }
                }
            }
        }
    }

    @Override
    public boolean onActivated(EntityPlayer player)
    {
        return ((BlockShieldGenerator) MPBlocks.SHIELD_GENERATOR).onMachineActivated(this.world, this.mainBlockPosition, MPBlocks.SHIELD_GENERATOR.getDefaultState(), player, player.getActiveHand(), player.getHeldItemMainhand(), player.getHorizontalFacing(), 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void onCreate(World world, BlockPos pos)
    {
        this.mainBlockPosition = pos;
    }

    @Override
    public void onDestroy(TileEntity tile)
    {
        BlockPos thisBlock = this.getPos();

        if (this.world.isRemote && this.world.rand.nextDouble() < 0.1D)
        {
            FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.up(), MPBlocks.DUMMY_BLOCK.getDefaultState().withProperty(BlockDummy.VARIANT, BlockDummy.BlockType.SHIELD_GENERATOR_TOP));
        }
        this.world.destroyBlock(this.getPos(), true);
        this.world.destroyBlock(thisBlock.up(), false);
    }

    @Override
    public void setDisabled(int index, boolean disabled)
    {
        if (this.disableCooldown == 0)
        {
            this.disabled = disabled;
            this.disableCooldown = 0;
        }
    }

    @Override
    public boolean getDisabled(int index)
    {
        return this.disabled;
    }

    @Override
    public double getPacketRange()
    {
        return 64.0D;
    }

    @Override
    public int getPacketCooldown()
    {
        return 1;
    }

    @Override
    public boolean isNetworkedTile()
    {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        if (nbt.hasKey("ShieldVisible"))
        {
            this.setBubbleVisible(nbt.getBoolean("ShieldVisible"));
        }
        if (nbt.hasKey("ShieldSize"))
        {
            this.shieldSize = nbt.getFloat("ShieldSize");
        }
        if (nbt.hasKey("MaxShieldSize"))
        {
            this.maxShieldSize = nbt.getInteger("MaxShieldSize");
        }
        if (nbt.hasKey("KnockAmount"))
        {
            this.knockAmount = nbt.getInteger("KnockAmount");
        }
        if (nbt.hasKey("ShieldDamage"))
        {
            this.shieldDamage = nbt.getInteger("ShieldDamage");
        }
        this.facing = nbt.getInteger("Facing");
        this.ownerUUID = nbt.getString("OwnerUUID");
        this.containingItems = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.containingItems);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setBoolean("ShieldVisible", this.shouldRender);
        nbt.setFloat("ShieldSize", this.shieldSize);
        nbt.setInteger("MaxShieldSize", this.maxShieldSize);
        nbt.setInteger("KnockAmount", this.knockAmount);
        nbt.setInteger("ShieldDamage", this.shieldDamage);
        nbt.setInteger("Facing", this.facing);
        ItemStackHelper.saveAllItems(nbt, this.containingItems);

        if (this.ownerUUID != null)
        {
            nbt.setString("OwnerUUID", this.ownerUUID);
        }
        return nbt;
    }

    @Override
    public int getSizeInventory()
    {
        return this.containingItems.size();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.getItems().get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemStack = ItemStackHelper.getAndSplit(this.getItems(), index, count);

        if (!itemStack.isEmpty())
        {
            this.markDirty();
        }
        return itemStack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.getItems(), index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack)
    {
        this.getItems().set(index, itemStack);

        if (itemStack.getCount() > this.getInventoryStackLimit())
        {
            itemStack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemStack : this.containingItems)
        {
            if (!itemStack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    protected NonNullList<ItemStack> getItems()
    {
        return this.containingItems;
    }

    @Override
    public String getName()
    {
        return GCCoreUtil.translate("container.shield_generator.name");
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(this.getName());
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[] { 0 };
    }

    @Override
    public boolean canInsertItem(int slotID, ItemStack itemStack, EnumFacing side)
    {
        return this.isItemValidForSlot(slotID, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotID, ItemStack itemStack, EnumFacing side)
    {
        return slotID == 0;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return slot == 0 && ItemElectricBase.isElectricItem(itemStack.getItem());
    }

    @Override
    public boolean shouldUseEnergy()
    {
        return !this.disabled;
    }

    @Override
    public EnumFacing getFront()
    {
        IBlockState state = this.world.getBlockState(this.getPos());

        if (state.getBlock() instanceof BlockShieldGenerator)
        {
            return state.getValue(BlockStateHelper.FACING_HORIZON);
        }
        return EnumFacing.NORTH;
    }

    @Override
    public EnumFacing getElectricInputDirection()
    {
        return this.getFront();
    }

    @Override
    public ItemStack getBatteryInSlot()
    {
        return this.getStackInSlot(0);
    }

    @Override
    public void setBubbleVisible(boolean render)
    {
        this.shouldRender = render;
    }

    @Override
    public float getBubbleSize()
    {
        return this.shieldSize;
    }

    @Override
    public boolean getBubbleVisible()
    {
        return this.shouldRender;
    }

    @Override
    public void getPositions(BlockPos placedPosition, List<BlockPos> positions) {}

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared()
    {
        return Constants.RENDERDISTANCE_LONG;
    }

    @Override
    public EnumBlockMultiType getMultiType()
    {
        return null;
    }

    public void setFacing(int facing)
    {
        this.facing = facing;
    }

    /*public static boolean isInShield(EntityLivingBase entity)TODO Circle bounding box
    {
        double x = entity.posX;
        double y = entity.posY + entity.getEyeHeight();
        double z = entity.posZ;
        double sx = entity.getEntityBoundingBox().maxX - entity.getEntityBoundingBox().minX;
        double sy = entity.getEntityBoundingBox().maxY - entity.getEntityBoundingBox().minY;
        double sz = entity.getEntityBoundingBox().maxZ - entity.getEntityBoundingBox().minZ;
        double smin = Math.min(sx, Math.min(sy, sz)) / 2;
        AxisAlignedBB bb = new AxisAlignedBB(x - smin, y - smin, z - smin, x + smin, y + smin, z + smin);
        double avgX = (bb.minX + bb.maxX) / 2.0D;
        double avgY = (bb.minY + bb.maxY) / 2.0D;
        double avgZ = (bb.minZ + bb.maxZ) / 2.0D;
        int dimID = GCCoreUtil.getDimensionID(entity.worldObj);

        for (BlockVec3Dim blockVec : TileEntityShieldGenerator.loadedTiles)
        {
            if (blockVec != null && blockVec.dim == dimID)
            {
                TileEntity tile = blockVec.getTileEntity();

                if (tile instanceof TileEntityShieldGenerator)
                {
                    if (((TileEntityShieldGenerator) tile).inShield(avgX, avgY, avgZ))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void pushEntity(EntityLivingBase entity)
    {
        if (TileEntityShieldGenerator.shieldPos != null)
        {
            double d4 = entity.getDistance(TileEntityShieldGenerator.shieldPos.getX(), TileEntityShieldGenerator.shieldPos.getY(), TileEntityShieldGenerator.shieldPos.getZ());
            double d6 = entity.posX - TileEntityShieldGenerator.shieldPos.getX();
            double d8 = entity.posY - TileEntityShieldGenerator.shieldPos.getY();
            double d10 = entity.posZ - TileEntityShieldGenerator.shieldPos.getZ();
            double d11 = MathHelper.sqrt_double(d6 * d6 + d8 * d8 + d10 * d10);
            d6 /= d11;
            d8 /= d11;
            d10 /= d11;
            double knockAmount = 1.0D;
            double d13 = (0.0D - d4) * knockAmount;
            double d14 = d13;
            double knockSpeed = 10.0D;
            entity.motionX -= d6 * d14 / knockSpeed;
            entity.motionY -= d8 * d14 / knockSpeed;
            entity.motionZ -= d10 * d14 / knockSpeed;
            entity.attackEntityFrom(DamageSource.generic, 5.0F);
        }
    }

    private boolean inShield(double pX, double pY, double pZ)
    {
        double radius = this.shieldSize;
        double rX = this.getPos().getX() + 0.5D - pX;
        double rY = this.getPos().getZ() + 0.5D - pZ;
        double rZ = this.getPos().getY() + 0.5D - pY;
        radius *= radius;
        rX *= rX;
        pY *= pY;

        if (rX > radius)
        {
            return false;
        }
        if (rX + pY > radius)
        {
            return false;
        }
        return rX + pY + rZ * rZ < radius;
    }*/
}