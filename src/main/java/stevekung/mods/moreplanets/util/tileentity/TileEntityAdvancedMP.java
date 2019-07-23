package stevekung.mods.moreplanets.util.tileentity;

import micdoodle8.mods.galacticraft.core.tile.TileEntityAdvanced;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityAdvancedMP extends TileEntityAdvanced
{
    public TileEntityAdvancedMP(String tileName)
    {
        super(tileName);
    }

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