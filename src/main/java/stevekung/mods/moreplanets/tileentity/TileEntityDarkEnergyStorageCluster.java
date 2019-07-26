package stevekung.mods.moreplanets.tileentity;

import stevekung.mods.moreplanets.blocks.BlockTieredEnergyStorageCluster;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityEnergyStorageClusterMP;

public class TileEntityDarkEnergyStorageCluster extends TileEntityEnergyStorageClusterMP
{
    public TileEntityDarkEnergyStorageCluster()
    {
        super(12500000.0F, 2500.0F, 4, BlockTieredEnergyStorageCluster.BlockType.DARK_ENERGY_STORAGE_CLUSTER.toString());
    }
}