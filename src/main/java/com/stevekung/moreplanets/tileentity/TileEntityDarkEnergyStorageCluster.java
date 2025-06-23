package com.stevekung.moreplanets.tileentity;

import com.stevekung.moreplanets.blocks.BlockTieredEnergyStorageCluster;
import com.stevekung.moreplanets.utils.tileentity.TileEntityEnergyStorageClusterMP;

public class TileEntityDarkEnergyStorageCluster extends TileEntityEnergyStorageClusterMP
{
    public TileEntityDarkEnergyStorageCluster()
    {
        super(12500000.0F, 2500.0F, 4, BlockTieredEnergyStorageCluster.BlockType.DARK_ENERGY_STORAGE_CLUSTER.toString());
    }
}