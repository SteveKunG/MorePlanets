package stevekung.mods.moreplanets.planets.chalos.tileentity;

import stevekung.mods.moreplanets.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityTreasureChestMP;

public class TileEntityChalosTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityChalosTreasureChest()
    {
        super(5, "chalos", ChalosBlocks.CHALOS_TREASURE_CHEST);
    }
}