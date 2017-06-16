package stevekung.mods.moreplanets.module.planets.chalos.tileentity;

import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.util.tileentity.TileEntityTreasureChestMP;

public class TileEntityChalosTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityChalosTreasureChest()
    {
        super(5, "chalos", ChalosBlocks.CHALOS_TREASURE_CHEST);
    }
}