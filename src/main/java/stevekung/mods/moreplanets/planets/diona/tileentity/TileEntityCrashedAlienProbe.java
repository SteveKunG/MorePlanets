package stevekung.mods.moreplanets.planets.diona.tileentity;

import java.util.Random;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.diona.blocks.BlockCrashedAlienProbe;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityRenderTickable;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class TileEntityCrashedAlienProbe extends TileEntityRenderTickable implements IInventoryDefaults
{
    protected ResourceLocation lootTable;
    protected long lootTableSeed;
    private NonNullList<ItemStack> containingItems = NonNullList.withSize(5, ItemStack.EMPTY);

    public TileEntityCrashedAlienProbe()
    {
        this.isContainer = true;
    }

    @Override
    public void update()
    {
        if (this.world.getBlockState(this.getPos()) == MPBlocks.CRASHED_ALIEN_PROBE.getDefaultState().withProperty(BlockCrashedAlienProbe.HAS_ALIEN, true))
        {
            super.update();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        if (!this.checkLootAndRead(nbt))
        {
            this.containingItems = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(nbt, this.containingItems);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if (!this.checkLootAndWrite(nbt))
        {
            ItemStackHelper.saveAllItems(nbt, this.containingItems);
        }
        return nbt;
    }

    @Override
    public String getName()
    {
        return LangUtils.translate("container.crashed_probe.name");
    }

    @Override
    public int getSizeInventory()
    {
        return this.containingItems.size();
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        this.fillWithLoot(null);
        return this.containingItems.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        this.fillWithLoot(null);
        ItemStack itemStack = ItemStackHelper.getAndSplit(this.containingItems, index, count);

        if (!itemStack.isEmpty())
        {
            this.markDirty();
        }
        return itemStack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        this.fillWithLoot(null);
        ItemStack oldstack = ItemStackHelper.getAndRemove(this.containingItems, index);

        if (!oldstack.isEmpty())
        {
            this.markDirty();
        }
        return oldstack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack)
    {
        this.fillWithLoot(null);
        this.containingItems.set(index, itemStack);

        if (itemStack.getCount() > this.getInventoryStackLimit())
        {
            itemStack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }

    @Override
    public boolean isEmpty()
    {
        this.fillWithLoot(null);

        for (ItemStack itemStack : this.containingItems)
        {
            if (!itemStack.isEmpty())
            {
                return false;
            }
        }
        return true;
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
    public boolean isItemValidForSlot(int slotID, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(this.getName());
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

    public void setLootTable(ResourceLocation lootTable, long seed)
    {
        this.lootTable = lootTable;
        this.lootTableSeed = seed;
    }
}