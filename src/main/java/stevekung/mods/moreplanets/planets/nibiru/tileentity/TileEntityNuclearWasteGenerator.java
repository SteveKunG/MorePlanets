package stevekung.mods.moreplanets.planets.nibiru.tileentity;

import java.util.*;

import micdoodle8.mods.galacticraft.api.tile.IDisableableMachine;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectricalSource;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class TileEntityNuclearWasteGenerator extends TileBaseUniversalElectricalSource implements IConnector, IDisableableMachine
{
    public int maxGenerate = 50000;
    @NetworkedField(targetSide = Side.CLIENT)
    public float generateTick;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean disabled = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public int disableCooldown = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean missingTank;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean missingWaste;
    private int alertTick;
    public static final Map<BlockPos, IBlockState> multiBlockLists = new HashMap<>();
    public final List<BlockPos> multiTileClientLists = new ArrayList<>();
    public final Map<BlockPos, IBlockState> multiBlockClientLists = new HashMap<>();
    public boolean initMultiBlock;

    static
    {
        multiBlockLists.put(new BlockPos(1, -1, 0), MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(1, -1, 1), MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(1, -1, -1), MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(-1, -1, -1), MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(-1, -1, 1), MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(-1, -1, 0), MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(0, -1, 1), MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(0, -1, -1), MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState());

        multiBlockLists.put(new BlockPos(3, 0, 0), MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState());
        multiBlockLists.put(new BlockPos(2, 0, 2), MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState());
        multiBlockLists.put(new BlockPos(2, 0, -2), MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState());

        multiBlockLists.put(new BlockPos(-3, 0, 0), MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState());
        multiBlockLists.put(new BlockPos(-2, 0, -2), MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState());
        multiBlockLists.put(new BlockPos(-2, 0, 2), MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState());

        multiBlockLists.put(new BlockPos(0, 0, 3), MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState());
        multiBlockLists.put(new BlockPos(0, 0, -3), MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState());
    }

    public TileEntityNuclearWasteGenerator()
    {
        super("container.nuclear_waste_generator.name");
        this.inventory = NonNullList.withSize(1, ItemStack.EMPTY);
        this.initMultiBlock = true;
        this.setTierGC(4);
        this.storage.setCapacity(100000000.0F);
        this.storage.setMaxExtract(this.maxGenerate);
    }

    @Override
    public int getPacketCooldown()
    {
        return 1;
    }

    @Override
    public void update()
    {
        super.update();
        this.alertTick++;

        if (this.initMultiBlock)
        {
            this.multiTileClientLists.add(new BlockPos(3, 0, 0));
            this.multiTileClientLists.add(new BlockPos(2, 0, 2));
            this.multiTileClientLists.add(new BlockPos(2, 0, -2));
            this.multiTileClientLists.add(new BlockPos(-3, 0, 0));
            this.multiTileClientLists.add(new BlockPos(-2, 0, -2));
            this.multiTileClientLists.add(new BlockPos(-2, 0, 2));
            this.multiTileClientLists.add(new BlockPos(0, 0, 3));
            this.multiTileClientLists.add(new BlockPos(0, 0, -3));
            this.multiBlockClientLists.putAll(multiBlockLists);
            this.initMultiBlock = false;
        }

        for (BlockPos renderPos : ClientEventHandler.WASTE_RENDER_POS)
        {
            this.multiBlockClientLists.entrySet().removeIf(entry -> this.world.isRemote && this.pos.equals(renderPos) && this.world.getBlockState(this.pos.add(entry.getKey())) == entry.getValue());
            this.multiTileClientLists.removeIf(pos -> this.world.isRemote && this.pos.equals(renderPos) && this.world.getTileEntity(this.pos.add(pos)) != null && this.world.getTileEntity(this.pos.add(pos)).getClass().equals(TileEntityNuclearWasteTank.class));
        }

        if (!this.world.isRemote)
        {
            if (!this.disabled)
            {
                if (this.generateTick > 0)
                {
                    this.receiveEnergyGC(null, this.generateTick, false);

                    for (int x = -1; x < 2; x++)
                    {
                        for (int z = -1; z < 2; z++)
                        {
                            if (this.world.rand.nextInt(5000000) == 0 && this.getWaste(this.getPos().add(x, -1, z)))
                            {
                                this.world.setBlockState(this.getPos().add(x, -1, z), Blocks.OBSIDIAN.getDefaultState());
                            }
                        }
                    }
                    for (int x = -3; x < 4; x++)
                    {
                        for (int z = -3; z < 4; z++)
                        {
                            if (this.getTank(this.getPos().add(x, 0, z)))
                            {
                                this.world.setBlockState(this.getPos().add(x, 0, z), MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState());

                                if (this.world.getTileEntity(this.getPos().add(x, 0, z)) instanceof TileEntityNuclearWasteTank)
                                {
                                    TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) this.world.getTileEntity(this.getPos().add(x, 0, z));
                                    tank.hasRod = this.world.rand.nextInt(5000000) != 0;
                                }
                            }
                        }
                    }

                    this.ticks = this.ticks + this.world.rand.nextInt(2);

                    if (this.ticks % 33 == 0)
                    {
                        this.world.playSound(null, this.getPos(), MPSounds.MACHINE_GENERATOR_AMBIENT, SoundCategory.BLOCKS, 0.05F, 1.0F);
                    }
                    if (this.alertTick % 100 == 0)
                    {
                        if (this.missingWaste || this.missingTank)
                        {
                            this.world.playSound(null, this.getPos(), MPSounds.MACHINE_ALERT, SoundCategory.BLOCKS, 5.0F, 1.0F);
                        }
                    }
                }

                this.missingWaste = !this.hasValidWaste();
                this.missingTank = !this.hasValidTank();

                if (this.hasValidSource())
                {
                    this.generateTick = Math.min(this.generateTick + Math.max(this.generateTick * 0.005F, 1.0F), this.maxGenerate);
                }
                else
                {
                    this.generateTick = Math.max(this.generateTick - 10.0F, 0);
                }
                this.generateTick = Math.min(Math.max(this.generateTick, 0.0F), this.getMaxEnergyStoredGC());
            }
            this.produce();
            this.recharge(this.getInventory().get(0));
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.generateTick = nbt.getFloat("GenerateTick");
        this.setDisabled(0, nbt.getBoolean("Disabled"));
        this.disableCooldown = nbt.getInteger("DisabledCooldown");
        this.missingTank = nbt.getBoolean("MissingTank");
        this.missingWaste = nbt.getBoolean("MissingWaste");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setFloat("GenerateTick", this.generateTick);
        nbt.setInteger("DisabledCooldown", this.disableCooldown);
        nbt.setBoolean("Disabled", this.getDisabled(0));
        nbt.setBoolean("MissingTank", this.missingTank);
        nbt.setBoolean("MissingWaste", this.missingWaste);
        return nbt;
    }

    @Override
    public float receiveElectricity(EnumFacing from, float energy, int tier, boolean doReceive)
    {
        return 0;
    }

    @Override
    public EnumSet<EnumFacing> getElectricalInputDirections()
    {
        return EnumSet.noneOf(EnumFacing.class);
    }

    @Override
    public EnumSet<EnumFacing> getElectricalOutputDirections()
    {
        return EnumSet.of(this.getElectricOutputDirection());
    }

    @Override
    public EnumFacing getElectricOutputDirection()
    {
        return EnumFacing.DOWN;
    }

    @Override
    public boolean canConnect(EnumFacing direction, NetworkType type)
    {
        if (direction == null || type != NetworkType.POWER)
        {
            return false;
        }
        return direction == this.getElectricOutputDirection();
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
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[] { 0 };
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, EnumFacing side)
    {
        return slot == 0;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return slot == 0 && ItemElectricBase.isElectricItem(itemStack.getItem());
    }

    public int getScaledElecticalLevel(int i)
    {
        return (int) Math.floor(this.getEnergyStoredGC() * i / this.getMaxEnergyStoredGC());
    }

    public String getStatus()
    {
        if (this.getDisabled(0))
        {
            return TextFormatting.GOLD + LangUtils.translate("gui.status.disabled.name");
        }
        if (this.missingTank && this.missingWaste)
        {
            return TextFormatting.RED + LangUtils.translate("gui.status.waste_and_tank_depleted.name");
        }
        if (this.missingTank)
        {
            return TextFormatting.RED + LangUtils.translate("gui.status.waste_tank_depleted.name");
        }
        if (this.missingWaste)
        {
            return TextFormatting.RED + LangUtils.translate("gui.status.waste_depleted.name");
        }
        if (this.generateTick > 0)
        {
            return TextFormatting.DARK_GREEN + LangUtils.translate("gui.status.collectingenergy.name");
        }
        return TextFormatting.DARK_RED + LangUtils.translate("gui.status.no_waste_sources.name");
    }

    private boolean hasValidSource()
    {
        return this.hasValidWaste() && this.hasValidTank();
    }

    private boolean hasValidTank()
    {
        return this.getTank(this.getPos().add(3, 0, 0)) && this.getTank(this.getPos().add(-3, 0, 0)) && this.getTank(this.getPos().add(0, 0, 3)) && this.getTank(this.getPos().add(0, 0, -3)) && this.getTank(this.getPos().add(2, 0, 2)) && this.getTank(this.getPos().add(-2, 0, 2)) && this.getTank(this.getPos().add(2, 0, -2)) && this.getTank(this.getPos().add(-2, 0, -2));
    }

    private boolean hasValidWaste()
    {
        return this.getWaste(this.getPos().add(1, -1, 0)) && this.getWaste(this.getPos().add(-1, -1, 0)) && this.getWaste(this.getPos().add(0, -1, 1)) && this.getWaste(this.getPos().add(0, -1, -1)) && this.getWaste(this.getPos().add(1, -1, 1)) && this.getWaste(this.getPos().add(-1, -1, 1)) && this.getWaste(this.getPos().add(-1, -1, -1)) && this.getWaste(this.getPos().add(1, -1, -1));
    }

    private boolean getWaste(BlockPos pos)
    {
        return this.world.getBlockState(pos) == MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState();
    }

    private boolean getTank(BlockPos pos)
    {
        if (this.world.getBlockState(pos) == MPBlocks.NUCLEAR_WASTE_TANK.getDefaultState())
        {
            return this.world.getTileEntity(pos) instanceof TileEntityNuclearWasteTank && ((TileEntityNuclearWasteTank)this.world.getTileEntity(pos)).hasRod;
        }
        return false;
    }
}