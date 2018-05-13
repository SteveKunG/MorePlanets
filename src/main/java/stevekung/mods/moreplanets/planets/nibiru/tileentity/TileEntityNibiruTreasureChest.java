package stevekung.mods.moreplanets.planets.nibiru.tileentity;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityTreasureChestMP;

public class TileEntityNibiruTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityNibiruTreasureChest()
    {
        super("nibiru", MPBlocks.NIBIRU_TREASURE_CHEST);
    }

    @Override
    public Item getDungeonKey()
    {
        return MPItems.NIBIRU_DUNGEON_KEY;
    }
}