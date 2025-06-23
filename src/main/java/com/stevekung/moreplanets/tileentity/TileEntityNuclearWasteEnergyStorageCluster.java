package com.stevekung.moreplanets.tileentity;

import com.stevekung.moreplanets.blocks.BlockTieredEnergyStorageCluster.BlockType;
import com.stevekung.moreplanets.utils.tileentity.TileEntityEnergyStorageClusterMP;

public class TileEntityNuclearWasteEnergyStorageCluster extends TileEntityEnergyStorageClusterMP
{
    public TileEntityNuclearWasteEnergyStorageCluster()
    {
        super(50000000.0F, 7500.0F, 5, BlockType.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER.toString());
    }
}