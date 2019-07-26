package stevekung.mods.moreplanets.tileentity;

import stevekung.mods.moreplanets.blocks.BlockTieredEnergyStorageCluster.BlockType;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityEnergyStorageClusterMP;

public class TileEntityNuclearWasteEnergyStorageCluster extends TileEntityEnergyStorageClusterMP
{
    public TileEntityNuclearWasteEnergyStorageCluster()
    {
        super(50000000.0F, 7500.0F, 5, BlockType.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER.toString());
    }
}