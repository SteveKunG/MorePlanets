package stevekung.mods.moreplanets.module.planets.nibiru.tileentity;

import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.tileentity.TileEntityTreasureChestMP;

public class TileEntityNibiruTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityNibiruTreasureChest()
    {
        super(6, "nibiru", NibiruBlocks.NIBIRU_TREASURE_CHEST);
    }
}