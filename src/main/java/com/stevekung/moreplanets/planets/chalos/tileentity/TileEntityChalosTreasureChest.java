package com.stevekung.moreplanets.planets.chalos.tileentity;

import net.minecraft.item.Item;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.utils.tileentity.TileEntityTreasureChestMP;

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