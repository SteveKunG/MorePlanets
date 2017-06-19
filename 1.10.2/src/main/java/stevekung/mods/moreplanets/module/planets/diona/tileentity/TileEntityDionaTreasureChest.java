package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.util.tileentity.TileEntityTreasureChestMP;

public class TileEntityDionaTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityDionaTreasureChest()
    {
        super(4, "diona", DionaBlocks.DIONA_TREASURE_CHEST);
    }
}