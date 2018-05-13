package stevekung.mods.moreplanets.planets.chalos.tileentity;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityTreasureChestMP;

public class TileEntityChalosTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityChalosTreasureChest()
    {
        super("chalos", MPBlocks.CHALOS_TREASURE_CHEST);
    }

    @Override
    public Item getDungeonKey()
    {
        return MPItems.CHALOS_DUNGEON_KEY;
    }
}