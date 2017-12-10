package stevekung.mods.moreplanets.tileentity;

import java.util.List;
import java.util.UUID;

import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.entities.IBubbleProvider;
import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
    private ItemStack[] containingItems = new ItemStack[4];
    @NetworkedField(targetSide = Side.CLIENT)
    public float shieldSize;
    @NetworkedField(targetSide = Side.CLIENT)
    public float prevShieldSize;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean shouldRender = true;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean enableShield = true;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean enableDamage = true;
    @NetworkedField(targetSide = Side.CLIENT)
    public int maxShieldSize = 16;
    @NetworkedField(targetSide = Side.CLIENT)
    public int shieldCapacity;
    @NetworkedField(targetSide = Side.CLIENT)
    public int maxShieldCapacity = 32000;
    @NetworkedField(targetSide = Side.CLIENT)
    public int shieldDamage = 8;
    @NetworkedField(targetSide = Side.CLIENT)
    public int shieldChargeCooldown = 1200;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean needCharged;
    @NetworkedField(targetSide = Side.CLIENT)
    public String ownerUUID = "";
    private boolean initialize = true;
    private static ShieldEvent EVENT;

    public TileEntityShieldGenerator()
    {
        EVENT = new ShieldEvent(this);
        this.storage.setMaxExtract(250);
        this.storage.setCapacity(100000.0F);
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!this.worldObj.isRemote)
        {
            MinecraftForge.EVENT_BUS.unregister(TileEntityShieldGenerator.EVENT);
        }
    }

    @Override
    public void onChunkUnload()
    {
        super.onChunkUnload();

        if (!this.worldObj.isRemote)
        {
            MinecraftForge.EVENT_BUS.unregister(TileEntityShieldGenerator.EVENT);
        }
    }

    @Override
    public void onLoad()
    {
        if (!this.worldObj.isRemote)
        {
            MinecraftForge.EVENT_BUS.register(TileEntityShieldGenerator.EVENT);
        }
    }

    @Override
    public void update()
    {
        super.update();
        this.renderTicks++;

        if (this.initialize)
        {
            this.renderTicks = this.renderTicks + this.worldObj.rand.nextInt(100);
            this.solarRotate = this.solarRotate + this.worldObj.rand.nextInt(360);
            this.initialize = false;
        }
        if (this.hasEnoughEnergyToRun && !this.disabled)
        {
            this.solarRotate++;
            this.solarRotate %= 360;
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.ALIEN_MINER_SPARK, this.pos.getX() + 0.5D, this.pos.getY() + 1.75D, this.pos.getZ() + 0.5D, new Object[] { -0.5F });

            if (this.ticks % 33 == 0)
            {
                this.worldObj.playSound(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), MPSounds.MACHINE_GENERATOR_AMBIENT, SoundCategory.BLOCKS, 0.075F, 1.0F);
            }
        }
        if (!this.worldObj.isRemote)
        {
            if (!this.needCharged && this.shieldCapacity == 0)
            {
                this.needCharged = true;
                this.shieldChargeCooldown = 1200;

                if (this.shieldChargeCooldown == 0)
                {
                    this.shieldCapacity = 100;
                }
            }
            if (!this.disabled && this.getEnergyStoredGC() > 0.0F && this.hasEnoughEnergyToRun)
            {
                if (this.shieldSize <= this.maxShieldSize)
                {
                    this.shieldSize += 0.1F;

                    if (this.shieldChargeCooldown > 0)
                    {
                        this.shieldChargeCooldown--;
                    }
                    if (this.shieldChargeCooldown == 0 && this.ticks % 2 == 0)
                    {
                        this.shieldCapacity += 100;
                        this.needCharged = false;
                    }
                }
            }
            else
            {
                this.shieldSize -= 0.1F;
            }

            if (this.shieldSize <= this.maxShieldSize)
            {
                this.shieldSize = Math.min(Math.max(this.shieldSize, 0.0F), this.maxShieldSize);
            }
            else
            {
                this.shieldSize -= 0.1F;
            }
            this.shieldCapacity = Math.min(Math.max(this.shieldCapacity, 0), this.maxShieldCapacity);
        }

        float range = this.shieldSize;
        List<Entity> entitiesMob = this.worldObj.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(this.pos.getX() - range, this.pos.getY() - range, this.pos.getZ() - range, this.pos.getX() + range, this.pos.getY() + range, this.pos.getZ() + range));

        for (Entity entity : entitiesMob)
        {
            if (!this.disabled && this.enableShield && this.shieldCapacity > 0)
            {
                if (entity instanceof EntityArrow && !(((EntityArrow)entity).shootingEntity instanceof EntityPlayer) && !((EntityArrow)entity).inGround || entity instanceof EntityPotion && !(((EntityPotion)entity).getThrower() instanceof EntityPlayer) || entity instanceof EntityFireball || entity instanceof EntityShulkerBullet)
                {
                    if (this.worldObj instanceof WorldServer)
                    {
                        ((WorldServer)this.worldObj).spawnParticle(EnumParticleTypes.CRIT_MAGIC, entity.posX, entity.posY, entity.posZ, 20, 0.0D, 0.5D, 0.0D, 1.0D);
                    }
                    float motion = MathHelper.sqrt_double(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
                    this.shieldCapacity -= motion * 2;
                    entity.setDead();
                }
            }
        }
    }

    @Override
    public boolean onActivated(EntityPlayer player)
    {
        return ((BlockShieldGenerator) MPBlocks.SHIELD_GENERATOR).onMachineActivated(this.worldObj, this.mainBlockPosition, MPBlocks.SHIELD_GENERATOR.getDefaultState(), player, player.getActiveHand(), player.getHeldItemMainhand(), player.getHorizontalFacing(), 0.0F, 0.0F, 0.0F);
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

        if (this.worldObj.isRemote && this.worldObj.rand.nextDouble() < 0.1D)
        {
            FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.up(), MPBlocks.DUMMY_BLOCK.getDefaultState().withProperty(BlockDummy.VARIANT, BlockDummy.BlockType.SHIELD_GENERATOR_TOP));
        }
        this.worldObj.destroyBlock(this.getPos(), true);
        this.worldObj.destroyBlock(thisBlock.up(), false);
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
        this.setBubbleVisible(nbt.getBoolean("ShieldVisible"));
        this.shieldSize = nbt.getFloat("ShieldSize");
        this.maxShieldSize = nbt.getInteger("MaxShieldSize");
        this.shieldDamage = nbt.getInteger("ShieldDamage");
        this.shieldCapacity = nbt.getInteger("ShieldCapacity");
        this.maxShieldCapacity = nbt.getInteger("MaxShieldCapacity");
        this.shieldChargeCooldown = nbt.getInteger("ShieldChargeCooldown");
        this.needCharged = nbt.getBoolean("NeedCharged");
        this.enableShield = nbt.getBoolean("EnableShield");
        this.enableDamage = nbt.getBoolean("EnableDamage");
        this.facing = nbt.getInteger("Facing");
        this.ownerUUID = nbt.getString("OwnerUUID");

        NBTTagList list = nbt.getTagList("Items", 10);
        this.containingItems = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            int slot = compound.getByte("Slot") & 255;

            if (slot < this.containingItems.length)
            {
                this.containingItems[slot] = ItemStack.loadItemStackFromNBT(compound);
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setBoolean("ShieldVisible", this.shouldRender);
        nbt.setFloat("ShieldSize", this.shieldSize);
        nbt.setInteger("MaxShieldSize", this.maxShieldSize);
        nbt.setInteger("ShieldDamage", this.shieldDamage);
        nbt.setInteger("ShieldCapacity", this.shieldCapacity);
        nbt.setInteger("MaxShieldCapacity", this.maxShieldCapacity);
        nbt.setInteger("ShieldChargeCooldown", this.shieldChargeCooldown);
        nbt.setBoolean("NeedCharged", this.needCharged);
        nbt.setBoolean("EnableShield", this.enableShield);
        nbt.setBoolean("EnableDamage", this.enableDamage);
        nbt.setInteger("Facing", this.facing);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.containingItems.length; ++i)
        {
            if (this.containingItems[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.containingItems[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        if (this.ownerUUID != null)
        {
            nbt.setString("OwnerUUID", this.ownerUUID);
        }
        nbt.setTag("Items", list);
        return nbt;
    }

    @Override
    public int getSizeInventory()
    {
        return this.containingItems.length;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.containingItems[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.containingItems[index] != null)
        {
            ItemStack itemStack;

            if (this.containingItems[index].stackSize <= count)
            {
                itemStack = this.containingItems[index];
                this.containingItems[index] = null;
                return itemStack;
            }
            else
            {
                itemStack = this.containingItems[index].splitStack(count);

                if (this.containingItems[index].stackSize == 0)
                {
                    this.containingItems[index] = null;
                }
                return itemStack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        if (this.containingItems[index] != null)
        {
            ItemStack itemStack = this.containingItems[index];
            this.containingItems[index] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack)
    {
        this.containingItems[index] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
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
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D) <= 64.0D;
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
        if (slot == 1)
        {
            return itemStack.getItem() == Items.REDSTONE;
        }
        else if (slot == 2)
        {
            return itemStack.getItem() == Items.ENDER_PEARL;
        }
        else if (slot == 3)
        {
            return itemStack.getItem() == Items.DIAMOND;
        }
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
        IBlockState state = this.worldObj.getBlockState(this.getPos());

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

    public String getStatus()
    {
        if (this.getEnergyStoredGC() == 0)
        {
            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.missingpower.name");
        }
        if (this.getDisabled(0))
        {
            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.ready.name");
        }
        if (this.getEnergyStoredGC() < this.storage.getMaxExtract())
        {
            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.missingpower.name");
        }
        if (this.needCharged)
        {
            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.shield_charging.name");
        }
        return EnumColor.BRIGHT_GREEN + GCCoreUtil.translate("gui.status.active.name");
    }

    protected boolean isInRangeOfShield(BlockPos pos)
    {
        double dx = this.pos.getX() + 0.5D - pos.getX();
        double dy = Math.abs(this.pos.getY() + 0.5D - pos.getY());
        double dz = this.pos.getZ() + 0.5D - pos.getZ();

        if (dx * dx + dz * dz <= this.shieldSize * this.shieldSize && dy <= this.shieldSize)
        {
            return true;
        }
        return false;
    }

    public static class ShieldEvent
    {
        private TileEntityShieldGenerator tile;

        public ShieldEvent(TileEntityShieldGenerator tile)
        {
            this.tile = tile;
        }

        @SubscribeEvent
        public void onLivingSpawn(LivingSpawnEvent.CheckSpawn event)
        {
            if (event.getResult() == Result.ALLOW) //TODO Check spawner
            {
                return;
            }
            if (this.tile.worldObj != null && !this.tile.worldObj.isRemote)
            {
                if (!this.tile.disabled && this.tile.isInRangeOfShield(event.getEntity().getPosition()))
                {
                    event.setResult(Result.DENY);
                }
            }
        }

        @SubscribeEvent
        public void onEnderTeleport(EnderTeleportEvent event)
        {
            if (!this.tile.disabled && this.tile.isInRangeOfShield(event.getEntity().getPosition()))
            {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public void onLivingUpdate(LivingUpdateEvent event)
        {
            Entity entity = event.getEntity();

            if (entity instanceof IMob)
            {
                if (!this.tile.disabled && this.tile.enableShield && this.tile.shieldCapacity > 0 && this.tile.isInRangeOfShield(event.getEntity().getPosition()))
                {
                    if (!this.tile.enableDamage)
                    {
                        double d4 = entity.getDistance(this.tile.pos.getX(), this.tile.pos.getY(), this.tile.pos.getZ());
                        double d6 = entity.posX - this.tile.pos.getX();
                        double d8 = entity.posY - this.tile.pos.getY();
                        double d10 = entity.posZ - this.tile.pos.getZ();
                        double d11 = MathHelper.sqrt_double(d6 * d6 + d8 * d8 + d10 * d10);
                        d6 /= d11;
                        d8 /= d11;
                        d10 /= d11;
                        double d13 = (0.0D - d4) * 2.0D / 10.0D;
                        double d14 = d13;
                        double knockSpeed = 10.0D;
                        entity.motionX -= d6 * d14 / knockSpeed;
                        entity.motionY -= d8 * d14 / knockSpeed;
                        entity.motionZ -= d10 * d14 / knockSpeed;
                    }

                    UUID uuid;

                    try
                    {
                        uuid = UUID.fromString(this.tile.ownerUUID);
                    }
                    catch (Exception e)
                    {
                        uuid = UUID.fromString("eef3a603-1c1b-4c98-8264-d2f04b231ef4"); //default uuid :)
                    }

                    if (uuid != null && this.tile.worldObj.getPlayerEntityByUUID(uuid) != null)
                    {
                        if (entity.ticksExisted % 8 == 0 && this.tile.worldObj instanceof WorldServer)
                        {
                            ((WorldServer)this.tile.worldObj).spawnParticle(EnumParticleTypes.CRIT_MAGIC, entity.posX, entity.posY, entity.posZ, 20, 0.0D, 0.5D, 0.0D, 1.0D);
                        }
                        if (this.tile.enableDamage)
                        {
                            entity.attackEntityFrom(DamageSource.causePlayerDamage(this.tile.worldObj.getPlayerEntityByUUID(uuid)), this.tile.shieldDamage);
                        }
                    }
                    else
                    {
                        if (entity.ticksExisted % 8 == 0 && this.tile.worldObj instanceof WorldServer)
                        {
                            ((WorldServer)this.tile.worldObj).spawnParticle(EnumParticleTypes.CRIT_MAGIC, entity.posX, entity.posY, entity.posZ, 20, 0.0D, 0.5D, 0.0D, 1.0D);
                        }
                        if (this.tile.enableDamage)
                        {
                            entity.attackEntityFrom(DamageSource.generic, this.tile.shieldDamage);
                        }
                    }
                    float motion = MathHelper.sqrt_double(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
                    this.tile.shieldCapacity -= motion * 2;
                }
            }
        }
    }
}