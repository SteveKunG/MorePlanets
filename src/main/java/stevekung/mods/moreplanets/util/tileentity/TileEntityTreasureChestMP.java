package stevekung.mods.moreplanets.util.tileentity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.item.IKeyable;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.tile.TileEntityAdvanced;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPSounds;

public class TileEntityTreasureChestMP extends TileEntityAdvanced implements IKeyable, IInteractionObject
{
    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;
    private Block block;
    private ResourceLocation lootTable;
    private long lootTableSeed;

    @NetworkedField(targetSide = Side.CLIENT)
    public int tier = 1;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean locked = true;

    public TileEntityTreasureChestMP(int tier, String name, Block block)
    {
        super("container." + name + ".treasurechest.name");
        this.inventory = NonNullList.withSize(27, ItemStack.EMPTY);
        this.tier = tier;
        this.block = block;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        this.fillWithLoot((EntityPlayer)null);
        return super.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        this.fillWithLoot((EntityPlayer)null);
        return super.decrStackSize(index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        this.fillWithLoot((EntityPlayer)null);
        return super.removeStackFromSlot(index);
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack)
    {
        this.fillWithLoot((EntityPlayer)null);
        super.setInventorySlotContents(index, stack);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        this.locked = nbt.getBoolean("isLocked");
        this.tier = nbt.getInteger("tier");

        if (!this.checkLootAndRead(nbt))
        {
            super.readFromNBT(nbt);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setBoolean("isLocked", this.locked);
        nbt.setInteger("tier", this.tier);

        if (!this.checkLootAndWrite(nbt))
        {
            super.writeToNBT(nbt);
        }
        return nbt;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void update()
    {
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        ++this.ticksSinceSync;
        float f;

        if (this.locked)
        {
            this.numPlayersUsing = 0;
        }

        if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            f = 5.0F;
            List list = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(i - f, j - f, k - f, i + 1 + f, j + 1 + f, k + 1 + f));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

                if (entityplayer.openContainer instanceof ContainerChest)
                {
                    IInventory iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();

                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).isPartOfLargeChest(this))
                    {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        f = this.world.provider instanceof IGalacticraftWorldProvider ? 0.05F : 0.1F;
        double d2;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
        {
            double d1 = i + 0.5D;
            d2 = k + 0.5D;
            this.world.playSound((EntityPlayer)null, d1, j + 0.5D, d2, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.provider instanceof IGalacticraftWorldProvider ? this.world.rand.nextFloat() * 0.1F + 0.6F : this.world.rand.nextFloat() * 0.1F + 0.9F);
        }

        if ((this.numPlayersUsing == 0 || this.locked) && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f1 = this.lidAngle;

            if (this.numPlayersUsing == 0 || this.locked)
            {
                this.lidAngle -= f;
            }
            else
            {
                this.lidAngle += f;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;

            if (this.lidAngle < f2 && f1 >= f2)
            {
                d2 = i + 0.5D;
                double d0 = k + 0.5D;
                this.world.playSound((EntityPlayer)null, d2, j + 0.5D, d0, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.provider instanceof IGalacticraftWorldProvider ? this.world.rand.nextFloat() * 0.1F + 0.6F : this.world.rand.nextFloat() * 0.1F + 0.9F);
            }
            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
        super.update();
    }

    @Override
    public boolean receiveClientEvent(int id, int type)
    {
        if (id == 1)
        {
            this.numPlayersUsing = type;
            return true;
        }
        else
        {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
        if (!player.isSpectator())
        {
            if (this.numPlayersUsing < 0)
            {
                this.numPlayersUsing = 0;
            }
            ++this.numPlayersUsing;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
        }
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
        if (!player.isSpectator() && this.getBlockType() == this.block)
        {
            --this.numPlayersUsing;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        this.updateContainingBlockInfo();
    }

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    @Override
    public String getGuiID()
    {
        return "moreplanets:treasure_chest";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
    {
        this.fillWithLoot(player);
        return new ContainerChest(playerInventory, this, player);
    }

    @Override
    public void clear()
    {
        this.fillWithLoot((EntityPlayer)null);
        super.clear();
    }

    @Override
    public int getTierOfKeyRequired()
    {
        return this.tier;
    }

    @Override
    public boolean onValidKeyActivated(EntityPlayer player, ItemStack key, EnumFacing facing)
    {
        if (this.locked)
        {
            this.locked = false;

            if (this.world.isRemote)
            {
                player.playSound(MPSounds.UNLOCK_TREASURE_CHEST, 1.0F, 1.0F);
            }
            else
            {
                if (!player.capabilities.isCreativeMode && player.inventory.getCurrentItem().getCount() == 0)
                {
                    player.inventory.getCurrentItem().shrink(1);
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onActivatedWithoutKey(EntityPlayer player, EnumFacing facing)
    {
        if (this.locked)
        {
            if (player.world.isRemote)
            {
                GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_ON_FAILED_CHEST_UNLOCK, GCCoreUtil.getDimensionID(this.world), new Object[] { this.getTierOfKeyRequired() }));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canBreak()
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentString(this.getName());
    }

    @Override
    public double getPacketRange()
    {
        return 20.0D;
    }

    @Override
    public int getPacketCooldown()
    {
        return 3;
    }

    @Override
    public boolean isNetworkedTile()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return new AxisAlignedBB(this.getPos().add(-1, 0, -1), this.getPos().add(2, 2, 2));
    }

    @Override
    public int[] getSlotsForFace(EnumFacing facing)
    {
        return new int[] {};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing facing)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack itemStack, EnumFacing facing)
    {
        return false;
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    protected boolean checkLootAndRead(NBTTagCompound compound)
    {
        if (compound.hasKey("LootTable", 8))
        {
            this.lootTable = new ResourceLocation(compound.getString("LootTable"));
            this.lootTableSeed = compound.getLong("LootTableSeed");
            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean checkLootAndWrite(NBTTagCompound compound)
    {
        if (this.lootTable != null)
        {
            compound.setString("LootTable", this.lootTable.toString());

            if (this.lootTableSeed != 0L)
            {
                compound.setLong("LootTableSeed", this.lootTableSeed);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    protected void fillWithLoot(@Nullable EntityPlayer player)
    {
        if (this.lootTable != null)
        {
            LootTable loottable = this.world.getLootTableManager().getLootTableFromLocation(this.lootTable);
            this.lootTable = null;
            Random random;

            if (this.lootTableSeed == 0L)
            {
                random = new Random();
            }
            else
            {
                random = new Random(this.lootTableSeed);
            }

            LootContext.Builder lootcontext$builder = new LootContext.Builder((WorldServer)this.world);

            if (player != null)
            {
                lootcontext$builder.withLuck(player.getLuck());
            }

            loottable.fillInventory(this, random, lootcontext$builder.build());
        }
    }

    public ResourceLocation getLootTable()
    {
        return this.lootTable;
    }

    public void setLootTable(ResourceLocation lootTable, long seed)
    {
        this.lootTable = lootTable;
        this.lootTableSeed = seed;
    }

    public static TileEntityTreasureChestMP findClosest(Entity entity, int tier)
    {
        double distance = Double.MAX_VALUE;
        TileEntityTreasureChestMP chest = null;

        for (TileEntity tile : entity.world.loadedTileEntityList)
        {
            if (tile instanceof TileEntityTreasureChestMP && ((TileEntityTreasureChestMP) tile).getTierOfKeyRequired() == tier)
            {
                double dist = entity.getDistanceSq(tile.getPos().getX() + 0.5, tile.getPos().getY() + 0.5, tile.getPos().getZ() + 0.5);

                if (dist < distance)
                {
                    distance = dist;
                    chest = (TileEntityTreasureChestMP) tile;
                }
            }
        }
        return chest;
    }
}