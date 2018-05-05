package stevekung.mods.moreplanets.planets.diona.tileentity;

import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityTreasureChestMP;

public class TileEntityDionaTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityDionaTreasureChest()
    {
        super(4, "diona", DionaBlocks.DIONA_TREASURE_CHEST);
    }
}