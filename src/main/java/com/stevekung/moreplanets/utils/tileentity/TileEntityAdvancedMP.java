package com.stevekung.moreplanets.utils.tileentity;

import net.minecraft.nbt.NBTTagCompound;

import micdoodle8.mods.galacticraft.core.tile.TileEntityAdvanced;

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