package stevekung.mods.moreplanets.planets.diona.tileentity;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityTreasureChestMP;

public class TileEntityDionaTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityDionaTreasureChest()
    {
        super("diona", MPBlocks.DIONA_TREASURE_CHEST);
    }

    @Override
    public Item getDungeonKey()
    {
        return MPItems.DIONA_DUNGEON_KEY;
    }
}