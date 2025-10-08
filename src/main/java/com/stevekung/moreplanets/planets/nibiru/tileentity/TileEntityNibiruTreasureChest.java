package com.stevekung.moreplanets.planets.nibiru.tileentity;

import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.utils.tileentity.TileEntityTreasureChestMP;

import net.minecraft.item.Item;

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