package com.stevekung.moreplanets.planets.diona.tileentity;

import net.minecraft.item.Item;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.utils.tileentity.TileEntityTreasureChestMP;

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