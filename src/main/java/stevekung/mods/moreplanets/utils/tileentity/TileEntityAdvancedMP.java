package stevekung.mods.moreplanets.utils.tileentity;

import micdoodle8.mods.galacticraft.core.tile.TileEntityAdvanced;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityAdvancedMP extends TileEntityAdvanced
{
    @Override
    public int getPacketCooldown()
    {
        return 1;
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }
}