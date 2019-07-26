package stevekung.mods.moreplanets.planets.nibiru.tileentity;

import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalConductor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class TileEntitySealableNuclearWasteRod extends TileBaseUniversalConductor
{
    private static float BC3_RATIO = 128.0F;
    private static float RF_RATIO = TileEntitySealableNuclearWasteRod.BC3_RATIO / 8.0F;

    /** ForgeEnergy **/
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        if (this.getNetwork() == null || EnergyConfigHandler.disableFEInput)
        {
            return 0;
        }
        float receiveGC = maxReceive * TileEntitySealableNuclearWasteRod.RF_RATIO;
        float sentGC = receiveGC - this.getNetwork().produce(receiveGC, !simulate, 1);
        return MathHelper.floor(sentGC / TileEntitySealableNuclearWasteRod.RF_RATIO);
    }

    @Override
    public int getMaxEnergyStored()
    {
        if (this.getNetwork() == null || EnergyConfigHandler.disableFEInput)
        {
            return 0;
        }
        return MathHelper.floor(this.getNetwork().getRequest(this) / TileEntitySealableNuclearWasteRod.RF_RATIO);
    }

    /** RedstoneFlux **/
    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
    {
        if (this.getNetwork() == null)
        {
            return 0;
        }
        float receiveGC = maxReceive * TileEntitySealableNuclearWasteRod.RF_RATIO;
        float sentGC = receiveGC - this.getNetwork().produce(receiveGC, !simulate, 1);
        return MathHelper.floor(sentGC / TileEntitySealableNuclearWasteRod.RF_RATIO);
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from)
    {
        if (this.getNetwork() == null)
        {
            return 0;
        }
        return MathHelper.floor(this.getNetwork().getRequest(this) / TileEntitySealableNuclearWasteRod.RF_RATIO);
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public int getTierGC()
    {
        return 5;
    }
}