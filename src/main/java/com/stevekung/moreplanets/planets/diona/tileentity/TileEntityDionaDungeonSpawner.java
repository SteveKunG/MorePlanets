package com.stevekung.moreplanets.planets.diona.tileentity;

import com.stevekung.moreplanets.planets.diona.entity.EntityInfectedPurloniteSlimeBoss;
import com.stevekung.moreplanets.utils.tileentity.TileEntityDungeonSpawnerMP;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityDionaDungeonSpawner extends TileEntityDungeonSpawnerMP<EntityInfectedPurloniteSlimeBoss>
{
    public TileEntityDionaDungeonSpawner()
    {
        super(EntityInfectedPurloniteSlimeBoss.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        try
        {
            this.bossClass = (Class<EntityInfectedPurloniteSlimeBoss>) Class.forName(nbt.getString("bossClass"));
        }
        catch (Exception e)
        {
            if (!this.world.isRemote)
            {
                e.printStackTrace();
            }
        }
        super.readFromNBT(nbt);
    }
}